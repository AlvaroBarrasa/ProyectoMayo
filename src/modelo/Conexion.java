package modelo;
import java.sql.*;
/**
 * Clase para conectarme a la base de datos
 * @author Alvaro
 */
public class Conexion {

  private String db = "abarrasa_US";

  private String user = "AdminUs";

  private String password = "Barrasa0416_";
  
  private String url = "jdbc:mysql://abarrasa.salesianas.es/"+db;

  private Connection conn = null;


   public Conexion(){
        this.url = "jdbc:mysql://abarrasa.salesianas.es/"+this.db;
       try{

         Class.forName("com.mysql.jdbc.Driver");
  
         conn = DriverManager.getConnection( this.url, this.user , this.password);         
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }catch(ClassNotFoundException e){
         System.err.println( e.getMessage() );
      }
   }


   public Connection getConexion()
   {
    return this.conn;
   }

}
