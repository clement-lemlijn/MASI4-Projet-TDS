package app.state;

import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import domain.common.Mode;
import domain.events.ImageChangedEvent;
import domain.events.ModeChangedEvent;
import domain.image.Image;

@Singleton
public class AppState extends State {

    private Mode mode;
    private Image rgbImage;

    @Inject
    public AppState(EventBus eventBus) {
        super(eventBus);
    }

    public void setMode(Mode mode) {
        this.mode = mode;
        this.eventBus.post(new ModeChangedEvent(mode));
    }

    public void setImage(Image image) {
        this.rgbImage = image;
        this.eventBus.post(new ImageChangedEvent(image));
    }

    public Image getImage(){
        return this.rgbImage;
    }
}
