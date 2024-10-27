package controller;

import model.MenuModel;
import view.MenuView;

public class MenuController {

    public MenuController(MenuModel model, MenuView view, SeriesSelector seriesSelector) {
        // Assignation des action listeners aux boutons du menu
        view.getQuiButton().addActionListener(new QuiListener());                 // Quitte l'application

        // Définir le sélecteur de séries
        view.setSeriesSelector(seriesSelector);
    }
}
