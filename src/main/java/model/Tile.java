package model;

public class Tile {
    private int id;  // Ajoute l'attribut id
    private TerrainType[] terrains;  // 2 terrains maximum par tuile
    private int segmentsForTerrain1;
    private int rotation;

    // Constructeur modifié pour inclure l'ID
    public Tile(int id, TerrainType terrain1, TerrainType terrain2, int segmentsForTerrain1) {
        this.id = id;
        this.terrains = new TerrainType[]{terrain1, terrain2};
        this.segmentsForTerrain1 = segmentsForTerrain1;
        this.rotation = 0;
    }

    public void rotateClockwise() {
        rotation = (rotation + 1) % 6;
    }

    public void rotateCounterClockwise() {
        rotation = (rotation + 5) % 6;  // Tourner dans le sens inverse, équivalent à -1 dans un modulo 6
    }
    

    public int getRotation() {
        return rotation;
    }

    public int getId() {
        return id;
    }

    public TerrainType getTerrain(int index) {
        return index >= 0 && index < 2 ? terrains[index] : null;
    }

    public int getSegmentsForTerrain(int index) {
        return index == 0 ? segmentsForTerrain1 : 6 - segmentsForTerrain1;
    }
}
