package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener pour le bouton Quitter dans le menu principal.
 * Ferme l'application lorsqu'il est activé.
 */
public class QuitButtonListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);  // Ferme l'application
    }
}
