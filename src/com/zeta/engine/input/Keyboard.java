package com.zeta.engine.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.zeta.engine.graphics.Screen;

public class Keyboard {

	public static final int MAX_KEYS = 256;
	
	private final Listener listener;
	private final boolean[] down;
	
	public Keyboard(Screen screen) {
		
		down = new boolean[MAX_KEYS];
		listener = new Listener();
		screen.getCanvas().addKeyListener(listener);
		
	}
	
	public boolean keyDown(int keyCode) {
		return down[keyCode];
	}
	
	public boolean keyReleased(int keyCode) {
		return !down[keyCode];
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
