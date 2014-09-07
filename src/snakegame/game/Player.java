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

public class Player extends GameObject {

	public static final SpriteSheet SHEET = SpriteSheet.load(9, 4, 64, 64, Player.class.getResource("/sheet4.png"));
	
	private final Animation idle_up = Animation.load(1, new int[] {0}, SHEET);
	private final Animation idle_left = Animation.load(1, new int[] {9}, SHEET);
	private final Animation idle_down = Animation.load(1, new int[] {18}, SHEET);
	private final Animation idle_right = Animation.load(1, new int[] {27}, SHEET);
	private final Animation walk_up = Animation.load(9, new int[]{1, 2, 3, 4, 5, 6, 7, 8}, SHEET);
	private final Animation walk_left = Animation.load(9, new int[]{10, 11, 12, 13, 14, 15, 16, 17}, SHEET);
	private final Animation walk_down = Animation.load(9, new int[]{19, 20, 21, 22, 23, 24, 25, 26}, SHEET);
	private final Animation walk_right = Animation.load(9, new int[]{28, 29, 30, 31, 32, 33, 34, 35}, SHEET);
	
	private Bitmap bitmap;
	private SpriteRenderer spriteRenderer;
	private Animator animator;
	private Direction direction;
	private boolean moving;
	private int speed;
	
	private int xPrev = 0, yPrev = 0;
	
	private enum Direction {
		
		NORTH, EAST, SOUTH, WEST;
		
	}
	
	public Player() {
		
		speed = 1;
		direction = Direction.NORTH;
		bitmap = SHEET.getSprite(28);
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
			
			if (getX() != xPrev || getY() != yPrev) {
				moving = true;
				xPrev = getX();
				yPrev = getY();
			} else {
				moving = false;
			}
			
			int x = 0;
			int y = 0;
			
			if (Input.isKeyDown(KeyEvent.VK_W)) {
				direction = Direction.NORTH;
				y = -speed;
			} else if (Input.isKeyDown(KeyEvent.VK_S)) {
				direction = Direction.SOUTH;
				y = speed;
			} 
			
			if (Input.isKeyDown(KeyEvent.VK_A)) {
				direction = Direction.WEST;
				x = -speed;
			} else if (Input.isKeyDown(KeyEvent.VK_D)) {
				direction = Direction.EAST;
				x = speed;
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
			
			gameObject.move(x, y);
			
		}
		
	}
	
}
