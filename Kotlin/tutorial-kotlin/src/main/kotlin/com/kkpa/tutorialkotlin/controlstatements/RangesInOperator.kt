package com.kkpa.tutorialkotlin.controlstatements

fun main(args: Array<String>) {

    for ( i in 0..10) {
        println(i)
    }

    val backwardRange = 5.downTo(1)
    for ( i in backwardRange) {
        print("  ${i}") // 5,4,3,2,1
    }

    val rangeTo = 3.rangeTo(5)
    println("**")
   for ( i in rangeTo) {
       print(" ${i}") // 3 , 4 , 5
   }

    println("**")
    val fi = 1..10
    val steps = fi.step(2)
    for ( x in steps) {
        print(" ${x}") // 1 ,3 ,5 ,7 9
    }


    val seasons = arrayOf("Spring", "Summer")
    println( "Winter" in seasons) // false


    // For Each Index
    seasons.forEachIndexed { index, value ->
        println(" Season ${index} - ${value}")
    }


    cycleNames()


}

fun  cycleNames() {

    for ( i in 1..3) {
        println("i is ${i}")
        jloop@ for ( j in 4..6) {
            println("j is ${j}")
             kloop@       for ( k in 6..8) {
                        println("k is ${k}")
                        if ( k == 7) {
                        break@kloop
                    }
                }
        }
    }

}
