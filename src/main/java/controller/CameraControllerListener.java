package controller;

import java.awt.Point;

public interface CameraControllerListener {
    void updateViewOffset(int deltaX, int deltaY);
    void setMouseDragStart(Point point);
    Point getMouseDragStart();
    void resetMouseDragStart();
}
