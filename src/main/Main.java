package main;

import view.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.net.URL;

/**
 * Classe principale du programme qui initialise l'interface utilisateur
 * et joue un fichier audio en boucle.
 */
public class Main {

    /**
     * Point d'entrée du programme. Initialise l'interface utilisateur
     * et démarre la lecture en boucle de la musique de fond.
     *
     * @param args les arguments de ligne de commande (non utilisés)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuView menuView = new MenuView();
            App.addView(menuView, App.MENU_VIEW);
            App.showView(App.MENU_VIEW);
            String filepath = "/java/Music/audio.wav";
            PlayMusic(filepath);
        });
    }

    /**
     * Joue un fichier audio en boucle.
     *
     * @param location le chemin d'accès au fichier audio à jouer
     */
    public static void PlayMusic(String location) {
        try {
            URL url = Main.class.getResource(location);
            if (url != null) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(url);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY); // Boucle en continu
            } else {
                System.out.println("Fichier audio introuvable");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
