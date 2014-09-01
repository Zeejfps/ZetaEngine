package com.zeta.engine.fonts;

import com.zeta.engine.graphics.Bitmap;

public class FontChar {

	public final int id;
	public final int xOffset;
	public final int yOffset;
	public final int xAdvance;
	public final Bitmap bitmap;
	
	private FontChar(int id, int xOffset, int yOffset, int xAdvance, Bitmap bitmap) {
		
		this.id = id;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.xAdvance = xAdvance;
		this.bitmap = bitmap;
		
	}
	
	public static FontChar create(int id, int xOffset, int yOffset, int xAdvance, int x, int y, int width, int height, Bitmap page) {
		
		int[] pixelData = new int[width * height];
		for (int i = 0; i < height; i++) {
			
			for (int j = 0; j < width; j++) {
				
				pixelData[i*width + j] = page.getPixel(x+j, y+i);
				
			}
			
		}
		
		Bitmap bitmap = new Bitmap(width, height, pixelData);
		return new FontChar(id, xOffset, yOffset, xAdvance, bitmap);
	}
	
}
