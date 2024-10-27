package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * La classe NewListener implémente ActionListener et gère les actions
 * liées à la création d'un nouveau jeu. 
 */
public class NewListener implements ActionListener {

    /**
     * Appelé lorsque l'action est déclenchée. 
     * Actuellement, cela imprime un message de test dans la console.
     *
     * @param e l'événement d'action qui a été déclenché
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Juste pour tester - New Game");
        
    }
}