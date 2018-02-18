package com.kkpa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kkpa.soap.CountryRepository;

import kkpa.ws_tutorial.Country;

@Component
public class ReceiverListener {

	private CountryRepository countryRepository;
	
	@Autowired
	public ReceiverListener(CountryRepository countryRepository) {
		this.countryRepository = countryRepository;
	}
	
	public void receiveMessage(String message)  {
		System.out.println("Received : " + message);
		countryRepository.findCountry(message);
	}
	
}
