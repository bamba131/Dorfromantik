package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AllScore {
    public static ArrayList<Integer> getScoresForSeries(int idSerie) {
        ArrayList<Integer> scores = new ArrayList<>();

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Erreur : pilote JDBC non trouvé");
            System.exit(1);
        }

        try (Connection cnx = DriverManager.getConnection(
                "jdbc:mariadb://dwarves.iut-fbleau.fr/akagundu",
                "akagundu", "dersim62Lodek")) {
            try (PreparedStatement pst = cnx.prepareStatement("SELECT score FROM Score WHERE id_serie=? ORDER BY score DESC LIMIT 10")) {
                pst.setInt(1, idSerie);
                try (ResultSet rs = pst.executeQuery()) {
                    while (rs.next()) {
                        scores.add(rs.getInt("score"));
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur de connexion ou d'exécution de la requête SQL");
            e.printStackTrace();
        }

        return scores;
    }
}
