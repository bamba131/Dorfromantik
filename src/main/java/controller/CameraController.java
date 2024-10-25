package controller;

import javax.swing.JPanel;
import java.awt.Point;


public class CameraController {

    private Point mouseDragStart = null;
    private GameContext context;
    private JPanel gridPanel;

    public CameraController(JPanel gridPanel, GameContext context) {
        this.gridPanel = gridPanel;
        this.context = context;
        setupMouseDragToMove(gridPanel); // Initialise les écouteurs pour gérer le déplacement
    }

    public void updateViewOffset(int deltaX, int deltaY) {
        // Met à jour uniquement l'offset dans GameContext
        context.updateOffset(deltaX, deltaY);
        // Repeindre la grille après mise à jour
        context.repaintGrid(gridPanel);

        // Debug : Affiche l'offset actuel
        System.out.println("Nouvel offset dans GameContext : " + context.getOffset());
    }

    private void setupMouseDragToMove(JPanel gridPanel) {
        gridPanel.addMouseListener(new MousePressHandler(this, context));
        gridPanel.addMouseMotionListener(new MouseDragHandler(this, context));
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

    public Point getViewOffset() {
        return context.getOffset();
    }
}
