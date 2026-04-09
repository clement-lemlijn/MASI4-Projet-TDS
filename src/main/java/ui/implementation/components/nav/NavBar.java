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

import javax.swing.*;
import java.awt.*;

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
    private JMenu jMenuFourierAfficher;
    private JMenu jMenuHistogramme;
    private JMenuItem jMenuHistogrammeAfficher;
    private JMenu jMenuImage;
    private JMenuItem jMenuItemCouleurPinceau;
    private JMenuItem jMenuItemEnregistrerSous;
    private JMenuItem jMenuItemFourierAfficherModule;
    private JMenuItem jMenuItemFourierAfficherPartieImaginaire;
    private JMenuItem jMenuItemFourierAfficherPartieReelle;
    private JMenuItem jMenuItemFourierAfficherPhase;
    private JMenuItem jMenuItemNouvelleNG;
    private JMenuItem jMenuItemNouvelleRGB;
    private JMenuItem jMenuItemOuvrirNG;
    private JMenuItem jMenuItemOuvrirRGB;
    private JMenu jMenuNouvelle;
    private JMenu jMenuOuvrir;
    private JMenuItem jMenuQuitter;
    private JSeparator jSeparator1;
    private JSeparator jSeparator2;

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
                        new MenuItem("RGB", e -> {}),
                        new MenuItem("NG", e -> {})
                ),
                new Menu("Ouvrir", "/folder_036_p3.jpg",
                        new MenuItem("RGB", e -> {}),
                        new MenuItem("NG", e -> {})
                ),
                new MenuItem("Enregistrer sous...","/dd_27_p3.jpg", e -> {}),
                new MenuItem("Quitter","/cp_59_p3.jpg",  e -> {})
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

    private void jCheckBoxMenuItemDessinerPixelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemDessinerPixelActionPerformed
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

    private void jCheckBoxMenuItemDessinerRectanglePleinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemDessinerRectanglePleinActionPerformed
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

    private void jCheckBoxMenuItemDessinerRectangleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemDessinerRectangleActionPerformed
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

    private void jCheckBoxMenuItemDessinerLigneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemDessinerLigneActionPerformed
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

    private void jCheckBoxMenuItemDessinerCerclePleinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemDessinerCerclePleinActionPerformed
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

    private void jCheckBoxMenuItemDessinerCercleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemDessinerCercleActionPerformed
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


    private void jMenuHistogrammeAfficherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuHistogrammeAfficherActionPerformed
        int histo[];
        try
        {
            int f_int[][] = imageNG.getMatrice();
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

        XYPlot plot = (XYPlot)chart.getXYPlot();
        ValueAxis axeX = plot.getDomainAxis();
        axeX.setRange(0,255);
        plot.setDomainAxis(axeX);

        // creation d'une frame
        ChartFrame frame = new ChartFrame("Histogramme de l'image",chart);
        frame.pack();
        frame.setVisible(true);
    }

    private void jMenuItemFourierAfficherPartieImaginaireActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFourierAfficherPartieImaginaireActionPerformed
        /*try
        {
            int f_int[][] = imageNG.getMatrice();
            double f[][] = new double[imageNG.getLargeur()][imageNG.getHauteur()];
            for(int i=0 ; i<imageNG.getLargeur() ; i++)
                for(int j=0 ; j<imageNG.getHauteur() ; j++) f[i][j] = (double)(f_int[i][j]);

            System.out.println("Debut Fourier");
            MatriceComplexe fourier = Fourier.Fourier2D(f);
            System.out.println("Fin Fourier");
            fourier = Fourier.decroise(fourier);
            double partieImaginaire[][] = fourier.getPartieImaginaire();

            DoubleMatrix dialog = new DoubleMatrix(this,true,partieImaginaire,"Fourier : Affichage de la partie imaginaire");
            dialog.setVisible(true);
        }
        catch (CImageNGException ex)
        {
            System.out.println("Erreur CImageNG : " + ex.getMessage());
        }*/
    }

    private void jMenuItemFourierAfficherPartieReelleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFourierAfficherPartieReelleActionPerformed
        /*try
        {
            int f_int[][] = imageNG.getMatrice();
            double f[][] = new double[imageNG.getLargeur()][imageNG.getHauteur()];
            for(int i=0 ; i<imageNG.getLargeur() ; i++)
                for(int j=0 ; j<imageNG.getHauteur() ; j++) f[i][j] = (double)(f_int[i][j]);

            System.out.println("Debut Fourier");
            MatriceComplexe fourier = Fourier.Fourier2D(f);
            System.out.println("Fin Fourier");
            fourier = Fourier.decroise(fourier);
            double partieReelle[][] = fourier.getPartieReelle();

            DoubleMatrix dialog = new DoubleMatrix(this,true,partieReelle,"Fourier : Affichage de la partie reelle");
            dialog.setVisible(true);
        }
        catch (CImageNGException ex)
        {
            System.out.println("Erreur CImageNG : " + ex.getMessage());
        }*/

    }

    private void jMenuItemFourierAfficherPhaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFourierAfficherPhaseActionPerformed
        /*try
        {
            int f_int[][] = imageNG.getMatrice();
            double f[][] = new double[imageNG.getLargeur()][imageNG.getHauteur()];
            for(int i=0 ; i<imageNG.getLargeur() ; i++)
                for(int j=0 ; j<imageNG.getHauteur() ; j++) f[i][j] = (double)(f_int[i][j]);

            System.out.println("Debut Fourier");
            MatriceComplexe fourier = Fourier.Fourier2D(f);
            System.out.println("Fin Fourier");
            fourier = Fourier.decroise(fourier);
            double phase[][] = fourier.getPhase();

            DoubleMatrix dialog = new DoubleMatrix(this,true,phase,"Fourier : Affichage de la phase");
            dialog.setVisible(true);
        }
        catch (CImageNGException ex)
        {
            System.out.println("Erreur CImageNG : " + ex.getMessage());
        }*/

    }

    private void jMenuItemFourierAfficherModuleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFourierAfficherModuleActionPerformed
        /*try
        {
            int f_int[][] = imageNG.getMatrice();
            double f[][] = new double[imageNG.getLargeur()][imageNG.getHauteur()];
            for(int i=0 ; i<imageNG.getLargeur() ; i++)
                for(int j=0 ; j<imageNG.getHauteur() ; j++) f[i][j] = (double)(f_int[i][j]);

            System.out.println("Debut Fourier");
            MatriceComplexe fourier = Fourier.Fourier2D(f);
            System.out.println("Fin Fourier");
            fourier = Fourier.decroise(fourier);
            double module[][] = fourier.getModule();

            DoubleMatrix dialog = new DoubleMatrix(this,true,module,"Fourier : Affichage du module");
            dialog.setVisible(true);
        }
        catch (CImageNGException ex)
        {
            System.out.println("Erreur CImageNG : " + ex.getMessage());
        }*/
    }

    private void jMenuItemCouleurPinceauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCouleurPinceauActionPerformed
        if (imageRGB != null)
        {
            Color newC = JColorChooser.showDialog(this,"Couleur du pinceau",couleurPinceauRGB);
            if (newC != null) couleurPinceauRGB = newC;
            observer.setCouleurPinceau(couleurPinceauRGB);
        }

        /*if (imageNG != null)
        {
            GreyScalePicker dialog = new GreyScalePicker(this,true,couleurPinceauNG);
            dialog.setVisible(true);
            couleurPinceauNG = dialog.getCouleur();
        }*/
    }


}
