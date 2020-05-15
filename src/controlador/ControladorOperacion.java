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
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import vista.VentanaOperacion;

/**
 *
 * @author Alvaro
 */
public class ControladorOperacion implements ActionListener, MouseListener{
    
    DefaultTableModel m;
    Statement sent;
    VentanaOperacion vista;
    
    public enum AccionMVC {
        Ingreso,
        Retirada,
        Transaccion,
        Retroceder,
        Buscar
    }
    
    
    public ControladorOperacion(VentanaOperacion vista){
        this.vista=vista;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(ControladorOperacion.AccionMVC.valueOf(e.getActionCommand())){
            case Ingreso:
                
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

        this.vista.Ingreso.setActionCommand("Ingreso");
        this.vista.Ingreso.addActionListener(this);

        this.vista.retroceder.setActionCommand("retroceder");
        this.vista.retroceder.addActionListener(this);
        
        this.vista.Retirada.setActionCommand("Retirada");
        this.vista.Retirada.addActionListener(this);
        
        this.vista.Transaccion.setActionCommand("Transaccion");
        this.vista.Transaccion.addActionListener(this);
        
        this.vista.Buscar.setActionCommand("Buscar");
        this.vista.Buscar.addActionListener(this);
        
        this.vista.tabla.addMouseListener(this);
        this.vista.tabla.setModel(m);
    }
}
