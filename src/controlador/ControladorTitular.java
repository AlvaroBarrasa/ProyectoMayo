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
import vista.VentanaTitular;

/**
 *
 * @author Alvaro
 */
public class ControladorTitular implements MouseListener, ActionListener {

    DefaultTableModel m;
    Statement sent;
    VentanaTitular vistaTitular;
    Modelo_usuario usu = new Modelo_usuario();
    Modelo_cuenta cu = new Modelo_cuenta();

    public ControladorTitular(VentanaTitular vistaTitular){
        this.vistaTitular=vistaTitular;
    }
    
    public enum AccionMVC {
        add,
        quitar,
        Busca
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton()==1) {
            int fila = this.vistaTitular.tabla.rowAtPoint(e.getPoint());
            if (fila > -1) {
                this.vistaTitular.NIF.setText(String.valueOf(this.vistaTitular.tabla.getValueAt(fila, 0)));
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
        switch (ControladorTitular.AccionMVC.valueOf(e.getActionCommand())) {
            case add:
                if (this.vistaTitular.cuenta.getText().length()==10 && this.vistaTitular.NIF.getText().length()==9) {
                    if (this.cu.aniadirTitular(this.vistaTitular.NIF.getText(), this.vistaTitular.cuenta.getText())) {
                        JOptionPane.showMessageDialog(vistaTitular, "Se ha agregado un titular a la cuenta con éxito");
                    }else{
                        JOptionPane.showMessageDialog(vistaTitular, "Datos mal introducidos");
                    }
                }else{
                    JOptionPane.showMessageDialog(vistaTitular, "Longitud de datos incorrecta");
                }
                break;
            case quitar:
                if (this.vistaTitular.cuenta.getText().length()==10 && this.vistaTitular.NIF.getText().length()==9){
                    if (this.cu.eliminarTitular(this.vistaTitular.NIF.getText(), this.vistaTitular.sucursal.getText()+this.vistaTitular.cuenta.getText())){
                        JOptionPane.showMessageDialog(vistaTitular, "Se ha quitado al usuario de la cuenta");
                    }else{
                        JOptionPane.showMessageDialog(vistaTitular, "Número de cuenta no existente");
                    }
                }else{
                    JOptionPane.showMessageDialog(vistaTitular, "Número de cuenta mal introducido");
                }
                break;
                
            case Busca:
                this.vistaTitular.tabla.setModel(this.usu.getTablaNombre(this.vistaTitular.Apellidos.getText()));
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
        
        this.vistaTitular.btnApellidos.setActionCommand("Busca");
        this.vistaTitular.btnApellidos.addActionListener(this);
        
        this.vistaTitular.tabla.addMouseListener(this);
        this.vistaTitular.tabla.setModel(this.usu.getTablaNombre(this.vistaTitular.Apellidos.getText()));
    }

}
