package ui.implementation.dialogs;

import domain.CImage.CImageRGB;
import domain.CImage.Exceptions.CImageRGBException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CreateImageDialog extends JDialog
{
    private Color color;
    private CImageRGB cImageRGB;


    public CreateImageDialog(Frame parent, boolean modal)
    {
        super(parent, modal);
        color = new Color(255,255,255);
        cImageRGB = null;
        
        initComponents();
    }

    private void initComponents() {
        jTextField1 = new JTextField();
        jPanel = new JPanel();
        jButtonOk = new JButton();
        jLabel1 = new JLabel();
        jTextFieldLargeur = new JTextField();
        jLabel2 = new JLabel();
        jTextFieldHauteur = new JTextField();
        jButtonChoisir = new JButton();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Choix du niveau de gris");
        setResizable(false);
        jPanel.setBorder(BorderFactory.createCompoundBorder(null, BorderFactory.createLineBorder(new Color(255, 0, 0))));
        GroupLayout jPanelLayout = new GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 62, Short.MAX_VALUE)
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 55, Short.MAX_VALUE)
        );

        jButtonOk.setText("Ok");
        jButtonOk.addActionListener(this::jButtonOkActionPerformed);

        jLabel1.setText("Largeur :");

        jTextFieldLargeur.setHorizontalAlignment(JTextField.CENTER);

        jLabel2.setText("Hauteur :");

        jTextFieldHauteur.setHorizontalAlignment(JTextField.CENTER);

        jButtonChoisir.setText("Choisir");
        jButtonChoisir.addActionListener(this::jButtonChoisirActionPerformed);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldLargeur, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(jLabel2)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldHauteur, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(51, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(jButtonChoisir)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 135, Short.MAX_VALUE)
                        .addComponent(jButtonOk, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))))
        );

        layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {jTextFieldHauteur, jTextFieldLargeur});

        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldHauteur, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldLargeur, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonOk, GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                        .addComponent(jButtonChoisir, GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE))
                    .addComponent(jPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pack();
        jPanel.setBackground(color);
        jTextFieldLargeur.setText("256");
        jTextFieldHauteur.setText("256");
    }

    private void jButtonChoisirActionPerformed(ActionEvent evt) {
            Color color = JColorChooser.showDialog(this,"Couleur de fond", this.color);
            if (color == null) return;
            this.color = color;
            jPanel.setBackground(this.color);
    }

    private void jButtonOkActionPerformed(ActionEvent evt) {
        try
        {
            int largeur = Integer.parseInt(jTextFieldLargeur.getText());
            int hauteur = Integer.parseInt(jTextFieldHauteur.getText());
            cImageRGB = new CImageRGB(largeur,hauteur, color);
            setVisible(false);
            dispose();
        } 
        catch (CImageRGBException ex) 
        {
            JOptionPane.showMessageDialog(this,ex.getMessage(),"Erreur RGB !!!", JOptionPane.ERROR_MESSAGE);
        }
        catch(NumberFormatException e)
        {
            JOptionPane.showMessageDialog(this,"Hauteur et Largeur doivent etre entiers !","Erreur RGB !!!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public CImageRGB getCImageRGB() { return cImageRGB; }

    private JButton jButtonChoisir;
    private JButton jButtonOk;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JPanel jPanel;
    private JTextField jTextField1;
    private JTextField jTextFieldHauteur;
    private JTextField jTextFieldLargeur;
    
}
