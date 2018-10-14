package com.kkpa.tutorialkotlin.collection

import com.kkpa.tutorialkotlin.collections.collectionWorker
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@SpringBootTest
@RunWith(SpringRunner::class)
class CollectionWorkerTests {


    @Autowired
    lateinit var collectionWorker : collectionWorker

    @Test
    fun givenNumbersShouldBeGroupedByEvenOrOdd() {
        val numbers = listOf<Int>( 1 , 2 ,3 ,4 ,5 ,6)

        collectionWorker.applyGroupBy(numbers)

    }

    @Test
    fun givenArrayReturnOrderDescending() {
        val numbers = listOf<Int>( 1 , 2 ,3 ,4 ,5 ,6)

        val numbersDescending = collectionWorker.reverseOrder(numbers)

        println(numbersDescending)

        assert(numbersDescending[0] == 6)
    }



}