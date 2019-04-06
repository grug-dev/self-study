package com.kkpa.tutorial.domain;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Data;

@Data
public class ValidationDTO {

	
	@NotBlank(message="You should write something")	
	public String something;
	
	@CreditCardNumber(message="Not a valid credit card number")
	@NotNull(message="ccNumber shoult no be null")
	private String ccNumber;

	@Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
	message="Must be formatted MM/YY")
	private String ccExpiration;

	@Digits(integer=3, fraction=0, message="Invalid CVV")
	private String ccCVV;
	
}
