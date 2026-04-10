package presenters;

import app.AppState;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import domain.common.Mode;
import domain.events.ImageChangedEvent;
import domain.events.ModeChangedEvent;
import ui.interfaces.IMainView;

public class MainPresenter {

    private final IMainView view;
    private final AppState appState;

    @Inject
    public MainPresenter(IMainView view, AppState appState, EventBus eventBus) {
        this.view = view;
        this.appState = appState;
        eventBus.register(this);
    }

    @Subscribe
    public void onImageChanged(ImageChangedEvent e){
        view.displayImage();
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
