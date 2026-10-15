package graphique.modeles;

import java.util.ArrayList;

/**
 * Représente une série composée d'épisodes.
 * @author Ethan
 */
public class Serie extends Media {

	private static final long serialVersionUID = 3L;

	private int nbEpisodes;
	private ArrayList<Episode> episodes;

	public int getNbEpisodes() {
		return nbEpisodes;
	}

	public ArrayList<Episode> getEpisodes() {
		return this.episodes;
	}

	/**
	 * Constructeur d'une série.
	 */
	public Serie(String titre, int annee, int duree, ArrayList<String> genre, String realisateur) {
		super(titre, annee, duree, genre, realisateur);
		this.nbEpisodes = 0;
		this.episodes = new ArrayList<Episode>();
	}

	/**
	 * Ajoute un épisode à la série.
	 */
	public void ajouterEpisode(Episode e) {
		episodes.add(e);
		nbEpisodes++;
	}

	/**
	 * Affiche les informations de la série et ses épisodes.
	 */
	public void afficher() {
		System.out.println("\t SERIE");
		System.out.println("Titre du film :" + getTitre());
		System.out.println("Annee de sortie du film :" + getAnnee());
		System.out.println("Duree du film :" + getDuree());
		System.out.println("Genre du film :" + getGenre());
		System.out.println("Realisateur du film :" + getRealisateur());
		System.out.println("Nombre episodes :" + getNbEpisodes());
		System.out.println();

		for (int i = 0; i < nbEpisodes; i++) {
			episodes.get(i).afficher();
		}
	}
}