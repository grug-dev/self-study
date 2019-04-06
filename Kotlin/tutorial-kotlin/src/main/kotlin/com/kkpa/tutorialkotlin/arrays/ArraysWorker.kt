package com.kkpa.tutorialkotlin.arrays

import org.springframework.data.domain.Sort
import java.util.ArrayList


fun main(args: Array<String>) {

    var myArray = arrayOf("2" , "Hola")

    myArray.forEach {
        println(it)
    }

    val orders = ArrayList<String>()
    orders.add("Hello")
    orders.add("World")
    orders.forEach { println(it) }

}