package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuView extends JFrame {
    private JTextField playerNameField;
    private JComboBox<String> suiteSelector;
    private JButton playGameButton;
    private JButton continueGameButton;

    public MenuView() {
        // Configuration de la fenêtre
        setTitle("Dorf Javatik");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Arrière-plan personnalisé
        BackgroundPanel background = new BackgroundPanel();
        add(background, BorderLayout.CENTER);

        JPanel panelArrondi = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                
                g2d.setColor(new Color(0, 0, 0, 100)); 

                
                int arcWidth = 10; 
                int arcHeight = 10;
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);
            }

            
            @Override
            public boolean isOpaque() {
                return false;
            }
        };

        JButton btn = new JButton("Jouer");
        this.playGameButton = btn;
        JButton btn2 = new JButton("Continuer");
        this.continueGameButton = btn2;
       
        panelArrondi.add(playGameButton);
        panelArrondi.add(continueGameButton);
        add(panelArrondi, BorderLayout.WEST);
    }

    public String getPlayerName() {
        return playerNameField.getText();
    }

    public String getSelectedSuite() {
        return (String) suiteSelector.getSelectedItem();
    }

  
    public void addContinueGameListener(ActionListener listener) {
        continueGameButton.addActionListener(listener);
    }
    public void addPlayGameListener(ActionListener listener) {
        playGameButton.addActionListener(listener);
    }

   
}
