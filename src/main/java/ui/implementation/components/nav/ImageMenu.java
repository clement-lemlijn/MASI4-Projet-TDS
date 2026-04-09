package ui.implementation.components.nav;

import ui.implementation.builders.MenuBuilder;
import ui.implementation.builders.MenuItemBuilder;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ImageMenu extends JMenuBar {

    private JMenuItem jMenuItemNouvelleNG;
    private JMenuItem jMenuItemNouvelleRGB;
    private JMenuItem jMenuItemOuvrirNG;
    private JMenuItem jMenuItemOuvrirRGB;
    private JMenu jMenuNouvelle;
    private JMenu jMenuOuvrir;
    private JMenuItem jMenuQuitter;
    private JMenuItem jMenuItemEnregistrerSous;

    public ImageMenu(){
        initComponents();
    }

    public void initComponents(){
        jMenuItemNouvelleRGB = new MenuItemBuilder()
                .setText("Image RGB")
                .setAction(this::jMenuItemNouvelleRGBActionPerformed)
                .build();

        jMenuItemNouvelleNG = new MenuItemBuilder()
                .setText("Image NG")
                .setAction(this::jMenuItemNouvelleNGActionPerformed)
                .build();

        jMenuNouvelle = new MenuBuilder()
                .setText("Nouvelle")
                .setIcon("/file_65_p3.jpg")
                .addItem(jMenuItemNouvelleRGB)
                .addItem(jMenuItemNouvelleNG)
                .build();

        jMenuItemOuvrirRGB = new MenuItemBuilder()
                .setText("Image RGB")
                .setAction(this::jMenuItemOuvrirRGBActionPerformed)
                .build();

        jMenuItemOuvrirNG = new MenuItemBuilder()
                .setText("Image NG")
                .setAction(this::jMenuItemOuvrirNGActionPerformed)
                .build();

        jMenuItemEnregistrerSous = new MenuItemBuilder()
                .setText("Enregistrer sous")
                .setIcon("/dd_27_p3.jpg")
                .setAction(this::jMenuItemEnregistrerSousActionPerformed)
                .build();

        jMenuQuitter = new MenuItemBuilder()
                .setText("Quitter")
                .setIcon("/cp_59_p3.jpg")
                .setAction(this::jMenuQuitterActionPerformed)
                .build();

        jMenuOuvrir = new MenuBuilder()
                .setText("Ouvrir")
                .setIcon("/folder_036_p3.jpg")
                .addItem(jMenuItemOuvrirRGB)
                .addItem(jMenuItemOuvrirNG)
                .build();

        this.text("Image")
                .setIcon("/net_13_p1.jpg")
                .addSubMenu(jMenuNouvelle)
                .addSubMenu(jMenuOuvrir)
                .addItem(jMenuItemEnregistrerSous)
                .addItem(jMenuQuitter)
                .build();
    }

    private void jMenuItemNouvelleRGBActionPerformed(ActionEvent evt) {
        /*NewRgbImage dialog = new NewRgbImage(this,true);
        dialog.setVisible(true);
        imageRGB = dialog.getCImageRGB();
        observer.setCImage(imageRGB);
        imageNG = null;
        activeMenusRGB();*/
    }

    private void jMenuQuitterActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    private void jMenuItemOuvrirRGBActionPerformed(java.awt.event.ActionEvent evt) {
        /*JFileChooser choix = new JFileChooser();
        File fichier;

        choix.setCurrentDirectory(new File ("."));
        if (choix.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            fichier = choix.getSelectedFile();
            if (fichier != null)
            {
                try
                {
                    imageRGB = new CImageRGB(fichier);
                    observer.setCImage(imageRGB);
                    imageNG = null;
                    activeMenusRGB();
                }
                catch (IOException ex)
                {
                    System.err.println("Erreur I/O : " + ex.getMessage());
                }
            }
        }*/
    }

    private void jMenuItemNouvelleNGActionPerformed(ActionEvent evt) {
        /*NewGreyScaleImage dialog = new NewGreyScaleImage(this,true);
        dialog.setVisible(true);
        imageNG = dialog.getCImageNG();
        observer.setCImage(imageNG);
        imageRGB = null;
        activeMenusNG();*/
    }

    private void jMenuItemOuvrirNGActionPerformed(ActionEvent evt) {
        /*JFileChooser choix = new JFileChooser();
        File fichier;

        choix.setCurrentDirectory(new File ("."));
        if (choix.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            fichier = choix.getSelectedFile();
            if (fichier != null)
            {
                try
                {
                    imageNG = new CImageNG(fichier);
                    observer.setCImage(imageNG);
                    imageRGB = null;
                    activeMenusNG();
                }
                catch (IOException ex)
                {
                    System.err.println("Erreur I/O : " + ex.getMessage());
                }
            }
        }*/
    }

    private void jMenuItemEnregistrerSousActionPerformed(ActionEvent evt) {
        /*JFileChooser choix = new JFileChooser();
        File fichier;

        choix.setCurrentDirectory(new File ("."));
        if (choix.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            fichier = choix.getSelectedFile();
            if (fichier != null)
            {
                try
                {
                    if (imageRGB != null) imageRGB.enregistreFormatPNG(fichier);
                    if (imageNG != null) imageNG.enregistreFormatPNG(fichier);
                }
                catch (IOException ex)
                {
                    System.err.println("Erreur I/O : " + ex.getMessage());
                }
            }
        }*/
    }


}
