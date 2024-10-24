package view;

import model.Tile;
import model.TerrainType;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;

public class HexagonTile extends JPanel {

    private Tile tile;
    private Point position;

    public HexagonTile(Point position) {
        this.position = position;
        this.tile = null;
        setPreferredSize(new Dimension(100, 100));  // Ajuste selon la taille de la tuile
    }

    public Point getPosition() {
        return position;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
        repaint();
    }

    public boolean isFilled() {
        return this.tile != null;  // Vérifie si la tuile a déjà été placée
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int largeRadius = 50;

        // Créer la zone de découpe pour le grand hexagone
        Shape largeHexagon = createHexagon(centerX, centerY, largeRadius);
        g2d.setClip(largeHexagon);

        if (tile != null) {
            // Dessiner les 6 segments de terrain en fonction des proportions
            drawTerrainSegments(g2d, centerX, centerY, largeRadius);
        } else {
            g2d.setColor(Color.LIGHT_GRAY);  // Couleur par défaut pour une case vide
            g2d.fill(largeHexagon);
        }

        // Dessiner la bordure de l'hexagone
        g2d.setClip(null);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(3));  // Bordure épaisse
        g2d.draw(largeHexagon);
    }

    // Dessiner les 6 segments de terrain avec une répartition variée
    private void drawTerrainSegments(Graphics2D g2d, int centerX, int centerY, int radius) {
        // Déterminer combien de segments sont attribués à chaque terrain
        int segmentsTerrain1 = tile.getSegmentsForTerrain(0);

        // Dessiner les segments adjacents pour chaque terrain
        for (int i = 0; i < 6; i++) {
            if (i < segmentsTerrain1) {
                g2d.setColor(getTerrainColor(tile.getTerrain(0)));  // Premier terrain
            } else {
                g2d.setColor(getTerrainColor(tile.getTerrain(1)));  // Deuxième terrain
            }
            g2d.fillArc(centerX - radius, centerY - radius, 2 * radius, 2 * radius, 60 * i, 60);
        }
    }

    // Créer la forme hexagonale
    private Shape createHexagon(int centerX, int centerY, int radius) {
        Path2D hexagon = new Path2D.Double();
        for (int i = 0; i < 6; i++) {
            double angle = Math.toRadians(60 * i);
            double x = centerX + radius * Math.cos(angle);
            double y = centerY + radius * Math.sin(angle);
            if (i == 0) {
                hexagon.moveTo(x, y);
            } else {
                hexagon.lineTo(x, y);
            }
        }
        hexagon.closePath();
        return hexagon;
    }

    // Obtenir la couleur en fonction du type de terrain
    private Color getTerrainColor(TerrainType terrain) {
        if (terrain == null) {
            return Color.WHITE;  // Par défaut si le terrain est nul
        }

        switch (terrain) {
            case MER:
                return Color.BLUE;
            case CHAMP:
                return Color.YELLOW;
            case PRE:
                return Color.GREEN;
            case FORET:
                return new Color(34, 139, 34);  // Vert foncé
            case MONTAGNE:
                return Color.GRAY;
            default:
                return Color.WHITE;
        }
    }
}
