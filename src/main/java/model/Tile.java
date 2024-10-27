// src/main/java/model/Tile.java
package model;

/**
 * La classe <code>Tile</code> représente une tuile dans le jeu, qui peut contenir un ou deux types de terrains.
 * Chaque tuile a un identifiant unique, un tableau de types de terrains, et gère sa rotation.
 */
public class Tile {
    private int id;  // ID de la tuile
    private TerrainType[] terrains;  // Tableau contenant deux types de terrains (ex. MER, FORET)
    private int segmentsForTerrain1;  // Nombre de segments pour le premier terrain
    private int rotation;  // Rotation de la tuile (en multiple de 60 degrés)

    /**
     * Constructeur pour créer une nouvelle tuile avec deux types de terrains.
     *
     * @param id                L'identifiant de la tuile.
     * @param terrain1         Le premier type de terrain.
     * @param terrain2         Le deuxième type de terrain.
     * @param segmentsForTerrain1 Le nombre de segments pour le premier terrain.
     */
    public Tile(int id, TerrainType terrain1, TerrainType terrain2, int segmentsForTerrain1) {
        this.id = id;
        this.terrains = new TerrainType[]{terrain1, terrain2};
        this.segmentsForTerrain1 = segmentsForTerrain1;
        this.rotation = 0;  // Initialisation de la rotation à 0
    }

    /**
     * Renvoie le terrain pour l'index donné (0 ou 1).
     *
     * @param index L'index du terrain à récupérer.
     * @return Le type de terrain à l'index spécifié, ou null si l'index est invalide.
     */
    public TerrainType getTerrain(int index) {
        if (index >= 0 && index < terrains.length) {
            return terrains[index];
        }
        return null;  // Retourne null si l'index est invalide
    }

    /**
     * Méthode pour obtenir le terrain associé à un segment spécifique (prend en compte la rotation).
     *
     * @param segmentIndex L'index du segment.
     * @return Le type de terrain associé au segment spécifié.
     */
    public TerrainType getTerrainForSegment(int segmentIndex) {
        int adjustedIndex = (segmentIndex - rotation + 6) % 6;
        if (adjustedIndex < segmentsForTerrain1) {
            return terrains[0];
        } else {
            return terrains[1];
        }
    }

    /**
     * Fait tourner la tuile dans le sens horaire.
     */
    public void rotateClockwise() {
        rotation = (rotation + 1) % 6;
    }

    /**
     * Fait tourner la tuile dans le sens antihoraire.
     */
    public void rotateCounterClockwise() {
        rotation = (rotation + 5) % 6;  // Tourner dans le sens inverse
    }

    /**
     * Renvoie la rotation actuelle de la tuile.
     *
     * @return La rotation de la tuile en multiples de 60 degrés.
     */
    public int getRotation() {
        return rotation;
    }

    /**
     * Renvoie l'identifiant de la tuile.
     *
     * @return L'identifiant de la tuile.
     */
    public int getId() {
        return id;
    }
}
