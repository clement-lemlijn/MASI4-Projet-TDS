package domain.events;

import domain.image.Image;

public record ImageChangedEvent(Image image) { }
