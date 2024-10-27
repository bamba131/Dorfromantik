package main;
import view.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuView menuView = new MenuView();
            App.addView(menuView, App.MENU_VIEW);
            App.showView(App.MENU_VIEW);
        });
    }

    public static void PlayMusic(String location) {
        try {
            URL url = Main.class.getResource(location);
            if (url != null) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(url);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            } else {
                System.out.println("Fichier audio introuvable");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
