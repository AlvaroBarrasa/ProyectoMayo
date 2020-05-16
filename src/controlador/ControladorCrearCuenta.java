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
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import modelo.Cuenta;
import modelo.Modelo_cuenta;
import vista.VentanaCrearCuenta;
import vista.VentanaCuenta;
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
        confirmar,
        Retroceder
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
        
        this.vista.retroceder.setActionCommand("Retroceder");
        this.vista.retroceder.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (ControladorCrearCuenta.AccionMVC.valueOf(e.getActionCommand())) {
            case confirmar:
                if (this.vista.cuenta.getText().length()==10) {
                    cuenta.setnCuenta(this.vista.sucursal.getText()+this.vista.cuenta.getText());
                    cuenta.setFecha_creacion(LocalDate.now());
                    cuenta.setSaldo(Float.parseFloat(this.vista.saldo.getText()));
                    if (cu.NuevaCuenta(cuenta.getnCuenta(),cuenta.getFecha_creacion(),cuenta.getSaldo())) {
                        this.vista.dispose();
                        new ControladorTitular(new VentanaTitular()).iniciar();
                    }
                }else{
                    JOptionPane.showMessageDialog(vista, "Número de cuenta no válido");
                }
                break;
            case Retroceder:
                this.vista.dispose();
                new ControladorCuenta(new VentanaCuenta()).iniciar();
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
}
