package envasilyev.zetaengine.comps;

import envasilyev.zetaengine.Screen;
import snakegame.Sprite;

public class SpriteRenderer extends Component {

	private Sprite sprite;
	
	public SpriteRenderer(Sprite sprite) {
		super(sprite);
		this.sprite = sprite;
	}

	@Override
	public void onRender() {
		Screen.getGraphics().drawBitmap(sprite.getBitmap(), sprite.transform);
	}

}
