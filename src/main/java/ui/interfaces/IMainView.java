package ui.interfaces;

import domain.common.Mode;

import java.awt.image.BufferedImage;

public interface IMainView {

    void changeMode(Mode m);
    void displayImage(BufferedImage image);

}
