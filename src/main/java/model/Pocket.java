// src/main/java/model/Pocket.java
package model;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

/**
 * La classe <code>Pocket</code> représente un groupe de tuiles adjacentes
 * d'un même type de terrain dans le jeu.
 */
public class Pocket {
    private TerrainType terrainType;  // Type de terrain de cette Pocket
    private Set<Point> tiles;  // Ensemble des positions des tuiles de la Pocket

    /**
     * Constructeur de la classe <code>Pocket</code>.
     *
     * @param terrainType Le type de terrain associé à cette Pocket.
     */
    public Pocket(TerrainType terrainType) {
        this.terrainType = terrainType;
        this.tiles = new HashSet<>();
    }

    /**
     * Récupère le type de terrain de cette Pocket.
     *
     * @return Le type de terrain de cette Pocket.
     */
    public TerrainType getTerrainType() {
        return terrainType;
    }

    /**
     * Obtient la taille de cette Pocket, c'est-à-dire le nombre de tuiles.
     *
     * @return La taille de la Pocket.
     */
    public int getSize() {
        return tiles.size();
    }

    /**
     * Ajoute une tuile à cette Pocket à une position donnée.
     *
     * @param position La position de la tuile à ajouter.
     */
    public void addTile(Point position) {
        tiles.add(position);
    }

    /**
     * Vérifie si une tuile à une position donnée est présente dans cette Pocket.
     *
     * @param position La position de la tuile à vérifier.
     * @return <code>true</code> si la tuile est présente, sinon <code>false</code>.
     */
    public boolean containsTile(Point position) {
        return tiles.contains(position);
    }

    /**
     * Fusionne cette Pocket avec une autre Pocket si elles partagent le même type de terrain.
     *
     * @param other La Pocket à fusionner.
     */
    public void merge(Pocket other) {
        if (this.terrainType == other.terrainType) {
            this.tiles.addAll(other.tiles);
        }
    }

    /**
     * Calcule le score de cette Pocket basé sur la taille au carré.
     *
     * @return Le score de la Pocket.
     */
    public int calculateScore() {
        return getSize() * getSize();  // La taille au carré donne le score
    }
}
