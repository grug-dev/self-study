package com.kkpa.tutorialkotlin.json

import com.kkpa.tutorialkotlin.jackson.Car
import com.kkpa.tutorialkotlin.jackson.CarEngine
import com.kkpa.tutorialkotlin.jackson.CarExample.CarFleet
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner


@SpringBootTest
@RunWith(SpringRunner::class)
class TestJson() {



    val cars  by lazy<MutableList<Car>> {
        val myCars = mutableListOf<Car>()
        for (i in 1..10) {
            val car1 = Car(name = "Name-${i}", model = "Model-${i}" , cost = 3500L , colors = arrayListOf("Black","White"),
                    carEngine = CarEngine( type = "Type" , power = "Power"))
            myCars.add(car1)
        }
         myCars
    }

    @Test
    fun writeJsonFile(){
        val carFleet = CarFleet(cars = cars)

        carFleet.writeJsonFile()

    }

}