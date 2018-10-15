package com.kkpa.tutorialkotlin.inheritance

fun main(args: Array<String>) {

    val printer = LaserPrinter("Model Child Laser" )
    printer.printModel()

    println(printer.bestSellingPrice())

}

// We use open, to change the default public final declaration.
// If we use abstract, its by default is open
abstract class Printer (val modelName : String) {


    // Open keywork in order to allow override by childs. By default the functions are final and public.
    open fun printModel() = println("The model name is ${modelName} from SuperClass")

    // Abstract is open by default
    abstract fun bestSellingPrice(): Double
}



open class LaserPrinter (modelName: String) : Printer(modelName) {

    override fun printModel() = println("The model name is ${modelName} from ChildClass")

    // Child cannot override this function.
    final override fun bestSellingPrice(): Double = 123123.123


    var anotherValue: Int = 0

    constructor( modelName: String = "" , anotherValue : Int = -1 ) : this (modelName) {
        this.anotherValue = anotherValue
    }
}


class SpecialLaserPrinter( modelName: String) : LaserPrinter(modelName ) {


    override fun printModel() {
        super.printModel()
    }


}
