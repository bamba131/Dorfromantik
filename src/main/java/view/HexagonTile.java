package view;

import model.Tile;
import model.TerrainType;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;

/**
 * La classe <code>HexagonTile</code> représente une tuile hexagonale dans le jeu.
 * Elle gère l'affichage d'une tuile avec ses segments de terrain et 
 * indique si la tuile est un placeholder.
 */
public class HexagonTile extends JPanel {
    /** La tuile associée à cet hexagone. */
    private Tile tile;
    
    /** La position de l'hexagone sur la grille. */
    private Point position;
    
    /** Indicateur si l'hexagone est un placeholder. */
    private boolean isPlaceholder;

    /**
     * Constructeur de la classe <code>HexagonTile</code>.
     * 
     * @param position La position de l'hexagone sur la grille.
     * @param isPlaceholder Indique si cet hexagone est un placeholder.
     */
    public HexagonTile(Point position, boolean isPlaceholder) {
        this.position = position;
        this.isPlaceholder = isPlaceholder;
        this.tile = null;
        setPreferredSize(new Dimension(100, 100));
    }

    /**
     * Récupère la position de l'hexagone.
     * 
     * @return La position de l'hexagone.
     */
    public Point getPosition() {
        return position;
    }

    /**
     * Définit la tuile associée à cet hexagone.
     * 
     * @param tile La tuile à associer.
     */
    public void setTile(Tile tile) {
        this.tile = tile;
        this.isPlaceholder = false;  // Une fois la tuile posée, ce n'est plus un placeholder
        repaint();
    }

    /**
     * Récupère la tuile associée à cet hexagone.
     * 
     * @return La tuile associée.
     */
    public Tile getTile() {
        return tile;
    }

    /**
     * Vérifie si l'hexagone est rempli avec une tuile.
     * 
     * @return <code>true</code> si l'hexagone contient une tuile, sinon <code>false</code>.
     */
    public boolean isFilled() {
        return this.tile != null;
    }

    /**
     * Méthode de peinture du composant.
     * Dessine l'hexagone et ses segments de terrain, ou un placeholder si aucun terrain n'est présent.
     * 
     * @param g Le contexte graphique dans lequel dessiner.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int largeRadius = isPlaceholder ? 40 : 50;  // Réduction de taille pour les placeholders

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
    }

    /**
     * Dessine les segments de terrain associés à la tuile.
     * 
     * @param g2d Le contexte graphique dans lequel dessiner.
     * @param centerX La coordonnée X du centre de l'hexagone.
     * @param centerY La coordonnée Y du centre de l'hexagone.
     * @param radius Le rayon de l'hexagone.
     */
    private void drawTerrainSegments(Graphics2D g2d, int centerX, int centerY, int radius) {
        // Parcourt les segments de 0 à 5 pour dessiner chaque segment en fonction du terrain associé
        for (int i = 0; i < 6; i++) {
            TerrainType terrain = tile.getTerrainForSegment(i);  // Récupère le terrain du segment, en prenant en compte la rotation
            g2d.setColor(getTerrainColor(terrain));
            g2d.fillArc(centerX - radius, centerY - radius, 2 * radius, 2 * radius, 60 * i, 60);
        }
    }

    /**
     * Crée un hexagone à partir des coordonnées centrales et du rayon.
     * 
     * @param centerX La coordonnée X du centre de l'hexagone.
     * @param centerY La coordonnée Y du centre de l'hexagone.
     * @param radius Le rayon de l'hexagone.
     * @return La forme hexagonale créée.
     */
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

    /**
     * Récupère la couleur associée à un type de terrain.
     * 
     * @param terrain Le type de terrain à évaluer.
     * @return La couleur correspondant au type de terrain.
     */
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
