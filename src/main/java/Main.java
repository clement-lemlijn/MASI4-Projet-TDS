import ui.components.views.MainView;

import java.awt.*;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new MainView().setVisible(true));
    }
}
