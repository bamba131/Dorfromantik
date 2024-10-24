package controller;
import model.*;
import view.*;


public class MenuController {
    private MenuModel model;
    private MenuView view;

    public MenuController(MenuModel model, MenuView view) {
        this.model = model;
        this.view = view;

        view.getResumeButton().addActionListener(new ResListener());
        view.getNewGameButton().addActionListener(new NewListener());
        view.getQuiButton().addActionListener(new QuiListener());
    }
}

