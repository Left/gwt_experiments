package com.vridosh.drawer.test;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;
import com.vridosh.drawer.ICanvasContext;
import com.vridosh.drawer.ICanvasDrawer;
import com.vridosh.drawer.IDrawable;
import com.vridosh.drawer.Shapppe;
import com.vridosh.drawer.Util;

public final class ActualDrawer implements ICanvasDrawer {
	final Random r = new Random(1228l);
	final List<IDrawable> triangles = Lists.newArrayList();
	long lDateTime = Util.getMilliseconds();

	@Override
	public void draw(ICanvasContext c2) {
		long now = Util.getMilliseconds();
		if (now - lDateTime >= 30) {
			lDateTime = now;
			
			c2.clearRect(0, 0, 1000, 800);
			for (IDrawable t : triangles) {
				t.draw(c2);
			}
		}
	}

	@Override
	public void init() {
		for (int i=0; i<20; ++i) {
			Shapppe createRandom = Shapppe.createRandom(r, 1000, 800);
			triangles.add(createRandom);
		}
	}
}