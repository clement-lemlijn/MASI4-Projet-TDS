package services;

import app.AppState;
import domain.image.Image;
import domain.image.Pixel;
import infrastructure.image.io.ImageLoader;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ImageService {

    private final AppState appState;

    public ImageService(AppState appState) {
        this.appState = appState;
    }

    public void createImage(int red, int green, int blue, int height, int width) {
        Image image = new Image(red, green, blue, height, width);
        appState.setImage(image);
    }

    public void loadImage(File f) throws IOException {
        Image image = ImageLoader.loadImage(f);
        appState.setImage(image);
    }
}
