/**
 * 
 */
package gwt.rubic.client;


import java.util.Collections;

import com.google.gwt.core.client.EntryPoint;
import com.vridosh.drawer.ICanvasDrawer;
import com.vridosh.drawer.IMouseListener;
import com.vridosh.drawer.test.SimpleRedQuad;

/**
 * @author Left
 *
 */
public class CanvasRenderer implements EntryPoint {
	public static native void log(Object o) /*-{
	  return console.log(o);
	}-*/;

	public static native CanvasContext createCanvas(int x, int y, int w, int h, double opacity, ICanvasDrawer clback, MouseEventProcessor mouseListener) /*-{
		var canv = document.createElement("canvas");
		canv.setAttribute('width', w);
		canv.setAttribute('height', h);
		canv.style.position = 'absolute';
		canv.style.left = x + 'px';
		canv.style.top = y + 'px';
		canv.style.opacity = opacity;
		document.body.appendChild(canv);

		clback.@com.vridosh.drawer.ICanvasDrawer::init(*)();

		var raf = 'mozRequestAnimationFrame' in window ? window.mozRequestAnimationFrame : window.requestAnimationFrame;
		var cntxt = canv.getContext("2d");
		function callback() {
			clback.@com.vridosh.drawer.ICanvasDrawer::draw(*)(cntxt);
			raf(callback);
		}
		raf(callback);

		['mouseup', 'mousedown', 'mousemove'].forEach(function (type, i) {
			canv.addEventListener(type, function(e) {
				mouseListener.@gwt.rubic.client.MouseEventProcessor::process(*)(type, e.x, e.y, e.which);
			}, false);
		});

		return cntxt;
	}-*/;

	IMouseListener.MouseButton getTypedMouseButton(final int btn) {
		if (btn == 1) {
			return IMouseListener.MouseButton.LEFT;
		} else if (btn == 2) {
			return IMouseListener.MouseButton.MIDDLE;
		} else if (btn == 3) {
			return IMouseListener.MouseButton.RIGHT;
		}
		return null;
	}

	@Override
	public void onModuleLoad() {
		// createCanvas(0, 0, 1000, 800, 1., new ActualDrawer());
		final SimpleRedQuad simpleRedQuad = new SimpleRedQuad();
		final IMouseListener mouse = (simpleRedQuad instanceof IMouseListener) ? (IMouseListener) simpleRedQuad : null;
		createCanvas(0, 0, 1000, 800, 1., simpleRedQuad, new MouseEventProcessor() {
			@Override
			public void process(final String type, final int x, final int y, final int which) {
				// log(type + "," + x + "," + y + "," + which);

				if (mouse != null) {
					if (type.equals("mouseup")) {
						mouse.onMouseButtonUp(getTypedMouseButton(which), x, y);
					} else if (type.equals("mousedown")) {
						mouse.onMouseButtonDown(getTypedMouseButton(which), x, y);
					} else if (type.equals("mousemove")) {
						mouse.onMouseButtonMove(
								Collections.singleton(getTypedMouseButton(which)), x, y);
					}
				}
			}
		});
	}

}
