package envasilyev.zetaengine.comps;

import envasilyev.zetaengine.GameObject;
import envasilyev.zetaengine.gfx.Graphics;

public abstract class Component {

	protected final GameObject gameObject;
	
	public Component(GameObject gameObject) {
		this.gameObject = gameObject;
	}
	
	public void onStart() {
		
	}
	
	public void onUpdate() {
		
	}

	public void onFixedUpdate() {

	}
	
	public void onRender() {
		
	}
	
}
