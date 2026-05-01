package ui.implementation.views;

import domain.image.GrayScaleMatrix;
import domain.image.Image;
import jakarta.inject.Inject;
import presenters.DoubleMatrixPresenter;
import ui.implementation.components.image.ImagePanel;
import ui.implementation.components.intent.FileChooser;
import ui.interfaces.IDoubleMatrix;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.Objects;

import javax.swing.*;
import javax.swing.event.ChangeEvent;

/**
 * @author Jean-Marc Wagner, Laurent Crema
 */
public class DoubleMatrix extends Frame implements IDoubleMatrix
{
    private double normalizedWhite;
    private double normalizedBlack;

    private boolean isUpdating = false;

    private JTabbedPane modeTab;
    private ImagePanel modulePreviewContainer;
    private ImagePanel phasePreviewContainer;
    private ImagePanel realPreviewContainer;
    private ImagePanel imaginaryPreviewContainer;

    private JSlider whiteSlider;
    private JTextField whiteTextField;
    private JLabel maxLabel;

    private JSlider blackSlider;
    private JTextField blackTextField;
    private JLabel minLabel;

    private final DoubleMatrixPresenter presenter;

    @Inject
    public DoubleMatrix(DoubleMatrixPresenter presenter)
    {
        this.presenter = presenter;
        initComponents();
        presenter.setView(this);
        initValues();
    }

    private void initComponents() {

        modulePreviewContainer = new ImagePanel();
        phasePreviewContainer = new ImagePanel();
        realPreviewContainer = new ImagePanel();
        imaginaryPreviewContainer = new ImagePanel();

        blackSlider = buildSlider();
        whiteSlider = buildSlider();

        blackTextField = buildTextField(Color.BLACK, Color.BLACK);
        whiteTextField = buildTextField();

        minLabel = buildValueLabel();
        maxLabel = buildValueLabel();
        JLabel minValueText = new JLabel("Valeur MIN :");
        JLabel maxValueText = new JLabel("Valeur MAX :");

        JButton saveButton = new JButton(new ImageIcon(Objects.requireNonNull(getClass().getResource("/dd_27_p3.jpg"))));
        saveButton.addActionListener(this::saveImage);

        blackSlider.addChangeListener(this::onValueUpdate);
        whiteSlider.addChangeListener(this::onValueUpdate);

        blackTextField.addActionListener(this::jTextFieldNoirActionPerformed);
        whiteTextField.addActionListener(this::jTextFieldBlancActionPerformed);

        modeTab = buildModeTab();
        buildLayout(modeTab, maxValueText, minValueText, minLabel, maxLabel, saveButton);

        setTitle("Fourier");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
    }

    private void initValues(){
        modeTab.setSelectedIndex(1);

        blackSlider.setValue(0);
        whiteSlider.setValue(1000);
    }

    private JTabbedPane buildModeTab(){

        JTabbedPane modeTabs = new JTabbedPane();
        modeTabs.addTab("Module", modulePreviewContainer);
        modeTabs.addTab("Phase", phasePreviewContainer);
        modeTabs.addTab("Partie réelle", realPreviewContainer);
        modeTabs.addTab("Partie imaginaire", imaginaryPreviewContainer);
        modeTabs.addChangeListener(e -> {
            switch (modeTabs.getSelectedIndex()) {
                case 0 -> presenter.loadModule();
                case 1 -> presenter.loadPhase();
                case 2 -> presenter.loadReal();
                case 3 -> presenter.loadImaginary();
            }
        });
        return modeTabs;
    }

    private void buildLayout(JTabbedPane modeTab,
                             JLabel labelMax, JLabel labelMin,
                             JLabel valeurMin, JLabel valeurMax,
                             JButton saveButton) {

        JPanel rightPanel = new JPanel();
        GroupLayout rightLayout = new GroupLayout(rightPanel);
        rightPanel.setLayout(rightLayout);

        rightLayout.setHorizontalGroup(
                rightLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(whiteTextField, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                        .addGroup(rightLayout.createSequentialGroup()
                                .addComponent(blackSlider)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(whiteSlider)
                        )
                        .addComponent(blackTextField, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
        );

        rightLayout.setVerticalGroup(
                rightLayout.createSequentialGroup()
                        .addComponent(whiteTextField, GroupLayout.PREFERRED_SIZE,
                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(rightLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(blackSlider)
                                .addComponent(whiteSlider)
                        )
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(blackTextField, GroupLayout.PREFERRED_SIZE,
                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );

        // -------------------------
        // MAIN LAYOUT
        // -------------------------
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)

                        // TOP BAR
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(labelMin)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(valeurMin)
                                .addGap(50)
                                .addComponent(labelMax)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(valeurMax)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(saveButton)
                                .addContainerGap()
                        )

                        // MAIN ROW
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(modeTab, 400, 400, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rightPanel, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap()
                        )
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()

                        // TOP BAR
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(labelMin)
                                .addComponent(valeurMin)
                                .addComponent(labelMax)
                                .addComponent(valeurMax)
                                .addComponent(saveButton)
                        )

                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)

                        // MAIN CONTENT
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(modeTab)
                                .addComponent(rightPanel)
                        )
        );
    }

    private File chooseFile(){
        FileChooser chooser = new FileChooser("./");

        int dialogResult = chooser.showOpenDialog(this);
        if (dialogResult != JFileChooser.APPROVE_OPTION) return null;

        return chooser.getSelectedFile();
    }

    private void onValueUpdate(ChangeEvent e) {
        if (isUpdating) return;
        isUpdating = true;

        try {
            int black = blackSlider.getValue();
            int white = whiteSlider.getValue();

            int minGap = 1;
            if (white - black < minGap) {
                if (blackSlider.getValueIsAdjusting()) {
                    black = white - minGap;
                    blackSlider.setValue(black);
                } else {
                    white = black + minGap;
                    whiteSlider.setValue(white);
                }
            }

            normalizedBlack = (double) black / blackSlider.getMaximum();
            normalizedWhite = (double) white / whiteSlider.getMaximum();

            switch (modeTab.getSelectedIndex()) {
                case 0 -> presenter.clipModule(normalizedBlack, normalizedWhite);
                case 1 -> presenter.clipPhase(normalizedBlack, normalizedWhite);
                case 2 -> presenter.clipReal(normalizedBlack, normalizedWhite);
                case 3 -> presenter.clipImaginary(normalizedBlack, normalizedWhite);
            }

        } finally {
            isUpdating = false;
        }
    }


    @Override
    public void updateModule(GrayScaleMatrix source, GrayScaleMatrix clipped) {
        updatePreview(modulePreviewContainer, source, clipped);
    }

    @Override
    public void updatePhase(GrayScaleMatrix source, GrayScaleMatrix clipped) {
        updatePreview(phasePreviewContainer, source, clipped);
    }

    @Override
    public void updateReal(GrayScaleMatrix source, GrayScaleMatrix clipped) {
        updatePreview(realPreviewContainer, source, clipped);
    }

    @Override
    public void updateImaginary(GrayScaleMatrix source, GrayScaleMatrix clipped) {
        updatePreview(imaginaryPreviewContainer, source, clipped);
    }

    private void updatePreview(ImagePanel imagePanel, GrayScaleMatrix source, GrayScaleMatrix matrix) {
        try {
            imagePanel.loadImage(matrix);
            updateLabels(source.getMinValue(), source.getMaxValue());
            updateTextFields(
                    presenter.getValueAt(source, normalizedBlack),
                    presenter.getValueAt(source, normalizedWhite)
            );
        } catch (Exception e){
            displayErrorMessage("Oups !", e.getMessage());
        }
    }

    private void updateTextFields(double black, double white) {
        blackTextField.setText(String.format("%.5f", black));
        whiteTextField.setText(String.format("%.5f", white));
    }

    private void updateLabels(double black, double white) {
        minLabel.setText(String.format("%.5f", black));
        maxLabel.setText(String.format("%.5f", white));
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

    private void jTextFieldNoirActionPerformed(ActionEvent evt) {
//        double val = Double.parseDouble(jTextFieldNoir.getText());
//
//        double black = presenter.getBlackLevel();
//        double white = presenter.getWhiteLevel();
//
//        if (val < black)
//        {
//            jSliderNoir.setValue(0);
//            return;
//        }
//        if (val >= normalizedWhite)
//        {
//            jSliderNoir.setValue(jSliderBlanc.getValue()-1);
//            return;
//        }
//        int s = (int)((double)D*(val-black)/(white-black)+0.5);
//        jSliderNoir.setValue(s);

    }

    private void jTextFieldBlancActionPerformed(ActionEvent evt) {
//        double val = Double.parseDouble(jTextFieldBlanc.getText());
//        double black = presenter.getBlackLevel();
//        double white = presenter.getWhiteLevel();
//
//        if (val > white)
//        {
//            jSliderBlanc.setValue(D);
//            return;
//        }
//        if (val <= normalizedBlack)
//        {
//            jSliderBlanc.setValue(jSliderNoir.getValue()+1);
//            return;
//        }
//        int s = (int)((double)D*(val-black)/(white-black)+0.5);
//        jSliderBlanc.setValue(s);
    }
}
