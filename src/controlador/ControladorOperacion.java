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
/**
 * Importo las clases para utilizar los métodos
 */
    VentanaOperacion vista;
    Modelo_Operacion op = new Modelo_Operacion();
    Operacion o = new Operacion();
/**
 * Declaro las variables del tiempo como int para utilizarlas en el codigo
 */
    LocalDateTime k = LocalDateTime.now();
    int hora = k.getHour();
    int minutos = k.getMinute();
    int segundos = k.getSecond();
    int anio = k.getYear();
    int mes = k.getMonthValue();
    int dia = k.getDayOfMonth();
/**
 * Pongo en el enum los botones que tendrá la vista
 */
    public enum AccionMVC {
        Ingreso,
        Retirada,
        Transaccion,
        Retroceder,
        Realizar
    }
/**
 * Constructor para abrir la ventana desde otras vistas
 * @param vista 
 */
    public ControladorOperacion(VentanaOperacion vista) {
        this.vista = vista;
    }
/**
 * Switch case para que haga una acción depende de los botones que se seleccionen 
 * @param e 
 */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (ControladorOperacion.AccionMVC.valueOf(e.getActionCommand())) {
            //Si se selecciona esta opcion se deshabilita el campo de la cuenta destino
            case Ingreso:
                this.vista.Cuenta_objetivo.setEnabled(false);
                break;
                //Si se selecciona esta opcion se deshabilita el campo de la cuenta destino
            case Retirada:
                this.vista.Cuenta_objetivo.setEnabled(false);
                break;
                //Si el campo estuviera desactivado y se marca esta opcion, se habilita de nuevo
            case Transaccion:
                this.vista.Cuenta_objetivo.setEnabled(true);
                break;
                //Depende del boton que seleccione hace una accion
            case Realizar:
                //Verifica el boton seleccionado
                if (this.vista.Ingreso.isSelected()) {
                    //Verifica que la cantidad sea mayor que 0 para que no haya trucos
                    if (Double.parseDouble(this.vista.Cantidad.getText())>0) {
                        try {
                            //Se dan los valores al objeto operacion
                            o.setCodigo("IN" +String.valueOf(anio)+String.valueOf(mes)+String.valueOf(dia)+ String.valueOf(hora) + String.valueOf(minutos) + String.valueOf(segundos));
                            o.setTipo_operacion("Ingreso");
                            o.setFecha_realizacion(LocalDate.now());
                            o.setCantidad(Double.parseDouble(this.vista.Cantidad.getText()));
                            o.setUsuario(this.vista.nif_elegido.getText());
                            o.setCuenta(this.vista.Cuenta_prop.getText());
                            o.setObjetivo(this.vista.Cuenta_prop.getText());
                            //Verifica que se pueda hacer el ingreso y que se pueda crear un nuevo registro en la base de datos de esta operacion
                            if (op.Ingreso(o) && op.NuevaOperacion(o)) {
                                JOptionPane.showMessageDialog(vista, "Ingreso realizado correctamente");
                                this.vista.tabla.setModel(this.op.getTablaTitular1());
                            } else {
                                JOptionPane.showMessageDialog(vista, "Datos incorrectos");
                            }
                        } catch (NumberFormatException im) {
                            JOptionPane.showMessageDialog(vista, "Ponga números en la cantidad");
                        }catch (SQLException ex) {
                            System.err.println(ex.getMessage());
                        }catch(NullPointerException nu){
                            JOptionPane.showMessageDialog(vista, "Rellene todos los campos posibles");
                        }
                    }
                }
                //Verifica el boton seleccionado
                else if (this.vista.Retirada.isSelected()) {
                    //Verifica que la cantidad sea mayor que 0 para que no haya trucos
                    if (Double.parseDouble(this.vista.Cantidad.getText())>0) {
                        try {
                            //Se dan los valores al objeto operacion
                            o.setCodigo("RE" +String.valueOf(anio)+String.valueOf(mes)+String.valueOf(dia)+ String.valueOf(hora) + String.valueOf(minutos) + String.valueOf(segundos));
                            o.setTipo_operacion("Retirada");
                            o.setFecha_realizacion(LocalDate.now());
                            o.setCantidad(Float.parseFloat(op.soloNumeros(this.vista.Cantidad.getText())));
                            o.setUsuario(this.vista.nif_elegido.getText());
                            o.setCuenta(this.vista.Cuenta_prop.getText());
                            o.setObjetivo(this.vista.Cuenta_prop.getText());
                            //Verifica que la cuenta no se puede quedar sin dinero
                            if (Double.parseDouble(this.vista.comp.getText())-o.getCantidad()>=0) {
                                //Verifica que se pueda hacer la retirada y que se pueda crear un nuevo registro en la base de datos de esta operacion
                                if (op.Retirada(o) && op.NuevaOperacion(o)) {
                                    JOptionPane.showMessageDialog(vista, "Retirada realizada correctamente");
                                    this.vista.tabla.setModel(this.op.getTablaTitular1());
                                }else{
                                    JOptionPane.showMessageDialog(vista, "Datos incorrectos");
                                }
                            } else {
                                JOptionPane.showMessageDialog(vista, "No puede retirar una cantidad superior a su saldo");
                            }
                        } catch (NumberFormatException im) {
                            JOptionPane.showMessageDialog(vista, "Ponga números en la cantidad");
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(vista, "Error al introducir los datos");
                        }
                    }
                }
                //Verifica el boton seleccionado
                else if (this.vista.Transaccion.isSelected()) {
                    //Verifica que la cantidad sea mayor que 0 y que la longitud sea exactamente 10 para rellenar el campo del numero de cuenta
                    if (this.vista.Cuenta_objetivo.getText().length() == 10 && Double.parseDouble(this.vista.Cantidad.getText())>0) {
                        try {
                            o.setCodigo("TR" +String.valueOf(anio)+String.valueOf(mes)+String.valueOf(dia)+ String.valueOf(hora) + String.valueOf(minutos) + String.valueOf(segundos));
                            o.setTipo_operacion("Transaccion");
                            o.setFecha_realizacion(LocalDate.now());
                            o.setCantidad(Float.parseFloat(op.soloNumeros(this.vista.Cantidad.getText())));
                            o.setUsuario(this.vista.nif_elegido.getText());
                            o.setCuenta(this.vista.Cuenta_prop.getText());
                            o.setObjetivo(this.vista.sucursal.getText()+this.vista.Cuenta_objetivo.getText());
                            //Verifica que la cuenta que realiza la operacion y la destinataria no son la misma
                            if (!o.getCuenta().equals(o.getObjetivo())) {
                                //Verifica que la cuenta no se puede quedar sin dinero
                                if (Double.parseDouble(this.vista.comp.getText())-o.getCantidad()>=0) {
                                    //Verifica que se haga la transaccion y se pueda crear un nuevo registro en la base de datos de esta operacion
                                if (op.Transaccion(o) && op.NuevaOperacion(o)) {
                                    JOptionPane.showMessageDialog(vista, "Transacción realizada correctamente");
                                    this.vista.tabla.setModel(this.op.getTablaTitular1());
                                }else{
                                    JOptionPane.showMessageDialog(vista, "Datos incorrectos");
                                }
                            } else {
                                JOptionPane.showMessageDialog(vista, "No puede retirar una cantidad superior a su saldo");
                            }
                            }else{
                                JOptionPane.showMessageDialog(vista, "La cuenta destinataria no puede ser la cuenta realizante");
                            }
                        } catch (NumberFormatException im) {
                            JOptionPane.showMessageDialog(vista, "Ponga números en la cantidad");
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(vista, "Error al introducir los datos");
                        }catch(InputMismatchException in){
                            JOptionPane.showMessageDialog(vista, "Ponga datos válidas");
                        }
                    }
                }
                else{
                    JOptionPane.showMessageDialog(vista, "Seleccione una opción");
                }
                break;
                //Retrocede a la ventana anterior
            case Retroceder:
                this.vista.dispose();
                new ControladorPrincipal(new VentanaPrincipal()).iniciar();
                break;
        }
    }
/**
 * Metodo para dar valores a los campos haciendo click en la tabla
 * @param e 
 */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 1) {
            int fila = this.vista.tabla.rowAtPoint(e.getPoint());
            if (fila > -1) {
                this.vista.nif_elegido.setText(String.valueOf(this.vista.tabla.getValueAt(fila, 0)));
                this.vista.Cuenta_prop.setText(String.valueOf(this.vista.tabla.getValueAt(fila, 1)));
                this.vista.comp.setText(String.valueOf(this.vista.tabla.getValueAt(fila, 2)));
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
/**
 * Metodo para iniciar la vista y las acciones de los botones
 */
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

        this.vista.nif_elegido.setVisible(false);
        this.vista.comp.setVisible(false);
        
        this.vista.Ingreso.setActionCommand("Ingreso");
        this.vista.Ingreso.addActionListener(this);

        this.vista.Retirada.setActionCommand("Retirada");
        this.vista.Retirada.addActionListener(this);

        this.vista.Transaccion.setActionCommand("Transaccion");
        this.vista.Transaccion.addActionListener(this);
        
        this.vista.Realizar.setActionCommand("Realizar");
        this.vista.Realizar.addActionListener(this);

        this.vista.tabla.addMouseListener(this);
        this.vista.tabla.setModel(this.op.getTablaTitular(this.vista.NIF.getText()));
        
        this.vista.retroceder.setActionCommand("Retroceder");
        this.vista.retroceder.addActionListener(this);
    }
}
