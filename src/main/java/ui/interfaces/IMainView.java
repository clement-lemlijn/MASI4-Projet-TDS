package ui.interfaces;

import domain.common.Mode;
import domain.image.Image;
import presenters.DoubleMatrixPresenter;
import presenters.MainPresenter;

import java.awt.image.BufferedImage;

public interface IMainView {

    void changeMode(Mode m);
    void displayImage(Image image);

}
