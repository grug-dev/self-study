package com.kkpa.tutorialkotlin.objects.singleton

import java.time.Year

fun main(args: Array<String>) {
    println(Singleton.getTagLine())
    println(Singleton.getCopyRightLine())
}


// In Order to use a singleton, we use the Object.

/**
 * This doesnt have constructors.
 */
object Singleton {

    val currentYear = Year.now().value

    internal fun getTagLine() = "Our company rocks!"

    fun getCopyRightLine() = "Copyright \u00A9 $currentYear Our Company. All rights reserved."

}
