package modelo;
import java.sql.*;
import java.time.LocalDateTime;
import javax.swing.table.DefaultTableModel;
/**
 * @web http://www.jc-mouse.net
 * @author Mouse
 */
public class Operacion extends Conexion{
    
    private String codigo;
    private Tipo tipo_operacion;
    private LocalDateTime fecha_realizacion;
    private Cuenta cuenta;
    private Usuario usuario;
    /** Constructor de clase */
    public Operacion (){}

   

}
