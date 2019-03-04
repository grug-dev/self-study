package com.kkpa.tutorialkotlin.strings

import org.springframework.stereotype.Component
import java.util.*

@Component
class StringWorker {



    fun verifyIsString ( msg : Any) : Boolean {
        return if (msg is String) true else false
    }

    fun concat ( firstWord: String , secondWord: String) : String {

        val text = """
    |Tell me and I forget.
    |Teach me and I remember.
    |Involve me and I learn.
    |(Benjamin Franklin)
    """.trimMargin()

        println(text)

        return "$firstWord $secondWord"
    }


}