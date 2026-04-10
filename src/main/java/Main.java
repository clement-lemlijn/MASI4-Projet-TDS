import app.AppModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import presenters.MainPresenter;
import presenters.NavPresenter;
import ui.implementation.components.nav.NavBar;
import ui.implementation.views.MainView;
import ui.interfaces.IMainView;

import javax.swing.*;

/**
 * @author Laurent Crema
 */
public class Main {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new AppModule());

        SwingUtilities.invokeLater(() -> {

            NavBar navBar = injector.getInstance(NavBar.class);
            MainView view = (MainView) injector.getInstance(IMainView.class);
            view.setNavBar(navBar);
            view.setVisible(true);
        });
    }
}
