// src/main/java/controller/GameController.java
package controller;

import model.Tile;
import model.TileDatabaseManager;
import view.HexagonTile;

import javax.swing.*;
import java.awt.Point;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * La classe GameController gère le flux de jeu et la logique principale, y compris le placement de tuiles,
 * la mise à jour de la grille et le calcul du score. Elle prend en charge l'initialisation du jeu, 
 * la génération de tuiles et la gestion de la fin de la partie.
 */
public class GameController implements TilePlacer {
    private Map<Point, HexagonTile> hexagonMap;
    private Set<Point> availablePositions;
    private JPanel gridPanel;
    private Tile nextTile;
    private HexagonTile nextTilePreview;
    private GameContext gameContext;
    private TileDatabaseManager dbManager;
    private List<Tile> currentTiles;
    private int tileIndex;
    private ScoreGameContext scoreGameContext;

    private int placedTileCount = 0;
    private int seriesId;
    private GameEndListener gameEndListener;


    /**
     * Constructeur de la classe GameController.
     * Initialise les composants de la partie, y compris le contexte de jeu, la série de tuiles,
     * et le gestionnaire de fin de partie.
     *
     * @param gameContext      le contexte de jeu contenant l'état de la grille
     * @param gridPanel        le panneau d'affichage de la grille
     * @param nextTilePreview  la tuile d'aperçu pour la prochaine tuile
     * @param scoreLabel       le label d'affichage du score
     * @param seriesId         l'identifiant de la série de tuiles
     * @param gameEndListener  le listener pour gérer la fin de la partie
     */
    public GameController(GameContext gameContext, JPanel gridPanel, HexagonTile nextTilePreview, JLabel scoreLabel, int seriesId, GameEndListener gameEndListener) {
        this.seriesId = seriesId;
        this.gameContext = gameContext;
        this.gridPanel = gridPanel;
        this.hexagonMap = gameContext.getHexagonMap();
        this.availablePositions = gameContext.getAvailablePositions();
        this.nextTilePreview = nextTilePreview;
        this.dbManager = new TileDatabaseManager();
        this.tileIndex = 0;
        this.scoreGameContext = new ScoreGameContext(gameContext, scoreLabel);
        this.gameEndListener = gameEndListener;

        loadSeries(seriesId);
        updatePreview();
    }


    /**
     * Charge une série de tuiles spécifiée par l'identifiant de série.
     *
     * @param idSeries l'identifiant de la série à charger
     */
    public void loadSeries(int idSeries) {
        currentTiles = dbManager.getTilesBySeries(idSeries);
        tileIndex = 0;
        System.out.println("Série " + idSeries + " chargée avec " + currentTiles.size() + " tuiles.");
    }


    /**
     * Place une tuile à la position spécifiée dans la grille, si la position est disponible.
     *
     * @param position la position de la grille pour placer la tuile
     */
    @Override
    public void placeTile(Point position) {
        if (availablePositions.contains(position)) {
            HexagonTile hexTile = hexagonMap.get(position);
            if (hexTile == null) {
                System.out.println("Erreur: hexTile est null à la position : " + position);
                return;
            }

            // Placer la tuile actuelle
            hexTile.setTile(nextTile);
            gridPanel.revalidate();
            gridPanel.repaint();
            availablePositions.remove(position);

            // Mettre à jour les positions disponibles autour de la tuile ajoutée
            for (Point adj : getAdjacentPositions(position)) {
                if (!hexagonMap.containsKey(adj)) {
                    availablePositions.add(adj);
                    addHexagonTile(adj, gridPanel, 50, null, null);
                }
            }

            gameContext.repaintGrid(gridPanel);
            scoreGameContext.calculateScore();

            // Incrémenter le nombre de tuiles placées et vérifier si la limite est atteinte
            placedTileCount++;
            if (placedTileCount > 48) {
                endGame();  // Terminer la partie si on a atteint la 50ᵉ tuile pile
                return;  // Arrêter ici pour éviter de générer une tuile vide
            }

            // Générer la prochaine tuile si la partie n'est pas terminée
            generateNextTile();
        }
    }

    /**
     * Termine la partie, enregistre le score final et notifie le listener de fin de partie.
     */
    private void endGame() {
        int finalScore = scoreGameContext.getScore();

        // Enregistrer le score dans la base de données
        new SendScore().insertscore(seriesId, finalScore);

        // Notifiez le listener de la fin de la partie
        if (gameEndListener != null) {
            gameEndListener.onGameEnd(finalScore);
        }
    }

    /**
     * Initialise le jeu en plaçant une tuile initiale au centre de la grille et en configurant la vue.
     *
     * @param cameraController le contrôleur de caméra pour gérer les déplacements de vue
     */
    public void initializeGame(CameraController cameraController) {
        generateNextTile();
        Tile initialTile = getNextTile();
        if (initialTile == null) {
            System.out.println("Erreur : aucune tuile initiale générée.");
            return;
        }

        int centerX = gridPanel.getPreferredSize().width / 2;
        int centerY = gridPanel.getPreferredSize().height / 2;
        Point initialPosition = new Point(centerX / 50, centerY / 50);
        placeInitialTile(initialPosition, cameraController, initialTile);
        scoreGameContext.calculateScore();
        generateNextTile();
    }

    /**
     * Place la tuile initiale dans la grille et initialise les positions adjacentes comme disponibles.
     *
     * @param position         la position centrale pour la tuile initiale
     * @param cameraController le contrôleur de caméra pour appliquer l'offset de vue
     * @param tile             la tuile initiale à placer
     */
    public void placeInitialTile(Point position, CameraController cameraController, Tile tile) {
        if (tile == null) {
            System.out.println("Erreur : tuile initiale non définie.");
            return;
        }

        addHexagonTile(position, gridPanel, 50, cameraController, tile);
        availablePositions.remove(position);

        for (Point adj : getAdjacentPositions(position)) {
            if (!hexagonMap.containsKey(adj)) {
                availablePositions.add(adj);
                addHexagonTile(adj, gridPanel, 50, cameraController, null);
            }
        }
    }

    /**
     * Ajoute une nouvelle tuile hexagonale à la grille à la position spécifiée.
     *
     * @param position         la position de la tuile dans la grille
     * @param panel            le panneau contenant la grille
     * @param hexSize          la taille de l'hexagone
     * @param cameraController le contrôleur de caméra pour ajuster la position
     * @param tile             la tuile à placer ou null pour un espace réservé
     */
    public void addHexagonTile(Point position, JPanel panel, int hexSize, CameraController cameraController, Tile tile) {
        if (position == null || panel == null) {
            System.out.println("Erreur : position ou panel est null");
            return;
        }

        int xOffset = position.x * (int) (hexSize * 3 / 2);
        int yOffset = position.y * (int) (Math.sqrt(3) * hexSize);

        if (cameraController != null) {
            Point viewOffset = cameraController.getViewOffset();
            xOffset += viewOffset.x;
            yOffset += viewOffset.y;
        }

        if (position.x % 2 != 0) {
            yOffset += (int) (Math.sqrt(3) * hexSize / 2);
        }

        boolean isPlaceholder = (tile == null);
        HexagonTile hexTile = new HexagonTile(position, isPlaceholder);

        if (tile != null) {
            hexTile.setTile(tile);
        }

        hexTile.setBounds(xOffset, yOffset, hexSize, hexSize);
        hexTile.addMouseListener(new HexagonMouseListener(hexTile, this, availablePositions));
        hexagonMap.put(position, hexTile);
        panel.add(hexTile);
        panel.revalidate();
        panel.repaint();
    }

    /**
     * Génère la prochaine tuile de la série et met à jour l'aperçu.
     */
    public void generateNextTile() {
        if (tileIndex < currentTiles.size()) {
            nextTile = currentTiles.get(tileIndex++);
            updatePreview();
        } else {
            nextTile = null;
            updatePreview();
        }
    }

    /**
     * Met à jour l'aperçu de la tuile suivante.
     */
    private void updatePreview() {
        if (nextTilePreview != null) {
            if (nextTile != null) {
                nextTilePreview.setTile(nextTile);
            } else {
                nextTilePreview.setTile(null);
            }
            nextTilePreview.repaint();
        }
    }

    /**
     * Retourne la prochaine tuile à placer.
     *
     * @return la prochaine tuile ou null si aucune tuile n'est disponible
     */
    public Tile getNextTile() {
        return nextTile;
    }

    /**
     * Retourne les positions adjacentes à une position donnée dans la grille.
     *
     * @param position la position centrale
     * @return un tableau de positions adjacentes
     */
    private Point[] getAdjacentPositions(Point position) {
        if (position.x % 2 == 0) {
            return new Point[]{
                new Point(position.x + 1, position.y),
                new Point(position.x - 1, position.y),
                new Point(position.x, position.y + 1),
                new Point(position.x, position.y - 1),
                new Point(position.x + 1, position.y - 1),
                new Point(position.x - 1, position.y - 1)
            };
        } else {
            return new Point[]{
                new Point(position.x + 1, position.y),
                new Point(position.x - 1, position.y),
                new Point(position.x, position.y + 1),
                new Point(position.x, position.y - 1),
                new Point(position.x + 1, position.y + 1),
                new Point(position.x - 1, position.y + 1)
            };
        }
    }
}
