package domain.image.processing.linear;

import domain.image.GrayScaleMatrix;
import domain.image.Image;
import domain.image.processing.complex.ComplexMatrix;
import domain.image.processing.complex.Complexe;
import domain.image.processing.fourier.Fourier;

import java.util.function.Function;

public class GlobalLinearFiltering {
    public static Image filterFromCenter(Image image, Function<Double, Double> distanceFilterValue) {
        ComplexMatrix fourier = Fourier.decroise(
                Fourier.Fourier2D(image.toGrayScale().getRawData())
        );

        int centreL = fourier.getLignes() / 2;
        int centreC = fourier.getColonnes() / 2;

        for (int c = 0; c < fourier.getColonnes(); c++) {
            for (int l = 0; l < fourier.getLignes(); l++) {
                double distance = Math.sqrt(Math.pow(c - centreC, 2) + Math.pow(l - centreL, 2));
                double weight = distanceFilterValue.apply(distance);

                Complexe originalValue = fourier.get(l, c);

                // Multiply by the calculated mask value
                Complexe filteredValue = new Complexe(
                        originalValue.getPartieReelle() * weight,
                        originalValue.getPartieImaginaire() * weight
                );

                fourier.set(l, c, filteredValue);
            }
        }

        fourier = Fourier.InverseFourier2D(fourier);
        return new GrayScaleMatrix(fourier.getModule()).toImage();
    }

    public static Image idealLowPassFilter(Image image, int cutoffFrequency) {
        return filterFromCenter(image, (distance) -> {
            if (distance > cutoffFrequency) {
                return 0.0;
            }
            return 1.0;
        });
    }

    public static Image idealHighPassFilter(Image image, int cutoffFrequency) {
        return filterFromCenter(image, (distance) -> {
            if (distance < cutoffFrequency) {
                return 0.0;
            }
            return 1.0;
        });
    }

    public static Image butterworthLowPassFilter(Image image, int cutoffFrequency, int order) {
        return filterFromCenter(image, (distance) -> {
            if (cutoffFrequency == 0) {
                return 1.0;
            }
            return 1.0/(1.0+Math.pow((double) distance/cutoffFrequency, 2*order));
        });
    }

    public static Image butterworthHighPassFilter(Image image, int cutoffFrequency, int order) {
        return filterFromCenter(image, (distance) -> {
            if (distance == 0) {
                return 1.0;
            }
            return 1.0/(1.0+Math.pow((double) cutoffFrequency/distance, 2*order));
        });
    }
}
