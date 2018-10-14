package com.kkpa.tutorialkotlin.objects

import org.springframework.stereotype.Component

@Component
data class Customer (
        var id: Int = 1,
        var name:String = "" ,
        var address : String = ""
)