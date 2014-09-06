package com.engine.graphics;

public class Animation {

	private final Bitmap[] frames;
	private int frame = 0;
	private int fps;
	private long startTime;

	private boolean loop = true;
	
	public Animation(int fps, Bitmap[] frames) {
		this.frames = frames;
		this.fps = fps;
		startTime = System.currentTimeMillis();
	}
	
	public void play() {
		int framesPerMs = 1000 / fps;
		if (System.currentTimeMillis() - startTime >= framesPerMs) {
			
			frame++;
			startTime = System.currentTimeMillis();
			if (frame >= frames.length) {
				
				if (loop)
					frame = 0;
			}
			
		}
		
	}

	
	public void setLooping(boolean looping) {
		this.loop = looping;
	}
	
	public void setFps(int fps) {
		this.fps = fps;
	}
	
	public Bitmap getCurrentFrame() {
		return frames[frame];
	}
	
	public Bitmap[] getFrames() {
		return frames;
	}
	
	public boolean isLooping() {
		return loop;
	}
	
	public static Animation load(int fps, int[] indecies, SpriteSheet spriteSheet) {
		
		Bitmap[] frames = new Bitmap[indecies.length];
		for (int i = 0; i < indecies.length; i++) {
			frames[i] = spriteSheet.getSprite(indecies[i]);
		}
		
		return new Animation(fps, frames);
	}
	
}
