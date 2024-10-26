package controller;
import view.App;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;

import view.GameView;

public class ResListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        SwingUtilities.invokeLater(() -> new GameView());
        App.getInstance().dispose();
        
    }
}