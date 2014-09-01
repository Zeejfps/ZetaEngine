package com.zeta.engine;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.zeta.engine.graphics.Screen;

public final class Input {

	public static final int MAX_KEYS = 256;
	private static Input input;
	
	private final Listener listener;
	private final boolean[] down;
	
	private Input(Screen screen) {
		
		down = new boolean[MAX_KEYS];
		listener = new Listener();
		screen.getCanvas().addKeyListener(listener);
		
	}
	
	public static boolean keyDown(int keyCode) {
		return input.down[keyCode];
	}
	
	public static boolean keyReleased(int keyCode) {
		return !input.down[keyCode];
	}

	public static void create(Screen screen) {
		
		if (input == null) {
			input = new Input(screen);
		}
		
	}
	
	private class Listener extends KeyAdapter {

		@Override
		public void keyTyped(KeyEvent e) {
			down[e.getKeyCode()] = true;
		}

		@Override
		public void keyPressed(KeyEvent e) {
			down[e.getKeyCode()] = true;
		}

		@Override
		public void keyReleased(KeyEvent e) {
			down[e.getKeyCode()] = false;
		}
		
	}
	
}