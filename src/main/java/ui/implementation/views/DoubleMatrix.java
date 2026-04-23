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
    private double blanc;
    private double noir;

    private boolean isUpdating = false;
    private final int D = 512;

    private ImagePanel imagePreviewContainer;
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
        presenter.loadModule();
    }

    private void initComponents() {
        imagePreviewContainer = new ImagePanel();

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

        jSliderNoir.addChangeListener(this::updateBlackValue);
        jSliderNoir.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent evt) { jSliderNoirMouseReleased(evt); }
        });

        jSliderBlanc.addChangeListener(this::updateWhiteValue);
        jSliderBlanc.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent evt) { jSliderBlancMouseReleased(evt); }
        });

        jTextFieldNoir.addActionListener(this::jTextFieldNoirActionPerformed);
        jTextFieldBlanc.addActionListener(this::jTextFieldBlancActionPerformed);

        JScrollPane imagePane = new JScrollPane(imagePreviewContainer);

        buildLayout(imagePane, maxValueText, minValueText, minValueLabel, maxValueLabel, saveButton);

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

    private void buildLayout(JScrollPane scrollPane, JLabel labelMax, JLabel labelMin,
                             JLabel valeurMin, JLabel valeurMax, JButton button) {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(labelMin)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(valeurMin)
                                                .addGap(50, 50, 50)
                                                .addComponent(labelMax)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(valeurMax)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 225, Short.MAX_VALUE)
                                                .addComponent(button))
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
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
                                                .addComponent(labelMin)
                                                .addComponent(valeurMin)
                                                .addComponent(labelMax)
                                                .addComponent(valeurMax))
                                        .addComponent(button))
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
                                        .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE))
                                .addContainerGap())
        );
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


    public void updateBlackValue(ChangeEvent e) {
        if (isUpdating) return;
        isUpdating = true;
        try {
            if (jSliderNoir.getValue() >= jSliderBlanc.getValue())
                jSliderNoir.setValue(jSliderBlanc.getValue() - 1);
            double black = presenter.getBlackLevel();
            double white = presenter.getWhiteLevel();
            noir = black + (white - black) * (double) jSliderNoir.getValue() / jSliderNoir.getMaximum();
            jTextFieldNoir.setText(String.valueOf(noir));
        } finally {
            isUpdating = false;
        }
    }

    public void updateWhiteValue(ChangeEvent e) {
        if (isUpdating) return;
        isUpdating = true;
        try {
            if (jSliderBlanc.getValue() <= jSliderNoir.getValue())
                jSliderBlanc.setValue(jSliderNoir.getValue() + 1);
            double black = presenter.getBlackLevel();
            double white = presenter.getWhiteLevel();
            blanc = black + (white - black) * (double) jSliderBlanc.getValue() / jSliderBlanc.getMaximum();
            jTextFieldBlanc.setText(String.valueOf(blanc));
        } finally {
            isUpdating = false;
        }
    }

    private void jSliderNoirMouseReleased(MouseEvent evt) {
        presenter.setBlackLevel(jSliderNoir.getValue());
    }

    private void jSliderBlancMouseReleased(MouseEvent evt) {
        presenter.setWhiteLevel(jSliderBlanc.getValue());
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
}
