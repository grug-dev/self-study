package com.kkpa.tutorialkotlin.generics

fun main(args: Array<String>) {

    val numbers = 1..10
    numbers.toList().printCollection()

    val strins = arrayListOf("Hello", "World")
    if (strins is List<String>) { // In java we can't do this.
        strins.printCollection()
    }

    numbers.toList().printPowOf(2)

    println(append(StringBuilder("String 1"), StringBuilder("String 2")))


    val listAny = listOf(1,2,3)
    if ( listAny is List<*>) {
        val listAsString = listAny as List<String>
        listAsString.printCollection()
    }

}

fun <T> List<T>.printCollection() {
    this.forEach(::println)
}


fun <T> append(item1: T, item2: T)
        where T : CharSequence, T : Appendable {
    println(" Result is ${item1.append(item2)}")
}

fun <T : Number> List<T>.printPowOf(pow: Int) {
    forEach({
        val result = it.toInt() * pow
        print("  ${result}")
    })
}
