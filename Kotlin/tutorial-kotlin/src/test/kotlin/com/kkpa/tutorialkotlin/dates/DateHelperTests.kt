package com.kkpa.tutorialkotlin.dates

import com.kkpa.tutorialkotlin.classess.SomeInterface
import com.kkpa.tutorialkotlin.getClock
import com.kkpa.tutorialkotlin.timezone.DateHelper
import com.kkpa.tutorialkotlin.timezone.InterfaceForObject
import com.kkpa.tutorialkotlin.timezone.RequestLogging
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.Spy
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import java.time.Clock
import java.time.Instant
import java.time.ZoneId


@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ContextConfiguration(classes = [RequestLogging::class])
class DateHelperTests {

    var clock: Clock = Clock.fixed(Instant.parse("2018-03-31T23:00:00.00Z"), ZoneId.of("UTC"))


    lateinit var dateHelperMock: DateHelper

    @Mock
    lateinit var requestLoggin: RequestLogging

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun test_with_mock() {
        val mock = mock<InterfaceForObject>()

        whenever(mock.getTzOffsetRequestInMinutes()).thenReturn(42)

        val answer = mock.getTzOffsetRequestInMinutes()

        assertEquals(42, answer)
    }

    @Test
    fun mockDateHelper() {
        println(Instant.now(clock))
        dateHelperMock = DateHelper(clock,requestLoggin)
        whenever(requestLoggin.getTzOffsetRequestInMinutes()).thenAnswer { 85 }
        val currentDay = dateHelperMock.currentDayAtRequestZoneId()
        println(" ${currentDay}  - Mock ${dateHelperMock.currentDayAtRequestZoneId()}")
    }



}

