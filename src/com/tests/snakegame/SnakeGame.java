package com.tests.snakegame;

import java.awt.event.KeyEvent;

import com.zeta.engine.Input;
import com.zeta.engine.WindowedGame;
import com.zeta.engine.graphics.Bitmap;
import com.zeta.engine.graphics.Graphics;

public class SnakeGame extends WindowedGame {
	
	public static final int WIDTH = 800, HEIGHT = 600, SCALE = 2;
	public static final String TITLE = "Zeta Engine v0.3";
	
	double x = 0.0, y = 0.0;
	Bitmap testMap = Bitmap.load("res/test2.png");
	
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
	protected void render(Graphics g) {
		g.fill(0xff00ff);
		g.drawRect(0, 0, 20, 30, 0x00ffff);
		
		testMap.getGraphics().drawPixel(10, 10, 0x0000ff);
		
		g.drawBitmap((int)x, (int)y, testMap);
		screen.swapBuffers();
	}

	public static void main(String[] args) {
		
		WindowedGame game = new SnakeGame();
		game.launch();
		
	}
	
}
