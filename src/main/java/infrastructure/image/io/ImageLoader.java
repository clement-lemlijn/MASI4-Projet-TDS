package infrastructure.image.io;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import domain.image.Image;
import domain.image.Pixel;

public class ImageLoader {

    public static Image loadImage(File f) throws IOException {

        BufferedImage buffered = ImageIO.read(f);

        if (buffered == null) throw new IOException("Unsupported or invalid image file: " + f);

        int width = buffered.getWidth();
        int height = buffered.getHeight();

        Pixel[][] pixels = new Pixel[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                int rgb = buffered.getRGB(x, y);

                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;

                pixels[y][x] = new Pixel(r, g, b);
            }
        }

        return new Image(pixels);
    }
}