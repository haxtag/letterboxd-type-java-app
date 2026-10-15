package graphique;
import graphique.modeles.FactoryMedia;
import graphique.vues.fenetrePrincipal.VueBarLateral;
import graphique.vues.fenetrePrincipal.VuePrincipale;

class ApplicationMedias
{
    public static void main(String[] args)
    {
        FactoryMedia.creerTousLesObjets();
        VuePrincipale a=new VuePrincipale();
    }

}