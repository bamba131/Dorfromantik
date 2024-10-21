package controllers;

import models.MenuModel;
import views.MenuView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuController {
    private MenuModel model;
    private MenuView view;

    public MenuController(MenuModel model, MenuView view) {
        this.model = model;
        this.view = view;

        // Gestion des événements
        this.view.addPlayGameListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setPlayerName(view.getPlayerName());
                model.setSelectedSuite(view.getSelectedSuite());
                System.out.println("Nom du joueur: " + model.getPlayerName());
                System.out.println("Suite sélectionnée: " + model.getSelectedSuite());
            }
        });

        this.view.addContinueGameListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logique pour continuer la partie
                System.out.println("Partie Jouer sélectionnée");
            }
        });
    }
}
