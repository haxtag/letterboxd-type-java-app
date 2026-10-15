package graphique.vues.reutiliser;

import graphique.modeles.*;
import graphique.vues.fenetrePrincipal.VuePrincipale;
import graphique.vues.reutiliser.carte.CarteFilm;
import graphique.vues.reutiliser.carte.CarteSeries;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Un panneau de défilement personnalisé qui hérite de JScrollPane.
 * Cette classe est responsable de l'affichage dynamique des cartes de médias (films et séries) 
 * sous forme de grille (3 colonnes). Elle gère le chargement, le filtrage par genre 
 * ou par type, et intègre directement les composants dans un conteneur déroulant.
 * * @author Ethan
 */
public class ScrollMedia extends JScrollPane {
    private final Color COULEURFOND=new Color(15, 17, 21);
    public JPanel panelCarte;
    public Utilisateur utilisateur;
    public VuePrincipale vuePrincipale;
    /**
     * Crée un panneau de défilement affichant l'intégralité des médias disponibles.
     * * @param vuePrincipale La {@link VuePrincipale} de l'application permettant d'accéder au contexte et à l'utilisateur.
     */
    public ScrollMedia(VuePrincipale vuePrincipale)
    {
        this.vuePrincipale=vuePrincipale;
        this.utilisateur=vuePrincipale.getUtilisateur();
        panelCarte = new JPanel();

        init();
        filtrerMedia(null);
    }

    /**
     * Crée un panneau de défilement filtré selon le type de média spécifié ("Film" ou "Serie").
     * * @param genre         Le type de média à filtrer (attend "Film" ou "Serie").
     * @param vuePrincipale La VuePrincipale de l'application.
     */
    public ScrollMedia(String genre, VuePrincipale vuePrincipale)
    {

        this.vuePrincipale=vuePrincipale;
        this.utilisateur=vuePrincipale.getUtilisateur();
        panelCarte = new JPanel();

        if(genre=="Film" || genre=="Serie")
        {
            filtrerMediaType(genre);
        }
        init();
    }
    
    /**
     * Crée un panneau de défilement affichant une liste spécifique et préexistante de médias.
     * * @param mediaListe    La ListeMedia contenant les données à afficher.
     * @param vuePrincipale La VuePrincipale de l'application.
     */
    public ScrollMedia(ListeMedia mediaListe, VuePrincipale vuePrincipale)
    {
        this.vuePrincipale=vuePrincipale;

        this.utilisateur=vuePrincipale.getUtilisateur();
        panelCarte = new JPanel();
        creeCarte(mediaListe.getMedias());
        init();
    }


   /**
     * Initialise les propriétés graphiques du panneau de défilement.
     * Configure la grille de disposition (GridLayout de 3 colonnes), applique la couleur 
     * de fond, supprime les bordures et désactive la barre de défilement horizontale 
     * tout en optimisant la vitesse du défilement vertical.
     */
    public void init()
    {
        JPanel panelP = new JPanel(new BorderLayout());
        panelP.setBackground(COULEURFOND);

        panelCarte.setLayout(new GridLayout(0, 3, 0, 0));
        panelCarte.setBackground(COULEURFOND);

        panelP.add(panelCarte, BorderLayout.NORTH); // pour centrer

        setViewportView(panelP);
        setBorder(null);
        setBackground(COULEURFOND);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        getVerticalScrollBar().setUnitIncrement(16);
    }



    /**
     * Filtre et charge les médias en fonction de leur type (uniquement les films ou uniquement les séries).
     * * @param type Le type demandé ("Film"} ou {"Serie").
     */
    public void filtrerMediaType(String type) {
        ArrayList<Media> media = new ArrayList<Media>();
        if (type == "Film")
            media = FactoryMedia.getToutLesFilms();
        else if (type == "Serie")
            media = FactoryMedia.getTouteLesSeries();
        creeCarte(media);
    }

    /**
     * Filtre et charge les médias en fonction d'un genre textuel précis (Action, Comédie, etc.).
     * * @param genre Le nom du genre à filtrer. Si null, l'intégralité des médias est chargée.
     */
    public void filtrerMedia(String genre)
    {
        ArrayList<Media> media=new ArrayList<Media>();
        if (genre!=null) media=FactoryMedia.getTousLesMediasFiltrer(genre);
        else            media=FactoryMedia.getTousLesMedias();
       creeCarte(media);
    }
    /**
     * Génère et ajoute dynamiquement les cartes graphiques dans le panneau d'affichage.
     * Cette méthode vide d'abord le conteneur actuel, puis l'alimente en identifiant 
     * s'il s'agit d'une instance de Film ou de Serie afin d'instancier 
     * la carte visuelle correspondante (CarteFilm ou CarteSeries).
     * * @param media La liste d'objets Media à transformer en cartes visuelles.
     */
    public void creeCarte(ArrayList<Media> media)
    {
        panelCarte.removeAll(); // sécurité si appelé plusieurs fois

        for (int i = 0; i < media.size(); i++)
        {
            if (media.get(i).getClass() == Film.class)
            {
                Film film = (Film) media.get(i);
                panelCarte.add(new CarteFilm(film, utilisateur, vuePrincipale));
            }
            else
            {
                Serie serie = (Serie) media.get(i);
                panelCarte.add(new CarteSeries(serie, utilisateur, vuePrincipale));
            }
        }
        panelCarte.repaint();
    }


    }
