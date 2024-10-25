package controller;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;

public class MousePressHandler extends MouseAdapter {

    private CameraController controller;

    public MousePressHandler(CameraController controller, GameContext context) {
        this.controller = controller;
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
            controller.resetMouseDragStart();
        }
    }
}
