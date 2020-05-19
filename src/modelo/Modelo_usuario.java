package modelo;

import com.mysql.jdbc.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.sound.midi.SysexMessage;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alvaro
 */
public class Modelo_usuario extends Conexion {
/**
 * Metodo que lista los usuarios para la lista de la tabla de gestion de usuario
 * @return 
 */
    public DefaultTableModel getTablaUsuario() {
        DefaultTableModel tablemodel = new DefaultTableModel();
        int registros = 0;
        String[] columNames = {"NIF", "Apellidos", "Nombre", "Año_nacimiento", "Dirección", "Email", "Teléfono"};
        try {
            PreparedStatement st = this.getConexion().prepareStatement("SELECT count(*) as total FROM Usuario");
            ResultSet res = st.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        Object[][] data = new String[registros][8];
        try {
            PreparedStatement st = this.getConexion().prepareStatement("SELECT * FROM Usuario");
            ResultSet resultado = st.executeQuery();
            int i = 0;
            while (resultado.next()) {
                data[i][0] = resultado.getString("NIF");
                data[i][1] = resultado.getString("Apellidos");
                data[i][2] = resultado.getString("Nombre");
                data[i][3] = resultado.getString("Año_nacimiento");
                data[i][4] = resultado.getString("Dirección");
                data[i][5] = resultado.getString("Email");
                data[i][6] = resultado.getString("Teléfono");
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
 * Metodo para listar los usuarios en la lista de la ventana para añadir usuarios
 * @param nif
 * @return 
 */
    public DefaultTableModel getTablaNombre(String nif) {
        DefaultTableModel tablemodel = new DefaultTableModel();
        int registros = 0;
        String[] columNames = {"NIF", "Nombre", "Apellidos"};
        try {
            // 
            PreparedStatement st = this.getConexion().prepareStatement("SELECT count(*) as total FROM Usuario");
            ResultSet res = st.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        Object[][] data = new String[registros][3];
        try {
            PreparedStatement st = this.getConexion().prepareStatement("SELECT NIF, Nombre, Apellidos FROM Usuario WHERE NIF LIKE '%" + nif + "%'");
            ResultSet resultado = st.executeQuery();
            int i = 0;
            while (resultado.next()) {
                data[i][0] = resultado.getString("NIF");
                data[i][1] = resultado.getString("Nombre");
                data[i][2] = resultado.getString("Apellidos");
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
 * Metodo para listar los titulares de todas las cuentas del banco en la ventana para gestionar usuarios
 * @return 
 */
    public DefaultTableModel getTablaLista() {
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

        Object[][] data = new String[registros][4];
        try {
            PreparedStatement st = this.getConexion().prepareStatement("SELECT DNI, U.nCuenta, C.Saldo FROM us_cuentas U, Cuentas C WHERE C.nCuenta=U.nCuenta");
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
     * Metodo para listar los usuarios con sus cuentas en la ventana para quitar titulares
     * @param cuenta
     * @return 
     */
    public DefaultTableModel getTablaTitular(String cuenta) {
        DefaultTableModel tablemodel = new DefaultTableModel();
        int registros = 0;
        String[] columNames = {"DNI", "Apellidos", "Nombre","nCuenta"};
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

        Object[][] data = new String[registros][4];
        try {
            PreparedStatement st = this.getConexion().prepareStatement("SELECT DNI, U.Apellidos, U.Nombre, UC.nCuenta FROM us_cuentas UC, Usuario U WHERE U.NIF=UC.DNI AND UC.nCuenta LIKE '%"+cuenta+"%'");
            ResultSet resultado = st.executeQuery();
            int i = 0;
            while (resultado.next()) {
                data[i][0] = resultado.getString("DNI");
                data[i][1] = resultado.getString("Apellidos");
                data[i][2] = resultado.getString("Nombre");
                data[i][3] = resultado.getString("nCuenta");
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
     * Metodo para actualizar la lista cuando se borra un titular
     * @return 
     */
   public DefaultTableModel getTablaTitular1() {
        DefaultTableModel tablemodel = new DefaultTableModel();
        int registros = 0;
        String[] columNames = {"NIF", "Apellidos", "Nombre","nCuenta"};
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

        Object[][] data = new String[registros][4];
        try {
            PreparedStatement st = this.getConexion().prepareStatement("SELECT NIF, U.Apellidos, U.Nombre, UC.nCuenta FROM us_cuentas UC, Usuario U WHERE U.NIF=UC.DNI");
            ResultSet resultado = st.executeQuery();
            int i = 0;
            while (resultado.next()) {
                data[i][0] = resultado.getString("NIF");
                data[i][1] = resultado.getString("Apellidos");
                data[i][2] = resultado.getString("Nombre");
                data[i][3] = resultado.getString("nCuenta");
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
 * Metodo para insertar un usuario
 * @param u
 * @return 
 */
    public boolean NuevoUsuario(Usuario u) {
        if (validar_dni(u.getDni()) && validarEmail(u.getEmail())) {
            try {
                CallableStatement cs = (CallableStatement) this.getConexion().prepareCall("{call Agregar_usuario(?,?,?,?,?,?,?)}");
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
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
            return false;
        } else {
            return false;
        }
    }
/**
 * Metodo que valida un dni
 * @param dni
 * @return 
 */
    public boolean validar_dni(String dni) {
        boolean correcto = false;
        Pattern pattern = Pattern.compile("(\\d{1,8})([TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke])");
        Matcher matcher = pattern.matcher(dni);
        if (matcher.matches()) {
            String letra = matcher.group(2);
            String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
            int index = Integer.parseInt(matcher.group(1));
            index = index % 23;
            String reference = letras.substring(index, index + 1);
            if (reference.equalsIgnoreCase(letra)) {
                correcto = true;
            } else {
                correcto = false;
            }
        } else {
            correcto = false;
        }
        return correcto;
    }
/**
 * Metodo para validar un email
 * @param email
 * @return 
 */
    public boolean validarEmail(String email) {
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher match = pattern.matcher(email);
        if (match.find()==true) {
            return true;
        }else{
            return false;
        }
    }
/**
 * Metodo que modifica los campos de un usuario
 * @param u
 * @return 
 */
    public boolean ModificaUsuario(Usuario u) {
        try {
            CallableStatement cs = (CallableStatement) this.getConexion().prepareCall("{call Modifica_usuario(?,?,?,?,?,?,?)}");
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
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }
}
