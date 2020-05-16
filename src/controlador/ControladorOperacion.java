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
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import modelo.Modelo_Operacion;
import modelo.Modelo_usuario;
import modelo.Operacion;
import vista.VentanaOperacion;
import vista.VentanaPrincipal;

/**
 *
 * @author Alvaro
 */
public class ControladorOperacion implements ActionListener, MouseListener {

    DefaultTableModel m;
    Statement sent;
    VentanaOperacion vista;
    Modelo_Operacion op = new Modelo_Operacion();
    Operacion o = new Operacion();

    LocalDateTime k = LocalDateTime.now();
    int hora = k.getHour();
    int minutos = k.getMinute();
    int segundos = k.getSecond();

    public enum AccionMVC {
        Ingreso,
        Retirada,
        Transaccion,
        Retroceder,
        Buscar,
        Realizar
    }

    public ControladorOperacion(VentanaOperacion vista) {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (ControladorOperacion.AccionMVC.valueOf(e.getActionCommand())) {
            case Ingreso:
                this.vista.Cuenta_objetivo.setEnabled(false);
                break;
            case Retirada:
                this.vista.Cuenta_objetivo.setEnabled(false);
                break;
            case Transaccion:
                this.vista.Cuenta_objetivo.setEnabled(true);
                break;
            case Buscar:
                this.vista.tabla.setModel(this.op.getTablaTitular(this.vista.NIF.getText()));
                break;
            case Realizar:
                if (this.vista.Ingreso.isSelected()) {
                    if (Float.parseFloat(this.vista.Cantidad.getText())>0) {
                        try {
                            o.setCodigo("IN" + String.valueOf(hora) + String.valueOf(minutos) + String.valueOf(segundos));
                            o.setTipo_operacion("Ingreso");
                            o.setFecha_realizacion(LocalDate.now());
                            o.setCantidad(Float.parseFloat(op.soloNumeros(this.vista.Cantidad.getText())));
                            o.setUsuario(this.vista.NIF.getText());
                            o.setCuenta(this.vista.Cuenta_prop.getText());
                            o.setObjetivo(this.vista.Cuenta_prop.getText());
                            if (op.Ingreso(o.getCuenta(),o.getCantidad()) && op.NuevaOperacion(o)) {
                                JOptionPane.showMessageDialog(vista, "Ingreso realizado correctamente");
                            } else {
                                JOptionPane.showMessageDialog(vista, "Datos incorrectos");
                            }
                        } catch (NumberFormatException im) {
                            JOptionPane.showMessageDialog(vista, "Ponga números en la cantidad");
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(vista, "Error al introducir los datos");

                        } catch (SQLException ex) {
                           JOptionPane.showMessageDialog(vista, "Datos repetidos");
                        }catch(NullPointerException nu){
                            JOptionPane.showMessageDialog(vista, "Rellene todos los campos posibles");
                        }
                    }
                }
                else if (this.vista.Retirada.isSelected()) {
                    if (Float.parseFloat(this.vista.Cantidad.getText())>0) {
                        try {
                            o.setCodigo("RE" + String.valueOf(hora) + String.valueOf(minutos) + String.valueOf(segundos));
                            o.setTipo_operacion("Retirada");
                            o.setFecha_realizacion(LocalDate.now());
                            o.setCantidad(Float.parseFloat(op.soloNumeros(this.vista.Cantidad.getText())));
                            o.setUsuario(this.vista.NIF.getText());
                            o.setCuenta(this.vista.Cuenta_prop.getText());
                            o.setObjetivo(this.vista.Cuenta_prop.getText());
                            if (op.Retirada(o) && op.NuevaOperacion(o)) {
                                JOptionPane.showMessageDialog(vista, "Retirada realizada correctamente");
                            } else {
                                JOptionPane.showMessageDialog(vista, "Datos incorrectos");
                            }
                        } catch (NumberFormatException im) {
                            JOptionPane.showMessageDialog(vista, "Ponga números en la cantidad");
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(vista, "Error al introducir los datos");
                        }

                    }
                }
                else if (this.vista.Transaccion.isSelected()) {
                    if (this.vista.Cuenta_objetivo.getText().length() == 23 && Float.parseFloat(this.vista.Cantidad.getText())>0) {
                        try {
                            o.setCodigo("TR" + String.valueOf(hora) + String.valueOf(minutos) + String.valueOf(segundos));
                            o.setTipo_operacion("Transaccion");
                            o.setFecha_realizacion(LocalDate.now());
                            o.setCantidad(Float.parseFloat(op.soloNumeros(this.vista.Cantidad.getText())));
                            o.setUsuario(this.vista.NIF.getText());
                            o.setCuenta(this.vista.Cuenta_prop.getText());
                            o.setObjetivo(this.vista.Cuenta_objetivo.getText());
                            if (op.Transaccion(o) && op.NuevaOperacion(o)) {
                                JOptionPane.showMessageDialog(vista, "Transacción realizada correctamente");
                            } else {
                                JOptionPane.showMessageDialog(vista, "Datos incorrectos");
                            }
                        } catch (NumberFormatException im) {
                            JOptionPane.showMessageDialog(vista, "Ponga números en la cantidad");
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(vista, "Error al introducir los datos");
                        }

                    }
                }
                else{
                    JOptionPane.showMessageDialog(vista, "Seleccione una opción");
                }
                break;
            case Retroceder:
                this.vista.dispose();
                new ControladorPrincipal(new VentanaPrincipal()).iniciar();
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 1) {
            int fila = this.vista.tabla.rowAtPoint(e.getPoint());
            if (fila > -1) {
                this.vista.Cuenta_prop.setText(String.valueOf(this.vista.tabla.getValueAt(fila, 1)));
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
        
        this.vista.Realizar.setActionCommand("Realizar");
        this.vista.Realizar.addActionListener(this);

        this.vista.tabla.addMouseListener(this);
        this.vista.tabla.setModel(this.op.getTablaTitular(this.vista.NIF.getText()));
        
        this.vista.retroceder.setActionCommand("Retroceder");
        this.vista.retroceder.addActionListener(this);
    }
}
