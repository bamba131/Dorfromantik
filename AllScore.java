import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AllScore {
    
    public static ArrayList<Integer> getScoresForSeries(int idSerie) {
        ArrayList<Integer> scores = new ArrayList<>();
        
        try { Class.forName("org.mariadb.jdbc.Driver");
      }catch(ClassNotFoundException e ){
          System.err.println("erreur db");
          System.exit(1);

      };

      try { Connection cnx = DriverManager.getConnection(
        "jdbc:mariadb://dwarves.iut-fbleau.fr/akagundu",
        "akagundu", "dersim62Lodek");
        try{
          PreparedStatement pst = cnx.prepareStatement("SELECT score from score where id_serie=?;");
          pst.setInt(1, idSerie); 
          ResultSet rs = pst.executeQuery();
          while(rs.next()) {
            scores.add(rs.getInt(1)); 
          }
          rs.close();
          pst.close();
          cnx.close();

          }
        catch(SQLException e ){
          System.err.println("erreur aff");
          System.exit(2);
        }
      }catch(SQLException e ){
        System.err.println("erreur cn");
        System.exit(2);
      };
    
        
        return scores; 
    }

    public static void main(String[] args) {
        int idSerie = 1; 
        ArrayList<Integer> scores = getScoresForSeries(idSerie);
        
        System.out.println("Scores for series " + idSerie + ": " + scores);
    }
}