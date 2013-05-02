package com.vridosh.drawer;

public class Color {   
	final int r;
	final int g;
	final int b;
	final double a;

	public Color(int r, int g, int b, double a) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}

	@Override
	public String toString() {
		return "rgba(" + r + "," + g + "," + b + "," + a + ")";
	}	
}
