package app.state;

import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import domain.events.MatrixChangedEvent;
import domain.events.fourier.ImaginaryChangedEvent;
import domain.events.fourier.ModuleChangedEvent;
import domain.events.fourier.PhaseChangedEvent;
import domain.events.fourier.RealChangedEvent;
import domain.image.GrayScaleMatrix;

@Singleton
public class DoubleMatrixState extends State {

    private GrayScaleMatrix module;
    private GrayScaleMatrix phase;
    private GrayScaleMatrix real;
    private GrayScaleMatrix imaginary;

    @Inject
    public DoubleMatrixState(EventBus eventBus) {
        super(eventBus);
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