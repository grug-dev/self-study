package com.kkpa.gradle_soap.kkpa.endpoints;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.kkpa.gradle_soap.kkpa.ws_tutorial.Country;
import com.kkpa.gradle_soap.kkpa.ws_tutorial.GetCountryRequest;
import com.kkpa.gradle_soap.kkpa.ws_tutorial.GetCountryResponse;

@Endpoint
public class CountryEndpoint {
	private static final String NAMESPACE_URI = "http://kkpa/ws_tutorial";


	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
	@ResponsePayload
	public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
		GetCountryResponse response = new GetCountryResponse();
		
		System.out.println("Sending message...");
		Country c = new Country(); c.setCapital("CAPITAL" + request.getName());
        response.setCountry(c);
        
		return response;
	}
}