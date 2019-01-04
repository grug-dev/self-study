package com.kkpa.tutorialkotlin.jackson.CarExample

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.kkpa.tutorialkotlin.jackson.Car
import com.kkpa.tutorialkotlin.jackson.CarEngine
import java.io.File

fun main(args: Array<String>) {
    CarFleet(mutableListOf()).writeJsonFile()
}

data class CarFleet(var cars: MutableList<Car>) {




    fun writeJsonFile() {
        val cars  by lazy<MutableList<Car>> {
            val myCars = mutableListOf<Car>()
            for (i in 1..10) {
                val car1 = Car(name = "Name-${i}", model = "Model-${i}" , cost = 3500L , colors = arrayListOf("Black","White"),
                        carEngine = CarEngine( type = "Type" , power = "Power"))
                myCars.add(car1)
            }
            myCars
        }

        this.cars = cars
        val mapper : ObjectMapper = jacksonObjectMapper()
        val file = File("output/carFleet.json")
        file.createNewFile()
        mapper.writeValue( file , this)
    }

}