package ui.implementation.views;

import domain.CImage.CImageNG;
import domain.CImage.CImageRGB;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

import domain.common.Mode;
import presenters.MainPresenter;
import ui.implementation.components.image.ImagePanel;
import ui.implementation.components.nav.NavBar;
import ui.interfaces.IMainView;
import ui.old.Observers.JLabelBeanCImage;

public class MainView extends JFrame implements IMainView
{
    private CImageRGB imageRGB;
    private CImageNG imageNG;
    private JLabelBeanCImage observer;
    private Color couleurPinceauRGB;
    private int   couleurPinceauNG;

    private JScrollPane jScrollPane;
    private NavBar navBar;

    private MainPresenter presenter;

    public MainView(){
        initComponents();

        imageRGB = null;
        imageNG  = null;

        couleurPinceauRGB = Color.BLACK;
        couleurPinceauNG = 0;
    }

    public void setNavBar(NavBar navBar) {
        this.navBar = navBar;
        setJMenuBar(this.navBar);
    }

    public void setPresenter(MainPresenter presenter) {
        this.presenter = presenter;
    }

    private void initComponents() {
//        observer = new JLabelBeanCImage();
//        observer.setMode(JLabelBeanCImage.INACTIF);

        jScrollPane = new JScrollPane();
//        jScrollPane.setViewportView(observer);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane, GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane, GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
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
    public void displayImage(BufferedImage image) {
        ImagePanel panel = new ImagePanel(image);
        jScrollPane.setViewportView(panel);
        jScrollPane.revalidate();
        jScrollPane.repaint();
    }

    @Override
    public void changeMode(Mode m) {

    }
}
