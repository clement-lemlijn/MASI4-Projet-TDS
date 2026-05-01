package ui.implementation.components.nav;

import app.state.GlobalFilteringType;
import domain.image.GrayScaleMatrix;
import domain.image.processing.histogram.Histogramme;
import domain.common.Mode;
import jakarta.inject.Inject;
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
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

/**
 * @author Jean-Marc Wagner, Laurent Crema
 */
public class NavBar extends JMenuBar implements INavBar {

    private JMenu imageMenu;
    private JMenu drawingMenu;
    private JMenu fourierMenu;
    private JMenu histogramMenu;
    private JMenu linearFilteringMenu;

    private NavPresenter presenter;

    @Inject
    public NavBar(NavPresenter presenter) {
        this.presenter = presenter;
        this.presenter.setView(this);
        initComponents();
    }

    private void initComponents(){

        imageMenu = new Menu("Image","/net_13_p1.jpg",
                new Menu("Nouvelle", "/file_65_p3.jpg",
                        new MenuItem("RGB", e -> this.createImage(e, new RGBImageCreatorDialog(new JFrame(),true))),
                        new MenuItem("NG", e -> this.createImage(e, new GreyScaleImageCreatorDialog(new JFrame(),true)))
                ),
                new MenuItem("Ouvrir...", "/folder_036_p3.jpg", this::loadImage),
                new MenuItem("Enregistrer sous...","/dd_27_p3.jpg", e -> {}),
                new MenuItem("Quitter","/cp_59_p3.jpg", this::quit)
        );

        drawingMenu = new Menu("Editer","/dd_28_p1.jpg",
                new Menu("Dessiner", "/display_14_p3.jpg",
                        new MenuItem("Couleur", this::chooseColor),
                        new Menu("Formes",
                                new MenuItem("Pixel", e -> this.setMode(e, Mode.DRAW_PIXEL)),
                                new MenuItem("Ligne", e -> this.setMode(e, Mode.DRAW_LINE)),
                                new MenuItem("Rectangle",e -> this.setMode(e, Mode.DRAW_RECTANGLE)),
                                new MenuItem("Cercle", e -> this.setMode(e, Mode.DRAW_CIRCLE))
                        )
                ),
                new Menu("Convertir",
                        new MenuItem("RGB", e -> {}),
                        new MenuItem("NG", e -> {})
                        )
        );

        fourierMenu = new Menu("Fourier","/cp_51_p1.jpg",
                new MenuItem("Afficher", "/cp_51_p3.jpg", this::displayFourier)
        );

        histogramMenu = new Menu("Histogramme","/report_48_hot.jpg",
                new MenuItem("Afficher", "/report_32_hot.jpg",this::displayHistogram)
        );

        linearFilteringMenu = new Menu("Filtrage linéaire", "/placeholder.jpg",
                new Menu("Global", "/placeholder.jpg",
                        new MenuItem("Filtre passe-bas idéal", e -> {this.displayGlobalLinearFilter(e, GlobalFilteringType.IDEAL_LOW_PASS);}),
                        new MenuItem("Filtre passe-haut idéal", e -> {this.displayGlobalLinearFilter(e, GlobalFilteringType.IDEAL_HIGH_PASS);}),
                        new MenuItem("Filtre passe-bas Butterworth", e -> {this.displayGlobalLinearFilter(e, GlobalFilteringType.BUTTERWORTH_LOW_PASS);}),
                        new MenuItem("Filtre passe-haut Butterworth", e -> {this.displayGlobalLinearFilter(e, GlobalFilteringType.BUTTERWORTH_HIGH_PASS);})
                )
        );

        this.add(imageMenu);
        this.add(drawingMenu);
        this.add(fourierMenu);
        this.add(histogramMenu);
        this.add(linearFilteringMenu);

        drawingMenu.setEnabled(false);
        fourierMenu.setEnabled(false);
        histogramMenu.setEnabled(false);
        linearFilteringMenu.setEnabled(false);
    }

    //####################################################

    private File chooseFile(){
        FileChooser chooser = new FileChooser("./");

        int dialogResult = chooser.showOpenDialog(this);
        if (dialogResult != JFileChooser.APPROVE_OPTION) return null;

        return chooser.getSelectedFile();
    }

    private void loadImage(ActionEvent e) {
        try
        {
            File file = chooseFile();
            if(file == null) return;
            presenter.loadImage(file);
            activeMenusRGB();
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
        dialog.setVisible(true);
        presenter.createImage(
                dialog.getRed(), dialog.getGreen(), dialog.getBlue(),
                dialog.getImageHeight(), dialog.getImageWidth());
        activeMenusRGB();
        activeMenusNG();
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
        drawingMenu.setEnabled(true);
        fourierMenu.setEnabled(true);
        histogramMenu.setEnabled(true);
        linearFilteringMenu.setEnabled(true);
    }

    private void activeMenusRGB()
    {
        drawingMenu.setEnabled(true);
        fourierMenu.setEnabled(false);
        histogramMenu.setEnabled(false);
        linearFilteringMenu.setEnabled(false);
    }

    //####################################################

    private void displayHistogram(ActionEvent e) {
        GrayScaleMatrix i_graymatrix = presenter.getImageGrayMatrix();
        int[] histo = Histogramme.Histogramme256(i_graymatrix);

        //Création du dataset
        XYSeries serie = new XYSeries("Histo");
        for(int i=0 ; i<256 ; i++) serie.add(i,histo[i]);
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(serie);

        // Creation du chart
        JFreeChart chart = ChartFactory.createHistogram("Histogramme","Niveaux de gris",
                "Nombre de pixels", dataset, PlotOrientation.VERTICAL,false,false,false);

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

    private void displayFourier(ActionEvent e) {
        try
        {
            presenter.goToFourier();
//          int f_int[][] = imageNG.getMatrice();
//          double f[][] = new double[imageNG.getLargeur()][imageNG.getHauteur()];
//          for(int i=0 ; i<imageNG.getLargeur() ; i++)
//              for(int j=0 ; j<imageNG.getHauteur() ; j++) f[i][j] = (double)(f_int[i][j]);
//
//          System.out.println("Debut Fourier");
//          MatriceComplexe fourier = Fourier.Fourier2D(f);
//          System.out.println("Fin Fourier");
//          fourier = Fourier.decroise(fourier);
//          double module[][] = fourier.getModule();
//
//          DoubleMatrix dialog = new DoubleMatrix(this,true,module,"Fourier : Affichage du module");
//          dialog.setVisible(true);
        }
        catch (Exception ex)
        {
            System.out.println("Erreur CImageNG : " + ex.getMessage());
        }
    }

    private void displayGlobalLinearFilter(ActionEvent e, GlobalFilteringType type) {
        presenter.goToGlobalFilter(type);
    }
}
