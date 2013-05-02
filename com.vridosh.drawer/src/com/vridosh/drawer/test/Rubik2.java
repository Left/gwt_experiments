package com.vridosh.drawer.test;

import java.util.Arrays;
import java.util.Map.Entry;
import java.util.Random;

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

	public static State move(final State orig, final Axis a, final int index) {
		return new State() {
			@Override
			public int size() {
				return orig.size();
			}

			@Override
			public Cell getCell(Face f, int x, int y) {
				if (a.contains(f) && a.selectIndex(x, y) == index) {
					return orig.getCell(a.next(f), x, y);
				}
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
			.put(Face.BACK, new PrintingPoint(1, 3, false, false))
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
		HORIZONTAL(Face.LEFT, Face.FRONT, Face.RIGHT, Face.BACK) {
			@Override
			public int selectIndex(int x, int y) {
				return y;
			}
		},
		VERTICAL(Face.BOTTOM, Face.FRONT, Face.TOP, Face.BACK) {
			@Override
			public int selectIndex(int x, int y) {
				return x;
			}
		},
		Z_AXIS(Face.BOTTOM, Face.LEFT, Face.TOP, Face.RIGHT) {
			@Override
			public int selectIndex(int x, int y) {
				return x;
			}
		}
		;

		Face a1, a2, a3, a4;

		Axis(Face a1, Face a2, Face a3, Face a4) {
			this.a1 = a1;
			this.a2 = a2;
			this.a3 = a3;
			this.a4 = a4;
		}

		public abstract int selectIndex(int x, int y);
		
		public boolean contains(Face f) {
			return index(f) != -1;
		}
		
		public int index(Face f) {
			if (a1 == f) {
				return 0;
			} else if (a2 == f) {
				return 1;
			} else if (a3 == f) {
				return 2;
			} else if (a4 == f) {
				return 3;
			}
			return -1;
		} 
		
		public Face get(int i) {
			if (i == 0) {
				return a1;
			} else if (i == 1) {
				return a2;
			} else if (i == 2) {
				return a3;
			} else if (i == 3) {
				return a4;
			}
			throw new IllegalArgumentException("Argument " + i + " should not be passes");
		}

		public Face next(Face f) {
			return get((index(f) + 1) % 4);
		}
		
		public Face prev(Face f) {
			return get((index(f) + 1) % 4);
		}
	}

	public static void main(String[] args) {
		// State s = new NormalState(2);

		Random r = new Random(125);
		State orig = new NormalState(3);
		System.out.println(printState(orig));
		
		for (int i=0; i<20; ++i) {
			Axis zAxis = Axis.values()[r.nextInt(Axis.values().length)];
			int index = r.nextInt(orig.size());
			System.out.println("Moving " + zAxis.name() + " at " + index);
			orig = move(orig, zAxis, index);
			System.out.println(printState(orig));
		}
		
		// State move2 = move(move1, Axis.VERTICAL, 1);
		
	}
}
