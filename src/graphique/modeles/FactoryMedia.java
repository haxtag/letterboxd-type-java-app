package graphique.modeles;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Factory responsable de la création, du chargement et de la sauvegarde
 * de tous les médias et utilisateurs du système.
 * @author Erick
 */
public class FactoryMedia {

	private static final String FICHIER_MEDIAS = "medias.dat";
	private static final String FICHIER_USERS  = "utilisateurs.dat";

	private static ArrayList<Media> tousLesMedias;
	private static ArrayList<Utilisateur> tousLesUtilisateurs;

	/**
	 * Charge les objets depuis les fichiers si existants,
	 * sinon crée les données par défaut.
	 */
	public static void creerTousLesObjets() {

		File fMedias = new File(FICHIER_MEDIAS);
		File fUsers  = new File(FICHIER_USERS);

		if (fMedias.exists() && fUsers.exists()) {
			try {
				System.out.println("Fichiers trouvés, chargement");

				FileInputStream fichierM = new FileInputStream(FICHIER_MEDIAS);
				ObjectInputStream flotM = new ObjectInputStream(fichierM);
				tousLesMedias = (ArrayList<Media>) flotM.readObject();
				flotM.close();

				FileInputStream fichierU = new FileInputStream(FICHIER_USERS);
				ObjectInputStream flotU = new ObjectInputStream(fichierU);
				tousLesUtilisateurs = (ArrayList<Utilisateur>) flotU.readObject();
				flotU.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			// Initialisation des données par défaut
			System.out.println("Fichiers absents, création des objet");
			creeMedia();
			creeUser();
			sauvegarder();
		}
	}

	/**
	 * Sauvegarde des médias et utilisateurs dans des fichiers binaires.
	 */
	public static void sauvegarder() {
		try {
			ObjectOutputStream flotM =
					new ObjectOutputStream(new FileOutputStream(FICHIER_MEDIAS));
			flotM.writeObject(tousLesMedias);
			flotM.close();

			ObjectOutputStream flotU =
					new ObjectOutputStream(new FileOutputStream(FICHIER_USERS));
			flotU.writeObject(tousLesUtilisateurs);
			flotU.close();

			System.out.println("Sauvegarde effectuée.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<Media> getTousLesMedias() {
		return tousLesMedias;
	}

	public static ArrayList<Utilisateur> getTousLesUtilisateurs() {
		return tousLesUtilisateurs;
	}

	/**
	 * Filtre les médias par genre
	 */
	public static ArrayList<Media> getTousLesMediasFiltrer(String genre) {
		ArrayList<Media> result = new ArrayList<>();

		for (Media m : tousLesMedias) {
			if (m.estDeGenre(genre)) {
				result.add(m);
			}
		}
		return result;
	}

	/**
	 * Retourne uniquement les films
	 */
	public static ArrayList<Media> getToutLesFilms() {
		ArrayList<Media> result = new ArrayList<>();

		for (Media m : tousLesMedias) {
			if (m.getClass() == Film.class) {
				result.add(m);
			}
		}
		return result;
	}

	/**
	 * Retourne uniquement les séries
	 */
	public static ArrayList<Media> getTouteLesSeries() {
		ArrayList<Media> result = new ArrayList<>();

		for (Media m : tousLesMedias) {
			if (m.getClass() == Serie.class) {
				result.add(m);
			}
		}
		return result;
	}
	public static void creeMedia()
	{
		ArrayList<String> avatarGenres = new ArrayList<>(List.of("Science-Fiction", "Aventure", "Action"));
		ArrayList<String> titanicGenres = new ArrayList<>(List.of("Romance", "Drame"));
		ArrayList<String> breakingBadGenres = new ArrayList<>(List.of("Crime", "Drame", "Thriller"));
		ArrayList<String> matrixGenres = new ArrayList<>(List.of("Science-Fiction", "Action"));
		ArrayList<String> gladiatorGenres = new ArrayList<>(List.of("Action", "Aventure", "Drame", "Historique"));
		ArrayList<String> forrestGumpGenres = new ArrayList<>(List.of("Drame", "Romance", "Comédie"));
		ArrayList<String> fightClubGenres = new ArrayList<>(List.of("Drame", "Thriller"));
		ArrayList<String> shawshankGenres = new ArrayList<>(List.of("Drame"));
		ArrayList<String> djangoGenres = new ArrayList<>(List.of("Western", "Drame"));
		ArrayList<String> pulpFictionGenres = new ArrayList<>(List.of("Crime", "Drame"));
		ArrayList<String> darkKnightGenres = new ArrayList<>(List.of("Action", "Crime", "Drame", "Super-héros"));
		ArrayList<String> interstellarGenres = new ArrayList<>(List.of("Science-Fiction", "Drame", "Aventure"));
		ArrayList<String> inceptionGenres = new ArrayList<>(List.of("Science-Fiction", "Action", "Thriller"));

		ArrayList<String> strangerThingsGenres = new ArrayList<>(List.of("Science-Fiction", "Horreur", "Drame"));

		// Création des médias
		Film f1 = new Film("Inception", 2010, 148, inceptionGenres, "Christopher Nolan");
		Film f2 = new Film("Interstellar", 2014, 169, interstellarGenres, "Christopher Nolan");
		Film f3 = new Film("The Dark Knight", 2008, 152, darkKnightGenres, "Christopher Nolan");
		Film f4 = new Film("Pulp Fiction", 1994, 154, pulpFictionGenres, "Quentin Tarantino");
		Film f5 = new Film("Django Unchained", 2012, 165, djangoGenres, "Quentin Tarantino");
		Film f6 = new Film("The Shawshank Redemption", 1994, 142, shawshankGenres, "Frank Darabont");
		Film f7 = new Film("Forrest Gump", 1994, 142, forrestGumpGenres, "Robert Zemeckis");
		Film f8 = new Film("Fight Club", 1999, 139, fightClubGenres, "David Fincher");
		Film f9 = new Film("The Matrix", 1999, 136, matrixGenres, "Lana et Lilly Wachowski");
		Film f10 = new Film("Gladiator", 2000, 155, gladiatorGenres, "Ridley Scott");
		Film f11 = new Film("Titanic", 1997, 195, titanicGenres, "James Cameron");
		Film f12 = new Film("Avatar", 2009, 162, avatarGenres, "James Cameron");

		Serie s1 = new Serie("Breaking Bad", 2008, 45, breakingBadGenres, "Vince Gilligan");
		Episode e1 = new Episode(1, 1, "Pilot", 58);
		Episode e2 = new Episode(1, 2, "Cat's in the Bag", 48);
		s1.ajouterEpisode(e1);
		s1.ajouterEpisode(e2);

		// Nouvelle série ajoutée
		Serie s2 = new Serie("Stranger Things", 2016, 50, strangerThingsGenres, "Duffer Brothers");
		s2.ajouterEpisode(new Episode(1, 1, "The Vanishing of Will Byers", 49));
		s2.ajouterEpisode(new Episode(1, 2, "The Weirdo on Maple Street", 55));

		tousLesMedias = new ArrayList<>(List.of(
				f1, f2, f3, f4, f5, f6,
				f7, f8, f9, f10, f11, f12,
				s1, s2
		));
	}

	public static void creeUser()
	{
		// Utilisateurs plus naturels
		Utilisateur u1 = new Utilisateur("Alex");
		Utilisateur u2 = new Utilisateur("Emma");
		Utilisateur u3 = new Utilisateur("Lucas");

		// Listes Alex
		u1.creerListe("Films vus", "vus");
		u1.creerListe("À voir absolument", "personnalisee");
		u1.creerListe("Mes coups de cœur", "personnalisee");

		ListeMedia vus = u1.getListes().get(0);
		ListeMedia aVoir = u1.getListes().get(1);
		ListeMedia favoris = u1.getListes().get(2);

		u1.ajouterMedia(tousLesMedias.getFirst(), vus);
		u1.ajouterMedia(tousLesMedias.getLast(), vus);
		u1.ajouterMedia(tousLesMedias.get(2), aVoir);
		u1.ajouterMedia(tousLesMedias.get(4), favoris);

		// Notes / avis Alex
		u1.noterMedia(tousLesMedias.getFirst(), 5, "Incroyable, un chef-d'œuvre !", "2025-01-10");
		u1.noterMedia(tousLesMedias.get(2), 5, "Nolan au sommet de son art", "2025-02-01");

		// Emma
		u2.creerListe("Films préférés", "personnalisee");
		u2.ajouterMedia(tousLesMedias.get(1), u2.getListes().getFirst());
		u2.noterMedia(tousLesMedias.get(1), 5, "Emotion incroyable", "2025-03-12");
		u2.noterMedia(tousLesMedias.get(3), 4, "Très stylé et intense", "2025-03-15");

		// Lucas
		u3.creerListe("Watchlist", "vus");
		u3.ajouterMedia(tousLesMedias.get(7), u3.getListes().getFirst());
		u3.noterMedia(tousLesMedias.get(7), 5, "Mind-blowing !", "2025-04-20");
		u3.noterMedia(tousLesMedias.get(8), 4, "Très bon univers", "2025-04-21");

		// Abonnements
		u1.suivre(u2);
		u1.suivre(u3);
		u2.suivre(u1);

		tousLesUtilisateurs = new ArrayList<>(List.of(u1, u2, u3));
	}
	
	public static Utilisateur getU1()
	{
		return tousLesUtilisateurs.getFirst();
	}
}