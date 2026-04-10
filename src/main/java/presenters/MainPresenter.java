package presenters;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import domain.events.ImageChangedEvent;
import domain.events.ModeChangedEvent;
import services.ImageService;
import ui.interfaces.IMainView;

/**
 * @author Laurent Crema
 */
public class MainPresenter {

    private final IMainView view;
    private final ImageService service;

    @Inject
    public MainPresenter(IMainView view, ImageService service, EventBus eventBus) {
        this.view = view;
        this.service = service;
        eventBus.register(this);
    }

    @Subscribe
    public void onImageChanged(ImageChangedEvent e){
        view.displayImage(null);
    }

    @Subscribe
    public void onModeChanged(ModeChangedEvent e){
        view.changeMode(e.mode());
    }

    public boolean isPixelModeActive(){
        return false;
    }

    public boolean isLineModeActive(){
        return false;
    }

    public boolean isRectangleModeActive(){
        return false;
    }

    public boolean isPlainRectangleActive(){
        return false;
    }

    public boolean isCircleModeActive(){
        return false;
    }

    public boolean isPlainCircleModeActive(){
        return false;
    }

}
