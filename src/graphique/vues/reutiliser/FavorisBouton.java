package graphique.vues.reutiliser;

import javax.swing.*;
import java.awt.*;

/**
 * Un bouton de bascule (Toggle Button) personnalisé pour la gestion des favoris.
 * Ce composant hérite de  JToggleButton et affiche une icône différente 
 * selon son état : une icône par défaut lorsque le bouton n'est pas sélectionné, 
 * et une icône distincte lorsqu'il est actif (sélectionné).
 * * @author Erick
 */
public class FavorisBouton extends JToggleButton {
    /**
     * Crée un nouveau bouton de favoris avec un état de sélection initial.
     * Ce constructeur charge, redimensionne et applique les icônes de favoris 
     * (sélectionné et non-sélectionné), puis rend le fond du bouton transparent 
     * pour ne laisser visible que l'image.
     * * @param selectionne true si le bouton doit être initialement coché 
     * (favori actif),  false sinon.
     */
    public FavorisBouton(boolean selectionne) {

        ImageIcon fav = redimensionner("Images/Favoris.png");
        ImageIcon favSelect = redimensionner("Images/FavorisSelect.png");

        setIcon(fav);
        setSelectedIcon(favSelect);

        setSelected(selectionne);

        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);
        setText("");
    }
    /**
     * Charge une image depuis le chemin spécifié et la redimensionne aux dimensions 
     * standard du bouton (20x20 pixels) avec un rendu fluide.
     * * @param chemin Le chemin d'accès relatif ou absolu vers le fichier image.
     * @return Une nouvelle instance de ImageIcon redimensionnée et prête à être affichée.
     */
    private ImageIcon redimensionner(String chemin) {
        ImageIcon icon = new ImageIcon(chemin);
        Image img = icon.getImage().getScaledInstance(
                20, 20, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }
}