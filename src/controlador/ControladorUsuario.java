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
import modelo.Modelo_usuario;
import modelo.Usuario;
import vista.VentanaPrincipal;
import vista.VentanaUsuario;

/**
 *
 * @author Alvaro
 */
public class ControladorUsuario implements ActionListener, MouseListener {

    DefaultTableModel m;
    Statement sent;
    VentanaUsuario vista;
    Modelo_usuario usu = new Modelo_usuario();
    Usuario usua = new Usuario();

    public enum AccionMVC {
        addUsuario,
        addCuentaUsu,
        listarCuentaUsuario,
        modUsuario,
        listarUsuario,
        retroceder
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (AccionMVC.valueOf(e.getActionCommand())) {
            case addUsuario:
                if (this.vista.NIF.getText().length() == 9
                        && this.vista.Apellidos.getText().length() > 0
                        && this.vista.Nombre.getText().length() > 0
                        && this.vista.Anio.getText().length() == 4
                        && this.vista.Direccion.getText().length() > 0
                        && this.vista.Email.getText().length() > 0
                        && this.vista.tlf.getText().length() == 9) {
                    usua.setDni(this.vista.NIF.getText());
                    usua.setApellidos(this.vista.Apellidos.getText());
                    usua.setNombre(this.vista.Nombre.getText());
                    usua.setAnio_nacimiento(Integer.parseInt(this.vista.Anio.getText()));
                    usua.setDireccion(this.vista.Direccion.getText());
                    usua.setEmail(this.vista.Email.getText());
                    usua.setTelefono(Integer.parseInt(this.vista.tlf.getText()));
                    if (this.usu.NuevoUsuario(usua)) {
                        this.vista.tablaUsuario.setModel(this.usu.getTablaUsuario());
                        JOptionPane.showMessageDialog(vista, "Exito: Nuevo usuario agregado");
                        this.vista.NIF.setText("");
                        this.vista.Apellidos.setText("");
                        this.vista.Nombre.setText("");
                        this.vista.Anio.setText("");
                        this.vista.Direccion.setText("");
                        this.vista.Email.setText("");
                        this.vista.tlf.setText("");
                    } else {
                        JOptionPane.showMessageDialog(vista, "Datos incorrectos");
                    }
                } else {
                    JOptionPane.showMessageDialog(vista, "Datos muy largos o cortos");
                }
                break;
            case listarCuentaUsuario:
                this.vista.tablaUsuarioCuenta.setModel(this.usu.getTablaLista());
                break;
            case listarUsuario:
                this.vista.tablaUsuario.setModel(this.usu.getTablaUsuario());
                break;
            case modUsuario:
                try {
                    usua.setDni(this.vista.NIF.getText());
                    usua.setApellidos(this.vista.Apellidos.getText());
                    usua.setNombre(this.vista.Nombre.getText());
                    usua.setAnio_nacimiento(Integer.parseInt(this.vista.Anio.getText()));
                    usua.setDireccion(this.vista.Direccion.getText());
                    usua.setEmail(this.vista.Email.getText());
                    usua.setTelefono(Integer.parseInt(this.vista.tlf.getText()));
                    if (this.usu.ModificaUsuario(usua)) {
                        this.vista.tablaUsuario.setModel(this.usu.getTablaUsuario());
                        JOptionPane.showMessageDialog(vista, "Exito: Usuario modificado");
                        this.vista.NIF.setText("");
                        this.vista.Apellidos.setText("");
                        this.vista.Nombre.setText("");
                        this.vista.Anio.setText("");
                        this.vista.Direccion.setText("");
                        this.vista.Email.setText("");
                        this.vista.tlf.setText("");
                    }
                } catch (Exception mod) {
                    JOptionPane.showMessageDialog(null, "Error al modificar el usuario");
                }
                this.vista.NIF.setEnabled(true);
                break;
            case retroceder:
                this.vista.dispose();
                new ControladorPrincipal(new VentanaPrincipal()).iniciar();
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getButton() == 1) {
            this.vista.NIF.setEnabled(false);
            this.vista.Nombre.setEnabled(false);
            this.vista.Apellidos.setEnabled(false);
            this.vista.Anio.setEnabled(false);
            int fila = this.vista.tablaUsuario.rowAtPoint(me.getPoint());
            if (fila > -1) {
                this.vista.NIF.setText(String.valueOf(this.vista.tablaUsuario.getValueAt(fila, 0)));
                this.vista.Apellidos.setText(String.valueOf(this.vista.tablaUsuario.getValueAt(fila, 1)));
                this.vista.Nombre.setText(String.valueOf(this.vista.tablaUsuario.getValueAt(fila, 2)));
                this.vista.Anio.setText(String.valueOf(this.vista.tablaUsuario.getValueAt(fila, 3)));
                this.vista.Direccion.setText(String.valueOf(this.vista.tablaUsuario.getValueAt(fila, 4)));
                this.vista.Email.setText(String.valueOf(this.vista.tablaUsuario.getValueAt(fila, 5)));
                this.vista.tlf.setText(String.valueOf(this.vista.tablaUsuario.getValueAt(fila, 6)));
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

    public ControladorUsuario(VentanaUsuario vista) {
        this.vista = vista;
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

        this.vista.addUsuario.setActionCommand("addUsuario");
        this.vista.addUsuario.addActionListener(this);

        this.vista.listarCuentaUsuario.setActionCommand("listarCuentaUsuario");
        this.vista.listarCuentaUsuario.addActionListener(this);

        this.vista.modUsuario.setActionCommand("modUsuario");
        this.vista.modUsuario.addActionListener(this);

        this.vista.listarUsuario.setActionCommand("listarUsuario");
        this.vista.listarUsuario.addActionListener(this);

        this.vista.retroceder.setActionCommand("retroceder");
        this.vista.retroceder.addActionListener(this);

        this.vista.tablaUsuario.addMouseListener(this);
        this.vista.tablaUsuario.setModel(this.usu.getTablaUsuario());
    }

}
