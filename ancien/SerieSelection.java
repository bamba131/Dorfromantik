import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SerieSelection {

    public static void main(String[] args) {
        JFrame fenetre = new JFrame("Sélection de série");
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setSize(600, 200);  
        fenetre.setLayout(new BorderLayout()); 

        JLabel message = new JLabel("Avec quelle série voulez-vous jouer ?", JLabel.CENTER);
        message.setFont(new Font("Arial", Font.BOLD, 16)); 
        fenetre.add(message, BorderLayout.NORTH); 

        JPanel panelBoutons = new JPanel();
        panelBoutons.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20)); 

        JButton serie1Button = new JButton("Série 1");
        JButton serie2Button = new JButton("Série 2");
        JButton serie3Button = new JButton("Série 3");
        JButton serie4Button = new JButton("Série 4");

        serie1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(fenetre, "Vous avez sélectionné la Série 1 !");
            }
        });

        serie2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(fenetre, "Vous avez sélectionné la Série 2 !");
            }
        });

        serie3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(fenetre, "Vous avez sélectionné la Série 3 !");
            }
        });

        serie4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(fenetre, "Vous avez sélectionné la Série 4 !");
            }
        });

        panelBoutons.add(serie1Button);
        panelBoutons.add(serie2Button);
        panelBoutons.add(serie3Button);
        panelBoutons.add(serie4Button);

        fenetre.add(panelBoutons, BorderLayout.CENTER);

        fenetre.setVisible(true);
    }
}
