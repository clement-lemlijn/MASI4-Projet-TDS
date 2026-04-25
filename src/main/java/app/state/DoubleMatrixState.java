package app.state;

import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import domain.events.fourier.*;
import domain.image.GrayScaleMatrix;
import domain.image.processing.complex.ComplexMatrix;

@Singleton
public class DoubleMatrixState extends State {

    private ComplexMatrix fourier;
    private GrayScaleMatrix module;
    private GrayScaleMatrix phase;
    private GrayScaleMatrix real;
    private GrayScaleMatrix imaginary;

    @Inject
    public DoubleMatrixState(EventBus eventBus) {
        super(eventBus);
    }

    public void setFourier(ComplexMatrix fourier) {
        this.fourier = fourier;
        this.eventBus.post(new ComplexMatrixChanged(fourier));
    }

    public ComplexMatrix getFourier() {
        return fourier;
    }

    public void setModule(GrayScaleMatrix matrix) {
        this.module = matrix;
        this.eventBus.post(new ModuleChangedEvent(matrix));
    }

    public void setPhase(GrayScaleMatrix matrix) {
        this.phase = matrix;
        this.eventBus.post(new PhaseChangedEvent(matrix));
    }

    public void setReal(GrayScaleMatrix matrix) {
        this.real = matrix;
        this.eventBus.post(new RealChangedEvent(matrix));
    }

    public void setImaginary(GrayScaleMatrix matrix) {
        this.imaginary = matrix;
        this.eventBus.post(new ImaginaryChangedEvent(matrix));
    }
}