import com.google.inject.Guice;
import com.google.inject.Injector;
import ui.components.views.MainView;

import javax.swing.*;
import java.awt.*;

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
