package com.kkpa.tutorialkotlin.objects.singleton

import java.time.Year

fun main(args: Array<String>) {
    println(Singleton.getTagLine())
    println(Singleton.getCopyRightLine())

    println("--- Singleton ---")
    println(Singleton.message)

    Singleton.showSingleMessage()
    Singleton.setSingleMessage("Hello Kolination!")

    println("--- singleton1 ---")
    val singleton1 = Singleton
    singleton1.showSingleMessage()
    Singleton.setSingleMessage("Hello Kolineer!")

    println("--- singleton2 ---")
    val singleton2 = Singleton
    singleton2.showSingleMessage()
}


// In Order to use a singleton, we use the Object.

/**
 * This doesnt have constructors.
 */
object Singleton {

    val currentYear = Year.now().value

    internal fun getTagLine() = "Our company rocks!"

    fun getCopyRightLine() = "Copyright \u00A9 $currentYear Our Company. All rights reserved."

    var message: String = "default Message"


    fun showSingleMessage() {
        println("Message: " + message)
    }

    fun setSingleMessage(message: String) {
        this.message = message
    }

}
