package com.kkpa.tutorialkotlin.strings

private val NORMALIZE_REGEX = "[^0-9a-zA-Z ]*".toRegex()

fun main(args: Array<String>) {
    println(joinNotBlank("master","185", separator = "-"))
}

fun joinNotBlank(vararg messages: Any?, separator: String = ": "): String {
    return messages
        .asSequence()
        .map { it?.toString().blankToNull() }
        .filterNotNull()
        .distinct()
        .joinToString(separator)
}


/**
 * Coerces blank or empty strings to null, passing nulls through unaltered.
 */
fun String?.blankToNull(): String? {
    return when {
        this.isNullOrBlank() -> null
        else -> this
    }
}