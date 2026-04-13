package presenters;

import app.state.DoubleMatrixState;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import domain.events.MatrixChangedEvent;
import domain.image.Image;
import services.ImageService;
import ui.interfaces.IDoubleMatrix;

import java.io.IOException;

/**
 * @author Laurent Crema
 */
public class DoubleMatrixPresenter {

    private IDoubleMatrix view;
    private DoubleMatrixState state;
    private ImageService imageService;

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

    public void setWhiteLevel(int value){
        view.onWhiteValueChanged();
    }

    public void setBlackLevel(int value){
        view.onBlackValueChanged();
    }

    @Subscribe
    public void onMatrixUpdate(MatrixChangedEvent event) {
        view.updateMatrix(event.matrix());
    }

    public void loadGrayScale(){
        //imageService.loadGrayScale();
    }

}
