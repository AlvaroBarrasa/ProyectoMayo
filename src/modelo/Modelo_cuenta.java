package modelo;
import java.sql.*;
import java.time.LocalDate;
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

    
    public boolean NuevaCuenta(int nCuenta, LocalDate fecha_creacion , float saldo)
    {
        if( validar_cuenta(nCuenta, fecha_creacion, saldo)  )
        {
            try {
                CallableStatement cs = (CallableStatement) this.getConexion().prepareStatement("{call Agregar_cuenta(?,?,?)}");
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

    public boolean EliminarProducto( int nCuenta )
    {
         boolean res=false;
        String q = " DELETE FROM Cuenta WHERE  nCuenta='" + nCuenta + "' " ;
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

    /** Metodo privado para validar datos */
    private boolean validar_cuenta(int nCuenta, LocalDate fecha_creacion , float saldo)
    {
        if (Integer.toString(nCuenta).length()!=20) {
            return false;
    }else{
            return true;
        }

}
}