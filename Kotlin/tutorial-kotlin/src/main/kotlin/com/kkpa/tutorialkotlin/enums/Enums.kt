package com.kkpa.tutorialkotlin.enums

fun main(args: Array<String>) {

    val myDpto = Department.HR.getDptoInfo(1L , "email@ss.com")
    println(myDpto)

    val something = Department.HR

    if (something == Department.HR) {
        println("Department HR")
    }


}

enum class Department(val fullName: String, val numEmployeess: Int) {
    HR("Human Resources", 100),
    IT("Info Tech ", 39)
    ;

    fun getDptoInfo(vararg arguments : Any): String = "The ${fullName} - ${numEmployeess} - ${arguments.forEach { print(" ${it} ") }}"
}
