package controller;

import model.Tile;
import view.HexagonTile;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MouseWheelController implements MouseWheelListener {

    private HexagonTile previewTile;
    private GameController gameController;
    private long lastRotationTime = 0;  // Stocke le temps de la dernière rotation
    private static final int ROTATION_DELAY = 100;  // Délai minimum en millisecondes entre chaque rotation

    public MouseWheelController(HexagonTile previewTile, GameController gameController) {
        this.previewTile = previewTile;
        this.gameController = gameController;
    }

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
