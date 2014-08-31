package com.zeta.engine.graphics;

public class RenderContext {

	private final Screen screen;	
	private final int[] screenPixelData;
	
	public RenderContext(Screen screen, int[] screenPixelData) {
		this.screen = screen;
		this.screenPixelData = screenPixelData;
	}
	
	public void drawRect(int x, int y, int width, int height, int color) {
		
		for (int i = 0; i < height; i ++) {
	
			int yPix = i + y;
			if (yPix < 0 || yPix >= screen.getHeight()) continue;
			
			for (int j = 0; j < width; j++) {
				
				int xPix = j + x;
				if (xPix < 0 || xPix >= screen.getWidth()) continue;
				
				screenPixelData[yPix * screen.getWidth() + xPix] = color;
				
			}
			
		}
		
	}
	
	public void drawTexture(int x, int y, Texture texture) {
		
		for (int i = 0; i < texture.getHeight(); i ++) {
			
			int yPix = i + y;
			if (yPix < 0 || yPix >= screen.getHeight()) continue;
			
			for (int j = 0; j < texture.getWidth(); j++) {
				
				int xPix = j + x;
				if (xPix < 0 || xPix >= screen.getWidth()) continue;
				
				screenPixelData[yPix * screen.getWidth() + xPix] = texture.getPixelData()[i * texture.getWidth() + j];
				
			}
			
		}
		
	}
	
}
