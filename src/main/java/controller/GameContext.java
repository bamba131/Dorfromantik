package controller;

import view.HexagonTile;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GameContext {
    private Map<Point, HexagonTile> hexagonMap;  // Stocke la grille
    private Set<Point> availablePositions;  // Positions libres pour les placeholders
    private Point offset;  // Offset global pour la grille

    public GameContext() {
        this.hexagonMap = new HashMap<>();
        this.availablePositions = new HashSet<>();
        this.offset = new Point(0, 0);  // Initialisation de l’offset à (0, 0)
    }

    // Getters pour la grille, les positions et l'offset
    public Map<Point, HexagonTile> getHexagonMap() {
        return hexagonMap;
    }

    public Set<Point> getAvailablePositions() {
        return availablePositions;
    }

    public Point getOffset() {
        return offset;
    }

    // Méthode pour mettre à jour l'offset de la grille
    public void updateOffset(int deltaX, int deltaY) {
        offset.translate(deltaX, deltaY);
    }
}
