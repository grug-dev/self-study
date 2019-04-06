package com.kkpa.tutorialkotlin.enums

fun main(args: Array<String>) {

    val myDpto = Department.HR.getDptoInfo(1L , "email@ss.com")
    println(myDpto)

    val something = Department.HR

    if (something == Department.HR) {
        println("Department HR")
    }

    val txCategoryGroup = TransactionCategoryGroup.fromValue("exp4ense")
    when (txCategoryGroup) {
        TransactionCategoryGroup.EXPENSE -> println("Found!")
        else -> println("Not Found!")
    }
    println()

}

enum class TransactionCategoryGroup(val groupName:String) {
    EXPENSE("expense");

    companion object {
        fun fromValue(value: String): TransactionCategoryGroup? {
            TransactionCategoryGroup.values().forEach {
                if (it.groupName === value) {
                    return it
                }
            }
            return null// not found
        }
    }

}

enum class Department(val fullName: String, val numEmployeess: Int) {
    HR("Human Resources", 100),
    IT("Info Tech ", 39)
    ;

    fun getDptoInfo(vararg arguments : Any): String = "The ${fullName} - ${numEmployeess} - ${arguments.forEach { print(" ${it} ") }}"
}
