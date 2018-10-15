package com.kkpa.tutorialkotlin.classess

val MY_CONSTANT = 100

fun main(args: Array<String>) {
    val c : Constructors = Constructors()
    c.testConstructors()
}

class Constructors {

    fun testConstructors() {
        val emp = Employee("FirstName")
        emp.fullTime = false
        println("Printing Employee ${emp} ")

        val empFullTime = Employee("Second Name" , true)
        println("Printing Employee ${empFullTime} ")

    }

}

private class Employee ( val firstName : String ) {

    var fullTime : Boolean = false
    // If we want a custom get/set we need to do it after declaration
    // We need to reference the backend field.
    get() {
        println ("Running the custom get of FullTime")
        return field
    }
    set(value) {
        println("Setting the custom set of FullTime ${this}")
        field = value
    }


    constructor( firstName : String , fullTime : Boolean) : this (firstName) {
        this.fullTime = fullTime
    }

    override fun toString(): String {
        return "Employee(firstName='$firstName' , fullTime=$fullTime)"
    }


}



