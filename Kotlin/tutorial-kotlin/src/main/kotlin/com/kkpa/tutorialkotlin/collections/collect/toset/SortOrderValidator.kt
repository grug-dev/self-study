package com.kkpa.tutorialkotlin.collections.collect.toset


import java.util.Arrays
import java.util.stream.Collectors
import java.util.stream.Stream

interface SortOrderValidator {

    /**
     * Check if sorting order is valid.
     * @return True if valid, otherwise false.
     */
    val isValid: Boolean

    companion object {
        val directions : Set<String> =  Arrays.stream(SortDirection.values()).map { d -> d.name }.collect(Collectors.toSet())

        }

}
