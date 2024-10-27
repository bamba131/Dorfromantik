package model;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

public class Pocket {
    private TerrainType terrainType;  // Type de terrain de cette Pocket
    private Set<Point> tiles;  // Ensemble des positions des tuiles de la Pocket

    public Pocket(TerrainType terrainType) {
        this.terrainType = terrainType;
        this.tiles = new HashSet<>();
    }

    public TerrainType getTerrainType() {
        return terrainType;
    }

    public int getSize() {
        return tiles.size();
    }

    public void addTile(Point position) {
        tiles.add(position);
    }

    public boolean containsTile(Point position) {
        return tiles.contains(position);
    }

    public void merge(Pocket other) {
        if (this.terrainType == other.terrainType) {
            this.tiles.addAll(other.tiles);
        }
    }

    public int calculateScore() {
        return getSize() * getSize();  // La taille au carré donne le score
    }
}
