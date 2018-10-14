package com.kkpa.tutorialkotlin.controller

import com.kkpa.tutorialkotlin.dto.GreetingDTO
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController("/greeting")
class HelloController {


    @GetMapping("/")
    fun sayHello () : GreetingDTO {
        var greetingDTO = GreetingDTO( "Cristian" , "Message")

        return greetingDTO;
    }

}