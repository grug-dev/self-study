package com.kkpa.tutorialkotlin.timezone

import org.springframework.stereotype.Component


@Component
object RequestLogging: InterfaceForObject {

    override fun getTzOffsetRequestInMinutes(): Int = 0

}

interface InterfaceForObject {
    fun getTzOffsetRequestInMinutes(): Int
}