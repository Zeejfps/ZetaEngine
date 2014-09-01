package com.zeta.engine;

import com.zeta.engine.graphics.Screen;
import com.zeta.engine.graphics.Window;

public abstract class WindowedGame {

	private volatile boolean running = false;
	private final GameThread gameThread = new GameThread();
	
	protected final Screen screen;
	protected final Window window;
	
	public WindowedGame(int width, int height, int scale, String title) {
		
		screen = new Screen(width, height, scale);
		window = new Window(this, screen, title);
		
		Input.create(screen);
		
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
	
	protected abstract void update(double dt);
	
	protected abstract void render();
	
	private class GameThread extends Thread {
		
		private static final double NS_PER_SEC = 1000000000.0;
		
		public void run() {
			
			long debugTime = System.currentTimeMillis();
			int updates = 0, frames = 0;
			double currentTime = System.nanoTime();
			
			init();
			while(running) {
				
				double newTime = System.nanoTime();
			    double frameTime = newTime - currentTime;
			    currentTime = newTime;
			    
				update(frameTime / NS_PER_SEC);
				updates++;

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
