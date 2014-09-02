package com.zeta.engine.graphics;

import java.util.Arrays;

public class Graphics {

	private final Bitmap bitmap;
	private final int[] pixelData;
	
	public Graphics(Bitmap bitmap) {
		this.bitmap = bitmap;
		this.pixelData = bitmap.getPixelData();
	}
	
	public Bitmap getBitmap() {
		return bitmap;
	}
	
	public void fill(int color) {
		Arrays.fill(pixelData, color);
	}
	
	public void drawPixel(int x, int y, int color) {
		pixelData[y * bitmap.getWidth() + x] = color;
	}
	
	public void drawRect(int x, int y, int width, int height, int color) {
		
		for (int i = 0; i < height; i ++) {
	
			int yPix = i + y;
			if (yPix < 0 || yPix >= bitmap.getHeight()) continue;
			
			for (int j = 0; j < width; j++) {
				
				int xPix = j + x;
				if (xPix < 0 || xPix >= bitmap.getWidth()) continue;
				
				drawPixel(xPix, yPix, color);
			}
			
		}
		
	}
	
	public void drawBitmap(int x, int y, Bitmap bitmap) {
		
		for (int i = 0; i < bitmap.getHeight(); i ++) {
			
			int yPix = i + y;
			if (yPix < 0 || yPix >= this.bitmap.getHeight()) continue;
			
			for (int j = 0; j < bitmap.getWidth(); j++) {
				
				int xPix = j + x;
				if (xPix < 0 || xPix >= this.bitmap.getWidth()) continue;
				
				drawPixel(xPix, yPix, bitmap.getPixel(j, i));
				
			}
			
		}
		
	}
	
}
