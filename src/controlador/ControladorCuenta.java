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
import vista.VentanaPrincipal;
import vista.VentanaTitular;

/**
 *
 * @author Alvaro
 */
public class ControladorCuenta implements ActionListener, MouseListener {

    DefaultTableModel m;
    Statement sent;
    VentanaCuenta vistaCuenta;

    public ControladorCuenta(VentanaCuenta vistaCuenta){
        this.vistaCuenta=vistaCuenta;
    }
    
    public enum AccionMVC {
        addCuenta,
        retroceder,
        addTitular
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (ControladorCuenta.AccionMVC.valueOf(e.getActionCommand())) {
            case addCuenta:

                break;
            case addTitular:
                new ControladorTitular(new vista.VentanaTitular()).iniciar();
                break;
            case retroceder:
                this.vistaCuenta.dispose();
                new ControladorPrincipal(new VentanaPrincipal()).iniciar();
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
            SwingUtilities.updateComponentTreeUI(vistaCuenta);
            vistaCuenta.setVisible(true);
        } catch (UnsupportedLookAndFeelException ex) {
        } catch (ClassNotFoundException ex) {
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        }

        this.vistaCuenta.addCuenta.setActionCommand("addCuenta");
        this.vistaCuenta.addCuenta.addActionListener(this);

        this.vistaCuenta.retroceder.setActionCommand("retroceder");
        this.vistaCuenta.retroceder.addActionListener(this);
    }
}
