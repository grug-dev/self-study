package com.kkpa.tutorialkotlin.collections

import org.springframework.stereotype.Component
import java.util.*
import kotlin.Comparator

@Component
class collectionWorker {

    fun applyGroupBy( numbers: Collection<Int>): Map<String, List<Int>> {
        val groups = numbers.groupBy {
            if ( it and 1 == 0) "even" else "odd"
        }
        return groups
    }

    fun reverseOrder (numbers: List<Int>) : List<Int> {

        var numbersCopy = numbers.toMutableList()

        Collections.sort( numbersCopy , object : Comparator<Int>{
            override fun compare(o1: Int, o2: Int): Int {
                return o2.compareTo(o1)
            }
        })

        return numbersCopy.toList()

    }

}