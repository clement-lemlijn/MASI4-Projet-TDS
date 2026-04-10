package ui.implementation.components.image;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImagePanel extends JPanel {
    private BufferedImage image;

    public ImagePanel() {

    }

    public ImagePanel(BufferedImage image) {
        loadImage(image);
    }

    public void loadImage(BufferedImage image){
        this.image = image;
        setPreferredSize(new Dimension(image.getWidth(null), image.getHeight(null)));
        repaint();
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
