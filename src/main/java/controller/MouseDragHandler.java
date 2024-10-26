package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Point;
import javax.swing.SwingUtilities;

public class MouseDragHandler extends MouseAdapter {

    private CameraControllerListener listener;

    public MouseDragHandler(CameraControllerListener listener) {
        this.listener = listener;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (listener.getMouseDragStart() != null && SwingUtilities.isRightMouseButton(e)) {
            Point current = e.getPoint();
            int deltaX = current.x - listener.getMouseDragStart().x;
            int deltaY = current.y - listener.getMouseDragStart().y;

            // Déplacement dans CameraController
            listener.updateViewOffset(deltaX, deltaY);

            // Met à jour la position de départ
            listener.setMouseDragStart(current);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            listener.resetMouseDragStart();
        }
    }
}
