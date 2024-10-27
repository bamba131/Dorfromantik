package view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

/**
 * La classe <code>ButtonHoverListener</code> écoute les événements de la souris pour les boutons de type <code>BtnPerso</code>.
 * Elle change la couleur de fond du bouton lorsque la souris survole ou quitte le bouton.
 */
public class ButtonHoverListener extends MouseAdapter {

    /** Couleur de survol avec transparence. */
    private final Color hoverColor = new Color(200, 150, 100, 200);
    
    /** Couleur normale avec transparence. */
    private final Color normalColor = new Color(243, 171, 115, 150);

    /**
     * Change la couleur de fond du bouton lorsqu'il est survolé par la souris.
     * 
     * @param e L'événement de souris déclenché lorsque la souris entre dans le bouton.
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() instanceof BtnPerso) { // Applique uniquement aux boutons de type BtnPerso
            BtnPerso button = (BtnPerso) e.getSource();
            button.setBackground(hoverColor);
        }
    }

    /**
     * Restaure la couleur de fond normale du bouton lorsque la souris quitte le bouton.
     * 
     * @param e L'événement de souris déclenché lorsque la souris quitte le bouton.
     */
    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() instanceof BtnPerso) { // Applique uniquement aux boutons de type BtnPerso
            BtnPerso button = (BtnPerso) e.getSource();
            button.setBackground(normalColor);
        }
    }
}
