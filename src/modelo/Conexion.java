package modelo;
import java.sql.*;
/**

 * @author Mouse
 */
public class Conexion {
 /* DATOS PARA LA CONEXION */
  /** base de datos por defecto es test*/
  private String db = "abarrasa_US";
  /** usuario */
  private String user = "AdminUs";
  /** contraseña de MySql*/
  private String password = "Barrasa0416_";
  /** Cadena de conexion */
  private String url = "jdbc:mysql://abarrasa.salesianas.es/"+db;
  /** variable para trabajar con la conexion a la base de datos */
  private Connection conn = null;

   /** Constructor de clase */
   public Conexion(){
        this.url = "jdbc:mysql://abarrasa.salesianas.es/"+this.db;
       try{
         //obtenemos el driver de para mysql
         Class.forName("com.mysql.jdbc.Driver");
         //obtenemos la conexión
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
