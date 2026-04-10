package services;

import app.AppState;
import com.google.inject.Inject;
import domain.image.Image;
import infrastructure.image.io.ImageLoader;

import java.io.File;
import java.io.IOException;

public class ImageService {

    private final AppState appState;

    @Inject
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
