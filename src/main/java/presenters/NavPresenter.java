package presenters;

import app.INavigator;
import com.google.inject.Inject;
import domain.common.Mode;
import domain.image.GrayScaleMatrix;
import services.ImageService;
import services.ModeService;
import ui.interfaces.INavBar;

import java.io.File;
import java.io.IOException;

/**
 * @author Laurent Crema
 */
public class NavPresenter {

    private INavBar navBar;
    private INavigator navigator;
    private final ImageService imageService;
    private final ModeService modeService;

    @Inject
    public NavPresenter(INavigator navigator, ImageService imageService, ModeService modeService) {
        this.navigator = navigator;
        this.imageService = imageService;
        this.modeService = modeService;
    }

    public void setView(INavBar navBar) {
        this.navBar = navBar;
    }

    public void createImage(int red, int green, int blue, int height, int width) {
        imageService.createImage(red, green, blue, height, width);
    }

    public void loadImage(File f) throws IOException {
        imageService.loadImage(f);
    }

    public void goToFourier(){
        navigator.showFourier();
    }

    public GrayScaleMatrix getImageGrayMatrix() {
        //return ((CImageNG)appState.getImage()).getMatrice();
        return imageService.getImage().toGrayScale();
    }

    public void setMode(Mode mode) {
        modeService.setMode(mode);
    }
}
