// src/main/java/controller/GameController.java
package controller;

import model.Tile;
import model.TileDatabaseManager;
import view.HexagonTile;

import javax.swing.*;
import java.awt.Point;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GameController implements TilePlacer {
    private Map<Point, HexagonTile> hexagonMap;
    private Set<Point> availablePositions;
    private JPanel gridPanel;
    private Tile nextTile;
    private HexagonTile nextTilePreview;
    private GameContext gameContext;
    private TileDatabaseManager dbManager;
    private List<Tile> currentTiles;
    private int tileIndex;
    private ScoreGameContext scoreGameContext;

    private int placedTileCount = 0;
    private int seriesId;
    private GameEndListener gameEndListener;

    /**
     * Constructeur de la classe GameController.
     * Initialise les composants de la partie, y compris le contexte de jeu, la série de tuiles,
     * et le gestionnaire de fin de partie.
     *
     * @param gameContext      le contexte de jeu contenant l'état de la grille
     * @param gridPanel        le panneau d'affichage de la grille
     * @param nextTilePreview  la tuile d'aperçu pour la prochaine tuile
     * @param scoreLabel       le label d'affichage du score
     * @param seriesId         l'identifiant de la série de tuiles
     * @param gameEndListener  le listener pour gérer la fin de la partie
     */
    public GameController(GameContext gameContext, JPanel gridPanel, HexagonTile nextTilePreview, JLabel scoreLabel, int seriesId, GameEndListener gameEndListener) {
        this.seriesId = seriesId;
        this.gameContext = gameContext;
        this.gridPanel = gridPanel;
        this.hexagonMap = gameContext.getHexagonMap();
        this.availablePositions = gameContext.getAvailablePositions();
        this.nextTilePreview = nextTilePreview;
        this.dbManager = new TileDatabaseManager();
        this.tileIndex = 0;
        this.scoreGameContext = new ScoreGameContext(gameContext, scoreLabel);
        this.gameEndListener = gameEndListener;

        loadSeries(seriesId);
        updatePreview();
    }

    public void loadSeries(int idSeries) {
        currentTiles = dbManager.getTilesBySeries(idSeries);
        tileIndex = 0;
        System.out.println("Série " + idSeries + " chargée avec " + currentTiles.size() + " tuiles.");
    }

    @Override
    public void placeTile(Point position) {
        if (availablePositions.contains(position)) {
            HexagonTile hexTile = hexagonMap.get(position);
            if (hexTile == null) {
                System.out.println("Erreur: hexTile est null à la position : " + position);
                return;
            }

            hexTile.setTile(nextTile);
            gridPanel.revalidate();
            gridPanel.repaint();
            availablePositions.remove(position);

            // Mettre à jour les positions disponibles autour de la tuile ajoutée
            for (Point adj : getAdjacentPositions(position)) {
                if (!hexagonMap.containsKey(adj)) {
                    availablePositions.add(adj);
                    addHexagonTile(adj, gridPanel, 50, null, null);
                }
            }

            gameContext.repaintGrid(gridPanel);
            
            // Calculer les scores et mettre à jour la visualisation des poches
            scoreGameContext.calculateScore();

            placedTileCount++;
            if (placedTileCount > 48) {
                endGame();
                return;
            }

            generateNextTile();
        }
    }

    private void endGame() {
        int finalScore = scoreGameContext.getScore();
        new SendScore().insertscore(seriesId, finalScore);

        if (gameEndListener != null) {
            gameEndListener.onGameEnd(finalScore);
        }
    }

    public void initializeGame(CameraController cameraController) {
        generateNextTile();
        Tile initialTile = getNextTile();
        if (initialTile == null) {
            System.out.println("Erreur : aucune tuile initiale générée.");
            return;
        }

        int centerX = gridPanel.getPreferredSize().width / 2;
        int centerY = gridPanel.getPreferredSize().height / 2;
        Point initialPosition = new Point(centerX / 50, centerY / 50);
        placeInitialTile(initialPosition, cameraController, initialTile);
        scoreGameContext.calculateScore();
        generateNextTile();
    }

    public void placeInitialTile(Point position, CameraController cameraController, Tile tile) {
        if (tile == null) {
            System.out.println("Erreur : tuile initiale non définie.");
            return;
        }

        addHexagonTile(position, gridPanel, 50, cameraController, tile);
        availablePositions.remove(position);

        for (Point adj : getAdjacentPositions(position)) {
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

        boolean isPlaceholder = (tile == null);
        HexagonTile hexTile = new HexagonTile(position, isPlaceholder);

        if (tile != null) {
            hexTile.setTile(tile);
        }

        hexTile.setBounds(xOffset, yOffset, hexSize, hexSize);
        hexTile.addMouseListener(new HexagonMouseListener(hexTile, this, availablePositions));
        hexagonMap.put(position, hexTile);
        panel.add(hexTile);
        panel.revalidate();
        panel.repaint();
    }

    public void generateNextTile() {
        if (tileIndex < currentTiles.size()) {
            nextTile = currentTiles.get(tileIndex++);
            updatePreview();
        } else {
            nextTile = null;
            updatePreview();
        }
    }

    private void updatePreview() {
        if (nextTilePreview != null) {
            if (nextTile != null) {
                nextTilePreview.setTile(nextTile);
            } else {
                nextTilePreview.setTile(null);
            }
            nextTilePreview.repaint();
        }
    }

    public Tile getNextTile() {
        return nextTile;
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
}
