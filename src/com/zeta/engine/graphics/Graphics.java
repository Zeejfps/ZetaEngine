package com.zeta.engine.graphics;

import java.util.Arrays;

import com.zeta.engine.Font;

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
		
		int xStart = x < 0 ? 0 : x;
		int xEnd = x + width >= bitmap.getWidth() ? bitmap.getWidth()-1 : x + width;
		
		int yStart = y < 0 ? 0 : y;
		int yEnd = y + height >= bitmap.getHeight() ? bitmap.getHeight() -1 : y + height;
		
		renderRect(xStart, yStart, xEnd, yEnd, color);
	}
	
	private void renderRect(int xStart, int yStart, int xEnd, int yEnd, int color) {
		
		for (int y = yStart; y <= yEnd; y ++) {
			
			for (int x = xStart; x <= xEnd; x++) {
				drawPixel(x, y, color);
			}
			
		}
		
	}
	
	public void drawBitmap(int x, int y, Bitmap bitmap) {
		drawBitmap(x, y, bitmap, Graphics.TRANSPERANCY_LOW);
	}
	
	public void drawBitmap(int x, int y, Bitmap bitmap, int transperancy) {
		
		int xStart = x;
		int xStartImage = 0;
		if (x < 0) {
			xStart  = 0;
			xStartImage -= x;
		}
		
		int yStart = y;
		int yStartImage = 0;
		if (yStart < 0) {
			yStart = 0;
			yStartImage -= y;;
		}
		
		int xEnd = x + bitmap.getWidth() >= this.bitmap.getWidth() ? this.getBitmap().getWidth() - 1 : x + bitmap.getWidth()-1;
		int yEnd = y + bitmap.getHeight() >= this.bitmap.getHeight() ? this.getBitmap().getHeight() - 1 : y + bitmap.getHeight()-1;
		
		switch (transperancy) {
		
		case TRANSPERANCY_LOW:
			renderBitmapLowTransparancy(xStart, yStart, xEnd, yEnd, xStartImage, yStartImage, bitmap);
			break;
			
		case TRANSPERANCY_HIGH:
			renderBitmapLowTransparancy(xStart, yStart, xEnd, yEnd, xStartImage, yStartImage, bitmap);
			break;
		
		default:
			renderBitmapNoTransparancy(xStart, yStart, xEnd, yEnd, xStartImage, yStartImage, bitmap);
			break;
		
		}
				
	}


	private void renderBitmapNoTransparancy(int xStart, int yStart, int xEnd, int yEnd, 
											int xStartImage, int yStartImage,
											Bitmap bitmap) {
		
		for (int y = yStart, yImage = yStartImage; y <= yEnd; y ++, yImage++) {
			
			for (int x = xStart, xImage = xStartImage; x <= xEnd; x++, xImage++) {
				int src = bitmap.getPixel(xImage, yImage);
				if (src == 0) src = 0xffffffff;
				drawPixel(x, y, src);
			}
			
		}
		
	}
	
	private void renderBitmapLowTransparancy(int xStart, int yStart, int xEnd, int yEnd, 
											 int xStartImage, int yStartImage,
											 Bitmap bitmap) {
		
		for (int y = yStart, yImage = yStartImage; y <= yEnd; y ++, yImage++) {
			
			for (int x = xStart, xImage = xStartImage; x <= xEnd; x++, xImage++) {
				int src = bitmap.getPixel(xImage, yImage);
				if (src != 0) drawPixel(x, y, src);
			}
			
		}		
		
	}
	
	public void drawString(int x, int y, Font font, String text) {
		for (int i = 0; i < text.length(); i++) {
			
			Bitmap character = font.getChar((int)text.charAt(i));
			drawBitmap(x, y, character);
			
			x += character.getWidth();
			
		}
		
	}
	
}
