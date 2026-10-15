package graphique.vues.reutiliser;

import javax.swing.*;
import java.awt.*;


/**
 * Un panneau personnalisé (conteneur) qui hérite de JPanel.
 * Cette classe utilise un BoxLayout horizontal pour aligner des composants sur une ligne.
 * Le panneau est transparent par défaut.
 *
 * @author Erick
 */
public class PanelLigne extends JPanel {

    /**
     * Crée une ligne contenant un label et un champ de texte.
     *
     * @param lbl le JLabel affiché à gauche
     * @param tfd le JTextField affiché à droite
     */
    public PanelLigne(JLabel lbl, JTextField tfd) {
        BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);
        setLayout(layout);
        add(lbl);
        add(Box.createHorizontalStrut(0));
        add(tfd);
        setOpaque(false);
        setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    /**
     * Crée une ligne contenant deux boutons.
     *
     * @param btn1 le premier bouton
     * @param btn2 le second bouton
     */
    public PanelLigne(JButton btn1, JButton btn2) {
        BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);
        setLayout(layout);
        add(btn1);
        add(Box.createHorizontalStrut(0));
        add(btn2);
        setOpaque(false);
        setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    /**
     * Crée une ligne contenant deux composants quelconques.
     *
     * @param cmpnt1 premier composant
     * @param cmpnt2 second composant
     */
    public PanelLigne(JComponent cmpnt1, JComponent cmpnt2) {
        BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);
        setLayout(layout);
        add(cmpnt1);
        add(Box.createHorizontalStrut(10));
        add(cmpnt2);
        setOpaque(false);
        setAlignmentX(Component.CENTER_ALIGNMENT);
    }
}