package com.kkpa.java8.interfaces;

public class Triangle implements Figure<TriangleDTO> {
	
	@Override
	public double getArea(TriangleDTO data) {
		return 0.5d *data.getBase()*data.getVerticalHeight();
	}
	

}
