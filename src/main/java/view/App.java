package view;

import javax.swing.*;
import java.awt.*;

public class App {
    public static final String MENU_VIEW = "MenuView";
    public static final String GAME_VIEW = "GameView";
    public static final String SCORE_VIEW = "ScoreView";

    private static JFrame frame;
    private static CardLayout cardLayout;
    private static JPanel mainPanel;

    static {
        frame = new JFrame("Application de Jeu");
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        frame.setContentPane(mainPanel);
        frame.setSize(1500, 750);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static JFrame getInstance() {
        return frame;
    }

    public static void addView(JPanel view, String viewName) {
        mainPanel.add(view, viewName);
    }

    public static void showView(String viewName) {
        cardLayout.show(mainPanel, viewName);
        frame.setVisible(true);
    }
}