package com.kkpa.tutorialkotlin.timezone

import java.util.stream.Collectors
import java.time.ZoneId
import java.time.LocalDateTime
import java.time.ZoneOffset
import jdk.nashorn.internal.objects.NativeArray.forEach
import org.apache.catalina.util.ConcurrentDateFormat.GMT
import java.math.BigDecimal
import java.time.Instant
import kotlin.streams.toList

const val ETC_GMT_ZONEID = "Etc/GMT+12"
const val PACIFIC_APIA_ZONEID = "Pacific/Apia"

private val PACIFIC_APIA_TZ_OFFSET_IN_MINS = LocalDateTime.now().atZone(ZoneId.of(PACIFIC_APIA_ZONEID)).offset.totalSeconds/60
private val ETC_GMTTZ_OFFSET_IN_MINS = LocalDateTime.now().atZone(ZoneId.of(ETC_GMT_ZONEID)).offset.totalSeconds/60

fun main(args: Array<String>) {

    println(BigDecimal(5).abs())
    println(BigDecimal(-5).abs())

    val min = BigDecimal("5").abs().negate()
    println(min)

    println(BigDecimal("-5").abs().negate())



    println(Instant.now().minusSeconds(60L * 300).atZone(ZoneId.of(UTC)).toLocalDateTime())
    println(Instant.now().minusSeconds(60L * -300).atZone(ZoneId.of(UTC)).toLocalDateTime())

    val now = LocalDateTime.now()
    println("${LocalDateTime.now().atZone(ZoneId.of("Etc/GMT+12")).offset.totalSeconds/60} - ${ETC_GMTTZ_OFFSET_IN_MINS}")
    println("${LocalDateTime.now().atZone(ZoneId.of("Pacific/Apia")).offset.totalSeconds/60} - ${PACIFIC_APIA_TZ_OFFSET_IN_MINS}")

    val tzOffsetRequestInMinutes = 840
    if (tzOffsetRequestInMinutes !in ETC_GMTTZ_OFFSET_IN_MINS..PACIFIC_APIA_TZ_OFFSET_IN_MINS) throw IllegalArgumentException("Timezone offset unknown")

    val display = TimezoneDisplay()

    println("Time zones in UTC:")
    val utc = display.getTimeZoneList(
            OffsetBase.UTC)
    utc.forEach{ println(it) }



    /*
      println("Time zones in GMT:")
    val gmt = display.getTimeZoneList(
            TimezoneDisplay.OffsetBase.GMT)
    gmt.forEach(Consumer<String> { println(it) })
     */

}

enum class OffsetBase {
    GMT, UTC
}

private class ZoneComparator : Comparator<ZoneId> {

    override fun compare(zoneId1: ZoneId, zoneId2: ZoneId): Int {
        val now = LocalDateTime.now()
        val offset1 = now.atZone(zoneId1).offset
        val offset2 = now.atZone(zoneId2).offset

        return offset1.compareTo(offset2)
    }
}
class TimezoneDisplay() {

    fun getTimeZoneList(base: OffsetBase): List<String> {

        val now = LocalDateTime.now()
        return ZoneId.getAvailableZoneIds().stream()
                .map(
                    ZoneId::of
                )
                .sorted(ZoneComparator())
                .map { id ->
                    String.format(
                            "(%s%s) %s",
                            base, getOffset(now, id), id.id)
                }
                .toList()
    }

    private fun getOffset(dateTime: LocalDateTime, id: ZoneId): String {
        val clientTZOffset = dateTime.atZone(id).offset.totalSeconds/60

        if (clientTZOffset !in ETC_GMTTZ_OFFSET_IN_MINS..PACIFIC_APIA_TZ_OFFSET_IN_MINS) {
            println("${id.id} - offsetInMinutes: ${clientTZOffset}")
        }

        return dateTime
                .atZone(id)
                .offset
                .id
                .replace("Z", "+00:00")
    }
}