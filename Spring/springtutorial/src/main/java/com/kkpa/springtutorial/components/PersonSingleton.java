package com.kkpa.springtutorial.components;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class PersonSingleton {
	
	private int age;
	private String name;
	
	@Override
	public String toString() {
		return "Person [age=" + age + ", name=" + name + "]";
	}

}
