package view;

import javax.swing.*;
import java.awt.*;
import controller.SeriesSelector;

public class MenuView extends JComponent {

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
    private JLabel labelImg; // Déclaration de labelImg

    public MenuView() {
        initMenu();
    }

    public void setSeriesSelector(SeriesSelector seriesSelector) {
        this.seriesSelector = seriesSelector;
    }

    // Méthode pour afficher les boutons de sélection de série
    public void showSeriesButtons() {
        howToPlayPanel.setVisible(false);
        centeredPanel.removeAll();
        centeredPanel.add(seriesPanel);
        seriesPanel.setVisible(true);

        revalidate();
        repaint();
    }

    // Méthode pour afficher/masquer le panneau "Comment jouer"
    private void toggleHowToPlay() {
        seriesPanel.setVisible(false);
        howToPlayPanel.setVisible(!howToPlayPanel.isVisible());
        centeredPanel.removeAll();
        centeredPanel.add(howToPlayPanel);

        revalidate();
        repaint();
    }

    private void initMenu() {
        // Initialisation du panneau latéral
        panelCote = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panelCote.setBackground(new Color(243, 171, 115, 150));
        panelCote.setPreferredSize(new Dimension(300, 0));

        // Charger les images
        backgroundImage = new ImageIcon(getClass().getResource("/java/view/img/bg.png")).getImage();
        logo = new ImageIcon(getClass().getResource("/java/view/img/D.png"));
        quit = new ImageIcon(getClass().getResource("/java/view/img/quit.png"));
        
        // Redimensionnement des images
        Image quit1 = quit.getImage();
        Image lg = logo.getImage();
        Image resizedlg = lg.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        labelImg = new JLabel(new ImageIcon(resizedlg)); // Initialisation de labelImg

        // Configuration du bouton "Quitter" avec une icône redimensionnée
        int buttonWidth = 65;
        int buttonHeight = 40;
        Image resizedImage = quit1.getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        // Boutons
        resumeButton = new BtnPerso("JOUER");
        newGameButton = new BtnPerso("COMMENT JOUER");
        quitButton = new JButton(resizedIcon);

        // Configurer le bouton "Quitter" pour enlever le fond et la bordure
        quitButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        quitButton.setBackground(new Color(243, 171, 115, 150)); // Fond transparent similaire
        quitButton.setBorderPainted(false); // Enlever la bordure pour un look plus propre
        quitButton.setOpaque(true); // Rendre le fond visible
        quitButton.setFocusPainted(false); // Enlever le focus autour du bouton

        // Ajout des listeners pour les boutons
        resumeButton.addActionListener(e -> showSeriesButtons());
        newGameButton.addActionListener(e -> toggleHowToPlay());

        // Créer le panneau "Comment jouer" et le panneau de séries
        howToPlayPanel = createHowToPlayPanel();
        howToPlayPanel.setVisible(false);

        seriesPanel = createSeriesPanel();
        seriesPanel.setVisible(false);

        // Panneau centré pour le contenu dynamique
        centeredPanel = new JPanel(new GridBagLayout());
        centeredPanel.setOpaque(false);
        centeredPanel.add(howToPlayPanel);

        // Ajout des composants au panneau latéral
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 1.0;
        gbc.gridx = 0;

        // Ajouter l'image de logo
        gbc.gridy = 0;
        panelCote.add(labelImg, gbc); // Ajout de labelImg ici

        // Ajouter les boutons
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

    private JPanel createSeriesPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.setOpaque(false);

        for (int i = 1; i <= 4; i++) {
            int seriesId = i;
            BtnPerso seriesButton = new BtnPerso("Série " + seriesId);
            seriesButton.addActionListener(e -> {
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

    public BtnPerso getResumeButton() {
        return resumeButton;
    }

    public BtnPerso getNewGameButton() {
        return newGameButton;
    }

    public JButton getQuiButton() {
        return quitButton;
    }
}
