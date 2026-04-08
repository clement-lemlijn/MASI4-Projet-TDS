package ui.implementation.builders;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MenuBuilder {

    private String text;
    private URL icon;
    private List<JMenu> subMenus;
    private List<JMenuItem> items;

    public MenuBuilder() {
        text = "text_placeholder";
        subMenus = new ArrayList<>();
        items = new ArrayList<>();
    }

    public MenuBuilder setText(String text) {
        this.text = text;
        return this;
    }

    public MenuBuilder setIcon(String iconPath) {
        if (iconPath != null) {
            this.icon = getClass().getResource(iconPath);
        }
        return this;
    }

    public MenuBuilder addSubMenu(JMenu item) {
        this.subMenus.add(item);
        return this;
    }

    public MenuBuilder addItem(JMenuItem item) {
        this.items.add(item);
        return this;
    }

    public JMenu build() {
        JMenu menu = new JMenu(text);
        if (icon != null) menu.setIcon(new ImageIcon(icon));
        if(!items.isEmpty()) {
            for (JMenuItem item : items) {
                menu.add(item);
            }
        }
        if(!subMenus.isEmpty()) {
            for (JMenu subMenu : subMenus) {
                menu.add(subMenu);
            }
        }
        return menu;
    }
}
