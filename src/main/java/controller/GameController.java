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

    public GameController(GameContext gameContext, JPanel gridPanel, HexagonTile nextTilePreview) {
        this.gameContext = gameContext;
        this.gridPanel = gridPanel;
        this.hexagonMap = gameContext.getHexagonMap();
        this.availablePositions = gameContext.getAvailablePositions();
        this.nextTilePreview = nextTilePreview;

        this.dbManager = new TileDatabaseManager();
        this.tileIndex = 0;

        loadSeries(1); // Charger la série par défaut (ex. série 1)
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

            System.out.println("Placement de la tuile avec ID : " + (nextTile != null ? nextTile.getId() : "null") + " à la position : " + position);
            
            hexTile.setTile(nextTile);  // Place la tuile actuelle
            gridPanel.revalidate();
            gridPanel.repaint();

            availablePositions.remove(position);

            Point[] adjacentPositions = getAdjacentPositions(position);
            for (Point adj : adjacentPositions) {
                if (!hexagonMap.containsKey(adj)) {
                    availablePositions.add(adj);
                    addHexagonTile(adj, gridPanel, 50, null, null);
                }
            }

            gameContext.repaintGrid(gridPanel);
            generateNextTile();  // Génère la tuile suivante
        }
    }



    public void initializeGame(CameraController cameraController) {
        generateNextTile();  // Génère la première tuile et assigne une tuile valide à `nextTile`
        
        Tile initialTile = getNextTile();  // Récupère la tuile générée
        if (initialTile == null) {
            System.out.println("Erreur : aucune tuile initiale générée.");
            return;  // Arrête l'initialisation si aucune tuile n'a été générée
        }
        
        System.out.println("ID de la tuile initiale générée : " + initialTile.getId());  // Affiche l'ID de la tuile initiale
        
        int centerX = gridPanel.getPreferredSize().width / 2;
        int centerY = gridPanel.getPreferredSize().height / 2;
        
        Point initialPosition = new Point(0, 0);
        initialPosition.setLocation(centerX / 50, centerY / 50);  // Calcule la position centrale
        
        placeInitialTile(initialPosition, cameraController, initialTile);  // Place la première tuile
        generateNextTile();
    
    }
    
    

    public void placeInitialTile(Point position, CameraController cameraController, Tile tile) {
        if (tile == null) {
            System.out.println("Erreur : tuile initiale non définie.");
            return;
        }
    
        System.out.println("Placement de la tuile initiale avec ID : " + tile.getId() + " à la position : " + position);
    
        addHexagonTile(position, gridPanel, 50, cameraController, tile);  // Place la première tuile
        availablePositions.remove(position);  // Marque la position comme occupée
    
        Point[] adjacentPositions = getAdjacentPositions(position);
        for (Point adj : adjacentPositions) {
            if (!hexagonMap.containsKey(adj)) {
                availablePositions.add(adj);
                addHexagonTile(adj, gridPanel, 50, cameraController, null);  // Placeholder vide pour les positions adjacentes
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
    
        boolean isPlaceholder = (tile == null);  // Si tile est null, c'est un placeholder
        HexagonTile hexTile = new HexagonTile(position, isPlaceholder);
    
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
        if (tileIndex < currentTiles.size()) {
            nextTile = currentTiles.get(tileIndex++);
            System.out.println("Génération de la prochaine tuile avec ID : " + nextTile.getId() + " (index " + tileIndex + ")");
            updatePreview();  // Met à jour l'aperçu de la tuile suivante
        } else {
            nextTile = null;  // Fin de la série, plus de tuiles à placer
            updatePreview();  // Met à jour l'aperçu pour refléter l'absence de prochaine tuile
            System.out.println("Fin de la série. Plus de tuiles à placer.");
        }
    }
    
    
    

    private void updatePreview() {
        if (nextTilePreview != null) {
            if (nextTile != null) {
                nextTilePreview.setTile(nextTile);  // Met à jour avec une tuile valide
            } else {
                nextTilePreview.setTile(null);  // Affiche un placeholder ou un message si `nextTile` est null
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
