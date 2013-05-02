package com.vridosh.drawer;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class GifEncoderRunner {
	public static void main(String[] args) throws FileNotFoundException {
		AnimatedGifEncoder e = new AnimatedGifEncoder();
		e.start(new FileOutputStream("e:\\tmp\\out.gif"));
		java.awt.Color transparent = new java.awt.Color(12, 34, 56);
		// e.setTransparent(transparent);
		
		e.setQuality(1);
		e.setRepeat(150);
		e.setDelay(1000/25);   // 25 frames per sec
		
		int max = 100;
		for (int i = 0; i < max; ++i) {
			BufferedImage img = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
			Graphics2D gr = (Graphics2D) img.getGraphics();

			gr.setColor(java.awt.Color.BLUE);
			gr.fillRect(0, 0, img.getWidth(), img.getHeight());
			
			// gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			// gr.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
			// gr.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			// gr.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
						
			gr.setColor(java.awt.Color.RED);
			gr.drawOval(
					img.getWidth()/2 + (int)(Math.sin(i*Math.PI*2./max)*60.) - 20, 
					img.getHeight()/2 + (int)(Math.cos(i*Math.PI*2./max)*30.) - 20, 
					10 + (int)Math.abs(Math.sin(i*Math.PI*2./max)*10.), 
					10 + (int)Math.abs(Math.cos(i*Math.PI*2./max)*10.));
			
			e.addFrame(img);
		}

		e.finish();
	}

}
