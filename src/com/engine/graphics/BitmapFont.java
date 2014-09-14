package com.engine.graphics;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class BitmapFont {
	
	public static final BitmapFont ARIAL = BitmapFont.createFont(BitmapFont.class.getResource("/fonts/Arial.fnt"));
	public static final BitmapFont ARIAL_BOLD = BitmapFont.createFont(BitmapFont.class.getResource("/fonts/ArialBold.fnt"));
	
	private ArrayList<Glyph> fontChars;
	private int lineHeight;
	
	private BitmapFont(int lineHeight, int spacing, ArrayList<Glyph> fontChars) {
		this.lineHeight = lineHeight; 
		this.fontChars = fontChars;
	}
	
	public int getLineHeight() {
		return lineHeight;
	}
	
	public Glyph getChar(int id) {
		for (Glyph fontChar : fontChars) {
			if (fontChar.id == id) return fontChar;
		}
		return null;
	}
	
	private static Glyph createGlyph(int id, int xOffset, int yOffset, int xAdvance, int x, int y, int width, int height, Bitmap page) {
		
		int[] pixelData = new int[width * height];
		for (int i = 0; i < height; i++) {
			
			for (int j = 0; j < width; j++) {
				
				pixelData[i*width + j] = page.getPixel(x+j, y+i);
				
			}
			
		}
		
		Bitmap bitmap = new Bitmap(width, height, pixelData);
		return new Glyph(id, xOffset, yOffset, xAdvance, bitmap);
	}
	
	public static BitmapFont createFont(URL path) {
		Document xmlDoc = getDocument(path);
		xmlDoc.normalize();
		Element fontElement = xmlDoc.getDocumentElement();
		
		//Node infoNode = null; 
		Node commonNode = null, pagesNode = null, charsNode = null;
		
		NodeList fontNodes = fontElement.getChildNodes();
		for (int i = 0; i < fontNodes.getLength(); i++) {
			
			/*
			if (fontNodes.item(i).getNodeName().equals("info")) {
				infoNode = fontNodes.item(i);
			} else 
			//*/
				
			if (fontNodes.item(i).getNodeName().equals("common")) {
				commonNode = fontNodes.item(i);
			} else if (fontNodes.item(i).getNodeName().equals("pages")) {
				pagesNode = fontNodes.item(i);
			} else if (fontNodes.item(i).getNodeName().equals("chars")) {
				charsNode = fontNodes.item(i);
			}
			
		}

		int numPages = 0;
		int lineHeight = 0;
		NamedNodeMap commonAttribs = commonNode.getAttributes();
		for (int i = 0; i < commonAttribs.getLength(); i++) {
			if (commonAttribs.item(i).getNodeName().equals("pages")) {
				numPages = Integer.parseInt(commonAttribs.item(i).getNodeValue());
			} else if (commonAttribs.item(i).getNodeName().equals("lineHeight")) {
				lineHeight = Integer.parseInt(commonAttribs.item(i).getNodeValue());
			}
		}
		
		Bitmap[] pages = new Bitmap[numPages];
		NodeList pagesList = pagesNode.getChildNodes();
		for (int i = 0; i < pagesList.getLength(); i++) {
			if (pagesList.item(i).getNodeName().equals("page")) {
				int id = 0;
				String file = "/fonts/";
				NamedNodeMap pageAttribs = pagesList.item(i).getAttributes();
				for (int j = 0; j < pageAttribs.getLength(); j++) {
					if (pageAttribs.item(j).getNodeName().equals("id")) {
						id = Integer.parseInt(pageAttribs.item(j).getNodeValue());
					} else if (pageAttribs.item(j).getNodeName().equals("file")) {
						file += pageAttribs.item(j).getNodeValue();
					}
				}
				try {
					pages[id] = Bitmap.load(BitmapFont.class.getResource(file));
				} catch (IOException e) {
					System.err.println("Failed to load font file! \n" + file);
				}
			}
			
		}
		
		ArrayList<Glyph> chars = new ArrayList<>();
		
		NodeList charsList = charsNode.getChildNodes();
		for (int i = 0; i < charsList.getLength(); i++) {
			
			if (charsList.item(i).getNodeName().equals("char")) {
				
				int id = 0, x = 0, y = 0, width = 0, height = 0, xOffset = 0, yOffset = 0, xAdvance = 0, page = 0;
				
				NamedNodeMap charAttribs = charsList.item(i).getAttributes();
				for (int j = 0; j < charAttribs.getLength(); j++) {
					
					Node attrib = charAttribs.item(j); 
				
					if (attrib != null) {
						if (attrib.getNodeName().equals("id")) {
							
							id = Integer.parseInt(attrib.getNodeValue());

						} else if (attrib.getNodeName().equals("x")) {
							
							x = Integer.parseInt(attrib.getNodeValue());
							
						} else if (attrib.getNodeName().equals("y")) {
							
							y = Integer.parseInt(attrib.getNodeValue());
							
						} else if (attrib.getNodeName().equals("width")) {
							
							width = Integer.parseInt(attrib.getNodeValue());
							
						} else if (attrib.getNodeName().equals("height")) {
							
							height = Integer.parseInt(attrib.getNodeValue());
							
						} else if (attrib.getNodeName().equals("xoffset")) {
							
							xOffset = Integer.parseInt(attrib.getNodeValue());
							
						} else if (attrib.getNodeName().equals("yoffset")) {
							
							yOffset = Integer.parseInt(attrib.getNodeValue());
							
						} else if (attrib.getNodeName().equals("xadvance")) {
							
							xAdvance = Integer.parseInt(attrib.getNodeValue());
							
						} else if (attrib.getNodeName().equals("page")) {
							
							page = Integer.parseInt(attrib.getNodeValue());
							
						}
						
					}
					
				}
				
				Glyph fontChar = createGlyph(id, xOffset, yOffset, xAdvance, x, y, width, height, pages[page]);
				chars.add(fontChar);
				
			}
			
		}
		
		return new BitmapFont(lineHeight, 12, chars);
	}
	
	private static Document getDocument(URL path) {
		
		try {
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setIgnoringComments(true);
			factory.setIgnoringElementContentWhitespace(true);

			DocumentBuilder builder = factory.newDocumentBuilder();

			return builder.parse(path.openStream());
			
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	static class Glyph {

		public final int id;
		public final int xOffset;
		public final int yOffset;
		public final int xAdvance;
		public final Bitmap bitmap;
		
		private Glyph(int id, int xOffset, int yOffset, int xAdvance, Bitmap bitmap) {
			
			this.id = id;
			this.xOffset = xOffset;
			this.yOffset = yOffset;
			this.xAdvance = xAdvance;
			this.bitmap = bitmap;
			
		}
		
	}
	
}
