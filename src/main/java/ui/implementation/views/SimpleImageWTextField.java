package ui.implementation.views;

import domain.image.GrayScaleMatrix;
import domain.image.Image;
import jakarta.inject.Inject;
import presenters.components.TextInput;
import services.ImageService;
import ui.implementation.components.image.ImagePanel;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class SimpleImageWTextField extends Frame {
    private ImageService imageService;

    private ImagePanel imagePanel;

    private java.util.List<JTextField> textFields = new LinkedList<JTextField>();
    private java.util.List<JLabel> textFieldLabels = new LinkedList<JLabel>();

    // private JButton saveButton;

    @Inject
    public SimpleImageWTextField(ImageService imageService) {
        this.imageService = imageService;
    }

    public void setupComponents(String title, TextInput[] textInputs) {
        imagePanel = new ImagePanel();
        imagePanel.loadImage(this.imageService.getGrayScale());

        for (TextInput input : textInputs) {
            if (input == null) {continue;}

            JTextField textField = buildTextField(Color.BLACK);
            textField.setColumns(3);
            if (input.getValue() != null) {
                textField.setText(input.getValue());
            }
            textField.addActionListener(e -> {
                Image inputImage = this.imageService.getImage();

                // Appliquer le traitement avec la lambda passée en argument
                GrayScaleMatrix result = input.getProcessingTask().apply(inputImage);

                this.imagePanel.loadImage(result);
                this.imagePanel.repaint();
            });
            textFields.add(textField);

            JLabel label = buildLabel(input.getLabel());
            textFieldLabels.add(label);
        }

        // saveButton = new JButton(new ImageIcon(Objects.requireNonNull(getClass().getResource("/dd_27_p3.jpg"))));
        // saveButton.addActionListener(this::saveImage);

        // LAYOUT
        setupLayout();

        setTitle(title);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
    }

    public void setImage(GrayScaleMatrix image) {
        imagePanel.loadImage(image);
    }

    public String getTextFieldValue(int index) {
        if (index >= 0 && index < textFields.size()) {
            return this.textFields.get(index).getText();
        }
        return null;
    }

    private void setupLayout() {
        JPanel navBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));

        for (int fieldIndex = 0; fieldIndex < textFields.size(); ++fieldIndex) {
            JTextField textField = textFields.get(fieldIndex);
            JLabel label = textFieldLabels.get(fieldIndex);
            navBar.add(label);
            navBar.add(textField);
        }
        // navBar.add(saveButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(navBar, BorderLayout.NORTH);
        this.imagePanel.setPreferredSize(new Dimension(800, 600));
        getContentPane().add(this.imagePanel, BorderLayout.CENTER);

        setSize(new Dimension(1000, 800));
        setLocationRelativeTo(null);
    }

    // Doesn't save, it opens
    /*private void saveImage(ActionEvent evt) {

        File file = chooseFile();
        if (file != null) {
            try {
                this.imageService.saveImage(null);
                //image.enregistreFormatPNG(fichier);
            } catch (IOException ex) {
                System.err.println("Erreur I/O : " + ex.getMessage());
            }
        }
    }

    private File chooseFile(){
        FileChooser chooser = new FileChooser("./");

        int dialogResult = chooser.showOpenDialog(this);
        if (dialogResult != JFileChooser.APPROVE_OPTION) return null;

        return chooser.getSelectedFile();
    }*/


}
