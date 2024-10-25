package controller;
import model.*;
import view.*;


public class MenuController {

    public MenuController(MenuModel model, MenuView view) {

        view.getResumeButton().addActionListener(new ResListener());
        view.getNewGameButton().addActionListener(new NewListener());
        view.getQuiButton().addActionListener(new QuiListener());
    }
}

