package controller;

import view.HowToPlayView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HowToPlayListener implements ActionListener {
    private JFrame parent;

    public HowToPlayListener(JFrame parent) {
        this.parent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        HowToPlayView howToPlayDialog = new HowToPlayView(parent);
        howToPlayDialog.setVisible(true);
    }
}
