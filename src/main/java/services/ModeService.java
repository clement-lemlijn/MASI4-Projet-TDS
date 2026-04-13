package services;

import app.state.AppState;
import com.google.inject.Inject;
import domain.common.Mode;

public class ModeService {

    private final AppState appState;

    @Inject
    public ModeService(AppState appState) {
        this.appState = appState;
    }

    public void setMode(Mode m){
        appState.setMode(m);
    }

}
