package com.kkpa.java8.optional;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
public class LocationDTO {
    
    private String city = "Default";

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
    
    

}
