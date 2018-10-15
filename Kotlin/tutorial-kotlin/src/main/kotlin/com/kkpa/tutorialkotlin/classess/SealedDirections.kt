package com.kkpa.tutorialkotlin.classess

fun main(args: Array<String>) {
    val myobj = ChildrenDirection()
    myobj.log()

}

class ChildrenDirection : Directions() {
    override fun log() {
       println("My Custom Log  ")

        var mydirection = getDirectionBy(NONE)
        mydirection.log()

        mydirection = getDirectionBy(TOP)
        mydirection.log()
    }

}

val NONE : String = "NONE"
val TOP = "TOP"
sealed class Directions {


    abstract  fun log ()

    fun getDirectionBy( type : String ) : Directions {
        return when(type) {
            TOP -> Directions.Top
            NONE -> Directions.None
            else -> Directions.Default
        }
    }

    object  Default : Directions() {
        override fun log() {
            println (" Default ")
        }
    }

    object None : Directions() {
        override fun log() {
            println( "None")
        }
    }

    object Top : Directions () {
        override fun log() {
            println ( "Top")
        }
    }

}

