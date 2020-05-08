
package modelo;

import com.mysql.jdbc.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.sound.midi.SysexMessage;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alvaro
 */
public class Modelo_usuario extends Conexion {
    
    public DefaultTableModel getTablaUsuario()
    {
      DefaultTableModel tablemodel = new DefaultTableModel();
      int registros = 0;
      String[] columNames = {"NIF","Apellidos","Nombre","Año_nacimiento","Dirección","Email","Teléfono"};
      try{
         PreparedStatement st = this.getConexion().prepareStatement( "SELECT count(*) as total FROM Usuario");
         ResultSet res = st.executeQuery();
         res.next();
         registros = res.getInt("total");
         res.close();
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }

    Object[][] data = new String[registros][8];
      try{
         PreparedStatement st = this.getConexion().prepareStatement("SELECT * FROM Usuario");
         ResultSet resultado = st.executeQuery();
         int i=0;
         while(resultado.next()){
                data[i][0] = resultado.getString( "NIF" );
                data[i][1] = resultado.getString( "Apellidos" );
                data[i][2] = resultado.getString( "Nombre" );
                data[i][3] = resultado.getString( "Año_nacimiento" );
                data[i][4] = resultado.getString( "Dirección" );
                data[i][5] = resultado.getString( "Email" );
                data[i][6] = resultado.getString( "Teléfono" );
            i++;
         }
         resultado.close();
         tablemodel.setDataVector(data, columNames );
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return tablemodel;
    }
    public boolean NuevoUsuario(Usuario u)
    {
        if( validar_usuario(u.getDni())  )
        {
            try {
                CallableStatement cs =  (CallableStatement) this.getConexion().prepareCall("{call Agregar_usuario(?,?,?,?,?,?,?)}");
                cs.setString(1, u.getDni());
                cs.setString(2, u.getApellidos());
                cs.setString(3, u.getNombre());
                cs.setInt(4, u.getAnio_nacimiento());
                cs.setString(5, u.getDireccion());
                cs.setString(6, u.getEmail());
                cs.setInt(7, u.getTelefono());
                cs.execute();
                cs.close();
                return true;
            }catch(SQLException e){
                System.err.println( e.getMessage() );
            }
            return false;
        }
        else
         return false;
    }
    
    private boolean validar_usuario(String dni)
    {
        try {
            CallableStatement cs = (CallableStatement) this.getConexion().prepareCall("{?=call Valida_dni(?)}");
            cs.registerOutParameter(1, Types.VARCHAR);
            cs.setString(2, dni);
            cs.execute();
            cs.close();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }
    
    public boolean ModificaUsuario(Usuario u)
    {
         try {
                CallableStatement cs =  (CallableStatement) this.getConexion().prepareCall("{call Modifica_usuario(?,?,?,?,?,?,?)}");
                cs.setString(1, u.getDni());
                cs.setString(2, u.getApellidos());
                cs.setString(3, u.getNombre());
                cs.setInt(4, u.getAnio_nacimiento());
                cs.setString(5, u.getDireccion());
                cs.setString(6, u.getEmail());
                cs.setInt(7, u.getTelefono());
                cs.execute();
                cs.close();
                return true;
            }catch(SQLException e){
                System.err.println( e.getMessage() );
            }
            return false;
}
}
