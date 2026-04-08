package app;

import com.google.common.eventbus.EventBus;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import ui.implementation.views.MainView;
import ui.interfaces.IMainView;

public class AppModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(EventBus.class).in(Scopes.SINGLETON);
        bind(AppState.class).in(Scopes.SINGLETON);

        bind(IMainView.class).to(MainView.class);
    }
}