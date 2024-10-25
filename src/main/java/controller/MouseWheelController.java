package controller;

import model.Tile;
import view.HexagonTile;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MouseWheelController implements MouseWheelListener {

    private HexagonTile previewTile;
    private GameController gameController;

    public MouseWheelController(HexagonTile previewTile, GameController gameController) {
        this.previewTile = previewTile;
        this.gameController = gameController;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        Tile nextTile = gameController.getNextTile();

        if (e.getWheelRotation() < 0) {
            nextTile.rotateClockwise();
        } else if (e.getWheelRotation() > 0) {
            nextTile.rotateClockwise();
        }

        previewTile.repaint();  // Mettre à jour l'aperçu avec la nouvelle rotation
    }
}
