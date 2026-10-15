package graphique.controleurs;

import graphique.modeles.Media;
import graphique.modeles.Utilisateur;
import graphique.vues.fenetrePrincipal.VuePrincipale;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Contrôleur des cartes média :
 * - gestion favoris
 * - gestion "à voir"
 * - navigation vers détail média
 * @author Ilyes Ethan Erick
 */
public class EcouteurCarte implements ActionListener {

    private JToggleButton favoris;
    private JToggleButton aVoir;
    private JButton accesFilm;
    private Media media;
    private Utilisateur utilisateur;
    private VuePrincipale fenetre;

    public EcouteurCarte(
            JToggleButton favoris,
            JToggleButton aVoir,
            JButton accesFilm,
            Media media,
            Utilisateur utilisateur,
            VuePrincipale fenetre) {

        this.favoris = favoris;
        this.aVoir = aVoir;
        this.accesFilm = accesFilm;
        this.media = media;
        this.utilisateur = utilisateur;
        this.fenetre = fenetre;
    }

    /**
     * Constructeur utilisé uniquement pour la gestion des listes (sans navigation UI)
     */
    public EcouteurCarte(
            JToggleButton favoris,
            JToggleButton aVoir,
            Media media,
            Utilisateur utilisateur) {

        this.favoris = favoris;
        this.aVoir = aVoir;
        this.media = media;
        this.utilisateur = utilisateur;
        this.accesFilm = null;
        this.fenetre = null;
    }

    /**
     * Gère :
     * - ajout/suppression favoris
     * - ajout/suppression "à voir"
     * - ouverture détail média
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        Object bouton = e.getSource();

        if (bouton == favoris) {

            if (favoris.isSelected()) {
                utilisateur.ajouterMedia(media, utilisateur.getListe("Favoris"));
            } else {
                utilisateur.supprimerMedia(media, utilisateur.getListe("Favoris"));
            }

        } else if (bouton == aVoir) {

            if (aVoir.isSelected()) {
                utilisateur.ajouterMedia(media, utilisateur.getListe("A voir"));
            } else {
                utilisateur.supprimerMedia(media, utilisateur.getListe("A voir"));
            }

        } else if (bouton == accesFilm) {

            // Navigation vers la page détail du média
            fenetre.afficherPage(
                    new graphique.vues.media.VueDetailMedia(fenetre, media, utilisateur),
                    "VueDetailMedia_" + media.getTitre()
            );
        }
    }
}