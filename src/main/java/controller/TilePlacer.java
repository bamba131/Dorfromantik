package controller;

import java.awt.Point;

/**
 * Interface TilePlacer.
 * 
 * Cette interface définit le comportement pour le placement de tuiles
 * sur un plateau de jeu. Elle doit être implémentée par toute classe
 * qui gère le placement des tuiles.
 */
public interface TilePlacer {

     /**
     * Place une tuile à la position spécifiée.
     *
     * @param position la position sur le plateau où la tuile doit être placée,
     *                 représentée par un objet Point (coordonnées x et y)
     */
    void placeTile(Point position);
}
