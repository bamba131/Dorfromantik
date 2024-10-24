package view;

import javax.swing.JButton;
import java.awt.*;


public class BtnPerso extends JButton {

    public BtnPerso(String text){
        super(text);
        setFont(new Font("Helvetica", Font.BOLD,25));
        setContentAreaFilled(false);
        setForeground(Color.BLACK);
        setBorderPainted(false);
        setOpaque(true);
        setFocusPainted(false);
        setBackground(new Color(243, 171, 115, 150));

        Dimension d = new Dimension(200,50);
        setPreferredSize(d);
    }

    
}
