package ui.implementation.views;

import domain.image.GrayScaleMatrix;
import domain.image.Image;
import infrastructure.ui.ImageMapper;
import jakarta.inject.Inject;
import presenters.DoubleMatrixPresenter;
import ui.implementation.components.image.ImagePanel;
import ui.implementation.components.intent.FileChooser;
import ui.interfaces.IDoubleMatrix;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;

/**
 * @author Jean-Marc Wagner, Laurent Crema
 */
public class DoubleMatrix extends JFrame implements IDoubleMatrix
{
    private double blanc;
    private double noir;
    
    private final int D = 512;
    
//    private double matrice[][];
//    private int M,N;
//    private int matrice_int[][];
    
    private ImagePanel imagePreviewContainer;

    private JButton jButton1;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabelValeurMax;
    private JLabel jLabelValeurMin;
    private JScrollPane jScrollPane1;
    private JSlider jSliderBlanc;
    private JSlider jSliderNoir;
    private JTextField jTextFieldBlanc;
    private JTextField jTextFieldNoir;

    private final DoubleMatrixPresenter presenter;

    @Inject
    public DoubleMatrix(DoubleMatrixPresenter presenter)
    {
        initComponents();
        this.presenter = presenter;
        presenter.setView(this);
        presenter.loadGrayScale();

//        matrice = new double[][]{{}};
//        M = matrice.length;
//        N = matrice[0].length;
//        matrice_int = new int[M][N];
//        try
//        {
//            image = new CImageNG(M,N,0);
//        }
//        catch (CImageNGException ex)
//        { System.out.println("Erreur CImageNG : " + ex.getMessage()); }

//        // ***** Recherche du Min *****
//        Min = matrice[0][0];
//        for(int i=0 ; i<M ; i++)
//            for(int j=0 ; j<N ; j++)
//                if (matrice[i][j] < Min) Min = matrice[i][j];
//        noir = Min;
//
//        // ***** Recherche du Max *****
//        Max = matrice[0][0];
//        for(int i=0 ; i<M ; i++)
//            for(int j=0 ; j<N ; j++)
//                if (matrice[i][j] > Max) Max = matrice[i][j];
//        blanc = Max;

//        jLabelValeurMin.setText("" + Min);
//        jTextFieldNoir.setText("" + noir);
//        jLabelValeurMax.setText("" + Max);
//        jTextFieldBlanc.setText("" + blanc);
    }

    private void initComponents() {
        jLabel1 = new JLabel();
        jLabelValeurMax = new JLabel();
        jLabel2 = new JLabel();
        jLabelValeurMin = new JLabel();
        jScrollPane1 = new JScrollPane();
        jSliderBlanc = new JSlider();
        jSliderNoir = new JSlider();
        jTextFieldBlanc = new JTextField();
        jTextFieldNoir = new JTextField();
        jButton1 = new JButton();

        imagePreviewContainer = new ImagePanel();
        jScrollPane1.setViewportView(imagePreviewContainer);

        jSliderNoir.setMaximum(D);
        jSliderNoir.setValue(0);
        jSliderBlanc.setMaximum(D);
        jSliderBlanc.setValue(D);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        jLabel1.setText("Valeur MAX :");

        jLabelValeurMax.setFont(new Font("Tahoma", 1, 11));
        jLabelValeurMax.setForeground(new Color(0, 0, 255));
        jLabelValeurMax.setText("0.0");

        jLabel2.setText("Valeur MIN :");

        jLabelValeurMin.setFont(new Font("Tahoma", 1, 11));
        jLabelValeurMin.setForeground(new Color(0, 0, 255));
        jLabelValeurMin.setText("0.0");

        jSliderBlanc.setForeground(new Color(255, 255, 255));
        jSliderBlanc.setMaximum(1000);
        jSliderBlanc.setOrientation(JSlider.VERTICAL);
        jSliderBlanc.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent evt) {
                jSliderBlancMouseReleased(evt);
            }
        });
        jSliderBlanc.addChangeListener(this::updateWhiteValue);

        jSliderNoir.setForeground(new Color(0, 1, 0));
        jSliderNoir.setMaximum(1000);
        jSliderNoir.setOrientation(JSlider.VERTICAL);
        jSliderNoir.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent evt) {
                jSliderNoirMouseReleased(evt);
            }
        });
        jSliderNoir.addChangeListener(this::updateBlackValue);

        jTextFieldBlanc.setFont(new Font("Tahoma", 1, 11));
        jTextFieldBlanc.setForeground(new Color(255, 0, 0));
        jTextFieldBlanc.setHorizontalAlignment(JTextField.CENTER);
        jTextFieldBlanc.addActionListener(this::jTextFieldBlancActionPerformed);

        jTextFieldNoir.setBackground(new Color(0, 0, 0));
        jTextFieldNoir.setFont(new Font("Tahoma", 1, 11));
        jTextFieldNoir.setForeground(new Color(255, 0, 0));
        jTextFieldNoir.setHorizontalAlignment(JTextField.CENTER);
        jTextFieldNoir.addActionListener(this::jTextFieldNoirActionPerformed);

        jButton1.setIcon(new ImageIcon(getClass().getResource("/dd_27_p3.jpg")));
        jButton1.addActionListener(this::saveImage);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelValeurMin)
                        .addGap(50, 50, 50)
                        .addComponent(jLabel1)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelValeurMax)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 225, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jSliderNoir, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                                .addComponent(jSliderBlanc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextFieldBlanc, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                            .addComponent(jTextFieldNoir, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabelValeurMin)
                        .addComponent(jLabel1)
                        .addComponent(jLabelValeurMax))
                    .addComponent(jButton1))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextFieldBlanc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(jSliderNoir, GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                            .addComponent(jSliderBlanc, GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldNoir, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE))
                .addContainerGap())
        );
        pack();

        setTitle("titre");
    }

    private File chooseFile(){
        FileChooser chooser = new FileChooser("./");

        int dialogResult = chooser.showOpenDialog(this);
        if (dialogResult != JFileChooser.APPROVE_OPTION) return null;

        return chooser.getSelectedFile();
    }

    @Override
    public void updateMatrix(GrayScaleMatrix matrix) {
        try {
            imagePreviewContainer.loadImage(matrix);
        } catch (Exception e){
            displayErrorMessage("Oups !", e.getMessage());
        }
    }

    public void updateBlackValue(ChangeEvent e){
        presenter.setBlackLevel(jSliderNoir.getValue());
    }

    public void updateWhiteValue(ChangeEvent e){
        presenter.setWhiteLevel(jSliderBlanc.getValue());
    }

    public void onBlackValueChanged(){
        int sNoir = jSliderNoir.getValue();
        int sBlanc = jSliderBlanc.getValue();

        double black = presenter.getBlackLevel();
        double white = presenter.getWhiteLevel();

        if (sBlanc > sNoir)
        {
            noir = black + (white-black)*(double)sNoir/(double)D;
            jTextFieldNoir.setText("" + noir);
        }
    }

    public void onWhiteValueChanged(){
        int sNoir = jSliderNoir.getValue();
        int sBlanc = jSliderBlanc.getValue();

        double black = presenter.getBlackLevel();
        double white = presenter.getWhiteLevel();

        if (sBlanc > sNoir)
        {
            blanc = black + (white-black)*(double)sBlanc/(double)D;
            jTextFieldBlanc.setText("" + blanc);
        }
    }

    private void saveImage(ActionEvent evt) {

        File file = chooseFile();
        if (file != null) {
            try {
                presenter.saveImage(null);
                //image.enregistreFormatPNG(fichier);
            } catch (IOException ex) {
                System.err.println("Erreur I/O : " + ex.getMessage());
            }
        }
    }

    private void displayErrorMessage(String title, String message) {
        JOptionPane.showMessageDialog(
                this,
                message,
                title,
                JOptionPane.ERROR_MESSAGE
        );
    }

    //####################################################

//    private void MiseAJourCImage()
//    {
//        int val;
//
//        for(int i=0 ; i<M ; i++)
//            for(int j=0 ; j<N ; j++)
//            {
//                if (matrice[i][j] >= blanc)
//                {
//                    matrice_int[i][j] = 255;
//                }
//                else
//                {
//                    if (matrice[i][j] <= noir)
//                    {
//                        matrice_int[i][j] = 0;
//                    }
//                    else
//                    {
//                        val = (int)((matrice[i][j] - noir)/(blanc-noir)*255+0.5);
//                        if (val > 255) val = 255;
//                        if (val < 0) val = 0;
//                        matrice_int[i][j] = val;
//                    }
//                }
//            }
//        try
//        {
//            image.setMatrice(matrice_int);
//        }
//        catch (CImageNGException ex)
//        {
//            System.out.println("Erreur CImageNG : " + ex.getMessage());
//        }
//    }
    
    private void jTextFieldNoirActionPerformed(ActionEvent evt) {
        double val = Double.parseDouble(jTextFieldNoir.getText());

        double black = presenter.getBlackLevel();
        double white = presenter.getWhiteLevel();

        if (val < black)
        {
            jSliderNoir.setValue(0);
            return;
        }
        if (val >= blanc)
        {
            jSliderNoir.setValue(jSliderBlanc.getValue()-1);
            return;
        }
        int s = (int)((double)D*(val-black)/(white-black)+0.5);
        jSliderNoir.setValue(s);

    }

    private void jTextFieldBlancActionPerformed(ActionEvent evt) {
        double val = Double.parseDouble(jTextFieldBlanc.getText());
        double black = presenter.getBlackLevel();
        double white = presenter.getWhiteLevel();

        if (val > white)
        {
            jSliderBlanc.setValue(D);
            return;
        }
        if (val <= noir)
        {
            jSliderBlanc.setValue(jSliderNoir.getValue()+1);
            return;
        }
        int s = (int)((double)D*(val-black)/(white-black)+0.5);
        jSliderBlanc.setValue(s);
    }

    private void jSliderNoirMouseReleased(MouseEvent evt) {
        if (jSliderNoir.getValue() >= jSliderBlanc.getValue())
            jSliderNoir.setValue(jSliderBlanc.getValue()-1);
    }

    private void jSliderBlancMouseReleased(MouseEvent evt) {
        if (jSliderBlanc.getValue() <= jSliderNoir.getValue()) 
            jSliderBlanc.setValue(jSliderNoir.getValue()+1); 
    }
}
