package com.engine.core.components;

import com.engine.core.GameObject;
import com.engine.graphics.Bitmap;
import com.engine.graphics.Graphics;

public class SpriteRenderer extends Component {

	private Bitmap bitmap;
	
	public SpriteRenderer(Bitmap bitmap, GameObject gameObject) {
		super(gameObject);
		this.bitmap = bitmap;
	}

	@Override
	public void render(Graphics g) {
		g.drawBitmap(bitmap, gameObject.transform);
	}
	
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	
	public Bitmap getBitmap(){
		return bitmap;
	}

}
