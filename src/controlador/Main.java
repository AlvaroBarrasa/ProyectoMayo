package controlador;
import vista.VentanaPrincipal;
/**
 * @author Alvaro
 */
public class Main {
    
    public static void main(String[] args) {
        new ControladorPrincipal(new VentanaPrincipal()).iniciar();
    }

}
