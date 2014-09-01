package com.tests.snakegame;

import java.awt.event.KeyEvent;
import java.util.Random;

import com.zeta.engine.Input;
import com.zeta.engine.WindowedGame;
import com.zeta.engine.graphics.Bitmap;
import com.zeta.engine.graphics.Graphics;

public class SnakeGame extends WindowedGame {
	
	public static final int WIDTH = 800, HEIGHT = 600, SCALE = 2;
	public static final String TITLE = "Zeta Engine v0.4";
	
	double x = 0.0, y = 0.0;
	Bitmap testMap = Bitmap.load("res/test3.png");
	Bitmap randomMap = new Bitmap(50, 50);
	
	Snake snake = new Snake();
	
	public SnakeGame() {
		super(WIDTH, HEIGHT, SCALE, TITLE);
		Random r = new Random();
		
		for (int i = 0; i < randomMap.getHeight(); i++) {
			for (int j = 0; j < randomMap.getWidth(); j++) {
				randomMap.getGraphics().drawPixel(j, i, r.nextInt());
			}
		}
		//setMaxUps(5);
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
		
		snake.update();
		
	}

	@Override
	protected void render(Graphics g) {
		g.fill(0);
		g.drawRect(0, 0, 20, 30, 0x0000ff);
		testMap.getGraphics().drawPixel(10, 10, 0x0000ff);
		g.drawBitmap((int)x, (int)y, testMap);
		g.drawBitmap(0, 0, randomMap);
		
		snake.render(g);
		
		screen.swapBuffers();
	}

	public static void main(String[] args) {
		
		WindowedGame game = new SnakeGame();
		game.launch();
		
	}
	
}
