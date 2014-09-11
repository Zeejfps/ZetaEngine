package com.engine.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class Bitmap {

	private final int width, height;
	private final int[] pixelData;
	private final Graphics graphics;
	
	public Bitmap(int width, int height, int[] pixelData) {
		this.width = width;
		this.height = height;
		this.pixelData = pixelData;
		
		graphics = new Graphics(this);
	}
	
	public Bitmap(int width, int height) {
		this(width, height, new int[width * height]);
	}
	
	public Bitmap subBitmap(int x, int y, int width, int height) {
		
		int xStart = x;
		int yStart = y;
		int xEnd = x + width;
		int yEnd = y + height;
		int[] pixels = new int[width * height];
		
		for (int i = yStart, ii = 0; i < yEnd; i++, ii++) {
			
			for (int j = xStart, jj = 0; j < xEnd; j++, jj++) {
				
				pixels[ii*width + jj] = getPixel(j, i);
				
			}
			
		}
		
		return new Bitmap(width, height, pixels);
	}
	
	public void setPixel(int x, int y, int color) {
		setPixel(y*width + x, color);
	}
	
	public void setPixel(int index, int color) {
		pixelData[index] = color;
	}
	
	public void fill(int color) {
		Arrays.fill(pixelData, color);
	}
	
	public int getPixel(int x, int y) {
		return getPixel(y * width + x);
	}
	
	public int getPixel(int index) {
		return pixelData[index];
	}
	
	public Graphics getGraphics() {
		return graphics;
	}
	
	public int[] getPixelData() {
		return pixelData;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public static Bitmap load(String path) throws IOException {
		return load(new File(path));
	}
	
	public static Bitmap load(File file) throws IOException {
		BufferedImage image = ImageIO.read(file);
		return load(image);
	}
	
	public static Bitmap load(URL url) throws IOException {
		BufferedImage image = ImageIO.read(url);
		return load(image);
	}
	
	public static Bitmap load(BufferedImage image) {
		
		int width, height;
		int[] pixelData;

		width = image.getWidth();
		height = image.getHeight();
		pixelData = new int[width * height];
		image.getRGB(0, 0, width, height, pixelData, 0, width);
		
		return new Bitmap(width, height, pixelData);
	}
	
}
