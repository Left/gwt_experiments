package com.vridosh.drawer;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;

public class SwingDrawer {
	private static final ImmutableMap<Integer, IMouseListener.MouseButton> BUTTONS_TRANSLATION =
			ImmutableMap.of(
					MouseEvent.BUTTON1, IMouseListener.MouseButton.LEFT,
					MouseEvent.BUTTON2, IMouseListener.MouseButton.MIDDLE,
					MouseEvent.BUTTON3, IMouseListener.MouseButton.RIGHT);

	private static final class ComponentExtension extends Component {
		// The image that will contain everything that has been drawn on
		// bufferGraphics.
		private final Image offscreen;

		// The object we will use to write with instead of the standard
		// screen graphics
		private final Graphics bufferGraphics;

		private final ICanvasDrawer impl;
		private final Dimension dim;
		private static final long serialVersionUID = 4962975671723630173L;

		private ComponentExtension(final ICanvasDrawer impl, final Dimension dim) {
			this.impl = impl;
			this.dim = dim;

			this.offscreen = new BufferedImage(dim.width, dim.height, BufferedImage.TYPE_INT_ARGB);
			this.bufferGraphics = offscreen.getGraphics();

			final Graphics2D g2 = (Graphics2D) bufferGraphics.create();
			g2.setPaint(java.awt.Color.WHITE);
			g2.fillRect(0, 0, dim.width, dim.width);
		}

		@Override
		public void update(final Graphics g) {
			paint(g);
		}

		@Override
		public void paint(final Graphics g) {

			final Graphics2D g2 = (Graphics2D) bufferGraphics.create();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

			impl.draw(new SwingCanvasContext(g2));

			// draw the offscreen image to the screen like a normal image.
			// Since offscreen is the screen width we start at 0,0.
			g.drawImage(offscreen, 0, 0, this);
		}

		@Override
		public boolean isOpaque() {
			return false;
		}
	}

	private static final class SwingCanvasContext implements ICanvasContext {
		private final LinkedList<Graphics2D> savedContexts = Lists.newLinkedList();
		private Graphics2D currentContext;
		Path2D.Double path = null;

		private SwingCanvasContext(final Graphics2D g2) {
			this.currentContext = g2;
		}

		@Override
		public void stroke() {
			currentContext.draw(path);
		}

		@Override
		public void setLineWidth(final double width) {
			currentContext.setStroke(new BasicStroke((float) width));
		}

		@Override
		public void save() {
			savedContexts.push(currentContext);
			currentContext = (Graphics2D) currentContext.create();
		}

		@Override
		public void restore() {
			currentContext = savedContexts.pop();
		}

		@Override
		public void moveTo(final double x, final double y) {
			path.moveTo(x, y);
		}

		@Override
		public void lineTo(final double x, final double y) {
			path.lineTo(x, y);
		}

		@Override
		public void fillRect(final double x, final double y, final double w, final double h) {
			currentContext.fill(new Rectangle2D.Double(x, y, w, h));
		}

		@Override
		public void fill() {
			currentContext.fill(path);
		}

		@Override
		public void closePath() {
			path.closePath();
		}

		@Override
		public void clip() {
			currentContext.clip(path);
		}

		@Override
		public void clearRect(final double x, final double y, final double w, final double h) {
			// clear
			currentContext.setComposite(AlphaComposite
					.getInstance(AlphaComposite.CLEAR));
			currentContext.fill(new Rectangle2D.Double(x, y, w, h));
			// reset composite
			currentContext.setComposite(AlphaComposite
					.getInstance(AlphaComposite.SRC_OVER));
		}

		@Override
		public void bezierCurveTo(final double c1x, final double c1y,
				final double c2x, final double c2y, final double x, final double y) {
			path.curveTo(c1x, c1y, c2x, c2y, x, y);
		}

		@Override
		public void beginPath() {
			path = new Path2D.Double();
		}

		class SolidColorFillStyle extends java.awt.Color implements
		FillStyle {
			public SolidColorFillStyle(final double r, final double g,
					final double b, final double a) {
				super((float) r, (float) g, (float) b, (float) a);
			}

			private static final long serialVersionUID = 1L;
		}

		@Override
		public FillStyle createSolidColorFill(final int r, final int g, final int b,
				final double a) {
			return new SolidColorFillStyle(r / 255., g / 255.,
					b / 255., a);
		}

		@Override
		public void setFillStyle(final FillStyle style) {
			currentContext.setPaint((Paint) style);
		}

		@Override
		public void setStrokeStyle(final FillStyle style) {
			currentContext.setPaint((Paint) style);
		}
	}

	protected static void openSwingActualDrawer(final ICanvasDrawer impl) {
		final JFrame f = new JFrame("Painter test");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(1000, 1000);

		impl.init();

		// To get the width and height of the applet.
		final Dimension dim = f.getSize();


		final Component component = new ComponentExtension(impl, dim);

		if  (impl instanceof IMouseListener) {
			final IMouseListener listener = (IMouseListener) impl;
			component.addMouseListener(new MouseListener() {
				@Override
				public void mouseReleased(final MouseEvent arg0) {
					listener.onMouseButtonUp(
							BUTTONS_TRANSLATION.get(arg0.getButton()),
							arg0.getX(),
							arg0.getY());
				}

				@Override
				public void mousePressed(final MouseEvent arg0) {
					listener.onMouseButtonDown(
							BUTTONS_TRANSLATION.get(arg0.getButton()),
							arg0.getX(),
							arg0.getY());
				}

				@Override
				public void mouseExited(final MouseEvent arg0) {

				}

				@Override
				public void mouseEntered(final MouseEvent arg0) {

				}

				@Override
				public void mouseClicked(final MouseEvent arg0) {

				}
			});

			component.addMouseMotionListener(new MouseMotionListener() {
				@Override
				public void mouseMoved(final MouseEvent arg0) {
					processEvent(arg0);
				}

				private void processEvent(final MouseEvent arg0) {
					final ImmutableSet.Builder<IMouseListener.MouseButton> btnsBuilder = ImmutableSet.builder();
					if (SwingUtilities.isLeftMouseButton(arg0)) {
						btnsBuilder.add(IMouseListener.MouseButton.LEFT);
					}
					if (SwingUtilities.isRightMouseButton(arg0)) {
						btnsBuilder.add(IMouseListener.MouseButton.RIGHT);
					}
					if (SwingUtilities.isMiddleMouseButton(arg0)) {
						btnsBuilder.add(IMouseListener.MouseButton.MIDDLE);
					}
					listener.onMouseButtonMove(
							btnsBuilder.build(),
							arg0.getX(),
							arg0.getY());
				}

				@Override
				public void mouseDragged(final MouseEvent arg0) {
					processEvent(arg0);
				}
			});
		}

		final Timer timer = new Timer(1000 / 25, new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				// component.invalidate();
				component.repaint();
			}
		});
		timer.setInitialDelay(0);
		timer.start();

		component.setVisible(true);
		component.setBackground(null);
		f.add(component);
		f.setVisible(true);
	}

	public SwingDrawer() {
		super();
	}

}