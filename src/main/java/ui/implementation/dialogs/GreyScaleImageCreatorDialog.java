package ui.implementation.dialogs;

import java.awt.Color;
import javax.swing.*;

public class GreyScaleImageCreatorDialog extends ImageCreatorDialog
{
    private JSlider slider;

    public GreyScaleImageCreatorDialog(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        createColorPicker();
    }

    @Override
    protected void createColorPicker() {
        slider = new JSlider();
        slider.setValue(getValue());
        slider.setMajorTickSpacing(255);
        slider.setMaximum(255);
        slider.setMinorTickSpacing(15);
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        slider.addChangeListener(this::jSliderStateChanged);
        colorPickerContainer.add(slider);
    }

    private int getValue(){
        return color.getRed();
    }

    private void jSliderStateChanged(javax.swing.event.ChangeEvent evt) {
        int value = slider.getValue();
        color = new Color(value, value, value);
        colorVisualizer.setBackground(color);
    }
}
