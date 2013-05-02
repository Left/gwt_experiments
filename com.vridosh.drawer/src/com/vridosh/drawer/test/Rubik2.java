package com.vridosh.drawer.test;

import java.util.Arrays;
import java.util.Map.Entry;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;

public class Rubik2 {
	enum Cell {
		RED("R"),
		ORANGE("O"),
		GREEN("G"),
		BLUE("B"),
		YELLOW("Y"),
		WHITE("W")
		;
		final String shortcut;

		private Cell(String shortcut) {
			this.shortcut = shortcut;
		}
	};

	interface State {
		int size();
		Cell getCell(Face f, int x, int y);
	}

	static class NormalState implements State {
		final int sz;

		private NormalState(int sz) {
			this.sz = sz;
		}

		@Override
		public Cell getCell(Face f, int x, int y) {
			return Cell.values()[f.ordinal()];
		}

		@Override
		public int size() {
			return sz;
		}
	}

	public static State move(final State orig, Axis a, int index) {
		return new State() {
			@Override
			public int size() {
				return orig.size();
			}

			@Override
			public Cell getCell(Face f, int x, int y) {
				return orig.getCell(f, x, y);
			}
			
		};
	}

	private static class PrintingPoint {
		final int x;
		final int y;
		@SuppressWarnings("unused")
		final boolean flipX;
		@SuppressWarnings("unused")
		final boolean flipY;

		final static Ordering<PrintingPoint> MAX_X = new Ordering<PrintingPoint>() {
			@Override
			public int compare(PrintingPoint arg0, PrintingPoint arg1) {
				return Ints.compare(arg0.x, arg1.x);
			}
		};

		final static Ordering<PrintingPoint> MAX_Y = new Ordering<PrintingPoint>() {
			@Override
			public int compare(PrintingPoint arg0, PrintingPoint arg1) {
				return Ints.compare(arg0.y, arg1.y);
			}
		};

		PrintingPoint(int x, int y, boolean flipX, boolean flipY) {
			this.x = x;
			this.y = y;
			this.flipX = flipX;
			this.flipY = flipY;
		}
	};

	private static ImmutableMap<Face, PrintingPoint> toShow =
		ImmutableMap.<Face, PrintingPoint>builder()
			.put(Face.TOP, new PrintingPoint(1, 0, false, false))
			.put(Face.FRONT, new PrintingPoint(1, 1, false, false))
			.put(Face.LEFT, new PrintingPoint(0, 1, false, false))
			.put(Face.RIGHT, new PrintingPoint(2, 1, false, false))
			.put(Face.BOTTOM, new PrintingPoint(1, 2, false, false))
			// .put(Face.BACK, new PrintingPoint(1, 3, false, false))
			.build();

	public static String printState(State s) {
		int intCntX = PrintingPoint.MAX_X.max(toShow.values()).x + 1;
		int intCntY = PrintingPoint.MAX_Y.max(toShow.values()).y + 1;
		String[][] outValues = new String
				[intCntY * s.size() + intCntY - 1]
				[intCntX * s.size() + intCntX - 1]
				;
		for (Entry<Face, PrintingPoint> e : toShow.entrySet()) {
			Face face = e.getKey();
			PrintingPoint ppoint = e.getValue();

			for (int x=0; x<s.size(); ++x) {
				for (int y=0; y<s.size(); ++y) {
					outValues
						[ppoint.y * s.size() + ppoint.y + y]
						[ppoint.x * s.size() + ppoint.x + x]
								= "" + s.getCell(face, x, y).shortcut;
					/*
					System.out.println(
							(ppoint.x * s.size() + x) + ":" +
							(ppoint.y * s.size() + y) + "->" +
								s.getCell(face, x, y).shortcut);
					*/
				}
			}
		}

		return Joiner.on("\n").join(
				Iterables.transform(Arrays.asList(outValues),
						new Function<String[], String>() {
							@Override
							public String apply(String[] line) {
								return Joiner.on("").join(
										Iterables.transform(
												Arrays.asList(line),
												new Function<String, String>() {
													@Override
													public String apply(String arg0) {
														return arg0 == null ? " " : arg0;
													}
												}));
							}
						}));
	}

	enum Face {
		FRONT,
		TOP,
		LEFT,
		RIGHT,
		BOTTOM,
		BACK
		;
	}

	enum Axis {
		HORIZONTAL(Face.LEFT, Face.FRONT, Face.RIGHT, Face.BACK),
		VERTICAL(Face.BOTTOM, Face.FRONT, Face.TOP, Face.BACK),
		Z_AXIS(Face.BOTTOM, Face.LEFT, Face.TOP, Face.RIGHT)
		;

		Face a1, a2, a3, a4;

		Axis(Face a1, Face a2, Face a3, Face a4) {
			this.a1 = a1;
			this.a2 = a2;
			this.a3 = a3;
			this.a4 = a4;
		}
	}

	public static void main(String[] args) {
		// State s = new NormalState(2);

		System.out.println(printState(new NormalState(3)));
	}
}
