package com.kkpa.tutorialkotlin.lambdas

import java.util.*

fun main(args: Array<String>) {

    println(usingWith())

    println("Apply ${usingApply()} ")
    usingApplyConcated()

    println("Find BY LastName ${findByLastName(getEmployees(), "Wilson")}" )
    println("Find BY LastName ${findByLastName(getEmployees(), "Peña")}" )

}

fun usingWith() : String {
    val numbers = StringJoiner(",")
    return with( numbers) {
        for (i in 1..10) add(" " + i)
        toString()
    }
}

fun usingApply() : String {
    return  StringJoiner(" - ").apply () {
        for ( i in 1..10) add( " " + i)
    }.toString()
}

fun usingApplyConcated () {
    var someString : String ? = null
    var anotherString : String ? =  null

    someString  = "Some String"
    anotherString = "Another String"

    someString.apply somestring@ {
        anotherString.apply {
            println(toLowerCase())
            println( this@somestring.toUpperCase())

        }
    }

}


fun findByLastName ( employees : List<Employee> , lastName : String) : String {

    return employees.find { e -> e.lastName.equals(lastName) }?.toString() ?: "${lastName} Not Found"

}
