package com.kkpa.tutorial.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class ValidationResponseDTO {
	
	private String field;
	
	private String errorMessage;
		

}
