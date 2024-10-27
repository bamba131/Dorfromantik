package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SerieListener implements ActionListener {
    private final GameController gameController;
    private final int seriesNumber;

    public SerieListener(GameController gameController, int seriesNumber) {
        this.gameController = gameController;
        this.seriesNumber = seriesNumber;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameController.loadSeries(seriesNumber);
    }
}
