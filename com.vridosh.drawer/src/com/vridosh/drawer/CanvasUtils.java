package com.vridosh.drawer;

public class CanvasUtils {
	public static final void createRectShape(final ICanvasContext context, final Point2D leftTop, final Size2D size) {
		context.beginPath();
		context.moveTo(leftTop.x, leftTop.y);
		context.lineTo(leftTop.x + size.x, leftTop.y);
		context.lineTo(leftTop.x + size.x, leftTop.y + size.y);
		context.lineTo(leftTop.x, leftTop.y  + size.y);
		context.lineTo(leftTop.x, leftTop.y);
		context.closePath();
	}
}
