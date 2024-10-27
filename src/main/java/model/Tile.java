package model;

public class Tile {
    private int id;  // ID de la tuile
    private TerrainType[] terrains;  // Tableau contenant deux types de terrains (ex. MER, FORET)
    private int segmentsForTerrain1;  // Nombre de segments pour le premier terrain
    private int rotation;  // Rotation de la tuile (en multiple de 60 degrés)

    public Tile(int id, TerrainType terrain1, TerrainType terrain2, int segmentsForTerrain1) {
        this.id = id;
        this.terrains = new TerrainType[]{terrain1, terrain2};
        this.segmentsForTerrain1 = segmentsForTerrain1;
        this.rotation = 0;  // Initialisation de la rotation à 0
    }

    // Renvoie le terrain pour l'index donné (0 ou 1)
    public TerrainType getTerrain(int index) {
        if (index >= 0 && index < terrains.length) {
            return terrains[index];
        }
        return null;  // Retourne null si l'index est invalide
    }

    // Méthode pour obtenir le terrain associé à un segment spécifique (prend en compte la rotation)
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
        rotation = (rotation + 5) % 6;  // Tourner dans le sens inverse
    }

    public int getRotation() {
        return rotation;
    }

    public int getId() {
        return id;
    }
}
