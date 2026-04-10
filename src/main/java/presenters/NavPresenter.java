package presenters;

import app.AppState;
import com.google.inject.Inject;
import domain.CImage.CImageNG;
import domain.CImage.CImageRGB;
import domain.CImage.Exceptions.CImageNGException;
import domain.CImage.Exceptions.CImageRGBException;
import domain.common.Mode;
import ui.interfaces.INavBar;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class NavPresenter {

    private final INavBar navBar;
    private final AppState appState;

    @Inject
    public NavPresenter(INavBar navBar, AppState appState) {
        this.navBar = navBar;
        this.appState = appState;
    }

    public void createImage(Color c, int height, int width) throws CImageRGBException {
        CImageRGB cImageRGB = new CImageRGB(width, height, c);
        appState.setImage(cImageRGB);
    }

    public void loadImage(File f) throws IOException {
        CImageRGB imageRGB = new CImageRGB(f);
        appState.setImage(imageRGB);
    }

    public int[][] getImageMatrix() throws CImageNGException {
        return ((CImageNG)appState.getImage()).getMatrice();
    }

    public void setMode(Mode mode) {
        appState.setMode(mode);
    }
}
