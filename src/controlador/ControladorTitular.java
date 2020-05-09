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
import vista.VentanaCuenta;
import vista.VentanaPrincipal;
import vista.VentanaTitular;

/**
 *
 * @author Alvaro
 */
public class ControladorTitular implements MouseListener, ActionListener {

    DefaultTableModel m;
    Statement sent;
    VentanaTitular vistaTitular;

    public ControladorTitular(VentanaTitular vistaTitular){
        this.vistaTitular=vistaTitular;
    }
    
    public enum AccionMVC {
        add,
        quitar
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

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (ControladorTitular.AccionMVC.valueOf(e.getActionCommand())) {
            case add:

                break;
            case quitar:

                break;
        }
    }

    public void iniciar() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(vistaTitular);
            vistaTitular.setVisible(true);
        } catch (UnsupportedLookAndFeelException ex) {
        } catch (ClassNotFoundException ex) {
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        }

        this.vistaTitular.add.setActionCommand("add");
        this.vistaTitular.add.addActionListener(this);

        this.vistaTitular.quitar.setActionCommand("quitar");
        this.vistaTitular.quitar.addActionListener(this);
    }

}
