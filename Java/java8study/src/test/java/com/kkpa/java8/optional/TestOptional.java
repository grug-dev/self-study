package com.kkpa.java8.optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

import com.kkpa.java8.optional.OptionalFactory;

public class TestOptional {

    private OptionalFactory optionalFactory;
    
    private final static String UPPER_MESSAGE = "MESSAGE";

    @Before
    public void setup() {
	optionalFactory = new OptionalFactory();
    }
    
    @Test
    public void testCreateOptionalShouldBeSuccess() {
	Optional<String> message = optionalFactory.createOptional(UPPER_MESSAGE);
	
	assertEquals(UPPER_MESSAGE, message.get());
    }
    
    @Test
    public void testCreateOptionalWithNullButWithElseValue() {
	Optional<String> nullValueOpt = optionalFactory.createOptional(null);
	
	assertEquals(UPPER_MESSAGE, nullValueOpt.orElse(UPPER_MESSAGE));
    }
    
    @Test (expected = NoSuchElementException.class)
    public void nullOptionalShouldReturnNoSuchElementExceptionIfGetIsCalled() {
	Optional<String> nullValueOpt = optionalFactory.createOptional(null);
	
	nullValueOpt.get();
    }
    
    @Test 
    public void nullOptionalShouldReturnNotPresent() {
	Optional<String> optionalValue = optionalFactory.createOptional(null);
	
	assertFalse(optionalValue.isPresent());
    }
    
    @Test
    public void useMapWithOptionalShouldBeSuccess() {
	Optional<String> upperMsg = optionalFactory.createOptional(UPPER_MESSAGE);
	
	Optional<String> lowerMsg = optionalFactory.createOptional("message");
	
	assertEquals(upperMsg, lowerMsg.map(String::toUpperCase));
    }
    
    
    @Test
    public void getCityForEventShouldBeSuccess() {
	Optional city = Optional.ofNullable(optionalFactory.findEvent(-1))
		.flatMap( event -> Optional.ofNullable(event.getLocation()))
		.map( loc -> loc.getCity())
		;
	
	assertFalse(city.isPresent());
    }
    
}
