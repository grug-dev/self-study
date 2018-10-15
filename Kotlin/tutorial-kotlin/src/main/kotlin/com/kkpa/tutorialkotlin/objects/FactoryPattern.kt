package com.kkpa.tutorialkotlin.objects

fun main(args: Array<String>) {
    val lowerCase = FactoryPattern.upperOrLowerCase("Converting to" , true)
    println(lowerCase.type)

    val upperCase = FactoryPattern.upperOrLowerCase("Converting to" )
    println(upperCase.type)


}


class FactoryPattern private  constructor( val type : String) {

    companion object {
        fun upperOrLowerCase ( str : String , lowerCase : Boolean = false) : FactoryPattern {
            return if ( lowerCase) FactoryPattern(str.toLowerCase()) else FactoryPattern(str.toUpperCase())
        }
    }

}
