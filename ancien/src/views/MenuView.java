package views;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MenuView extends JPanel {
    private BtnPerso resumeButton;
    private BtnPerso newGameButton;
    private JButton quitButton;

    private Image backgroundImage;
    private Image logo;
    private ImageIcon quit;
    
    
   

    public MenuView() throws FontFormatException, IOException{
        backgroundImage = new ImageIcon("C:\\Users\\topba\\OneDrive\\Desktop\\BUT\\annee2\\DEV3.1\\Dorfromantik\\scr\\views\\img\\bg.png").getImage();
        setLayout(null);
        logo = new ImageIcon("C:\\Users\\topba\\OneDrive\\Desktop\\BUT\\annee2\\DEV3.1\\Dorfromantik\\scr\\views\\img\\D.png").getImage();
        quit = new ImageIcon("C:\\Users\\topba\\OneDrive\\Desktop\\BUT\\annee2\\DEV3.1\\Dorfromantik\\scr\\views\\img\\quit.png");
        Image quit1 = quit.getImage();

        resumeButton = new BtnPerso("RESUME");
        newGameButton = new BtnPerso("NEW GAME");
        
        int buttonWidth = 65;
        int buttonHeight = 40;
        
        Image resizedImage = quit1.getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH); 
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        quitButton = new JButton(resizedIcon);
        quitButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        resumeButton.setBounds(-50, 350, 400, 50);
        newGameButton.setBounds(-20, 420, 400, 50);
        quitButton.setBounds(270, 630,50,50);
        quitButton.setBackground(new Color(215, 171, 115, 150));
        quitButton.setBorderPainted(false);

        add(quitButton);
        add(resumeButton);
        add(newGameButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        g.setColor(new Color(243, 171, 115, 150));
        g.fillRect(0, 0, (getWidth() / 4) , getHeight());

        g.drawImage(logo, 0, 0, (getWidth()/4)  ,getHeight()/2,this);
    }
    public BtnPerso getResumeButton() {
        return resumeButton;
    }

    public BtnPerso getNewGameButton() {
        return newGameButton;
    }

}

