import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SendScore {
    public void insertscore(int idSerie,int score) {        
        try { Class.forName("org.mariadb.jdbc.Driver");
      }catch(ClassNotFoundException e ){
          System.err.println("erreur db");
          System.exit(1);

      };

      try { Connection cnx = DriverManager.getConnection(
        "jdbc:mariadb://dwarves.iut-fbleau.fr/akagundu",
        "akagundu", "dersim62Lodek");
        try{
          PreparedStatement pst = cnx.prepareStatement("INSERT INTO score (id_serie, score) VALUES (?, ?);");
          pst.setInt(1, idSerie); 
          pst.setInt(2, score);
          pst.executeUpdate();
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
    }

    public static void main(String[] args) {
        int idSerie = 1;
        int score=111;
        SendScore sendScore = new SendScore();
        sendScore.insertscore(idSerie, score);          
        System.out.println("Scores for series " + idSerie + ": " + score);
    }
}