package model;

import controller.GameContext;
import view.HexagonTile;
import java.awt.Color;
import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

public class Pocket {
    private GameContext gameContext;
    private Set<Point> tilePositions; // Ensemble des positions des tuiles dans cette poche
    private TerrainType terrainType;   // Type de terrain de cette poche

    /**
     * Constructeur de la classe Pocket.
     *
     * @param gameContext le contexte de jeu pour accéder aux tuiles
     * @param terrainType le type de terrain de cette poche
     */
    public Pocket(GameContext gameContext, TerrainType terrainType) {
        this.gameContext = gameContext;
        this.terrainType = terrainType;
        this.tilePositions = new HashSet<>();
    }

    /**
     * Ajoute une tuile à la poche.
     *
     * @param position la position de la tuile dans la grille
     */
    public void addTile(Point position) {
        tilePositions.add(position);
    }

    /**
     * Vérifie si la poche contient une tuile à la position donnée.
     *
     * @param position la position de la tuile à vérifier
     * @return true si la tuile est dans la poche, false sinon
     */
    public boolean containsTile(Point position) {
        return tilePositions.contains(position);
    }

    /**
     * Récupère le type de terrain de cette poche.
     *
     * @return le type de terrain de la poche
     */
    public TerrainType getTerrainType() {
        return terrainType;
    }

    /**
     * Calcule la taille de la poche en fonction du nombre de tuiles qu'elle contient.
     *
     * @return la taille de la poche
     */
    public int getSize() {
        return tilePositions.size();
    }

    /**
     * Calcule le score de la poche en fonction de sa taille.
     * La règle est que le score est la taille de la poche au carré.
     *
     * @return le score de la poche
     */
    public int calculateScore() {
        return (int) Math.pow(getSize(), 2);
    }

    /**
     * Fusionne cette poche avec une autre poche en ajoutant toutes ses tuiles.
     *
     * @param otherPocket la poche à fusionner avec celle-ci
     */
    public void merge(Pocket otherPocket) {
        tilePositions.addAll(otherPocket.tilePositions);
    }

    /**
     * Applique une couleur de contraste pour visualiser cette poche sur l'interface.
     */
    public void applyContrastColor() {
        Color contrastColor = new Color(255, 255, 255, 128); // Couleur semi-transparente pour visualiser
        for (Point position : tilePositions) {
            HexagonTile tile = gameContext.getHexagonMap().get(position);
            if (tile != null) {
                tile.setContrastColor(contrastColor);
            }
        }
    }
}
