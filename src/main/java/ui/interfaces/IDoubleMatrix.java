package ui.interfaces;

import domain.image.GrayScaleMatrix;

public interface IDoubleMatrix {

    void updateMatrix(GrayScaleMatrix matrix);
    void onBlackValueChanged();
    void onWhiteValueChanged();
}
