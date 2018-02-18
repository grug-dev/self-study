package com.kkpa.soap;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.kkpa.config.RabbitConfig;

import kkpa.ws_tutorial.Country;
import kkpa.ws_tutorial.GetCountryRequest;
import kkpa.ws_tutorial.GetCountryResponse;

@Endpoint
public class CountryEndpoint {
	private static final String NAMESPACE_URI = "http://kkpa/ws_tutorial";


	
	@Autowired
	private RabbitTemplate rabbitTemplate;


	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
	@ResponsePayload
	public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
		GetCountryResponse response = new GetCountryResponse();
		
		System.out.println("Sending message...");
		rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_NAME, request.getName());
		Country c = new Country(); c.setCapital("CAPITAL" + request.getName());
        response.setCountry(c);
        
		return response;
	}
}