package com.kkpa.tutorialkotlin.extensions

import org.springframework.stereotype.Component

@Component
class ExtensionWorker {

    private var _table : Map<String, Int>? = null

    fun swap (numbers : MutableList<Int> , index1 : Int , index2: Int) {

        fun MutableList<Int>.swap( index1 : Int , index2: Int) {
            val tmp = this[index1]
            this[index1] = this[index2]
            this[index2] = tmp
        }

        numbers.swap(index1, index2)

    }

    val table: Map<String, Int>
        get() {
            if (_table == null) {
                _table = HashMap<String, Int>() // Type parameters are inferred
            }
            return _table ?: throw AssertionError("Set to null by another thread")
        }

}