package graphique.modeles;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Représente un utilisateur de la plateforme.
 * Un utilisateur peut créer des listes de médias, ajouter ou supprimer
 * des médias de ses listes, publier des avis et suivre d'autres utilisateurs.
 *
 * @author Noham
 */
public class Utilisateur implements Serializable {

	private static final long serialVersionUID = 7L;
	private String pseudo;
	private int nbListes;
	private int nbAvis;
	private int nbSuivis;
	private ArrayList<ListeMedia> listes;
	private ArrayList<Avis> avis;
	private ArrayList<Utilisateur> suivis;

	/**
	 * Construit un nouvel utilisateur.
	 * Deux listes sont automatiquement créées :
	 * "Favoris" et "A voir".
	 *
	 * @param pseudo pseudo de l'utilisateur
	 */
	public Utilisateur(String pseudo)
	{
		this.pseudo = pseudo;
		this.nbListes = 2;
		this.nbAvis = 0;
		this.nbSuivis = 0;
		this.listes = new ArrayList<ListeMedia>();
		this.avis = new ArrayList<Avis>();
		this.suivis = new ArrayList<Utilisateur>();

		listes.add(new ListeMedia("Favoris","personnelle"));
		listes.add(new ListeMedia("A voir", "personnelle"));
	}

	/**
	 * Recherche une liste à partir de son nom.
	 *
	 * @param nom nom de la liste recherchée
	 * @return la liste correspondante ou null si elle n'existe pas
	 */
	public ListeMedia getListe(String nom)
	{
		for(int i=0;i<listes.size();i++)
		{
			if(listes.get(i).getNom().equals(nom))
				return listes.get(i);
		}
		return null;
	}

	/**
	 * Retourne toutes les listes de l'utilisateur.
	 *
	 * @return les listes de médias de l'utilisateur
	 */
	public ArrayList<ListeMedia> getListes()
	{
		return this.listes;
	}

	/**
	 * Ajoute un média dans une liste donnée.
	 *
	 * @param media média à ajouter
	 * @param listeDeMedia liste dans laquelle ajouter le média
	 */
	public void ajouterMedia(Media media, ListeMedia listeDeMedia)
	{
		listeDeMedia.ajouterMedia(media);
	}

	/**
	 * Supprime un média d'une liste.
	 *
	 * @param media média à supprimer
	 * @param listeDeMedia liste concernée
	 */
	public void supprimerMedia(Media media, ListeMedia listeDeMedia)
	{
		ArrayList<Media> mediasListe = listeDeMedia.getMedias();

		for (int i = 0; i < mediasListe.size(); i++)
		{
			if (mediasListe.get(i) == media)
			{
				listeDeMedia.supprimerMedia(i);
				return;
			}
		}
	}

	/**
	 * Ajoute un avis sur un média.
	 * Un utilisateur ne peut publier qu'un seul avis par média.
	 *
	 * @param media média concerné
	 * @param note note attribuée
	 * @param commentaire commentaire associé à l'avis
	 * @param date date de publication de l'avis
	 * @return true si l'avis est ajouté, false si un avis existe déjà
	 */
	public boolean noterMedia(Media media, int note, String commentaire, String date)
	{
		for (int i=0;i<avis.size();i++)
		{
			if (avis.get(i).getMedia()==media)
			{
				return false;
			}
		}

		Avis avis = new Avis(media,note, commentaire, date, pseudo);
		this.avis.add(avis);
		media.ajouterAvis(avis);
		nbAvis++;

		return true;
	}

	/**
	 * Crée une nouvelle liste de médias.
	 *
	 * @param nom nom de la liste
	 * @param type type de la liste
	 */
	public void creerListe(String nom, String type)
	{
		listes.add(new ListeMedia(nom, type));
		nbListes++;
	}

	/**
	 * Permet de suivre un autre utilisateur.
	 *
	 * @param u utilisateur à suivre
	 */
	public void suivre(Utilisateur u)
	{
		suivis.add(u);
		nbSuivis++;
	}

	/**
	 * Affiche toutes les listes de l'utilisateur.
	 */
	public void afficherListes()
	{
		System.out.println("\t Listes de " + pseudo);

		for (int i = 0; i < nbListes; i++)
		{
			listes.get(i).afficher();
		}
	}

	/**
	 * Supprime l'avis associé à un média.
	 *
	 * @param m média concerné
	 */
	public void retirerAvis(Media m)
	{
		for (int i = 0; i < avis.size(); i++)
		{
			if (avis.get(i).getMedia() == m)
			{
				m.retirerAvis(avis.get(i));
				avis.remove(i);
				nbAvis--;
				break;
			}
		}
	}

	/**
	 * Retourne le pseudo de l'utilisateur.
	 *
	 * @return le pseudo
	 */
	public String getPseudo()
	{
		return pseudo;
	}

	/**
	 * Retourne tous les avis publiés par l'utilisateur.
	 *
	 * @return liste des avis
	 */
	public ArrayList<Avis> getAvis()
	{
		return avis;
	}

	/**
	 * Remplace l'avis existant pour un média par un nouvel avis.
	 *
	 * @param media média concerné
	 * @param avis nouvel avis
	 */
	public void changerAvis(Media media, Avis avis)
	{
		for (int i = 0; i < this.avis.size(); i++)
		{
			if (this.avis.get(i).getMedia() == media)
			{
				media.retirerAvis(this.avis.get(i));
				this.avis.remove(i);
				break;
			}
		}

		this.avis.add(avis);
		media.ajouterAvis(avis);
	}

	/**
	 * Retourne la liste des utilisateurs suivis.
	 *
	 * @return utilisateurs suivis
	 */
	public ArrayList<Utilisateur> getSuivis()
	{
		return suivis;
	}

	/**
	 * Affiche les informations de l'utilisateur :
	 * listes, avis et utilisateurs suivis.
	 */
	public void afficher()
	{
		System.out.println("\t UTILISATEUR : " + pseudo);

		System.out.println("\t Listes ");
		for (int i = 0; i < nbListes; i++)
		{
			listes.get(i).afficher();
		}

		System.out.println("\t Avis donnes");
		for (int i = 0; i < nbAvis; i++)
		{
			avis.get(i).afficher();
		}

		System.out.println("\t Personnes suivies ");
		for (int i = 0; i < nbSuivis; i++)
		{
			System.out.println(" \t " + suivis.get(i).getPseudo());
		}
	}
}
