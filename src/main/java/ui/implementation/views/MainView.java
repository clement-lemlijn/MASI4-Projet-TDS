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
import java.awt.Dimension;
import java.awt.image.BufferedImage;

/**
 * @author Jean-Marc Wagner, Laurent Crema
 */
public class MainView extends JFrame implements IMainView
{
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

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(imagePreviewContainer, GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(imagePreviewContainer, GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
                                .addContainerGap())
        );

        setSize(new Dimension(500, 400));
        setLocationRelativeTo(null);
    }

//    public void ClicDetected(UnClicEvent e)
//    {
//        if (presenter.isPixelModeActive())
//        {
//            try
//            {
//                if (imageRGB != null)
//                    imageRGB.setPixel(e.getX(),e.getY(),couleurPinceauRGB);
//                if (imageNG != null)
//                    imageNG.setPixel(e.getX(),e.getY(),couleurPinceauNG);
//            }
//            catch (CImageRGBException ex)
//            { System.out.println("Erreur RGB : " + ex.getMessage()); }
//            catch (CImageNGException ex)
//            { System.out.println("Erreur NG : " + ex.getMessage()); }
//        }
//    }
//
//    public void SelectLigneDetected(DeuxClicsEvent e)
//    {
//        if (presenter.isLineModeActive())
//        {
//            try
//            {
//                if (imageRGB != null)
//                    imageRGB.DessineLigne(e.getX1(),e.getY1(),e.getX2(),e.getY2(),couleurPinceauRGB);
//                if (imageNG != null)
//                    imageNG.DessineLigne(e.getX1(),e.getY1(),e.getX2(),e.getY2(),couleurPinceauNG);
//            }
//            catch (CImageRGBException ex)
//            { System.out.println("Erreur RGB : " + ex.getMessage()); }
//            catch (CImageNGException ex)
//            { System.out.println("Erreur NG : " + ex.getMessage()); }
//        }
//    }
//
//    public void SelectRectDetected(DeuxClicsEvent e)
//    {
//        if (presenter.isRectangleModeActive())
//        {
//            try
//            {
//                if (imageRGB != null)
//                    imageRGB.DessineRect(e.getX1(),e.getY1(),e.getX2(),e.getY2(),couleurPinceauRGB);
//                if (imageNG != null)
//                    imageNG.DessineRect(e.getX1(),e.getY1(),e.getX2(),e.getY2(),couleurPinceauNG);
//            }
//            catch (CImageRGBException ex)
//            { System.out.println("Erreur RGB : " + ex.getMessage()); }
//            catch (CImageNGException ex)
//            { System.out.println("Erreur NG : " + ex.getMessage()); }
//        }
//    }
//
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
