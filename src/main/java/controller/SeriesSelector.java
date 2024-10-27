package controller;

import view.GameView;
import javax.swing.SwingUtilities;

public class SeriesSelector {

    public void startGameWithSeries(int seriesId) {
        SwingUtilities.invokeLater(() -> new GameView(seriesId)); // Démarre GameView avec l'ID de série donné
        System.out.println("Série " + seriesId + " sélectionnée et jeu démarré.");
    }
}
