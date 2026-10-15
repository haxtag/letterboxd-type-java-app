package graphique.vues.fenetrePrincipal;
import graphique.modeles.FactoryMedia;
import graphique.modeles.Utilisateur;
import graphique.vues.accueil.VueIntroduction;
import jdk.jshell.execution.Util;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Fenêtre principale de l'application (Frame) servant de contrôleur graphique majeur.
 * Elle hérite de JFrame et utilise un gestionnaire de disposition en cartes 
 * (CardLayout) pour orchestrer, stocker et alterner l'affichage des différentes pages 
 * de l'application (Introduction, Accueil, etc.).
 * * @author Noham
 */
public class VuePrincipale extends JFrame
{
    private CardLayout card;
    public ArrayList<Component> composant;
    public Utilisateur utilisateur;
    public JPanel pageCourante;
    /**
     * Constructeur de la fenêtre principale.
     * Récupère l'utilisateur par défaut, configure le plein écran sans bordures système,
     * initialise la structure de navigation par cartes, puis instancie et affiche 
     * la première vue de l'application (VueIntroduction).
     */
    public VuePrincipale()
    {
        utilisateur= FactoryMedia.getU1();
        //parametrage de la frame
        setUndecorated(true); // retire la barre systeme
        setExtendedState(JFrame.MAXIMIZED_BOTH); // met la fenetre en grand ecran
        setTitle("LetterIUT");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        card = new CardLayout();
        composant = new ArrayList<Component>();
        setLayout(card);


        VueIntroduction intro = new VueIntroduction(this);
        pageCourante=intro;
        composant.add(intro);
        add(intro,"VueIntroduction");
        card.show(this.getContentPane(),"VueIntroduction");
        // affichage de la fenetre
        this.setVisible(true);
    }
    /**
     * Permet de changer la page affichée au centre de l'écran principal.
     * Met à jour la référence de la page courante, ajoute le panneau à la pile 
     * du CardLayout s'il s'agit d'une nouvelle vue, puis ordonne le 
     * basculement visuel vers celle-ci.
     * * @param page Le conteneur JPanel représentant la nouvelle page à afficher.
     * @param name L'identifiant textuel unique associé à cette vue pour le CardLayout.
     */
    public void afficherPage(JPanel page, String name)
    {
        pageCourante=page;
        if(!composant.contains(page))
        {
            composant.add(page);
            add(page, name);
        }
        card.show(this.getContentPane(),name);
    }
    /**
     * Récupère le profil de l'utilisateur associé à la session de cette fenêtre.
     * * @return L'objet Utilisateur actuellement connecté.
     */
    public Utilisateur getUtilisateur()
    {
        return this.utilisateur;
    }
    /**
     * Récupère le panneau graphique de la page en cours de visionnage.
     * * @return Le JPanel représentant l'écran actuellement actif.
     */
    public JPanel getPage()
    {
        return pageCourante;
    }


}


