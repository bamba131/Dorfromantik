package view;

import controller.GameController;
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
    private HexagonTile nextTilePreview; // Tuile de prévisualisation à droite
    private int tileCount;
    private GameController gameController;

    public GameView() {
        this.hexagonMap = new HashMap<>();
        this.availablePositions = new HashSet<>();
        this.tileCount = 0;

        setTitle("Jeu de Tuiles");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Générer la première tuile aléatoire
        nextTile = generateRandomTile();

        // Créer la grille d'hexagones à gauche
        gridPanel = createHexagonGrid();
        JScrollPane scrollPane = new JScrollPane(gridPanel);
        scrollPane.setPreferredSize(new Dimension(600, 600));
        add(scrollPane, BorderLayout.CENTER);

        // Créer le panneau de contrôle à droite
        JPanel controlPanel = createControlPanel();
        add(controlPanel, BorderLayout.EAST);

        // Initialiser le contrôleur
        gameController = new GameController(hexagonMap, availablePositions, gridPanel, nextTile, nextTilePreview);

        // Placer la première tuile au centre
        Point initialPosition = new Point(0, 0);
        gameController.placeInitialTile(initialPosition);
        centerScrollOnPosition(initialPosition, scrollPane);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createHexagonGrid() {
        return new HexagonGridPanel();
    }

    private Tile generateRandomTile() {
        return new Tile();
    }

    private void centerScrollOnPosition(Point position, JScrollPane scrollPane) {
        int xCenter = position.x * 50 * 3 / 2;
        int yCenter = position.y * (int) (Math.sqrt(3) * 50);
        scrollPane.getViewport().setViewPosition(new Point(xCenter - scrollPane.getViewport().getWidth() / 2,
                yCenter - scrollPane.getViewport().getHeight() / 2));
    }

    private JPanel createControlPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Prochaine tuile : "));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Afficher la prévisualisation de la prochaine tuile
        nextTilePreview = new HexagonTile(null);
        nextTilePreview.setPreferredSize(new Dimension(100, 100));
        nextTilePreview.setTile(nextTile);
        panel.add(nextTilePreview);

        return panel;
    }
}
