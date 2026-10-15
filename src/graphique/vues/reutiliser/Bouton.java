// Classe uniquement pour structurer le code proprement
// https://www.youtube.com/watch?v=_ffSDRngczw&t=252s

package graphique.vues.reutiliser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Un bouton personnalisé qui hérite de JButton.
 * Ce composant propose un design moderne avec des coins arrondis (smooth arcs), 
 * une gestion avancée du rendu graphique (antialiasing) et des animations de couleurs 
 * fluides lors du survol (hover) et du clic (active).
 * * @author Erick
 */
public class Bouton extends JButton {
    private Color defaultBg = new Color(18, 18, 26);
    private Color hoverBg   = new Color(28, 28, 38);
    private Color activeBg  = new Color(12, 12, 18);

    private Color borderColor = new Color(60, 120, 255);
    private Color hoverBorder = new Color(90, 150, 255);

    private Color defaultText = new Color(235, 235, 245);
    private Color activeText  = new Color(255, 255, 255);
    private int radius = 30;

    /**
     * Crée un bouton par défaut avec le texte "Bouton".
     */
    public Bouton()
    {
        super("Bouton");
        init();
    }
    /**
     * Crée un bouton affichant uniquement une icône.
     * * @param image L'icône à afficher dans le bouton.
     */
    public Bouton(ImageIcon image)
    {
        super(image);
        init();
    }
    /**
     * Crée un bouton affichant un texte spécifique.
     * * @param texte Le texte à afficher.
     */
    public Bouton(String texte)
    {
        super(texte);
        init();
    }
    /**
     * Crée un bouton avec un texte et un rayon d'arrondi personnalisés.
     * * @param texte  Le texte à afficher.
     * @param radius Le rayon des coins arrondis (en pixels).
     */
    public Bouton(String texte, int radius)
    {
        super(texte);
        this.radius=radius;

        init();
    }
    /**
     * Crée un bouton entièrement personnalisé (texte, arrondi et couleur de fond initiale).
     * * @param texte     Le texte à afficher.
     * @param radius    Le rayon des coins arrondis (en pixels).
     * @param defaultBg La couleur de fond par défaut du bouton.
     */
    public Bouton(String texte, int radius, Color defaultBg)
    {
        super(texte);
        this.radius=radius;
        this.defaultBg=defaultBg;
        init();
    }

    /**
     * Initialise les propriétés graphiques par défaut du bouton et 
     * configure les écouteurs d'événements pour forcer le rafraîchissement 
     * visuel lors des interactions de la souris.
     */
    public void init()
    {
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);
        setForeground(defaultText);
        setFont(getFont().deriveFont(Font.BOLD,16f));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setMargin(new Insets(2,5,2,5));
        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e)
            {
                repaint();
            }
            public void mouseExited(MouseEvent e)
            {
                repaint();
            }

            public void mousePressed(MouseEvent e)
            {
                repaint();
            }

            public void mouseReleased(MouseEvent e)
            {
                repaint();
            }
        });

    }



    protected  void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if(getModel().isPressed()) g2.setColor(activeBg);
        else if(getModel().isRollover()) g2.setColor(hoverBg);
        else g2.setColor(defaultBg);


        //arrondir le bouton
        g2.fillRoundRect(0,0,getWidth(),getHeight(),radius,radius);


        if(getModel().isPressed()) g2.setColor(activeBg.darker());
        else if (getModel().isRollover()) g2.setColor(hoverBorder);
        else g2.setColor(borderColor);


        if(getModel().isPressed()) setForeground(activeText);
        else setForeground(defaultText);


        g2.dispose();

        super.paintComponent(g);
    }


        // getter et setter obligatoire
    public Color getDefaultBg() {
        return defaultBg;
    }

    public Color getHoverBg() {
        return hoverBg;
    }

    public Color getActiveBg() {
        return activeBg;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public Color getHoverBorder() {
        return hoverBorder;
    }

    public Color getDefaultText() {
        return defaultText;
    }

    public Color getActiveText() {
        return activeText;
    }

    public int getRadius() {
        return radius;
    }


    public void setDefaultBg(Color defaultBg) {
        this.defaultBg = defaultBg;
    }

    public void setHoverBg(Color hoverBg) {
        this.hoverBg = hoverBg;
    }

    public void setActiveBg(Color activeBg) {
        this.activeBg = activeBg;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public void setHoverBorder(Color hoverBorder) {
        this.hoverBorder = hoverBorder;
    }

    public void setDefaultText(Color defaultText) {
        this.defaultText = defaultText;
    }

    public void setActiveText(Color activeText) {
        this.activeText = activeText;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
    public boolean isOpaque()
    {
        return false;
    }
}
