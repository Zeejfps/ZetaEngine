package com.zeta.engine;

import com.zeta.engine.graphics.Screen;

public abstract class Game {

	private volatile boolean running = false;
	private final GameThread gameThread = new GameThread();
	
	public Game(int width, int height, int scale) {
		
		Screen.create(width, height, scale);
		Input.create(Screen.getCanvas());

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
	
	protected abstract void init();
	
	protected abstract void update();
	
	protected abstract void render();
	
	private class GameThread extends Thread {
		
		private static final double NS_PER_SEC = 1000000000.0;
		
		public void run() {
			
			long debugTime = System.currentTimeMillis();
			int updates = 0, frames = 0;
			double previous = System.nanoTime();
			double lag = 0.0;
			
			init();
			while(running) {
				
				double nsPerUpdate = NS_PER_SEC / 60.0;
	
				double current = System.nanoTime();
				double elapsed = current - previous;
				
				previous = current;
				lag += elapsed;
				
				while (lag >= nsPerUpdate) {
					
					update();
					updates ++;
					lag -= nsPerUpdate;
				}

				render();
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
