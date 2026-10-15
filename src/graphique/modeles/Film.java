package graphique.modeles;

import java.util.ArrayList;

/**
 * Représente un film, spécialisation de Media.
 * @author Ethan
 */
public class Film extends Media {

	private static final long serialVersionUID = 2L;

	/**
	 * Constructeur d'un film.
	 *
	 * @param titre titre du film
	 * @param annee année de sortie
	 * @param duree durée en minutes
	 * @param genre liste des genres
	 * @param realisateur réalisateur du film
	 */
	public Film(String titre, int annee, int duree, ArrayList<String> genre, String realisateur) {
		super(titre, annee, duree, genre, realisateur);
	}

	/**
	 * Affiche les informations du film dans la console.
	 */
	public void afficher() {
		System.out.println("\t FILM");
		System.out.println("Titre du film :" + getTitre());
		System.out.println("Annee de sortie du film :" + getAnnee());
		System.out.println("Duree du film :" + getDuree());
		System.out.println("Genre du film :" + getGenre());
		System.out.println("Realisateur du film :" + getRealisateur());
	}
}