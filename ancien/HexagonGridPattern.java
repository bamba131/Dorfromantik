import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;
import java.util.Random;

public class HexagonGridPattern extends JPanel {

    int bigHexRadius = 170; 
    int smallHexRadius = 30; 
    Color[] blueShades = {new Color(173, 216, 230), new Color(135, 206, 250), new Color(0, 191, 255)}; 

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        drawBigHexagonWithSmallHexagons(g2d, 300, 300, bigHexRadius);
    }

    public void drawBigHexagonWithSmallHexagons(Graphics2D g2d, int x, int y, int radius) {
        g2d.setColor(Color.BLACK);
        drawHexagon(g2d, x, y, radius, false);

        int xOffset = (int) (smallHexRadius * 2); 
        int yOffset = (int) (Math.sqrt(3) * smallHexRadius); 

        Random random = new Random();

        for (int row = -3; row <= 3; row++) {
            for (int col = -3; col <= 3; col++) {
                int xPos = x + col * xOffset;
                int yPos = y + row * yOffset;

                if (row % 2 != 0) {
                    xPos += xOffset / 2; 
                }

                if (isInsideBigHexagon(xPos, yPos, x, y, radius)) {
                    g2d.setColor(blueShades[random.nextInt(blueShades.length)]);
                    drawInvertedHexagon(g2d, xPos, yPos, smallHexRadius, true); 
                }
            }
        }
    }

    public boolean isInsideBigHexagon(int xPos, int yPos, int centerX, int centerY, int radius) {
        double dx = Math.abs(xPos - centerX);
        double dy = Math.abs(yPos - centerY);
        return dx + dy < radius * Math.sqrt(3); 
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

    public void drawInvertedHexagon(Graphics2D g2d, int x, int y, int radius, boolean fill) {
        Path2D hexagon = new Path2D.Double();
        for (int i = 0; i < 6; i++) {
            double angle = Math.toRadians(60 * i + 30); 
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
        frame.setSize(600, 600);
        frame.add(new HexagonGridPattern());
        frame.setVisible(true);
    }
}
