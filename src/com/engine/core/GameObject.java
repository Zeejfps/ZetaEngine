package com.engine.core;

import java.util.ArrayList;

import com.engine.core.components.Component;
import com.engine.graphics.Graphics;

public class GameObject {
	
	private int x, y;
	private float xRot, yRot;
	private float xScale, yScale;
	private ArrayList<Component> components;
	
	public GameObject() {
		x = 0; y = 0;
		xRot = 0f; yRot = 0f;
		xScale = 1f; yScale = 1f;
		components = new ArrayList<>();
	}
	
	protected final void update() {
		for (Component component : components) {
			component.update();
		}
	}
	
	protected final void render(Graphics g) {
		for (Component component : components) {
			component.render(g);
		}
	}
	
	public void addComponent(Component component) {
		components.add(component);
	}
	
	public void rotate(double x, double y) {
		xRot += x; yRot += y;
	}
	
	public void setRotX(float xRot) {
		this.xRot = xRot;
	}
	
	public void setRotY(float yRot) {
		this.yRot = yRot;
	}
	
	public void move(int dx, int dy) {
		x += dx; y += dy;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public float getScaleX() {
		return xScale;
	}
	
	public float getScaleY() {
		return yScale;
	}
	
	public float getRotX() {
		return xRot;
	}
	
	public float getRotY() {
		return yRot;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
}
