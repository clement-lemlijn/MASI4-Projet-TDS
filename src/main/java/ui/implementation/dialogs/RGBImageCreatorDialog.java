package ui.implementation.dialogs;

import javax.swing.*;
import java.awt.*;

public class RGBImageCreatorDialog extends ImageCreatorDialog
{

    private JButton colorPickerButton;

    public RGBImageCreatorDialog(Frame parent, boolean modal)
    {
        super(parent, modal);
        createColorPicker();
    }

    @Override
    protected void createColorPicker() {
        colorPickerButton = new JButton();
        colorPickerButton.setText("Choisir");
        colorPickerButton.addActionListener(this::onColorChosen);
        colorPickerContainer.add(colorPickerButton);
    }
}
