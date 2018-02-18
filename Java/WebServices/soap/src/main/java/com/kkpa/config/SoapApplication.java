package com.kkpa.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.kkpa")
public class SoapApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(SoapApplication.class, args);
	}
	
	
}
