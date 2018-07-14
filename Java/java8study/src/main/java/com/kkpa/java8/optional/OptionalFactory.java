package com.kkpa.java8.optional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class OptionalFactory {
    
    
    @Autowired
    private ApplicationContext appCtx;

    public Optional<String> createOptional(String message) {
	return Optional.ofNullable(message);
    }       
    
    public List<List<Optional<String>>> createOptionalList(String... values) {
	List<List<Optional<String>>> optionals = new ArrayList<>();
	
	for (String value : values) {
	    optionals.add(Arrays.asList(Optional.ofNullable(value)));
	}
	
	return optionals;
    }
    
    public EventDTO findEvent(int id) {	
	return new EventDTO(id);
    }

}
