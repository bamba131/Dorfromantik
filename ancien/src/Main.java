import java.awt.Menu;

import controllers.MenuController;
import models.MenuModel;
import views.MenuView;

public class Main {
    public static void main(String[] args) {
        // Initialisation du modèle, de la vue et du contrôleur
        MenuModel model = new MenuModel();
        MenuView view = new MenuView();
        MenuController controller  = new MenuController(model, view);

        // Affichage de la fenêtre
        view.setVisible(true);
    }
}