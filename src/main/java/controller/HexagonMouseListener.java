package controller;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Set;

import view.HexagonTile;
import view.GameView;

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
        if (availablePositions.contains(hexTile.getPosition())) {
            gameView.placeTile(hexTile.getPosition());
        }
    }
}
