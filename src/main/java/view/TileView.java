package view;

import model.Tile;
import model.TerrainType;  // Ajout de l'import pour TerrainType

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;

public class TileView extends JPanel {

    private Tile tile;

    public TileView(Tile tile) {
        this.tile = tile;
        setPreferredSize(new Dimension(100, 100));  // Ajuste selon la taille de la tuile
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

        // Diviser l'hexagone en 4 parties (quart de cercle) pour chaque terrain
        drawTerrainQuadrants(g2d, centerX, centerY, largeRadius);

        // Dessiner la bordure de l'hexagone
        g2d.setClip(null);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(3));  // Bordure épaisse
        g2d.draw(largeHexagon);
    }

    // Dessiner les 4 quadrants de terrain
    private void drawTerrainQuadrants(Graphics2D g2d, int centerX, int centerY, int radius) {
        for (int i = 0; i < 4; i++) {
            g2d.setColor(getTerrainColor(tile.getTerrain(i)));
            g2d.fillArc(centerX - radius, centerY - radius, 2 * radius, 2 * radius, 90 * i, 90);
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
