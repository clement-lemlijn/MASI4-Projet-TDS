package app;

import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import domain.common.Mode;
import domain.events.ModeChangedEvent;

@Singleton
public class AppState {

    private EventBus eventBus;
    private Mode mode;

    @Inject
    public AppState(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
        this.eventBus.post(new ModeChangedEvent(mode));
    }

    public Mode getMode() {
        return mode;
    }
}
