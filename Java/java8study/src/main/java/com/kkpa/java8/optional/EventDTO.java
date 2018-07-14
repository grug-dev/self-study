package com.kkpa.java8.optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NonNull;

@Data
@Component
public class EventDTO {

    @NonNull private int id;
        
    @Autowired
    private LocationDTO location;

}
