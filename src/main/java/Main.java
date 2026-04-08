import com.google.inject.Guice;
import com.google.inject.Injector;
import ui.implementation.views.MainView;

import javax.swing.*;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new AppModule());

        SwingUtilities.invokeLater(() -> {
            MainView frame = injector.getInstance(MainView.class);
            frame.setVisible(true);
        });
    }
}
