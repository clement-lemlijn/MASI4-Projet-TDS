package services;

import app.AppState;
import domain.common.Mode;

public class ModeService {

    private final AppState appState;

    public ModeService(AppState appState) {
        this.appState = appState;
    }

    public void setMode(Mode m){
        appState.setMode(m);
    }

}
