package domain.image;

import java.util.Arrays;

public class GrayScaleMatrix {

    private final double[][] matrix;

    private double maxValue;
    private double minValue;

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

    public void updateBlack(double value) {
        this.minValue = value;
        recompute();
    }

    public void updateWhite(double value) {
        this.maxValue = value;
        recompute();
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

    private void recompute() {

        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {

                double value = matrix[y][x];
                double result;

                if (value <= minValue) {
                    result = 0;
                } else if (value >= maxValue) {
                    result = 255;
                } else {
                    result = (value - minValue) / (maxValue - minValue) * 255;
                }

                result = Math.max(0, Math.min(255, result));
                matrix[y][x] = result;

                if (result < minValue) minValue = result;
                if (result > maxValue) maxValue = result;
            }
        }
    }

    public Image toImage() {
        Pixel[][] res = new Pixel[getHeight()][getWidth()];
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                double value = matrix[y][x];
                int normalized;
                if (value <= minValue) normalized = 0;
                else if (value >= maxValue) normalized = 255;
                else normalized = (int) ((value - minValue) / (maxValue - minValue) * 255);
                res[y][x] = new Pixel(normalized, normalized, normalized);
            }
        }
        return new Image(res);
    }

}
