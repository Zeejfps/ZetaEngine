package envasilyev.zetaengine.utils;

import envasilyev.zetaengine.gfx.Bitmap;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Zeejfps on 10/8/2016.
 */
public final class IOUtils {

    private IOUtils() {}

    public static Bitmap loadBitmap(String path) throws IOException {
        BufferedImage img = ImageIO.read(IOUtils.class.getClassLoader().getResourceAsStream(path));
        int[] pixels = img.getRGB(0, 0, img.getWidth(), img.getHeight(), null, 0, img.getWidth());
        return new Bitmap(img.getWidth(), img.getHeight(), pixels);
    }

}
