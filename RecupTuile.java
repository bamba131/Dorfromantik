import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RecupTuile {

    public String[][] recup(int idSerie) {
        String[][] tuiles = new String[3][50];  
        int index = 0; 

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("erreur db");
            return tuiles;  
        }

        try (Connection cnx = DriverManager.getConnection(
                "jdbc:mariadb://dwarves.iut-fbleau.fr/akagundu",
                "akagundu", "dersim62Lodek")) {

            String query = "SELECT couleur1, couleur2, nombre FROM Tuile WHERE id_serie = ?";  

            try (PreparedStatement pst = cnx.prepareStatement(query)) {
                pst.setInt(1, idSerie);  

                try (ResultSet rs = pst.executeQuery()) {
                    while (rs.next() && index < 50) { 
                        String couleur1 = rs.getString(1);
                        String couleur2 = rs.getString(2);
                        int nombre = rs.getInt(3);

                        // Remplir le tableau
                        tuiles[0][index] = couleur1 != null ? couleur1 : "null";
                        tuiles[1][index] = couleur2 != null ? couleur2 : "null";
                        tuiles[2][index] = String.valueOf(nombre);

                        index++; 
                    }
                }
            } catch (SQLException e) {
                System.err.println("erreur aff " + e.getMessage());
            }
        } catch (SQLException e) {
            System.err.println("erreur cn " + e.getMessage());
        }

        return tuiles;  
    }

    public static void main(String[] args) {
        RecupTuile recupTuile = new RecupTuile();
        int idSerie = 1;  
        String[][] tuiles = recupTuile.recup(idSerie);

        for (int i = 0; i < 50; i++) { 
            if (tuiles[0][i] != null) {  
                System.out.println("Tuile " + (i + 1) + ": Couleur 1 = " + tuiles[0][i] + ", Couleur 2 = " + tuiles[1][i] + ", Nombre = " + tuiles[2][i]);
            }
        }
    }
}
