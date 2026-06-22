package ui.implementation.views;

import com.google.inject.Inject;
import domain.common.Mode;
import domain.image.Image;
import infrastructure.ui.ImageMapper;
import presenters.MainPresenter;
import ui.implementation.components.image.ImagePanel;
import ui.implementation.components.nav.NavBar;
import ui.interfaces.IMainView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

/**
 * @author Jean-Marc Wagner, Laurent Crema
 */
public class MainView extends JFrame implements IMainView
{
    private Mode currentMode;
    private ImagePanel imagePreviewContainer;
    private final MainPresenter presenter;

    @Inject
    public MainView(MainPresenter presenter){
        this.presenter = presenter;
        this.presenter.setView(this);
        initComponents();
    }

    public void setNavBar(NavBar navBar) {
        setJMenuBar(navBar);
    }

    private void initComponents() {
        imagePreviewContainer = new ImagePanel();

        imagePreviewContainer.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                drawPixel(e.getX(), e.getY(), 255, 0, 0);
            }
        });

        imagePreviewContainer.addMouseListener(new MouseAdapter() {

            private Point startPoint;

            @Override
            public void mousePressed(MouseEvent e) {
                startPoint = new Point(e.getX(), e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                Point endPoint = new Point(e.getX(), e.getY());
                if (startPoint == null) return;

                drawShape(
                        startPoint.x,
                        endPoint.x,
                        startPoint.y,
                        endPoint.y,
                        255, 0, 0
                );

                startPoint = null;
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(imagePreviewContainer)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(imagePreviewContainer)
                                .addContainerGap())
        );

        setSize(new Dimension(500, 400));
        setLocationRelativeTo(null);
    }

    public void drawPixel(int x, int y, int red, int green, int blue){
        try {
            Point p = imagePreviewContainer.toImageCoordinates(x, y);
            if(p == null) return;
            presenter.drawPixel((int)p.getX(), (int)p.getY(), red, green, blue);
        } catch (Exception e) {
            displayErrorMessage("Oups !",
                    "Impossible de dessiner sur l'image pour le moment : " + e.getMessage());
        }
    }

    public void drawShape(int x1, int x2, int y1, int y2, int red, int green, int blue){
        try {
            Point p1 = imagePreviewContainer.toImageCoordinates(x1, y1);
            Point p2 = imagePreviewContainer.toImageCoordinates(x2, y2);
            if(p1 == null || p2 == null) return;
            presenter.drawShape(
                    (int)p1.getX(), (int)p2.getX(), (int) p1.getY(), (int) p2.getY(),
                    red, green, blue);

        } catch (Exception e) {
            displayErrorMessage("Oups !",
                    "Impossible de dessiner sur l'image pour le moment : " + e.getMessage());
        }
    }


//    public void SelectCercleDetected(DeuxClicsEvent e)
//    {
//        if (presenter.isCircleModeActive())
//        {
//            try
//            {
//                if (imageRGB != null)
//                    imageRGB.DessineCercle(e.getX1(),e.getY1(),e.getX2(),e.getY2(),couleurPinceauRGB);
//                if (imageNG != null)
//                    imageNG.DessineCercle(e.getX1(),e.getY1(),e.getX2(),e.getY2(),couleurPinceauNG);
//            }
//            catch (CImageRGBException ex)
//            { System.out.println("Erreur RGB : " + ex.getMessage()); }
//            catch (CImageNGException ex)
//            { System.out.println("Erreur NG : " + ex.getMessage()); }
//        }
//    }
//
//    public void SelectCercleFillDetected(DeuxClicsEvent e)
//    {
//        if (presenter.isPlainCircleModeActive())
//        {
//            try
//            {
//                if (imageRGB != null)
//                    imageRGB.RemplitCercle(e.getX1(),e.getY1(),e.getX2(),e.getY2(),couleurPinceauRGB);
//                if (imageNG != null)
//                    imageNG.RemplitCercle(e.getX1(),e.getY1(),e.getX2(),e.getY2(),couleurPinceauNG);
//            }
//            catch (CImageRGBException ex)
//            { System.out.println("Erreur RGB : " + ex.getMessage()); }
//            catch (CImageNGException ex)
//            { System.out.println("Erreur NG : " + ex.getMessage()); }
//        }
//    }
//
//    public void SelectRectFillDetected(DeuxClicsEvent e)
//    {
//        if (presenter.isPlainRectangleActive())
//        {
//            try
//            {
//                if (imageRGB != null)
//                    imageRGB.RemplitRect(e.getX1(),e.getY1(),e.getX2(),e.getY2(),couleurPinceauRGB);
//                if (imageNG != null)
//                    imageNG.RemplitRect(e.getX1(),e.getY1(),e.getX2(),e.getY2(),couleurPinceauNG);
//            }
//            catch (CImageRGBException ex)
//            { System.out.println("Erreur RGB : " + ex.getMessage()); }
//            catch (CImageNGException ex)
//            { System.out.println("Erreur NG : " + ex.getMessage()); }
//        }
//    }

    @Override
    public void displayImage(Image image) {
        try {
            BufferedImage bufferedImage = ImageMapper.toBufferedImage(image);
            imagePreviewContainer.loadImage(bufferedImage);
        } catch (Exception ex) {
            displayErrorMessage("Oups !", ex.getMessage());
        }
    }

    @Override
    public void changeMode(Mode m) {

    }

    private void displayErrorMessage(String title, String message) {
        JOptionPane.showMessageDialog(
                this,
                message,
                title,
                JOptionPane.ERROR_MESSAGE
        );
    }

}
