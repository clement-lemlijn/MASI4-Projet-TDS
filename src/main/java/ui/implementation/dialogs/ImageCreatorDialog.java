package ui.implementation.dialogs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public abstract class ImageCreatorDialog extends JDialog
{
    protected Color color;
    protected int height;
    protected int width;

    protected JButton confirmButton;
    protected JPanel colorVisualizer;
    protected JComponent colorPickerContainer;

    protected JTextField heightInput;
    protected JTextField widthInput;

    public ImageCreatorDialog(Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();

        color = new Color(255,255,255);
        width = 256;
        height = 256;
    }

    private void initComponents() {
        colorVisualizer = new JPanel();
        confirmButton = new JButton();
        widthInput = new JTextField();
        heightInput = new JTextField();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Choix de la couleur");
        setResizable(false);

        GroupLayout jPanelLayout = new GroupLayout(colorVisualizer);
        colorVisualizer.setBorder(BorderFactory.createCompoundBorder(null, BorderFactory.createLineBorder(new Color(255, 0, 0))));
        colorVisualizer.setLayout(jPanelLayout);

        jPanelLayout.setHorizontalGroup(
                jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 62, Short.MAX_VALUE)
        );
        jPanelLayout.setVerticalGroup(
                jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 55, Short.MAX_VALUE)
        );

        confirmButton.setText("Ok");
        confirmButton.addActionListener(this::onConfirmClicked);

        widthInput.setHorizontalAlignment(JTextField.CENTER);

        heightInput.setHorizontalAlignment(JTextField.CENTER);

        JLabel widthLabel = new JLabel("Largeur");
        JLabel heightLabel = new JLabel("Hauteur");

        colorPickerContainer = new JPanel(new BorderLayout());

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(widthLabel)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(widthInput, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
                                                .addGap(37, 37, 37)
                                                .addComponent(heightLabel)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(heightInput, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(51, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(colorVisualizer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addGap(25, 25, 25)
                                                .addComponent(colorPickerContainer)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 135, Short.MAX_VALUE)
                                                .addComponent(confirmButton, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                                                .addGap(26, 26, 26))))
        );

        layout.linkSize(SwingConstants.HORIZONTAL, heightInput, widthInput);

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(heightInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(heightLabel)
                                        .addComponent(widthInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(widthLabel)
                                )
                                .addGap(37, 37, 37)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(confirmButton, GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                                                .addComponent(colorPickerContainer, GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE))
                                        .addComponent(colorVisualizer, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        pack();
        colorVisualizer.setBackground(color);
        widthInput.setText("256");
        heightInput.setText("256");
    }

    protected void onColorChosen(ActionEvent e) {
        Color color = JColorChooser.showDialog(this,"Couleur de fond", this.color);
        if (color == null) return;
        this.color = color;
        colorVisualizer.setBackground(this.color);
    }

    protected void onConfirmClicked(ActionEvent e) {
        try
        {
            height = Integer.parseInt(widthInput.getText());
            width = Integer.parseInt(heightInput.getText());

            setVisible(false);
            dispose();
        } catch(NumberFormatException nfe)
        {
            JOptionPane.showMessageDialog(this,
                    "Hauteur et Largeur doivent etre entiers !","Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public Color getImageColor() {
        return color;
    }

    public int getImageWidth(){
        return width;
    }

    public int getImageHeight(){
        return height;
    }

    protected abstract void createColorPicker();
}
