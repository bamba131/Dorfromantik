package view;

import controller.AllScore;
import controller.BackButtonMouseListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * La classe <code>ScoreView</code> représente l'interface utilisateur
 * affichant le score final du joueur ainsi que le top 10 des scores
 * pour une série spécifique à la fin d'une partie.
 */
public class ScoreView extends JPanel {
    private int seriesId;
    private int finalScore;
    private final Color hoverColor = new Color(200, 150, 100, 150);
    private final Color normalColor = new Color(243, 171, 115, 150); // Couleur avec transparence

    /**
     * Constructeur de la classe <code>ScoreView</code>.
     * 
     * @param seriesId L'identifiant de la série.
     * @param finalScore Le score final du joueur.
     */
    public ScoreView(int seriesId, int finalScore) {
        this.seriesId = seriesId;
        this.finalScore = finalScore;
        initScoreView();
    }

    /**
     * Initialise l'interface utilisateur pour afficher le score final
     * et le top 10 des scores.
     */
    private void initScoreView() {
        setLayout(new BorderLayout());
        setBackground(normalColor);

        // Titre de la vue de score
        JLabel title = new JLabel("Fin de la Partie", JLabel.CENTER);
        title.setFont(new Font("Helvetica", Font.BOLD, 30));
        add(title, BorderLayout.NORTH);

        // Affichage du score actuel de la partie
        JLabel scoreLabel = new JLabel("Votre Score : " + finalScore, JLabel.CENTER);
        scoreLabel.setFont(new Font("Helvetica", Font.PLAIN, 20));
        add(scoreLabel, BorderLayout.CENTER);

        // Panneau des scores du top 10
        JPanel topScoresPanel = new JPanel();
        topScoresPanel.setLayout(new BoxLayout(topScoresPanel, BoxLayout.Y_AXIS));
        topScoresPanel.setBorder(BorderFactory.createTitledBorder("Top 10 des Scores"));
        topScoresPanel.setBackground(normalColor);

        ArrayList<Integer> scores = AllScore.getScoresForSeries(seriesId);
        Collections.sort(scores, Collections.reverseOrder());
        scores = new ArrayList<>(scores.subList(0, Math.min(10, scores.size())));

        for (Integer score : scores) {
            JLabel scoreItem = new JLabel(score.toString(), JLabel.CENTER);
            scoreItem.setFont(new Font("Helvetica", Font.PLAIN, 18));
            scoreItem.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Marquer le score final en couleur spéciale
            if (score == finalScore) {
                scoreItem.setForeground(new Color(128, 0, 128)); // Violet pour le score actuel
            } else {
                scoreItem.setForeground(Color.BLACK);
            }

            topScoresPanel.add(scoreItem);
        }

        add(topScoresPanel, BorderLayout.CENTER);

        // Bouton de retour au menu
        BtnPerso backButton = new BtnPerso("Retour au Menu");
        backButton.addActionListener(e -> App.showView(App.MENU_VIEW));

        // Ajout de BackButtonMouseListener pour le survol
        backButton.addMouseListener(new BackButtonMouseListener(backButton, hoverColor, normalColor));

        // Panneau de score actuel
        JPanel currentScorePanel = new JPanel();
        currentScorePanel.setBackground(normalColor);
        JLabel currentScoreLabel = new JLabel("Score Actuel : " + finalScore);
        currentScoreLabel.setFont(new Font("Helvetica", Font.BOLD, 22));
        currentScorePanel.add(currentScoreLabel);

        // Ajouter le panneau de score actuel et le bouton de retour
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(currentScorePanel, BorderLayout.NORTH);
        southPanel.add(backButton, BorderLayout.SOUTH);

        add(southPanel, BorderLayout.SOUTH);
    }
}
