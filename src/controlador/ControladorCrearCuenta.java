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
import java.time.LocalDate;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import modelo.Cuenta;
import modelo.Modelo_cuenta;
import vista.VentanaCrearCuenta;
import vista.VentanaTitular;


/**
 *
 * @author Alvaro
 */
public class ControladorCrearCuenta implements ActionListener, MouseListener{

    DefaultTableModel m;
    Statement sent;
    VentanaCrearCuenta vista;
    Cuenta cuenta= new Cuenta();
    Modelo_cuenta cu = new Modelo_cuenta();
    VentanaTitular vistatitular;
    
    public ControladorCrearCuenta(VentanaCrearCuenta vista){
        this.vista=vista;
    }
    
    public enum AccionMVC {
        confirmar
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (ControladorCrearCuenta.AccionMVC.valueOf(e.getActionCommand())) {
            case confirmar:
                if (this.vista.nCuenta.getText().length()!=10) {
                    cuenta.setnCuenta(this.vista.sucursal.getText()+this.vista.nCuenta.getText());
                    cuenta.setFecha_creacion(LocalDate.now());
                    cuenta.setSaldo(Float.parseFloat(this.vista.saldo.getText()));
                    if (cu.NuevaCuenta(cuenta)) {
                        this.vista.dispose();
                        new ControladorTitular(new VentanaTitular()).iniciar();
                    }
                }
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
        
        this.vista.confirmar.setActionCommand( "confirmar" );
        this.vista.confirmar.addActionListener(this);
        
    }
}
