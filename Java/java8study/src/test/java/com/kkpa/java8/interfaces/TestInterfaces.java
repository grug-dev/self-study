package com.kkpa.java8.interfaces;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TestInterfaces {
	
	private Figure figure;
	
	private FigureDTO dataFigure;
	
	private final static double DELTA = 0.0d;
	
	@Before
	public void setup() {
		dataFigure = new FigureDTO();
	}
	
	
	@Test	
	public void defaultMethodShouldBeSucessful() {
		figure = new Rectangle();
		
		double area = figure.getArea(dataFigure);
		assertEquals(area, 0.0d , DELTA);
	}
	
	@Test
	public void overrideDefaultMethodShouldBeSucessfull() {
		figure = new Triangle();
		
		TriangleDTO triangleDTO = new TriangleDTO();
		triangleDTO.setBase(1);
		triangleDTO.setVerticalHeight(2);
		double area = figure.getArea(triangleDTO);
		
		assertEquals(area, 1, DELTA);
	}

}
