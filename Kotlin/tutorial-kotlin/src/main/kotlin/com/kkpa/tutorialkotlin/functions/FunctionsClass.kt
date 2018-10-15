package com.kkpa.tutorialkotlin.functions

import com.kkpa.tutorialkotlin.extensions.upperFirstAndLast


    fun main(args: Array<String>) {
        printNumbers(1,2,3,4,5 , message = "Printing my numbers")

        val manyNumbers = arrayOf(1,2,3,4)
        printNumbers(*manyNumbers , message = "Using ARray using spread operator (*) ")

        // Using spread operator to unpack arrays
        val numbersToFive = arrayOf(1,2,3,4,5)
        val numbresToTen = arrayOf(6,7,8,9,10)

        var numbers = arrayOf(numbersToFive, numbresToTen , 11 , 12)
        printNumbers(numbers = *numbers, message = "Numbers UnPacked , the two arrays are going to be continuing as array")

        numbers = arrayOf(*numbersToFive, *numbresToTen , 11 , 12)
        printNumbers( numbers = *numbers , message = "Numbers packed")

    }


    fun printNumbers(vararg numbers : Any , message : String) {
        println(message.upperFirstAndLast())
        for ( number in numbers) {
            println(number)
        }
    }


