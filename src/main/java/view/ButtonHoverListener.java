package view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class ButtonHoverListener extends MouseAdapter {

    private final Color hoverColor = new Color(200, 150, 100, 200); // Couleur de hover avec transparence
    private final Color normalColor = new Color(243, 171, 115, 150); // Couleur normale avec transparence

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() instanceof BtnPerso) { // Applique uniquement aux boutons de type BtnPerso
            BtnPerso button = (BtnPerso) e.getSource();
            button.setBackground(hoverColor);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() instanceof BtnPerso) { // Applique uniquement aux boutons de type BtnPerso
            BtnPerso button = (BtnPerso) e.getSource();
            button.setBackground(normalColor);
        }
    }
}
