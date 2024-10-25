package controller;

import view.HexagonTile;
import java.awt.Point;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.swing.JPanel;

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

    // Ajout de la méthode pour recalculer les positions de la grille en fonction de l'offset
    public void repaintGrid(JPanel gridPanel) {
        for (Map.Entry<Point, HexagonTile> entry : hexagonMap.entrySet()) {
            Point position = entry.getKey();
            HexagonTile tile = entry.getValue();

            // Calcule la position avec l'offset
            int xOffset = position.x * (int) (50 * 3 / 2); // Ajuste la distance horizontale
            int yOffset = position.y * (int) (Math.sqrt(3) * 50); // Ajuste la distance verticale

            // Si la colonne est impaire, décale la tuile d'une demi-hauteur d'hexagone
            if (position.x % 2 != 0) {
                yOffset += (int) (Math.sqrt(3) * 50 / 2);
            }

            // Applique l'offset de vue
            xOffset += offset.x;
            yOffset += offset.y;

            // Met à jour la position de l'hexagone
            tile.setBounds(xOffset, yOffset, 50, 50);
        }
        gridPanel.revalidate();
        gridPanel.repaint();
    }
}
