package com.kkpa.tutorialkotlin.classess

sealed class Directions {

    abstract  fun log ()

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

