package tests;
import graphique.modeles.Avis;
import graphique.modeles.Serie;

import java.util.ArrayList;
import java.util.List;

public class MediaTests {
	private Serie s1;
	private Avis a1;
	private Avis a2;
	private Avis a3;
	public static void main(String[] args) {
		MediaTests test = new MediaTests();
		
		// ----- retirer un avis doit diminuer la liste
		//s1.ajouterAvis(a1);
		//s1.retirerAvis(a1);
		test.scoreMoyenSansAvis_doitRetournerZero();
        test.scoreMoyenAvecPlusieursAvis_doitEtreCorrect();
        test.retirerAvis_doitDiminuerLaListe();
        test.getAvis_doitEtreInitialiseVide();
        test.scoreMoyenApresRetraitAvis_doitEtreRecalcule();
        test.scoreMoyenAvecUnAvis_doitRetournerLaNote();
        System.out.println();
        
        System.out.println("–––––––––––––––––––––––––––––––––––––––––––––––––");
        System.out.println("|  TOUS LES TESTS DE LA CLASSE MEDIA SONT FINI  |");
        System.out.println("–––––––––––––––––––––––––––––––––––––––––––––––––");
	}
	
	private void setup() {
        ArrayList<String> GameOfThroneGenres = new ArrayList<String>(List.of("Fantastique", "Aventure", "Action"));
			s1 =  new Serie("Game of Throne",2010,100,GameOfThroneGenres,"jean");
			a1 = new Avis(s1,7,"Tres bien","04/04/2026");
			a2 = new Avis(s1,5,"bien","01/05/2026");
			a3 = new Avis(s1,9,"goat","01/06/2026");
		}
		// ---- Media : score moyen sans avis --> doit retourner 0---
	private void scoreMoyenSansAvis_doitRetournerZero() {
			setup();
			assert(s1.getScoreMoyen() == 0.0) : "Score moyen different de zero";
			System.out.println("scoreMoyenSansAvis_doitRetournerZero passed");
		}
	
	private void scoreMoyenAvecPlusieursAvis_doitEtreCorrect() {
        setup();
        s1.ajouterAvis(a1); 
        s1.ajouterAvis(a2); 
        s1.ajouterAvis(a3); 
        assert(s1.getScoreMoyen() == 7) : "Score moyen devrait etre 7.0";
        System.out.println("scoreMoyenAvecPlusieursAvis_doitEtreCorrect passed");
    }
	
	private void scoreMoyenAvecUnAvis_doitRetournerLaNote() {
        setup();
        s1.ajouterAvis(a2); 
        assert(s1.getScoreMoyen() == 5.0) : "Score moyen devrait etre 2.0 avec un seul avis";
        System.out.println("scoreMoyenAvecUnAvis_doitRetournerLaNote passed");
    }
	
	private void scoreMoyenApresRetraitAvis_doitEtreRecalcule() {
        setup();
        s1.ajouterAvis(a1); 
        s1.ajouterAvis(a2); 
        s1.retirerAvis(a1); 
        assert(s1.getScoreMoyen() == 5.0) : "Score moyen devrait etre 4.0 apres retrait de a1";
        System.out.println("scoreMoyenApresRetraitAvis_doitEtreRecalcule passed");
    }
	
	private void retirerAvis_doitDiminuerLaListe() {
		setup();
		s1.ajouterAvis(a1);
		s1.retirerAvis(a1);
		assert(s1.getAvis().isEmpty()): " La liste devrait etre vide apres retrait";
		System.out.println("retirerAvis_doitDiminiuerLaListe passed");
	}
	
	private void getAvis_doitEtreInitialiseVide() {
        setup();
        assert(s1.getAvis() != null) : "La liste d'avis ne devrait pas etre null";
        assert(s1.getAvis().isEmpty()) : "La liste d'avis devrait etre vide a la creation";
        System.out.println("getAvis_doitEtreInitialiseVide passed");
    }
}
