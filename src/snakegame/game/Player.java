package snakegame.game;

import java.awt.event.KeyEvent;
import java.io.IOException;

import com.engine.core.GameObject;
import com.engine.core.Input;
import com.engine.core.components.Animator;
import com.engine.core.components.Component;
import com.engine.core.components.SpriteRenderer;
import com.engine.graphics.Animation;
import com.engine.graphics.Bitmap;
import com.engine.graphics.SpriteSheet;

public class Player extends GameObject {

	public static final SpriteSheet SHEET = SpriteSheet.load(3, 4, 16, 18, "res/sheet2.png");
	
	private final Animation walk_up = Animation.load(8, new int[]{0, 1, 2}, SHEET);
	private final Animation walk_right = Animation.load(8, new int[]{3, 4, 5}, SHEET);
	private final Animation walk_down = Animation.load(8, new int[]{6, 7, 8}, SHEET);
	private final Animation walk_left = Animation.load(8, new int[]{9, 10, 11}, SHEET);
	
	private Bitmap bitmap;
	private SpriteRenderer spriteRenderer;
	private Animator animator;
	
	public Player() {
		
		try {
			bitmap = Bitmap.load("res/test3.png");
		} catch (IOException e) {
			System.err.println("Failed to load player bitmap!");
		}
		spriteRenderer = new SpriteRenderer(bitmap, this);
		animator = new Animator(spriteRenderer, walk_up, this);
		addComponent(spriteRenderer);
		addComponent(animator);
		addComponent(new MovementScript(this));
		
	}
	
	private class MovementScript extends Component {

		public MovementScript(GameObject gameObject) {
			super(gameObject);
		}
		
		@Override
		public void update() {
			
			if (Input.isKeyDown(KeyEvent.VK_W)) {
				gameObject.move(0, -1);
				animator.setAnimation(walk_up);
			} else if (Input.isKeyDown(KeyEvent.VK_S)) {
				gameObject.move(0, 1);
				animator.setAnimation(walk_down);
			} 
			
			if (Input.isKeyDown(KeyEvent.VK_A)) {
				gameObject.move(-1, 0);
				animator.setAnimation(walk_left);
			} else if (Input.isKeyDown(KeyEvent.VK_D)) {
				gameObject.move(1, 0);
				animator.setAnimation(walk_right);
			}
			
		}
		
	}
	
}
