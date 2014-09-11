package com.engine.graphics;

import com.engine.utils.Transform;

public class Graphics {

	public static final int TRANSPERANCY_NONE = 0;
	public static final int TRANSPERANCY_LOW = 1;
	
	private final Bitmap bitmap;
	private Font font;
	
	public Graphics(Bitmap bitmap) {
		this.bitmap = bitmap;
		font = Font.ARIAL;
	}
	
	public Bitmap getBitmap() {
		return bitmap;
	}
	
	public void fill(int color) {
		bitmap.fill(color);
	}
	
	public void drawPixel(int x, int y, int color) {
		bitmap.setPixel(x, y, color);
	}
	
	public void setFont(Font font) {
		this.font = font;
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
	
	public void drawBitmap(Bitmap bitmap, Transform transform) {
		
		float angle = (float) Math.toRadians(transform.getRotation());
		float sinA  = (float) Math.sin(angle);
		float cosA  = (float) Math.cos(angle);
		
		int half_width  = (int) (bitmap.getWidth() * 0.5);
		int half_height = (int) (bitmap.getHeight() * 0.5);
		
		int p1_x = -half_width;
		int p1_y = -half_height;
		int p2_x = p1_x + bitmap.getWidth();
		int p2_y = p1_y;
		int p3_x = p2_x;
		int p3_y = p2_y + bitmap.getHeight();
		int p4_x = p1_x;
		int p4_y = p3_y;
		
		int x1 = (int) (p1_x * cosA + p1_y * sinA);
		int y1 = (int) (p1_y * cosA - p1_x * sinA);
		int x2 = (int) (p2_x * cosA + p2_y * sinA);
		int y2 = (int) (p2_y * cosA - p2_x * sinA);
		int x3 = (int) (p3_x * cosA + p3_y * sinA);
		int y3 = (int) (p3_y * cosA - p3_x * sinA);
		int x4 = (int) (p4_x * cosA + p4_y * sinA);
		int y4 = (int) (p4_y * cosA - p4_x * sinA);
		
		int min_x = Math.min(Math.min(x1,x2),Math.min(x3, x4));
		int min_y = Math.min(Math.min(y1, y2), Math.min(y3, y4));
		int max_x = Math.max(Math.max(x1,x2),Math.max(x3, x4));
		int max_y = Math.max(Math.max(y1, y2), Math.max(y3, y4));

		int x_offset = (int) Math.round((( transform.position.x() + 1 ) * 0.5) * this.bitmap.getWidth());
		int y_offset = (int) Math.round((( 1 - transform.position.y() ) * 0.5) * this.bitmap.getHeight());
		
		int x_start = x_offset + min_x;
		if (x_start < 0)
			x_start = 0;
	
		int y_start = y_offset + min_y;
		if (y_start < 0) 
			y_start = 0;
		
		int x_end = x_offset + max_x;
		if (x_end > this.bitmap.getWidth())
			x_end = this.bitmap.getWidth();
		
		int y_end = y_offset + max_y;
		if (y_end > this.bitmap.getHeight())
			y_end = this.bitmap.getHeight();
		
		for (int i = y_start; i < y_end; i++) {
			
			for (int j = x_start; j < x_end; j++) {
				
				int x = (int) ((j-x_offset)*cosA - (i-y_offset)*sinA) + half_width;
				int y = (int) ((i-y_offset)*cosA + (j-x_offset)*sinA) + half_height;
				
				if (x < 0 || x >= bitmap.getWidth() || y < 0 || y >= bitmap.getHeight())
					continue;
				
				int src = bitmap.getPixel(x, y);
				if (src == 0xffff00ff) continue;
				
				drawPixel(j, i, bitmap.getPixel(x, y));
				
			}
			
		}
		
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
				if (src == 0 || src == 0xff00ff) src = 0xffffffff;
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
				if (src != 0xffff00ff && src != 0) {
					drawPixel(x, y, src);
				}
				
			}
			
		}		
		
	}
	
	public void drawString(int x, int y, int color, String text) {

		int xCursor = x;
		int yCursor = y;
		
		// loop through all characters
		for (int i = 0; i < text.length(); i++) {
			
			char c = text.charAt(i);
			if (c == '\n') {
				
				yCursor += font.getLineHeight();
				xCursor = x;
				
			} else {
			
				FontChar fontChar = font.getChar((int)text.charAt(i));
				int xRender = xCursor + fontChar.xOffset;
				int yRender = yCursor + fontChar.yOffset;
				renderChar(xRender, yRender, color, fontChar.bitmap);
				
				xCursor += fontChar.xAdvance;
			}
			
		}
		
	}
	
	private void renderChar(int x, int y, int color, Bitmap bitmap) {
		
		//TODO: add clipping, and proper rendering	
		for (int i = 0; i < bitmap.getHeight(); i++) {
			
			int yPix = i + y;
			if (yPix < 0 || yPix >= this.bitmap.getHeight()) continue;
			
			for (int j = 0; j < bitmap.getWidth(); j++) {
				
				int xPix = j + x;
				if (xPix < 0 || xPix >= this.bitmap.getWidth()) continue;
				
				int src = bitmap.getPixel(j, i);
				if (src == 0xffffffff) {
					drawPixel(j+x, i+y, color);
				}
				
			}
			
		}
		
	}
	
}
