package com.engine.core.components;

import com.engine.core.GameObject;
import com.engine.graphics.Graphics;

public abstract class Component {

	protected final GameObject gameObject;
	
	public Component(GameObject gameObject) {
		this.gameObject = gameObject;
	}
	
	public void start() {
		
	}
	
	public void update() {
		
	}
	
	public void render(Graphics g) {
		
	}
	
}
