package controller;

import view.GameView;
import javax.swing.SwingUtilities;

/**
 * La classe SeriesSelector est responsable de la sélection d'une série de jeux
 * et du démarrage de la vue de jeu correspondante. Elle gère l'initialisation
 * de l'interface graphique pour une série donnée.
 */
public class SeriesSelector {

    /**
     * Démarre le jeu avec la série spécifiée.
     *
     * @param seriesId l'identifiant de la série à charger et à jouer
     */
    public void startGameWithSeries(int seriesId) {
        SwingUtilities.invokeLater(() -> new GameView(seriesId)); // Démarre GameView avec l'ID de série donné
        System.out.println("Série " + seriesId + " sélectionnée et jeu démarré.");
    }
}
