package graphique.vues.accueil;

import graphique.modeles.ListeMedia;
import graphique.vues.fenetrePrincipal.VueBarLateral;
import graphique.vues.fenetrePrincipal.VuePrincipale;
import graphique.vues.reutiliser.ScrollMedia;

import javax.swing.*;
import java.awt.*;

/**
 * Représente le panneau principal d'accueil et de navigation de l'application.
 * Cette classe hérite de JPanel et organise graphiquement l'interface en y
 * intégrant une barre latérale (ou supérieure) de navigation et une zone défilante de médias.
 * Elle dispose de plusieurs constructeurs permettant d'adapter dynamiquement le titre et le 
 * contenu affiché (accueil personnalisé, filtrage par type, ou contenu d'une liste spécifique).
 * * @author Noham
 */

public class VueAccueil extends JPanel
{
    private final Color COULEUR_FOND  = new Color(15, 17, 21);
    private final Color COULEUR_TEXTE = new Color(241, 245, 249);

    private ScrollMedia media;
    private VueBarLateral barLateral;
    /**
     * Constructeur par défaut de la page d'accueil générale.
     * Initialise la vue avec un message de bienvenue personnalisé contenant le pseudonyme 
     * de l'utilisateur actuellement connecté.
     * * @param frame La fenêtre principale VuePrincipale permettant la gestion globale de l'application.
     */
    public VueAccueil(VuePrincipale frame)
    {
        media      = new ScrollMedia(frame);
        barLateral = new VueBarLateral(frame);
        construire("Heureux de vous revoir, " + frame.getUtilisateur().getPseudo());
    }
    /**
     * Constructeur permettant d'afficher l'accueil filtré par un type de média précis.
     * Le titre du panneau s'adapte automatiquement ("Films" ou "Séries") en fonction du filtre fourni.
     * * @param frame  La fenêtre principale VuePrincipale pour le contrôle des vues.
     * @param filtre Le critère de filtrage appliqué (ex: "Film" pour afficher uniquement les films).
     */
    public VueAccueil(VuePrincipale frame, String filtre)
    {
        media      = new ScrollMedia(filtre, frame);
        barLateral = new VueBarLateral(frame);
        construire(filtre.equals("Film") ? "Films" : "Séries");
    }
    /**
     * Constructeur permettant d'afficher le contenu exclusif d'une liste de médias donnée.
     * Le titre du panneau prend automatiquement le nom de la liste spécifiée.
     * * @param frame      La fenêtre principale VuePrincipale pour le contrôle des vues.
     * @param mediaListe La ListeMedia personnalisée dont on souhaite afficher le contenu.
     */
    public VueAccueil(VuePrincipale frame, ListeMedia mediaListe)
    {
        media      = new ScrollMedia(mediaListe, frame);
        barLateral = new VueBarLateral(frame);
        construire(mediaListe.getNom());
    }

    /**
     * Méthode interne centralisant la construction graphique et l'agencement du panneau.
     * Elle applique le gestionnaire de disposition (Layout), configure les arrière-plans, 
     * met en forme le titre textuel et assemble la barre de navigation avec la zone des médias.
     * * @param titre Le texte mis en valeur à afficher comme en-tête de la zone de contenu.
     */
    private void construire(String titre)
    {
        setLayout(new BorderLayout());
        setBackground(COULEUR_FOND);

        JPanel contenu = new JPanel();
        contenu.setLayout(new BoxLayout(contenu, BoxLayout.Y_AXIS));
        contenu.setBackground(COULEUR_FOND);

        JLabel label = new JLabel(titre);
        label.setForeground(COULEUR_TEXTE);
        label.setFont(label.getFont().deriveFont(Font.BOLD, 22f));
        label.setBorder(BorderFactory.createEmptyBorder(20, 30, 10, 30));
        label.setAlignmentX(LEFT_ALIGNMENT);

        contenu.add(label);
        contenu.add(media);

        add(barLateral, BorderLayout.NORTH);
        add(contenu, BorderLayout.CENTER);
    }
}