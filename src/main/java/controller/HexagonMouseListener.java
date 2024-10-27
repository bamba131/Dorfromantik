package controller;

import view.HexagonTile;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Set;


/**
 * La classe HexagonMouseListener gère les événements de souris pour un {@link HexagonTile}.
 * Elle vérifie si une tuile peut être placée sur l'hexagone lorsqu'il est cliqué, 
 * en fonction des positions disponibles pour le placement.
 */
public class HexagonMouseListener extends MouseAdapter {
    private final HexagonTile hexTile;
    private final TilePlacer tilePlacer;
    private final Set<Point> availablePositions;

    /**
     * Construit un écouteur de souris pour une tuile hexagonale.
     *
     * @param hexTile la tuile hexagonale liée à cet écouteur
     * @param tilePlacer l'instance de {@link TilePlacer} pour gérer le placement de tuiles
     * @param availablePositions les positions disponibles pour le placement des tuiles
     */
    public HexagonMouseListener(HexagonTile hexTile, TilePlacer tilePlacer, Set<Point> availablePositions) {
        this.hexTile = hexTile;
        this.tilePlacer = tilePlacer;
        this.availablePositions = availablePositions;
    }

    /**
     * Gère l'événement de clic sur la tuile hexagonale.
     * Si la position de l'hexagone cliqué est disponible pour le placement, 
     * elle utilise {@link TilePlacer#placeTile(Point)} pour placer une tuile.
     *
     * @param e l'événement de clic de souris
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        Point position = hexTile.getPosition();
        if (availablePositions.contains(position)) {
            System.out.println("Hexagone cliqué à la position : " + position);
            tilePlacer.placeTile(position);
        } else {
            System.out.println("Position non disponible pour le placement");
        }
    }
}
