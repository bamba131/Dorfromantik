package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * La classe AllScore est responsable de la récupération des scores pour une série spécifique depuis la base de données.
 * Elle utilise JDBC pour se connecter à une base de données MariaDB, exécuter une requête SQL, et retourner les scores 
 * sous forme de liste d'entiers.
 */
public class AllScore {

     /**
     * Récupère les scores pour une série donnée, identifiée par son identifiant, depuis la base de données.
     * Les scores sont récupérés en ordre décroissant et limités aux 10 meilleurs scores.
     *
     * @param idSerie l'identifiant de la série pour laquelle récupérer les scores
     * @return une liste d'entiers contenant les scores de la série, classés par ordre décroissant
     * @throws RuntimeException si le pilote JDBC n'est pas trouvé
     * @throws SQLException si une erreur survient lors de la connexion ou de l'exécution de la requête SQL
     */
    public static ArrayList<Integer> getScoresForSeries(int idSerie) {
        ArrayList<Integer> scores = new ArrayList<>();

        try {
            // Chargement du driver JDBC pour MariaDB
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Erreur : pilote JDBC non trouvé");
            System.exit(1);
        }
        // Préparation de la requête SQL pour récupérer les scores
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
