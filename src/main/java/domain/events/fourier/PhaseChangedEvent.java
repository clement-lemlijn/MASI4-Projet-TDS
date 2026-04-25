package domain.events.fourier;

import domain.image.GrayScaleMatrix;

public record PhaseChangedEvent(GrayScaleMatrix matrix)  {
}
