package view;

import javax.swing.*;
import java.awt.*;

/**
 * La classe <code>App</code> représente l'application de jeu qui gère les vues de l'interface utilisateur.
 * Elle utilise un <code>CardLayout</code> pour naviguer entre différentes vues telles que le menu, le jeu et le score.
 */
public class App {
    /** Nom de la vue du menu. */
    public static final String MENU_VIEW = "MenuView";
    
    /** Nom de la vue du jeu. */
    public static final String GAME_VIEW = "GameView";
    
    /** Nom de la vue des scores. */
    public static final String SCORE_VIEW = "ScoreView";

    /** La fenêtre principale de l'application. */
    private static JFrame frame;
    
    /** Le layout utilisé pour gérer l'affichage des vues. */
    private static CardLayout cardLayout;
    
    /** Le panneau principal contenant les différentes vues. */
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

    /**
     * Retourne l'instance de la fenêtre principale.
     * 
     * @return L'instance de <code>JFrame</code> de l'application.
     */
    public static JFrame getInstance() {
        return frame;
    }

    /**
     * Ajoute une vue au panneau principal de l'application.
     * 
     * @param view Le panneau de la vue à ajouter.
     * @param viewName Le nom sous lequel la vue sera identifiée.
     */
    public static void addView(JPanel view, String viewName) {
        mainPanel.add(view, viewName);
    }

    /**
     * Affiche la vue spécifiée dans le panneau principal.
     * 
     * @param viewName Le nom de la vue à afficher.
     */
    public static void showView(String viewName) {
        cardLayout.show(mainPanel, viewName);
        frame.setVisible(true);
    }
}
