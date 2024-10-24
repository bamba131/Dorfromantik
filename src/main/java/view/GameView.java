package view;

import controller.GameController;
import controller.CameraController;
import controller.GameContext;
import model.Tile;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GameView extends JFrame {

    private JPanel gridPanel;
    private Map<Point, HexagonTile> hexagonMap;
    private Set<Point> availablePositions;
    private Tile nextTile;
    private HexagonTile nextTilePreview;
    private GameController gameController;
    private CameraController cameraController;

    public GameView() {
        this.hexagonMap = new HashMap<>();
        this.availablePositions = new HashSet<>();

        setTitle("Jeu de Tuiles");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Générer la première tuile aléatoire
        nextTile = generateRandomTile();

        // Créer la grille d'hexagones à gauche
        gridPanel = createHexagonGrid();
        gridPanel.setLayout(null);  // Permet de placer les éléments sans layout manager
        gridPanel.setPreferredSize(new Dimension(800, 800));  // Taille de la grille adaptée

        // Ajout de la grille sans JScrollPane
        add(gridPanel, BorderLayout.CENTER);

        // Créer le panneau de contrôle à droite
        JPanel controlPanel = createControlPanel();
        controlPanel.setPreferredSize(new Dimension(200, 600));  // Taille fixe pour le panneau de contrôle
        add(controlPanel, BorderLayout.EAST);

        // Initialiser GameContext
        GameContext context = new GameContext(hexagonMap, gameController, availablePositions, cameraController);

        // Initialiser le contrôleur du jeu avec le contexte
        gameController = new GameController(hexagonMap, availablePositions, gridPanel, nextTile, nextTilePreview);

        // Initialiser le contrôleur de la souris pour le glissement de la grille avec le contexte
        cameraController = new CameraController(gridPanel, context);

        // Placer la première tuile au centre en passant CameraController
        Point initialPosition = new Point(0, 0);
        gameController.placeInitialTile(initialPosition, cameraController);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createHexagonGrid() {
        JPanel panel = new JPanel();
        panel.setLayout(null);  // Layout libre pour placer les hexagones
        panel.setBackground(Color.WHITE);  // S'assurer que le fond est blanc pour mieux voir les hexagones
        panel.setPreferredSize(new Dimension(1000, 1000));  // Taille initiale suffisamment grande
        return panel;
    }

    private Tile generateRandomTile() {
        return new Tile();
    }

    private JPanel createControlPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.LIGHT_GRAY);  // Améliorer la visibilité du panneau de contrôle
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  // Ajout d'espacement interne

        panel.add(new JLabel("Prochaine tuile : "));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Afficher la prévisualisation de la prochaine tuile
        nextTilePreview = new HexagonTile(null);
        nextTilePreview.setPreferredSize(new Dimension(150, 150));  // Augmenter la taille pour la prévisualisation
        nextTilePreview.setBackground(Color.GRAY);
        nextTilePreview.setOpaque(true);  // Rendre le fond opaque pour une meilleure visibilité
        nextTilePreview.setTile(nextTile);
        panel.add(nextTilePreview);

        return panel;
    }
}
