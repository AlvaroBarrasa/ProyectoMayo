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

    public DefaultTableModel getTablaTitular(String nif) {
        DefaultTableModel tablemodel = new DefaultTableModel();
        int registros = 0;
        String[] columNames = {"NIF", "nCuenta", "Saldo"};
        try {
            // 
            PreparedStatement st = this.getConexion().prepareStatement("SELECT count(*) as total FROM usu_cuenta");
            ResultSet res = st.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        Object[][] data = new String[registros][3];
        try {
            PreparedStatement st = this.getConexion().prepareStatement("SELECT U.NIF,U.nCuenta, C.Saldo FROM usu_cuenta U, Cuentas C WHERE C.nCuenta=U.nCuenta AND NIF LIKE '%" + nif + "%'");
            ResultSet resultado = st.executeQuery();
            int i = 0;
            while (resultado.next()) {
                data[i][0] = resultado.getString("NIF");
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

    public boolean NuevaOperacion(Operacion o) {
        String q = "INSERT INTO Operaciones VALUES('" + o.getCodigo() + "','" + o.getTipo_operacion() + "','" + o.getFecha_realizacion() + "','" + o.getCantidad() + "','" + o.getUsuario() + "','" + o.getCuenta() + "','" + o.getObjetivo() + "')";
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

    public boolean Ingreso(Operacion o) throws SQLException {
        String q = "UPDATE Cuentas SET Saldo=Saldo+" + o.getCantidad() + " WHERE nCuenta ='" + o.getCuenta() + "'";
        try {
            PreparedStatement cs = this.getConexion().prepareStatement(q);
            cs.executeUpdate();
            cs.close();
            return true;
        } catch (MySQLDataException e) {
            System.err.println("error");
        }
        return false;
    }

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
