package com.kkpa.tutorialkotlin.interfaces

fun main(args: Array<String>) {
    val myClass  = MyClass()
    myClass.usingInterface()
}

class MyClass: MyInterface {

    override val ANOTHER_NUMBER: Int
        get() = 85

    fun usingInterface() {
        println(" ${NUMBER_FROM_MYINTERFACE  - ANOTHER_NUMBER} ")
    }

}
