package controller;

import view.HexagonTile;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.SwingUtilities;

public class MousePressHandler extends MouseAdapter {

    private CameraController controller;
    private GameContext context;  // Ajout du GameContext

    public MousePressHandler(CameraController controller, GameContext context) {
        this.controller = controller;
        this.context = context;  // Utilisation du contexte
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            System.out.println("Clic droit détecté");
            controller.setMouseDragStart(e.getPoint());
        }
    }

    @Override
public void mouseReleased(MouseEvent e) {
    if (SwingUtilities.isRightMouseButton(e)) {
        // Créer une nouvelle carte pour stocker les nouvelles positions logiques
        Map<Point, HexagonTile> newHexagonMap = new HashMap<>();
        
        for (Map.Entry<Point, HexagonTile> entry : context.hexagonMap.entrySet()) {
            HexagonTile hexTile = entry.getValue();
            Point newLogicalPosition = calculateNewLogicalPosition(hexTile.getLocation());

            // Ajouter la nouvelle position logique dans la nouvelle carte
            newHexagonMap.put(newLogicalPosition, hexTile);
        }

        // Remplacer l'ancienne carte après avoir terminé l'itération
        Map<Point, HexagonTile> oldHexagonMap = context.hexagonMap;  // Sauvegarder l'ancienne référence
        context.hexagonMap = newHexagonMap;  // Réassigner la nouvelle carte

        oldHexagonMap.clear();  // Vider l'ancienne carte (maintenant non utilisée)
        controller.resetMouseDragStart();
    }
}


    private Point calculateNewLogicalPosition(Point visualPosition) {
        int logicalX = visualPosition.x / (int) (50 * 3 / 2);
        int logicalY = visualPosition.y / (int) (Math.sqrt(3) * 50);

        if (logicalX % 2 != 0) {
            logicalY -= (int) (Math.sqrt(3) * 50 / 2);
        }

        return new Point(logicalX, logicalY);
    }
}
