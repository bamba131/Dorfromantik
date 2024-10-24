package controller;

import view.HexagonTile;
import java.awt.*;
import java.util.Map;
import java.util.Set;

public class GameContext {
    public Map<Point, HexagonTile> hexagonMap;
    public GameController gameController;
    public Set<Point> availablePositions;
    public CameraController cameraController;

    public GameContext(Map<Point, HexagonTile> hexagonMap, GameController gameController, 
                       Set<Point> availablePositions, CameraController cameraController) {
        this.hexagonMap = hexagonMap;
        this.gameController = gameController;
        this.availablePositions = availablePositions;
        this.cameraController = cameraController;
    }
}
