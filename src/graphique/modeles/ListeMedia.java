package graphique.modeles;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Représente une liste de médias créée par un utilisateur.
 * Exemple : Favoris, À voir, etc.
 * @author Noham
 */
public class ListeMedia implements Serializable {

	private static final long serialVersionUID = 6L;

	private String nom;
	private String type;
	private int nbMedias;
	private ArrayList<Media> medias;

	public ArrayList<Media> getMedias() {
		return this.medias;
	}

	public String getNom() {
		return nom;
	}

	public String getType() {
		return type;
	}

	/**
	 * Constructeur d'une liste de médias.
	 * 
	 * @param nom nom de la liste
	 * @param type type de la liste (personnelle, vus, etc.)
	 */
	public ListeMedia(String nom, String type) {
		this.nom = nom;
		this.type = type;
		this.nbMedias = 0;
		this.medias = new ArrayList<Media>();
	}

	/**
	 * Ajoute un média dans la liste.
	 *
	 * @param m média à ajouter
	 */
	public void ajouterMedia(Media m) {
		medias.add(m);
		nbMedias++;
	}

	/**
	 * Supprime un média à l'indice donné.
	 *
	 * @param i index du média
	 */
	public void supprimerMedia(int i) {
		if (i >= 0 && i < nbMedias) {
			medias.remove(i);
			nbMedias--;
		} else {
			System.out.println("Indice invalide : " + i);
		}
	}

	/**
	 * Affiche le contenu de la liste.
	 */
	public void afficher() {
		System.out.println("\t Liste : " + nom + " (" + type + ") \t");

		for (int i = 0; i < nbMedias; i++) {
			medias.get(i).afficher();
		}
	}

	/**
	 * Retourne les médias filtrés par genre.
	 *
	 * @param genre genre recherché
	 * @return liste filtrée
	 */
	public ArrayList<Media> getMediaGenre(String genre) {
		ArrayList<Media> mediaFiltrer = new ArrayList<>();

		for (int i = 0; i < medias.size(); i++) {
			if (medias.get(i).getGenre().contains(genre)) {
				mediaFiltrer.add(medias.get(i));
			}
		}
		return mediaFiltrer;
	}
}