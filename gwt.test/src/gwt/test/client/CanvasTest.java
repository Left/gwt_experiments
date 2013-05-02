/**
 * 
 */
package gwt.test.client;

import java.util.Date;
import java.util.List;
import java.util.Random;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.primitives.Doubles;
import com.google.gwt.core.client.EntryPoint;

/**
 * @author Left
 *
 */
public class CanvasTest implements EntryPoint {	
	public static native void log(Object o) /*-{
	  return console.log(o);
	}-*/;
	
	public static native CanvasContext createCanvas(int x, int y, int w, int h, double opacity, Function<ICanvasContext, Void> clback) /*-{
		var canv = document.createElement("canvas");
		canv.setAttribute('width', w);
		canv.setAttribute('height', h);
		canv.style.position = 'absolute';
		canv.style.left = x + 'px';
		canv.style.top = y + 'px';
		canv.style.opacity = opacity;
		document.body.appendChild(canv);
		
		var raf = 'mozRequestAnimationFrame' in window ? window.mozRequestAnimationFrame : window.requestAnimationFrame;
		var cntxt = canv.getContext("2d");
		function callback() {
			clback.@com.google.common.base.Function::apply(*)(cntxt);
			raf(callback);
		}
		raf(callback);
		
		return cntxt;
	}-*/;

	static class Point2D {
		public final double x;
		public final double y;
		
		public Point2D(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}

	static class Size2D {
		public final double x;
		public final double y;
		
		public Size2D(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}

	static class Triangle implements IDrawable {
		public Point2D leftTop;
		public Size2D size;
		public String fill;
		public String stroke;
		public int lineW;
		public int star;
		public double speed;
		
		public static Triangle createRandom(Random r, int maxX, int maxY) {
			Triangle t = new Triangle();
			t.size = new Size2D(125 + r.nextInt(100), 125 + r.nextInt(100));
			t.leftTop = new Point2D(
					r.nextInt(maxX - (int)t.size.x),
					r.nextInt(maxY - (int)t.size.y));
			t.fill = "rgba(" + r.nextInt(255) + "," + r.nextInt(255) + "," + r.nextInt(255) + "," + r.nextDouble() + ")";
			t.stroke = "rgba(" + r.nextInt(255) + "," + r.nextInt(255) + "," + r.nextInt(255) + "," + r.nextDouble() + ")";
			t.lineW = r.nextInt(10);
			t.star = 3 + r.nextInt(30);
			t.speed = 1000. + r.nextInt(3000);

			return t;
		}

		static long lDateTime = getMilliseconds();
		
		/* (non-Javadoc)
		 * @see gwt.test.client.Drawable#draw(gwt.test.client.ICanvasContext)
		 */
		@Override
		public void draw(ICanvasContext c2) {
			c2.beginPath();
			// c2.lineTo(this.x, this.y + this.w);
			
			Point2D center = new Point2D(leftTop.x + size.x/2, leftTop.y + size.y/2);
			double dstep = 2*Math.PI/star;
			double d = (getMilliseconds() - lDateTime)*1./speed;
			for (int s=0; s<=star; s++,d+=dstep) {
				Point2D dest = new Point2D(
						center.x + size.x/2*Math.sin(d), 
						center.y + size.y/2*Math.cos(d));
				if (s == 0) {
					c2.moveTo(dest.x, dest.y);
				} else {
					c2.bezierCurveTo(
							center.x + size.x*Math.sin(d-3*dstep/4), 
							center.y + size.y*Math.cos(d-3*dstep/4),
							center.x + size.x*Math.sin(d-dstep/4), 
							center.y + size.y*Math.cos(d-dstep/4),
							dest.x, 
							dest.y);
				}
			}
			c2.closePath();
			// c2.setLineWidth(.5);
			// c2.setStrokeStyle("#0000FF");
			// c2.clip();
			c2.setFillStyle(this.fill);
			c2.setStrokeStyle(this.stroke);
			c2.setLineWidth(this.lineW);
			c2.fill();
			c2.stroke();
		}
	}
	
	@Override
	public void onModuleLoad() {
		/*
		ICanvasContext c1 = createCanvas(0, 0, 200, 200, 1.);
		c1.setFillStyle("#FF0000");
		c1.fillRect(0, 0, 100, 100);
		*/
		final Random r = new Random();

		final List<IDrawable> triangles = Lists.newArrayList();
		for (int i=0; i<20; ++i) {
			triangles.add(Triangle.createRandom(r, 1000, 800));
		}
		
		final long[] lDateTime = { getMilliseconds() };

		createCanvas(0, 0, 1000, 800, 1., new Function<ICanvasContext, Void>() {
			@Override
			public Void apply(ICanvasContext c2) {
				if (getMilliseconds() - lDateTime[0] >= 30) {
					lDateTime[0] = getMilliseconds();
					
					c2.clearRect(0, 0, 1000, 800);
					for (IDrawable t : triangles) {
						t.draw(c2);
					}
				}
				return null;
			}			
		});
		// 
		// c2.fillRect(0, 0, 400, 400);

	}

	private static long getMilliseconds() {
		return new Date().getTime();
	}

}
