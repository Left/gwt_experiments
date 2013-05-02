package com.vridosh.drawer;

public interface ICanvasContext {
	interface FillStyle {
	}

	FillStyle createSolidColorFill(int r, int g, int b, double a);

	void setFillStyle(FillStyle style);
	void setStrokeStyle(FillStyle style);
	void setLineWidth(double i);

	void fillRect(double x, double y, double w, double h);
	void clearRect(double x, double y, double w, double h);

	void beginPath();
	void closePath();
	void clip();
	void moveTo(double x, double y);
	void lineTo(double x, double y);
	void bezierCurveTo(double c1x, double c1y, double c2x, double c2y, double x, double y);
	void stroke();
	void fill();

	void save();
	void restore();
}