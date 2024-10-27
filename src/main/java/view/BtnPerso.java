package view;

import javax.swing.JButton;
import java.awt.*;

/**
 * La classe <code>BtnPerso</code> représente un bouton personnalisé utilisé dans l'interface utilisateur.
 * Elle étend la classe <code>JButton</code> et applique des styles spécifiques pour le rendu du bouton.
 */
public class BtnPerso extends JButton {

    /**
     * Constructeur de la classe <code>BtnPerso</code>.
     * 
     * @param text Le texte à afficher sur le bouton.
     */
    public BtnPerso(String text){
        super(text);
        setFont(new Font("Helvetica", Font.BOLD, 25));
        setContentAreaFilled(false);
        setForeground(Color.BLACK);
        setBorderPainted(false);
        setOpaque(true);
        setFocusPainted(false);
        setBackground(new Color(243, 171, 115, 150));

        Dimension d = new Dimension(200, 50);
        setPreferredSize(d);
    }
}
