package com.kkpa.java8study;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kkpa.java8.optional.OptionalFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class Java8studyApplicationTests {

    private OptionalFactory optFactory;
    
    @Before
    public void setup() {
	optFactory = new OptionalFactory();
    }

    @Test
    public void getCityForEventShouldBeSuccess() {
	Optional city = Optional.ofNullable(optFactory.findEvent(-1))
		.flatMap(event -> Optional.ofNullable(event.getLocation())).map(loc -> loc.getCity());

	System.out.println(city.orElse("Empty").toString());
    }

}
