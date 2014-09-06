package com.engine.core;

import java.util.ArrayList;

import com.engine.graphics.Graphics;

public class SceneGraph {

	private final Game game;
	private final ArrayList<GameObject> gameObjects;
	
	
	public SceneGraph(Game game) {
		this.game = game;
		this.gameObjects = new ArrayList<>();
	}
	
	public void add(GameObject obj) {
		gameObjects.add(obj);
	}
	
	public void remove(GameObject obj) {
		gameObjects.remove(obj);
	}
	
	protected void update() {
		for (GameObject obj : gameObjects) 
			obj.update();
	}
	
	protected void render(Graphics g) {
		for (GameObject obj : gameObjects) 
			obj.render(g);
	}
	
	public Game getGame() {
		return game;
	}
	
}
