package modelo;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.table.DefaultTableModel;
/**
 * @author Alvaro
 */
public class Modelo_cuenta extends Conexion{


    public Modelo_cuenta (){}

    public DefaultTableModel getTablaCuenta()
    {
      DefaultTableModel tablemodel = new DefaultTableModel();
      int registros = 0;
      String[] columNames = {"nCuenta","Fecha_creación","Saldo"};
      try{
         PreparedStatement st = this.getConexion().prepareStatement( "SELECT count(*) as total FROM Cuenta");
         ResultSet res = st.executeQuery();
         res.next();
         registros = res.getInt("total");
         res.close();
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
    Object[][] data = new String[registros][5];
      try{
         PreparedStatement pstm = this.getConexion().prepareStatement("SELECT * FROM Cuenta");
         ResultSet res = pstm.executeQuery();
         int i=0;
         while(res.next()){
                data[i][0] = res.getString( "nCuenta" );
                data[i][1] = res.getString( "Fecha_creación" );
                data[i][2] = res.getString( "Saldo" );
            i++;
         }
         res.close();
         tablemodel.setDataVector(data, columNames );
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return tablemodel;
    }

    
    public boolean NuevaCuenta(Cuenta c)
    {
        if( validar_ncuenta(c.getnCuenta())  )
        {
            try {
                CallableStatement cs = (CallableStatement) this.getConexion().prepareStatement("{call Agregar_cuenta(?,?,?)}");
                cs.setString(1, c.getnCuenta());
                cs.setDate(2, Date.valueOf(c.getFecha_creacion()));
                cs.setFloat(3, c.getSaldo());
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

    public boolean aniadirTitular(String nif, String nCuenta){
        if (validar_ncuenta(nCuenta)) {
            try {
                CallableStatement cs = (CallableStatement) this.getConexion().prepareStatement("{call Agregar_titular(?,?)}");
                cs.setString(1, nif);
                cs.setString(2, nCuenta);
                cs.execute();
                cs.close();
                return true;
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
            return false;
        }
        else{
            return false;
        }
    }
    
    public boolean eliminarTitular( String nif )
    {
         boolean res=false;
        String q = " DELETE FROM usu_cuenta WHERE  NIF='" + nif + "' " ;
         try {
            PreparedStatement st = this.getConexion().prepareStatement(q);
            st.execute();
            st.close();
            res=true;
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return res;
    }

    private boolean validar_ncuenta(String nCuenta)
    {
        boolean correcto=false;
        if (nCuenta.length()!=23) {
            return false;
        }
        Pattern patron = Pattern.compile("(\\d{10})");
        Matcher mat = patron.matcher(nCuenta);
        if (mat.matches()) {
            correcto=true;
        }else{
            correcto=false;
        }
        return correcto;
}
}