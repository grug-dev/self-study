package com.kkpa.tutorialkotlin.timezone

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalUnit

const val OFF_SET = 720L

fun main(args: Array<String>) {

    println("UTC: ${LocalDate.now(ZoneId.of("UTC"))} - Offset: ${LocalDateTime.now().atZone(ZoneId.of("UTC")).offset.totalSeconds} ")

    val ahora = LocalDate.now()

    println(" Ahora ${ahora} - TimeZone: ${ahora.atSofiTimeZone()} - ${ahora.isEqual(ahora.atSofiTimeZone())}")

}

fun LocalDate.atSofiTimeZone(): LocalDate = this.atStartOfDay().plusMinutes(OFF_SET ).toLocalDate()


