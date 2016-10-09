package envasilyev.zetaengine;

import java.util.ArrayList;
import java.util.List;

import envasilyev.zetaengine.comps.Component;
import envasilyev.zetaengine.comps.Transform;
import envasilyev.zetaengine.math.Vec2f;

public class GameObject {
	
	public final Transform transform;
	private List<Component> components;
	
	public GameObject() {
		transform = new Transform(new Vec2f(), new Vec2f(0, 0.01f), 0);
		components = new ArrayList<>();
	}
	
	final void update() {
		for (Component component : components) {
			component.onUpdate();
		}
	}

	final void fixedUpdate() {
		for (Component component : components) {
			component.onFixedUpdate();
		}
	}

	final void render() {
		for (Component component : components) {
			component.onRender();
		}
	}
	
	public void addComponent(Component component) {
		components.add(component);
	}
	
}
