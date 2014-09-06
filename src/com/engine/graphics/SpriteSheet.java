package com.engine.graphics;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class SpriteSheet {

	private final Bitmap[] sprites;
	private final int spritesPerRow;
	private final int spritesPerCol;
	private final int spriteWidth;
	private final int spriteHeight;
	
	public SpriteSheet(int spritesPerRow, int spritesPerCol, int spriteWidth, int spriteHeight, Bitmap[] sprites) {
		this.spritesPerRow = spritesPerRow;
		this.spritesPerCol = spritesPerCol;
		this.spriteWidth = spriteWidth;
		this.spriteHeight = spriteHeight;
		this.sprites = sprites;
	}
	
	public Bitmap[] getSprites() {
		return sprites;
	}
	
	public Bitmap getSprite(int x, int y) {
		return sprites[y * spritesPerRow + x];
	}
	
	public int getSpritesPerRow() {
		return spritesPerRow;
	}
	
	public int getSpritesPerCol() {
		return spritesPerCol;
	}
	
	public int getSpriteWidth() {
		return spriteWidth;
	}
	
	public int getSpriteHeight() {
		return spriteHeight;
	}
	
	public static SpriteSheet load(int spritesPerRow, int spritesPerCol, int spriteWidth, int spriteHeight, String path) {
		
		try {
			
			Bitmap bitmap = Bitmap.load(path);
			return load(spritesPerRow, spritesPerCol, spriteWidth, spriteHeight, bitmap);
			
		} catch (IOException e) {
			System.err.println("Failed to load SpriteSheet!\n" + path);
		}
	
		return null;
	}
	
	public static SpriteSheet load(int spritesPerRow, int spritesPerCol, int spriteWidth, int spriteHeight, URL path) {
		
		try {
			
			Bitmap bitmap = Bitmap.load(path);
			return load(spritesPerRow, spritesPerCol, spriteWidth, spriteHeight, bitmap);
			
		} catch (IOException e) {
			System.err.println("Failed to load SpriteSheet!\n" + path.getPath());
		}
	
		return null;
	}
	
	public static SpriteSheet load(int spritesPerRow, int spritesPerCol, int spriteWidth, int spriteHeight, File file) {
		
		try {
			
			Bitmap bitmap = Bitmap.load(file);
			return load(spritesPerRow, spritesPerCol, spriteWidth, spriteHeight, bitmap);
			
		} catch (IOException e) {
			System.err.println("Failed to load SpriteSheet!\n" + file.getAbsolutePath());
		}
	
		return null;
	}
	
	public static SpriteSheet load(int spritesPerRow, int spritesPerCol, int spriteWidth, int spriteHeight, Bitmap bitmap) {
		
		Bitmap[] sprites = new Bitmap[spritesPerRow * spritesPerCol];
		for (int i = 0; i < spritesPerCol; i++) {
			
			for (int j = 0; j < spritesPerRow; j++) {
				
				sprites[i*spritesPerRow + j] = bitmap.subBitmap(j*spriteWidth, i*spriteHeight, spriteWidth, spriteHeight);
				
			}
			
		}
		
		return new SpriteSheet(spritesPerRow, spritesPerCol, spriteWidth, spriteHeight, sprites);
		
	}
	
}
