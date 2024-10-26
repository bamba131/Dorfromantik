package controller;

import view.HexagonTile;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Set;

public class HexagonMouseListener extends MouseAdapter {
    private final HexagonTile hexTile;
    private final TilePlacer tilePlacer;
    private final Set<Point> availablePositions;

    public HexagonMouseListener(HexagonTile hexTile, TilePlacer tilePlacer, Set<Point> availablePositions) {
        this.hexTile = hexTile;
        this.tilePlacer = tilePlacer;
        this.availablePositions = availablePositions;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point position = hexTile.getPosition();
        if (availablePositions.contains(position)) {
            System.out.println("Hexagone cliqué à la position : " + position);
            tilePlacer.placeTile(position);
        } else {
            System.out.println("Position non disponible pour le placement");
        }
    }
}
