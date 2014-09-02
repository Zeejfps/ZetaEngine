package com.zeta.engine;

import com.zeta.engine.graphics.Window;

public abstract class WindowedGame extends Game {

	public WindowedGame(int width, int height, int scale, String title) {
		super(width, height, scale);
		Window.create(this, title);
	}
	
	protected void init() {
		Window.show();
	}

}
