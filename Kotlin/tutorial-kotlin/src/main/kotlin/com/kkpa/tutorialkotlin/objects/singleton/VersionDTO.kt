package com.kkpa.tutorialkotlin.objects.singleton

class VersionDTO(private val branchName:String = "", private val buildNumber:String = "", private val commitNumber:String = ""){
    fun getVersion(): String {
        return branchName.plus(buildNumber)
    }
}