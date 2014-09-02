package com.zeta.engine.graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

public final class Screen {

	private static Screen screen;
	
	private final Canvas canvas;
	private final int width, height, scale;
	private final BufferedImage buffer;
	private final int[] screenPixelData;
	
	private Screen(int width, int height, int scale) {
		
		this.width = width / scale;
		this.height = height / scale;
		this.scale = scale;
		
		buffer = new BufferedImage(width / scale, height / scale, BufferedImage.TYPE_INT_RGB);
		screenPixelData = ((DataBufferInt)buffer.getRaster().getDataBuffer()).getData();
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setBackground(Color.BLACK);
		canvas.setFocusable(true);
	}
	
	public static void clear(int color) {
		Arrays.fill(screen.screenPixelData, color);
	}
	
	public static void clear() {
		clear(0);
	}
	
	public static void swapBuffers() {
		
		if (screen.canvas.isDisplayable()) {
			
			BufferStrategy bs = screen.canvas.getBufferStrategy();
			if (bs == null) {
				screen.canvas.createBufferStrategy(2);
				return;
			}
			
			Graphics g = bs.getDrawGraphics();
			g.drawImage(screen.buffer, 0, 0, screen.canvas.getWidth(), screen.canvas.getHeight(), null);
			g.dispose();
			
			bs.show();
			Toolkit.getDefaultToolkit().sync();
			
		}
		
	}
	
	public static void drawRect(int x, int y, int width, int height, int color) {
		
		for (int i = 0; i < height; i ++) {
	
			int yPix = i + y;
			if (yPix < 0 || yPix >= screen.height) continue;
			
			for (int j = 0; j < width; j++) {
				
				int xPix = j + x;
				if (xPix < 0 || xPix >= screen.width) continue;
				
				screen.screenPixelData[yPix * screen.width + xPix] = color;
				
			}
			
		}
		
	}
	
	public static void drawTexture(int x, int y, Texture texture) {
		
		for (int i = 0; i < texture.getHeight(); i ++) {
			
			int yPix = i + y;
			if (yPix < 0 || yPix >= screen.height) continue;
			
			for (int j = 0; j < texture.getWidth(); j++) {
				
				int xPix = j + x;
				if (xPix < 0 || xPix >= screen.width) continue;
				
				screen.screenPixelData[yPix * screen.width + xPix] = texture.getPixelData()[i * texture.getWidth() + j];
				
			}
			
		}
		
	}
	
	public static Canvas getCanvas() {
		return screen.canvas;
	}
	
	public static int getWidth() {
		return screen.width;
	}
	
	public static int getHeight() {
		return screen.height;
	}
	
	public static int getScale() {
		return screen.scale;
	}
	
	public static void create(int width, int height, int scale) {
		
		if (screen == null) {
			screen = new Screen(width, height, scale);
		}
		
	}
	
}
