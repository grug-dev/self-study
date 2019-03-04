package com.kkpa.tutorialkotlin.dates

import com.kkpa.tutorialkotlin.timezone.DateHelper
import com.kkpa.tutorialkotlin.timezone.InterfaceForObject
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.springframework.boot.test.context.SpringBootTest
import java.time.Clock
import java.time.Instant
import java.time.ZoneId


@RunWith(MockitoJUnitRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class DateHelperTests2 {

    @Mock
    var clock: Clock = Clock.fixed(Instant.parse("2018-03-31T23:00:00.00Z"), ZoneId.of("UTC"))


    @Mock
    lateinit var myRequestLoggin: InterfaceForObject


    lateinit var dateHelperMock: DateHelper

    @Test
    fun test_with_mock() {
        val mock = mock<InterfaceForObject>()

        whenever(mock.getTzOffsetRequestInMinutes()).thenReturn(42)

        val answer = mock.getTzOffsetRequestInMinutes()

        junit.framework.Assert.assertEquals(42, answer)
    }

    @Before
    fun setup() {
        val mock = mock<InterfaceForObject>()
        whenever(mock.getTzOffsetRequestInMinutes()).thenReturn(42)
        MockitoAnnotations.initMocks(this)
    }


    @Test
    fun mockDateHelper() {
        println(Instant.now(clock))
        dateHelperMock = DateHelper(clock, myRequestLoggin)
        whenever(myRequestLoggin.getTzOffsetRequestInMinutes()).thenReturn(577)
        val currentDay = dateHelperMock.currentDayAtRequestZoneId()
        println(" ${currentDay}  - Mock ${dateHelperMock.currentDayAtRequestZoneId()}")

    }



}

