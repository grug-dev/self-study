package com.kkpa.tutorialkotlin.generics

import java.math.BigDecimal

fun main(args: Array<String>) {

    val mixedList: List<Any> = listOf("string", 1, BigDecimal(22.5), "fall",
            BigDecimal(-5938.393849))

    val bigDecimalsOnly = getElementsOfType<BigDecimal>(mixedList)
    println(bigDecimalsOnly)

}

inline fun <reified T> getElementsOfType( list: List<Any>) : List<Any> {
    val newList = mutableListOf<Any>()

    for ( x in list) {
        if ( x is T) {
            newList.add(x)
        }
    }

    return newList.toList()
}


