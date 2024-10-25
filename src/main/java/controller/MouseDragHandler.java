package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Point;
import javax.swing.SwingUtilities;

public class MouseDragHandler extends MouseAdapter {

    private CameraController controller;

    public MouseDragHandler(CameraController controller, GameContext context) {
        this.controller = controller;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (controller.getMouseDragStart() != null && SwingUtilities.isRightMouseButton(e)) {
            Point current = e.getPoint();
            int deltaX = current.x - controller.getMouseDragStart().x;
            int deltaY = current.y - controller.getMouseDragStart().y;

            // Déplacement dans CameraController
            controller.updateViewOffset(deltaX, deltaY);

            // Met à jour la position de départ
            controller.setMouseDragStart(current);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            controller.resetMouseDragStart();
        }
    }
}
