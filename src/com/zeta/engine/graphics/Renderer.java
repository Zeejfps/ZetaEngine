package com.zeta.engine.graphics;

public class Renderer {

	private final Bitmap bitmap;
	private final int[] pixelData;
	
	public Renderer(Bitmap bitmap) {
		this.bitmap = bitmap;
		this.pixelData = bitmap.getPixelData();
	}
	
	public void drawRect(int x, int y, int width, int height, int color) {
		
		for (int i = 0; i < height; i ++) {
	
			int yPix = i + y;
			if (yPix < 0 || yPix >= bitmap.getWidth()) continue;
			
			for (int j = 0; j < width; j++) {
				
				int xPix = j + x;
				if (xPix < 0 || xPix >= bitmap.getHeight()) continue;
				
				pixelData[yPix * bitmap.getWidth() + xPix] = color;
				
			}
			
		}
		
	}
	
	public void drawTexture(int x, int y, Bitmap texture) {
		
		for (int i = 0; i < texture.getHeight(); i ++) {
			
			int yPix = i + y;
			if (yPix < 0 || yPix >= bitmap.getHeight()) continue;
			
			for (int j = 0; j < texture.getWidth(); j++) {
				
				int xPix = j + x;
				if (xPix < 0 || xPix >= bitmap.getWidth()) continue;
				
				pixelData[yPix * bitmap.getWidth() + xPix] = texture.getPixelData()[i * texture.getWidth() + j];
				
			}
			
		}
		
	}
	
}
