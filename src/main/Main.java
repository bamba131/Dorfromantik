package main;

import model.MenuModel;
import controller.MenuController;
import controller.SeriesSelector;
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

            // Initialiser SeriesSelector et le passer à MenuView
            SeriesSelector seriesSelector = new SeriesSelector();
            view.setSeriesSelector(seriesSelector);

            // Créer MenuController avec model, view et seriesSelector
            new MenuController(model, view, seriesSelector);

            JFrame frame = App.getInstance();
            frame.add(view);
            frame.setVisible(true);

            PlayMusic("/Music/audio.wav");
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
