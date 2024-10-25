package controller;

import view.HexagonTile;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Point;
import java.util.Set;

public class HexagonMouseListener extends MouseAdapter {

    private final HexagonTile hexTile;
    private final Set<Point> availablePositions;

    public HexagonMouseListener(HexagonTile hexTile, GameController gameController, Set<Point> availablePositions, CameraController cameraController) {
        this.hexTile = hexTile;
        this.availablePositions = availablePositions;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point position = hexTile.getPosition();
        if (availablePositions.contains(position)) {
            System.out.println("Hexagone cliqué à la position : " + position);
            // Logique de placement de la tuile ou appel à GameController ici si nécessaire
            // Par exemple, nous pourrions générer la tuile suivante directement ici
        } else {
            System.out.println("Position non disponible pour le placement");
        }
    }
}
