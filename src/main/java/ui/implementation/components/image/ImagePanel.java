package ui.implementation.components.image;

import domain.image.GrayScaleMatrix;
import domain.image.Image;
import infrastructure.ui.ImageMapper;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class ImagePanel extends JPanel {
    private BufferedImage image;
    private double scale;
    private int drawW;
    private int drawH;
    private int offsetX;
    private int offsetY;

    public ImagePanel() { }

    public ImagePanel(BufferedImage image) {
        loadImage(image);
    }

    public void loadImage(BufferedImage image){
        this.image = image;
        setPreferredSize(new Dimension(image.getWidth(null), image.getHeight(null)));
        repaint();
    }

    public void loadImage(GrayScaleMatrix matrix){
        this.image = ImageMapper.toBufferedImage(matrix.toImage());
        setPreferredSize(new Dimension(this.image.getWidth(null), this.image.getHeight(null)));
        repaint();
    }

    public Point toImageCoordinates(int x, int y) {
        if (image == null) return null;

        if (x < offsetX || x >= offsetX + drawW ||
                y < offsetY || y >= offsetY + drawH) {
            return null;
        }

        int imgX = (int) ((x - offsetX) / scale);
        int imgY = (int) ((y - offsetY) / scale);

        return new Point(imgX, imgY);
    }

    private void computeViewport() {
            if (image == null) return;

            int imgW = image.getWidth();
            int imgH = image.getHeight();

            scale = Math.min(
                    (double) getWidth() / imgW,
                    (double) getHeight() / imgH
            );

            drawW = (int) (imgW * scale);
            drawH = (int) (imgH * scale);

            offsetX = (getWidth() - drawW) / 2;
            offsetY = (getHeight() - drawH) / 2;
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image == null) return;
        computeViewport();
        g.drawImage(image, offsetX, offsetY, drawW, drawH, null);
    }
}
