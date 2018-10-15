package com.kkpa.tutorialkotlin.lambdas

import org.springframework.stereotype.Component

fun main(args: Array<String>) {


    val emp = findEmployeeMinByStartYear(getEmployees())
    println(emp)
}

fun findEmployeeMinByStartYear(employees: List<Employee>) : Employee? {
    //  employees.minBy { it.startYear }
    //  employees.minBy { e -> e.startYear }
    return employees.minBy(Employee::startYear)
}



@Component
class LambdasWorker {


    fun isPersonAdult ( person : Person): Boolean {

        fun Person.isAdult () = age >= 21
        val predicate = Person::isAdult

        return person.isAdult()

    }

}

