package graphique.controleurs;

import graphique.modeles.ListeMedia;
import graphique.vues.accueil.VueAccueil;
import graphique.vues.fenetrePrincipal.VuePrincipale;
import graphique.vues.reutiliser.Bouton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Contrôleur permettant d'afficher une liste utilisateur (playlist, favoris, etc.)
 * depuis un bouton personnalisé.
 * @author Ethan
 */
public class EcouteurUtilisateur implements ActionListener {

    private Bouton bouton;
    private ListeMedia liste;
    private VuePrincipale vuePrincipale;

    public EcouteurUtilisateur(Bouton bouton, ListeMedia liste, VuePrincipale vuePrincipale) {
        this.bouton = bouton;
        this.liste = liste;
        this.vuePrincipale = vuePrincipale;
    }

    /**
     * Ouvre la vue correspondant à la liste sélectionnée.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == bouton) {

            vuePrincipale.afficherPage(
                    new VueAccueil(vuePrincipale, liste),
                    "vueListe_" + liste.getNom()
            );
        }
    }
}