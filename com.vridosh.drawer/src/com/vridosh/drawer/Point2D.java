package com.vridosh.drawer;

public class Point2D {
	public final double x;
	public final double y;

	public Point2D(final double x, final double y) {
		this.x = x;
		this.y = y;
	}

	public final double distanceTo(final Point2D p) {
		return Math.sqrt((x - p.x)*(x - p.x) + (y - p.y)*(y - p.y));
	}

	@Override
	public String toString() {
		return "[" + x + ", " + y + "]";
	}

	public static Point2D create(final double x, final double y) {
		return new Point2D(x, y);
	}
}