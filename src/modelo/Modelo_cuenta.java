package modelo;

import java.awt.List;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.parser.Entity;
import vista.VentanaAñadirTitular;

/**
 * @author Alvaro
 */
public class Modelo_cuenta extends Conexion {
    
    Cuenta cuenta;
    VentanaAñadirTitular vistaAdd;
            
    public Modelo_cuenta() {
    }
/**
 * Metodo que lista todas las cuentas
 * @return 
 */
    public DefaultTableModel getTablaCuenta() {
        DefaultTableModel tablemodel = new DefaultTableModel();
        int registros = 0;
        String[] columNames = {"nCuenta", "Fecha_creación", "Saldo"};
        try {
            PreparedStatement st = this.getConexion().prepareStatement("SELECT count(*) as total FROM Cuentas");
            ResultSet res = st.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        Object[][] data = new String[registros][5];
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement("SELECT * FROM Cuentas");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while (res.next()) {
                data[i][0] = res.getString("nCuenta");
                data[i][1] = res.getString("Fecha_creación");
                data[i][2] = res.getString("Saldo");
                i++;
            }
            res.close();
            tablemodel.setDataVector(data, columNames);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return tablemodel;
    }
    /**
     * Metodo para listar las cuentas en la vista de aniadir titulares
     * @param cuenta
     * @return 
     */
    public DefaultTableModel getTablaInfo(String cuenta) {
        DefaultTableModel tablemodel = new DefaultTableModel();
        int registros = 0;
        String[] columNames = {"nCuenta","Saldo"};
        try {
            PreparedStatement st = this.getConexion().prepareStatement("SELECT count(*) as total FROM Cuentas");
            ResultSet res = st.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        Object[][] data = new String[registros][5];
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement("SELECT * FROM Cuentas WHERE nCuenta LIKE '%"+cuenta+"%'");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while (res.next()) {
                data[i][0] = res.getString("nCuenta");
                data[i][1] = res.getString("Saldo");
                i++;
            }
            res.close();
            tablemodel.setDataVector(data, columNames);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return tablemodel;
    }
/**
 * Metodo para insertar una nueva cuenta en la base de datos
 * @param nCuenta
 * @param fecha
 * @param saldo
 * @return 
 */
    public boolean NuevaCuenta(String nCuenta, LocalDate fecha, float saldo) {
        String q = "INSERT INTO Cuentas VALUES('"+nCuenta+"','"+fecha+"','"+saldo+"')";
        try {
            PreparedStatement st = this.getConexion().prepareStatement(q);
            st.execute();
            st.close();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }
/**
 * Metodo para agregar un titular a la cuenta
 * @param nif
 * @param nCuenta
 * @return 
 */
    public boolean aniadirTitular(String nif, String nCuenta) {
        //String q ="INSERT INTO u_cuentas (NIF,nCuenta) VALUES(?,?)";
        try {
            CallableStatement cs = this.getConexion().prepareCall("{call Agregar_titular(?,?)}");
            //PreparedStatement cs = this.getConexion().prepareStatement(q);
            cs.setString(1, nif);
            cs.setString(2, nCuenta);
            cs.execute();
            cs.close();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }catch(ArrayIndexOutOfBoundsException ar){
            System.err.println(ar.getMessage());
        }
        return false;

    }
/**
 * Metodo que quita un titular de la cuenta
 * @param nif
 * @param nCuenta
 * @return 
 */
    public boolean eliminarTitular(String nif, String nCuenta) {
       //String q = " DELETE FROM u_cuenta WHERE  NIF='" + nif + "' AND nCuenta='" + nCuenta + "'";
        try {
            CallableStatement st = this.getConexion().prepareCall("{call Quitar_titular(?,?)}");
            st.setString(1, nif);
            st.setString(2, nCuenta);
            st.executeUpdate();
            st.close();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }return false;
    }
    /**
     * Metodo que verifica que solo inserta numeros
     * @param a
     * @return
     * @throws IOException 
     */
    public String soloNumeros(String a) throws IOException {

        int cont = 0;
        do {
            if (a.matches("^([0-9]+){1,2}$") == false) {
                
            } else {
                cont = 1;
            }
        } while (cont == 0);
        return a;
    }
 
    
}
    
