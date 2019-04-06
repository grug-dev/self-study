package com.kkpa.tutorialkotlin.strings

import java.util.*


fun main(args: Array<String>) {
    var name : String = "We A ";
    name = name.replace("\\s".toRegex(),"")
    println("New Name ${name}")

    val a = "category_type_code"
    val b = "CATEGORY_TYPE_CODE"

    println(a.equals(b))
    println(a.equals(b, ignoreCase = true))
    println(a.equals(b, ignoreCase = true))

    var message : String? = null

    register(message.plus("Another Thin"))

    var optionalClass = OptionalClass()
    optionalClass.simple = null
    val result = Optional.ofNullable(optionalClass).map {
        it.simple
    }.orElse("Nullo")

    println("Result using Optional: ${result}")
}

fun register(message: String) {
    println("Registering: ${message}")
}


class OptionalClass {

    var simple:String? = null

}


class StringWorker {


    fun verifyIsString(msg: Any): Boolean {
        return if (msg is String) true else false
    }

    fun concat(firstWord: String, secondWord: String): String {

        val text = """
    |Tell me and I forget.
    |Teach me and I remember.
    |Involve me and I learn.
    |(Benjamin Franklin)
    """.trimMargin()

        println(text)

        return "$firstWord $secondWord"
    }


}