package com.vridosh.drawer.test;

import java.util.Set;

import com.vridosh.drawer.CanvasUtils;
import com.vridosh.drawer.ICanvasContext;
import com.vridosh.drawer.ICanvasDrawer;
import com.vridosh.drawer.IMouseListener;
import com.vridosh.drawer.Point2D;
import com.vridosh.drawer.Size2D;

public final class SimpleRedQuad implements ICanvasDrawer, IMouseListener {
	private Point2D leftTop = Point2D.create(0., 0.);
	private final Size2D size = Size2D.create(50., 50.);

	@Override
	public void init() {
	}

	@Override
	public void draw(final ICanvasContext c2) {
		c2.setFillStyle(c2.createSolidColorFill(255, 0, 0, 1.));

		CanvasUtils.createRectShape(c2, leftTop, size);
		c2.fill();

		c2.setLineWidth(.5);
		c2.setStrokeStyle(c2.createSolidColorFill(0, 0, 255, 1.));
		c2.stroke();
	}

	@Override
	public void onMouseButtonDown(final MouseButton btn, final int x, final int y) {
		if (btn == MouseButton.LEFT) {
			leftTop = Point2D.create(x, y);
		}
	}

	@Override
	public void onMouseButtonUp(final MouseButton btn, final int x, final int y) {
		if (btn == MouseButton.RIGHT) {
		}
	}

	@Override
	public void onMouseButtonMove(final Set<MouseButton> btns, final int x, final int y) {
		if (btns.contains(MouseButton.LEFT)) {
			leftTop = Point2D.create(x + 0., y + 0.);
		}
	}
}