package com.zeta.engine;

import com.zeta.engine.graphics.Graphics;
import com.zeta.engine.graphics.Screen;

public abstract class Game {

	private volatile boolean running = false;
	private final GameThread gameThread = new GameThread();
	private int maxUps = 60;
	
	protected final Screen screen;
	
	public Game(int width, int height, int scale) {
		screen = new Screen(width, height, scale);
		Input.create(screen.getCanvas());
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
	
	public Screen getScreen() {
		return screen;
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
			
			Graphics g = screen.getBitmap().getGraphics();
			init();
			while(running) {
				
				double nsPerUpdate = NS_PER_SEC / maxUps;
	
				double current = System.nanoTime();
				double elapsed = current - previous;
				
				previous = current;
				lag += elapsed;
				
				while (lag >= nsPerUpdate) {
					
					update();
					updates ++;
					lag -= nsPerUpdate;
				}

				render(g);
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
