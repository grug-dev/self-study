package com.sofi.aggregation.client.util

import com.sofi.aggregation.api.util.AMPERSAND
import com.sofi.aggregation.api.util.EQUALS_TOKEN
import java.io.UnsupportedEncodingException
import java.net.URLEncoder
import java.util.LinkedHashMap
import java.util.Objects
import java.util.stream.Collectors


/**
 * Param client helper class
 */
class ParamBuilder {

    // TODO: Why do we have our own code to do this instead of using a library?

    private val params: MutableMap<String, MutableList<Any>>

    init {
        this.params = LinkedHashMap()
    }

    fun addParameter(param: String, value: Any?): ParamBuilder {
        Objects.requireNonNull(param, "Param cannot be null")
        if (value != null) {
            params.compute(param) { k, v ->
                if (v == null) {
                    return@compute mutableListOf(value)
                }
                v.add(value)
                v
            }
        }

        return this
    }

    fun build(): String {
        val paramString = params.entries.stream()
                .flatMap { e -> e.value.stream().map { v -> encodeURL(e.key) + EQUALS_TOKEN + encodeURL(v) } }
                .collect(Collectors.joining(AMPERSAND))

        return if (paramString.isEmpty()) paramString else "?$paramString"
    }

    private fun encodeURL(`object`: Any): String {
        try {
            return URLEncoder.encode(`object` as? String ?: `object`.toString(), "UTF-8")
        } catch (e: UnsupportedEncodingException) {
            throw RuntimeException("UTF-8 throws unexpected unsupported encoding exception")
        }

    }
}
