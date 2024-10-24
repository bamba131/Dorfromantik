package controller;

import view.HexagonTile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class MouseDragHandler extends MouseAdapter {

    private CameraController controller;
    private GameContext context;  // Utiliser GameContext pour regrouper les données

    public MouseDragHandler(CameraController controller, GameContext context) {
        this.controller = controller;
        this.context = context;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (controller.getMouseDragStart() != null && SwingUtilities.isRightMouseButton(e)) {
            Point current = e.getPoint();
            int deltaX = current.x - controller.getMouseDragStart().x;
            int deltaY = current.y - controller.getMouseDragStart().y;

            // Déplacer uniquement les coordonnées visuelles, pas les coordonnées logiques
            for (HexagonTile hexTile : context.hexagonMap.values()) {
                Point currentPos = hexTile.getLocation();
                hexTile.setLocation(currentPos.x + deltaX, currentPos.y + deltaY);
            }

            // Mettre à jour la position initiale pour le prochain déplacement
            controller.setMouseDragStart(current);

            // Rafraîchir la vue
            controller.getGridPanel().revalidate();
            controller.getGridPanel().repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            // Recalculer les positions logiques lorsque la souris est relâchée
            Map<Point, HexagonTile> newHexagonMap = new HashMap<>();
            
            for (Map.Entry<Point, HexagonTile> entry : context.hexagonMap.entrySet()) {
                HexagonTile hexTile = entry.getValue();
                Point newLogicalPosition = calculateNewLogicalPosition(hexTile.getLocation());

                // Mettre à jour les écouteurs de la souris
                hexTile.removeMouseListener(hexTile.getMouseListeners()[0]);  // Supprimer l'ancien écouteur
                hexTile.addMouseListener(new HexagonMouseListener(hexTile, context.gameController, context.availablePositions, context.cameraController));

                // Ajouter la nouvelle position logique dans la nouvelle carte
                newHexagonMap.put(newLogicalPosition, hexTile);
            }

            // Remplacer la carte hexagonMap avec la nouvelle carte
            context.hexagonMap.clear();
            context.hexagonMap.putAll(newHexagonMap);

            controller.resetMouseDragStart();
        }
    }

    private Point calculateNewLogicalPosition(Point visualPosition) {
        int logicalX = visualPosition.x / (int) (50 * 3 / 2);  // Ajuste pour la largeur de l'hexagone
        int logicalY = visualPosition.y / (int) (Math.sqrt(3) * 50);  // Ajuste pour la hauteur de l'hexagone
    
        if (logicalX % 2 != 0) {
            logicalY -= (int) (Math.sqrt(3) * 50 / 2);  // Ajustement pour les colonnes impaires
        }

        return new Point(logicalX, logicalY);
    }
}
