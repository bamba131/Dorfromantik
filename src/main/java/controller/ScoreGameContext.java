package controller;

import model.Tile;
import model.TerrainType;
import model.Pocket;
import view.HexagonTile;

import javax.swing.*;
import java.awt.Point;
import java.util.*;

/**
 * La classe ScoreGameContext gère le calcul et l'affichage du score dans le jeu.
 * Elle crée des "Pockets" basées sur les tuiles placées et leurs types de terrain,
 * et calcule le score total en fonction de la taille de chaque Pocket.
 */
public class ScoreGameContext {
    private GameContext gameContext;
    private int score;
    private JLabel scoreLabel;
    private Map<Point, Pocket> pocketMap;  // Map des Pockets pour chaque position de tuile
    private Map<TerrainType, List<Pocket>> pocketsByTerrainType;  // Ensemble de toutes les poches par type de terrain

    /**
     * Constructeur pour ScoreGameContext.
     *
     * @param gameContext le contexte du jeu qui contient l'état de la grille
     * @param scoreLabel  le JLabel pour afficher le score
     */
    public ScoreGameContext(GameContext gameContext, JLabel scoreLabel) {
        this.gameContext = gameContext;
        this.scoreLabel = scoreLabel;
        this.pocketMap = new HashMap<>();
        this.pocketsByTerrainType = new HashMap<>();
        this.score = 0;
    }

    /**
     * Méthode principale pour recalculer le score en reconstruisant toutes les poches.
     * Elle parcourt les tuiles dans la grille et crée des poches par type de terrain.
     */
    public void calculateScore() {
        score = 0;
        pocketsByTerrainType.clear();

        // Parcourt chaque tuile dans la grille pour créer des poches par type de terrain
        for (Map.Entry<Point, HexagonTile> entry : gameContext.getHexagonMap().entrySet()) {
            Point position = entry.getKey();
            HexagonTile hexTile = entry.getValue();

            if (hexTile.isFilled()) {
                Tile tile = hexTile.getTile();
                
                // Crée des poches pour chaque type de terrain de la tuile
                addTileToPockets(tile.getTerrain(0), position);  // Premier terrain
                if (tile.hasTwoTerrains()) {  // Deuxième terrain si disponible
                    addTileToPockets(tile.getTerrain(1), position);
                }
            }
        }

        // Calcule le score total en additionnant le score de chaque poche
        System.out.println("Poches et leurs tailles:");
        for (List<Pocket> pockets : pocketsByTerrainType.values()) {
            for (Pocket pocket : pockets) {
                int pocketSize = pocket.getSize();
                score += pocket.calculateScore();
                System.out.println("Poche de taille " + pocketSize + " ajoutant " + pocket.calculateScore() + " points au score.");
            }
        }

        updateScoreDisplay();
    }

    /**
     * Ajoute une tuile à une poche de terrains en regroupant les tuiles adjacentes.
     * Si des poches existent autour, fusionne ces poches.
     *
     * @param terrainType Le type de terrain de la tuile.
     * @param position    La position de la tuile.
     */
    private void addTileToPockets(TerrainType terrainType, Point position) {
        List<Pocket> pockets = pocketsByTerrainType.computeIfAbsent(terrainType, k -> new ArrayList<>());
        Pocket foundPocket = null;
        List<Pocket> pocketsToMerge = new ArrayList<>();

        for (Pocket pocket : pockets) {
            for (Point adj : gameContext.getAdjacentPositions(position)) {
                if (pocket.containsTile(adj)) {
                    if (foundPocket == null) {
                        foundPocket = pocket;
                        foundPocket.addTile(position);
                    } else {
                        foundPocket.merge(pocket);
                        pocketsToMerge.add(pocket);
                    }
                    break;
                }
            }
        }

        if (foundPocket == null) {
            Pocket newPocket = new Pocket(gameContext, terrainType);
            newPocket.addTile(position);
            pockets.add(newPocket);
            pocketMap.put(position, newPocket);
        }

        pockets.removeAll(pocketsToMerge);
    }

    /**
     * Met à jour l'affichage du score dans GameView.
     */
    private void updateScoreDisplay() {
        System.out.println("Score mis à jour : " + score);
        scoreLabel.setText("Score: " + score);
    }

    /**
     * Retourne le score actuel.
     *
     * @return le score actuel
     */
    public int getScore() {
        return score;
    }
}
