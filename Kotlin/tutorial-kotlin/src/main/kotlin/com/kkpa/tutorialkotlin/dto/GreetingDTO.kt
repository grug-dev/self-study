package com.kkpa.tutorialkotlin.dto

import org.springframework.stereotype.Component

@Component
data class GreetingDTO (var name : String =" No One",
                        var message:String = "WelCome")
