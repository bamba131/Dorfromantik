package controller;

import view.HowToPlayView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * La classe HowToPlayListener est un écouteur d'événements pour afficher la fenêtre 
 * "Comment jouer" lors d'une action utilisateur, comme le clic sur un bouton d'aide.
 * Elle affiche une boîte de dialogue {@link HowToPlayView} pour présenter les instructions de jeu.
 */
public class HowToPlayListener implements ActionListener {
    private JFrame parent;

    /**
     * Construit un écouteur HowToPlayListener.
     *
     * @param parent la fenêtre parent associée, utilisée comme référence pour afficher la boîte de dialogue
     */
    public HowToPlayListener(JFrame parent) {
        this.parent = parent;
    }

    /**
     * Gère l'événement d'action déclenché, en affichant la fenêtre de dialogue "Comment jouer".
     * 
     * @param e l'événement d'action déclenché par l'utilisateur
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        HowToPlayView howToPlayDialog = new HowToPlayView(parent);
        howToPlayDialog.setVisible(true);
    }
}
