package com.engine.core.components;

import com.engine.core.GameObject;
import com.engine.graphics.Animation;

public class Animator extends Component {

	private final SpriteRenderer spriteRenderer;
	private Animation animation;
	
	public Animator(SpriteRenderer spriteRenderer, Animation animation, GameObject gameObject) {
		super(gameObject);
		this.spriteRenderer = spriteRenderer;
		this.animation = animation;
	}

	@Override
	public void update() {
		animation.play();
		spriteRenderer.setBitmap(animation.getCurrentFrame());
	}
	
	public void setAnimation(Animation animation) {
		this.animation = animation;
	}
	
	public Animation getAnimation() {
		return animation;
	}

}
