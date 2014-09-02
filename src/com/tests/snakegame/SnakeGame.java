package com.tests.snakegame;

import java.awt.event.KeyEvent;

import com.zeta.engine.Input;
import com.zeta.engine.WindowedGame;
import com.zeta.engine.graphics.Screen;

public class SnakeGame extends WindowedGame {
	
	public static final int WIDTH = 800, HEIGHT = 600, SCALE = 2;
	public static final String TITLE = "Zeta Engine v0.3";
	
	double x = 0.0, y = 0.0;
	
	public SnakeGame() {
		super(WIDTH, HEIGHT, SCALE, TITLE);
	}

	@Override
	protected void update() {

		if (Input.isKeyDown(KeyEvent.VK_S)) {
			y++;
		} else if (Input.isKeyDown(KeyEvent.VK_W)) {
			y--;
		}
		
		if (Input.isKeyDown(KeyEvent.VK_D)) {
			x++;
		} else if (Input.isKeyDown(KeyEvent.VK_A)) {
			x--;
		}
		
	}

	@Override
	protected void render() {
		screen.clear(0xff00ff);
		renderer.drawRect(0, 0, 20, 30, 0x00ffff);
		renderer.drawRect((int)x, (int)y, 50, 50, 0xff0000);
		screen.swapBuffers();
	}

	public static void main(String[] args) {
		
		SnakeGame game = new SnakeGame();
		game.launch();
		
	}
	
}
