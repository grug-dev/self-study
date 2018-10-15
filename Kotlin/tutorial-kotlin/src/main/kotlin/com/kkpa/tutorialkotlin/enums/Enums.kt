package com.kkpa.tutorialkotlin.enums

fun main(args: Array<String>) {

    val myDpto = Department.HR.getDptoInfo()
    println(myDpto)


}

enum class Department (val fullName : String, val numEmployeess : Int ) {
    HR("Human Resources" , 100),
    IT("Info Tech " , 39)
    ;

     fun getDptoInfo() : String = "The ${fullName} - ${numEmployeess}"
}
