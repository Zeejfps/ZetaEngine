package com.engine.core;

import java.awt.Canvas;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public final class Input {

	public static final int MAX_KEYS = 256;
	private static Input input;
	
	private final Listener listener;
	private final boolean[] down;
	
	private Input(Canvas canvas) {
		
		down = new boolean[MAX_KEYS];
		listener = new Listener();
		canvas.addKeyListener(listener);
		
	}
	
	public static boolean isKeyDown(int keyCode) {
		return input.down[keyCode];
	}
	
	public static boolean isKeyReleased(int keyCode) {
		return !input.down[keyCode];
	}

	public static void create(Canvas canvas) {
		
		if (input == null) {
			input = new Input(canvas);
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
