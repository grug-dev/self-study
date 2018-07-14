package com.kkpa.java8.lambda;

import lombok.Data;
import lombok.NonNull;

@Data
public class Invoice {
    
    @NonNull private String name;
    @NonNull private int amount;

}
