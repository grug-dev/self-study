package com.kkpa.tutorialkotlin.numbers

import java.math.BigDecimal
import java.math.BigInteger
import java.math.RoundingMode
import java.time.Instant
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {
    println(Instant.now().minusSeconds(TimeUnit.HOURS.toSeconds(9)))

    arrayListOf<BigDecimal>(BigDecimal(-1.5),BigDecimal(-0.98)).also {
        it.map { amount -> amount.abs() }.forEach {
            if (it.compareTo(BigDecimal.ONE) < 1)
                println("Bad amount ${it}")
        }
    }



}