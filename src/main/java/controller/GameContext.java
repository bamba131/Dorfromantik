package controller;

import view.HexagonTile;
import java.awt.Point;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * La classe GameContext gère l'état et la configuration de la grille hexagonale dans le jeu.
 * Elle stocke les tuiles hexagonales, les positions disponibles et l'offset de vue global.
 * Elle offre des méthodes pour mettre à jour l'offset de vue et redessiner la grille en fonction de cet offset.
 */
public class GameContext {
    private Map<Point, HexagonTile> hexagonMap;  // Stocke la grille
    private Set<Point> availablePositions;  // Positions libres pour les placeholders
    private Point offset;  // Offset global pour la grille

    /**
     * Constructeur de la classe GameContext.
     * Initialise la map des tuiles hexagonales, le set des positions disponibles et l'offset de la vue.
     */
    public GameContext() {
        this.hexagonMap = new HashMap<>();
        this.availablePositions = new HashSet<>();
        this.offset = new Point(0, 0);  // Initialisation de l’offset à (0, 0)
    }

    /**
     * Récupère la map des tuiles hexagonales et leurs positions.
     *
     * @return une map associant les positions aux instances de tuiles hexagonales
     */
    public Map<Point, HexagonTile> getHexagonMap() {
        return hexagonMap;
    }

    /**
     * Récupère les positions disponibles pour le placement des placeholders.
     *
     * @return un set de points représentant les positions disponibles
     */
    public Set<Point> getAvailablePositions() {
        return availablePositions;
    }

    /**
     * Récupère l'offset actuel de la vue.
     *
     * @return un point représentant l'offset actuel de la vue
     */
    public Point getOffset() {
        return offset;
    }

    /**
     * Met à jour l'offset de la vue en ajoutant les valeurs spécifiées.
     *
     * @param deltaX le décalage horizontal de l'offset
     * @param deltaY le décalage vertical de l'offset
     */
    public void updateOffset(int deltaX, int deltaY) {
        offset.translate(deltaX, deltaY);
    }

    /**
     * Recalcule et applique la position de chaque tuile hexagonale en fonction de l'offset de vue.
     * Actualise l'interface graphique en ajustant les positions des tuiles et en repeignant le panneau.
     *
     * @param gridPanel le panneau contenant la grille hexagonale, à redessiner
     */
    public void repaintGrid(JPanel gridPanel) {
        for (Map.Entry<Point, HexagonTile> entry : hexagonMap.entrySet()) {
            Point position = entry.getKey();
            HexagonTile tile = entry.getValue();

            int xOffset = position.x * (int) (50 * 3 / 2);
            int yOffset = position.y * (int) (Math.sqrt(3) * 50);

            if (position.x % 2 != 0) {
                yOffset += (int) (Math.sqrt(3) * 50 / 2);
            }

            xOffset += offset.x;
            yOffset += offset.y;

            tile.setBounds(xOffset, yOffset, 50, 50);
        }
        gridPanel.revalidate();
        gridPanel.repaint();
    }

    /**
     * Retourne les positions adjacentes à une position donnée dans la grille hexagonale.
     *
     * @param position La position centrale pour laquelle obtenir les positions adjacentes.
     * @return Une liste de positions adjacentes à la position spécifiée.
     */
    public List<Point> getAdjacentPositions(Point position) {
        List<Point> adjacentPositions = new ArrayList<>();
        if (position.x % 2 == 0) {
            adjacentPositions.add(new Point(position.x + 1, position.y));
            adjacentPositions.add(new Point(position.x - 1, position.y));
            adjacentPositions.add(new Point(position.x, position.y + 1));
            adjacentPositions.add(new Point(position.x, position.y - 1));
            adjacentPositions.add(new Point(position.x + 1, position.y - 1));
            adjacentPositions.add(new Point(position.x - 1, position.y - 1));
        } else {
            adjacentPositions.add(new Point(position.x + 1, position.y));
            adjacentPositions.add(new Point(position.x - 1, position.y));
            adjacentPositions.add(new Point(position.x, position.y + 1));
            adjacentPositions.add(new Point(position.x, position.y - 1));
            adjacentPositions.add(new Point(position.x + 1, position.y + 1));
            adjacentPositions.add(new Point(position.x - 1, position.y + 1));
        }
        return adjacentPositions;
    }
}
