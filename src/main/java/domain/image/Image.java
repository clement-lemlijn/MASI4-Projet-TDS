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
        return pixels[x][y];
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
