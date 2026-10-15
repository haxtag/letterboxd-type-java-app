package graphique.vues.reutiliser;

import javax.swing.*;
import java.awt.*;

/**
 * Représente un bouton de bascule (Toggle) personnalisé pour la gestion de la liste "À voir".
 * Cette classe hérite de {@link JToggleButton} et adapte son aspect visuel en remplaçant 
 * l'affichage natif par deux icônes distinctes (sélectionné / non sélectionné) importées 
 * et redimensionnées automatiquement. Les bordures et fonds par défaut sont masqués 
 * pour offrir un rendu épuré de type "bouton icône".
 * * @author Erick
 */
public class AVoirBouton extends JToggleButton {
    /**
     * Constructeur du bouton "À voir".
     * Charge les deux états d'images (sélectionné et non sélectionné), applique le 
     * redimensionnement lissé, initialise l'état initial du bouton en fonction des listes 
     * de l'utilisateur, puis désactive les rendus de bordure, de focus et de fond système.
     * * @param isSelected true si le média associé est déjà présent dans la liste "À voir" de l'utilisateur, false sinon.
     */
    public AVoirBouton(boolean isSelected) {

        ImageIcon iconNonSelectionne =
                new ImageIcon("Images/AVoirPlusTard.png");
        ImageIcon iconSelectionne =
                new ImageIcon("Images/AVoirPlusTardSelect.png");

        setIcon(redimensionner(iconNonSelectionne));
        setSelectedIcon(redimensionner(iconSelectionne));

        setSelected(isSelected);

        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);
    }
    
    /**
     * Redimensionne de manière fluide une icône fournie afin de l'ajuster aux dimensions de l'interface.
     * Extrait l'image source pour lui appliquer une mise à l'échelle douce (Smooth) 
     * la contraignant à un format fixe de 20x20 pixels.
     * * @param icon L'icône d'origine au format ImageIcon à redimensionner.
     * @return Une nouvelle instance d'ImageIcon aux dimensions ajustées (20x20 pixels).
     */
    private ImageIcon redimensionner(ImageIcon icon) {
        Image img = icon.getImage().getScaledInstance(
                20, 20, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }
}