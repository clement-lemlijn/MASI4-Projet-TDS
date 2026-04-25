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
import domain.image.processing.complex.ComplexMatrix;
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

    public void loadModule(){
        double[][] module = getFourier().getModule();
        state.setModule(new GrayScaleMatrix(module));
    }

    public void loadPhase(){
        double[][] phase = getFourier().getPhase();
        state.setPhase(new GrayScaleMatrix(phase));
    }

    public void loadReal(){
        double[][] real = getFourier().getPartieReelle();
        state.setReal(new GrayScaleMatrix(real));
    }

    public void loadImaginary(){
        double[][] imaginary = getFourier().getPartieImaginaire();
        state.setImaginary(new GrayScaleMatrix(imaginary));
    }

    public double getValueAt(GrayScaleMatrix matrix, double normalized){
        return matrix.getValueAt(normalized);
    }

    public void clipModule(double black, double white){
        GrayScaleMatrix module = new GrayScaleMatrix(getFourier().getModule());
        GrayScaleMatrix matrix = clip(module, black, white);
        state.setModule(matrix);
    }

    public void clipPhase(double black, double white){
        GrayScaleMatrix phase = new GrayScaleMatrix(getFourier().getPhase());
        GrayScaleMatrix matrix = clip(phase, black, white);
        state.setPhase(matrix);
    }

    public void clipReal(double black, double white){
        GrayScaleMatrix real = new GrayScaleMatrix(getFourier().getPartieReelle());
        GrayScaleMatrix matrix = clip(real, black, white);
        state.setReal(matrix);
    }

    public void clipImaginary(double black, double white){
        GrayScaleMatrix imaginary = new GrayScaleMatrix(getFourier().getPartieImaginaire());
        GrayScaleMatrix matrix = clip(imaginary, black, white);
        state.setImaginary(matrix);
    }

    @Subscribe
    public void onModuleUpdate(ModuleChangedEvent event) {
        GrayScaleMatrix module = new GrayScaleMatrix(getFourier().getModule());
        view.updateModule(module, event.matrix());
    }

    @Subscribe
    public void onPhaseUpdate(PhaseChangedEvent event) {
        GrayScaleMatrix phase = new GrayScaleMatrix(getFourier().getPhase());
        view.updatePhase(phase, event.matrix());
    }

    @Subscribe
    public void onRealUpdate(RealChangedEvent event) {
        GrayScaleMatrix real = new GrayScaleMatrix(getFourier().getPartieReelle());
        view.updateReal(real, event.matrix());
    }

    @Subscribe
    public void onImaginaryUpdate(ImaginaryChangedEvent event) {
        GrayScaleMatrix imaginary = new GrayScaleMatrix(getFourier().getPartieImaginaire());
        view.updateImaginary(imaginary, event.matrix());
    }

    private GrayScaleMatrix clip(GrayScaleMatrix matrix, double black, double white){
        double lowerBound = matrix.getValueAt(black);
        double upperBound = matrix.getValueAt(white);
        return matrix.clip(lowerBound, upperBound);
    }

    private ComplexMatrix getFourier() {
        if (state.getFourier() == null) {
            state.setFourier(imageService.computeFourier());
        }
        return state.getFourier();
    }
}
