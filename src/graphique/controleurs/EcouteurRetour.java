package graphique.controleurs;

import graphique.vues.accueil.VueAccueil;
import graphique.vues.fenetrePrincipal.VuePrincipale;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Contrôleur de retour vers l'accueil.
 * @author Ilyes
 */
public class EcouteurRetour implements ActionListener {

    public JButton retour;
    public VuePrincipale fenetre;

    public EcouteurRetour(JButton retour, VuePrincipale fenetre) {
        this.retour = retour;
        this.fenetre = fenetre;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == retour) {
            fenetre.afficherPage(new VueAccueil(fenetre), "VueAccueil");
        }
    }
}
