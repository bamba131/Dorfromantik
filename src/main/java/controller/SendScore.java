package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SendScore {
    public void insertscore(int idSerie, int score) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Erreur : pilote JDBC non trouvé");
            System.exit(1);
        }

        try (Connection cnx = DriverManager.getConnection(
                "jdbc:mariadb://dwarves.iut-fbleau.fr/akagundu",
                "akagundu", "dersim62Lodek")) {
            try (PreparedStatement pst = cnx.prepareStatement("INSERT INTO Score (id_serie, score) VALUES (?, ?);")) {
                pst.setInt(1, idSerie);
                pst.setInt(2, score);
                pst.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Erreur de connexion ou d'exécution de la requête SQL");
            e.printStackTrace();
        }
    }
}
