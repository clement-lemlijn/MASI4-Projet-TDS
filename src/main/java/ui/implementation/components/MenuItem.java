package ui.implementation.components;

import java.net.URL;

public class MenuItem {

    private String title;
    private URL iconPath;

    public MenuItem() {

    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIconPath(URL iconPath) {
        this.iconPath = iconPath;
    }
}
