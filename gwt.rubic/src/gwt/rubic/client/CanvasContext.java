package gwt.rubic.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.vridosh.drawer.ICanvasContext;

public final class CanvasContext extends JavaScriptObject implements ICanvasContext {
	protected CanvasContext() {}

	@Override
	public final native void setFillStyle(FillStyle style) /*-{
	  return this.fillStyle = style;
	}-*/;

	@Override
	public final native void setStrokeStyle(FillStyle style) /*-{
	  return this.strokeStyle = style;
	}-*/;

	@Override
	public final native void setLineWidth(double lineWidth)  /*-{
		return this.lineWidth = lineWidth;
	}-*/;

	@Override
	public final native void fillRect(double x, double y, double w, double h) /*-{
	  return this.fillRect(x, y, w, h);
	}-*/;

	@Override
	public final native void clearRect(double x, double y, double w, double h) /*-{
	  return this.clearRect(x, y, w, h);
	}-*/;

	@Override
	public final native void beginPath() /*-{
	  return this.beginPath();
	}-*/;

	@Override
	public final native void closePath() /*-{
	  return this.closePath();
	}-*/;

	@Override
	public final native void clip() /*-{
	  return this.clip();
	}-*/;

	@Override
	public final native void moveTo(double x, double y) /*-{
	  return this.moveTo(x, y);
	}-*/;

	@Override
	public final native void lineTo(double x, double y) /*-{
	  return this.lineTo(x, y);
	}-*/;

	@Override
	public final native void bezierCurveTo(double c1x, double c1y, double c2x, double c2y, double x, double y)  /*-{
	  return this.bezierCurveTo(c1x, c1y, c2x, c2y, x, y);
	}-*/;

	@Override
	public final native void stroke() /*-{
	  return this.stroke();
	}-*/;

	@Override
	public final native void fill() /*-{
	  return this.fill();
	}-*/;

	@Override
	public final native void save() /*-{
	  return this.save();
	}-*/;

	@Override
	public final native void restore() /*-{
	  return this.restore();
	}-*/;

	@Override
	public final native FillStyleJSO createSolidColorFill(int r, int g, int b, double a) /*-{
	  return "rgba(" + r + "," + g + "," + b + "," + a + ")";
	}-*/;
}