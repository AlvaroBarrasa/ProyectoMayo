/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.mysql.jdbc.exceptions.MySQLDataException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import modelo.Cuenta;
import modelo.Modelo_cuenta;
import modelo.Modelo_usuario;
import vista.VentanaAñadirTitular;
import vista.VentanaCuenta;

/**
 *
 * @author Alvaro
 */
public class ControladorAddTitular implements MouseListener, ActionListener{
    DefaultTableModel m;
    VentanaAñadirTitular vista;
    Modelo_cuenta cu = new Modelo_cuenta();
    Cuenta c= new Cuenta();
    Modelo_usuario usu = new Modelo_usuario();
    
    public enum AccionMVC {
        Retroceder,
        add
    }
    
    public void iniciar() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(vista);
            vista.setVisible(true);
        } catch (UnsupportedLookAndFeelException ex) {
        } catch (ClassNotFoundException ex) {
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        }
        this.vista.cuenta_elegida.setVisible(false);
        this.vista.nif_elegido.setVisible(false);
        
        this.vista.add.setActionCommand("add");
        this.vista.add.addActionListener(this);

        this.vista.retroceder.setActionCommand("retroceder");
        this.vista.retroceder.addActionListener(this);

        this.vista.tablaCuentas.addMouseListener(this);
        this.vista.tablaCuentas.setModel(this.cu.getTablaInfo(this.vista.cuenta.getText()));
        
        this.vista.tablaUsuario.addMouseListener(this);
        this.vista.tablaUsuario.setModel(this.usu.getTablaNombre(this.vista.nif.getText()));
        
    }
    
    public ControladorAddTitular(VentanaAñadirTitular vista){
        this.vista=vista;
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

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (ControladorAddTitular.AccionMVC.valueOf(e.getActionCommand())) {
            case add:
            try {
                if (this.cu.verifica_add(this.vista.nif_elegido.getText(), this.vista.sucursal.getText()+this.cu.soloNumeros(this.vista.cuenta.getText()))) {
                    if (this.cu.aniadirTitular(this.vista.nif_elegido.getText(), this.vista.sucursal.getText()+this.cu.soloNumeros(this.vista.cuenta.getText()))) {
                        JOptionPane.showMessageDialog(vista, "Titular añadido correctamente");
                    }
                }else{
                    JOptionPane.showMessageDialog(vista, "Esta relación ya existe");
                }
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }   
                break;
            case Retroceder:
                this.vista.dispose();
                new ControladorCuenta(new VentanaCuenta()).iniciar();
                break;
        }
    }
    
}
