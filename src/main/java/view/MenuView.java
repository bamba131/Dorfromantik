package view;

import controller.SeriesSelector;

import javax.swing.*;
import java.awt.*;

/**
 * La classe <code>MenuView</code> représente l'interface utilisateur principale
 * du menu du jeu, incluant des options pour reprendre une partie, démarrer une nouvelle partie,
 * et afficher les instructions du jeu.
 */
public class MenuView extends JPanel {

    private BtnPerso resumeButton;
    private BtnPerso newGameButton;
    private JButton quitButton;
    private JPanel panelCote;
    private JPanel howToPlayPanel;
    private JPanel centeredPanel;
    private JPanel seriesPanel;
    private SeriesSelector seriesSelector;

    private Image backgroundImage;
    private ImageIcon logo;
    private ImageIcon quit;
    private JLabel labelImg;

    /**
     * Constructeur de la classe <code>MenuView</code>.
     * Initialise le menu et ses composants.
     */
    public MenuView() {
        initMenu();
    }

    /**
     * Définit le sélecteur de séries pour le menu.
     * 
     * @param seriesSelector Le sélecteur de séries à définir.
     */
    public void setSeriesSelector(SeriesSelector seriesSelector) {
        this.seriesSelector = seriesSelector;
    }

    /**
     * Affiche les boutons de sélection de série.
     */
    public void showSeriesButtons() {
        howToPlayPanel.setVisible(false);
        centeredPanel.removeAll();
        centeredPanel.add(seriesPanel);
        seriesPanel.setVisible(true);

        revalidate();
        repaint();
    }

    /**
     * Affiche ou masque le panneau "Comment jouer".
     */
    private void toggleHowToPlay() {
        seriesPanel.setVisible(false);
        howToPlayPanel.setVisible(!howToPlayPanel.isVisible());
        centeredPanel.removeAll();
        centeredPanel.add(howToPlayPanel);

        revalidate();
        repaint();
    }

    /**
     * Initialise le menu et ses composants.
     */
    private void initMenu() {
        panelCote = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panelCote.setBackground(new Color(243, 171, 115, 150));
        panelCote.setPreferredSize(new Dimension(300, 0));

        backgroundImage = new ImageIcon(getClass().getResource("/java/view/img/bg.png")).getImage();
        logo = new ImageIcon(getClass().getResource("/java/view/img/D.png"));
        quit = new ImageIcon(getClass().getResource("/java/view/img/quit.png"));
        
        Image quit1 = quit.getImage();
        Image lg = logo.getImage();
        Image resizedlg = lg.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        labelImg = new JLabel(new ImageIcon(resizedlg));

        int buttonWidth = 65;
        int buttonHeight = 40;
        Image resizedImage = quit1.getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        resumeButton = new BtnPerso("JOUER");
        newGameButton = new BtnPerso("COMMENT JOUER");
        quitButton = new JButton(resizedIcon);

        quitButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        quitButton.setBackground(new Color(243, 171, 115, 150));
        quitButton.setBorderPainted(false);
        quitButton.setOpaque(true);
        quitButton.setFocusPainted(false);

        resumeButton.addActionListener(e -> showSeriesButtons());
        newGameButton.addActionListener(e -> toggleHowToPlay());
        quitButton.addActionListener(e -> System.exit(0)); // Quitte l'application

        howToPlayPanel = createHowToPlayPanel();
        howToPlayPanel.setVisible(false);

        seriesPanel = createSeriesPanel();
        seriesPanel.setVisible(false);

        centeredPanel = new JPanel(new GridBagLayout());
        centeredPanel.setOpaque(false);
        centeredPanel.add(howToPlayPanel);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 1.0;
        gbc.gridx = 0;

        gbc.gridy = 0;
        panelCote.add(labelImg, gbc);

        gbc.gridy = 1;
        panelCote.add(resumeButton, gbc);

        gbc.gridy = 2;
        panelCote.add(newGameButton, gbc);

        gbc.gridy = 3;
        panelCote.add(quitButton, gbc);

        setLayout(new BorderLayout());
        add(panelCote, BorderLayout.WEST);
        add(centeredPanel, BorderLayout.CENTER);
    }

    /**
     * Crée et retourne le panneau affichant les instructions de jeu.
     * 
     * @return Un panneau contenant les instructions de jeu.
     */
    private JPanel createHowToPlayPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(243, 171, 115, 200));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("COMMENT JOUER");
        titleLabel.setFont(new Font("Helvetica", Font.BOLD, 30));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);

        String[] instructions = {
            "Déplacer la caméra : clique droit enfoncé",
            "Poser des tuiles : clique gauche sur un petit hexagone",
            "Orientation des tuiles : Molette de la souris"
        };

        for (String text : instructions) {
            JLabel label = new JLabel(text);
            label.setFont(new Font("Helvetica", Font.PLAIN, 20));
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
            panel.add(label);
        }

        panel.setPreferredSize(new Dimension(700, 350));
        return panel;
    }

    /**
     * Crée et retourne le panneau de sélection des séries.
     * 
     * @return Un panneau contenant les boutons de sélection de séries.
     */
    private JPanel createSeriesPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.setOpaque(false);

        for (int i = 1; i <= 4; i++) {
            int seriesId = i;
            BtnPerso seriesButton = new BtnPerso("Série " + seriesId);
            seriesButton.addActionListener(e -> {
                GameView gameView = new GameView(seriesId);
                App.addView(gameView, App.GAME_VIEW);
                App.showView(App.GAME_VIEW);
                if (seriesSelector != null) {
                    seriesSelector.startGameWithSeries(seriesId);
                }
            });

            seriesButton.addMouseListener(new ButtonHoverListener());
            panel.add(seriesButton);
        }
        return panel;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
