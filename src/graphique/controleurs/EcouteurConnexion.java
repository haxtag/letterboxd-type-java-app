package graphique.controleurs;

import graphique.vues.accueil.VueAccueil;
import graphique.vues.accueil.VueIntroduction;
import graphique.vues.fenetrePrincipal.VuePrincipale;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Contrôleur de connexion utilisateur.
 * Gère navigation entre écran connexion / accueil / introduction.
 * @author Ilyes
 */
public class EcouteurConnexion implements ActionListener {

    public JButton connexion;
    public JButton annuler;
    public VuePrincipale fenetre;

    public EcouteurConnexion(JButton connexion, JButton annuler, VuePrincipale fenetre) {
        this.connexion = connexion;
        this.annuler = annuler;
        this.fenetre = fenetre;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == connexion) {

            // Accès à l'application
            fenetre.afficherPage(new VueAccueil(fenetre), "VueAccueil");

        } else if (e.getSource() == annuler) {

            // Retour écran introduction
            fenetre.afficherPage(new VueIntroduction(fenetre), "VueIntroduction");
        }
    }
}