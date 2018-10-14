package com.kkpa.tutorialkotlin.objects

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith ( SpringRunner::class)
@SpringBootTest
class ObjectTests {

    @Autowired
    lateinit var customer: Customer

    @Test
    fun testingInmutability() {
        customer = Customer(100 , "Customer1" , "Addres1")

        val customer2 = customer.copy( name = "Customer2")

        println(customer2)

        assert( !(customer.name.equals(customer2.name)))

    }

}