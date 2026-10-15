package graphique.vues.accueil;

import graphique.modeles.FactoryMedia;
import graphique.vues.fenetrePrincipal.VuePrincipale;
import graphique.vues.reutiliser.Bouton;
import graphique.vues.reutiliser.LogoLabel;
import graphique.vues.reutiliser.PanelLigne;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Représente l'écran d'introduction et d'accueil initial de l'application.
 * Cette vue affiche le logo de l'application ainsi qu'un bouton de connexion permettant 
 * à l'utilisateur d'accéder au catalogue principal. Elle utilise un agencement 
 * vertical (BoxLayout) pour centrer ses composants.
 * * @author Noham
 */
public class VueIntroduction extends JPanel{
        LogoLabel logo;
        JButton connexion;
        /**
         * Constructeur de la vue d'introduction.
         * Initialise le style du panneau (couleur de fond, disposition), crée les composants 
         * visuels (logo et bouton), et configure un écouteur d'événements anonyme pour 
         * basculer vers la page d'accueil lors du clic sur le bouton de connexion.
         * * @param jframe La fenêtre principale VuePrincipale gérant le changement global de page.
         */
        public VueIntroduction(VuePrincipale jframe)
        {
                // parametrage du style du JPanel
                setBackground(new Color(15, 17, 21)); // couleur du logo (pour que ca soit coherent)
                setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));

                // creation des composant
                logo=new LogoLabel(500,500);
                connexion= new Bouton("Connexion");
                logo.setAlignmentX(Component.CENTER_ALIGNMENT);
                connexion.setAlignmentX(Component.CENTER_ALIGNMENT);

                // creation de l'ecouteur anonyme :
                ActionListener ecouteurAnonyme=new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                if (e.getSource() == connexion) {
                                        jframe.afficherPage(new VueAccueil(jframe),"VueAccueil");
                                }
                        }
                };
                // ajout de l'ecouteur au bouton:
                connexion.addActionListener(ecouteurAnonyme);
                // ajout des composant au panel
                add(logo);
                add(connexion);
        }
}
