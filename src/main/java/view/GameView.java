// src/main/java/view/GameView.java
package view;

import controller.*;

import javax.swing.*;
import java.awt.*;

/**
 * La classe <code>GameView</code> représente la vue principale du jeu.
 * Elle gère l'affichage de la grille hexagonale, la prévisualisation de la prochaine tuile,
 * et l'affichage du score. Elle implémente l'interface <code>GameEndListener</code>
 * pour gérer la fin du jeu.
 */
public class GameView extends JPanel implements GameEndListener {
    /** Le panneau de la grille hexagonale. */
    private JPanel gridPanel;
    
    /** La prévisualisation de la prochaine tuile. */
    private HexagonTile nextTilePreview;
    
    /** Le contrôleur du jeu. */
    private GameController gameController;
    
    /** Le contrôleur de la caméra. */
    private CameraController cameraController;
    
    /** Le contexte du jeu. */
    private GameContext gameContext;
    
    /** Étiquette affichant le score actuel. */
    private JLabel scoreLabel;
    
    /** Identifiant de la série de tuiles. */
    private int seriesId;

    /**
     * Constructeur de la classe <code>GameView</code>.
     * 
     * @param seriesId L'identifiant de la série de tuiles à charger.
     */
    public GameView(int seriesId) {
        this.seriesId = seriesId;
        setLayout(new BorderLayout());

        gameContext = new GameContext();
        gridPanel = createHexagonGrid();
        centerGridPanel();
        add(gridPanel, BorderLayout.CENTER);

        nextTilePreview = new HexagonTile(null, false);
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setForeground(Color.BLACK);

        JPanel controlPanel = createControlPanel();
        controlPanel.setPreferredSize(new Dimension(200, getPreferredSize().height));
        add(controlPanel, BorderLayout.EAST);

        gameController = new GameController(gameContext, gridPanel, nextTilePreview, scoreLabel, seriesId, this);
        gameController.loadSeries(seriesId);
        cameraController = new CameraController(gridPanel, gameContext);

        MouseWheelController wheelController = new MouseWheelController(nextTilePreview, gameController);
        addMouseWheelListener(wheelController);

        gameController.initializeGame(cameraController);

        JButton backButton = new JButton("Retour");
        backButton.setPreferredSize(new Dimension(100, 40));
        backButton.setBackground(new Color(202, 146, 104));
        backButton.setForeground(Color.BLACK);
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);

        backButton.addActionListener(e -> {
            App.showView(App.MENU_VIEW);
        });

        controlPanel.add(backButton);
    }

    /**
     * Crée et retourne un panneau représentant la grille hexagonale.
     * 
     * @return Un <code>JPanel</code> configuré pour la grille hexagonale.
     */
    private JPanel createHexagonGrid() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(243, 171, 115));
        panel.setPreferredSize(new Dimension(800, 800));
        return panel;
    }

    /**
     * Centre le panneau de la grille dans la vue.
     */
    private void centerGridPanel() {
        int centerX = (getWidth() - gridPanel.getPreferredSize().width) / 2;
        int centerY = (getHeight() - gridPanel.getPreferredSize().height) / 2;
        gameContext.updateOffset(centerX, centerY);
        gameContext.repaintGrid(gridPanel);
    }

    /**
     * Crée et retourne un panneau de contrôle contenant les informations sur la prochaine tuile et le score.
     * 
     * @return Un <code>JPanel</code> configuré pour le panneau de contrôle.
     */
    private JPanel createControlPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(243, 171, 115));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel nextTileLabel = new JLabel("Prochaine tuile : ");
        nextTileLabel.setForeground(Color.BLACK);
        panel.add(nextTileLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        nextTilePreview.setPreferredSize(new Dimension(150, 150));
        nextTilePreview.setBackground(new Color(200, 150, 100));
        nextTilePreview.setOpaque(true);
        panel.add(nextTilePreview);

        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        panel.add(scoreLabel);

        return panel;
    }

    /**
     * Méthode appelée lorsque le jeu se termine.
     * 
     * @param finalScore Le score final du jeu.
     */
    @Override
    public void onGameEnd(int finalScore) {
        SwingUtilities.invokeLater(() -> {
            ScoreView scoreView = new ScoreView(seriesId, finalScore);
            App.addView(scoreView, App.SCORE_VIEW);
            App.showView(App.SCORE_VIEW);
        });
    }
}
