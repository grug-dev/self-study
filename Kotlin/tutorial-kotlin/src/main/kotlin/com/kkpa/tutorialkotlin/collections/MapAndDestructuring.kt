package com.kkpa.tutorialkotlin.collections


fun main(args: Array<String>) {

    val immutableMap = mapOf(
            1 to Car("green" , " Toyota" , 2015) ,
            2 to Car("red" , "Ford" , 2016) ,
            3 to Car("silver" , "Honda" , 2013)
    )

    println(immutableMap.javaClass) // class java.util.LinkedHashMap
    println(immutableMap)


    val mutableMap = mutableMapOf(1 to Car("green" , " Toyota" , 2015) ,
            2 to Car("red" , "Ford" , 2016) ,
            3 to Car("silver" , "Honda" , 2013)
    )
    mutableMap.put(4, Car("black" , "Honda" , 2014))
    println(mutableMap.javaClass) // class java.util.LinkedHashMap


    val cadenas = mutableMapOf("BranchName" to " ", "BuildNumber" to " ")
    cadenas.keys.forEach { it.toUpperCase() }
    println(cadenas)

    // Hashmap
    val mutableHashMap =  hashMapOf(1 to Car("green" , " Toyota" , 2015) ,
            2 to Car("red" , "Ford" , 2016) ,
            3 to Car("silver" , "Honda" , 2013)
    )
    println(mutableHashMap.javaClass) // class java.util.HashMap

    val carsFiltered = mutableHashMap.filter { it.value.year > 2013 }
    println("Filter over map Cars  $carsFiltered ")


    val fordColors = mutableHashMap.filter { it.value.model == "Ford" }.map {
        it.value.color
    }
    println("Ford Colors: $fordColors ")


    // Desctructuring
    val pair = Pair(10, "ten")
    val ( key , value ) = pair

    for (entry in mutableHashMap) {
        val (key , value ) = entry
        println(" Key: ${key} - Value : ${value}")
    }

    for ( ( key , value ) in mutableMap) {
        println(" Key: ${key} - Value : ${value}")
    }

    // Destructuring Data Class
    val (color, model , year ) = Car("silver" , "Honda" , 2013)
    println( "Color: ${color} - Model   ${model}  - Year ${year} ")


    // Destructuring Class
    val myCarWithoutDataType = CarWithoutData("silver" , "Honda" , 2013)
    val (color2, model2 , year2 ) = myCarWithoutDataType
    println( "Color: ${color2} - Model   ${model2}  - Year ${year2} ")

}

data class Car ( val color : String, val model : String , val year : Int)

class CarWithoutData ( val color : String, val model : String , val year : Int) {

    // In order to allow destructuring
    operator  fun component1() = color
    operator  fun component2() = model
    operator  fun component3() = year

}

