package com.engine.graphics;

public class Animation {

	private final Bitmap[] frames;
	private int frame = 0;
	
	public Animation(Bitmap[] frames) {
		this.frames = frames;
	}
	
	public Bitmap getCurrentFrame() {
		return frames[frame];
	}
	
	public static Animation load(String path) {
		return null;
	}
	
}
