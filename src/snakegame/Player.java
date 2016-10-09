package snakegame;

import java.awt.event.KeyEvent;

import envasilyev.zetaengine.GameObject;
import envasilyev.zetaengine.Input;
import envasilyev.zetaengine.comps.Animator;
import envasilyev.zetaengine.comps.Component;
import envasilyev.zetaengine.comps.SpriteRenderer;
import envasilyev.zetaengine.gfx.Animation;
import envasilyev.zetaengine.gfx.Bitmap;
import envasilyev.zetaengine.gfx.SpriteSheet;
import envasilyev.zetaengine.math.Vec2f;

public class Player extends GameObject {

	public static final SpriteSheet SHEET = SpriteSheet.load(7, 4, 35, 35, "SpiderSpriteSheet.png");
	
	private final Animation idle_up = Animation.load(1, new int[] {14}, SHEET);
	private final Animation idle_left = Animation.load(1, new int[] {7}, SHEET);
	private final Animation idle_down = Animation.load(1, new int[] {0}, SHEET);
	private final Animation idle_right = Animation.load(1, new int[] {21}, SHEET);
	private final Animation walk_down = Animation.load(11, new int[]{0, 1, 2, 3}, SHEET);
	private final Animation walk_left = Animation.load(11, new int[]{7, 8, 9, 10}, SHEET);
	private final Animation walk_up = Animation.load(11, new int[]{15, 16, 17, 18}, SHEET);
	private final Animation walk_right = Animation.load(11, new int[]{21, 22, 23, 24}, SHEET);
	
	private Sprite sprite;
	private SpriteRenderer spriteRenderer;
	private Animator animator;
	private Direction direction;
	private boolean moving;
	private float speed;
	
	private float xPrev = 0, yPrev = 0;
	
	private enum Direction {
		NORTH, EAST, SOUTH, WEST
	}
	
	public Player() {
		speed = 0.01f;
		direction = Direction.NORTH;
		sprite = new Sprite(SHEET.getSprite(14));
		spriteRenderer = new SpriteRenderer(sprite);
		animator = new Animator(sprite, idle_up, this);
		addComponent(spriteRenderer);
		addComponent(animator);
		addComponent(new MovementScript(this));
	}
	
	private class MovementScript extends Component {

		public MovementScript(GameObject gameObject) {
			super(gameObject);
		}
		
		@Override
		public void onFixedUpdate() {
			
			if (transform.position.x != xPrev || transform.position.y != yPrev) {
				moving = true;
				xPrev = transform.position.x;
				yPrev = transform.position.y;
			} else {
				moving = false;
			}
			
			if (Input.getKey(KeyEvent.VK_W)) {
				direction = Direction.NORTH;
				gameObject.transform.translate(gameObject.transform.forward);
			} 

			else if (Input.getKey(KeyEvent.VK_S)) {
				direction = Direction.SOUTH;
				Vec2f back = Vec2f.negative(gameObject.transform.forward);
				gameObject.transform.translate(back);
			} 
			
			if (Input.getKey(KeyEvent.VK_A)) {
				direction = Direction.WEST;
				gameObject.transform.translate(new Vec2f(-0.01f, 0));
			} else if (Input.getKey(KeyEvent.VK_D)) {
				direction = Direction.EAST;
				gameObject.transform.translate(new Vec2f(0.01f, 0));
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
