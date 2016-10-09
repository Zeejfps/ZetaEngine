package snakegame;

import envasilyev.zetaengine.Input;
import envasilyev.zetaengine.Scene;
import envasilyev.zetaengine.gfx.Bitmap;
import envasilyev.zetaengine.utils.IOUtils;

import java.awt.event.KeyEvent;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by Zeejfps on 10/8/2016.
 */
public class Snake {

    private static Bitmap headBitmap, bodyBitmap;
    static {
        try {
            headBitmap = IOUtils.loadBitmap("Snake/head.png");
            bodyBitmap = IOUtils.loadBitmap("Snake/body.png");
        } catch (Exception e) {
            System.err.println("Failed to load snake bitmaps.");
            System.exit(1);
        }
    }

    private Sprite head;
    private Queue<Sprite> body;

    public Snake(Scene scene) {
        head = new Sprite(headBitmap);
        body = new ArrayDeque<>();

        scene.addGameObject(head);
    }

    public void update() {
        if (Input.getKeyDown(KeyEvent.VK_A)) {
            head.transform.setRotation(90);
        }
        else
        if (Input.getKeyDown(KeyEvent.VK_D)) {
            head.transform.setRotation(-90);
        }
    }

    public void render() {
        //Screen.getGraphics().drawBitmap((int)head.transform.position.x, (int)head.transform.position.y, headBitmap);
    }
}
