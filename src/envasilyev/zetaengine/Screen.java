package envasilyev.zetaengine;

import envasilyev.zetaengine.gfx.Bitmap;
import envasilyev.zetaengine.gfx.Graphics;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 * Created by Zeejfps on 10/7/2016.
 */
public final class Screen {

    private static BufferedImage colorBuffer;
    private static float aspect;
    private static Bitmap bitmap;
    private static Graphics graphics;

    private Screen() { }

    static void init() {
        setSize(320, 240);
    }

    public static void setSize(int width, int height) {
        aspect = width / (float)height;
        colorBuffer =  new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        bitmap = new Bitmap(
                width,
                height,
                ((DataBufferInt) colorBuffer.getRaster().getDataBuffer()).getData()
        );
        graphics = new Graphics(bitmap);
    }

    public static float getAspectRatio() {
        return aspect;
    }

    public static void clear(int color) {
        bitmap.fill(color);
    }

    public static Graphics getGraphics() {
        return graphics;
    }

    public static int[] pixels() {
        return bitmap.pixels();
    }

    public int getWidth() {
        return bitmap.getWidth();
    }

    public int getHeight() {
        return bitmap.getHeight();
    }

    static BufferedImage getColorBuffer() {
        return colorBuffer;
    }

}
