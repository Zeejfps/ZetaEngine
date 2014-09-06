package com.engine.core;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import com.engine.graphics.Bitmap;
import com.engine.graphics.Graphics;

public abstract class Game {

	private volatile boolean running = false;
	private final GameThread gameThread = new GameThread();
	private final Canvas canvas;
	private final int width, height, scale;
	private final BufferedImage screenBuffer;
	private final Bitmap screenBitmap;
	
	private int maxUps = 60;
	
	protected final SceneGraph sceneGraph;
	
	public Game(int width, int height, int scale) {
		
		this.width = width;
		this.height = height;
		this.scale = scale;
		this.sceneGraph = new SceneGraph(this);
		
		screenBuffer = new BufferedImage(width/scale, height/scale, BufferedImage.TYPE_INT_RGB);
		int[] screenPixelData = ((DataBufferInt)screenBuffer.getRaster().getDataBuffer()).getData();
		screenBitmap = new Bitmap(width/scale, height/scale, screenPixelData);

		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setBackground(Color.BLACK);
		canvas.setFocusable(true);
		
		Input.create(canvas);
	}
	
	public void launch() {
		if (!running) {
			running = true;
			gameThread.start();
		}
	}
	
	public void exit() {
		if (running) {
			running = false;
		}
	}
	
	public void setMaxUps(int maxUps) {
		this.maxUps = maxUps >= 0 ? maxUps : 60;
	}
	
	private void swapBuffers() {
		
		if (canvas.isDisplayable()) {
			
			BufferStrategy bs = canvas.getBufferStrategy();
			if (bs == null) {
				canvas.createBufferStrategy(2);
				return;
			}
			
			java.awt.Graphics g = bs.getDrawGraphics();
			g.drawImage(screenBuffer, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
			g.dispose();
			
			bs.show();
			Toolkit.getDefaultToolkit().sync();
			
		}
		
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getheight() {
		return height;
	}
	
	public int getScale() {
		return scale;
	}
	
	protected abstract void init();
	
	protected abstract void update();
	
	protected abstract void render(Graphics g);
	
	private class GameThread extends Thread {
		
		private static final double NS_PER_SEC = 1000000000.0;
		
		public void run() {
			
			long debugTime = System.currentTimeMillis();
			int updates = 0, frames = 0;
			double previous = System.nanoTime();
			double lag = 0.0;
			
			init();
			while(running) {
				
				double nsPerUpdate = NS_PER_SEC / maxUps;
	
				double current = System.nanoTime();
				double elapsed = current - previous;
				
				previous = current;
				lag += elapsed;
				
				while (lag >= nsPerUpdate) {
					
					update();
					sceneGraph.update();
					updates ++;
					lag -= nsPerUpdate;
				}

				render(screenBitmap.getGraphics());
				sceneGraph.render(screenBitmap.getGraphics());
				swapBuffers();
				frames++;
				
				if (System.currentTimeMillis() - debugTime >= 1000) {
					System.out.println(updates + " updates, " + frames + " frames");
					updates = 0;
					frames = 0;
					debugTime = System.currentTimeMillis();
				}
			}
			System.exit(0);
			
		}
		
	}
	
}
