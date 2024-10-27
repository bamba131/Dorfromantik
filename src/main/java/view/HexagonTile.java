package view;

import model.Tile;
import model.TerrainType;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;

public class HexagonTile extends JPanel {
    private Tile tile;
    private Point position;
    private boolean isPlaceholder;
    private Color contrastColor;  // Nouvelle couleur pour le contraste visuel des poches

    public HexagonTile(Point position, boolean isPlaceholder) {
        this.position = position;
        this.isPlaceholder = isPlaceholder;
        this.tile = null;
        this.contrastColor = null;  // Par défaut, pas de contraste
        setPreferredSize(new Dimension(100, 100));
    }

    public Point getPosition() {
        return position;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
        this.isPlaceholder = false;
        repaint();
    }

    public Tile getTile() {
        return tile;
    }

    public boolean isFilled() {
        return this.tile != null;
    }

    // Nouvelle méthode pour définir la couleur de contraste
    public void setContrastColor(Color color) {
        this.contrastColor = color;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int largeRadius = isPlaceholder ? 40 : 50;

        Shape largeHexagon = createHexagon(centerX, centerY, largeRadius);
        g2d.setClip(largeHexagon);

        if (tile != null) {
            drawTerrainSegments(g2d, centerX, centerY, largeRadius);
        } else {
            g2d.setColor(Color.LIGHT_GRAY);
            g2d.fill(largeHexagon);
        }

        g2d.setClip(null);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(3));
        g2d.draw(largeHexagon);

        // Appliquer la couleur contrastée si elle est définie
        if (contrastColor != null) {
            g2d.setColor(new Color(contrastColor.getRGB() & 0xFFFFFF | 0x66000000, true));
            g2d.fill(largeHexagon);
        }
    }

    private void drawTerrainSegments(Graphics2D g2d, int centerX, int centerY, int radius) {
        for (int i = 0; i < 6; i++) {
            TerrainType terrain = tile.getTerrainForSegment(i);
            g2d.setColor(getTerrainColor(terrain));
            g2d.fillArc(centerX - radius, centerY - radius, 2 * radius, 2 * radius, 60 * i, 60);
        }
    }

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

    private Color getTerrainColor(TerrainType terrain) {
        if (terrain == null) {
            return Color.WHITE;
        }
        switch (terrain) {
            case MER: return Color.BLUE;
            case CHAMP: return Color.YELLOW;
            case PRE: return Color.GREEN;
            case FORET: return new Color(34, 139, 34);
            case MONTAGNE: return Color.GRAY;
            default: return Color.WHITE;
        }
    }
}
