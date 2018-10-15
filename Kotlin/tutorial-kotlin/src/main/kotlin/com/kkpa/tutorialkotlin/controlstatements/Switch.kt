package com.kkpa.tutorialkotlin.controlstatements

import java.math.BigDecimal

fun main(args: Array<String>) {

    val obj : Any = "I'm a string"
    val obj2 : Any =  BigDecimal(25.2)
    val obj3 : Any = 45

    val something : Any = obj2


    when(something) {
        is String -> {  println(something.toUpperCase()) }
        is BigDecimal -> { println( something.add(BigDecimal(19.0)))}
        else -> { println( "Type no knowledge")}
    }

}
