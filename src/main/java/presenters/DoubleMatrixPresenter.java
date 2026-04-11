package presenters;

import com.google.inject.Inject;
import domain.image.Image;
import services.ImageService;
import ui.interfaces.IDoubleMatrix;

import java.io.IOException;

/**
 * @author Laurent Crema
 */
public class DoubleMatrixPresenter {

    private IDoubleMatrix view;
    private ImageService imageService;
    private Image image;

    @Inject
    public DoubleMatrixPresenter(ImageService imageService) {
        this.imageService = imageService;
    }

    public void setView(IDoubleMatrix view){
        this.view = view;
    }

    public void saveImage(Image image) throws IOException {
        this.imageService.saveImage(image);
    }

    public void setWhiteLevel(int value){
        view.onWhiteValueChanged();
    }

    public void setBlackLevel(int value){
        view.onBlackValueChanged();
    }

    public void loadGrayScale(){
        this.image = imageService.loadGrayScale();
    }

}
