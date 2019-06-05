package com.kkpa.tutorial.controller;


import com.kkpa.tutorial.domain.DateType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DateController {
    @GetMapping("/")
    public DateType get() {
        return new DateType();
    }
}
