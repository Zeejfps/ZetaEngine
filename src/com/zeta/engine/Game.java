package com.zeta.engine;

public abstract class Game {

	private volatile boolean running = false;
	private final GameThread gameThread = new GameThread();
	
	public Game() {
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
