package com.engine.graphics;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class SpriteSheet {

	private final Bitmap[] sprites;
	private final int cellsPerRow;
	private final int cellsPerCol;
	private final int cellWidth;
	private final int cellHeight;
	
	public SpriteSheet(int cellsPerRow, int cellsPerCol, int cellWidth, int cellHeight, Bitmap[] sprites) {
		this.cellsPerRow = cellsPerRow;
		this.cellsPerCol = cellsPerCol;
		this.cellWidth = cellWidth;
		this.cellHeight = cellHeight;
		this.sprites = sprites;
	}
	
	public Bitmap[] getSprites() {
		return sprites;
	}
	
	public Bitmap getSprite(int index) {
		return sprites[index];
	}
	
	public Bitmap getSprite(int x, int y) {
		return getSprite(y*cellsPerRow + x);
	}
	
	public int getCellsPerRow() {
		return cellsPerRow;
	}
	
	public int getCellsPerCol() {
		return cellsPerCol;
	}
	
	public int getCellWidth() {
		return cellWidth;
	}
	
	public int getCellHeight() {
		return cellHeight;
	}
	
	public static SpriteSheet load(int cellsPerRow, int cellsPerCol, int cellWidth, int cellHeight, String path) {
		
		try {
			
			Bitmap bitmap = Bitmap.load(path);
			return load(cellsPerRow, cellsPerCol, cellWidth, cellHeight, bitmap);
			
		} catch (IOException e) {
			System.err.println("Failed to load SpriteSheet!\n" + path);
		}
	
		return null;
	}
	
	public static SpriteSheet load(int cellsPerRow, int cellsPerCol, int cellWidth, int cellHeight, URL path) {
		
		try {
			
			Bitmap bitmap = Bitmap.load(path);
return load(cellsPerRow, cellsPerCol, cellWidth, cellHeight, bitmap);
			
		} catch (IOException e) {
			System.err.println("Failed to load SpriteSheet!\n" + path);
		}
	
		return null;
	}
	
	public static SpriteSheet load(int cellsPerRow, int cellsPerCol, int cellWidth, int cellHeight, File file) {
		
		try {
			
			Bitmap bitmap = Bitmap.load(file);
			return load(cellsPerRow, cellsPerCol, cellWidth, cellHeight, bitmap);
			
		} catch (IOException e) {
			System.err.println("Failed to load SpriteSheet!\n" + file.getAbsolutePath());
		}
	
		return null;
	}
	
	public static SpriteSheet load(int cellsPerRow, int cellsPerCol, int cellWidth, int cellHeight, Bitmap bitmap) {
		
		Bitmap[] sprites = new Bitmap[cellsPerRow * cellsPerCol];
		for (int i = 0; i < cellsPerCol; i++) {
			
			for (int j = 0; j < cellsPerRow; j++) {
				
				sprites[i*cellsPerRow + j] = bitmap.subBitmap(j*cellWidth, i*cellHeight, cellWidth, cellHeight);
				
			}
			
		}
		
		return new SpriteSheet(cellsPerRow, cellsPerCol, cellWidth, cellHeight, sprites);
		
	}
	
}
