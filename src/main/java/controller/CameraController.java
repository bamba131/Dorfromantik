package controller;

import javax.swing.JPanel;
import java.awt.Point;

/**
 * La classe CameraController gère le contrôle de la caméra dans l'interface utilisateur pour une vue basée sur un 
 * panneau de grille. Elle permet de déplacer la vue par un glissement de souris, en mettant à jour les offsets de vue 
 * dans le contexte de jeu (GameContext).
 */
public class CameraController implements CameraControllerListener {

    private Point mouseDragStart = null;
    private GameContext context;
    private JPanel gridPanel;

    /**
     * Constructeur de la classe CameraController.
     * Initialise les écouteurs de souris pour activer le déplacement de la vue par glissement.
     *
     * @param gridPanel le panneau de grille à afficher et manipuler par le contrôleur de caméra
     * @param context le contexte de jeu utilisé pour stocker et gérer l'offset de la vue
     */
    public CameraController(JPanel gridPanel, GameContext context) {
        this.gridPanel = gridPanel;
        this.context = context;
        setupMouseDragToMove(gridPanel); // Initialise les écouteurs pour gérer le déplacement
    }

    /**
     * Met à jour le décalage de la vue dans le contexte de jeu avec les valeurs fournies.
     * Puisque l'offset est mis à jour, la grille est repeinte pour refléter les nouvelles coordonnées.
     *
     * @param deltaX le décalage horizontal de la vue
     * @param deltaY le décalage vertical de la vue
     */
    @Override
    public void updateViewOffset(int deltaX, int deltaY) {
        // Met à jour uniquement l'offset dans GameContext
        context.updateOffset(deltaX, deltaY);
        // Repeindre la grille après mise à jour
        context.repaintGrid(gridPanel);

        // Debug : Affiche l'offset actuel
        System.out.println("Nouvel offset dans GameContext : " + context.getOffset());
    }

     /**
     * Initialise les écouteurs de souris pour détecter et gérer les événements de glissement de la souris sur le panneau.
     *
     * @param gridPanel le panneau de grille pour lequel activer le glissement de la souris
     */
    private void setupMouseDragToMove(JPanel gridPanel) {
        gridPanel.addMouseListener(new MousePressHandler(this));
        gridPanel.addMouseMotionListener(new MouseDragHandler(this));
    }

    /**
     * Définit le point de départ du glissement de la souris.
     *
     * @param point le point initial où le glissement de la souris commence
     */
    @Override
    public void setMouseDragStart(Point point) {
        this.mouseDragStart = point;
    }


    /**
     * Récupère le point de départ du glissement de la souris.
     *
     * @return le point de départ du glissement de la souris
     */
    @Override
    public Point getMouseDragStart() {
        return mouseDragStart;
    }

     /**
     * Réinitialise le point de départ du glissement de la souris à null, indiquant la fin du glissement.
     */
    @Override
    public void resetMouseDragStart() {
        this.mouseDragStart = null;
    }

    /**
     * Récupère le décalage actuel de la vue depuis le contexte de jeu.
     *
     * @return le point représentant l'offset actuel de la vue
     */
    public Point getViewOffset() {
        return context.getOffset();
    }
}
