package graphique.vues.reutiliser;

import javax.swing.*;
import java.awt.*;

/**
 * Un champ de saisie de texte personnalisé qui hérite de JTextField.
 * Cette classe permet de créer rapidement un champ textuel stylisé avec une couleur 
 * de fond personnalisée, un texte écrit en blanc et sans les bordures d'origine de Swing.
 * * @author Erick
 */
public class TextField extends JTextField {
    /**
     * Crée un nouveau champ de saisie de texte personnalisé.
     * * @param columns La largeur initiale du champ, exprimée en nombre de colonnes (caractères).
     * @param color   La couleur de fond (Color) à appliquer au champ.
     */
    public TextField(int columns, Color color)
    {
        setBackground(color);
        setBorder(null);
        setColumns(columns);
        setForeground(Color.WHITE);
    }
}
