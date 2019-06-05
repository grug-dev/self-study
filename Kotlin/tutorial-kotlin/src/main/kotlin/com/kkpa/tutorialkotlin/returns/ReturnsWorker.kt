package com.kkpa.tutorialkotlin.returns

import java.util.Random

fun main(args: Array<String>) {

    val r = Random()
    println(r.nextInt(10))




    returnFromForEach()

    returnFromLoophUsingBrek()

    breakromForEach()

    breakromForEachNull()
}

fun returnFromForEach() {
    listOf(1, 2, 3, 4, 5).forEach {
        if (it == 3) return@forEach // local return to the caller of the lambda, i.e. the forEach loop
        print(it)
    }
    println(" done with implicit label")
}

fun returnFromLoophUsingBrek() {
    println("Using Break")
    numbers@ for (i in 1..50) {
        print(i)
        if (i > 10) break@numbers
    }
}

fun breakromForEach() {
    println()
    println("Breaking from ForEach")
    run accounts@{
        listOf(1, 2, 3, 4, 5).forEach {
            if (it == 4) return@accounts
            print(it)
        }
    }
    println("")
    println("Finishing break from ForEach")
}

fun breakromForEachNull() {
    println()
    println("Breaking from ForEach with Null")
    run accounts@{
        listOf(1, 2, null, 4, 5).forEach {
            it ?: return@accounts
            print(it)
        }
    }
    println("")
    println("Finishing break from ForEach With Null")
}