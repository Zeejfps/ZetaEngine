package snakegame;

import envasilyev.zetaengine.*;
import envasilyev.zetaengine.gfx.Bitmap;
import envasilyev.zetaengine.gfx.Graphics;
import envasilyev.zetaengine.utils.IOUtils;

/**
 * Created by Zeejfps on 10/8/2016.
 */
public class MainScene extends Scene {

    private int mapWidth = 20;
    private int mapHeight = 20;

    private Snake snake;
    private Bitmap wallBitmap;

    public MainScene() {
        snake = new Snake(this);
        wallBitmap = new Bitmap(10, 10, 0x0000ff);
        Screen.setSize(mapWidth*10, mapHeight*10);
    }

    @Override
    protected void onStart() {

    }

    @Override
    protected void onUpdate() {
        snake.update();
    }

    @Override
    protected void onFixedUpdate() {

    }

    @Override
    protected void onRender() {
        Screen.clear(0);
        drawBoundry();
        snake.render();
    }

    private void drawBoundry() {
        for (int i = 0; i < mapHeight; i++) {
            for (int j = 0; j < mapWidth; j++) {
                if (i == 0 || i == mapHeight-1 || j == 0 || j == mapWidth-1) {
                    Screen.getGraphics().drawBitmap(
                            j * wallBitmap.getWidth(),
                            i * wallBitmap.getHeight(),
                            wallBitmap
                    );
                }
            }
        }
    }

    public static void main(String[] args) {
        Engine.init();
        Engine.showDebug(true);
        Window.setTitle("Game");
        Window.setSize(640, 480);
        Window.setResizable(true);
        Window.setVisible(true);
        Engine.loadScene(new MainScene());
    }

}
