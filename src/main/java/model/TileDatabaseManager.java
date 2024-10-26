package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TileDatabaseManager {
    private static final String DB_URL = "jdbc:mariadb://dwarves.iut-fbleau.fr/akagundu";
    private static final String USER = "akagundu";
    private static final String PASSWORD = "dersim62Lodek";

    public List<Tile> getTilesBySeries(int idSeries) {
        List<Tile> tiles = new ArrayList<>();
        String query = "SELECT id, couleur1, couleur2, chiffre FROM Tuile WHERE id_serie = ? ORDER BY id ASC";
    
        try (Connection cnx = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement pst = cnx.prepareStatement(query)) {
            pst.setInt(1, idSeries);
    
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");  // Récupère l'ID de la tuile
                    String couleur1 = rs.getString("couleur1");
                    String couleur2 = rs.getString("couleur2");
                    int segmentsForTerrain1 = rs.getInt("chiffre");
    
                    System.out.println("Récupération de la tuile avec ID : " + id);  // Message de débogage
    
                    // Crée la tuile avec l'ID et les autres paramètres
                    Tile tile = new Tile(id, TerrainType.valueOf(couleur1), 
                                         couleur2 != null ? TerrainType.valueOf(couleur2) : null, 
                                         segmentsForTerrain1);
                    tiles.add(tile);
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
        return tiles;
    }
    
    
    
}
