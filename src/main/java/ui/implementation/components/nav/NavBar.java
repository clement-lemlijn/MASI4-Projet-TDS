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
import ui.implementation.builders.MenuBuilder;
import ui.implementation.builders.MenuItemBuilder;

import javax.swing.*;
import java.awt.*;
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



        this.add(jMenuImage);

        buttonGroupDessiner = new ButtonGroup();
//        jMenuImage = new JMenu("Image");
//        jMenuNouvelle = new JMenu();
//        jMenuItemNouvelleRGB = new JMenuItem();
//        jMenuItemNouvelleNG = new JMenuItem();
//        jMenuOuvrir = new JMenu();
//        jMenuItemOuvrirRGB = new JMenuItem();
//        jMenuItemOuvrirNG = new JMenuItem();
//        jMenuItemEnregistrerSous = new JMenuItem();
//        jMenuQuitter = new JMenuItem();
        jSeparator1 = new JSeparator();
        jMenuDessiner = new JMenu();
        jMenuItemCouleurPinceau = new JMenuItem();
        jSeparator2 = new JSeparator();
        jCheckBoxMenuItemDessinerPixel = new JCheckBoxMenuItem();
        jCheckBoxMenuItemDessinerLigne = new JCheckBoxMenuItem();
        jCheckBoxMenuItemDessinerRectangle = new JCheckBoxMenuItem();
        jCheckBoxMenuItemDessinerRectanglePlein = new JCheckBoxMenuItem();
        jCheckBoxMenuItemDessinerCercle = new JCheckBoxMenuItem();
        jCheckBoxMenuItemDessinerCerclePlein = new JCheckBoxMenuItem();
        jMenuFourier = new JMenu();
        jMenuFourierAfficher = new JMenu();
        jMenuItemFourierAfficherModule = new JMenuItem();
        jMenuItemFourierAfficherPhase = new JMenuItem();
        jMenuItemFourierAfficherPartieReelle = new JMenuItem();
        jMenuItemFourierAfficherPartieImaginaire = new JMenuItem();
        jMenuHistogramme = new JMenu();
        jMenuHistogrammeAfficher = new JMenuItem();

//        jMenuImage.setIcon(new ImageIcon(getClass().getResource("/net_13_p1.jpg")));
//        jMenuImage.setText("Image");

//        jMenuNouvelle.setIcon(new ImageIcon(getClass().getResource("/file_65_p3.jpg"))); // NOI18N
//        jMenuNouvelle.setText("Nouvelle");

//        jMenuItemNouvelleRGB.setText("Image RGB");
//        jMenuItemNouvelleRGB.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                jMenuItemNouvelleRGBActionPerformed(evt);
//            }
//        });
//
//        jMenuNouvelle.add(jMenuItemNouvelleRGB);

//        jMenuItemNouvelleNG.setText("Image NG");
//        jMenuItemNouvelleNG.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                jMenuItemNouvelleNGActionPerformed(evt);
//            }
//        });
//        jMenuNouvelle.add(jMenuItemNouvelleNG);

 //       jMenuImage.add(jMenuNouvelle);

//        jMenuOuvrir.setIcon(new ImageIcon(getClass().getResource("/folder_036_p3.jpg"))); // NOI18N
//        jMenuOuvrir.setText("Ouvrir");

//        jMenuItemOuvrirRGB.setText("Image RGB");
//        jMenuItemOuvrirRGB.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                jMenuItemOuvrirRGBActionPerformed(evt);
//            }
//        });
//        jMenuOuvrir.add(jMenuItemOuvrirRGB);

//        jMenuItemOuvrirNG.setText("Image NG");
//        jMenuItemOuvrirNG.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                jMenuItemOuvrirNGActionPerformed(evt);
//            }
//        });
//        jMenuOuvrir.add(jMenuItemOuvrirNG);

//        jMenuImage.add(jMenuOuvrir);

//        jMenuItemEnregistrerSous.setIcon(new ImageIcon(getClass().getResource("/dd_27_p3.jpg"))); // NOI18N
//        jMenuItemEnregistrerSous.setText("Enregistrer sous...");
//        jMenuItemEnregistrerSous.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                jMenuItemEnregistrerSousActionPerformed(evt);
//            }
//        });
//        jMenuImage.add(jMenuItemEnregistrerSous);

//        jMenuQuitter.setIcon(new ImageIcon(getClass().getResource("/cp_59_p3.jpg"))); // NOI18N
//        jMenuQuitter.setText("Quitter");
//        jMenuQuitter.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                jMenuQuitterActionPerformed(evt);
//            }
//        });
//        jMenuImage.add(jMenuQuitter);

        jMenuImage.add(jSeparator1);

        jMenuDessiner.setIcon(new ImageIcon(getClass().getResource("/dd_28_p1.jpg"))); // NOI18N
        jMenuDessiner.setText("Dessiner");

        jMenuItemCouleurPinceau.setIcon(new ImageIcon(getClass().getResource("/display_14_p3.jpg"))); // NOI18N
        jMenuItemCouleurPinceau.setText("Couleur");
        jMenuItemCouleurPinceau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCouleurPinceauActionPerformed(evt);
            }
        });
        jMenuDessiner.add(jMenuItemCouleurPinceau);
        jMenuDessiner.add(jSeparator2);

        jCheckBoxMenuItemDessinerPixel.setText("Pixel");
        jCheckBoxMenuItemDessinerPixel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItemDessinerPixelActionPerformed(evt);
            }
        });
        jMenuDessiner.add(jCheckBoxMenuItemDessinerPixel);

        jCheckBoxMenuItemDessinerLigne.setText("Ligne");
        jCheckBoxMenuItemDessinerLigne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItemDessinerLigneActionPerformed(evt);
            }
        });
        jMenuDessiner.add(jCheckBoxMenuItemDessinerLigne);

        jCheckBoxMenuItemDessinerRectangle.setText("Rectangle");
        jCheckBoxMenuItemDessinerRectangle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItemDessinerRectangleActionPerformed(evt);
            }
        });
        jMenuDessiner.add(jCheckBoxMenuItemDessinerRectangle);

        jCheckBoxMenuItemDessinerRectanglePlein.setText("Rectangle plein");
        jCheckBoxMenuItemDessinerRectanglePlein.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItemDessinerRectanglePleinActionPerformed(evt);
            }
        });
        jMenuDessiner.add(jCheckBoxMenuItemDessinerRectanglePlein);

        jCheckBoxMenuItemDessinerCercle.setText("Cercle");
        jCheckBoxMenuItemDessinerCercle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItemDessinerCercleActionPerformed(evt);
            }
        });
        jMenuDessiner.add(jCheckBoxMenuItemDessinerCercle);

        jCheckBoxMenuItemDessinerCerclePlein.setText("Cercle plein");
        jCheckBoxMenuItemDessinerCerclePlein.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItemDessinerCerclePleinActionPerformed(evt);
            }
        });
        jMenuDessiner.add(jCheckBoxMenuItemDessinerCerclePlein);

        this.add(jMenuDessiner);

        jMenuFourier.setIcon(new ImageIcon(getClass().getResource("/cp_51_p1.jpg"))); // NOI18N
        jMenuFourier.setText("Fourier");

        jMenuFourierAfficher.setIcon(new ImageIcon(getClass().getResource("/cp_51_p3.jpg"))); // NOI18N
        jMenuFourierAfficher.setText("Afficher");

        jMenuItemFourierAfficherModule.setText("Module");
        jMenuItemFourierAfficherModule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFourierAfficherModuleActionPerformed(evt);
            }
        });
        jMenuFourierAfficher.add(jMenuItemFourierAfficherModule);

        jMenuItemFourierAfficherPhase.setText("Phase");
        jMenuItemFourierAfficherPhase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFourierAfficherPhaseActionPerformed(evt);
            }
        });
        jMenuFourierAfficher.add(jMenuItemFourierAfficherPhase);

        jMenuItemFourierAfficherPartieReelle.setText("Partie Reelle");
        jMenuItemFourierAfficherPartieReelle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFourierAfficherPartieReelleActionPerformed(evt);
            }
        });
        jMenuFourierAfficher.add(jMenuItemFourierAfficherPartieReelle);

        jMenuItemFourierAfficherPartieImaginaire.setText("Partie Imaginaire");
        jMenuItemFourierAfficherPartieImaginaire.addActionListener(this::jMenuItemFourierAfficherPartieImaginaireActionPerformed);
        jMenuFourierAfficher.add(jMenuItemFourierAfficherPartieImaginaire);

        jMenuFourier.add(jMenuFourierAfficher);

        this.add(jMenuFourier);

        jMenuHistogramme.setIcon(new ImageIcon(getClass().getResource("/report_48_hot.jpg"))); // NOI18N
        jMenuHistogramme.setText("Histogramme");

        jMenuHistogrammeAfficher.setIcon(new ImageIcon(getClass().getResource("/report_32_hot.jpg"))); // NOI18N
        jMenuHistogrammeAfficher.setText("Afficher");
        jMenuHistogrammeAfficher.addActionListener(this::jMenuHistogrammeAfficherActionPerformed);
        jMenuHistogramme.add(jMenuHistogrammeAfficher);

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
