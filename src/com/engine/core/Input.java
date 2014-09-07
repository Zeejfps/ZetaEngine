package com.engine.core;

import java.awt.Canvas;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public final class Input {

	public static final int MAX_KEYS = 256;
	public static final int MAX_BUTTONS = 5;
	private static Input input;
	
	private final KeyListener keyListener;
	private final MouseListener mouseListener;
	private final boolean[] keyButtons;
	private final boolean[] mouseButtons;
	private int mouse_x = 0;
	private int mouse_y = 0;
	
	private Input(Canvas canvas) {
		
		keyButtons = new boolean[MAX_KEYS];
		mouseButtons = new boolean[MAX_BUTTONS];
		keyListener = new KeyListener();
		mouseListener = new MouseListener();
		canvas.addKeyListener(keyListener);
		canvas.addMouseListener(mouseListener);
		canvas.addMouseMotionListener(mouseListener);
		
	}
	
	public static boolean isKeyDown(int keyCode) {
		return input.keyButtons[keyCode];
	}
	
	public static boolean isKeyReleased(int keyCode) {
		return !input.keyButtons[keyCode];
	}

	public static boolean isMouseButtonDown(int button) {
		return input.mouseButtons[button];
	}
	
	public static boolean isMouseButtonReleased(int button) {
		return !input.mouseButtons[button];
	}
	
	public static void create(Canvas canvas) {
		
		if (input == null) {
			input = new Input(canvas);
		}
		
	}
	
	public static int getMouseX() {
		return input.mouse_x;
	}
	
	public static int getMouseY() {
		return input.mouse_y;
	}
	
	private class KeyListener extends KeyAdapter {

		@Override
		public void keyTyped(KeyEvent e) {
			keyButtons[e.getKeyCode()] = true;
		}

		@Override
		public void keyPressed(KeyEvent e) {
			keyButtons[e.getKeyCode()] = true;
		}

		@Override
		public void keyReleased(KeyEvent e) {
			keyButtons[e.getKeyCode()] = false;
		}
		
	}
	
	private class MouseListener extends MouseAdapter {
	
		@Override
		public void mouseMoved(MouseEvent e) {
			mouse_x = e.getX();
			mouse_y = e.getY();
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			mouseButtons[e.getButton()] = true;
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			mouseButtons[e.getButton()] = false;
		}
		
	}
	
}
