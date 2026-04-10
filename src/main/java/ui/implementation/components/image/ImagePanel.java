package ui.implementation.components.image;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {
    private Image image;

    public ImagePanel() {

    }

    public ImagePanel(Image image) {
        loadImage(image);
    }

    private void loadImage(Image image){
        this.image = image;
        setPreferredSize(new Dimension(image.getWidth(null), image.getHeight(null)));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            int imgW = image.getWidth(null);
            int imgH = image.getHeight(null);
            double scale = Math.min((double) getWidth() / imgW, (double) getHeight() / imgH);
            int drawW = (int) (imgW * scale);
            int drawH = (int) (imgH * scale);
            int x = (getWidth() - drawW) / 2;
            int y = (getHeight() - drawH) / 2;
            g.drawImage(image, x, y, drawW, drawH, null);
        }
    }
}
