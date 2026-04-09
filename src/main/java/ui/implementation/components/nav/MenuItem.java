package ui.implementation.components.nav;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.net.URL;

public class MenuItem extends JMenuItem {

    public MenuItem(String title, ActionListener listener) {
        this(title, null, listener);
    }

    public MenuItem(String title, String iconPath, ActionListener listener) {
        super(title);
        if(iconPath != null) {
            URL url = getClass().getResource(iconPath);
            if (url != null) {
                super.setIcon(new ImageIcon(url));
            }
        }
        if (listener != null) {
            addActionListener(listener);
        }
    }
}
