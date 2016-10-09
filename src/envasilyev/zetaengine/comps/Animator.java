package envasilyev.zetaengine.comps;

import envasilyev.zetaengine.GameObject;
import envasilyev.zetaengine.gfx.Animation;
import snakegame.Sprite;

public class Animator extends Component {

	private final Sprite sprite;
	private Animation animation;
	
	public Animator(Sprite sprite, Animation animation, GameObject gameObject) {
		super(gameObject);
		this.sprite = sprite;
		this.animation = animation;
	}

	@Override
	public void onUpdate() {
		animation.play();
		sprite.setBitmap(animation.getCurrentFrame());
	}
	
	public void setAnimation(Animation animation) {
		this.animation = animation;
	}
	
	public Animation getAnimation() {
		return animation;
	}

}
