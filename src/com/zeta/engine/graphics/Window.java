package com.zeta.engine.graphics;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import com.zeta.engine.Game;

public final class Window {

	private static Window window;
	
	private final JFrame frame;
	private final Game game;
	
	private Window(Game game, String title) {
		this.game = game;
		
		frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(Screen.getCanvas());
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosed(WindowEvent e) {
				Window.this.game.exit();
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				Window.this.game.exit();
			}
			
		});
		
	}
	
	public static void show() {
		window.frame.setVisible(true);
	}
	
	public static void setTitle(String title) {
		window.frame.setTitle(title);
	}
	
	public static String getTitle() {
		return window.frame.getTitle();
	}
	
	public static int getWidth() {
		return window.frame.getWidth();
	}
	
	public static int getHeight() {
		return window.frame.getHeight();
	}
	
	public static void create(Game game, String title) {
		if (window == null) {
			window = new Window(game, title);
		}
		
	}
	
}
