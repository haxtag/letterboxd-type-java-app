package graphique.vues.fenetrePrincipal;

import graphique.modeles.Film;
import graphique.modeles.Utilisateur;
import graphique.vues.accueil.VueAccueil;
import graphique.vues.accueil.VueIntroduction;
import graphique.vues.reutiliser.Bouton;
import graphique.vues.reutiliser.LogoLabel;
import graphique.vues.utilisateurs.VueUtilisateur;
import jdk.jshell.execution.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Représente la barre de navigation supérieure ou latérale de l'application.
 * Cette classe hérite de JPanel et fournit une interface utilisateur sous forme 
 * de menu horizontal (via un FlowLayout) contenant le logo interactif et différents boutons 
 * permettant de naviguer entre l'accueil, les films, les séries, les utilisateurs et les favoris.
 * * @author Noham
 */
public class VueBarLateral extends JPanel {
    private VuePrincipale vuePrincipale;
    private Bouton logo;
    private Bouton accueil;
    private Bouton films;
    private Bouton series;
    private Bouton utilisateurs;
    private Utilisateur user;
    private Bouton favoris;
    private Color couleurBouton =new Color(15, 17, 21);

    /**
     * Constructeur de la barre latérale/supérieure.
     * Initialise la liaison avec la fenêtre principale, instancie les boutons de menu,
     * attache l'écouteur d'événements interne, charge l'illustration du logo 
     * et agence l'ensemble des composants au centre du panneau.
     * * @param vuePrincipale La VuePrincipale agissant comme contrôleur global du cadre graphique.
     */
    public VueBarLateral(VuePrincipale vuePrincipale)
    {
        // creation des composants
        this.vuePrincipale=vuePrincipale;
        this.user=vuePrincipale.getUtilisateur();
        logo=new Bouton();
        logo.setSize(300,300);
        accueil=new Bouton("Accueil",35,couleurBouton);
        films=new Bouton("Films",35,couleurBouton);
        series=new Bouton("Series",35,couleurBouton);
        utilisateurs=new Bouton("Utilisateurs",35,couleurBouton);
        favoris=new Bouton("Favoris",35,couleurBouton);



        //ajout de l'ecouteur au bouton
        EcouteurInterne ecouteur=new EcouteurInterne();
        logo.addActionListener(ecouteur);
        accueil.addActionListener(ecouteur);
        films.addActionListener(ecouteur);
        series.addActionListener(ecouteur);
        utilisateurs.addActionListener(ecouteur);
        favoris.addActionListener(ecouteur);





        // configuaration du panel
        setLayout(new FlowLayout(FlowLayout.CENTER,20,0));
        setLogo();
        setBackground(new Color(15, 17, 21));


        // ajout au panel
        add(logo);
        add(accueil);
        add(films);
        add(series);
        add(utilisateurs);
        add(favoris);

    }

    /**
     * Charge, redimensionne et applique l'icône du logo sur le bouton dédié.
     * Cette méthode extrait l'image depuis le répertoire des ressources, lui applique un
     * lissage de haute qualité pour la contraindre à un format de 100x100 pixels,
     * puis retire les bordures par défaut du bouton pour une intégration épurée.
     */
    public void setLogo()
    {
        logo.setText("");
        ImageIcon icon = new ImageIcon("images/logo.png");

        Image img = icon.getImage();
        Image imgResized = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);

        logo.setIcon(new ImageIcon(imgResized));
        logo.setBorder(null);
    }

    /**
     * Écouteur d'événements imbriqué (contrôleur interne) chargé d'intercepter les clics 
     * sur les composants de la barre de navigation.
     * Identifie le bouton cliqué pour ordonner à la fenêtre principale le chargement 
     * de la vue correspondante, en lui passant les filtres ou listes requis.
     */
    // class ecouteur imbriquer
    private class EcouteurInterne implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            Bouton bouton=(Bouton) e.getSource();
            if(bouton==logo) vuePrincipale.afficherPage(new VueIntroduction(vuePrincipale),"vueIntroduction");
            else if (bouton==accueil) vuePrincipale.afficherPage(new VueAccueil(vuePrincipale),"vueAccueil");
            else if (bouton==films) vuePrincipale.afficherPage(new VueAccueil(vuePrincipale,"Film"),"vueAccueilFilm");
            else if (bouton==series) vuePrincipale.afficherPage(new VueAccueil(vuePrincipale,"Serie"), "vueAccueilSerie");
            else if (bouton==favoris) vuePrincipale.afficherPage(new VueAccueil(vuePrincipale, vuePrincipale.getUtilisateur().getListe("Favoris") ), "vueFavoris" );
            else if (bouton==utilisateurs) vuePrincipale.afficherPage(new VueUtilisateur(vuePrincipale), "vueUtilisateur");
        }

    }



}
