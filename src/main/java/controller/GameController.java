package controller;

import model.Tile;
import view.HexagonTile;
import view.GameView;

import java.awt.*;
import java.util.Map;
import java.util.Set;
import javax.swing.JPanel;

public class GameController {
    private Map<Point, HexagonTile> hexagonMap;
    private Set<Point> availablePositions;
    private Tile nextTile;
    private HexagonTile nextTilePreview;
    private JPanel gridPanel;

    public GameController(Map<Point, HexagonTile> hexagonMap, Set<Point> availablePositions, JPanel gridPanel, Tile nextTile, HexagonTile nextTilePreview) {
        this.hexagonMap = hexagonMap;
        this.availablePositions = availablePositions;
        this.gridPanel = gridPanel;
        this.nextTile = nextTile;
        this.nextTilePreview = nextTilePreview;
    }

    public void placeInitialTile(Point position) {
        addHexagonTile(position, gridPanel, 50);
        availablePositions.remove(position);

        Point[] adjacentPositions = getAdjacentPositions(position);
        for (Point adj : adjacentPositions) {
            if (!hexagonMap.containsKey(adj)) {
                availablePositions.add(adj);
                addHexagonTile(adj, gridPanel, 50);
            }
        }
    }

    public void placeTile(Point position) {
        if (availablePositions.contains(position)) {
            HexagonTile hexTile = hexagonMap.get(position);
            if (hexTile != null && !hexTile.isFilled()) {
                // Placer la tuile actuelle
                hexTile.setTile(nextTile);

                // Générer une nouvelle tuile et mettre à jour la prévisualisation
                nextTile = generateRandomTile();
                nextTilePreview.setTile(nextTile);

                updateAdjacentPositions(position);
            }
        }
    }

    private void addHexagonTile(Point position, JPanel panel, int hexSize) {
        int xOffset = position.x * (int) (hexSize * 3 / 2);  // Décalage horizontal ajusté
        int yOffset = position.y * (int) (Math.sqrt(3) * hexSize);  // Décalage vertical ajusté

        if (position.x % 2 != 0) {
            yOffset += (int) (Math.sqrt(3) * hexSize / 2);
        }

        HexagonTile hexTile = new HexagonTile(position);
        hexTile.setBounds(xOffset, yOffset, hexSize, hexSize);
        hexTile.addMouseListener(new HexagonMouseListener(hexTile, this, availablePositions));

        hexagonMap.put(position, hexTile);
        panel.add(hexTile);
        panel.revalidate();
        panel.repaint();
    }

    private void updateAdjacentPositions(Point position) {
        Point[] adjacentPositions = getAdjacentPositions(position);
        for (Point adj : adjacentPositions) {
            if (!hexagonMap.containsKey(adj)) {
                availablePositions.add(adj);
                addHexagonTile(adj, gridPanel, 50);
            }
        }
    }

    private Point[] getAdjacentPositions(Point position) {
        return new Point[]{
                new Point(position.x + 1, position.y),
                new Point(position.x - 1, position.y),
                new Point(position.x, position.y + 1),
                new Point(position.x, position.y - 1),
                new Point(position.x + 1, position.y - 1),
                new Point(position.x - 1, position.y + 1)
        };
    }

    private Tile generateRandomTile() {
        return new Tile();
    }
}
