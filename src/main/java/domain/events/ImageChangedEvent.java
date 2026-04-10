package domain.events;

import domain.CImage.CImage;

public record ImageChangedEvent(CImage image) { }
