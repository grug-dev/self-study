package com.kkpa.java8.completablefuture;

public class Car {
    
    private String model;
    private int price;
    private int rating;

    public Car ( String model, int price) {
	this.model = model;
	this.price = price;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
	return "Car [model=" + model + ", price=" + price + "]";
    }
    
    

}
