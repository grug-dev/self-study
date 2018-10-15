package com.kkpa.newmodule


import com.kkpa.tutorialkotlin.extensions.upperFirstAndLast
import com.kkpa.tutorialkotlin.objects.SomeClass
import com.kkpa.tutorialkotlin.functions.printNumbers as printOf
import com.kkpa.tutorialkotlin.objects.singleton.Singleton as ComObj


fun main(args: Array<String>) {
    printOf(*arrayOf(1,2,3) , message = "From Module" )
    println(ComObj.getCopyRightLine().upperFirstAndLast())
    println(SomeClass.accessPrivateVar())
}
