package controller;

import model.Tile;
import view.HexagonTile;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/**
 * La classe MouseWheelController gère les événements de rotation de la molette 
 * de la souris pour faire pivoter la tuile en cours dans l'interface utilisateur.
 */
public class MouseWheelController implements MouseWheelListener {

    private HexagonTile previewTile;
    private GameController gameController;
    private long lastRotationTime = 0;  // Stocke le temps de la dernière rotation
    private static final int ROTATION_DELAY = 100;  // Délai minimum en millisecondes entre chaque rotation

    /**
     * Construit un contrôleur de molette de souris.
     *
     * @param previewTile la tuile d'aperçu dont la rotation doit être contrôlée
     * @param gameController le contrôleur de jeu qui gère l'état du jeu
     */
    public MouseWheelController(HexagonTile previewTile, GameController gameController) {
        this.previewTile = previewTile;
        this.gameController = gameController;
    }

     /**
     * Appelé lorsque la molette de la souris est déplacée.
     * Effectue la rotation de la tuile en cours en fonction de la direction 
     * de la rotation de la molette.
     *
     * @param e l'événement de rotation de la molette de souris
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        long currentTime = System.currentTimeMillis();

        // Si le délai entre les rotations est respecté, on procède à la rotation
        if (currentTime - lastRotationTime >= ROTATION_DELAY) {
            Tile nextTile = gameController.getNextTile();
            if (nextTile != null) {
                if (e.getWheelRotation() < 0) {
                    nextTile.rotateClockwise();
                } else if (e.getWheelRotation() > 0) {
                    nextTile.rotateCounterClockwise();
                }

                previewTile.repaint();  // Mettre à jour l'aperçu avec la nouvelle rotation
                lastRotationTime = currentTime;  // Mise à jour du temps de la dernière rotation
            }
        }
    }
}
