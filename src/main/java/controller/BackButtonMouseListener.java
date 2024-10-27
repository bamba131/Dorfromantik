package controller;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

/**
 * Listener de souris pour le bouton de retour dans l'interface de score.
 * Change la couleur de fond du bouton lorsqu'il est survolé.
 */
public class BackButtonMouseListener extends MouseAdapter {
    private final JButton button;
    private final Color hoverColor;
    private final Color normalColor;

    /**
     * Constructeur de <code>BackButtonMouseListener</code>.
     * 
     * @param button Le bouton cible.
     * @param hoverColor La couleur de fond du bouton lors du survol.
     * @param normalColor La couleur de fond normale du bouton.
     */
    public BackButtonMouseListener(JButton button, Color hoverColor, Color normalColor) {
        this.button = button;
        this.hoverColor = hoverColor;
        this.normalColor = normalColor;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        button.setBackground(hoverColor);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        button.setBackground(normalColor);
    }
}
