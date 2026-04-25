package ui.implementation.views;

import domain.image.GrayScaleMatrix;
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
import java.util.Objects;

import javax.swing.*;
import javax.swing.event.ChangeEvent;

/**
 * @author Jean-Marc Wagner, Laurent Crema
 */
public class DoubleMatrix extends JFrame implements IDoubleMatrix
{
    private double normalizedWhite;
    private double normalizedBlack;

    private boolean isUpdating = false;
    private int activePreviewIndex;
    private final int D = 255;

    private JTabbedPane modeTab;
    private ImagePanel modulePreviewContainer;
    private ImagePanel phasePreviewContainer;
    private ImagePanel realPreviewContainer;
    private ImagePanel imaginaryPreviewContainer;

    private JSlider jSliderBlanc;
    private JSlider jSliderNoir;
    private JTextField jTextFieldBlanc;
    private JTextField jTextFieldNoir;

    private final DoubleMatrixPresenter presenter;

    @Inject
    public DoubleMatrix(DoubleMatrixPresenter presenter)
    {
        this.presenter = presenter;
        initComponents();
        presenter.setView(this);
    }

    private void initComponents() {

        modulePreviewContainer = new ImagePanel();
        phasePreviewContainer = new ImagePanel();
        realPreviewContainer = new ImagePanel();
        imaginaryPreviewContainer = new ImagePanel();

        jSliderNoir  = buildSlider(0);
        jSliderBlanc = buildSlider(D);

        jTextFieldNoir  = buildTextField(Color.BLACK);
        jTextFieldBlanc = buildTextField(null);

        JLabel minValueLabel = buildValueLabel();
        JLabel maxValueLabel = buildValueLabel();
        JLabel minValueText = new JLabel("Valeur MIN :");
        JLabel maxValueText = new JLabel("Valeur MAX :");

        JButton saveButton = new JButton(new ImageIcon(Objects.requireNonNull(getClass().getResource("/dd_27_p3.jpg"))));
        saveButton.addActionListener(this::saveImage);

        jSliderNoir.addChangeListener(this::updateValues);
        jSliderNoir.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent evt) { onMouseReleased(evt); }
        });

        jSliderBlanc.addChangeListener(this::updateValues);
        jSliderBlanc.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent evt) { onMouseReleased(evt); }
        });

        jTextFieldNoir.addActionListener(this::jTextFieldNoirActionPerformed);
        jTextFieldBlanc.addActionListener(this::jTextFieldBlancActionPerformed);

        modeTab = buildModeTab();
        buildLayout(modeTab, maxValueText, minValueText, minValueLabel, maxValueLabel, saveButton);

        setTitle("titre");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
    }

    private JSlider buildSlider(int value) {
        JSlider slider = new JSlider(JSlider.VERTICAL, 0, D, value);
        slider.setForeground(value == 0 ? new Color(0, 1, 0) : Color.WHITE);
        return slider;
    }

    private JTextField buildTextField(Color background) {
        JTextField field = new JTextField();
        field.setFont(new Font("Tahoma", Font.BOLD, 11));
        field.setForeground(Color.RED);
        field.setHorizontalAlignment(JTextField.CENTER);
        if (background != null) field.setBackground(background);
        return field;
    }

    private JLabel buildValueLabel() {
        JLabel label = new JLabel("0.0");
        label.setFont(new Font("Tahoma", Font.BOLD, 11));
        label.setForeground(Color.BLUE);
        return label;
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
                        .addComponent(jTextFieldBlanc, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                        .addGroup(rightLayout.createSequentialGroup()
                                .addComponent(jSliderNoir)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jSliderBlanc)
                        )
                        .addComponent(jTextFieldNoir, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
        );

        rightLayout.setVerticalGroup(
                rightLayout.createSequentialGroup()
                        .addComponent(jTextFieldBlanc, GroupLayout.PREFERRED_SIZE,
                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(rightLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(jSliderNoir)
                                .addComponent(jSliderBlanc)
                        )
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldNoir, GroupLayout.PREFERRED_SIZE,
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

    public void updateLevels(){

    }

    public void updateValues(ChangeEvent e) {
        if (isUpdating) return;
        isUpdating = true;
        try {
            if (jSliderNoir.getValue() >= jSliderBlanc.getValue())
                jSliderNoir.setValue(jSliderBlanc.getValue() - 1);
//            double black = presenter.getBlackLevel();
//            double white = presenter.getWhiteLevel();
//            noir = black + (white - black) * (double) jSliderNoir.getValue() / jSliderNoir.getMaximum();
//            blanc = black + (white - black) * (double) jSliderBlanc.getValue() / jSliderBlanc.getMaximum();

            normalizedBlack = (double) jSliderNoir.getValue() / jSliderNoir.getMaximum();
            normalizedWhite = (double) jSliderBlanc.getValue() / jSliderBlanc.getMaximum();

            //Displayed real values from domain
//            jTextFieldNoir.setText(String.valueOf(noir));
//            jTextFieldNoir.setText(String.valueOf(blanc));
        } finally {
            isUpdating = false;
        }
    }

    private void onMouseReleased(MouseEvent e) {

        int index = modeTab.getSelectedIndex();

        switch (index) {
            case 0 -> presenter.setModuleLevel(normalizedBlack, normalizedWhite);
            case 1 -> presenter.setPhaseLevel(normalizedBlack, normalizedWhite);
            case 2 -> presenter.setRealLevel(normalizedBlack, normalizedWhite);
            case 3 -> presenter.setImaginary(normalizedBlack, normalizedWhite);
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

    @Override
    public void updateModule(GrayScaleMatrix matrix) {
        onPreviewUpdate(modulePreviewContainer, matrix);
    }

    @Override
    public void updatePhase(GrayScaleMatrix matrix) {
        onPreviewUpdate(phasePreviewContainer, matrix);
    }

    @Override
    public void updateReal(GrayScaleMatrix matrix) {
        onPreviewUpdate(realPreviewContainer, matrix);
    }

    @Override
    public void updateImaginary(GrayScaleMatrix matrix) {
        onPreviewUpdate(imaginaryPreviewContainer, matrix);
    }

    private void onPreviewUpdate(ImagePanel imagePanel, GrayScaleMatrix matrix) {
        try {
                imagePanel.loadImage(matrix);
                updateTextFields(matrix.getMinValue(), matrix.getMaxValue());
        } catch (Exception e){
            displayErrorMessage("Oups !", e.getMessage());
        }
    }

    private void updateTextFields(double black, double white) {
        jTextFieldNoir.setText(String.valueOf(black));
        jTextFieldNoir.setText(String.valueOf(white));
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
