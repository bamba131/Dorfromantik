package controllers;
import models.*;
import views.*;


public class MenuController {
    private MenuModel model;
    private MenuView view;

    public MenuController(MenuModel model, MenuView view) {
        this.model = model;
        this.view = view;

        view.getResumeButton().addActionListener(new ResListener());
        view.getNewGameButton().addActionListener(new NewListener());
    }
}

