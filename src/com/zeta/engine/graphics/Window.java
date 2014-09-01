package com.zeta.engine.graphics;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import com.zeta.engine.WindowedGame;

public class Window {

	private final JFrame frame;
	private final Screen screen;
	private final WindowedGame game;
	
	public Window(WindowedGame game, Screen screen, String title) {
		this.screen = screen;
		this.game = game;
		
		frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(screen.getCanvas());
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
	
	public void show() {
		frame.setVisible(true);
	}
	
	public void setTitle(String title) {
		frame.setTitle(title);
	}
	
	public Screen getScreen() {
		return screen;
	}
	
	public String getTitle() {
		return frame.getTitle();
	}
	
	public int getWidth() {
		return frame.getWidth();
	}
	
	public int getHeight() {
		return frame.getHeight();
	}
	
}
