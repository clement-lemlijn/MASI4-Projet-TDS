package ui.interfaces;

import domain.common.Mode;
import presenters.DoubleMatrixPresenter;
import presenters.MainPresenter;

import java.awt.image.BufferedImage;

public interface IMainView {

    void changeMode(Mode m);
    void displayImage(BufferedImage image);

}
