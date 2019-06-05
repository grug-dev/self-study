package com.kkpa.java8.optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NonNull;

@Component
public class EventDTO {

    @NonNull private int id;
        
    @Autowired
    private LocationDTO location;

	public EventDTO(int id2) {
		this.id = id2;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocationDTO getLocation() {
		return location;
	}

	public void setLocation(LocationDTO location) {
		this.location = location;
	}
    
    

}
