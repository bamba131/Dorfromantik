package controller;

import view.HexagonTile;
import view.GameView;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Point;
import java.util.Set;

public class HexagonMouseListener extends MouseAdapter {

    private final HexagonTile hexTile;
    private final GameView gameView;
    private final Set<Point> availablePositions;

    public HexagonMouseListener(HexagonTile hexTile, GameView gameView, Set<Point> availablePositions) {
        this.hexTile = hexTile;
        this.gameView = gameView;
        this.availablePositions = availablePositions;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point position = hexTile.getPosition();
        if (availablePositions.contains(position)) {
            gameView.placeTile(position);
        }
    }
}
