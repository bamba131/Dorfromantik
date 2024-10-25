package controller;

import model.Tile;
import view.HexagonTile;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GameController {
    private Map<Point, HexagonTile> hexagonMap; // Carte pour stocker les tuiles par position
    private Set<Point> availablePositions;      // Ensemble pour suivre les positions disponibles
    private JPanel gridPanel;

    public GameController(GameContext gameContext, JPanel gridPanel, Tile nextTile, HexagonTile nextTilePreview) {
        this.gridPanel = gridPanel;

        // Initialiser hexagonMap et availablePositions
        this.hexagonMap = new HashMap<>();
        this.availablePositions = new HashSet<>();
    }

    public void placeInitialTile(Point position, CameraController cameraController, Tile tile) {
        // Place la première tuile pleine au point central
        addHexagonTile(position, gridPanel, 50, cameraController, tile); 
        availablePositions.remove(position);

        // Ajouter des placeholders autour de la première tuile
        Point[] adjacentPositions = getAdjacentPositions(position);
        for (Point adj : adjacentPositions) {
            if (!hexagonMap.containsKey(adj)) {
                availablePositions.add(adj);
                addHexagonTile(adj, gridPanel, 50, cameraController, null); // Passer `null` pour les placeholders
            }
        }
    }

    public void addHexagonTile(Point position, JPanel panel, int hexSize, CameraController cameraController, Tile tile) {
        int xOffset = position.x * (int) (hexSize * 3 / 2);
        int yOffset = position.y * (int) (Math.sqrt(3) * hexSize);

        Point viewOffset = cameraController.getViewOffset();
        xOffset += viewOffset.x;
        yOffset += viewOffset.y;

        if (position.x % 2 != 0) {
            yOffset += (int) (Math.sqrt(3) * hexSize / 2);
        }

        HexagonTile hexTile = new HexagonTile(position);
        hexTile.setTile(tile); // Place la tuile fournie, ou null pour un placeholder
        hexTile.setBounds(xOffset, yOffset, hexSize, hexSize);

        // Ajout de l'écouteur pour gérer le clic sur l'hexagone
        hexTile.addMouseListener(new HexagonMouseListener(hexTile, this, availablePositions, cameraController));

        hexagonMap.put(position, hexTile);
        panel.add(hexTile);
        panel.revalidate();
        panel.repaint();
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
}
