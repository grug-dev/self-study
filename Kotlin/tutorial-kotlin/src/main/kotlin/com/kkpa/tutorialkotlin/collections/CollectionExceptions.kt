package com.kkpa.tutorialkotlin.collections

import java.lang.Exception

fun main(args: Array<String>) {
    val mycollection = arrayListOf<String>()

    fun notStart(s: String): Boolean {
        println(s)
        return false
    }

    mycollection.filterNot(::notStart)

    var hello = null
    mycollection.whenNotNullNorEmpty {
        val a = mycollection.firstOrNull { it == "Hello" }
        println(a)
    }

    val a = mycollection.firstOrNull { it == "Hello" }
    println("Value ${a}")

    println("Do something : ${doSomething(arrayListOf())} ")


    // java.util.NoSuchElementException: Collection contains no element matching the predicate.
    val b = mycollection.first { it == "Hello" }
    println("Value ${b}")




}

inline fun  <E: Any, T: Collection<E>> T?.whenNotNullNorEmpty(func: (T) -> Unit) {
    if (this != null && this.isNotEmpty()) {
        func(this)
    }
}


fun doSomething(myCollection: List<String>): String? {
    return try {
        myCollection.firstOrNull {it == "H"}
                ?.let { "Found! ${it}" }

    } catch (e: Exception) {
        e.printStackTrace()
        "Not Found H"
    }
}