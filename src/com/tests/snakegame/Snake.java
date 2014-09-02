package com.tests.snakegame;

import java.util.LinkedList;

import com.zeta.engine.graphics.Graphics;

public class Snake {

	LinkedList<BodyPart> bodyParts = new LinkedList<BodyPart>();
	
	public Snake() {
		
		for (int i = 0; i < 5; i++){
			bodyParts.add(new BodyPart(i*(BodyPart.WIDTH+2), 100, 22, 0));
		}
		
	}
	
	public void update() {
		BodyPart first = bodyParts.poll();
		BodyPart last = bodyParts.peekLast();
		
		first.setX(last.getX() + last.getDX());
		bodyParts.add(first);
	}
	
	public void render(Graphics g) {
		
		for (BodyPart bp : bodyParts) {
			g.drawRect(bp.getX(), bp.getY(), BodyPart.WIDTH, BodyPart.HEIGHT, BodyPart.COLOR);
		}
		
	}
	
	private class BodyPart {
		
		public static final int WIDTH = 20, HEIGHT = 20, COLOR = 0xff00ff00;
		
		private int x, y, dx, dy;
		
		public BodyPart(int x, int y, int dx, int dy) {
			this.x = x;
			this.y = y;
			this.dx = dx;
			this.dy = dy;
		}
		
		public void setX(int x) {
			this.x = x;
		}
		
		public void setY(int y) {
			this.y = y;
		}
		
		public int getX() {
			return x;
		}
		
		public int getY() {
			return y;
		}
		
		public int getDX() {
			return dx;
		}
		
		public int getDY() {
			return dy;
		}
		
	}
	
}
