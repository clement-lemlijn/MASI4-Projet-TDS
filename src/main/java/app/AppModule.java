package app;

import com.google.common.eventbus.EventBus;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import ui.implementation.components.nav.NavBar;
import ui.implementation.views.MainView;
import ui.interfaces.IMainView;
import ui.interfaces.INavBar;

public class AppModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(EventBus.class).in(Scopes.SINGLETON);
        bind(AppState.class).in(Scopes.SINGLETON);

        bind(INavBar.class).to(NavBar.class).in(Scopes.SINGLETON);
        bind(IMainView.class).to(MainView.class).in(Scopes.SINGLETON);
    }
}