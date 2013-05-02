package com.vridosh.drawer;

public class Size2D {
	public final double x;
	public final double y;

	public Size2D(final double x, final double y) {
		this.x = x;
		this.y = y;
	}

	public static Size2D create(final double x, final double y) {
		return new Size2D(x, y);
	}
}