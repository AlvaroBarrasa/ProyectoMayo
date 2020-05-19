package modelo;
import java.sql.*;
import java.time.LocalDate;
import javax.swing.table.DefaultTableModel;
/**
 * 
 * @author Alvaro
 */
public class Cuenta extends Conexion{

    private String nCuenta;
    private float saldo;
    private LocalDate fecha_creacion;
/**
 * Constructor vacio
 */
    public Cuenta (){}
/**
 * Getters y setters
 * @return 
 */
    public String getnCuenta() {
        return nCuenta;
    }

    public void setnCuenta(String nCuenta) {
        this.nCuenta = nCuenta;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public LocalDate getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(LocalDate fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

   

}
