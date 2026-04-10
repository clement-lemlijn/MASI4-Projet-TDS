package ui.implementation.views;

import domain.CImage.CImageNG;
import domain.CImage.Exceptions.CImageNGException;
import presenters.DoubleMatrixPresenter;
import ui.implementation.components.image.ImagePanel;
import ui.interfaces.IDoubleMatrix;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class DoubleMatrix extends JFrame implements IDoubleMatrix
{
    private double Max;
    private double Min;
    private double blanc;
    private double noir;
    
    private final int D = 512;
    
    private double   matrice[][];
    private int      M,N;
    private CImageNG image;
    private int      matrice_int[][];
    
    private ImagePanel imagePanel;

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

    public DoubleMatrix()
    {
        initComponents();
        setTitle("titre");
        
        matrice = new double[][]{{}};
        M = matrice.length;
        N = matrice[0].length;
        matrice_int = new int[M][N];
        try 
        {
            image = new CImageNG(M,N,0);
        } 
        catch (CImageNGException ex) 
        { System.out.println("Erreur CImageNG : " + ex.getMessage()); }
        
        imagePanel = new ImagePanel();
        jScrollPane1.setViewportView(imagePanel);
        
        jSliderNoir.setMaximum(D);
        jSliderNoir.setValue(0);
        jSliderBlanc.setMaximum(D);
        jSliderBlanc.setValue(D);
        
        // ***** Recherche du Min *****
        Min = matrice[0][0];
        for(int i=0 ; i<M ; i++)
            for(int j=0 ; j<N ; j++)
                if (matrice[i][j] < Min) Min = matrice[i][j];
        jLabelValeurMin.setText("" + Min);
        noir = Min;
        jTextFieldNoir.setText("" + noir);
        
        // ***** Recherche du Max *****
        Max = matrice[0][0];
        for(int i=0 ; i<M ; i++)
            for(int j=0 ; j<N ; j++)
                if (matrice[i][j] > Max) Max = matrice[i][j];
        jLabelValeurMax.setText("" + Max);
        blanc = Max;
        jTextFieldBlanc.setText("" + blanc);
        
        MiseAJourCImage();
    }

    public void setPresenter(DoubleMatrixPresenter  doubleMatrixPresenter){

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
        jSliderBlanc.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
                jSliderBlancStateChanged(evt);
            }
        });

        jSliderNoir.setForeground(new Color(0, 1, 0));
        jSliderNoir.setMaximum(1000);
        jSliderNoir.setOrientation(JSlider.VERTICAL);
        jSliderNoir.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent evt) {
                jSliderNoirMouseReleased(evt);
            }
        });
        jSliderNoir.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
                jSliderNoirStateChanged(evt);
            }
        });

        jTextFieldBlanc.setFont(new Font("Tahoma", 1, 11));
        jTextFieldBlanc.setForeground(new Color(255, 0, 0));
        jTextFieldBlanc.setHorizontalAlignment(JTextField.CENTER);
        jTextFieldBlanc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jTextFieldBlancActionPerformed(evt);
            }
        });

        jTextFieldNoir.setBackground(new Color(0, 0, 0));
        jTextFieldNoir.setFont(new Font("Tahoma", 1, 11));
        jTextFieldNoir.setForeground(new Color(255, 0, 0));
        jTextFieldNoir.setHorizontalAlignment(JTextField.CENTER);
        jTextFieldNoir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jTextFieldNoirActionPerformed(evt);
            }
        });

        jButton1.setIcon(new ImageIcon(getClass().getResource("/dd_27_p3.jpg")));
        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

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
    }

    private void jButton1ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFileChooser choix = new JFileChooser();
        File fichier;

        choix.setCurrentDirectory(new File ("."));
        if (choix.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        {
                fichier = choix.getSelectedFile();
                if (fichier != null)
                {
                    try
                    {
                        image.enregistreFormatPNG(fichier);
                    }
                    catch (IOException ex)
                    {
                        System.err.println("Erreur I/O : " + ex.getMessage());
                    }
                }
        }
    }

    private void MiseAJourCImage()
    {
        int val;

        for(int i=0 ; i<M ; i++)
            for(int j=0 ; j<N ; j++)
            {
                if (matrice[i][j] >= blanc)
                {
                    matrice_int[i][j] = 255;
                }
                else
                {
                    if (matrice[i][j] <= noir)
                    {
                        matrice_int[i][j] = 0;
                    }
                    else
                    {
                        val = (int)((matrice[i][j] - noir)/(blanc-noir)*255+0.5);
                        if (val > 255) val = 255;
                        if (val < 0) val = 0;
                        matrice_int[i][j] = val;
                    }
                }
            }
        try 
        {
            image.setMatrice(matrice_int);
        } 
        catch (CImageNGException ex) 
        {
            System.out.println("Erreur CImageNG : " + ex.getMessage());
        }
    }
    
    private void jTextFieldNoirActionPerformed(ActionEvent evt) {
        double val = Double.parseDouble(jTextFieldNoir.getText());
        if (val < Min)
        {
            jSliderNoir.setValue(0);
            return;
        }
        if (val >= blanc)
        {
            jSliderNoir.setValue(jSliderBlanc.getValue()-1);
            return;
        }
        int s = (int)((double)D*(val-Min)/(Max-Min)+0.5);
        jSliderNoir.setValue(s);

    }

    private void jTextFieldBlancActionPerformed(ActionEvent evt) {
        double val = Double.parseDouble(jTextFieldBlanc.getText());
        if (val > Max)
        {
            jSliderBlanc.setValue(D);
            return;
        }
        if (val <= noir)
        {
            jSliderBlanc.setValue(jSliderNoir.getValue()+1);
            return;
        }
        int s = (int)((double)D*(val-Min)/(Max-Min)+0.5);
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

    private void jSliderBlancStateChanged(ChangeEvent evt) {
        int sNoir = jSliderNoir.getValue();
        int sBlanc = jSliderBlanc.getValue();
        
        if (sBlanc > sNoir)
        {
            blanc = Min + (Max-Min)*(double)sBlanc/(double)D;
            jTextFieldBlanc.setText("" + blanc);
        }
        
        MiseAJourCImage();
    }

    private void jSliderNoirStateChanged(ChangeEvent evt) {
        int sNoir = jSliderNoir.getValue();
        int sBlanc = jSliderBlanc.getValue();
        
        if (sBlanc > sNoir)
        {
            noir = Min + (Max-Min)*(double)sNoir/(double)D;
            jTextFieldNoir.setText("" + noir);
        }

        MiseAJourCImage();
    }
}
