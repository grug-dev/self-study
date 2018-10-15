package com.kkpa.tutorialkotlin.collections

fun main(args: Array<String>) {

    //
    val immutableNumbers = setOf(10,10,20,30)
    val newNumbers = immutableNumbers.plus(40)
    println(newNumbers.minus(10))

    println(newNumbers.average())

    // Drop First 3 elements from the set.
    println(newNumbers.drop(3))


    // muttables
    val mutableSet = mutableSetOf(50,100)
    mutableSet.add(150)
    println(mutableSet)

    mutableSet.plus(200) // return new collection
    println(mutableSet)

    mutableSet.filter {
        it < 100
    }.forEach( ::println)

    val myset = mutableSetOf<Int>()




}
