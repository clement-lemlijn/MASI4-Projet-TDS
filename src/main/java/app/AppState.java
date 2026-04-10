package app;

import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import domain.CImage.CImage;
import domain.common.Mode;
import domain.events.ImageChangedEvent;
import domain.events.ModeChangedEvent;
import domain.image.Image;

@Singleton
public class AppState {

    private EventBus eventBus;
    private Mode mode;
    private Image image;

    @Inject
    public AppState(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
        this.eventBus.post(new ModeChangedEvent(mode));
    }

    public void setImage(Image image) {
        this.image = image;
        this.eventBus.post(new ImageChangedEvent(image));
    }

    public Image getImage(){
        return this.image;
    }
}
