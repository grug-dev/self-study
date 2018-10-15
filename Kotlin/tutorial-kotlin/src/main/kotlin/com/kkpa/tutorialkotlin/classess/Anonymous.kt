package com.kkpa.tutorialkotlin.classess


fun main(args: Array<String>) {
    var oneHoundred = 99

    wantsSomeInterface( object : SomeInterface{
        override fun mustImplement(num: Int): String {
            oneHoundred++
            return "Anonymous Implemented: ${num*oneHoundred}"
        }
    })
}


interface SomeInterface {
    fun mustImplement (num : Int) : String
}

fun wantsSomeInterface( si : SomeInterface) {
    println( " Printing from wantsSomeInterface ${si.mustImplement(22)}")
}
