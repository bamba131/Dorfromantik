// src/main/java/view/GameView.java
package view;

import controller.*;

import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel implements GameEndListener {
    private JPanel gridPanel;
    private HexagonTile nextTilePreview;
    private GameController gameController;
    private CameraController cameraController;
    private GameContext gameContext;
    private JLabel scoreLabel;
    private int seriesId;

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

    private JPanel createHexagonGrid() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(243, 171, 115));
        panel.setPreferredSize(new Dimension(800, 800));
        return panel;
    }

    private void centerGridPanel() {
        int centerX = (getWidth() - gridPanel.getPreferredSize().width) / 2;
        int centerY = (getHeight() - gridPanel.getPreferredSize().height) / 2;
        gameContext.updateOffset(centerX, centerY);
        gameContext.repaintGrid(gridPanel);
    }

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

    @Override
    public void onGameEnd(int finalScore) {
        SwingUtilities.invokeLater(() -> {
            ScoreView scoreView = new ScoreView(seriesId, finalScore);
            App.addView(scoreView, App.SCORE_VIEW);
            App.showView(App.SCORE_VIEW);
        });
    }
}
