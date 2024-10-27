package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * La classe QuiListener implémente ActionListener et gère l'action de fermeture de l'application.
 * Lorsque l'événement est déclenché, l'application se termine.
 */
public class QuiListener implements ActionListener {

    /**
     * Appelé lorsque l'action est déclenchée.
     * Cette méthode termine l'application en appelant System.exit(0).
     *
     * @param e l'événement d'action qui a été déclenché
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
