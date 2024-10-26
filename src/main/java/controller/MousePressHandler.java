package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;

public class MousePressHandler extends MouseAdapter {

    private CameraControllerListener listener;

    public MousePressHandler(CameraControllerListener listener) {
        this.listener = listener;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            listener.setMouseDragStart(e.getPoint());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            listener.resetMouseDragStart();
        }
    }
}
