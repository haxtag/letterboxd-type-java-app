package graphique.modeles;

public class Application
{
	public static void main(String[] args)
	{
		FactoryMedia.creerTousLesObjets();
		System.out.println("\t TOUS LES MEDIA");
		for (Media media: FactoryMedia.getTousLesMedias())
		{
			media.afficher();
		}
		
		System.out.println("\t TOUS LES UTILISATEURS");
		for (Utilisateur u: FactoryMedia.getTousLesUtilisateurs())
		{
			u.afficher();
		}
	}

}
