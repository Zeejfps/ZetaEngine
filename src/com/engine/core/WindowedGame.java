package com.engine.core;

import com.engine.graphics.Window;

public abstract class WindowedGame extends Game {

	protected Window window;
	
	public WindowedGame(int width, int height, int scale, String title) {
		super(width, height, scale);
		window = new Window(this, title);
	}
	
	protected void init() {
		window.show();
	}

	public Window getWindow() {
		return window;
	}
	
}
