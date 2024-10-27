package controller;

import model.Tile;
import model.TerrainType;
import model.Pocket;
import view.HexagonTile;

import javax.swing.*;
import java.awt.Point;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ScoreGameContext {
    private GameContext gameContext;
    private int score;
    private JLabel scoreLabel;
    private Map<Point, Pocket> pocketMap;  // Map des Pockets pour chaque tuile
    private Set<Pocket> pockets;  // Ensemble de toutes les Pockets

    public ScoreGameContext(GameContext gameContext, JLabel scoreLabel) {
        this.gameContext = gameContext;
        this.scoreLabel = scoreLabel;
        this.pocketMap = new HashMap<>();
        this.pockets = new HashSet<>();
        this.score = 0;
    }

    // Méthode principale pour recalculer le score en reconstruisant toutes les Pockets
    public void calculateScore() {
        score = 0;
        pockets.clear();
        pocketMap.clear();

        // Parcourt chaque tuile dans la grille pour créer des Pockets par type de terrain
        for (Map.Entry<Point, HexagonTile> entry : gameContext.getHexagonMap().entrySet()) {
            Point position = entry.getKey();
            HexagonTile hexTile = entry.getValue();

            if (hexTile.isFilled()) {
                Tile tile = hexTile.getTile();
                
                // Crée ou fusionne une Pocket pour chaque type de terrain de la tuile
                for (int segment = 0; segment < 6; segment++) {
                    TerrainType terrainType = tile.getTerrainForSegment(segment);
                    if (terrainType != null) {
                        Pocket pocket = findOrCreatePocket(position, segment, terrainType);
                        pocketMap.put(position, pocket);
                    }
                }
            }
        }

        // Calcule le score total en additionnant le score de chaque Pocket
        System.out.println("Pockets and their sizes:");
        for (Pocket pocket : pockets) {
            System.out.println("Pocket with terrain " + pocket.getTerrainType() + " has size " + pocket.getSize());
            score += pocket.calculateScore();
        }

        updateScoreDisplay();
    }

    // Recherche ou crée une Pocket pour un terrain spécifique dans une tuile
    private Pocket findOrCreatePocket(Point position, int segment, TerrainType terrainType) {
        Pocket newPocket = new Pocket(terrainType);
        newPocket.addTile(position);

        // Vérifie les voisins pour fusionner les Pockets si les segments se touchent
        for (int adjSegment = 0; adjSegment < 6; adjSegment++) {
            Point neighborPos = getAdjacentPositionForSegment(position, adjSegment);
            Pocket neighborPocket = pocketMap.get(neighborPos);

            if (neighborPocket != null && neighborPocket.getTerrainType() == terrainType) {
                // Vérifie si les segments de terrain se touchent avant de fusionner les Pockets
                if (areSegmentsConnected(position, neighborPos, segment, adjSegment, terrainType)) {
                    System.out.println("Merging pocket at " + position + " with pocket at " + neighborPos + " for terrain " + terrainType);
                    neighborPocket.merge(newPocket);
                    pockets.remove(newPocket);  // Supprime la Pocket fusionnée
                    return neighborPocket;  // Retourne la Pocket fusionnée
                }
            }
        }

        System.out.println("New pocket created at " + position + " for terrain " + terrainType);
        pockets.add(newPocket);  // Ajoute la nouvelle Pocket si aucune fusion n'a eu lieu
        return newPocket;
    }

    // Vérifie si les segments de deux tuiles se touchent et partagent le même type de terrain
    private boolean areSegmentsConnected(Point position1, Point position2, int segment1, int segment2, TerrainType terrainType) {
        Tile tile1 = gameContext.getHexagonMap().get(position1).getTile();
        Tile tile2 = gameContext.getHexagonMap().get(position2).getTile();

        if (tile1 == null || tile2 == null) return false;

        TerrainType terrainSegment1 = tile1.getTerrainForSegment(segment1);
        TerrainType terrainSegment2 = tile2.getTerrainForSegment(segment2);

        boolean connected = terrainSegment1 == terrainType && terrainSegment2 == terrainType;
        if (connected) {
            System.out.println("Segments connected between " + position1 + " and " + position2 + " for terrain " + terrainType);
        }
        return connected;
    }

    // Obtenir la position adjacente pour un segment spécifique (0 à 5)
    private Point getAdjacentPositionForSegment(Point position, int segment) {
        if (position.x % 2 == 0) {
            switch (segment) {
                case 0: return new Point(position.x + 1, position.y);
                case 1: return new Point(position.x + 1, position.y - 1);
                case 2: return new Point(position.x, position.y - 1);
                case 3: return new Point(position.x - 1, position.y - 1);
                case 4: return new Point(position.x - 1, position.y);
                case 5: return new Point(position.x, position.y + 1);
                default: return position;
            }
        } else {
            switch (segment) {
                case 0: return new Point(position.x + 1, position.y);
                case 1: return new Point(position.x + 1, position.y + 1);
                case 2: return new Point(position.x, position.y + 1);
                case 3: return new Point(position.x - 1, position.y + 1);
                case 4: return new Point(position.x - 1, position.y);
                case 5: return new Point(position.x, position.y - 1);
                default: return position;
            }
        }
    }

    // Met à jour l'affichage du score dans GameView
    private void updateScoreDisplay() {
        System.out.println("Updated Score: " + score); // Débogage du score
        scoreLabel.setText("Score: " + score);
    }

    public int getScore() {
        return score;
    }
}
