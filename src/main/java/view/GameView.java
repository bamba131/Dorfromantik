package view;

import controller.GameController;
import controller.CameraController;
import controller.GameContext;
import model.Tile;

import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {
    private JPanel gridPanel;
    private Tile nextTile;
    private HexagonTile nextTilePreview;
    private GameController gameController;
    private CameraController cameraController;
    private GameContext gameContext;

    public GameView() {
        setTitle("Jeu de Tuiles");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(1500, 750); 
        setLocationRelativeTo(null);

        // Initialiser le contexte de jeu
        gameContext = new GameContext();

        // Créer la grille d'hexagones
        gridPanel = createHexagonGrid();
        
        // Calculer et centrer la grille dans GameView
        centerGridPanel();

        add(gridPanel, BorderLayout.CENTER);

        // Créer le panneau de contrôle à droite
        JPanel controlPanel = createControlPanel();
        controlPanel.setPreferredSize(new Dimension(200, 600));
        add(controlPanel, BorderLayout.EAST);

        // Initialiser les contrôleurs avec le contexte de jeu
        gameController = new GameController(gameContext, gridPanel, nextTile, nextTilePreview);
        cameraController = new CameraController(gridPanel, gameContext);

        // Générer une première tuile pleine et la placer au centre
        placeInitialTileWithRandomTile();

        setVisible(true);
    }

    private JPanel createHexagonGrid() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(800, 800));
        return panel;
    }

    private void centerGridPanel() {
        int gridX = (getWidth() - gridPanel.getPreferredSize().width) / 2;
        int gridY = (getHeight() - gridPanel.getPreferredSize().height) / 2;

        gridPanel.setLocation(gridX, gridY);
        gameContext.updateOffset(gridX, gridY);  // Mettre à jour l'offset dans GameContext
    }

    private Tile generateRandomTile() {
        return new Tile(); // Génère une tuile aléatoire avec des caractéristiques définies dans Tile.java
    }

    private void placeInitialTileWithRandomTile() {
        // Générer une première tuile pleine de manière aléatoire
        Tile initialTile = generateRandomTile();

        // Utiliser `placeInitialTile` pour placer cette tuile au centre de la grille
        Point initialPosition = new Point(0, 0);
        gameController.placeInitialTile(initialPosition, cameraController, initialTile);
    }

    private JPanel createControlPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("Prochaine tuile : "));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        nextTilePreview = new HexagonTile(null);
        nextTilePreview.setPreferredSize(new Dimension(150, 150));
        nextTilePreview.setBackground(Color.GRAY);
        nextTilePreview.setOpaque(true);
        panel.add(nextTilePreview);

        return panel;
    }
}
