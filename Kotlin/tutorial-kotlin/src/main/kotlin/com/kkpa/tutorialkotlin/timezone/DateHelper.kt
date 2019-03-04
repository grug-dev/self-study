package com.kkpa.tutorialkotlin.timezone

import com.kkpa.tutorialkotlin.timezone.RequestLogging.getTzOffsetRequestInMinutes
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.lang.reflect.Constructor
import java.time.*


const val UTC = "UTC"

@Service
data class DateHelper @Autowired constructor (val clock: Clock, val offSet: InterfaceForObject) {


    private val PACIFIC_APIA_TZ_OFFSET_IN_MINS = LocalDateTime.now().atZone(ZoneId.of(PACIFIC_APIA_ZONEID)).offset.totalSeconds / 60
    private val ETC_GMTTZ_OFFSET_IN_MINS = LocalDateTime.now().atZone(ZoneId.of(ETC_GMT_ZONEID)).offset.totalSeconds / 60



    fun currentDayAtRequestZoneId(): LocalDate {
        println("Clock: ${Instant.now(clock)} - TzOffset: ${offSet.getTzOffsetRequestInMinutes()}")
        if (getTzOffsetRequestInMinutes() !in ETC_GMTTZ_OFFSET_IN_MINS..PACIFIC_APIA_TZ_OFFSET_IN_MINS) throw IllegalArgumentException("Timezone offset unknown")
        return Instant.now(clock).plusSeconds(60L * offSet.getTzOffsetRequestInMinutes()).atZone(ZoneId.of(UTC)).toLocalDate()
    }
}