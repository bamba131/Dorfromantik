package main;

import javax.swing.SwingUtilities;
import view.GameView;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameView());
    }
}
