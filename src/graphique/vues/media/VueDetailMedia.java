package graphique.vues.media;

import graphique.controleurs.EcouteurAjoutAvis;
import graphique.controleurs.EcouteurCarte;
import graphique.controleurs.EcouteurRetour;
import graphique.modeles.ListeMedia;
import graphique.modeles.Media;
import graphique.modeles.Utilisateur;
import graphique.vues.fenetrePrincipal.VuePrincipale;
import graphique.vues.reutiliser.AVoirBouton;
import graphique.vues.reutiliser.Bouton;
import graphique.vues.reutiliser.FavorisBouton;
import graphique.vues.avis.VueAvis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Représente l'écran graphique détaillé affichant les informations d'un média sélectionné.
 * Cette classe hérite de {@link JPanel} et orchestre un ensemble riche de composants graphiques 
 * séparés en deux volets principaux (gauche et droit). Elle intègre l'affichage de l'affiche, 
 * des métadonnées (titre, réalisateur, etc.), de la note moyenne, ainsi que des interactions 
 * utilisateurs permettant de gérer les listes de lecture (favoris, à voir) et de soumettre un avis.
 * * @author Ilyes
 */

public class VueDetailMedia extends JPanel
{
    private VuePrincipale vuePrincipale;
    private Media media;
    private Utilisateur utilisateur;

    private Bouton retour;
    private JLabel image;
    private JLabel titre;
    private JLabel annee;
    private JLabel duree;
    private JLabel realisateur;
    private JLabel noteMoyenne;
    private JLabel labelAvis;
    private JLabel labelNote;
    private JLabel labelCommentaire;
    private FavorisBouton favorisBouton;
    private AVoirBouton aVoirBouton;
    private JComboBox<String> comboListes;
    private Bouton ajouterListe;
    private JTextField champCommentaire;
    private JSpinner champNote;
    private Bouton soumettre;

    private JPanel panelGauche;
    private JPanel panelDroit;
    private JPanel panelBoutons;
    private JPanel panelAvis;
    private JPanel panelFormulaire;
    private JScrollPane scroll;

    private Color couleurFond   = new Color(15, 17, 21);
    private Color couleurTexte  = new Color(241, 245, 249);
    private Color couleurMuted  = new Color(148, 163, 184);
    private Color couleurSurface = new Color(32, 37, 46);

    /**
     * Constructeur complet de la vue détaillée d'un média.
     * Initialise les variables de contexte, instancie tous les composants graphiques avec les données 
     * du modèle, charge et redimensionne la couverture, configure l'arborescence des panneaux 
     * (BorderLayout, BoxLayout, FlowLayout), attache les écouteurs d'événements (externes et anonymes) 
     * et applique la charte graphique globale.
     * * @param vuePrincipale La fenêtre principale VuePrincipale gérant la navigation globale.
     * @param media         Le modèle Media ciblé dont on extrait les informations.
     * @param utilisateur   L'Utilisateur connecté réalisant l'action.
     */
    public VueDetailMedia(VuePrincipale vuePrincipale, Media media, Utilisateur utilisateur)
    {
        this.vuePrincipale = vuePrincipale;
        this.media         = media;
        this.utilisateur   = utilisateur;

        // creation des composants
        retour          = new Bouton("Retour", 10, couleurSurface);
        titre           = new JLabel(media.getTitre());
        annee           = new JLabel("Année : " + media.getAnnee());
        duree           = new JLabel("Durée : " + media.getDureeString());
        realisateur     = new JLabel("Réalisateur : " + media.getRealisateur());
        noteMoyenne     = new JLabel("★  " + String.format("%.1f", media.getScoreMoyen()) + " / 10");
        labelAvis       = new JLabel("Avis :");
        labelNote       = new JLabel("Note (1-10) :");
        labelCommentaire= new JLabel("Commentaire :");
        favorisBouton   = new FavorisBouton(utilisateur.getListe("Favoris").getMedias().contains(media));
        aVoirBouton     = new AVoirBouton(utilisateur.getListe("A voir").getMedias().contains(media));
        champCommentaire= new JTextField(25);
        champNote       = new JSpinner(new SpinnerNumberModel(5, 1, 10, 1));
        soumettre       = new Bouton("Ajouter mon avis", 10, couleurSurface);
        ajouterListe    = new Bouton("Ajouter à la liste", 10, couleurSurface);

        // creation du combobox avec les listes utilisateur
        comboListes = new JComboBox<>();
        for (int i = 0; i < utilisateur.getListes().size(); i++)
        {
            comboListes.addItem(utilisateur.getListes().get(i).getNom());
        }

        ImageIcon couverture = new ImageIcon(media.getChemin());
        Image img = couverture.getImage().getScaledInstance(220, 320, Image.SCALE_SMOOTH);
        couverture.setImage(img);
        image = new JLabel(couverture);

        panelGauche    = new JPanel();
        panelDroit     = new JPanel();
        panelBoutons   = new JPanel();
        panelAvis      = new JPanel();
        panelFormulaire= new JPanel();
        scroll         = new JScrollPane(panelAvis);

        // parametrage du style des composants
        styleComposants();

        // parametrage panel gauche (retour + affiche)
        panelGauche.setLayout(new BoxLayout(panelGauche, BoxLayout.Y_AXIS));
        panelGauche.setBackground(couleurFond);
        panelGauche.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelGauche.setPreferredSize(new Dimension(280, 0));
        panelGauche.add(retour);
        panelGauche.add(Box.createVerticalStrut(20));
        panelGauche.add(image);

        // parametrage panel boutons (favoris, avoir, combo liste)
        panelBoutons.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panelBoutons.setBackground(couleurFond);
        panelBoutons.add(favorisBouton);
        panelBoutons.add(aVoirBouton);
        panelBoutons.add(comboListes);
        panelBoutons.add(ajouterListe);

        // parametrage panel avis
        panelAvis.setLayout(new BoxLayout(panelAvis, BoxLayout.Y_AXIS));
        panelAvis.setBackground(couleurFond);
        panelAvis.setAlignmentX(LEFT_ALIGNMENT);
        panelAvis.add(labelAvis);
        for (int i = 0; i < media.getAvis().size(); i++)
        {
            panelAvis.add(new VueAvis(media.getAvis().get(i)));
        }

        // parametrage scroll
        scroll.setBackground(couleurFond);
        scroll.getViewport().setBackground(couleurFond);
        scroll.setBorder(null);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.getVerticalScrollBar().setUnitIncrement(12);
        scroll.setPreferredSize(new Dimension(700, 200));
        // parametrage panel formulaire
        panelFormulaire.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panelFormulaire.setBackground(couleurFond);
        panelFormulaire.add(labelNote);
        panelFormulaire.add(champNote);
        panelFormulaire.add(labelCommentaire);
        panelFormulaire.add(champCommentaire);
        panelFormulaire.add(soumettre);

        // parametrage panel droit (toutes les infos)
        panelDroit.setLayout(new BoxLayout(panelDroit, BoxLayout.Y_AXIS));
        panelDroit.setBackground(couleurFond);
        panelDroit.setBorder(BorderFactory.createEmptyBorder(40, 30, 20, 30));

        // forcer l'alignement gauche sur chaque composant
        titre.setAlignmentX(LEFT_ALIGNMENT);
        annee.setAlignmentX(LEFT_ALIGNMENT);
        duree.setAlignmentX(LEFT_ALIGNMENT);
        realisateur.setAlignmentX(LEFT_ALIGNMENT);
        noteMoyenne.setAlignmentX(LEFT_ALIGNMENT);
        labelAvis.setAlignmentX(LEFT_ALIGNMENT);
        panelBoutons.setAlignmentX(LEFT_ALIGNMENT);
        scroll.setAlignmentX(LEFT_ALIGNMENT);
        panelFormulaire.setAlignmentX(LEFT_ALIGNMENT);

        panelDroit.add(titre);
        panelDroit.add(Box.createVerticalStrut(10));
        panelDroit.add(annee);
        panelDroit.add(Box.createVerticalStrut(5));
        panelDroit.add(duree);
        panelDroit.add(Box.createVerticalStrut(5));
        panelDroit.add(realisateur);
        panelDroit.add(Box.createVerticalStrut(15));
        panelDroit.add(noteMoyenne);
        panelDroit.add(Box.createVerticalStrut(15));
        panelDroit.add(panelBoutons);
        panelDroit.add(Box.createVerticalStrut(20));
        panelDroit.add(labelAvis);
        panelDroit.add(Box.createVerticalStrut(10));
        panelDroit.add(scroll);
        panelDroit.add(Box.createVerticalStrut(10));
        panelDroit.add(panelFormulaire);

        // ajout des ecouteurs
        retour.addActionListener(new EcouteurRetour(retour, vuePrincipale));

        EcouteurCarte ecouteurCarte = new EcouteurCarte(favorisBouton, aVoirBouton, media, utilisateur);
        favorisBouton.addActionListener(ecouteurCarte);
        aVoirBouton.addActionListener(ecouteurCarte);

        soumettre.addActionListener(new EcouteurAjoutAvis(soumettre, champCommentaire, champNote, media, utilisateur, panelAvis, noteMoyenne));
        // ecouteur anonyme
        ajouterListe.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                String nomListe = (String) comboListes.getSelectedItem();
                ListeMedia liste = utilisateur.getListe(nomListe);
                if (!liste.getMedias().contains(media))
                {
                    utilisateur.ajouterMedia(media, liste);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Ce média est déjà dans la liste " + nomListe, "Action impossible", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // configuration du panel principal
        setLayout(new BorderLayout());
        setBackground(couleurFond);

        // ajout des composants
        add(panelGauche, BorderLayout.WEST);
        add(panelDroit, BorderLayout.CENTER);
    }
    /**
     * Personnalise et homogénéise l'aspect visuel de l'ensemble des composants de l'interface.
     * Configure finement les polices (taille et graisse), les couleurs de premier plan (Foreground) 
     * et d'arrière-plan (Background), les curseurs de saisie ainsi que les bordures transparentes 
     * des éléments interactifs et textuels.
     */
    public void styleComposants()
    {
        titre.setForeground(couleurTexte);
        titre.setFont(titre.getFont().deriveFont(Font.BOLD, 28f));

        annee.setForeground(couleurMuted);
        duree.setForeground(couleurMuted);
        realisateur.setForeground(couleurMuted);

        noteMoyenne.setForeground(new Color(250, 204, 21));
        noteMoyenne.setFont(noteMoyenne.getFont().deriveFont(Font.BOLD, 22f));

        labelAvis.setForeground(couleurTexte);
        labelAvis.setFont(labelAvis.getFont().deriveFont(Font.BOLD, 16f));


        labelNote.setForeground(couleurMuted);
        labelCommentaire.setForeground(couleurMuted);

        champCommentaire.setBackground(couleurSurface);
        champCommentaire.setForeground(couleurTexte);
        champCommentaire.setCaretColor(couleurTexte);
        champCommentaire.setBorder(BorderFactory.createEmptyBorder(5, 8, 5, 8));

        champNote.setBackground(couleurSurface);
        champNote.setForeground(couleurTexte);

        comboListes.setBackground(couleurSurface);
        comboListes.setForeground(couleurTexte);
    }
}