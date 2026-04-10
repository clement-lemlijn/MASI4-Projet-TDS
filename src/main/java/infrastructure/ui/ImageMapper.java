package infrastructure.ui;

import domain.image.Pixel;

import java.awt.image.BufferedImage;

public final class ImageMapper {

    public static BufferedImage toBufferedImage(domain.image.Image image) {

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
}
