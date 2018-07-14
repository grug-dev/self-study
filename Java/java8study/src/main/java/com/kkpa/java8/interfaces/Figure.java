package com.kkpa.java8.interfaces;

public interface Figure <T extends FigureDTO> {

	default double getArea(T data) {
		return data.getBase() * data.getVerticalHeight();
	}
	
}
