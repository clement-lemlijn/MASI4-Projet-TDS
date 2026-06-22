package presenters;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import domain.common.Mode;
import domain.events.ImageChangedEvent;
import domain.events.ModeChangedEvent;
import domain.image.Image;
import services.ImageService;
import services.ModeService;
import ui.interfaces.IMainView;

/**
 * @author Laurent Crema
 */
public class MainPresenter {

    private IMainView view;
    private final ModeService modeService;
    private final ImageService imageService;

    @Inject
    public MainPresenter(ModeService modeService, ImageService imageService, EventBus eventBus) {
        this.modeService = modeService;
        this.imageService = imageService;
        eventBus.register(this);
    }

    public void setView(IMainView view) {
        this.view = view;
    }

    @Subscribe
    public void onImageChanged(ImageChangedEvent e){
        view.displayImage(e.image());
    }

    @Subscribe
    public void onModeChanged(ModeChangedEvent e){
        view.changeMode(e.mode());
    }

    public void drawPixel(int x, int y, int red, int green, int blue){
        if(modeService.getMode() != Mode.DRAW_PIXEL) return;
        Image image = imageService.getImage();
        image.setPixelColor(x, y, red, green, blue);
        imageService.setImage(image);
    }

    public void drawShape(int x1, int x2, int y1, int y2, int red, int green, int blue){
        Image image = imageService.getImage();
        switch (modeService.getMode()) {
            case DRAW_RECTANGLE:
                image.setRectangleColor(x1, x2, y1, y2, red, green, blue);
                break;
            case DRAW_LINE:
                image.setLineColor(x1, x2, y1, y2, red, green, blue);
                break;
            case DRAW_CIRCLE:
                break;
        }
        imageService.setImage(image);
    }


}
