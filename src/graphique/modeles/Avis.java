package graphique.modeles;

import java.io.Serializable;

/**
 * Représente un avis donné par un utilisateur sur un média.
 * Contient une note, un commentaire, une date et un auteur.
 * @author Ilyes
 */
public class Avis implements Serializable {

	private static final long serialVersionUID = 5L;

	private int note;
	private String commentaire;
	private String dateAjout;
	private Media media;
	private String auteur;

	/*
	 * @return la note donnée au média
	 */
	public int getNote() {
		return this.note;
	}

	/**
	 * @return l'auteur de l'avis
	 */
	public String getAuteur() {
		return this.auteur;
	}

	/**
	 * @return le commentaire associé à l'avis
	 */
	public String getCommentaire() {
		return this.commentaire;
	}

	/**
	 * @return le média concerné par l'avis
	 */
	public Media getMedia() {
		return media;
	}

	/**
	 * Constructeur complet d'un avis
	 *
	 * @param media média concerné
	 * @param note note attribuée
	 * @param commentaire texte de l'avis
	 * @param dateAjout date de création
	 * @param auteur auteur de l'avis
	 */
	public Avis(Media media, int note, String commentaire, String dateAjout, String auteur) {
		this.media = media;
		this.note = note;
		this.commentaire = commentaire;
		this.dateAjout = dateAjout;
		this.auteur = auteur;
	}

	/**
	 * Constructeur avec auteur anonyme par défaut
	 */
	public Avis(Media media, int note, String commentaire, String dateAjout) {
		this.media = media;
		this.note = note;
		this.commentaire = commentaire;
		this.dateAjout = dateAjout;
		this.auteur = "anonyme";
	}

	/**
	 * Affiche l'avis dans la console
	 */
	public void afficher() {
		System.out.println("Titre : " + media.getTitre());
		System.out.println("Note : " + note + "/5");
		System.out.println("Commentaire : " + commentaire);
		System.out.println("Date : " + dateAjout);
	}
}