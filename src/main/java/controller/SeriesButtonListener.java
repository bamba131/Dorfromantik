package controller;

import view.App;
import view.GameView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener pour les boutons de sélection de séries dans le menu principal.
 */
public class SeriesButtonListener implements ActionListener {
    private final int seriesId;
    private final SeriesSelector seriesSelector;

    public SeriesButtonListener(int seriesId, SeriesSelector seriesSelector) {
        this.seriesId = seriesId;
        this.seriesSelector = seriesSelector;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GameView gameView = new GameView(seriesId);
        App.addView(gameView, App.GAME_VIEW);
        App.showView(App.GAME_VIEW);
        if (seriesSelector != null) {
            seriesSelector.startGameWithSeries(seriesId);
        }
    }
}
