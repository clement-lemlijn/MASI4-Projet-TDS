package ui.implementation.components.nav;

import domain.CImage.Exceptions.CImageNGException;
import domain.CImage.Exceptions.CImageRGBException;
import domain.CImage.Observers.JLabelBeanCImage;
import domain.ImageProcessing.Histogramme.Histogramme;
import domain.common.Mode;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import presenters.NavPresenter;
import ui.implementation.components.intent.FileChooser;
import ui.implementation.dialogs.ImageCreatorDialog;
import ui.implementation.dialogs.GreyScaleImageCreatorDialog;
import ui.implementation.dialogs.RGBImageCreatorDialog;
import ui.interfaces.INavBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class NavBar extends JMenuBar implements INavBar {

    private JLabelBeanCImage observer;
    private JMenu jMenuDessiner;
    private JMenu jMenuFourier;
    private JMenu jMenuHistogramme;
    private JMenu jMenuImage;

//    private CImageRGB imageRGB;
//    private CImageNG imageNG;

    private Color couleurPinceauRGB;
    private int   couleurPinceauNG;

    private NavPresenter presenter;

    public NavBar() {
        initComponents();
        jMenuDessiner.setEnabled(false);
        jMenuFourier.setEnabled(false);
        jMenuHistogramme.setEnabled(false);
    }

    public void setPresenter(NavPresenter presenter) {
        this.presenter = presenter;
    }

    private void initComponents(){

        jMenuImage = new Menu("Image","/net_13_p1.jpg",
                new Menu("Nouvelle", "/file_65_p3.jpg",
                        new MenuItem("RGB", e -> this.createImage(e, new RGBImageCreatorDialog(new JFrame(),true))),
                        new MenuItem("NG", e -> this.createImage(e, new GreyScaleImageCreatorDialog(new JFrame(),true)))
                ),
                new Menu("Ouvrir", "/folder_036_p3.jpg",
                        new MenuItem("RGB", this::loadRGBImage),
                        new MenuItem("NG", this::loadGreyScaleImage)
                ),
                new MenuItem("Enregistrer sous...","/dd_27_p3.jpg", e -> {}),
                new MenuItem("Quitter","/cp_59_p3.jpg", this::quit)
        );

        jMenuDessiner = new Menu("Dessiner","/dd_28_p1.jpg",
                new MenuItem("Couleur", "/display_14_p3.jpg", this::chooseColor),
                new Menu("Formes",
                        new MenuItem("Pixel", e -> this.setMode(e, Mode.DRAW_PIXEL)),
                        new MenuItem("Ligne", e -> this.setMode(e, Mode.DRAW_LINE)),
                        new MenuItem("Rectangle",e -> this.setMode(e, Mode.DRAW_RECTANGLE)),
                        new MenuItem("Cercle", e -> this.setMode(e, Mode.DRAW_CIRCLE))
                )
        );

        jMenuFourier = new Menu("Fourier","/cp_51_p1.jpg",
                new Menu("Afficher", "/cp_51_p3.jpg",
                        new MenuItem("Module", this::jMenuItemFourierAfficherModuleActionPerformed),
                        new MenuItem("Phase", this::jMenuItemFourierAfficherPhaseActionPerformed),
                        new MenuItem("Partie réelle", this::jMenuItemFourierAfficherPartieReelleActionPerformed),
                        new MenuItem("Partie imaginaire", this::jMenuItemFourierAfficherPartieImaginaireActionPerformed)
        ));

        jMenuHistogramme = new Menu("Histogramme","/report_48_hot.jpg",
                new MenuItem("Afficher", "/report_32_hot.jpg",this::jMenuHistogrammeAfficherActionPerformed)
        );

        this.add(jMenuImage);
        this.add(jMenuDessiner);
        this.add(jMenuFourier);
        this.add(jMenuHistogramme);
    }

    //####################################################

    private File chooseFile(){
        FileChooser chooser = new FileChooser("./");

        int dialogResult = chooser.showOpenDialog(this);
        if (dialogResult != JFileChooser.APPROVE_OPTION) return null;

        return chooser.getSelectedFile();
    }

    private void loadRGBImage(ActionEvent e) {
        try
        {
            File file = chooseFile();
            presenter.loadImage(file);
            activeMenusRGB();
        }
        catch (IOException ex)
        {
            System.err.println("Erreur I/O : " + ex.getMessage());
        }
    }

    private void loadGreyScaleImage(ActionEvent e) {
        try
        {
            File file = chooseFile();
            presenter.loadImage(file);
            activeMenusNG();
        }
        catch (IOException ex)
        {
            System.err.println("Erreur I/O : " + ex.getMessage());
        }
    }

    private void quit(ActionEvent e) {
        System.exit(0);
    }

    private void createImage(ActionEvent e, ImageCreatorDialog dialog) {
        try {
            dialog.setVisible(true);
            presenter.createImage(dialog.getImageColor(), dialog.getImageHeight(), dialog.getImageWidth());
            activeMenusRGB();
            activeMenusNG();
        } catch (CImageRGBException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void setMode(ActionEvent e, Mode mode) {
        presenter.setMode(mode);
    }

    private void chooseColor(ActionEvent e) {
        /*if (imageRGB != null)
        {
            Color newC = JColorChooser.showDialog(this,"Couleur du pinceau", couleurPinceauRGB);
            if (newC != null) couleurPinceauRGB = newC;
            observer.setCouleurPinceau(couleurPinceauRGB);
        }

        if (imageNG != null)
        {
            GreyScalePicker dialog = new GreyScalePicker(new JFrame(),true, couleurPinceauNG);
            dialog.setVisible(true);
            couleurPinceauNG = dialog.getCouleur();
        }*/
    }


    private void activeMenusNG()
    {
        jMenuDessiner.setEnabled(true);
        jMenuFourier.setEnabled(true);
        jMenuHistogramme.setEnabled(true);
    }

    private void activeMenusRGB()
    {
        jMenuDessiner.setEnabled(true);
        jMenuFourier.setEnabled(false);
        jMenuHistogramme.setEnabled(false);
    }

    //####################################################

    private void jMenuHistogrammeAfficherActionPerformed(ActionEvent e) {
        int[][] f_int = null;
        try {
            f_int = presenter.getImageMatrix();
        } catch (CImageNGException ex) {
            throw new RuntimeException(ex);
        }
        int[] histo = Histogramme.Histogramme256(f_int);

        //Création du dataset
        XYSeries serie = new XYSeries("Histo");
        for(int i=0 ; i<256 ; i++) serie.add(i,histo[i]);
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(serie);

        // Creation du chart
        JFreeChart chart = ChartFactory.createHistogram("Histogramme","Niveaux de gris","Nombre de pixels",dataset, PlotOrientation.VERTICAL,false,false,false);

        XYPlot plot = chart.getXYPlot();
        ValueAxis axeX = plot.getDomainAxis();
        axeX.setRange(0,255);
        plot.setDomainAxis(axeX);

        // creation d'une frame
        ChartFrame frame = new ChartFrame("Histogramme de l'image",chart);
        frame.pack();
        frame.setVisible(true);
    }

    private void jMenuItemFourierAfficherPartieImaginaireActionPerformed(ActionEvent e) {
//        try
//        {
//            int f_int[][] = imageNG.getMatrice();
//            double f[][] = new double[imageNG.getLargeur()][imageNG.getHauteur()];
//            for(int i=0 ; i<imageNG.getLargeur() ; i++)
//                for(int j=0 ; j<imageNG.getHauteur() ; j++) f[i][j] = (double)(f_int[i][j]);
//
//            System.out.println("Debut Fourier");
//            MatriceComplexe fourier = Fourier.Fourier2D(f);
//            System.out.println("Fin Fourier");
//            fourier = Fourier.decroise(fourier);
//            double partieImaginaire[][] = fourier.getPartieImaginaire();
//
//            DoubleMatrix dialog = new DoubleMatrix(this,true,partieImaginaire,"Fourier : Affichage de la partie imaginaire");
//            dialog.setVisible(true);
//        }
//        catch (CImageNGException ex)
//        {
//            System.out.println("Erreur CImageNG : " + ex.getMessage());
//        }
    }

    private void jMenuItemFourierAfficherPartieReelleActionPerformed(ActionEvent e) {
//        try
//        {
//            int f_int[][] = imageNG.getMatrice();
//            double f[][] = new double[imageNG.getLargeur()][imageNG.getHauteur()];
//            for(int i=0 ; i<imageNG.getLargeur() ; i++)
//                for(int j=0 ; j<imageNG.getHauteur() ; j++) f[i][j] = (double)(f_int[i][j]);
//
//            System.out.println("Debut Fourier");
//            MatriceComplexe fourier = Fourier.Fourier2D(f);
//            System.out.println("Fin Fourier");
//            fourier = Fourier.decroise(fourier);
//            double partieReelle[][] = fourier.getPartieReelle();
//
//            DoubleMatrix dialog = new DoubleMatrix(this,true,partieReelle,"Fourier : Affichage de la partie reelle");
//            dialog.setVisible(true);
//        }
//        catch (CImageNGException ex)
//        {
//            System.out.println("Erreur CImageNG : " + ex.getMessage());
//        }
    }

    private void jMenuItemFourierAfficherPhaseActionPerformed(ActionEvent e) {
//        try
//        {
//            int f_int[][] = imageNG.getMatrice();
//            double f[][] = new double[imageNG.getLargeur()][imageNG.getHauteur()];
//            for(int i=0 ; i<imageNG.getLargeur() ; i++)
//                for(int j=0 ; j<imageNG.getHauteur() ; j++) f[i][j] = (double)(f_int[i][j]);
//
//            System.out.println("Debut Fourier");
//            MatriceComplexe fourier = Fourier.Fourier2D(f);
//            System.out.println("Fin Fourier");
//            fourier = Fourier.decroise(fourier);
//            double phase[][] = fourier.getPhase();
//
//            DoubleMatrix dialog = new DoubleMatrix(this,true,phase,"Fourier : Affichage de la phase");
//            dialog.setVisible(true);
//        }
//        catch (CImageNGException ex)
//        {
//            System.out.println("Erreur CImageNG : " + ex.getMessage());
//        }
    }

    private void jMenuItemFourierAfficherModuleActionPerformed(ActionEvent e) {
//        try
//        {
//            int f_int[][] = imageNG.getMatrice();
//            double f[][] = new double[imageNG.getLargeur()][imageNG.getHauteur()];
//            for(int i=0 ; i<imageNG.getLargeur() ; i++)
//                for(int j=0 ; j<imageNG.getHauteur() ; j++) f[i][j] = (double)(f_int[i][j]);
//
//            System.out.println("Debut Fourier");
//            MatriceComplexe fourier = Fourier.Fourier2D(f);
//            System.out.println("Fin Fourier");
//            fourier = Fourier.decroise(fourier);
//            double module[][] = fourier.getModule();
//
//            DoubleMatrix dialog = new DoubleMatrix(this,true,module,"Fourier : Affichage du module");
//            dialog.setVisible(true);
//        }
//        catch (CImageNGException ex)
//        {
//            System.out.println("Erreur CImageNG : " + ex.getMessage());
//        }
    }
}
