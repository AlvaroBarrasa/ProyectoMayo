/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author Alvaro
 */
public class VentanaUsuario extends javax.swing.JFrame {

    /**
     * Creates new form VentanaUsuario
     */
    public VentanaUsuario() {
        initComponents();
         ImageIcon icono = new ImageIcon(getClass().getResource("/Imagenes/iconoUS.png"));
        Icon fondo = new ImageIcon(icono.getImage().getScaledInstance(IconoUS.getWidth(), IconoUS.getHeight(), Image.SCALE_DEFAULT));
        IconoUS.setIcon(fondo);
        this.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        addUsuario = new javax.swing.JButton();
        listarUsuario = new javax.swing.JButton();
        listarCuentaUsuario = new javax.swing.JButton();
        modUsuario = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaUsuario = new javax.swing.JTable();
        NIF = new javax.swing.JTextField();
        Apellidos = new javax.swing.JTextField();
        Nombre = new javax.swing.JTextField();
        Direccion = new javax.swing.JTextField();
        Email = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaUsuarioCuenta = new javax.swing.JTable();
        etiNIF = new javax.swing.JLabel();
        etiApellidos = new javax.swing.JLabel();
        etiNombre = new javax.swing.JLabel();
        etiAnio = new javax.swing.JLabel();
        etiDireccion = new javax.swing.JLabel();
        etiEmail = new javax.swing.JLabel();
        etiTLF = new javax.swing.JLabel();
        retroceder = new javax.swing.JButton();
        Anio = new javax.swing.JFormattedTextField();
        tlf = new javax.swing.JFormattedTextField();
        IconoUS = new javax.swing.JLabel();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        addUsuario.setText("Añadir Usuario");

        listarUsuario.setText("Listar Usuarios");
        listarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listarUsuarioActionPerformed(evt);
            }
        });

        listarCuentaUsuario.setText("Listar usuarios y cuentas");
        listarCuentaUsuario.setToolTipText("");

        modUsuario.setText("Modificar Datos");

        tablaUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "NIF", "Apellidos", "Nombre", "Año_nacimiento", "Dirección", "Email", "Teléfono"
            }
        ));
        jScrollPane1.setViewportView(tablaUsuario);

        NIF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NIFActionPerformed(evt);
            }
        });

        Apellidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ApellidosActionPerformed(evt);
            }
        });

        Nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NombreActionPerformed(evt);
            }
        });

        Direccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DireccionActionPerformed(evt);
            }
        });

        tablaUsuarioCuenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "NIF", "Número de cuenta", "Saldo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tablaUsuarioCuenta);

        etiNIF.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        etiNIF.setText("NIF");

        etiApellidos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        etiApellidos.setText("Apellidos");

        etiNombre.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        etiNombre.setText("Nombre");

        etiAnio.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        etiAnio.setText("Año_nacimiento");

        etiDireccion.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        etiDireccion.setText("Dirección");

        etiEmail.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        etiEmail.setText("Email");

        etiTLF.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        etiTLF.setText("Teléfono");

        retroceder.setText("Retroceder");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(listarCuentaUsuario)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(retroceder)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(listarUsuario)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(Direccion, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                                            .addComponent(Nombre)
                                            .addComponent(Apellidos)
                                            .addComponent(etiAnio, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(Anio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(26, 26, 26)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(etiTLF)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(tlf, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(addUsuario, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(modUsuario, javax.swing.GroupLayout.Alignment.TRAILING)))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(etiApellidos)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(NIF, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(etiNIF, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(85, 85, 85)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(etiEmail)
                                                    .addComponent(Email, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(IconoUS, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(etiNombre)
                                            .addComponent(etiDireccion))
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addGap(32, 32, 32))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(23, Short.MAX_VALUE)
                        .addComponent(retroceder)
                        .addGap(52, 52, 52))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(IconoUS, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiNIF)
                    .addComponent(etiEmail))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NIF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiApellidos)
                    .addComponent(etiTLF))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(modUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(etiAnio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Anio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(etiDireccion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(listarUsuario))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Apellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addUsuario)
                            .addComponent(tlf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(etiNombre)
                        .addGap(2, 2, 2)
                        .addComponent(Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(listarCuentaUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void listarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listarUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_listarUsuarioActionPerformed

    private void NIFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NIFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NIFActionPerformed

    private void ApellidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ApellidosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ApellidosActionPerformed

    private void NombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NombreActionPerformed

    private void DireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DireccionActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaUsuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JFormattedTextField Anio;
    public javax.swing.JTextField Apellidos;
    public javax.swing.JTextField Direccion;
    public javax.swing.JTextField Email;
    private javax.swing.JLabel IconoUS;
    public javax.swing.JTextField NIF;
    public javax.swing.JTextField Nombre;
    public javax.swing.JButton addUsuario;
    private javax.swing.JLabel etiAnio;
    private javax.swing.JLabel etiApellidos;
    private javax.swing.JLabel etiDireccion;
    private javax.swing.JLabel etiEmail;
    private javax.swing.JLabel etiNIF;
    private javax.swing.JLabel etiNombre;
    private javax.swing.JLabel etiTLF;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JButton listarCuentaUsuario;
    public javax.swing.JButton listarUsuario;
    public javax.swing.JButton modUsuario;
    public javax.swing.JButton retroceder;
    public javax.swing.JTable tablaUsuario;
    public javax.swing.JTable tablaUsuarioCuenta;
    public javax.swing.JFormattedTextField tlf;
    // End of variables declaration//GEN-END:variables
}
