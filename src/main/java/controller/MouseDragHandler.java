package controller;

import view.HexagonTile;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Point;
import javax.swing.SwingUtilities;

public class MouseDragHandler extends MouseAdapter {

    private CameraController controller;
    private GameContext context;

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

            // Déplace chaque tuile dans le contexte de la grille
            for (HexagonTile hexTile : context.getHexagonMap().values()) {
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
            controller.resetMouseDragStart();
        }
    }
}
