import app.AppModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import presenters.MainPresenter;
import presenters.NavPresenter;
import ui.implementation.components.nav.NavBar;
import ui.implementation.views.MainView;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new AppModule());

        SwingUtilities.invokeLater(() -> {

            NavBar navBar = injector.getInstance(NavBar.class);
            NavPresenter navPresenter = injector.getInstance(NavPresenter.class);
            navBar.setPresenter(navPresenter);

            MainView view = injector.getInstance(MainView.class);
            MainPresenter mainPresenter = injector.getInstance(MainPresenter.class);
            view.setPresenter(mainPresenter);
            view.setNavBar(navBar);
            view.setVisible(true);
        });
    }
}
