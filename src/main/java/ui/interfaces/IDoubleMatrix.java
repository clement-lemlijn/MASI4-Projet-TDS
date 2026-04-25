package ui.interfaces;

import domain.image.GrayScaleMatrix;

public interface IDoubleMatrix {

    void updateModule(GrayScaleMatrix matrix);
    void updatePhase(GrayScaleMatrix matrix);
    void updateReal(GrayScaleMatrix matrix);
    void updateImaginary(GrayScaleMatrix matrix);
}
