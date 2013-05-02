package com.vridosh.drawer;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;


public class Shapppe implements IDrawable {
	private static final double SPEED = 0.002;
	public Point2D leftTop;
	public Point2D movement;	
	public Size2D size;
	public Color fill;
	public Color stroke;
	public double lineW;
	public int star;
	public double rotationSpeed;
	
	public static Shapppe createRandom(Random r, int maxX, int maxY) {
		Shapppe t = new Shapppe();
		int x = 25 + r.nextInt(100);
		t.size = new Size2D(x, x);
		t.movement = new Point2D(//0, 0);
				SPEED*r.nextDouble() - SPEED/2, 
				SPEED*r.nextDouble() - SPEED/2);
		t.leftTop = new Point2D(
				r.nextInt(maxX - (int)x),
				r.nextInt(maxY - (int)x));
		t.lineW = 1 + r.nextDouble()*10;
		t.star = 4 + r.nextInt(10);
		t.rotationSpeed = 10000. + r.nextInt(3000);
		t.fill = new Color(128+r.nextInt(128), 128+r.nextInt(128), r.nextInt(255), 0.1 + r.nextDouble()/2);
		t.stroke = new Color(128+r.nextInt(128), 128+r.nextInt(128), r.nextInt(255), 0.1 + r.nextDouble()/2);
		all.add(t);

		return t;
	}
	
	public final Point2D center() {
		return new Point2D(leftTop.x + size.x/2., leftTop.y + size.y/2.);
	}

	static List<Shapppe> all = Lists.newArrayList();
	static long lDateTime = Util.getMilliseconds();
	private long lastUpdateDateTime = lDateTime;
	
	/* (non-Javadoc)
	 * @see gwt.test.client.Drawable#draw(gwt.test.client.ICanvasContext)
	 */
	@Override
	public void draw(ICanvasContext c2) {		
		c2.beginPath();
		
		Point2D center = new Point2D(leftTop.x + size.x/2, leftTop.y + size.y/2);
		double dstep = 2*Math.PI/star;
		long nowMs = Util.getMilliseconds();
		double d = 0.; //(nowMs - lDateTime)*1./rotationSpeed;
		if (lastUpdateDateTime != nowMs) {
			double time = nowMs - lastUpdateDateTime;
			
			Point2D thisCenter = center();
			for (Shapppe t : all) {
				if (t != this) {
					Point2D center2 = t.center();
					double distance = center2.distanceTo(thisCenter);
					double force = 0.05 * t.size.x / Math.pow(distance, 2);
					double fy = center2.y - thisCenter.y;
					double fx = center2.x - thisCenter.x;
					final double alpha;
					if (fx > 0) {
						alpha = Math.atan(fy / fx);
					} else if (fx < 0 && fy >= 0) {
						alpha = Math.atan(fy / fx) + Math.PI;
					} else if (fx < 0 && fy < 0) {
						alpha = Math.atan(fy / fx) - Math.PI;
					} else if (fx == 0 && fy > 0) {
						alpha = Math.PI/2.;
					} else if (fx == 0 && fy < 0) {
						alpha = - Math.PI/2.;
					} else {
						alpha = 0.;
					}

					movement = new Point2D(
							movement.x + Math.cos(alpha)*force*time,
							movement.y + Math.sin(alpha)*force*time);
					
					// System.out.println(fill + "," + alpha + "," + (distance * distance) + ":" + force);
				}
				
				movement = new Point2D(
						movement.x * (1. - 1./time/time/time),
						movement.y * (1. - 1./time/time/time));
			}
			
			leftTop = new Point2D(
					leftTop.x + movement.x,
					leftTop.y + movement.y);
			
			lastUpdateDateTime = nowMs;
		}
		
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
		c2.setFillStyle(c2.createSolidColorFill(fill.r, fill.g, fill.b, fill.a));
		c2.fill();
		
		// speed line
		c2.beginPath();
		c2.moveTo(center().x, center().y);
		c2.lineTo(center().x + movement.x * 100., center().y + movement.y* 100.);
		c2.closePath();
		c2.setStrokeStyle(c2.createSolidColorFill(0, 0, 0, 0.33));
		c2.stroke();
		/*
		c2.setStrokeStyle(c2.createSolidColorFill(fill.r, fill.g, fill.b, fill.a));
		c2.setLineWidth(this.lineW);
		c2.stroke();
		*/
	}
}