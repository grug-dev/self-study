package com.kkpa.tutorialkotlin.sequences

import com.kkpa.tutorialkotlin.collections.Car


// Hashmap
val mutableHashMap =  hashMapOf(1 to Car("green" , " Toyota" , 2015) ,
        2 to Car("red" , "Ford" , 2016) ,
        16 to Car("silver" , "Honda" , 2013),
        3 to Car("black" , "Mazda" , 2013)
)

fun main(args: Array<String>) {

    val result = listOf("Joe", "Mary" , "Jane").asSequence()
            .filter {
                println( "Filtering $it")
                it[0].toUpperCase() == 'J'
            }
            .map {
                println( "Mapping $it") ; it.toUpperCase()
            }
            .toList()
    println("Result $result ")


    val colorsFord = mutableHashMap.filter { it.value.model == "Ford" }.map { it.value.color }



    val myCar = mutableHashMap.filter { it.value.model == "Ford" }.map { it.value.color }.also {
        it[0]
    }

}
