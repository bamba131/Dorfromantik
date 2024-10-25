package controller;

import model.Tile;
import view.HexagonTile;

import javax.swing.*;
import java.awt.Point;
import java.util.Map;
import java.util.Set;

public class GameController {
    private Map<Point, HexagonTile> hexagonMap;
    private Set<Point> availablePositions;
    private JPanel gridPanel;
    private Tile nextTile;
    private HexagonTile nextTilePreview;
    private GameContext gameContext;

    public GameController(GameContext gameContext, JPanel gridPanel, Tile nextTile, HexagonTile nextTilePreview) {
        this.gameContext = gameContext;
        this.gridPanel = gridPanel;
        this.hexagonMap = gameContext.getHexagonMap();
        this.availablePositions = gameContext.getAvailablePositions();
        this.nextTile = nextTile;
        this.nextTilePreview = nextTilePreview;

        // Mettre à jour la preview initiale
        updatePreview();
    }

    public void placeTile(Point position) {
        // Vérifier si la position est disponible
        if (availablePositions.contains(position)) {
            HexagonTile hexTile = hexagonMap.get(position);
            if (hexTile == null) {
                System.out.println("Erreur: hexTile est null à la position : " + position);
                return;
            }

            // Assigner la tuile actuelle
            hexTile.setTile(nextTile);
            gridPanel.revalidate();
            gridPanel.repaint();

            // Retirer la position des positions disponibles
            availablePositions.remove(position);

            // Mettre à jour les positions adjacentes
            Point[] adjacentPositions = getAdjacentPositions(position);
            for (Point adj : adjacentPositions) {
                if (!hexagonMap.containsKey(adj)) {
                    availablePositions.add(adj);
                    addHexagonTile(adj, gridPanel, 50, null, null);  // Ajouter des placeholders
                }
            }

            // Repeindre la grille
            gameContext.repaintGrid(gridPanel);

            // Générer une nouvelle tuile pour la prochaine preview
            generateNextTile();
        }
    }

    public void initializeGame(CameraController cameraController) {
        Tile initialTile = generateRandomTile();

        int centerX = gridPanel.getPreferredSize().width / 2;
        int centerY = gridPanel.getPreferredSize().height / 2;

        Point initialPosition = new Point(0, 0);
        initialPosition.setLocation(centerX / 50, centerY / 50);

        placeInitialTile(initialPosition, cameraController, initialTile);

        // Générer la première tuile pour la preview
        generateNextTile();
    }

    public void placeInitialTile(Point position, CameraController cameraController, Tile tile) {
        addHexagonTile(position, gridPanel, 50, cameraController, tile);
        availablePositions.remove(position);

        Point[] adjacentPositions = getAdjacentPositions(position);
        for (Point adj : adjacentPositions) {
            if (!hexagonMap.containsKey(adj)) {
                availablePositions.add(adj);
                addHexagonTile(adj, gridPanel, 50, cameraController, null);
            }
        }
    }

    public void addHexagonTile(Point position, JPanel panel, int hexSize, CameraController cameraController, Tile tile) {
        if (position == null || panel == null) {
            System.out.println("Erreur : position ou panel est null");
            return;
        }

        int xOffset = position.x * (int) (hexSize * 3 / 2);
        int yOffset = position.y * (int) (Math.sqrt(3) * hexSize);

        if (cameraController != null) {
            Point viewOffset = cameraController.getViewOffset();
            xOffset += viewOffset.x;
            yOffset += viewOffset.y;
        }

        if (position.x % 2 != 0) {
            yOffset += (int) (Math.sqrt(3) * hexSize / 2);
        }

        HexagonTile hexTile = new HexagonTile(position);
        if (tile != null) {
            hexTile.setTile(tile);
        } else {
            System.out.println("Aucun tile n'a été fourni pour cette position : " + position);
        }

        hexTile.setBounds(xOffset, yOffset, hexSize, hexSize);
        hexTile.addMouseListener(new HexagonMouseListener(hexTile, this, availablePositions));

        hexagonMap.put(position, hexTile);
        panel.add(hexTile);
        panel.revalidate();
        panel.repaint();
    }

    public void generateNextTile() {
        // Génère une nouvelle tuile pour la prochaine pose
        nextTile = new Tile();
        updatePreview();
    }

    private void updatePreview() {
        nextTilePreview.setTile(nextTile);
        nextTilePreview.repaint();
    }

    private Point[] getAdjacentPositions(Point position) {
        if (position.x % 2 == 0) {
            return new Point[]{
                    new Point(position.x + 1, position.y),
                    new Point(position.x - 1, position.y),
                    new Point(position.x, position.y + 1),
                    new Point(position.x, position.y - 1),
                    new Point(position.x + 1, position.y - 1),
                    new Point(position.x - 1, position.y - 1)
            };
        } else {
            return new Point[]{
                    new Point(position.x + 1, position.y),
                    new Point(position.x - 1, position.y),
                    new Point(position.x, position.y + 1),
                    new Point(position.x, position.y - 1),
                    new Point(position.x + 1, position.y + 1),
                    new Point(position.x - 1, position.y + 1)
            };
        }
    }

    private Tile generateRandomTile() {
        return new Tile();
    }

    public Tile getNextTile() {
        return nextTile;
    }
}
