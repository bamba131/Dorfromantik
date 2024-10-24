package view;

import javax.swing.*;
import java.awt.*;


public class MenuView extends JComponent {
    private BtnPerso resumeButton;
    private BtnPerso newGameButton;
    private JButton quitButton;

    private Image backgroundImage;
    private ImageIcon logo;
    private ImageIcon quit;
    private JPanel panelCoté;
    private JLabel labelImg;
    
    
   

    public MenuView(){

         panelCoté = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panelCoté.setBackground(new Color(243,171,115,150));
        panelCoté.setPreferredSize(new Dimension(300,0));

        backgroundImage = new ImageIcon("\\\\wsl.localhost\\Ubuntu-24.04\\home\\topb\\DEV\\SAE31_2024\\src\\main\\java\\view\\img\\bg.png").getImage();
        logo = new ImageIcon("\\\\wsl.localhost\\Ubuntu-24.04\\home\\topb\\DEV\\SAE31_2024\\src\\main\\java\\view\\img\\D.png");
        quit = new ImageIcon("\\\\wsl.localhost\\Ubuntu-24.04\\home\\topb\\DEV\\SAE31_2024\\src\\main\\java\\view\\img\\quit.png");
        Image quit1 = quit.getImage();
        Image lg = logo.getImage();
        Image resizedlg = lg.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon logoIcon = new ImageIcon(resizedlg);



        labelImg = new JLabel(logoIcon);
    


        resumeButton = new BtnPerso("RESUME");
        newGameButton = new BtnPerso("NEW GAME");
        
        int buttonWidth = 65;
        int buttonHeight = 40;
        Image resizedImage = quit1.getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH); 
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        quitButton = new JButton(resizedIcon);
        quitButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        quitButton.setPreferredSize(new Dimension(50,50));
        quitButton.setBackground(new Color(243, 171, 115, 150));
        quitButton.setBorderPainted(false);
        quitButton.setOpaque(true);
        quitButton.setFocusPainted(false);

        ButtonHoverListener hoverListener = new ButtonHoverListener();
        resumeButton.addMouseListener(hoverListener);
        newGameButton.addMouseListener(hoverListener);
        quitButton.addMouseListener(hoverListener);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 1.0;

        gbc.gridx = 0;
        gbc.gridy = 0;
       
        panelCoté.add(labelImg,gbc); 
        

        gbc.gridy = 1;
        panelCoté.add(resumeButton,gbc);
        

        gbc.gridy = 2;
        panelCoté.add(newGameButton, gbc);

        gbc.gridy = 3;
        panelCoté.add(quitButton, gbc);


        setLayout(new BorderLayout());
        add(panelCoté,BorderLayout.WEST);
      
        

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
    public BtnPerso getResumeButton() {
        return resumeButton;
    }

    public BtnPerso getNewGameButton() {
        return newGameButton;
    }

    public JButton getQuiButton(){
        return quitButton;
    }

}
