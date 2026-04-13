package domain.image;

public class GrayScaleMatrix {

    private final double[][] matrix;

    private double maxValue;
    private double minValue;

    public GrayScaleMatrix(double[][] matrix) {
        this.matrix = matrix;
        this.maxValue = computeMax();
        this.minValue = computeMin();

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

    public int getWidth(){
        return matrix[0].length;
    }

    public int getHeight(){
        return matrix.length;
    }

    public void updateBlack(double black){
        for (int y = 0; y < getHeight(); y++)
            for (int x = 0; x < getWidth(); x++) {
                if (matrix[y][x] <= black) {
                   //matrice_int[y][x] = 0;
                    matrix[y][x] = 0;
                } else {
                    int val = (int) ((matrix[y][x] - minValue) / (maxValue - minValue) * 255 + 0.5);
                    if (val > 255) val = 255;
                    if (val < 0) val = 0;
                    matrix[y][x] = val;
                }
            }
    }

    public void updateWhite(double white){
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                if (matrix[y][x] >= white) {
                    //matrice_int[y][x] = 255;
                    matrix[y][x] = 255;
                }
            }
        }
    }

    public Image toImage(){
        Pixel[][] res = new Pixel[getHeight()][getWidth()];

        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {

                int value = (int)matrix[y][x];
                res[y][x] = new Pixel(value, value, value);
            }
        }

        return new Image(res);
    }

}
