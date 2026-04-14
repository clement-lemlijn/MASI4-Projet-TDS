package presenters;

import app.state.DoubleMatrixState;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import domain.events.MatrixChangedEvent;
import domain.image.GrayScaleMatrix;
import domain.image.Image;
import services.ImageService;
import ui.interfaces.IDoubleMatrix;

import java.io.IOException;

/**
 * @author Laurent Crema
 */
public class DoubleMatrixPresenter {

    private IDoubleMatrix view;
    private final DoubleMatrixState state;
    private final ImageService imageService;

    @Inject
    public DoubleMatrixPresenter(ImageService imageService, DoubleMatrixState state) {
        this.imageService = imageService;
        this.state = state;
        this.state.register(this);
    }

    public void setView(IDoubleMatrix view){
        this.view = view;
    }

    public void saveImage(Image image) throws IOException {
        this.imageService.saveImage(image);
    }

    public double getBlackLevel(){
        return state.getMatrix().getMinValue();
    }

    public double getWhiteLevel(){
        return state.getMatrix().getMaxValue();
    }

    public void setWhiteLevel(int value){
        GrayScaleMatrix matrix = state.getMatrix();
        matrix.updateBlack(value);
        state.setMatrix(matrix);
    }

    public void setBlackLevel(int value){
        GrayScaleMatrix matrix = state.getMatrix();
        matrix.updateBlack(value);
        state.setMatrix(matrix);
    }

    @Subscribe
    public void onMatrixUpdate(MatrixChangedEvent event) {
        view.updateMatrix(event.matrix());
        view.onBlackValueChanged();
        view.onWhiteValueChanged();
    }

    public void loadGrayScale(){
        state.setMatrix(imageService.loadGrayScale());
        //imageService.loadGrayScale();
    }

}
