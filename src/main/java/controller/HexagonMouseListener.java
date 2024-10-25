package controller;

import view.HexagonTile;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Set;

public class HexagonMouseListener extends MouseAdapter {
    private final HexagonTile hexTile;
    private final GameController gameController;
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
            System.out.println("Hexagone cliqué à la position : " + position);

            // Appeler le GameController pour placer une nouvelle tuile à cet emplacement
            gameController.placeTile(position);

            // Générer la prochaine tuile après avoir placé celle-ci
            gameController.generateNextTile();
        } else {
            System.out.println("Position non disponible pour le placement");
        }
    }
}
