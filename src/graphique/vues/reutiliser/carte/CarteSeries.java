package graphique.vues.reutiliser.carte;

import graphique.modeles.Serie;
import graphique.modeles.Utilisateur;
import graphique.vues.fenetrePrincipal.VuePrincipale;

import javax.swing.*;

/**
 * Représente la carte graphique spécifique dédiée à l'affichage d'une série.
 * Cette classe hérite de la structure globale de la classe abstraite {@link Carte} 
 * pour adapter le composant visuel aux caractéristiques propres d'un modèle {@link Serie}.
 * Elle modifie dynamiquement le libellé de la durée pour y afficher le nombre total d'épisodes.
 * * @author Ethan
 */
public class CarteSeries extends Carte {
    JLabel nbEpisodes;
    public CarteSeries(Serie serie, Utilisateur utilisateur, VuePrincipale vuePrincipale) {
        super(serie, utilisateur,vuePrincipale);
        String nbEpisodes = Integer.toString(serie.getNbEpisodes()) + " ep" ;
        duree.setText(nbEpisodes);
    }
}
