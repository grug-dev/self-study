package com.kkpa.springtutorial.services;

public class HelloException extends RuntimeException{
	
	public HelloException(String name ) {
		super(name);
	}

}
