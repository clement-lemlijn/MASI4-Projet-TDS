package app;

import com.google.common.eventbus.EventBus;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import services.ImageService;
import ui.implementation.components.nav.NavBar;
import ui.implementation.views.MainView;
import ui.interfaces.IMainView;
import ui.interfaces.INavBar;

public class AppModule extends AbstractModule {
    @Override
    protected void configure() {

        //--- Application layer ---
        bind(EventBus.class).in(Scopes.SINGLETON);
        bind(AppState.class).in(Scopes.SINGLETON);

        //--- UI layer ---
        bind(INavBar.class).to(NavBar.class).in(Scopes.SINGLETON);
        bind(IMainView.class).to(MainView.class).in(Scopes.SINGLETON);


        //--- Service layer layer ---
        bind(ImageService.class);

    }
}