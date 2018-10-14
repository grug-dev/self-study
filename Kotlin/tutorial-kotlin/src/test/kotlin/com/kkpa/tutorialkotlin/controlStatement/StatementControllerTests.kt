package com.kkpa.tutorialkotlin.controlStatement

import com.kkpa.tutorialkotlin.controlstatements.StatementController
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@SpringBootTest
@RunWith(SpringRunner::class)
class StatementControllerTests {

    @Autowired
    lateinit var statementCtrl : StatementController


    @Test
    fun givenBoundariesShouldBeSumed() {
        val initial : Int = 1
        val final : Int = 3
        val expected : Int = 3

        val result : Int = statementCtrl.sumOf(initial , final)
        statementCtrl.counter = 7
        println("----" + statementCtrl.counter)

        assert(result == expected)
    }

    @Test
    fun givenBoundariesClosedShouldBeSumed() {
        val initial : Int = 1
        val final : Int = 3
        val expected : Int = 6

        val result : Int = statementCtrl.sumOfClosed(initial , final)

        assert(result == expected)
    }

}