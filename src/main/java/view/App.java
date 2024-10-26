package view;

import javax.swing.*;

public class App {
    private static JFrame frame;

    public static JFrame getInstance() {
        if (frame == null) {
            frame = new JFrame("Menu");
            frame.setSize(1500, 750);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        return frame;
    }
}
