package ui.implementation.views;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    protected JSlider buildSlider() {
        return new JSlider(JSlider.VERTICAL);
    }

    protected JTextField buildTextField() {
        return buildTextField(Color.BLACK, null);
    }
    protected JTextField buildTextField(Color foreground) {
        return buildTextField(foreground, null);
    }

    protected JTextField buildTextField(Color foreground, Color background) {
        JTextField field = new JTextField();
        field.setFont(new Font("Tahoma", Font.BOLD, 11));
        field.setForeground(foreground);
        field.setHorizontalAlignment(JTextField.CENTER);
        if (background != null) field.setBackground(background);
        return field;
    }

    protected JLabel buildValueLabel() {
        return buildLabel("0.0", Color.BLUE, Font.BOLD);
    }

    protected JLabel buildLabel(String text) {
        return buildLabel(text, Color.BLACK);
    }

    protected JLabel buildLabel(String text,  Color foreground) {
        return buildLabel(text, foreground, Font.PLAIN);
    }

    protected JLabel buildLabel(String text, Color foreground, int fontStyle) {
        JLabel label = new JLabel(text);
        //noinspection MagicConstant
        label.setFont(new Font("Tahoma", fontStyle, 11));
        label.setForeground(foreground);
        return label;
    }
}
