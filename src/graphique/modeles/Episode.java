package graphique.modeles;

import java.io.Serializable;

/**
 * Représente un épisode d'une série.
 * @author Ethan
 */
public class Episode implements Serializable {

	private static final long serialVersionUID = 4L;

	private int numSaison;
	private int numEpisode;
	private String titre;
	private int duree;

	public int getNumSaison() {
		return this.numSaison;
	}

	public int getNumEpisode() {
		return this.numEpisode;
	}

	public String getTitre() {
		return this.titre;
	}

	public int getDuree() {
		return this.duree;
	}

	/**
	 * Constructeur d'un épisode
	 */
	Episode(int numSaison, int numEpisode, String titre, int duree) {
		this.numSaison = numSaison;
		this.numEpisode = numEpisode;
		this.titre = titre;
		this.duree = duree;
	}

	/**
	 * Affiche les informations de l'épisode
	 */
	public void afficher() {
		System.out.println(" Titre :" + titre);
		System.out.println(" Saison numero :" + numSaison);
		System.out.println(" Episode numero :" + numEpisode);
		System.out.println(" Duree :" + duree);
		System.out.println();
	}
}