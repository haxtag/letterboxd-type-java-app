package graphique.vues.reutiliser;

import javax.swing.*;
import java.awt.*;

/**
 * Un composant d'affichage d'image personnalisé qui hérite de {@link JLabel}.
 * Cette classe simplifie le chargement, l'affichage et le redimensionnement 
 * dynamique du logo de l'application (situé par défaut dans "images/logo.png").
 * * @author Erick
 */
public class LogoLabel extends JLabel {
    ImageIcon icon;
    /**
     * Crée un composant de logo par défaut.
     * Charge l'image du logo en mémoire sans l'affecter immédiatement au label. 
     * Ce constructeur est idéal si vous prévoyez de définir la taille plus tard 
     * via la méthode {@link #setSizeLogo(int, int)}.
     */
    public LogoLabel() {
        icon = new ImageIcon("images/logo.png");
    }
    /**
     * Crée un composant de logo et le redimensionne immédiatement aux dimensions indiquées.
     * * @param longueur La largeur ciblée pour le logo (en pixels).
     * @param largeur  La hauteur ciblée pour le logo (en pixels).
     */
    public LogoLabel(int longueur,int largeur) {
        icon = new ImageIcon("images/logo.png");
        Image img = icon.getImage().getScaledInstance(longueur, largeur, Image.SCALE_SMOOTH); // https://docs.oracle.com/javase/8/docs/api/javax/swing/ImageIcon.html
        ImageIcon resizedIcon = new ImageIcon(img);
        setIcon(resizedIcon);
    }
    /**
     * Redimensionne dynamiquement le logo et met à jour l'affichage du composant.
     * Cette méthode utilise un algorithme de lissage {@link Image#SCALE_SMOOTH} 
     * pour garantir une qualité d'image optimale après le redimensionnement.
     * * @param longueur La nouvelle largeur du logo (en pixels).
     * @param largeur  La nouvelle hauteur du logo (en pixels).
     */
    public void setSizeLogo(int longueur,int largeur)
    {
        Image img = icon.getImage().getScaledInstance(longueur, largeur, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(img);
        setIcon(resizedIcon);
    }
}