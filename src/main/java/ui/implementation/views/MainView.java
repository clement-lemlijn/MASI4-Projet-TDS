package ui.implementation.views;

import domain.CImage.CImageNG;
import domain.CImage.CImageRGB;
import domain.CImage.Exceptions.CImageNGException;
import domain.CImage.Exceptions.CImageRGBException;
import domain.CImage.Observers.Events.*;

import java.awt.*;
import javax.swing.*;

import domain.CImage.Observers.JLabelBeanCImage;
import presenters.MainPresenter;
import ui.implementation.components.nav.NavBar;
import ui.interfaces.IMainView;

public class MainView extends JFrame implements IMainView,
        ClicListener, SelectLigneListener,SelectRectListener,
        SelectRectFillListener, SelectCercleListener,SelectCercleFillListener
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

    public void setPresenter(MainPresenter presenter) {
        this.presenter = presenter;
    }

    private void initComponents() {
        observer = new JLabelBeanCImage();
        observer.addClicListener(this);
        observer.addSelectLigneListener(this);
        observer.addSelectRectListener(this);
        observer.addSelectRectFillListener(this);
        observer.addSelectCercleListener(this);
        observer.addSelectCercleFillListener(this);
        observer.setMode(JLabelBeanCImage.INACTIF);
        navBar = new NavBar(observer);

        jScrollPane = new JScrollPane();
        jScrollPane.setViewportView(observer);

        setJMenuBar(navBar);

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

    public void ClicDetected(UnClicEvent e)
    {
        if (presenter.isPixelModeActive())
        {
            try
            {
                if (imageRGB != null)
                    imageRGB.setPixel(e.getX(),e.getY(),couleurPinceauRGB);
                if (imageNG != null)
                    imageNG.setPixel(e.getX(),e.getY(),couleurPinceauNG);
            }
            catch (CImageRGBException ex)
            { System.out.println("Erreur RGB : " + ex.getMessage()); }
            catch (CImageNGException ex)
            { System.out.println("Erreur NG : " + ex.getMessage()); }
        }
    }

    public void SelectLigneDetected(DeuxClicsEvent e)
    {
        if (presenter.isLineModeActive())
        {
            try
            {
                if (imageRGB != null)
                    imageRGB.DessineLigne(e.getX1(),e.getY1(),e.getX2(),e.getY2(),couleurPinceauRGB);
                if (imageNG != null)
                    imageNG.DessineLigne(e.getX1(),e.getY1(),e.getX2(),e.getY2(),couleurPinceauNG);
            }
            catch (CImageRGBException ex)
            { System.out.println("Erreur RGB : " + ex.getMessage()); }
            catch (CImageNGException ex)
            { System.out.println("Erreur NG : " + ex.getMessage()); }
        }
    }

    public void SelectRectDetected(DeuxClicsEvent e)
    {
        if (presenter.isRectangleModeActive())
        {
            try
            {
                if (imageRGB != null)
                    imageRGB.DessineRect(e.getX1(),e.getY1(),e.getX2(),e.getY2(),couleurPinceauRGB);
                if (imageNG != null)
                    imageNG.DessineRect(e.getX1(),e.getY1(),e.getX2(),e.getY2(),couleurPinceauNG);
            }
            catch (CImageRGBException ex)
            { System.out.println("Erreur RGB : " + ex.getMessage()); }
            catch (CImageNGException ex)
            { System.out.println("Erreur NG : " + ex.getMessage()); }
        }
    }

    public void SelectCercleDetected(DeuxClicsEvent e)
    {
        if (presenter.isCircleModeActive())
        {
            try
            {
                if (imageRGB != null)
                    imageRGB.DessineCercle(e.getX1(),e.getY1(),e.getX2(),e.getY2(),couleurPinceauRGB);
                if (imageNG != null)
                    imageNG.DessineCercle(e.getX1(),e.getY1(),e.getX2(),e.getY2(),couleurPinceauNG);
            }
            catch (CImageRGBException ex)
            { System.out.println("Erreur RGB : " + ex.getMessage()); }
            catch (CImageNGException ex)
            { System.out.println("Erreur NG : " + ex.getMessage()); }
        }
    }

    public void SelectCercleFillDetected(DeuxClicsEvent e)
    {
        if (presenter.isPlainCircleModeActive())
        {
            try
            {
                if (imageRGB != null)
                    imageRGB.RemplitCercle(e.getX1(),e.getY1(),e.getX2(),e.getY2(),couleurPinceauRGB);
                if (imageNG != null)
                    imageNG.RemplitCercle(e.getX1(),e.getY1(),e.getX2(),e.getY2(),couleurPinceauNG);
            }
            catch (CImageRGBException ex)
            { System.out.println("Erreur RGB : " + ex.getMessage()); }
            catch (CImageNGException ex)
            { System.out.println("Erreur NG : " + ex.getMessage()); }
        }
    }

    public void SelectRectFillDetected(DeuxClicsEvent e)
    {
        if (presenter.isPlainRectangleActive())
        {
            try
            {
                if (imageRGB != null)
                    imageRGB.RemplitRect(e.getX1(),e.getY1(),e.getX2(),e.getY2(),couleurPinceauRGB);
                if (imageNG != null)
                    imageNG.RemplitRect(e.getX1(),e.getY1(),e.getX2(),e.getY2(),couleurPinceauNG);
            }
            catch (CImageRGBException ex)
            { System.out.println("Erreur RGB : " + ex.getMessage()); }
            catch (CImageNGException ex)
            { System.out.println("Erreur NG : " + ex.getMessage()); }
        }
    }
}
