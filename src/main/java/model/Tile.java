package model;

public class Tile {
    private int id;
    private TerrainType[] terrains;
    private int segmentsForTerrain1;
    private int rotation;

    public Tile(int id, TerrainType terrain1, TerrainType terrain2, int segmentsForTerrain1) {
        this.id = id;
        this.terrains = new TerrainType[]{terrain1, terrain2};
        this.segmentsForTerrain1 = segmentsForTerrain1;
        this.rotation = 0;
    }

    public TerrainType getTerrain(int index) {
        if (index >= 0 && index < terrains.length) {
            return terrains[index];
        }
        return null;
    }

    public TerrainType getTerrainForSegment(int segmentIndex) {
        int adjustedIndex = (segmentIndex - rotation + 6) % 6;
        if (adjustedIndex < segmentsForTerrain1) {
            return terrains[0];
        } else {
            return terrains[1];
        }
    }

    public void rotateClockwise() {
        rotation = (rotation + 1) % 6;
    }

    public void rotateCounterClockwise() {
        rotation = (rotation + 5) % 6;
    }

    public int getRotation() {
        return rotation;
    }

    public int getId() {
        return id;
    }

    /**
     * Vérifie si la tuile a deux terrains différents.
     *
     * @return true si la tuile a deux terrains, sinon false.
     */
    public boolean hasTwoTerrains() {
        return terrains[1] != null;
    }
}
