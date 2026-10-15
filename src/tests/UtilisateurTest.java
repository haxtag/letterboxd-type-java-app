package tests;

import graphique.modeles.Utilisateur;
import graphique.modeles.ListeMedia;
import graphique.modeles.Media;
import graphique.modeles.Film;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurTest {

    // Objets utilisés dans les tests
    private Utilisateur utilisateur;
    private ListeMedia liste;
    private Media media;

    public static void main(String[] args) {
        UtilisateurTest test = new UtilisateurTest();

        System.out.println("=== Tests UtilisateurTest ===\n");

        // Test ajout d’un média
        System.out.println("-- ajouterMedia --");
        test.ajouterMedia_MediaEtListe_MediaPresentDansListe();
        System.out.println();

        // Test suppression d’un média
        System.out.println("-- supprimerMedia --");
        test.supprimerMedia_MediaPresent_MediaAbsentDansListe();
        test.supprimerMedia_MediaAbsent_ListeInchangee();
        System.out.println();

        // Test notation des médias
        System.out.println("-- noterMedia --");
        test.noterMedia_NoteValide_AvisAjouteAUtilisateurEtMedia();
        test.noterMedia_NoteInvalideInferieure_AucunAvisAjoute();
        test.noterMedia_NoteInvalideSuperieure_AucunAvisAjoute();
        System.out.println();

        // Test création de liste
        System.out.println("-- creerListe --");
        test.creerListe_NomEtType_ListeAjoutee();
        System.out.println();

        // Test suivi d’un autre utilisateur
        System.out.println("-- suivre --");
        test.suivre_Utilisateur_UtilisateurPresentDansSuivis();
        System.out.println();

        // Test suppression d’un avis
        System.out.println("-- retirerAvis --");
        test.retirerAvis_AvisPresent_AvisAbsentDeUtilisateurEtMedia();
        System.out.println();

        System.out.println("=== All tests passed. ===");
    }

    // Initialise les objets avant chaque test
    private void setup() {
        ArrayList<String> inceptionGenres = new ArrayList<>(List.of("Science-Fiction", "Action", "Thriller"));
        utilisateur = new Utilisateur("alice");
        liste = new ListeMedia("Ma liste", "Films");
        media = new Film("Inception", 2010, 148, inceptionGenres, "Nolan");
    }

    // Méthode ajouterMedia
    // Vérifie que le média est bien ajouté à la liste
    private void ajouterMedia_MediaEtListe_MediaPresentDansListe() {
        setup();
        utilisateur.ajouterMedia(media, liste);
        assert liste.getMedias().contains(media) : "Le media devrait etre dans la liste.";
        System.out.println("ajouterMedia_MediaEtListe_MediaPresentDansListe passed.");
    }

    // Méthode supprimerMedia
    // Vérifie que le média est supprimé s’il est présent
    private void supprimerMedia_MediaPresent_MediaAbsentDansListe() {
        setup();
        liste.ajouterMedia(media);
        utilisateur.supprimerMedia(media, liste);
        assert !liste.getMedias().contains(media) : "Le media devrait avoir ete supprime de la liste.";
        System.out.println("supprimerMedia_MediaPresent_MediaAbsentDansListe passed.");
    }

    // Vérifie que rien ne change si le média n’existe pas dans la liste
    private void supprimerMedia_MediaAbsent_ListeInchangee() {
        setup();
        ArrayList<String> avatarGenres = new ArrayList<String>(List.of("Science-Fiction", "Aventure", "Action"));
        Media autreMedia = new Film("Avatar", 2009, 162, avatarGenres, "Cameron");
        liste.ajouterMedia(media);
        utilisateur.supprimerMedia(autreMedia, liste);
        assert liste.getMedias().size() == 1 : "La liste ne devrait pas avoir change.";
        System.out.println("supprimerMedia_MediaAbsent_ListeInchangee passed.");
    }

    // Méthode noterMedia
    // Vérifie qu’un avis est bien ajouté à l’utilisateur et au média pour une note valide
    private void noterMedia_NoteValide_AvisAjouteAUtilisateurEtMedia() {
        setup();
        utilisateur.noterMedia(media, 4, "Super film", "2024-01-01");
        assert utilisateur.getAvis().size() == 1 : "L'utilisateur devrait avoir 1 avis.";
        assert media.getAvis().size() == 1 : "Le media devrait avoir 1 avis.";
        assert utilisateur.getAvis().get(0).getNote() == 4 : "La note devrait etre 4.";
        System.out.println("noterMedia_NoteValide_AvisAjouteAUtilisateurEtMedia passed.");
    }

    // Vérifie qu’aucun avis n’est ajouté si la note est inférieure à 1
    private void noterMedia_NoteInvalideInferieure_AucunAvisAjoute() {
        setup();
        utilisateur.noterMedia(media, 0, "Mauvais", "2024-01-01");
        assert utilisateur.getAvis().size() == 0 : "Aucun avis ne devrait etre ajoute pour une note de 0.";
        assert media.getAvis().size() == 0 : "Le media ne devrait pas avoir d'avis.";
        System.out.println("noterMedia_NoteInvalideInferieure_AucunAvisAjoute passed.");
    }

    // Vérifie qu’aucun avis n’est ajouté si la note est supérieure à 5
    private void noterMedia_NoteInvalideSuperieure_AucunAvisAjoute() {
        setup();
        utilisateur.noterMedia(media, 6, "Trop bien", "2024-01-01");
        assert utilisateur.getAvis().size() == 0 : "Aucun avis ne devrait etre ajoute pour une note de 6.";
        assert media.getAvis().size() == 0 : "Le media ne devrait pas avoir d'avis.";
        System.out.println("noterMedia_NoteInvalideSuperieure_AucunAvisAjoute passed.");
    }

    // Méthode creerListe
    // Vérifie que la liste est bien créée avec le bon nom et type
    private void creerListe_NomEtType_ListeAjoutee() {
        setup();
        utilisateur.creerListe("Favoris", "Series");
        assert utilisateur.getListes().size() == 1 : "L'utilisateur devrait avoir 1 liste.";
        assert utilisateur.getListes().get(0).getNom().equals("Favoris") : "Le nom de la liste devrait etre Favoris.";
        assert utilisateur.getListes().get(0).getType().equals("Series") : "Le type de la liste devrait etre Series.";
        System.out.println("creerListe_NomEtType_ListeAjoutee passed.");
    }

    // Méthode suivre
    // Vérifie qu’un utilisateur est bien ajouté à la liste des suivis
    private void suivre_Utilisateur_UtilisateurPresentDansSuivis() {
        setup();
        Utilisateur bob = new Utilisateur("bob");
        utilisateur.suivre(bob);
        assert utilisateur.getSuivis().contains(bob) : "bob devrait etre dans les suivis.";
        assert utilisateur.getSuivis().size() == 1 : "Il devrait y avoir 1 suivi.";
        System.out.println("suivre_Utilisateur_UtilisateurPresentDansSuivis passed.");
    }

    // Méthode retirerAvis
    // Vérifie qu’un avis est bien retiré de l’utilisateur et du média
    private void retirerAvis_AvisPresent_AvisAbsentDeUtilisateurEtMedia() {
        setup();
        utilisateur.noterMedia(media, 3, "Pas mal", "2024-01-01");
        assert utilisateur.getAvis().size() == 1 : "Precondition : l'utilisateur devrait avoir 1 avis.";
        utilisateur.retirerAvis(media);
        assert utilisateur.getAvis().size() == 0 : "L'avis devrait avoir ete retire de l'utilisateur.";
        assert media.getAvis().size() == 0 : "L'avis devrait avoir ete retire du media.";
        System.out.println("retirerAvis_AvisPresent_AvisAbsentDeUtilisateurEtMedia passed.");
    }
}