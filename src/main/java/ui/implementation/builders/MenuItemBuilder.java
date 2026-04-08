package ui.implementation.builders;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.net.URL;

public class MenuItemBuilder {

    private String text;
    private URL icon;
    private ActionListener listener;

    public MenuItemBuilder setText(String text) {
        this.text = text;
        return this;
    }

    public MenuItemBuilder setIcon(String iconPath) {
        if (iconPath != null) {
            this.icon = getClass().getResource(iconPath);
        }
        return this;
    }

    public MenuItemBuilder setAction(ActionListener listener) {
        this.listener = listener;
        return this;
    }

    public JMenuItem build() {
        if (text == null) {
            throw new IllegalStateException("MenuItem text is required");
        }
        JMenuItem item = new JMenuItem(text);
        if (icon != null) item.setIcon(new ImageIcon(icon));
        if (listener != null) item.addActionListener(listener);
        return item;
    }
}