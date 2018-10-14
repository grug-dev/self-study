package com.kkpa.tutorialkotlin.strings

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner


@SpringBootTest
@RunWith(SpringRunner::class)
class StringTests {


    @Autowired
    lateinit var stringWorker : StringWorker

    @Test
    fun givenStringShouldReturnTrue() {
        val result = stringWorker.verifyIsString("Hello");
        assert( result == true)
    }

    @Test
    fun givenNumberShouldReturnFalse() {
        val result = stringWorker.verifyIsString(5)
        assert( result == false)
    }

    @Test
    fun givenTwoWordShouldBeConcated() {
        val expected = "Hello World"
        val result = stringWorker.concat("Hello" , "World")
        assert(expected == result)
    }

}