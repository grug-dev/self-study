package com.kkpa.tutorial.controller;

import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RepositoryRestController
public class CustomEndpointRepositoryRest {
	
	@GetMapping("/recent")
	public ResponseEntity<String> getCustomRecent() {
		return ResponseEntity.ok().body("Yeah, it works!");
	}

}
