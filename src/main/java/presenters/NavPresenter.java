package presenters;

import com.google.inject.Inject;
import domain.CImage.Exceptions.CImageNGException;
import domain.common.Mode;
import services.ImageService;
import services.ModeService;
import ui.interfaces.INavBar;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author Laurent Crema
 */
public class NavPresenter {

    private final INavBar navBar;
    private final ImageService imageService;
    private final ModeService modeService;

    @Inject
    public NavPresenter(INavBar navBar, ImageService imageService, ModeService modeService) {
        this.navBar = navBar;
        this.imageService = imageService;
        this.modeService = modeService;
    }

    public void createImage(int red, int green, int blue, int height, int width) {
        imageService.createImage(red, green, blue, height, width);
    }

    public void loadImage(File f) throws IOException {
        imageService.loadImage(f);
    }

    public int[][] getImageMatrix() {
        //return ((CImageNG)appState.getImage()).getMatrice();
        return null;
    }

    public void setMode(Mode mode) {
        modeService.setMode(mode);
    }
}
