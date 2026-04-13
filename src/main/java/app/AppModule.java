package app;

import app.state.AppState;
import com.google.common.eventbus.EventBus;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import presenters.DoubleMatrixPresenter;
import presenters.MainPresenter;
import presenters.NavPresenter;
import services.ImageService;
import ui.implementation.components.nav.NavBar;
import ui.implementation.views.DoubleMatrix;
import ui.implementation.views.MainView;
import ui.interfaces.IDoubleMatrix;
import ui.interfaces.IMainView;
import ui.interfaces.INavBar;

/**
 * @author Laurent Crema
 */
public class AppModule extends AbstractModule {

    @Override
    protected void configure() {

        //--- Application layer ---
        bind(EventBus.class).in(Scopes.SINGLETON);
        bind(AppState.class).in(Scopes.SINGLETON);
        bind(INavigator.class).to(SwingNavigator.class).in(Scopes.SINGLETON);

        //--- UI layer ---
        bind(INavBar.class).to(NavBar.class).in(Scopes.SINGLETON);
        bind(IMainView.class).to(MainView.class).in(Scopes.SINGLETON);
        bind(IDoubleMatrix.class).to(DoubleMatrix.class).in(Scopes.SINGLETON);

        //--- Presentation layer ---
        bind(MainPresenter.class).in(Scopes.SINGLETON);
        bind(NavPresenter.class).in(Scopes.SINGLETON);
        bind(DoubleMatrixPresenter.class).in(Scopes.SINGLETON);

        //--- Service layer layer ---
        bind(ImageService.class);

    }
}