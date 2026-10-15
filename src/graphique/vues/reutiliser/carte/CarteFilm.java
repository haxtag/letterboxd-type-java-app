package graphique.vues.reutiliser.carte;

import graphique.modeles.Film;
import graphique.modeles.Utilisateur;
import graphique.vues.fenetrePrincipal.VuePrincipale;

/**
 * Représente la carte graphique spécifique dédiée à l'affichage d'un film.
 * Cette classe hérite de la structure globale et des fonctionnalités de la classe abstraite {@link Carte} 
 * pour adapter le composant visuel aux caractéristiques propres d'un modèle {@link Film}.
 * * @author Ethan
 */
public class CarteFilm extends Carte {
    public CarteFilm(Film film, Utilisateur utilisateur,VuePrincipale vuePrincipale) {
        super(film, utilisateur, vuePrincipale);
    }
}
