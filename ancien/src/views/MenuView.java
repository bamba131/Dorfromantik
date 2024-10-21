package views;

import javax.swing.*;
import java.awt.*;

public class MenuView extends JPanel {
    private JButton resumeButton;
    private JButton newGameButton;

    private Image backgroundImage;

    public MenuView() {
        backgroundImage = new ImageIcon("C:\\Users\\topba\\OneDrive\\Desktop\\BUT\\annee2\\DEV3.1\\Dorfromantik\\scr\\views\\img\\bg.png").getImage();
        setLayout(null);

        resumeButton = new JButton("RESUME");
        newGameButton = new JButton("NEW GAME");

        resumeButton.setBounds(10, 200, 200, 50);
        newGameButton.setBounds(10, 270, 200, 50);

        add(resumeButton);
        add(newGameButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRect(0, 0, getWidth() / 6, getHeight()); 
    }

    public JButton getResumeButton() {
        return resumeButton;
    }

    public JButton getNewGameButton() {
        return newGameButton;
    }

}
