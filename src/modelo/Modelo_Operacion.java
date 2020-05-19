package modelo;

import com.mysql.jdbc.exceptions.MySQLDataException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import vista.VentanaOperacion;

public class Modelo_Operacion extends Conexion {

    /**
     * @author Alvaro
     */
    public Modelo_Operacion() {
    }

    VentanaOperacion ope = new VentanaOperacion();
    BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
/**
 * Metodo para sacar la lista de la vista de las operaciones filtrando por el DNI
 * @param nif
 * @return 
 */
    public DefaultTableModel getTablaTitular(String nif) {
        DefaultTableModel tablemodel = new DefaultTableModel();
        int registros = 0;
        String[] columNames = {"DNI", "nCuenta", "Saldo"};
        try {
            // 
            PreparedStatement st = this.getConexion().prepareStatement("SELECT count(*) as total FROM us_cuentas");
            ResultSet res = st.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        Object[][] data = new String[registros][3];
        try {
            PreparedStatement st = this.getConexion().prepareStatement("SELECT U.DNI,U.nCuenta, C.Saldo FROM us_cuentas U, Cuentas C WHERE C.nCuenta=U.nCuenta AND DNI LIKE '%" + nif + "%'");
            ResultSet resultado = st.executeQuery();
            int i = 0;
            while (resultado.next()) {
                data[i][0] = resultado.getString("DNI");
                data[i][1] = resultado.getString("nCuenta");
                data[i][2] = resultado.getString("Saldo");
                i++;
            }
            resultado.close();
            tablemodel.setDataVector(data, columNames);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return tablemodel;
    }
    /**
     * Metodo para sacar la lista de la vista de las operaciones despues de una operacion
     * @return 
     */
    public DefaultTableModel getTablaTitular1() {
        DefaultTableModel tablemodel = new DefaultTableModel();
        int registros = 0;
        String[] columNames = {"DNI", "nCuenta", "Saldo"};
        try {
            // 
            PreparedStatement st = this.getConexion().prepareStatement("SELECT count(*) as total FROM us_cuentas");
            ResultSet res = st.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        Object[][] data = new String[registros][3];
        try {
            PreparedStatement st = this.getConexion().prepareStatement("SELECT U.DNI,U.nCuenta, C.Saldo FROM us_cuentas U, Cuentas C WHERE C.nCuenta=U.nCuenta");
            ResultSet resultado = st.executeQuery();
            int i = 0;
            while (resultado.next()) {
                data[i][0] = resultado.getString("DNI");
                data[i][1] = resultado.getString("nCuenta");
                data[i][2] = resultado.getString("Saldo");
                i++;
            }
            resultado.close();
            tablemodel.setDataVector(data, columNames);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return tablemodel;
    }
    
/**
 * Metodo para insertar una operacion en la base de datos
 * @param o
 * @return 
 */
    public boolean NuevaOperacion(Operacion o) {
        String q = "INSERT INTO Operacione VALUES('" + o.getCodigo() + "','" + o.getTipo_operacion() + "','" + o.getFecha_realizacion() + "','" + o.getCantidad() + "','" + o.getUsuario() + "','" + o.getCuenta() + "','" + o.getObjetivo() + "')";
        try {
            PreparedStatement cs = this.getConexion().prepareStatement(q);
            cs.execute();
            cs.close();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }
/**
 * Metodo para realizar un ingreso (update del campo sueldo de una sola cuenta)
 * @param o
 * @return
 * @throws SQLException 
 */
    public boolean Ingreso(Operacion o) throws SQLException {
        String q = "UPDATE Cuentas SET Saldo=Saldo+" + o.getCantidad() + " WHERE nCuenta ='" + o.getCuenta() + "'";
        try {
            PreparedStatement cs = this.getConexion().prepareStatement(q);
            cs.executeUpdate();
            cs.close();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }catch(NumberFormatException nm){
            System.err.println(nm.getMessage());
        }
        return false;
    }
/**
 * Metodo para realizar una retirada (update del campo sueldo de una sola cuenta)
 * @param o
 * @return 
 */
    public boolean Retirada(Operacion o) {
        String q = "UPDATE Cuentas SET Saldo=Saldo-" + o.getCantidad() + " WHERE nCuenta ='" + o.getCuenta() + "'";
        try {
            PreparedStatement cs = this.getConexion().prepareStatement(q);
            cs.executeUpdate();
            cs.close();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }
/**
 * Metodo para realizar una transaccion (update del campo sueldo de 2 cuentas)
 * @param o
 * @return 
 */
    public boolean Transaccion(Operacion o) {
        String q = "UPDATE Cuentas SET Saldo=Saldo+" + o.getCantidad() + " WHERE nCuenta ='" + o.getObjetivo() + "'";
        String w = "UPDATE Cuentas SET Saldo=Saldo-" + o.getCantidad() + " WHERE nCuenta ='" + o.getCuenta() + "'";
        try {
            PreparedStatement cs = this.getConexion().prepareStatement(q);
            cs.executeUpdate();
            cs.close();
            PreparedStatement ct = this.getConexion().prepareStatement(w);
            ct.executeUpdate();
            ct.close();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }
/**
 * Metodo que controla que solo se metan numeros
 * @param a
 * @return
 * @throws IOException 
 */
    public String soloNumeros(String a) throws IOException {

        int cont = 0;
        do {
            if (a.matches("^([0-9]+){1,2}$") == false) {
                JOptionPane.showMessageDialog(this.ope, "Introduzca solo números");
            } else {
                cont = 1;
            }
        } while (cont == 0);
        return a;
    }
}
