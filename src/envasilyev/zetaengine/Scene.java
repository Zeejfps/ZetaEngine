package envasilyev.zetaengine;

import java.util.ArrayList;
import java.util.List;

import envasilyev.zetaengine.gfx.Graphics;

public abstract class Scene {

	private final List<GameObject> gameObjects;
	private boolean loaded;

	public Scene() {
		this.gameObjects = new ArrayList<>();
	}
	
	public final boolean addGameObject(GameObject obj) {
		return gameObjects.add(obj);
	}
	
	public final boolean removeGameObject(GameObject obj) {
		return gameObjects.remove(obj);
	}

	void load() {
		onLoad();
		loaded = true;
	}

	void start() {
		onStart();
	}

	void update() {
		for (GameObject obj : gameObjects) {
			obj.update();
		}
		onUpdate();
	}

	void fixedUpdate() {
		for (GameObject obj : gameObjects) {
			obj.fixedUpdate();
		}
		onFixedUpdate();
	}

	void render() {
		onRender();
		for (GameObject obj : gameObjects) {
			obj.render();
		}
	}

	void postRender() {
		onPostRender();
	}

	void unload() {
		onUnload();
		loaded = false;
	}

	public boolean isLoaded() {
		return loaded;
	}

	protected void onLoad(){}

	protected void onStart() {}

	protected void onUpdate(){}

	protected void onFixedUpdate(){}

	protected void onRender(){}

	protected void onPostRender(){}

	protected void onUnload(){}
}
