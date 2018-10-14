package com.kkpa.tutorialkotlin.lambdas

import org.springframework.stereotype.Component

@Component
class LambdasWorker {


    fun isPersonAdult ( person : Person): Boolean {

        fun Person.isAdult () = age >= 21
        val predicate = Person::isAdult

        return person.isAdult()

    }

}