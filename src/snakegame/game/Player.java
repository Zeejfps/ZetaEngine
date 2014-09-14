package snakegame.game;

import java.awt.event.KeyEvent;

import com.engine.core.GameObject;
import com.engine.core.Input;
import com.engine.core.components.Animator;
import com.engine.core.components.Component;
import com.engine.core.components.SpriteRenderer;
import com.engine.graphics.Animation;
import com.engine.graphics.Bitmap;
import com.engine.graphics.SpriteSheet;
import com.engine.utils.Vector3;

public class Player extends GameObject {

	public static final SpriteSheet SHEET = SpriteSheet.load(7, 4, 35, 35, Player.class.getResource("/SpiderSpriteSheet.png"));
	
	private final Animation idle_up = Animation.load(1, new int[] {14}, SHEET);
	private final Animation idle_left = Animation.load(1, new int[] {7}, SHEET);
	private final Animation idle_down = Animation.load(1, new int[] {0}, SHEET);
	private final Animation idle_right = Animation.load(1, new int[] {21}, SHEET);
	private final Animation walk_down = Animation.load(11, new int[]{1, 2, 3, 4, 5, 6}, SHEET);
	private final Animation walk_left = Animation.load(11, new int[]{8, 9, 10, 11, 12, 13}, SHEET);
	private final Animation walk_up = Animation.load(11, new int[]{15, 16, 17, 18}, SHEET);
	private final Animation walk_right = Animation.load(11, new int[]{22, 23, 24, 25, 26, 27}, SHEET);
	
	private Bitmap bitmap;
	private SpriteRenderer spriteRenderer;
	private Animator animator;
	private Direction direction;
	private boolean moving;
	private float speed;
	
	private float xPrev = 0, yPrev = 0;
	
	private enum Direction {
		
		NORTH, EAST, SOUTH, WEST;
		
	}
	
	public Player() {
		
		speed = 0.01f;
		
		direction = Direction.NORTH;
		bitmap = SHEET.getSprite(14);
		spriteRenderer = new SpriteRenderer(bitmap, this);
		animator = new Animator(spriteRenderer, idle_up, this);
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
			
			if (transform.position.x() != xPrev || transform.position.y() != yPrev) {
				moving = true;
				xPrev = transform.position.x();
				yPrev = transform.position.y();
			} else {
				moving = false;
			}
			
			if (Input.isKeyDown(KeyEvent.VK_W)) {
				//direction = Direction.NORTH;
				gameObject.transform.translate(gameObject.transform.forward);
			} 

			else if (Input.isKeyDown(KeyEvent.VK_S)) {
				//direction = Direction.SOUTH;
				Vector3 back = Vector3.negative(gameObject.transform.forward);
				gameObject.transform.translate(back);
			} 
			
			if (Input.isKeyDown(KeyEvent.VK_A)) {
				//direction = Direction.WEST;
				gameObject.transform.rotate(5);
			} else if (Input.isKeyDown(KeyEvent.VK_D)) {
				//direction = Direction.EAST;
				gameObject.transform.rotate(-5);
			}
			
			
			switch (direction) {
				
			case NORTH:
				if (!moving) animator.setAnimation(idle_up);
				else animator.setAnimation(walk_up);
				break;
				
			case EAST:
				if (!moving) animator.setAnimation(idle_right);
				else animator.setAnimation(walk_right);
				break;
				
			case SOUTH:
				if (!moving) animator.setAnimation(idle_down);
				else animator.setAnimation(walk_down);
				break;
				
			case WEST:
				if (!moving) animator.setAnimation(idle_left);
				else animator.setAnimation(walk_left);
				break;
			
			}
			
		}
		
	}
	
}
