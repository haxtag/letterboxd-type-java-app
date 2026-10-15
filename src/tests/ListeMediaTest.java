package tests;

import graphique.modeles.ListeMedia;
import graphique.modeles.Media;
import graphique.modeles.Film;

import java.util.ArrayList;
import java.util.List;

public class ListeMediaTest
{
    private ListeMedia liste;
    private Media media;

    public static void main(String[] args)
    {
        ListeMediaTest test = new ListeMediaTest();

        System.out.println("=== Tests ListeMediaTest ===");
        System.out.println();

        System.out.println("-- ajouterMedia --");
        test.ajouterMedia_Media_MediaPresentDansListe();
        test.ajouterMedia_PlusieursMedias_TousPresents();
        System.out.println();

        System.out.println("-- supprimerMedia --");
        test.supprimerMedia_IndiceValide_MediaAbsentDeLaListe();
        test.supprimerMedia_IndiceValide_TailleDecrementee();
        test.supprimerMedia_IndiceNegatif_ListeInchangee();
        test.supprimerMedia_IndiceSuperieurTaille_ListeInchangee();
        System.out.println();

        System.out.println("=== All tests passed. ===");
    }

    private void setup()
    {
        ArrayList<String> inceptionGenres = new ArrayList<>(List.of("Science-Fiction", "Action", "Thriller"));
        liste = new ListeMedia("Ma liste", "Films");
        media = new Film("Inception", 2010, 148, inceptionGenres, "Nolan");
    }

    // Méthode : ajouterMedia

    private void ajouterMedia_Media_MediaPresentDansListe()
    {
        setup();
        liste.ajouterMedia(media);
        assert liste.getMedias().contains(media) : "Le media devrait etre dans la liste.";
        System.out.println("ajouterMedia_Media_MediaPresentDansListe passed.");
    }

    private void ajouterMedia_PlusieursMedias_TousPresents()
    {
        ArrayList<String> avatarGenres = new ArrayList<String>(List.of("Science-Fiction", "Aventure", "Action"));
        setup();
        Media media2 = new Film("Avatar", 2009, 162, avatarGenres, "Cameron");
        liste.ajouterMedia(media);
        liste.ajouterMedia(media2);
        assert liste.getMedias().size() == 2 : "La liste devrait contenir 2 medias.";
        assert liste.getMedias().contains(media) : "Le premier media devrait etre dans la liste.";
        assert liste.getMedias().contains(media2) : "Le deuxieme media devrait etre dans la liste.";
        System.out.println("ajouterMedia_PlusieursMedias_TousPresents passed.");
    }

    // Méthode : supprimerMedia

    private void supprimerMedia_IndiceValide_MediaAbsentDeLaListe()
    {
        setup();
        liste.ajouterMedia(media);
        liste.supprimerMedia(0);
        assert !liste.getMedias().contains(media) : "Le media devrait avoir ete supprime de la liste.";
        System.out.println("supprimerMedia_IndiceValide_MediaAbsentDeLaListe passed.");
    }

    private void supprimerMedia_IndiceValide_TailleDecrementee()
    {
        setup();
        liste.ajouterMedia(media);	
        liste.supprimerMedia(0);
        assert liste.getMedias().size() == 0 : "La liste devrait etre vide apres suppression.";
        System.out.println("supprimerMedia_IndiceValide_TailleDecrementee passed.");
    }

    private void supprimerMedia_IndiceNegatif_ListeInchangee()
    {
        setup();
        liste.ajouterMedia(media);
        liste.supprimerMedia(-1);
        assert liste.getMedias().size() == 1 : "La liste ne devrait pas avoir change avec un indice negatif.";
        System.out.println("supprimerMedia_IndiceNegatif_ListeInchangee passed.");
    }

    private void supprimerMedia_IndiceSuperieurTaille_ListeInchangee()
    {
        setup();
        liste.ajouterMedia(media);
        liste.supprimerMedia(5);
        assert liste.getMedias().size() == 1 : "La liste ne devrait pas avoir change avec un indice trop grand.";
        System.out.println("supprimerMedia_IndiceSuperieurTaille_ListeInchangee passed.");
    }
}