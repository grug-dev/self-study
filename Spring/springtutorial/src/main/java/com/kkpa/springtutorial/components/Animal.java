package com.kkpa.springtutorial.components;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class Animal {
	
	private String name;
	private Integer age;

}
