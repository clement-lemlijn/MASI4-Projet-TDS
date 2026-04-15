package domain.image.processing.histogram;

import domain.image.GrayScaleMatrix;
import domain.image.Image;

public class Histogramme {
    public static int[] Histogramme256(GrayScaleMatrix mat) {
        int M = mat.getHeight();
        int N = mat.getWidth();
        int histo[] = new int[256];

        for (int i = 0; i < 256; i++) histo[i] = 0;

        Image grayScaleImage = mat.toImage();

        int pixelGrayValue = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                pixelGrayValue = grayScaleImage.getPixel(i, j).grayValue();
                if ((pixelGrayValue >= 0) && (pixelGrayValue <= 255)) {
                    histo[pixelGrayValue]++;
                }
            }
        }
        return histo;
    }
}
