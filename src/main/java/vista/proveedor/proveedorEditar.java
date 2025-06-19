/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package vista.proveedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;
import modelo.Conexion;

public class proveedorEditar extends javax.swing.JDialog {

    private int codigo = 0;

    public proveedorEditar(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnCancelar = new rojeru_san.RSButtonRiple();
        btnGuardar = new rojeru_san.RSButtonRiple();
        jLabel8 = new javax.swing.JLabel();
        txtNombre = new RSMaterialComponent.RSTextFieldMaterial();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtproducto = new RSMaterialComponent.RSTextFieldMaterial();
        jLabel9 = new javax.swing.JLabel();
        txttelefono = new RSMaterialComponent.RSTextFieldMaterial();
        jLabel7 = new javax.swing.JLabel();
        txtdireccion = new RSMaterialComponent.RSTextFieldMaterial();
        btnBusca = new rojeru_san.RSButtonRiple();
        txtCodigo = new RSMaterialComponent.RSTextFieldMaterial();
        txttipo = new RSMaterialComponent.RSTextFieldMaterial();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(46, 49, 82));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Century751 BT", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Editar Proveedor");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 520, 50));

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
        btnGuardar.setText("Actualizar");
        btnGuardar.setColorHover(new java.awt.Color(0, 153, 51));
        btnGuardar.setFont(new java.awt.Font("Humnst777 BlkCn BT", 1, 14)); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 420, 140, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel8.setText("Nombre:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, 20));

        txtNombre.setForeground(new java.awt.Color(0, 0, 0));
        txtNombre.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtNombre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNombre.setPhColor(new java.awt.Color(0, 0, 0));
        txtNombre.setPlaceholder("Ingrese el nombre...");
        txtNombre.setSelectionColor(new java.awt.Color(0, 0, 0));
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 200, 30));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel5.setText("Tipo");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel6.setText("Producto");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, -1, -1));

        txtproducto.setForeground(new java.awt.Color(0, 0, 0));
        txtproducto.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtproducto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtproducto.setPhColor(new java.awt.Color(0, 0, 0));
        txtproducto.setPlaceholder("Ingrese la cantidad...");
        txtproducto.setSelectionColor(new java.awt.Color(0, 0, 0));
        txtproducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtproductoActionPerformed(evt);
            }
        });
        jPanel1.add(txtproducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 200, 30));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel9.setText("Telefono:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 130, -1, -1));

        txttelefono.setForeground(new java.awt.Color(0, 0, 0));
        txttelefono.setColorMaterial(new java.awt.Color(0, 0, 0));
        txttelefono.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txttelefono.setPhColor(new java.awt.Color(0, 0, 0));
        txttelefono.setPlaceholder("Ingrese telefono");
        txttelefono.setSelectionColor(new java.awt.Color(0, 0, 0));
        txttelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttelefonoActionPerformed(evt);
            }
        });
        jPanel1.add(txttelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 170, 200, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel7.setText("Direccion");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 210, -1, -1));

        txtdireccion.setForeground(new java.awt.Color(0, 0, 0));
        txtdireccion.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtdireccion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtdireccion.setPhColor(new java.awt.Color(0, 0, 0));
        txtdireccion.setPlaceholder("Ingrese la cantidad...");
        txtdireccion.setSelectionColor(new java.awt.Color(0, 0, 0));
        txtdireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdireccionActionPerformed(evt);
            }
        });
        jPanel1.add(txtdireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 250, 200, 30));

        btnBusca.setBackground(new java.awt.Color(46, 49, 82));
        btnBusca.setText("Buscar");
        btnBusca.setFont(new java.awt.Font("Humnst777 BlkCn BT", 1, 14)); // NOI18N
        btnBusca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscaActionPerformed(evt);
            }
        });
        jPanel1.add(btnBusca, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 80, 90, 30));

        txtCodigo.setForeground(new java.awt.Color(0, 0, 0));
        txtCodigo.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtCodigo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCodigo.setPhColor(new java.awt.Color(0, 0, 0));
        txtCodigo.setPlaceholder("Ingrese el codigo...");
        txtCodigo.setSelectionColor(new java.awt.Color(0, 0, 0));
        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });
        jPanel1.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 120, 30));

        txttipo.setForeground(new java.awt.Color(0, 0, 0));
        txttipo.setColorMaterial(new java.awt.Color(0, 0, 0));
        txttipo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txttipo.setPhColor(new java.awt.Color(0, 0, 0));
        txttipo.setPlaceholder("Ingrese la cantidad...");
        txttipo.setSelectionColor(new java.awt.Color(0, 0, 0));
        txttipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttipoActionPerformed(evt);
            }
        });
        jPanel1.add(txttipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 200, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 480, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose(); // Cierra la ventana emergente
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

       try {
    Connection con = Conexion.getConnection();
    String sql = "UPDATE proveedor SET nombre = ?, telefono = ?, direccion = ?, tipo = ?, producto = ? WHERE codigo_proveedor = ?";  
    // Asegúrate de que "codigo_proveedor" sea el nombre correcto de la columna
    
    PreparedStatement ps = con.prepareStatement(sql);
    
    // Parámetros en el orden correcto
    ps.setString(1, txtNombre.getText());
    ps.setString(2, txttelefono.getText());
    ps.setString(3, txtdireccion.getText());  // ¡Faltaba este campo!
    ps.setString(4, txttipo.getText());
    ps.setString(5, txtproducto.getText());
    
    // El último parámetro es el ID del proveedor (debe ser un número)
    int codigoProveedor = Integer.parseInt(txtCodigo.getText());
    ps.setInt(6, codigoProveedor);  // Se añade el 6to parámetro
    
    int rowsAffected = ps.executeUpdate();
    if (rowsAffected > 0) {
        JOptionPane.showMessageDialog(this, "Datos actualizados correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    } else {
        JOptionPane.showMessageDialog(this, "No se pudo actualizar el registro", "Error", JOptionPane.ERROR_MESSAGE);
    }
    

            con.close();
            this.dispose();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La cantidad debe ser un número", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtproductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtproductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtproductoActionPerformed

    private void txttelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttelefonoActionPerformed

    private void txtdireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdireccionActionPerformed

    private void btnBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscaActionPerformed
        try {
            String codigoStr = txtCodigo.getText();
            if (codigoStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese un ID válido", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int codigo = Integer.parseInt(codigoStr);  // Cambiado a int
            Connection con = Conexion.getConnection();
            String sql = "SELECT * FROM proveedor WHERE codigo_proveedor = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, codigo);  // Cambiado a setInt

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // Llenar los campos con los datos de la base de datos
                txtCodigo.setText(String.valueOf(rs.getInt("codigo_proveedor")));  // Asegúrate que coincide con el nombre de columna
                txtNombre.setText(rs.getString("nombre"));
                txttelefono.setText(rs.getString("telefono"));
                txtdireccion.setText(rs.getString("direccion"));
                txttipo.setText(rs.getString("tipo"));
                txtproducto.setText(rs.getString("producto"));

                // Puedes agregar más campos según necesites
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró producción con ese ID", "Error", JOptionPane.ERROR_MESSAGE);
            }

            con.close();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al buscar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnBuscaActionPerformed

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void txttipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttipoActionPerformed

    
    
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(proveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(proveedorEditar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(proveedorEditar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(proveedorEditar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(proveedorEditar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                proveedorEditar dialog = new proveedorEditar(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSButtonRiple btnBusca;
    private rojeru_san.RSButtonRiple btnCancelar;
    private rojeru_san.RSButtonRiple btnGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private RSMaterialComponent.RSTextFieldMaterial txtCodigo;
    private RSMaterialComponent.RSTextFieldMaterial txtNombre;
    private RSMaterialComponent.RSTextFieldMaterial txtdireccion;
    private RSMaterialComponent.RSTextFieldMaterial txtproducto;
    private RSMaterialComponent.RSTextFieldMaterial txttelefono;
    private RSMaterialComponent.RSTextFieldMaterial txttipo;
    // End of variables declaration//GEN-END:variables
}
