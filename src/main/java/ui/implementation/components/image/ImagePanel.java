package ui.implementation.components.image;

import domain.image.GrayScaleMatrix;
import domain.image.Image;
import infrastructure.ui.ImageMapper;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class ImagePanel extends JPanel {
    private Image original;
    private BufferedImage image;

    public ImagePanel() {

    }

    public ImagePanel(BufferedImage image) {
        loadImage(image);
    }

    public Image getImage(){
        return original;
    }

    public void loadImage(BufferedImage image){
        this.image = image;
        this.original = ImageMapper.fromBufferedImage(image);
        setPreferredSize(new Dimension(image.getWidth(null), image.getHeight(null)));
        repaint();
    }

    public void loadImage(Image image){
        this.original = image;
        this.image = ImageMapper.toBufferedImage(image);
        setPreferredSize(new Dimension(this.image.getWidth(null), this.image.getHeight(null)));
        repaint();
    }

    public void loadImage(GrayScaleMatrix matrix){
        this.original = matrix.toImage();
        this.image = ImageMapper.toBufferedImage(original);
        setPreferredSize(new Dimension(this.image.getWidth(null), this.image.getHeight(null)));
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
