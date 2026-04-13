package app.state;

import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import domain.events.MatrixChangedEvent;
import domain.image.GrayScaleMatrix;

@Singleton
public class DoubleMatrixState extends State {

    private GrayScaleMatrix matrix;

    @Inject
    public DoubleMatrixState(EventBus eventBus) {
        super(eventBus);
    }

    public void setMatrix(GrayScaleMatrix matrix) {
        this.matrix = matrix;
        this.eventBus.post(new MatrixChangedEvent(matrix));
    }
}