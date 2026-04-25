package domain.events.fourier;

import domain.image.GrayScaleMatrix;

public record ImaginaryChangedEvent(GrayScaleMatrix matrix) {
}
