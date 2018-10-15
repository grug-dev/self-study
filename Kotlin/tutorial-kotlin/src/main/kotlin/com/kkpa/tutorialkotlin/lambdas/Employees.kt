package com.kkpa.tutorialkotlin.lambdas

fun getEmployees() :  List<Employee> {
    val employees = listOf(Employee("John", "Smith", 2012),
            Employee("Jane", "Wilson", 2015),
            Employee("Mary", "Johnson", 2010),
            Employee("Mike", "Jones", 2002))

    return employees
}

data class Employee(val firstName: String, val lastName: String, val startYear: Int) {


}
