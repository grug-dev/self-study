package com.kkpa.tutorialkotlin.dates

import java.time.Instant
import java.util.*
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {
    val delay = getMinutes(10)

    println("Instant EPOCH ${Instant.EPOCH} - Now: ${Instant.now()}")

    println ( "Ten Minutes in Millis  ${TimeUnit.MINUTES.toMillis(10)} ")
    println ( "Ten Minutes in Seconds  ${TimeUnit.MINUTES.toSeconds(10)} ")
    val TEN_MINUTES_IN_SECONDS = TimeUnit.MINUTES.toSeconds(10)

    println( "Ten Minutes in Seconds ${TEN_MINUTES_IN_SECONDS} - To Millis : ${secondsToMillis(TEN_MINUTES_IN_SECONDS)}")

    val now = Instant.now()
    println("Now: ${Date.from(now)} - Delay :${Date.from(now.plusMillis(delay))}  Minus Seconds: ${Date.from(now.minusSeconds((600)))}" )
}

fun getMinutes( minutes: Int ) : Long {

    return (60000 * minutes).toLong()
}

private fun secondsToMillis( seconds : Long) : Long{
    return TimeUnit.SECONDS.toMillis(seconds)
}