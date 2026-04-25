package presenters;

import app.state.DoubleMatrixState;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import domain.events.fourier.ImaginaryChangedEvent;
import domain.events.fourier.ModuleChangedEvent;
import domain.events.fourier.PhaseChangedEvent;
import domain.events.fourier.RealChangedEvent;
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

//    public double getBlackLevel(){
//        return imageService.getGrayScale().getMinValue();
//    }
//
//    public double getWhiteLevel(){
//        return imageService.getGrayScale().getMaxValue();
//    }

    public void setModuleLevel(double black, double white){
        GrayScaleMatrix matrix = updateLevels(imageService.getGrayScale(), black, white);
        state.setModule(matrix);
    }

    public void setPhaseLevel(double black, double white){
        GrayScaleMatrix matrix = updateLevels(imageService.getGrayScale(), black, white);
        state.setPhase(matrix);
    }

    public void setRealLevel(double black, double white){
        GrayScaleMatrix matrix = updateLevels(imageService.getGrayScale(), black, white);
        state.setReal(matrix);
    }

    public void setImaginary(double black, double white){
        GrayScaleMatrix matrix = updateLevels(imageService.getGrayScale(), black, white);
        state.setImaginary(matrix);
    }

    @Subscribe
    public void onModuleUpdate(ModuleChangedEvent event) {
        view.updateModule(event.matrix());
    }

    @Subscribe
    public void onPhaseUpdate(PhaseChangedEvent event) {
        view.updatePhase(event.matrix());
    }

    @Subscribe
    public void onRealUpdate(RealChangedEvent event) {
        view.updateReal(event.matrix());
    }

    @Subscribe
    public void onImaginaryUpdate(ImaginaryChangedEvent event) {
        view.updateImaginary(event.matrix());
    }

    public void loadModule(){
        GrayScaleMatrix matrix = imageService.getGrayScale();
        MatriceComplexe fourier = Fourier.Fourier2D(matrix.getRawData());
        fourier = Fourier.decroise(fourier);
        double[][] module = fourier.getModule();
        state.setModule(new GrayScaleMatrix(module));
    }

    public void loadPhase(){
        GrayScaleMatrix matrix = imageService.getGrayScale();
        MatriceComplexe fourier = Fourier.Fourier2D(matrix.getRawData());
        fourier = Fourier.decroise(fourier);
        double[][] module = fourier.getModule();
        state.setPhase(new GrayScaleMatrix(module));
    }

    public void loadReal(){
        GrayScaleMatrix matrix = imageService.getGrayScale();
        MatriceComplexe fourier = Fourier.Fourier2D(matrix.getRawData());
        fourier = Fourier.decroise(fourier);
        double[][] module = fourier.getPartieReelle();
        state.setReal(new GrayScaleMatrix(module));
    }

    public void loadImaginary(){
        GrayScaleMatrix matrix = imageService.getGrayScale();
        MatriceComplexe fourier = Fourier.Fourier2D(matrix.getRawData());
        fourier = Fourier.decroise(fourier);
        double[][] module = fourier.getPartieImaginaire();
        state.setImaginary(new GrayScaleMatrix(module));
    }

    private GrayScaleMatrix updateLevels(GrayScaleMatrix matrix, double black, double white){
        matrix.updateBlack(black);
        matrix.updateWhite(white);
        return matrix;
    }
}
