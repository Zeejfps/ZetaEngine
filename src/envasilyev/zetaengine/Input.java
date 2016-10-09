package envasilyev.zetaengine;

import java.awt.*;
import java.awt.event.*;

/**
 * Created by Zeejfps on 6/15/2016.
 */
public final class Input {

    private static final int MAX_KEYS = 600;

    private static KeyListener keyListener;
    private static MouseListener mouseListener;
    private static MouseMotionListener mouseMotionListener;
    private static MouseWheelListener mouseWheelListener;

    private static boolean[] keys = new boolean[MAX_KEYS];
    private static boolean[] keysDown = new boolean[MAX_KEYS];
    private static boolean[] keysUp = new boolean[MAX_KEYS];

    //public final Vec2i mousePosition;

    static void init() {
        Component component = Window.canvas;
        keyListener = new InputKeyListener();
        mouseListener = new InputMouseListener();
        mouseMotionListener = new InputMouseMotionListener();
        mouseWheelListener = new InputMouseWheelListener();
        //mousePosition = new Vec2i();

        component.addKeyListener(keyListener);
        component.addMouseListener(mouseListener);
        component.addMouseMotionListener(mouseMotionListener);
        component.addMouseWheelListener(mouseWheelListener);
    }

    public static boolean getKey(int keycode) {
        return keys[keycode];
    }

    public static boolean getKeyDown(int keycode) {
        boolean keyDown = keysDown[keycode];
        keysDown[keycode] = false;
        return keyDown;
    }

    public static boolean getKeyUp(int keycode) {
        boolean keyUp = keysUp[keycode];
        keysUp[keycode] = false;
        return keyUp;
    }

    private static class InputKeyListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyPressed(KeyEvent e) {
            if (keys[e.getKeyCode()] && !keysDown[e.getKeyCode()]) {
                keys[e.getKeyCode()] = true;
            }
            else {
                keys[e.getKeyCode()] = true;
                keysDown[e.getKeyCode()] = true;
            }
            keysUp[e.getKeyCode()] = false;
        }

        @Override
        public void keyReleased(KeyEvent e) {
            keys[e.getKeyCode()] = false;
            keysDown[e.getKeyCode()] = false;
            keysUp[e.getKeyCode()] = true;
        }
    }

    private static class InputMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    private static class InputMouseMotionListener implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent e) {
            //float renderScale = window.getSettings().getRenderScale();
            //mousePosition.x = (int)(e.getX() * renderScale + 0.5f);
            //mousePosition.y = (int)(e.getY() * renderScale + 0.5f);
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            //float renderScale = window.getSettings().getRenderScale();
            //mousePosition.x = (int)(e.getX()* renderScale + 0.5f);
            //mousePosition.y = (int)(e.getY()* renderScale + 0.5f);
        }
    }

    private static class InputMouseWheelListener implements  MouseWheelListener  {

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {

        }
    }
}
