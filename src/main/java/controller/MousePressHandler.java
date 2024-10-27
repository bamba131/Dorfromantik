package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;

/**
 * La classe MousePressHandler gère les événements de pression de la souris 
 * pour initialiser le début d'un glissement dans la vue de la grille.
 * Elle utilise un {@link CameraControllerListener} pour suivre l'état du glissement.
 */
public class MousePressHandler extends MouseAdapter {

    private CameraControllerListener listener;

    /**
     * Construit un gestionnaire de pression de souris.
     *
     * @param listener le listener pour gérer le début et la fin du glissement de la souris
     */
    public MousePressHandler(CameraControllerListener listener) {
        this.listener = listener;
    }

    /**
     * Appelé lorsque le bouton droit de la souris est enfoncé. 
     * Définit le point de départ du glissement pour le mouvement ultérieur.
     *
     * @param e l'événement de pression de la souris
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            listener.setMouseDragStart(e.getPoint());
        }
    }

    /**
     * Appelé lorsque le bouton droit de la souris est relâché. 
     * Réinitialise le point de départ du glissement.
     *
     * @param e l'événement de relâchement de la souris
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            listener.resetMouseDragStart();
        }
    }
}
