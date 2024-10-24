package controller;

import view.HexagonTile;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Point;
import java.util.Set;

public class HexagonMouseListener extends MouseAdapter {

    private final HexagonTile hexTile;
    private final GameController gameController;
    private final Set<Point> availablePositions;
    private final CameraController cameraController; // Ajouter CameraController

    public HexagonMouseListener(HexagonTile hexTile, GameController gameController, Set<Point> availablePositions, CameraController cameraController) {
        this.hexTile = hexTile;
        this.gameController = gameController;
        this.availablePositions = availablePositions;
        this.cameraController = cameraController; // Initialiser CameraController
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point position = hexTile.getPosition();
        if (availablePositions.contains(position)) {
            System.out.println("Hexagone cliqué à la position : " + position);
            gameController.placeTile(position, cameraController);  // Appelle la méthode pour placer une tuile
        } else {
            System.out.println("Position non disponible pour le placement");
        }
    }



}
