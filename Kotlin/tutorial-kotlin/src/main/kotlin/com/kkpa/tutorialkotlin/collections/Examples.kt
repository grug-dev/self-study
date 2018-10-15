package com.kkpa.tutorialkotlin.collections

fun main(args: Array<String>) {

    //workingWithList()
    val strings = listOf( "spring" , "summer")

    val emptylist = emptyList<String>()
    println(emptylist.javaClass) // class kotlin.collections.EmptyList

    strings.also {
        println(it.javaClass) // class java.util.Arrays$ArrayList
    }

    mutableListOf(1,2,3).apply {
        println(this.javaClass) // class java.util.ArrayList
    }

}

