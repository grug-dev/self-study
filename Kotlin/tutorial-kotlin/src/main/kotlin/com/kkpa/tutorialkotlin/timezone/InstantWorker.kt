package com.kkpa.tutorialkotlin.timezone

import java.time.*
import jdk.nashorn.internal.objects.NativeDate.getSeconds
import java.time.temporal.ChronoUnit

fun main(args: Array<String>) {

    println("Boolean ${"true".toBoolean()}")

    println(Instant.now().plusMillis(604800000L))
    println(LocalDateTime.now().plus(604800000L,ChronoUnit.MILLIS))

    val clock = Clock.fixed(Instant.parse("2018-09-06T15:00:00.00Z"), ZoneId.of("UTC"))
    println("Instant Using Clock :${Instant.now(clock)}")

    if (1 in 1..3) {
        println("Yes")
    }

    println(Instant.now().plusSeconds(60L * 720).atZone(ZoneId.of("UTC")).toLocalDateTime())

    println("Zona System Default : ${ZoneId.systemDefault()} ")

    val instantNow = Instant.now()
    println("Instant now: ${instantNow} vs LocalDateTime${LocalDateTime.now()}")

    val colOffset = ZoneOffset.ofHoursMinutes(-5,0)
    println("ZoneOffSet ${LocalDate.now(colOffset)} ZoneOffset Instant: ${Instant.now().atOffset(colOffset).toLocalDate()}")

    // Japan = UTC+9
    val jpTime = instantNow.atZone(ZoneId.of("Asia/Tokyo"))

    println("ZonedDateTime Japan: $jpTime - Offset ${jpTime.offset} - InstantUTCFromJapanToUTC : ${jpTime.toInstant()}")

    println("OffsetDateTime ${OffsetDateTime.now().plusMinutes(0)}")

    println("Pacific Time: ${ Instant.now().atZone(ZoneId.of("US/Pacific"))}")

    val oneHours = Duration.ofDays(1)
    println(oneHours.toMinutes())

}