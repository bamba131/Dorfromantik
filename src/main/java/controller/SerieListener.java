package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * La classe SerieListener est un écouteur d'événements qui réagit aux actions de l'utilisateur
 * concernant les séries de jeux. Elle charge une série spécifique lorsque l'utilisateur interagit.
 */
public class SerieListener implements ActionListener {
    private final GameController gameController;
    private final int seriesNumber;

    /**
     * Constructeur de SerieListener.
     *
     * @param gameController le contrôleur de jeu qui gère la logique de la série
     * @param seriesNumber   le numéro de la série à charger
     */
    public SerieListener(GameController gameController, int seriesNumber) {
        this.gameController = gameController;
        this.seriesNumber = seriesNumber;
    }

     /**
     * Méthode appelée lorsqu'un événement d'action se produit. Elle charge la série spécifiée
     * dans le contrôleur de jeu.
     *
     * @param e l'événement d'action contenant les détails de l'événement
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        gameController.loadSeries(seriesNumber);
    }
}
