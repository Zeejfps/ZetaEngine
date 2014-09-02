package com.zeta.engine.graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public final class Screen {

	private final Canvas canvas;
	private final int width, height, scale;
	private final BufferedImage screenBuffer;
	private final Bitmap screenBitmap;
	
	public Screen(int width, int height, int scale) {
		
		this.width = width / scale;
		this.height = height / scale;
		this.scale = scale;
		
		screenBuffer = new BufferedImage(width / scale, height / scale, BufferedImage.TYPE_INT_RGB);
		int[] screenPixelData = ((DataBufferInt)screenBuffer.getRaster().getDataBuffer()).getData();
		
		screenBitmap = new Bitmap(this.width, this.height, screenPixelData);
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setBackground(Color.BLACK);
		canvas.setFocusable(true);
	}
		
	public void swapBuffers() {
		
		if (canvas.isDisplayable()) {
			
			BufferStrategy bs = canvas.getBufferStrategy();
			if (bs == null) {
				canvas.createBufferStrategy(2);
				return;
			}
			
			Graphics g = bs.getDrawGraphics();
			g.drawImage(screenBuffer, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
			g.dispose();
			
			bs.show();
			Toolkit.getDefaultToolkit().sync();
			
		}
		
	}
	
	public com.zeta.engine.graphics.Graphics getGraphics() {
		return screenBitmap.getGraphics();
	}
	
	public Bitmap getBitmap() {
		return screenBitmap;
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getScale() {
		return scale;
	}
	
}
