package view;

import model.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

        // Placer la première tuile au centre
        Point initialPosition = new Point(0, 0);
        placeInitialTile(initialPosition);
        centerScrollOnPosition(initialPosition, scrollPane);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void placeInitialTile(Point position) {
        addHexagonTile(position, gridPanel, 50);
        availablePositions.remove(position);

        Point[] adjacentPositions = getAdjacentPositions(position);
        for (Point adj : adjacentPositions) {
            if (!hexagonMap.containsKey(adj)) {
                availablePositions.add(adj);
                addHexagonTile(adj, gridPanel, 50);
            }
        }
    }

    private JPanel createHexagonGrid() {
        JPanel panel = new JPanel(null) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(3000, 3000);
            }
        };
        return panel;
    }

    // Ajouter un hexagone à une position donnée
    private void addHexagonTile(Point position, JPanel panel, int hexSize) {
        int xOffset = position.x * (int) (hexSize * 3 / 2);  // Décalage horizontal ajusté
        int yOffset = position.y * (int) (Math.sqrt(3) * hexSize);  // Décalage vertical ajusté

        // Décaler les colonnes impaires verticalement
        if (position.x % 2 != 0) {
            yOffset += (int) (Math.sqrt(3) * hexSize / 2);
        }

        HexagonTile hexTile = new HexagonTile(position);
        hexTile.setBounds(xOffset, yOffset, hexSize, hexSize);
        hexTile.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (availablePositions.contains(hexTile.getPosition())) {
                    placeTile(hexTile.getPosition());
                }
            }
        });
        hexagonMap.put(position, hexTile);
        panel.add(hexTile);
        panel.revalidate();
        panel.repaint();
    }

    // Placer une tuile à la position spécifiée
    private void placeTile(Point position) {
        if (availablePositions.contains(position)) {
            HexagonTile hexTile = hexagonMap.get(position);
            if (hexTile != null && !hexTile.isFilled()) {
                // Placer la tuile actuelle
                hexTile.setTile(nextTile);
                tileCount++;

                // Générer une nouvelle tuile et mettre à jour la prévisualisation
                nextTile = generateRandomTile();
                nextTilePreview.setTile(nextTile);

                updateAdjacentPositions(position);
            }
        }
    }

    private void updateAdjacentPositions(Point position) {
        Point[] adjacentPositions = getAdjacentPositions(position);
        for (Point adj : adjacentPositions) {
            if (!hexagonMap.containsKey(adj)) {
                availablePositions.add(adj);
                addHexagonTile(adj, gridPanel, 50);
            }
        }
    }

    private Point[] getAdjacentPositions(Point position) {
        return new Point[]{
                new Point(position.x + 1, position.y),
                new Point(position.x - 1, position.y),
                new Point(position.x, position.y + 1),
                new Point(position.x, position.y - 1),
                new Point(position.x + 1, position.y - 1),
                new Point(position.x - 1, position.y + 1)
        };
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameView());
    }
}
