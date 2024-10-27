package controller;

import java.awt.Point;

/**
 * L'interface CameraControllerListener définit les méthodes nécessaires pour gérer les interactions 
 * de contrôle de la caméra dans une application. Elle permet de mettre à jour l'offset de la vue, 
 * de gérer le point de départ du glissement de la souris, et de réinitialiser ce point.
 */
public interface CameraControllerListener {

     /**
     * Met à jour le décalage de la vue en fonction des valeurs de déplacement fournies.
     *
     * @param deltaX le décalage horizontal pour ajuster l'offset de la vue
     * @param deltaY le décalage vertical pour ajuster l'offset de la vue
     */
    void updateViewOffset(int deltaX, int deltaY);

    /**
     * Définit le point de départ pour le glissement de la souris.
     *
     * @param point le point de départ du glissement de la souris
     */
    void setMouseDragStart(Point point);

    /**
     * Récupère le point de départ du glissement de la souris.
     *
     * @return le point de départ du glissement de la souris
     */    
    Point getMouseDragStart();

    /**
     * Réinitialise le point de départ du glissement de la souris, marquant la fin du glissement.
     */
    void resetMouseDragStart();
}
