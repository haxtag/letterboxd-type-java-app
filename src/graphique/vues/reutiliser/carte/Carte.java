package graphique.vues.reutiliser.carte;

import graphique.controleurs.EcouteurCarte;
import graphique.modeles.Media;
import graphique.modeles.Utilisateur;
import graphique.vues.fenetrePrincipal.VuePrincipale;
import graphique.vues.reutiliser.AVoirBouton;
import graphique.vues.reutiliser.FavorisBouton;
import graphique.vues.reutiliser.PanelLigne;
import javax.swing.*;
import java.awt.*;

/**
 * Classe abstraite modélisant une carte graphique réutilisable pour représenter un média.
 * Elle hérite de JPanel et organise verticalement (BoxLayout) l'affichage rapide 
 * d'un contenu multimédia : une affiche cliquable pour accéder aux détails, le titre, 
 * la durée, ainsi que des raccourcis d'action (boutons Favoris et À voir).
 * * @author Ethan
 */
public abstract class Carte extends JPanel{
    private final Color COULEUR_FOND=new Color(15, 17, 21);
    private final Color COULEUR_TEXTE =new Color(241, 245, 249);
    private JButton accesFilm;
    private JLabel nom;
    protected JLabel duree;
    private JPanel conteneurNomEtDuree;
    private FavorisBouton favorisBouton;
    private AVoirBouton aVoirBouton;
    private PanelLigne panelNomDuree;
    private PanelLigne panelFavorisAvoir;
    private EcouteurCarte ecouteurCarte;
    private VuePrincipale vuePrincipale;
    public Utilisateur utilisateur;
    public Media media;
    /**
     * Constructeur de la carte multimédia.
     * Initialise l'agencement vertical, charge et redimensionne proprement l'image de couverture,
     * instancie les composants textuels et les boutons d'action rapide selon l'état actuel des listes 
     * de l'utilisateur, configure et attache le contrôleur d'événements, puis assemble l'interface.
     * * @param media         Le Media} à représenter graphiquement sur la carte.
     * @param utilisateur   L'Utilisateur connecté pour vérifier la présence du média dans ses listes.
     * @param vuePrincipale La VuePrincipale requise pour piloter la navigation vers les détails.
     */
    public Carte(Media media, Utilisateur utilisateur, VuePrincipale vuePrincipale)
    {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(COULEUR_FOND);

        ImageIcon couverture = new ImageIcon(media.getChemin());
        Image img = couverture.getImage().getScaledInstance(150, 220, Image.SCALE_SMOOTH);
        couverture.setImage(img);

        this.media       = media;
        this.utilisateur = utilisateur;
        accesFilm        = new JButton(couverture);
        this.nom         = new JLabel(media.getTitre());
        this.duree       = new JLabel(media.getDureeString());
        favorisBouton    = new FavorisBouton(this.utilisateur.getListe("Favoris").getMedias().contains(this.media));
        aVoirBouton      = new AVoirBouton(this.utilisateur.getListe("A voir").getMedias().contains(this.media));
        panelNomDuree    = new PanelLigne(this.nom, this.duree);
        panelFavorisAvoir= new PanelLigne(favorisBouton, aVoirBouton);
        this.vuePrincipale = vuePrincipale;
        ecouteurCarte    = new EcouteurCarte(favorisBouton, aVoirBouton, accesFilm, media, utilisateur, this.vuePrincipale);

        favorisBouton.addActionListener(ecouteurCarte);
        aVoirBouton.addActionListener(ecouteurCarte);
        accesFilm.addActionListener(ecouteurCarte);

        paramBouton();
        setLbl();

        // supprimer les marges internes des panels
        panelNomDuree.setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
        panelFavorisAvoir.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        add(accesFilm);
        add(panelNomDuree);
        add(panelFavorisAvoir);
    }
    /**
     * Configure et stylise le bouton supportant la couverture du média.
     * Cette méthode retire le texte par défaut, centre le composant, annule l'opacité,
     * supprime le remplissage de la zone de contenu ainsi que le tracé des bordures 
     * et du focus pour transformer l'image en un élément interactif épuré.
     */
    public void paramBouton()
    {
        accesFilm.setText("");
        accesFilm.setAlignmentX(CENTER_ALIGNMENT);
        accesFilm.setOpaque(false);
        accesFilm.setContentAreaFilled(false);
        accesFilm.setBorderPainted(false);
        accesFilm.setFocusPainted(false);
    }
    /**
     * Applique la charte de couleurs textuelles sur les différents labels.
     * Assigne la couleur claire définie par COULEUR_TEXTE au titre ainsi qu'à la durée.
     */
    public void setLbl()
    {
        nom.setForeground(COULEUR_TEXTE);
        duree.setForeground(COULEUR_TEXTE);
    }
}
