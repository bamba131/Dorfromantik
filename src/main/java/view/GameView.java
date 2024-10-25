package view;

import controller.GameController;
import controller.CameraController;
import controller.GameContext;
import controller.MouseWheelController;
import model.Tile;

import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {
    private JPanel gridPanel;
    private Tile nextTile;  // Tuile en attente
    private HexagonTile nextTilePreview;  // Composant pour afficher la tuile en attente
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

        // Initialiser la tuile en attente et la preview
        nextTile = new Tile();
        nextTilePreview = new HexagonTile(null);
        nextTilePreview.setTile(nextTile);  // Lier nextTile à la preview
        JPanel controlPanel = createControlPanel();
        controlPanel.setPreferredSize(new Dimension(200, 600));
        add(controlPanel, BorderLayout.EAST);

        // Initialiser les contrôleurs avec le contexte de jeu
        gameController = new GameController(gameContext, gridPanel, nextTile, nextTilePreview);  // Passer nextTile et nextTilePreview
        cameraController = new CameraController(gridPanel, gameContext);

        // Ajouter un écouteur pour la molette de la souris
        MouseWheelController wheelController = new MouseWheelController(nextTilePreview, gameController);

        addMouseWheelListener(wheelController);

        // Appeler l'initialisation du jeu
        gameController.initializeGame(cameraController);

        setVisible(true);
    }

    private JPanel createHexagonGrid() {
        JPanel panel = new JPanel();
        panel.setLayout(null); // Permet de placer des composants avec des coordonnées absolues
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(800, 800));  // Peut être ajusté si nécessaire
        return panel;
    }

    private void centerGridPanel() {
        int centerX = (getWidth() - gridPanel.getPreferredSize().width) / 2;
        int centerY = (getHeight() - gridPanel.getPreferredSize().height) / 2;
        gameContext.updateOffset(centerX, centerY);
        gameContext.repaintGrid(gridPanel);  // Rappel pour centrer initialement les tuiles
    }

    private JPanel createControlPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("Prochaine tuile : "));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        nextTilePreview.setPreferredSize(new Dimension(150, 150));
        nextTilePreview.setBackground(Color.GRAY);
        nextTilePreview.setOpaque(true);
        panel.add(nextTilePreview);

        return panel;
    }
}
