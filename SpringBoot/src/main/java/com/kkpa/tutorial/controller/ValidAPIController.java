package com.kkpa.tutorial.controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kkpa.tutorial.domain.ValidationDTO;
import com.kkpa.tutorial.domain.ValidationResponseDTO;

@RestController
@RequestMapping("/validationAPI")
public class ValidAPIController {
	
	
	@PostMapping
	public List<ValidationResponseDTO> add(@Valid @RequestBody ValidationDTO validationDTO, Errors errors) {		
		if ( errors.hasErrors()) {
			List<ValidationResponseDTO> validations = errors.getFieldErrors().stream()
					.map( f -> new ValidationResponseDTO(f.getField(),f.getDefaultMessage())).collect(Collectors.toList());
			
			return validations;
		}
		
		
		return Collections.emptyList();
	}
	

}
