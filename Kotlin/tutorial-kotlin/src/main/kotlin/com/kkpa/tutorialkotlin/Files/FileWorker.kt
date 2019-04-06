package com.kkpa.tutorialkotlin.Files

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.core.io.ClassPathResource
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.util.*

enum class AccountType {
    ADVICE, WEALTH
}

data class User(
        var email: String? = null,
        @JsonIgnore
        var partyId: Long,
        var lastName: String,
        var firstName: String,
        var accountType: AccountType = AccountType.ADVICE
)

fun main(args: Array<String>) {

    val lines = readResource("test.json")

    lines.forEach { println(it) }

    for ( i in 1..10) {
        File("test.json").appendText("Adding new line  ${i}")
    }

    val lines2 = readResource("test.json")

    lines2.forEach { println(it) }

}

fun readResource(resourceName: String): List<String> {
    val lines = ArrayList<String>()
    try {
        ClassPathResource(resourceName).inputStream.use { resource -> return scanResource(resource) }
    } catch (e: IOException) {
        e.printStackTrace()
    }

    return lines
}

private fun scanResource(resource: InputStream): List<String> {
    val lines = ArrayList<String>()
    Scanner(resource).use { scanner ->
        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine())
        }
        return lines
    }
}
