package model;

import java.util.Random;

public class Tile {
    public enum TerrainType {
        MER, CHAMP, PRE, FORET, MONTAGNE
    }

    private TerrainType[] terrains;  // 4 terrains pour chaque quart de la tuile
    private static final Random random = new Random();

    public Tile() {
        this.terrains = new TerrainType[4];
        generateTerrains();
    }

    // Génère des terrains aléatoires pour les 4 parties
    private void generateTerrains() {
        for (int i = 0; i < 4; i++) {
            terrains[i] = generateRandomTerrain();
        }
    }

    // Génère un terrain aléatoire selon certaines probabilités
    private TerrainType generateRandomTerrain() {
        int rand = random.nextInt(100);  // Pourcentage pour chaque terrain

        if (rand < 20) {
            return TerrainType.MER;       // 20% MER
        } else if (rand < 40) {
            return TerrainType.CHAMP;     // 20% CHAMP
        } else if (rand < 60) {
            return TerrainType.PRE;       // 20% PRE
        } else if (rand < 80) {
            return TerrainType.FORET;     // 20% FORET
        } else {
            return TerrainType.MONTAGNE;  // 20% MONTAGNE
        }
    }

    public TerrainType getTerrain(int index) {
        if (index >= 0 && index < 4) {
            return terrains[index];
        }
        return null;
    }

    @Override
    public String toString() {
        return "Terrains : " + terrains[0] + ", " + terrains[1] + ", " + terrains[2] + ", " + terrains[3];
    }
}
