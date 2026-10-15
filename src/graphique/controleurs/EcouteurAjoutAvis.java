package graphique.controleurs;

import graphique.modeles.Avis;
import graphique.modeles.FactoryMedia;
import graphique.modeles.Media;
import graphique.modeles.Utilisateur;
import graphique.vues.avis.VueAvis;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import static java.awt.Component.LEFT_ALIGNMENT;

/**
 * Contrôleur gérant l'ajout et la modification d'un avis utilisateur.
 * Met à jour l'interface et la sauvegarde après action.
 * @author Noham
 */
public class EcouteurAjoutAvis implements ActionListener {

    private JButton soumettre;
    private JTextField champCommentaire;
    private JSpinner champNote;
    private Media media;
    private Utilisateur utilisateur;
    private JPanel panelAvis;
    private JLabel noteMoyenne;

    public EcouteurAjoutAvis(
            JButton soumettre,
            JTextField champCommentaire,
            JSpinner champNote,
            Media media,
            Utilisateur utilisateur,
            JPanel panelAvis,
            JLabel noteMoyenne) {

        this.soumettre = soumettre;
        this.champCommentaire = champCommentaire;
        this.champNote = champNote;
        this.media = media;
        this.utilisateur = utilisateur;
        this.panelAvis = panelAvis;
        this.noteMoyenne = noteMoyenne;
    }

    /**
     * Gère la soumission d'un avis :
     * - création d'un nouvel avis
     * - ou modification d'un avis existant
     * - mise à jour de l'affichage
     * - sauvegarde des données
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == soumettre) {

            String commentaire = champCommentaire.getText();
            int note = (int) champNote.getValue();
            String date = LocalDate.now().toString();

            boolean succes = utilisateur.noterMedia(media, note, commentaire, date);

            if (!succes) {

                // L'utilisateur a déjà noté ce média → remplacement de l'avis
                Avis nouvelAvis = new Avis(media, note, utilisateur.getPseudo(), commentaire, date);
                utilisateur.changerAvis(media, nouvelAvis);

                // Rechargement complet des avis
                panelAvis.removeAll();

                for (int i = 0; i < media.getAvis().size(); i++) {
                    Avis avis = media.getAvis().get(i);
                    VueAvis vueAvis = new VueAvis(avis);
                    vueAvis.setAlignmentX(LEFT_ALIGNMENT);
                    panelAvis.add(vueAvis);
                }

            } else {

                // Ajout simple du nouvel avis
                VueAvis vueAvis =
                        new VueAvis(media.getAvis().get(media.getAvis().size() - 1));

                vueAvis.setAlignmentX(LEFT_ALIGNMENT);
                panelAvis.add(vueAvis);
            }

            panelAvis.revalidate();
            panelAvis.repaint();

            // Mise à jour de la moyenne affichée
            noteMoyenne.setText("★  " + media.getScoreMoyen() + " / 10");

            // Sauvegarde globale
            FactoryMedia.sauvegarder();

            // Reset formulaire
            champCommentaire.setText("");
            champNote.setValue(5);
        }
    }
}