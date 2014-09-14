package com.engine.core;

import java.util.ArrayList;

import com.engine.core.components.Component;
import com.engine.graphics.Graphics;
import com.engine.utils.Transform;
import com.engine.utils.Vector3;

public class GameObject {
	
	public final Transform transform;
	private ArrayList<Component> components;
	
	public GameObject() {
		transform = new Transform(new Vector3(), new Vector3(0, 0.01f, 0), 0);
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
	
}
