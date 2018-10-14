package com.kkpa.tutorialkotlin.extensions

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner


@RunWith( SpringRunner::class)
@SpringBootTest
class ExtensionTests {



    @Autowired
    lateinit var extensionWorker : ExtensionWorker

    @Test
    fun givenArraySwapToItems() {

        val numbers = mutableListOf<Int>( 1 , 2 , 3)
        extensionWorker.swap(numbers, 0 , 1)

        extensionWorker.table

        assert(numbers[0] == 2)
        assert(numbers[1] == 1)

    }

}