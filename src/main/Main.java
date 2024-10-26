package main;

import model.MenuModel;
import controller.*;
import view.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuModel model = new MenuModel();
            MenuView view = new MenuView();

            JFrame frame = App.getInstance();
            frame.add(view);

            // Créer le contrôleur
            new MenuController(model, view);

            frame.setVisible(true);

            // Chargement de la musique
            PlayMusic("/Music/audio.wav");
        });
    }

    public static void PlayMusic(String location){
        try {
            // Utilisation de getResource pour charger l'audio
            URL url = Main.class.getResource(location);

            if (url != null) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(url);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            } else {
                System.out.println("fichier introuvable");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
