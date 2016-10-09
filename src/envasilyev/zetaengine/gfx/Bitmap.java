package envasilyev.zetaengine.gfx;

import java.util.Arrays;

/**
 * Created by Zeejfps on 7/30/2016.
 */
public class Bitmap {

    protected int width;
    protected int height;
    protected int[] pixels;

    public Bitmap() {
        this(0, 0);
    }

    public Bitmap(Bitmap copy){
        this(copy.width, copy.height, Arrays.copyOf(copy.pixels, copy.pixels.length));
    }

    public Bitmap(int width, int height) {
        this(width, height, 0xff000000);
    }

    public Bitmap(int width, int height, int color) {
        this(width, height, new int[width*height]);
        Arrays.fill(pixels, color);
    }

    public Bitmap(int width, int height, int[] pixels) {
        this.width = width;
        this.height = height;
        this.pixels = pixels;
    }

    public Bitmap subBitmap(int x, int y, int width, int height) {

        int xStart = x;
        int yStart = y;
        int xEnd = x + width;
        int yEnd = y + height;
        int[] pixels = new int[width * height];

        for (int i = yStart, ii = 0; i < yEnd; i++, ii++) {

            for (int j = xStart, jj = 0; j < xEnd; j++, jj++) {

                pixels[ii*width + jj] = getPixel(j, i);

            }

        }

        return new Bitmap(width, height, pixels);
    }

    public void fill(int color) {
        Arrays.fill(pixels, color);
    }

    public void setPixel(int x, int y, int color) {
        if (x > -1 && y > -1 && x < width && y < height) {
            pixels[y*width+x] = color;
        }
    }

    public int getPixel(int x, int y) {
        if (x > -1 && y > -1 && x < width && y < height) {
            return pixels[y*width+x];
        }
        return 0;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[] pixels() {
        return pixels;
    }

    @Override
    public String toString() {
        return width + " x " + height + " Bitmap";
    }
}
