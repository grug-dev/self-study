package com.kkpa.java8.lambda;

import lombok.Data;
import lombok.NonNull;

public class Invoice {
    
    @NonNull private String name;
    @NonNull private int amount;
	public Invoice(String string, int i) {
		this.name = string;
		this.amount = i;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
    
    

}
