package com.kkpa.tutorialkotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.time.Clock



@SpringBootApplication
class TutorialKotlinApplication

fun main(args: Array<String>) {
    runApplication<TutorialKotlinApplication>(*args)
}

@Bean
fun clock(): Clock {
    return Clock.systemDefaultZone()
}

@Bean
fun getClock(): Clock {
    return Clock.systemUTC()
}
