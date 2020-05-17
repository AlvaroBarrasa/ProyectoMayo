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
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import vista.VentanaAñadirTitular;
import vista.VentanaCrearCuenta;
import vista.VentanaCuenta;
import vista.VentanaPrincipal;
import vista.VentanaQuitarTitular;

/**
 *
 * @author Alvaro
 */
public class ControladorCuenta implements ActionListener, MouseListener {

    DefaultTableModel m;
    Statement sent;
    VentanaCuenta vistaCuenta;

    public ControladorCuenta(VentanaCuenta vistaCuenta) {
        this.vistaCuenta = vistaCuenta;
    }

    public enum AccionMVC {
        addCuenta,
        quitarTitular,
        addTitular,
        retroceder
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
        
        this.vistaCuenta.quitarTitular.setActionCommand("quitarTitular");
        this.vistaCuenta.quitarTitular.addActionListener(this);
        
        this.vistaCuenta.addTitular.setActionCommand("addTitular");
        this.vistaCuenta.addTitular.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (ControladorCuenta.AccionMVC.valueOf(e.getActionCommand())) {
            case addCuenta:
                this.vistaCuenta.dispose();
                new ControladorCrearCuenta(new VentanaCrearCuenta()).iniciar();
                break;
            case quitarTitular:
                this.vistaCuenta.dispose();
                new ControladorQuitarTitular(new VentanaQuitarTitular()).iniciar();
                break;
            case addTitular:
                this.vistaCuenta.dispose();
                new ControladorAddTitular(new VentanaAñadirTitular()).iniciar();
                break;
            case retroceder:
                this.vistaCuenta.dispose();
                new ControladorPrincipal(new VentanaPrincipal()).iniciar();
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
