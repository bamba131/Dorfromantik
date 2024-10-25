package controller;

import javax.swing.*;
import java.awt.*;

public class CameraController {

    private Point mouseDragStart = null;  // Stocke la position de départ du clic droit
    private Point viewOffset = new Point(0, 0); // Stocke le décalage actuel de la vue
    private JPanel gridPanel;
    private GameContext context;

    public CameraController(JPanel gridPanel, GameContext context) {
        this.gridPanel = gridPanel;
        this.context = context;
        setupMouseDragToMove(); // Initialise les écouteurs pour gérer le déplacement
    }

    public Point getViewOffset() {
        return viewOffset;
    }

    public void updateViewOffset(int deltaX, int deltaY) {
        viewOffset.translate(deltaX, deltaY);
        gridPanel.setLocation(viewOffset);
    }

    private void setupMouseDragToMove() {
        MousePressHandler mousePressHandler = new MousePressHandler(this, context);
        MouseDragHandler mouseDragHandler = new MouseDragHandler(this, context);

        gridPanel.addMouseListener(mousePressHandler);
        gridPanel.addMouseMotionListener(mouseDragHandler);
    }

    public void setMouseDragStart(Point point) {
        this.mouseDragStart = point;
    }

    public Point getMouseDragStart() {
        return mouseDragStart;
    }

    public void resetMouseDragStart() {
        this.mouseDragStart = null;
    }

    public JPanel getGridPanel() {
        return gridPanel;
    }
}
