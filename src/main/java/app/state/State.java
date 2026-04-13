package app.state;

import com.google.common.eventbus.EventBus;

public abstract class State {

    protected EventBus eventBus;

    public State(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void register(Object obj) {
        eventBus.register(obj);
    }
}
