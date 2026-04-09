package ui.implementation.components.nav;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MenuItem extends JMenuItem {

    public MenuItem(String title, ActionListener listener) {
        this(title, null, listener);
    }

    public MenuItem(String title, String iconPath, ActionListener listener) {
        super(title);
        if(iconPath != null) {
            super.setIcon(new ImageIcon(iconPath));
        }
        if (listener != null) {
            addActionListener(listener);
        }
    }
}
