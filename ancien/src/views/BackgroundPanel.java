package views;
import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            ImageIcon bg = new ImageIcon("menu.jpg"); 
            g.drawImage(bg.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
}
