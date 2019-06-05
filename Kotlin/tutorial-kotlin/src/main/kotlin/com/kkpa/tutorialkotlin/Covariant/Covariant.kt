package com.kkpa.tutorialkotlin.Covariant

fun main(args: Array<String>) {
    val b = B()
    val a:A = getB(b) // B is A
    val c = C()

    getB(c) // c is B
    //getB(a) // A is not B
}

open class A {
    val a1 = ""
}
open class B: A() {
    val b1 = ""
}
class C: B() {
    val c1 = ""
}

fun getB(param: B):B {
    return C()
}
