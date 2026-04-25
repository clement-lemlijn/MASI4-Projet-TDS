package infrastructure.ui;

import domain.image.Image;
import domain.image.Pixel;

import java.awt.image.BufferedImage;

public final class ImageMapper {

    public static BufferedImage toBufferedImage(Image image) {

        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage out = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                Pixel p = image.getPixel(x, y);

                int rgb = (p.getRed() << 16)
                        | (p.getGreen() << 8)
                        | p.getBlue();

                out.setRGB(x, y, rgb);
            }
        }

        return out;
    }

    public static Image fromBufferedImage(BufferedImage img) {

        int width = img.getWidth();
        int height = img.getHeight();

        Pixel[][] pixels = new Pixel[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                int rgb = img.getRGB(x, y);

                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;

                pixels[y][x] = new Pixel(r, g, b);
            }
        }

        return new Image(pixels);
    }
}
