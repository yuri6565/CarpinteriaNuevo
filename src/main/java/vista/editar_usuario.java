/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package vista;





import controlador.Ctrl_Perfil;
import java.awt.Frame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import modelo.UsuarioModelo;
//


/**
 *
 * @author ZenBook
 */
public class editar_usuario extends javax.swing.JDialog {

  private int idUsuario;
    private JPanel parentPanel;
    private DefaultTableModel tableModel;
    private int selectedRow;
    public boolean guardado = false; // Add this flag

    public editar_usuario(java.awt.Frame parent, boolean modal, int idUsuario, DefaultTableModel tableModel, int selectedRow) {
        super(parent, modal);
        this.idUsuario = idUsuario;
        this.tableModel = tableModel;
        this.selectedRow = selectedRow;
        initComponents();
        setTitle("Editar Usuario");
        cargarDatosUsuario();
        
    }

    public boolean isGuardado() {
        return guardado;
    }

    private void cargarDatosUsuario() {
        Ctrl_Perfil controlUsuario = new Ctrl_Perfil();
        modelo.UsuarioModelo usuario = controlUsuario.obtenerUsuario(idUsuario);

        if (usuario != null) {
            nombretxt.setText(usuario.getNombre());
            apellidotxt.setText(usuario.getApellido());
            usuariotxt.setText(usuario.getUsuario());
            correotxt.setText(usuario.getCorreo_electronico());
            contrasenatxt.setText(usuario.getContrasena());
            telefonotxt.setText(usuario.getTelefono());
            roltxt.setSelectedItem(usuario.getRol());
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo cargar la información del usuario.");
            dispose();
        }
    }

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        nombretxt = new RSMaterialComponent.RSTextFieldMaterial();
        btnCancelar = new rojeru_san.RSButtonRiple();
        btnGuardar = new rojeru_san.RSButtonRiple();
        usuariotxt = new RSMaterialComponent.RSTextFieldMaterial();
        contrasenatxt = new RSMaterialComponent.RSTextFieldMaterial();
        jLabel10 = new javax.swing.JLabel();
        apellidotxt = new RSMaterialComponent.RSTextFieldMaterial();
        jLabel8 = new javax.swing.JLabel();
        telefonotxt = new RSMaterialComponent.RSTextFieldMaterial();
        jLabel9 = new javax.swing.JLabel();
        roltxt = new RSMaterialComponent.RSComboBoxMaterial();
        jLabel11 = new javax.swing.JLabel();
        correotxt = new RSMaterialComponent.RSTextFieldMaterial();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(46, 49, 82));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Century751 BT", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Editar Usuario");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 520, 50));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel4.setText("Nombre:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, 20));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel6.setText("Usuario:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel7.setText("Correo Electronico:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, -1));

        nombretxt.setForeground(new java.awt.Color(0, 0, 0));
        nombretxt.setColorMaterial(new java.awt.Color(0, 0, 0));
        nombretxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nombretxt.setPhColor(new java.awt.Color(0, 0, 0));
        nombretxt.setPlaceholder("Ingrese el nombre...");
        nombretxt.setSelectionColor(new java.awt.Color(0, 0, 0));
        nombretxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombretxtActionPerformed(evt);
            }
        });
        jPanel1.add(nombretxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 200, 30));

        btnCancelar.setBackground(new java.awt.Color(46, 49, 82));
        btnCancelar.setText("Cancelar");
        btnCancelar.setColorHover(new java.awt.Color(204, 0, 0));
        btnCancelar.setFont(new java.awt.Font("Humnst777 BlkCn BT", 1, 14)); // NOI18N
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 420, 140, -1));

        btnGuardar.setBackground(new java.awt.Color(46, 49, 82));
        btnGuardar.setText("Guardar");
        btnGuardar.setColorHover(new java.awt.Color(0, 153, 51));
        btnGuardar.setFont(new java.awt.Font("Humnst777 BlkCn BT", 1, 14)); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 420, 140, -1));

        usuariotxt.setForeground(new java.awt.Color(0, 0, 0));
        usuariotxt.setColorMaterial(new java.awt.Color(0, 0, 0));
        usuariotxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        usuariotxt.setPhColor(new java.awt.Color(0, 0, 0));
        usuariotxt.setPlaceholder("Ingrese el telefono..");
        usuariotxt.setSelectionColor(new java.awt.Color(0, 0, 0));
        usuariotxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuariotxtActionPerformed(evt);
            }
        });
        jPanel1.add(usuariotxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 200, 30));

        contrasenatxt.setForeground(new java.awt.Color(0, 0, 0));
        contrasenatxt.setColorMaterial(new java.awt.Color(0, 0, 0));
        contrasenatxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        contrasenatxt.setPhColor(new java.awt.Color(0, 0, 0));
        contrasenatxt.setPlaceholder("Ingrese la dirección...");
        contrasenatxt.setSelectionColor(new java.awt.Color(0, 0, 0));
        contrasenatxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contrasenatxtActionPerformed(evt);
            }
        });
        jPanel1.add(contrasenatxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 210, 200, 30));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel10.setText("Apellido:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 110, -1, 20));

        apellidotxt.setForeground(new java.awt.Color(0, 0, 0));
        apellidotxt.setColorMaterial(new java.awt.Color(0, 0, 0));
        apellidotxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        apellidotxt.setPhColor(new java.awt.Color(0, 0, 0));
        apellidotxt.setPlaceholder("Ingrese el apellido");
        apellidotxt.setSelectionColor(new java.awt.Color(0, 0, 0));
        apellidotxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apellidotxtActionPerformed(evt);
            }
        });
        jPanel1.add(apellidotxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 140, 200, 30));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel8.setText("Telefono");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 260, -1, -1));

        telefonotxt.setForeground(new java.awt.Color(0, 0, 0));
        telefonotxt.setColorMaterial(new java.awt.Color(0, 0, 0));
        telefonotxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        telefonotxt.setPhColor(new java.awt.Color(0, 0, 0));
        telefonotxt.setPlaceholder("Ingrese el telefono..");
        telefonotxt.setSelectionColor(new java.awt.Color(0, 0, 0));
        telefonotxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                telefonotxtActionPerformed(evt);
            }
        });
        jPanel1.add(telefonotxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 290, 200, 30));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel9.setText("Rol");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, -1, -1));

        roltxt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Escoja el rol:", "Administrador", "Trabajador", "Contador" }));
        roltxt.setColorMaterial(new java.awt.Color(29, 30, 51));
        roltxt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel1.add(roltxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 200, 30));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel11.setText("Contraseña");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 180, -1, -1));

        correotxt.setForeground(new java.awt.Color(0, 0, 0));
        correotxt.setColorMaterial(new java.awt.Color(0, 0, 0));
        correotxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        correotxt.setPhColor(new java.awt.Color(0, 0, 0));
        correotxt.setPlaceholder("Ingrese el telefono..");
        correotxt.setSelectionColor(new java.awt.Color(0, 0, 0));
        correotxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                correotxtActionPerformed(evt);
            }
        });
        jPanel1.add(correotxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 200, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nombretxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombretxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombretxtActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose(); 
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
  
  // Validate inputs
        if (nombretxt.getText().trim().isEmpty() || apellidotxt.getText().trim().isEmpty() ||
            usuariotxt.getText().trim().isEmpty() || correotxt.getText().trim().isEmpty() ||
            contrasenatxt.getText().trim().isEmpty() || telefonotxt.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
            return;
        }

        String selectedRol = roltxt.getSelectedItem().toString();
        if (selectedRol.equals("Escoja el rol:")) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un rol válido.");
            return;
        }

        // Create and populate the user model
        UsuarioModelo usuarioEditado = new UsuarioModelo();
        usuarioEditado.setId_usuario(idUsuario);
        usuarioEditado.setNombre(nombretxt.getText().trim());
        usuarioEditado.setApellido(apellidotxt.getText().trim());
        usuarioEditado.setUsuario(usuariotxt.getText().trim());
        usuarioEditado.setCorreo_electronico(correotxt.getText().trim());
        usuarioEditado.setContrasena(contrasenatxt.getText().trim());
        usuarioEditado.setTelefono(telefonotxt.getText().trim());
        usuarioEditado.setRol(selectedRol);

        // Update the user via the controller
        Ctrl_Perfil control = new Ctrl_Perfil();
        if (control.editar(usuarioEditado, idUsuario)) {
            JOptionPane.showMessageDialog(this, "Datos actualizados correctamente");
            guardado = true;

            if (tableModel != null && selectedRow != -1) {
                tableModel.setValueAt(usuarioEditado.getNombre(), selectedRow, 1);
                tableModel.setValueAt(usuarioEditado.getApellido(), selectedRow, 2);
                tableModel.setValueAt(usuarioEditado.getUsuario(), selectedRow, 3);
                tableModel.setValueAt(usuarioEditado.getCorreo_electronico(), selectedRow, 4);
                tableModel.setValueAt(usuarioEditado.getTelefono(), selectedRow, 5);
                tableModel.setValueAt(usuarioEditado.getRol(), selectedRow, 6);
            }

            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Error al actualizar los datos del usuario.");
            guardado = false;
        }

    }//GEN-LAST:event_btnGuardarActionPerformed

    private void usuariotxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuariotxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usuariotxtActionPerformed

    private void contrasenatxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contrasenatxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_contrasenatxtActionPerformed

    private void apellidotxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apellidotxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_apellidotxtActionPerformed

    private void telefonotxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_telefonotxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_telefonotxtActionPerformed

    private void correotxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_correotxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_correotxtActionPerformed

    /**
     */
public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(editar_usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            editar_usuario dialog = new editar_usuario(new javax.swing.JFrame(), true, 1, null, -1);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RSMaterialComponent.RSTextFieldMaterial apellidotxt;
    private rojeru_san.RSButtonRiple btnCancelar;
    private rojeru_san.RSButtonRiple btnGuardar;
    private RSMaterialComponent.RSTextFieldMaterial contrasenatxt;
    private RSMaterialComponent.RSTextFieldMaterial correotxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private RSMaterialComponent.RSTextFieldMaterial nombretxt;
    private RSMaterialComponent.RSComboBoxMaterial roltxt;
    private RSMaterialComponent.RSTextFieldMaterial telefonotxt;
    private RSMaterialComponent.RSTextFieldMaterial usuariotxt;
    // End of variables declaration//GEN-END:variables
}
