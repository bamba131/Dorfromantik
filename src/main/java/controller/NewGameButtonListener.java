package controller;

import view.MenuView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener pour le bouton Nouveaux Jeu dans le menu principal.
 */
public class NewGameButtonListener implements ActionListener {
    private final MenuView menuView;

    public NewGameButtonListener(MenuView menuView) {
        this.menuView = menuView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        menuView.toggleHowToPlay();
    }
}
