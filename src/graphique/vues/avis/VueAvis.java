package graphique.vues.avis;

import graphique.modeles.Avis;

import javax.swing.*;
import java.awt.*;

/**
 * Représente le composant graphique (panneau) affichant un avis d'utilisateur.
 * Cette classe hérite de JPanel et organise de manière linéaire (FlowLayout)
 * les informations essentielles d'une critique : le pseudonyme de l'auteur, 
 * la note étoilée attribuée et le texte du commentaire.
 * * @author Ilyes
 */
public class VueAvis extends JPanel
{
    private JLabel pseudo;
    private JLabel note;
    private JLabel commentaire;

    /**
     * Constructeur de la vue d'un avis.
     * Extrait les données de l'objet métier Avis fourni pour initialiser 
     * les composants textuels, applique les styles graphiques et assemble le panneau 
     * avec un alignement à gauche.
     * * @param avis L'objet Avis contenant les données de la critique à afficher.
     */
    public VueAvis(Avis avis)
    {
        // creation des composants
        pseudo = new JLabel(avis.getAuteur());
        note = new JLabel("★ " + avis.getNote() + " / 10");
        commentaire = new JLabel(avis.getCommentaire());

        // parametrage du style des composants
        styleComposants();

        // configuration du panel
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        setBackground(new Color(15, 17, 21));

        // ajout des composants
        add(pseudo);
        add(note);
        add(commentaire);
    }
    /**
     * Configure et personnalise l'aspect visuel de chaque composant textuel.
     * Définit des polices spécifiques (gras ou normal), des tailles adaptées 
     * ainsi qu'un code couleur distinct pour le pseudo (blanc/clair), 
     * la note (jaune/or) et le commentaire (gris/discret).
     */
    public void styleComposants()
    {
        pseudo.setForeground(new Color(241, 245, 249));
        pseudo.setFont(pseudo.getFont().deriveFont(Font.BOLD, 14f));

        note.setForeground(new Color(250, 204, 21));
        note.setFont(note.getFont().deriveFont(Font.BOLD, 13f));

        commentaire.setForeground(new Color(148, 163, 184));
        commentaire.setFont(commentaire.getFont().deriveFont(Font.PLAIN, 13f));
    }
}