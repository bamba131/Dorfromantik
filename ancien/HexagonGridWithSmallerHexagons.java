import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;

public class HexagonGridWithSmallerHexagons extends JPanel {

    int bigHexRadius = 100; 
    int smallHexRadius = 15; 

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        drawBigHexagonWithSmallHexagons(g2d, 200, 200, bigHexRadius);
    }

    public void drawBigHexagonWithSmallHexagons(Graphics2D g2d, int x, int y, int radius) {
        g2d.setColor(Color.BLACK);
        drawHexagon(g2d, x, y, radius, true);

        int xOffset = (int) (1.5 * smallHexRadius);
        int yOffset = (int) (Math.sqrt(3) * smallHexRadius / 2);
        
        for (int row = -3; row <= 3; row++) {
            for (int col = -3; col <= 3; col++) {
                int xPos = x + col * xOffset;
                int yPos = y + row * yOffset * 2;
                if (col % 2 != 0) {
                    yPos += yOffset; 
                }
                if (isInsideBigHexagon(xPos, yPos, x, y, radius)) {
                    g2d.setColor(new Color(135, 206, 235)); 
                    drawHexagon(g2d, xPos, yPos, smallHexRadius, true);
                }
            }
        }
    }

    public boolean isInsideBigHexagon(int xPos, int yPos, int centerX, int centerY, int radius) {
        double dx = Math.abs(xPos - centerX);
        double dy = Math.abs(yPos - centerY);
        double distance = Math.sqrt(dx * dx + dy * dy);
        return distance < radius;
    }

    public void drawHexagon(Graphics2D g2d, int x, int y, int radius, boolean fill) {
        Path2D hexagon = new Path2D.Double();
        for (int i = 0; i < 6; i++) {
            double angle = Math.toRadians(60 * i);
            int xOffset = (int) (x + radius * Math.cos(angle));
            int yOffset = (int) (y + radius * Math.sin(angle));
            if (i == 0) {
                hexagon.moveTo(xOffset, yOffset);
            } else {
                hexagon.lineTo(xOffset, yOffset);
            }
        }
        hexagon.closePath();
        if (fill) {
            g2d.fill(hexagon);
        }
        g2d.setColor(Color.BLACK);
        g2d.draw(hexagon);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.add(new HexagonGridWithSmallerHexagons());
        frame.setVisible(true);
    }
}
