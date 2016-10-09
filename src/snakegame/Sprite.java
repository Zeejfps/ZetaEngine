package snakegame;

import envasilyev.zetaengine.GameObject;
import envasilyev.zetaengine.comps.SpriteRenderer;
import envasilyev.zetaengine.gfx.Bitmap;

/**
 * Created by Zeejfps on 10/8/2016.
 */
public class Sprite extends GameObject {

    private Bitmap bitmap;

    public Sprite(Bitmap bitmap) {
        this.bitmap = bitmap;
        addComponent(new SpriteRenderer(this));
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

}
