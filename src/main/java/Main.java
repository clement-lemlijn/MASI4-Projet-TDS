import app.AppModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import presenters.MainPresenter;
import ui.implementation.views.MainView;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new AppModule());

        SwingUtilities.invokeLater(() -> {
            MainView view = injector.getInstance(MainView.class);
            MainPresenter presenter = injector.getInstance(MainPresenter.class);
            view.setPresenter(presenter);
        });
    }
}
