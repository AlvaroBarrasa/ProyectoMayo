package modelo;
import java.sql.*;
import java.time.LocalDateTime;
import javax.swing.table.DefaultTableModel;
/**
 * @author Alvaro
 */
public class Operacion extends Conexion{
    
    private String codigo,tipo_operacion;
    private LocalDateTime fecha_realizacion;
    private Cuenta cuenta;
    private Usuario usuario;
    private float cantidad;
    /** Constructor de clase */
    public Operacion (){}

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTipo_operacion() {
        return tipo_operacion;
    }

    public void setTipo_operacion(String tipo_operacion) {
        this.tipo_operacion = tipo_operacion;
    }

    public LocalDateTime getFecha_realizacion() {
        return fecha_realizacion;
    }

    public void setFecha_realizacion(LocalDateTime fecha_realizacion) {
        this.fecha_realizacion = fecha_realizacion;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

   

}
