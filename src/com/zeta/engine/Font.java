package com.zeta.engine;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.zeta.engine.graphics.Bitmap;

public class Font {

	private static final Pattern pages = Pattern.compile("pages=\"2\"");
	
	private HashMap<Integer, Bitmap> charMap = new HashMap<Integer, Bitmap>();
	
	public Bitmap getChar(int id) {
		return charMap.get(id);
	}
	
	public static Font create(String path) {
		
		String name;
		int size, lineHeight, base, width, height, pages;
		boolean bold, italic;
		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line = br.readLine();
			line = br.readLine();
			System.out.println(line);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
}
