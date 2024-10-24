package controller;

import view.HexagonTile;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Point;
import java.util.Set;

public class HexagonMouseListener extends MouseAdapter {

    private final HexagonTile hexTile;
    private final GameController gameController;  // Utilise maintenant GameController
    private final Set<Point> availablePositions;

    public HexagonMouseListener(HexagonTile hexTile, GameController gameController, Set<Point> availablePositions) {
        this.hexTile = hexTile;
        this.gameController = gameController;
        this.availablePositions = availablePositions;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point position = hexTile.getPosition();
        if (availablePositions.contains(position)) {
            gameController.placeTile(position);  // Appelle la méthode placeTile du contrôleur
        }
    }
}
