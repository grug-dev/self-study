package com.kkpa.tutorialkotlin.classess

fun main(args: Array<String>) {
    val c : Constructors = Constructors()
    println(c.testConstructors())
}

private class Employee ( val firstName : String ) {

    var fullTime : Boolean = false

    constructor( firstName : String , fullTime : Boolean) : this (firstName) {
        this.fullTime = fullTime
    }

}



class Constructors {

    fun testConstructors() {
        val emp = Employee("FirstName")

        val empFullTime = Employee("FirstName" , true)
        println(empFullTime.fullTime)

    }

}
