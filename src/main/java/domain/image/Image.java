package domain.image;

/**
 * @author Laurent Crema
 */
public class Image {

    private final Pixel[][] pixels;
    private GrayScaleMatrix grayScaleEquivalent;

    public Image(Pixel[][] pixels) {
        if(pixels == null || pixels.length == 0 || pixels[0].length == 0)
            throw new IllegalArgumentException("Invalid dimensions");
        this.pixels = pixels;
    }

    public Image(int red, int green, int blue, int height, int width) {
        pixels =  new Pixel[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixels[y][x] = new Pixel(red, green, blue);
            }
        }
    }

    public int getWidth(){
        return pixels[0].length;
    }

    public int getHeight(){
        return pixels.length;
    }

    public Pixel getPixel(int x, int y){
        if(x < 0 || y < 0 || x >= getWidth() || y >= getHeight())
            throw new IllegalArgumentException("Invalid coordinates");
        return pixels[y][x];
    }

    public void setPixelColor(int x, int y, int red, int green, int blue){
        if(x < 0 || y < 0 || x >= getWidth() || y >= getHeight())
            throw new IllegalArgumentException("Invalid coordinates");
        Pixel p = new Pixel(red, green, blue);
        pixels[y][x] = p;
        grayScaleEquivalent = toGrayScale();
    }

    public void setRectangleColor(int x1, int x2, int y1, int y2,
                                  int red, int green, int blue) {

        int minX = Math.max(0, Math.min(x1, x2));
        int maxX = Math.min(pixels[0].length - 1, Math.max(x1, x2));

        int minY = Math.max(0, Math.min(y1, y2));
        int maxY = Math.min(pixels.length - 1, Math.max(y1, y2));

        Pixel p = new Pixel(red, green, blue);

        for (int y = minY; y <= maxY; y++) {
            for (int x = minX; x <= maxX; x++) {
                pixels[y][x] = p;
            }
        }

        grayScaleEquivalent = toGrayScale();
    }

    public void setLineColor(int x1, int x2, int y1, int y2,
                             int red, int green, int blue) {

        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);

        int sx = x1 < x2 ? 1 : -1;
        int sy = y1 < y2 ? 1 : -1;

        int err = dx - dy;

        Pixel p = new Pixel(red, green, blue);

        while (true) {
            if (x1 >= 0 && y1 >= 0 && x1 < getWidth() && y1 < getHeight()) {
                pixels[y1][x1] = p;
            }

            if (x1 == x2 && y1 == y2) break;

            int e2 = 2 * err;

            if (e2 > -dy) {
                err -= dy;
                x1 += sx;
            }

            if (e2 < dx) {
                err += dx;
                y1 += sy;
            }
        }

        grayScaleEquivalent = toGrayScale();
    }

    /**
     * Return the corresponding gray scale matrix. Since this operation is destructive, the original image is not modified.
     * Instead, a new one is returned.
     * @return A newly converted image
     */
    public GrayScaleMatrix toGrayScale() {

        if(grayScaleEquivalent != null) return this.grayScaleEquivalent;

        double[][] res = new double[getHeight()][getWidth()];

        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {

                Pixel p = pixels[y][x];
                res[y][x] = p.grayValue();
            }
        }

        this.grayScaleEquivalent = new GrayScaleMatrix(res);
        return this.grayScaleEquivalent;
    }
}
