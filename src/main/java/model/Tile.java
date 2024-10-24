package model;

import java.util.Random;

public class Tile {
    private TerrainType[] terrains;  // 2 terrains maximum par tuile
    private int segmentsForTerrain1;  // Nombre de segments pour le premier terrain
    private static final Random random = new Random();

    public Tile() {
        this.terrains = new TerrainType[2];  // Seulement deux terrains
        generateTerrains();
        assignSegments();
    }

    // Génère deux terrains aléatoires pour la tuile
    private void generateTerrains() {
        terrains[0] = generateRandomTerrain();
        terrains[1] = generateRandomTerrain();

        // Assure que les deux terrains sont différents
        while (terrains[0] == terrains[1]) {
            terrains[1] = generateRandomTerrain();
        }
    }

    // Assigner le nombre de segments pour chaque terrain avec plus de diversité
    private void assignSegments() {
        // Terrain 1 occupe entre 1 et 5 segments, le reste pour le terrain 2
        this.segmentsForTerrain1 = random.nextInt(5) + 1;
    }

    // Génère un terrain aléatoire avec plus de variété dans les probabilités
    private TerrainType generateRandomTerrain() {
        int rand = random.nextInt(100);

        if (rand < 15) {
            return TerrainType.MER;       // 15% MER
        } else if (rand < 30) {
            return TerrainType.CHAMP;     // 15% CHAMP
        } else if (rand < 50) {
            return TerrainType.PRE;       // 20% PRE
        } else if (rand < 75) {
            return TerrainType.FORET;     // 25% FORET
        } else {
            return TerrainType.MONTAGNE;  // 25% MONTAGNE
        }
    }

    public TerrainType getTerrain(int index) {
        if (index >= 0 && index < 2) {
            return terrains[index];
        }
        return null;
    }

    public int getSegmentsForTerrain(int index) {
        if (index == 0) {
            return segmentsForTerrain1;  // Nombre de segments pour le premier terrain
        } else {
            return 6 - segmentsForTerrain1;  // Le reste pour le second terrain
        }
    }
}
