package view;

import javax.swing.*;
import java.awt.*;

public class HexagonGridPanel extends JPanel {

    public HexagonGridPanel() {
        super(null);  // Layout null pour gérer manuellement la position des composants
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(3000, 3000);
    }
}
