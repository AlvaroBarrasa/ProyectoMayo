package modelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class Modelo_Operacion extends Conexion {

    /**
     * @author Alvaro
     */
    public Modelo_Operacion() {
    }

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
            PreparedStatement st = this.getConexion().prepareStatement("SELECT U.nCuenta, C.Saldo FROM usu_cuenta U, Cuentas C WHERE C.nCuenta=U.nCuenta AND NIF='" + nif + "'");
            ResultSet resultado = st.executeQuery();
            int i = 0;
            while (resultado.next()) {
                data[i][0] = resultado.getString("nCuenta");
                data[i][1] = resultado.getString("Saldo");
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
        try {
            CallableStatement cs = this.getConexion().prepareCall("{call Nueva_operacion(?,?,?,?,?,?,?)}");
            cs.setString(0, o.getCodigo());
            cs.setString(1, o.getTipo_operacion());
            cs.setDate(2, Date.valueOf(o.getFecha_realizacion()));
            cs.setFloat(3, o.getCantidad());
            cs.setString(4, o.getUsuario());
            cs.setString(5, o.getCuenta());
            cs.setString(6, o.getObjetivo());
            cs.execute();
            cs.close();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    public boolean Ingreso(Operacion o) {
        try {
            CallableStatement cs = this.getConexion().prepareCall("{call Ingreso(?,?)}");
            cs.setString(0, o.getObjetivo());
            cs.setFloat(1, o.getCantidad());
            cs.execute();
            cs.close();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    public boolean Retirada(Operacion o) {
        try {
            CallableStatement cs = this.getConexion().prepareCall("{call Retirada(?,?)}");
            cs.setString(0, o.getObjetivo());
            cs.setFloat(1, o.getCantidad());
            cs.execute();
            cs.close();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    public boolean Transaccion(Operacion o) {
        try {
            CallableStatement cs = this.getConexion().prepareCall("{call Transaccion(?,?,?)}");
            cs.setString(0, o.getCuenta());
            cs.setString(1, o.getObjetivo());
            cs.setFloat(2, o.getCantidad());
            cs.execute();
            cs.close();
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
                System.out.println("Introduzca solo numeros, por favor.");
                a = teclado.readLine();
            } else {
                cont = 1;
            }
        } while (cont == 0);
        return a;
    }
}
