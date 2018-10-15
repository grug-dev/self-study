package com.kkpa.tutorialkotlin.objects

fun main(args: Array<String>) {
    println(SomeClass.accessPrivateVar())
}


class SomeClass {

    companion object {
        private val privateVar = 5

        fun accessPrivateVar() {
            println(" I'm accessing  private var': ${privateVar} ")
        }

    }


}
