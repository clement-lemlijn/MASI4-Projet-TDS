package domain.events;

import domain.image.GrayScaleMatrix;

public record MatrixChangedEvent(GrayScaleMatrix matrix) {
}
