package graphique.modeles;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe abstraite représentant un média (film ou série).
 * Contient les informations communes : titre, genre, avis, etc.
 * @author Ilyes
 */
public abstract class Media implements Serializable {

	private static final long serialVersionUID = 1L;

	private String titre;
	private int annee;
	private int duree;
	private String dureeString;
	private ArrayList<String> genre;
	private String realisateur;
	private ArrayList<Avis> avis;
	private int nbAvis;
	private String chemin;

	public String getChemin() { return this.chemin; }
	public String getTitre() { return this.titre; }
	public int getAnnee() { return this.annee; }
	public int getDuree() { return this.duree; }
	public ArrayList<String> getGenre() { return this.genre; }
	public String getRealisateur() { return this.realisateur; }
	public String getDureeString() { return this.dureeString; }

	/**
	 * Constructeur d'un média.
	 *
	 * @param titre titre
	 * @param annee année
	 * @param duree durée en minutes
	 * @param genre genres
	 * @param realisateur réalisateur
	 */
	Media(String titre, int annee, int duree, ArrayList<String> genre, String realisateur) {

		this.titre = titre;
		this.annee = annee;
		this.duree = duree;
		this.genre = genre;
		this.realisateur = realisateur;

		this.dureeString = calculerDureeString();
		this.avis = new ArrayList<Avis>();
		this.nbAvis = 0;
		this.chemin = createChemin();
	}

	/**
	 * Ajoute un avis au média.
	 */
	public void ajouterAvis(Avis a) {
		avis.add(a);
		nbAvis++;
	}

	/**
	 * @return liste des avis
	 */
	public ArrayList<Avis> getAvis() {
		return avis;
	}

	/**
	 * Retire un avis du média.
	 */
	public void retirerAvis(Avis a) {
		avis.remove(a);
		nbAvis--;
	}

	/**
	 * Calcule la moyenne des notes.
	 */
	public double getScoreMoyen() {
		double total = 0;

		if (nbAvis == 0) {
			return 0;
		}

		for (int i = 0; i < avis.size(); i++) {
			total += avis.get(i).getNote();
		}

		return total / nbAvis;
	}

	public abstract void afficher();

	/**
	 * Convertit la durée en format HH:MM.
	 */
	public String calculerDureeString() {
		String dureeS = "";
		int heure = duree / 60;
		int minute = duree - 60 * heure;

		dureeS = "0" + String.valueOf(heure) + ":";

		if (minute < 10) {
			dureeS += "0";
		}

		dureeS += String.valueOf(minute);
		return dureeS;
	}

	/**
	 * Vérifie si le média appartient à un genre.
	 */
	public Boolean estDeGenre(String genre) {
		for (int i = 0; i < this.genre.size(); i++) {
			if (this.genre.get(i).equals(genre)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Génère le chemin de l'image associée au média.
	 */
	public String createChemin() {
		return "Images/" + this.titre.replace(" ", "") + ".jpg";
	}
}