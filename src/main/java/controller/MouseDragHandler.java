package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Point;
import javax.swing.SwingUtilities;


/**
 * La classe MouseDragHandler gère les événements de glisser-déposer pour déplacer la vue d'une grille.
 * Elle utilise un {@link CameraControllerListener} pour ajuster l'offset de la vue en fonction des mouvements de souris.
 */
public class MouseDragHandler extends MouseAdapter {

    private CameraControllerListener listener;


    /**
     * Construit un gestionnaire de glissement de souris.
     *
     * @param listener le listener pour gérer les mises à jour de la vue de la grille en réponse au déplacement de la souris
     */
    public MouseDragHandler(CameraControllerListener listener) {
        this.listener = listener;
    }


    /**
     * Appelé lorsque la souris est glissée avec le bouton droit enfoncé. 
     * Calcule le déplacement et met à jour la vue via le listener.
     *
     * @param e l'événement de glissement de la souris
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        if (listener.getMouseDragStart() != null && SwingUtilities.isRightMouseButton(e)) {
            Point current = e.getPoint();
            int deltaX = current.x - listener.getMouseDragStart().x;
            int deltaY = current.y - listener.getMouseDragStart().y;

            // Déplacement dans CameraController
            listener.updateViewOffset(deltaX, deltaY);

            // Met à jour la position de départ
            listener.setMouseDragStart(current);
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
