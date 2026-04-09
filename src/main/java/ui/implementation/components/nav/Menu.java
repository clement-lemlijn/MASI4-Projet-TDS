package ui.implementation.components.nav;

import javax.swing.*;
import java.net.URL;

public class Menu extends JMenu {

    public Menu(String title, JMenuItem... items)
    {
        this(title,null, items);
    }

    public Menu(String title, String iconPath, JMenuItem... items)
    {
        super(title);

        if (iconPath != null) {
            URL url = getClass().getResource(iconPath);
            if (url != null) {
                setIcon(new ImageIcon(url));
            }
        }

        if (items != null) {
            for (JMenuItem item : items) {
                if (item != null) {
                    this.add(item);
                }
            }
        }
    }
}
