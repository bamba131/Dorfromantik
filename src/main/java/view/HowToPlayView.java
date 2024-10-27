package view;

import javax.swing.*;
import java.awt.*;

public class HowToPlayView extends JDialog {

    public HowToPlayView(JFrame parent) {
        super(parent, "Comment Jouer", true);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(243, 171, 115, 200));
        
        JLabel titleLabel = new JLabel("COMMENT JOUER");
        titleLabel.setFont(new Font("Helvetica", Font.BOLD, 30));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);
        
        JLabel instruction1 = new JLabel("Déplacer la caméra : clique droit enfoncé");
        JLabel instruction2 = new JLabel("Poser des tuiles : clique gauche sur un petit hexagone");
        JLabel instruction3 = new JLabel("Orientation des tuiles : Molette de la souris");

        for (JLabel label : new JLabel[]{instruction1, instruction2, instruction3}) {
            label.setFont(new Font("Helvetica", Font.PLAIN, 20));
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
            panel.add(label);
        }
        
        add(panel);
        setSize(600, 400);
        setLocationRelativeTo(parent);
    }
}
