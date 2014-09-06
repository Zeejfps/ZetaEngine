package snakegame.game;

import java.awt.event.KeyEvent;
import java.io.IOException;

import com.engine.core.GameObject;
import com.engine.core.Input;
import com.engine.core.components.Component;
import com.engine.core.components.SpriteRenderer;
import com.engine.graphics.Bitmap;

public class Player extends GameObject {

	private Bitmap bitmap;
	private SpriteRenderer spriteRenderer;
	
	public Player() {
		
		try {
			bitmap = Bitmap.load("res/test3.png");
		} catch (IOException e) {
			System.err.println("Failed to load player bitmap!");
		}
		spriteRenderer = new SpriteRenderer(bitmap, this);
		addComponent(spriteRenderer);
		addComponent(new MovementScript(this));
		
	}
	
	private class MovementScript extends Component {

		public MovementScript(GameObject gameObject) {
			super(gameObject);
		}
		
		@Override
		public void update() {
			
			if (Input.isKeyDown(KeyEvent.VK_W)) {
				gameObject.move(0, -5);
			} else if (Input.isKeyDown(KeyEvent.VK_S)) {
				gameObject.move(0, 5);
			}
			
			if (Input.isKeyDown(KeyEvent.VK_A)) {
				gameObject.move(-5, 0);
			} else if (Input.isKeyDown(KeyEvent.VK_D)) {
				gameObject.move(5, 0);
			}
			
		}
		
	}
	
}
