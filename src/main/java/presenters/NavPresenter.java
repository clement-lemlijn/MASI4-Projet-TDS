package presenters;

import app.AppState;
import com.google.inject.Inject;
import domain.common.Mode;
import ui.interfaces.INavBar;

public class NavPresenter {

    private final INavBar navBar;
    private final AppState appState;

    @Inject
    public NavPresenter(INavBar navBar, AppState appState) {
        this.navBar = navBar;
        this.appState = appState;
    }

    public void setMode(Mode mode) {
        appState.setMode(mode);
    }

}
