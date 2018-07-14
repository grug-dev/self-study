package com.kkpa.java8.optional;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class LocationDTO {
    
    private String city = "Default";

}
