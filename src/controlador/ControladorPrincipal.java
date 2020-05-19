/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import vista.VentanaCuenta;
import vista.VentanaOperacion;
import vista.VentanaPrincipal;
import vista.VentanaUsuario;

/**
 *
 * @author Alvaro
 */
public class ControladorPrincipal implements ActionListener,MouseListener{
/**
 * Importo las clases para utilizar los métodos
 */
    VentanaPrincipal vista;
/**
 * Switch case para que haga una acción depende de los botones que se seleccionen 
 * @param e 
 */
    @Override
    public void actionPerformed(ActionEvent e) {
     
        switch ( AccionMVC.valueOf( e.getActionCommand() ) )
        {
            //Abre la ventana para gestionar usuarios
            case gestUsuario:
                this.vista.dispose();
                new ControladorUsuario(new VentanaUsuario()).iniciar();
                break;
            //Abre la ventana para gestionar cuentas
            case gestCuenta:
                this.vista.dispose();
                new ControladorCuenta(new VentanaCuenta()).iniciar();
                break;
            //Abre la ventana para realizar operaciones
            case gestOperacion:
                this.vista.dispose();
                new ControladorOperacion(new VentanaOperacion()).iniciar();
                break;       
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
       
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
       
    }
    /**
     * Pongo en el enum los botones que tendrá la vista
     */
    public enum AccionMVC
    {
        gestUsuario,
        gestCuenta,
        gestOperacion
    }
    /**
     * Constructor para abrir la ventana desde otras vistas
     * @param vista 
     */
    public ControladorPrincipal(VentanaPrincipal vista){
        this.vista=vista;
    }
    /**
     * Metodo para iniciar la vista y las acciones de los botones
     */
    public void iniciar()
    {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(vista);
            vista.setVisible(true);
        } catch (UnsupportedLookAndFeelException ex) {}
          catch (ClassNotFoundException ex) {}
          catch (InstantiationException ex) {}
          catch (IllegalAccessException ex) {}
        
        this.vista.gestUsuario.setActionCommand( "gestUsuario" );
        this.vista.gestUsuario.addActionListener(this);

        this.vista.gestCuenta.setActionCommand( "gestCuenta" );
        this.vista.gestCuenta.addActionListener(this);

        this.vista.gestOperacion.setActionCommand( "gestOperacion" );
        this.vista.gestOperacion.addActionListener(this);
        
    }
}
