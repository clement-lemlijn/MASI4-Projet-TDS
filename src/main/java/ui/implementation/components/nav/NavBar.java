package ui.implementation.components.nav;

import domain.CImage.CImageNG;
import domain.CImage.CImageRGB;
import domain.CImage.Exceptions.CImageNGException;
import domain.CImage.Observers.JLabelBeanCImage;
import domain.ImageProcessing.Histogramme.Histogramme;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import ui.implementation.components.intent.FileChooser;
import ui.implementation.dialogs.NewRgbImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class NavBar extends JMenuBar {

    private final JLabelBeanCImage observer;
    private ButtonGroup buttonGroupDessiner;
    private JCheckBoxMenuItem jCheckBoxMenuItemDessinerCercle;
    private JCheckBoxMenuItem jCheckBoxMenuItemDessinerCerclePlein;
    private JCheckBoxMenuItem jCheckBoxMenuItemDessinerLigne;
    private JCheckBoxMenuItem jCheckBoxMenuItemDessinerPixel;
    private JCheckBoxMenuItem jCheckBoxMenuItemDessinerRectangle;
    private JCheckBoxMenuItem jCheckBoxMenuItemDessinerRectanglePlein;
    private JMenu jMenuDessiner;
    private JMenu jMenuFourier;
    private JMenu jMenuHistogramme;
    private JMenu jMenuImage;

    private CImageRGB imageRGB;
    private CImageNG imageNG;

    private Color couleurPinceauRGB;
    private int   couleurPinceauNG;

    public NavBar(JLabelBeanCImage observer) {
        initComponents();
        this.observer = observer;
        jMenuDessiner.setEnabled(false);
        jMenuFourier.setEnabled(false);
        jMenuHistogramme.setEnabled(false);
    }

    private void initComponents(){

        jMenuImage = new Menu("Image","/net_13_p1.jpg",
                new Menu("Nouvelle", "/file_65_p3.jpg",
                        new MenuItem("RGB", this::jMenuItemNouvelleRGBActionPerformed),
                        new MenuItem("NG", e -> {})
                ),
                new Menu("Ouvrir", "/folder_036_p3.jpg",
                        new MenuItem("RGB", this::loadRGBImage),
                        new MenuItem("NG", this::loadGreyScaleImage)
                ),
                new MenuItem("Enregistrer sous...","/dd_27_p3.jpg", e -> {}),
                new MenuItem("Quitter","/cp_59_p3.jpg", this::quit)
        );

        jMenuDessiner = new Menu("Dessiner","/dd_28_p1.jpg",
                new MenuItem("Couleur", "/display_14_p3.jpg", this::jMenuItemCouleurPinceauActionPerformed),
                new Menu("Formes",
                        new MenuItem("Pixel", this::jCheckBoxMenuItemDessinerPixelActionPerformed),
                        new MenuItem("Ligne", this::jCheckBoxMenuItemDessinerLigneActionPerformed),
                        new MenuItem("Rectangle", this::jCheckBoxMenuItemDessinerRectangleActionPerformed),
                        new MenuItem("Cercle", this::jCheckBoxMenuItemDessinerCercleActionPerformed)
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
            imageRGB = new CImageRGB(file);
            observer.setCImage(imageRGB);
            imageNG = null;
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
            imageNG = new CImageNG(file);
            observer.setCImage(imageNG);
            imageRGB = null;
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

    //####################################################

    private void jMenuItemNouvelleRGBActionPerformed(java.awt.event.ActionEvent e) {
        NewRgbImage dialog = new NewRgbImage(new JFrame(),true);
        dialog.setVisible(true);
        imageRGB = dialog.getCImageRGB();
        observer.setCImage(imageRGB);
        imageNG = null;
        activeMenusRGB();
    }


    private void jCheckBoxMenuItemDessinerPixelActionPerformed(java.awt.event.ActionEvent e) {
        if (!jCheckBoxMenuItemDessinerPixel.isSelected()) observer.setMode(JLabelBeanCImage.INACTIF);
        else {
            jCheckBoxMenuItemDessinerPixel.setSelected(true);
            jCheckBoxMenuItemDessinerLigne.setSelected(false);
            jCheckBoxMenuItemDessinerRectangle.setSelected(false);
            jCheckBoxMenuItemDessinerRectanglePlein.setSelected(false);
            jCheckBoxMenuItemDessinerCercle.setSelected(false);
            jCheckBoxMenuItemDessinerCerclePlein.setSelected(false);
            observer.setMode(JLabelBeanCImage.CLIC);
        }
    }

    private void jCheckBoxMenuItemDessinerRectanglePleinActionPerformed(java.awt.event.ActionEvent e) {
        if (!jCheckBoxMenuItemDessinerRectanglePlein.isSelected()) observer.setMode(JLabelBeanCImage.INACTIF);
        else
        {
            jCheckBoxMenuItemDessinerPixel.setSelected(false);
            jCheckBoxMenuItemDessinerLigne.setSelected(false);
            jCheckBoxMenuItemDessinerRectangle.setSelected(false);
            jCheckBoxMenuItemDessinerRectanglePlein.setSelected(true);
            jCheckBoxMenuItemDessinerCercle.setSelected(false);
            jCheckBoxMenuItemDessinerCerclePlein.setSelected(false);
            observer.setMode(JLabelBeanCImage.SELECT_RECT_FILL);
        }
    }

    private void jCheckBoxMenuItemDessinerRectangleActionPerformed(java.awt.event.ActionEvent e) {
        if (!jCheckBoxMenuItemDessinerRectangle.isSelected()) observer.setMode(JLabelBeanCImage.INACTIF);
        else
        {
            jCheckBoxMenuItemDessinerPixel.setSelected(false);
            jCheckBoxMenuItemDessinerLigne.setSelected(false);
            jCheckBoxMenuItemDessinerRectangle.setSelected(true);
            jCheckBoxMenuItemDessinerRectanglePlein.setSelected(false);
            jCheckBoxMenuItemDessinerCercle.setSelected(false);
            jCheckBoxMenuItemDessinerCerclePlein.setSelected(false);
            observer.setMode(JLabelBeanCImage.SELECT_RECT);
        }
    }

    private void jCheckBoxMenuItemDessinerLigneActionPerformed(java.awt.event.ActionEvent e) {
        if (!jCheckBoxMenuItemDessinerLigne.isSelected()) observer.setMode(JLabelBeanCImage.INACTIF);
        else
        {
            jCheckBoxMenuItemDessinerPixel.setSelected(false);
            jCheckBoxMenuItemDessinerLigne.setSelected(true);
            jCheckBoxMenuItemDessinerRectangle.setSelected(false);
            jCheckBoxMenuItemDessinerRectanglePlein.setSelected(false);
            jCheckBoxMenuItemDessinerCercle.setSelected(false);
            jCheckBoxMenuItemDessinerCerclePlein.setSelected(false);
            observer.setMode(JLabelBeanCImage.SELECT_LIGNE);
        }
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

    private void jCheckBoxMenuItemDessinerCerclePleinActionPerformed(ActionEvent e) {
        if (!jCheckBoxMenuItemDessinerCerclePlein.isSelected()) observer.setMode(JLabelBeanCImage.INACTIF);
        else {
            jCheckBoxMenuItemDessinerPixel.setSelected(false);
            jCheckBoxMenuItemDessinerLigne.setSelected(false);
            jCheckBoxMenuItemDessinerRectangle.setSelected(false);
            jCheckBoxMenuItemDessinerRectanglePlein.setSelected(false);
            jCheckBoxMenuItemDessinerCercle.setSelected(false);
            jCheckBoxMenuItemDessinerCerclePlein.setSelected(true);
            observer.setMode(JLabelBeanCImage.SELECT_CERCLE_FILL);
        }
    }

    private void jCheckBoxMenuItemDessinerCercleActionPerformed(ActionEvent e) {
        if (!jCheckBoxMenuItemDessinerCercle.isSelected()) observer.setMode(JLabelBeanCImage.INACTIF);
        else
        {
            jCheckBoxMenuItemDessinerPixel.setSelected(false);
            jCheckBoxMenuItemDessinerLigne.setSelected(false);
            jCheckBoxMenuItemDessinerRectangle.setSelected(false);
            jCheckBoxMenuItemDessinerRectanglePlein.setSelected(false);
            jCheckBoxMenuItemDessinerCercle.setSelected(true);
            jCheckBoxMenuItemDessinerCerclePlein.setSelected(false);
            observer.setMode(JLabelBeanCImage.SELECT_CERCLE);
        }
    }


    private void jMenuHistogrammeAfficherActionPerformed(ActionEvent e) {
        int[] histo;
        try
        {
            int[][] f_int = imageNG.getMatrice();
            histo = Histogramme.Histogramme256(f_int);
        }
        catch (CImageNGException ex)
        {
            System.out.println("Erreur CImageNG : " + ex.getMessage());
            return;
        }

        // Cr�ation du dataset
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

    private void jMenuItemCouleurPinceauActionPerformed(ActionEvent e) {
//        if (imageRGB != null)
//        {
//            Color newC = JColorChooser.showDialog(this,"Couleur du pinceau",couleurPinceauRGB);
//            if (newC != null) couleurPinceauRGB = newC;
//            observer.setCouleurPinceau(couleurPinceauRGB);
//        }
//
//        if (imageNG != null)
//        {
//            GreyScalePicker dialog = new GreyScalePicker(this,true,couleurPinceauNG);
//            dialog.setVisible(true);
//            couleurPinceauNG = dialog.getCouleur();
//        }
    }
}
