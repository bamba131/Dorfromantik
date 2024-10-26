package controller;

import javax.swing.JPanel;
import java.awt.Point;

public class CameraController implements CameraControllerListener {

    private Point mouseDragStart = null;
    private GameContext context;
    private JPanel gridPanel;

    public CameraController(JPanel gridPanel, GameContext context) {
        this.gridPanel = gridPanel;
        this.context = context;
        setupMouseDragToMove(gridPanel); // Initialise les écouteurs pour gérer le déplacement
    }

    @Override
    public void updateViewOffset(int deltaX, int deltaY) {
        // Met à jour uniquement l'offset dans GameContext
        context.updateOffset(deltaX, deltaY);
        // Repeindre la grille après mise à jour
        context.repaintGrid(gridPanel);

        // Debug : Affiche l'offset actuel
        System.out.println("Nouvel offset dans GameContext : " + context.getOffset());
    }

    private void setupMouseDragToMove(JPanel gridPanel) {
        gridPanel.addMouseListener(new MousePressHandler(this));
        gridPanel.addMouseMotionListener(new MouseDragHandler(this));
    }

    @Override
    public void setMouseDragStart(Point point) {
        this.mouseDragStart = point;
    }

    @Override
    public Point getMouseDragStart() {
        return mouseDragStart;
    }

    @Override
    public void resetMouseDragStart() {
        this.mouseDragStart = null;
    }

    public Point getViewOffset() {
        return context.getOffset();
    }
}
