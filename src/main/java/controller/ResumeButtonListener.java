package controller;

import view.MenuView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener pour le bouton Reprendre la partie dans le menu principal.
 */
public class ResumeButtonListener implements ActionListener {
    private final MenuView menuView;

    public ResumeButtonListener(MenuView menuView) {
        this.menuView = menuView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        menuView.showSeriesButtons();
    }
}
