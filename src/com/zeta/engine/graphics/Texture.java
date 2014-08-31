package com.zeta.engine.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class Texture {

	private final int width, height;
	private final int[] pixelData;
	
	private Texture(int width, int height, int[] pixelData) {
		this.width = width;
		this.height = height;
		this.pixelData = pixelData;
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
	
	public static Texture load(String path) {
		return load(new File(path));
	}
	
	public static Texture load(File file) {
		
		int width, height;
		int[] pixelData;
		
		try {
	
			BufferedImage image = ImageIO.read(file);
			width = image.getWidth();
			height = image.getHeight();
			pixelData = new int[width * height];
			image.getRGB(0, 0, width, height, pixelData, 0, width);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			System.err.println("Failed to load texture: " + file.getAbsolutePath());
			
			width = 0;
			height = 0;
			pixelData = new int[0];
		}
		
		return new Texture(width, height, pixelData);
		
	}
	
	public static Texture load(URL url) {
		
		int width, height;
		int[] pixelData;
		
		try {
			
			BufferedImage image = ImageIO.read(url);
			width = image.getWidth();
			height = image.getHeight();
			pixelData = new int[width * height];
			image.getRGB(0, 0, width, height, pixelData, 0, width);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			System.err.println("Failed to load texture: " + url.getPath());
			
			width = 10;
			height = 10;
			pixelData = new int[width*height];
			Arrays.fill(pixelData, 0xff00ff);
		}
		
		return new Texture(width, height, pixelData);
	}
	
}
