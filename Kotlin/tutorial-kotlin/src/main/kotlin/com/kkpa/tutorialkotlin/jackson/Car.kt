package com.kkpa.tutorialkotlin.jackson

data class Car(private val name : String,
               private val model : String,
               private val cost : Long,
               private val colors : List<String>,
               private val carEngine : CarEngine) {


    override fun toString(): String {
        return "${name}"
    }

}