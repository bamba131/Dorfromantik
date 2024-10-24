import model.*;
import controller.*;
import view.*;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuModel model = new MenuModel();
            MenuView view = new MenuView();
            
          
            JFrame frame = new JFrame(" Menu");
            frame.setSize(1500, 750);                                  
            frame.setLocation(0, 0);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(view);
            
            // Créer le contrôleur
            new MenuController(model, view);

            frame.setVisible(true);
            String filepath = "Music/audio.wav";
            PlayMusic(filepath);
        });
    }

    public static void PlayMusic(String location){
        try {
           // System.out.println(location);
            File musicPath = new File(location);

            if(musicPath.exists()){

                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
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