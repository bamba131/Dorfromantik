package controller;

/**
 * L'interface GameEndListener définit un écouteur pour la fin de la partie.
 * Elle permet de notifier la fin de la partie et de transmettre le score final.
 */
public interface GameEndListener {

    /**
     * Méthode appelée lorsque la partie se termine.
     *
     * @param finalScore le score final atteint à la fin de la partie
     */
    void onGameEnd(int finalScore);
}