package com.kkpa.tutorialkotlin.functions

fun main(args: Array<String>) {
    val withFunction = WithFunction()

    withFunction.withNumber(2) {
        withFunction.withString("mas") {
            println("Finish!")
        }
    }
}

class WithFunction {

    fun <RetType> withNumber(number: Int, block: () -> RetType): RetType {
        println("WithNumber ${number}")
        return block.invoke()
    }

    fun <RetType> withString(cadena: String, block: () -> RetType): RetType {
        println("withString ${cadena}")
        return block.invoke()
    }

}