package com.kkpa.tutorialkotlin.functions

fun main(args: Array<String>) {

}

// With
/*
* inline fun <T, R> with(receiver: T, block: T.() -> R): R {
    return receiver.block()
}
*
* */


// Also
/*
inline fun <T> T.also(block: (T) -> Unit): T {
    block(this)
    return this
}
* */