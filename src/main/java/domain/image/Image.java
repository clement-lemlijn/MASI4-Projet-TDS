package domain.image;

/**
 * @author Laurent Crema
 */
public class Image {

    private Pixel[][] pixels;

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
     * Convert the image into a gray scale one. Since this operation is destructive, the original image is not modified.
     * Instead, a new one is returned.
     * @return A newly converted image
     */
    public Image toGreyScale() {

        Pixel[][] res = new Pixel[getHeight()][getWidth()];

        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {

                Pixel p = pixels[y][x];
                int grayValue = p.grayValue();

                res[y][x] = new Pixel(grayValue, grayValue, grayValue);
            }
        }

        return new Image(res);
    }
}
