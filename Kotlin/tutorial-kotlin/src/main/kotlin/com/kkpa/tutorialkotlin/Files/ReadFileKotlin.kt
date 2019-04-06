package com.kkpa.tutorialkotlin.Files

import java.io.File
import java.util.*
import java.util.stream.Collectors
import java.util.stream.Stream

const val VERSION_FILE_PATH = "files/version.txt"

fun main(args: Array<String>) {
    val versionInfo = mutableMapOf<String,String>()
    val lines = File(VERSION_FILE_PATH).reader().readLines()

    lines.stream().map { line -> line.split("=") }.map { it[0] to it[1] }.forEach {
        println("First: ${it.first} - Second: ${it.second} ")
        versionInfo.put(it.first, it.second)
    }

    println("Version info to String: ${versionInfo.toString()}")


    lines.stream().map { line -> line.split("=") }.flatMap {  x -> x.stream()}

    lines.stream()
            .map { line -> line.split("=") }
            .forEach { (key,value) ->  println("New Key ${key} - Value :${value}")}


    // Close resource
    val version = File(VERSION_FILE_PATH).reader().use {
        it.readText()
    }
    println("Version $version ")
    version.split("=").stream().forEach {
        println(it)
    }


    // Read Huge Files line by line
    // Its going to read a line at time
    File(VERSION_FILE_PATH).reader().forEachLine { println(it) }
}
