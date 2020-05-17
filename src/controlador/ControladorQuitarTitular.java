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
import modelo.Modelo_cuenta;
import modelo.Modelo_usuario;
import vista.VentanaCuenta;
import vista.VentanaQuitarTitular;

/**
 *
 * @author Alvaro
 */
public class ControladorQuitarTitular implements MouseListener, ActionListener {

    DefaultTableModel m;
    Statement sent;
    VentanaQuitarTitular vistaTitular;
    Modelo_usuario usu = new Modelo_usuario();
    Modelo_cuenta cu = new Modelo_cuenta();

    public ControladorQuitarTitular(VentanaQuitarTitular vistaTitular){
        this.vistaTitular=vistaTitular;
    }
    
    public enum AccionMVC {
        quitar,
        Retroceder
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton()==1) {
            int fila = this.vistaTitular.tabla.rowAtPoint(e.getPoint());
            if (fila > -1) {
                this.vistaTitular.NIF.setText(String.valueOf(this.vistaTitular.tabla.getValueAt(fila, 0)));
                this.vistaTitular.sucursal.setText(String.valueOf(this.vistaTitular.tabla.getValueAt(fila, 3)));
            }
        }
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
        switch (ControladorQuitarTitular.AccionMVC.valueOf(e.getActionCommand())) {
            case quitar:
                if (this.vistaTitular.tabla.getModel().getRowCount()>1) {
                    if (this.cu.eliminarTitular(this.vistaTitular.NIF.getText(),this.vistaTitular.sucursal.getText())){
                        JOptionPane.showMessageDialog(vistaTitular, "Se ha quitado al usuario de la cuenta");
                    }else{
                        JOptionPane.showMessageDialog(vistaTitular, "Número de cuenta no existente");
                    }
                }else{
                    JOptionPane.showMessageDialog(vistaTitular, "La cuenta no se puede quedar sin titular");
                }
                this.vistaTitular.tabla.setModel(this.usu.getTablaTitular1());
                break;
            case Retroceder:
                this.vistaTitular.dispose();
                new ControladorCuenta(new VentanaCuenta()).iniciar();
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
        
        this.vistaTitular.quitar.setActionCommand("quitar");
        this.vistaTitular.quitar.addActionListener(this);
        
        this.vistaTitular.tabla.addMouseListener(this);
        this.vistaTitular.tabla.setModel(this.usu.getTablaTitular(this.vistaTitular.BuscaCuenta.getText()));
        
        this.vistaTitular.retroceder.setActionCommand("Retroceder");
        this.vistaTitular.retroceder.addActionListener(this);
    }

}
