package view;

import controller.GameController;
import controller.CameraController;
import controller.GameContext;
import controller.MouseWheelController;

import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {
    private JPanel gridPanel;
    private HexagonTile nextTilePreview;
    private GameController gameController;
    private CameraController cameraController;
    private GameContext gameContext;
    private JLabel scoreLabel;

    // Couleurs pour le style
    private final Color hoverColor = new Color(200, 150, 100); // Couleur de hover avec transparence
    private final Color normalColor = new Color(243, 171, 115); // Couleur normale avec transparence

    public GameView(int seriesId) {
        setTitle("Jeu de Tuiles");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(1500, 750); 
        setLocationRelativeTo(null);

        gameContext = new GameContext();
        gridPanel = createHexagonGrid();
        centerGridPanel();
        add(gridPanel, BorderLayout.CENTER);

        nextTilePreview = new HexagonTile(null, false);
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setForeground(Color.BLACK); // Texte noir pour contraste

        JPanel controlPanel = createControlPanel();
        controlPanel.setPreferredSize(new Dimension(200, 600));
        add(controlPanel, BorderLayout.EAST);

        gameController = new GameController(gameContext, gridPanel, nextTilePreview, scoreLabel);
        gameController.loadSeries(seriesId); // Charge la série
        cameraController = new CameraController(gridPanel, gameContext);

        MouseWheelController wheelController = new MouseWheelController(nextTilePreview, gameController);
        addMouseWheelListener(wheelController);

        gameController.initializeGame(cameraController);
        setVisible(true);
    }

    private JPanel createHexagonGrid() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(normalColor); // Couleur de fond de la grille
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
        panel.setBackground(normalColor); // Couleur normale pour le panneau de contrôle
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel nextTileLabel = new JLabel("Prochaine tuile : ");
        nextTileLabel.setForeground(Color.BLACK); // Texte noir pour contraste
        panel.add(nextTileLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        nextTilePreview.setPreferredSize(new Dimension(150, 150));
        nextTilePreview.setBackground(hoverColor); // Couleur hover pour différencier
        nextTilePreview.setOpaque(true);
        panel.add(nextTilePreview);

        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        panel.add(scoreLabel);

        return panel;
    }
}
