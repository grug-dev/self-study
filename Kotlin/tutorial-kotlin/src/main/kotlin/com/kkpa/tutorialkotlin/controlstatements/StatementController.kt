package com.kkpa.tutorialkotlin.controlstatements

import org.springframework.stereotype.Component

@Component
class StatementController {

    var counter = 0 // Note: the initializer assigns the backing field directly
        set(value) {
            if (value >= 0) field = value
        }


    fun sumOf ( initial : Int , final : Int) : Int {
        var sum : Int = 0
        for ( i in initial until final) {
            sum += i
        }
        return sum
    }

    fun sumOfClosed ( initial : Int , final : Int) : Int {
        var sum : Int = 0
        for ( i in initial..final step 1) {
            sum += i
        }
        return sum
    }
}