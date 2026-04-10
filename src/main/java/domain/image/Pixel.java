package domain.image;

/**
 * @author Laurent Crema
 */
public class Pixel {

    private int red, green, blue;

    public Pixel(int red, int green, int blue) {

        if(checkColorValidity(red)) throw new IllegalArgumentException("Invalid red value");
        if(checkColorValidity(green)) throw new IllegalArgumentException("Invalid green value");
        if(checkColorValidity(blue)) throw new IllegalArgumentException("Invalid blue value");

        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int value) {
        if(checkColorValidity(value)) throw new IllegalArgumentException("Invalid value");
        this.red = value;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int value) {
        if(checkColorValidity(value)) throw new IllegalArgumentException("Invalid value");
        this.green = value;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int value) {
        if(checkColorValidity(value)) throw new IllegalArgumentException("Invalid value");
        this.blue = value;
    }

    public boolean checkColorValidity(int color) {
        return color >= 0 && color <= 255;
    }

    /**
     * Convert the current RGB combination into a gray value according to UIT-R BT 709 standard.
     * The current RGB values is not modified.
     * @return the corresponding gray scale value
     */
    public int grayValue(){
        return (int) (0.2126 * red + 0.7152 * green + 0.0722 * blue);
    }

}
