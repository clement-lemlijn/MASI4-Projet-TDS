package presenters;

import app.state.DoubleMatrixState;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import domain.events.MatrixChangedEvent;
import domain.image.GrayScaleMatrix;
import domain.image.Image;
import domain.image.processing.complex.MatriceComplexe;
import domain.image.processing.fourier.Fourier;
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
        System.out.println(value);
        GrayScaleMatrix matrix = state.getMatrix();
        matrix.updateWhite(value);
        state.setMatrix(matrix);
    }

    @Subscribe
    public void onMatrixUpdate(MatrixChangedEvent event) {
        view.updateMatrix(event.matrix());
    }

    public void loadGrayScale(){
        state.setMatrix(imageService.loadGrayScale());
        //imageService.loadGrayScale();
    }

    public void loadModule(){
        GrayScaleMatrix matrix = imageService.loadGrayScale();
        MatriceComplexe fourier = Fourier.Fourier2D(matrix.getRawData());
        fourier = Fourier.decroise(fourier);
        double[][] module = fourier.getModule();
        state.setMatrix(new GrayScaleMatrix(module));
    }

    public void loadPhase(){
        GrayScaleMatrix matrix = imageService.loadGrayScale();
        MatriceComplexe fourier = Fourier.Fourier2D(matrix.getRawData());
        fourier = Fourier.decroise(fourier);
        double[][] module = fourier.getModule();
        state.setMatrix(new GrayScaleMatrix(module));
    }

    public void loadReal(){
        GrayScaleMatrix matrix = imageService.loadGrayScale();
        MatriceComplexe fourier = Fourier.Fourier2D(matrix.getRawData());
        fourier = Fourier.decroise(fourier);
        double[][] module = fourier.getPartieReelle();
        state.setMatrix(new GrayScaleMatrix(module));
    }

    public void loadImaginary(){
        GrayScaleMatrix matrix = imageService.loadGrayScale();
        MatriceComplexe fourier = Fourier.Fourier2D(matrix.getRawData());
        fourier = Fourier.decroise(fourier);
        double[][] module = fourier.getPartieImaginaire();
        state.setMatrix(new GrayScaleMatrix(module));
    }
}
