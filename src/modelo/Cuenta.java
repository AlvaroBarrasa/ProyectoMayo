package modelo;
import java.sql.*;
import java.time.LocalDate;
import javax.swing.table.DefaultTableModel;
/**
 * @web http://www.jc-mouse.net
 * @author Mouse
 */
public class Cuenta extends Conexion{

    private int nCuenta;
    private float saldo;
    private LocalDate fecha_creacion;
    /** Constructor de clase */
    public Cuenta (){}

    public int getnCuenta() {
        return nCuenta;
    }

    public void setnCuenta(int nCuenta) {
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
