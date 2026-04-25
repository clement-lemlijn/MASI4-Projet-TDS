package ui.interfaces;

import domain.image.GrayScaleMatrix;

public interface IDoubleMatrix {

    void updateModule(GrayScaleMatrix source, GrayScaleMatrix clipped);
    void updatePhase(GrayScaleMatrix source, GrayScaleMatrix clipped);
    void updateReal(GrayScaleMatrix source, GrayScaleMatrix clipped);
    void updateImaginary(GrayScaleMatrix source, GrayScaleMatrix clipped);
}
