package presenters;

import app.AppState;
import com.google.inject.Inject;
import ui.interfaces.IMainView;

public class MainPresenter {

    private final IMainView view;
    private final AppState appState;

    @Inject
    public MainPresenter(IMainView view, AppState appState) {
        this.view = view;
        this.appState = appState;
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
