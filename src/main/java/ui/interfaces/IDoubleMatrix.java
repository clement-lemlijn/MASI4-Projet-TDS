package ui.interfaces;

import domain.image.Image;

public interface IDoubleMatrix {

    void updateImage(Image image);
    void onBlackValueChanged();
    void onWhiteValueChanged();
}
