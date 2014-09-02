package com.zeta.engine.graphics;

import java.util.Arrays;

public class Graphics {

	public static final int TRANSPERANCY_NONE = 0;
	public static final int TRANSPERANCY_LOW = 1;
	public static final int TRANSPERANCY_HIGH = 2;
	
	private final Bitmap bitmap;
	private final int[] pixelData;
	
	public Graphics(Bitmap bitmap, int[] pixelData) {
		this.bitmap = bitmap;
		this.pixelData = pixelData;
	}
	
	public int[] getPixelData() {
		return pixelData;
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
		drawBitmap(x, y, bitmap, TRANSPERANCY_LOW);
	}
	
	public void drawBitmap(int x, int y, Bitmap bitmap, int transperancy) {
		
		switch (transperancy) {
		
		case TRANSPERANCY_LOW:
			drawBitmapLowTransparancy(x, y, bitmap);
			break;
			
		case TRANSPERANCY_HIGH:
			drawBitmapNoTransparancy(x, y, bitmap);
			break;
		
		default:
			drawBitmapNoTransparancy(x, y, bitmap);
			break;
		
		}
				
	}
	
	private void drawBitmapNoTransparancy(int x, int y, Bitmap bitmap) {
		for (int i = 0; i < bitmap.getHeight(); i ++) {
			
			int yPix = i + y;
			if (yPix < 0 || yPix >= this.bitmap.getHeight()) continue;
			
			for (int j = 0; j < bitmap.getWidth(); j++) {
				
				int xPix = j + x;
				if (xPix < 0 || xPix >= this.bitmap.getWidth()) continue;
				
				int src = bitmap.getPixel(j, i);
				if (src == 0){
					drawPixel(xPix, yPix, 0xffffff);
				} else {
					drawPixel(xPix, yPix, src);
				}

			}
			
		}
		
	}
	
	private void drawBitmapLowTransparancy(int x, int y, Bitmap bitmap) {
		for (int i = 0; i < bitmap.getHeight(); i ++) {
			
			int yPix = i + y;
			if (yPix < 0 || yPix >= this.bitmap.getHeight()) continue;
			
			for (int j = 0; j < bitmap.getWidth(); j++) {
				
				int xPix = j + x;
				if (xPix < 0 || xPix >= this.bitmap.getWidth()) continue;
				
				int src = bitmap.getPixel(j, i);
				if (src != 0){
					drawPixel(xPix, yPix, src);
				}

			}
			
		}
		
	}
	
}
