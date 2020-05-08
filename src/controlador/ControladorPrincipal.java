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
import vista.VentanaPrincipal;
import vista.VentanaUsuario;

/**
 *
 * @author Alvaro
 */
public class ControladorPrincipal implements ActionListener,MouseListener{
    DefaultTableModel m;
    Statement sent;
    VentanaPrincipal vista;

    @Override
    public void actionPerformed(ActionEvent e) {
     
        switch ( AccionMVC.valueOf( e.getActionCommand() ) )
        {
            case gestUsuario:
                this.vista.dispose();
                new ControladorUsuario(new VentanaUsuario()).iniciar();
                break;
            case gestCuenta:
                
                break;
            case gestOperacion:
                
               
                break;       
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public enum AccionMVC
    {
        gestUsuario,
        gestCuenta,
        gestOperacion
    }
    
    public ControladorPrincipal(VentanaPrincipal vista){
        this.vista=vista;
    }
    
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
