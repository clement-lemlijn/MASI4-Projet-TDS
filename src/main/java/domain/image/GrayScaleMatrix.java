package domain.image;

import infrastructure.image.io.ImageSaver;

import java.util.Arrays;

/**
 * @author Jean-Marc Wagner, Laurent Crema
 */
public class GrayScaleMatrix {

    private final double[][] matrix;

    private final double maxValue;
    private final double minValue;

    public GrayScaleMatrix(double[][] matrix) {
        this.matrix = matrix;
        this.maxValue = computeMax();
        this.minValue = computeMin();
    }

    /**
     * Returns a copy of the actual content of this matrix.
     * Since a copy is returned, no change that will be applied on it is going to affect the original matrix
     * @return a copy of the actual content
     */
    public double[][] getRawData() {
        return Arrays.copyOf(matrix, matrix.length);
    }

    public int getWidth(){
        return matrix[0].length;
    }

    public int getHeight(){
        return matrix.length;
    }

    public double getMaxValue() {
        return maxValue;
    }

    public double getMinValue() {
        return minValue;
    }

    public double getValueAt(double normalized) {
        if(normalized <= 0) { return minValue; }
        if(normalized >= 1) { return maxValue; }
        return minValue + (maxValue - minValue) * normalized;
    }

    private double computeMax(){
        double res = matrix[0][0];
        for(int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                double value = matrix[y][x];
                if (value > res) res = matrix[y][x];
            }
        }
        return res;
    }

    private double computeMin(){
        double res = matrix[0][0];
        for(int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                double value = matrix[y][x];
                if (value < res) res = matrix[y][x];
            }
        }
        return res;
    }

    public GrayScaleMatrix clip() {
        return clip(minValue, maxValue);
    }

    public GrayScaleMatrix clip(double lowerBound, double upperBound) {

        int height = getHeight();
        int width = getWidth();

        double[][] res = new double[height][width];

        double range = upperBound - lowerBound;
        if (range == 0) range = 1;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                double v = matrix[y][x];
                double out;

                if (v >= upperBound) {
                    out = 255;
                } else if (v <= lowerBound) {
                    out = 0;
                } else {
                    out = (v - lowerBound) / range * 255.0;
                }

                res[y][x] = Math.clamp(out, 0, 255);
            }
        }

        return new GrayScaleMatrix(res);
    }

    public Image toImage() {

        Pixel[][] res = new Pixel[getHeight()][getWidth()];

        double range = maxValue - minValue;
        if (range == 0) range = 1;

        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {

                double normalized = (matrix[y][x] - minValue) / range;
                int gray = (int) (normalized * 255);

                res[y][x] = new Pixel(gray, gray, gray);
            }
        }

        return new Image(res);
    }
}
