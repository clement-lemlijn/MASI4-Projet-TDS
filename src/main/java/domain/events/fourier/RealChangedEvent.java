package domain.events.fourier;

import domain.image.GrayScaleMatrix;

public record RealChangedEvent(GrayScaleMatrix matrix)  {
}
