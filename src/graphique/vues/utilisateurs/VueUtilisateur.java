package graphique.vues.utilisateurs;

import graphique.controleurs.EcouteurUtilisateur;
import graphique.modeles.ListeMedia;
import graphique.modeles.Utilisateur;
import graphique.vues.fenetrePrincipal.VueBarLateral;
import graphique.vues.fenetrePrincipal.VuePrincipale;
import graphique.vues.reutiliser.Bouton;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VueUtilisateur extends JPanel
{
    private final Color COULEUR_MUTED = new Color(148, 163, 184);
    private final Color COULEUR_TEXTE = new Color(241, 245, 249);
    private final Color COULEUR_FOND    = new Color(15, 17, 21);
    private final Color COULEUR_SURFACE = new Color(32, 37, 46);

    private VuePrincipale vuePrincipale;
    private Utilisateur utilisateur;

    public VueUtilisateur(VuePrincipale vuePrincipale)
    {
        this.vuePrincipale = vuePrincipale;
        this.utilisateur   = vuePrincipale.getUtilisateur();

        setLayout(new BorderLayout());
        setBackground(COULEUR_FOND);

        add(new VueBarLateral(vuePrincipale), BorderLayout.NORTH);
        add(creerPanelListes(), BorderLayout.CENTER);
    }

    private JPanel creerPanelListes()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(COULEUR_FOND);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // pseudo
        JLabel labelPseudo = new JLabel("Utilisateur : " + utilisateur.getPseudo());
        labelPseudo.setForeground(COULEUR_TEXTE);
        labelPseudo.setFont(labelPseudo.getFont().deriveFont(Font.BOLD, 20f));
        labelPseudo.setAlignmentX(LEFT_ALIGNMENT);

        // stats
        int nbAvis      = utilisateur.getAvis().size();
        int nbFavoris   = utilisateur.getListe("Favoris").getMedias().size();
        int nbAVoir     = utilisateur.getListe("A voir").getMedias().size();

        double moyenne  = 0;
        if (nbAvis > 0)
        {
            int total = 0;
            for (int i = 0; i < utilisateur.getAvis().size(); i++)
                total += utilisateur.getAvis().get(i).getNote();
            moyenne = (double) total / nbAvis;
        }

        JLabel labelStats = new JLabel(
                nbAvis + " avis - " +
                        nbFavoris + " favoris - " +
                        nbAVoir + " à voir - " +
                        "★ " + Double.toString(moyenne) + " moy."
        );
        labelStats.setForeground(COULEUR_MUTED);
        labelStats.setFont(labelStats.getFont().deriveFont(14f));
        labelStats.setAlignmentX(LEFT_ALIGNMENT);

        // listes
        JLabel labelListes = new JLabel("Listes :");
        labelListes.setForeground(COULEUR_TEXTE);
        labelListes.setFont(labelListes.getFont().deriveFont(Font.BOLD, 18f));
        labelListes.setAlignmentX(LEFT_ALIGNMENT);

        panel.add(labelPseudo);
        panel.add(Box.createVerticalStrut(8));
        panel.add(labelStats);
        panel.add(Box.createVerticalStrut(20));
        panel.add(labelListes);
        panel.add(Box.createVerticalStrut(10));

        ArrayList<ListeMedia> listes = utilisateur.getListes();
        for (int i = 0; i < listes.size(); i++)
        {
            Bouton boutonListe = new Bouton(listes.get(i).getNom(), 20, COULEUR_SURFACE);
            boutonListe.setAlignmentX(LEFT_ALIGNMENT);
            panel.add(boutonListe);
            boutonListe.addActionListener(new EcouteurUtilisateur(boutonListe, listes.get(i), vuePrincipale));
            panel.add(Box.createVerticalStrut(10));
        }

        return panel;
    }
}