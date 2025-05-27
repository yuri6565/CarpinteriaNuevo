/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package vista;



import controlador.Ctrl_Cliente;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;



/**
 *
 * @author ZenBook
 */

public class EditarCliente1 extends javax.swing.JDialog {

    private int idCliente;
    private JPanel parentPanel; // Referencia al panel principal (Cliente)
    private DefaultTableModel tableModel; // Referencia al modelo de la tabla
    private int selectedRow; // Fila seleccionada

    public EditarCliente1(java.awt.Frame parent, boolean modal, int idCliente, DefaultTableModel tableModel, int selectedRow) {
        super(parent, modal);
        initComponents();
        this.idCliente = idCliente;
        this.tableModel = tableModel;
        this.selectedRow = selectedRow;
        cargarDatosCliente();
    }



    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        nombretxt = new RSMaterialComponent.RSTextFieldMaterial();
        btnCancelar = new rojeru_san.RSButtonRiple();
        btnGuardar = new rojeru_san.RSButtonRiple();
        numerotxt = new RSMaterialComponent.RSTextFieldMaterial();
        telefonotxt = new RSMaterialComponent.RSTextFieldMaterial();
        direcciontxt = new RSMaterialComponent.RSTextFieldMaterial();
        identificaciontxt = new RSMaterialComponent.RSComboBoxMaterial();
        jLabel10 = new javax.swing.JLabel();
        apellidotxt = new RSMaterialComponent.RSTextFieldMaterial();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(46, 49, 82));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Century751 BT", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Crear Cliente");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 520, 50));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel3.setText("Numero:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel4.setText("Nombre:  (obligatorio)");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, 20));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel6.setText("Telefono:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel7.setText("Dirección:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 270, -1, -1));

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
        jPanel1.add(nombretxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 200, 30));

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

        numerotxt.setForeground(new java.awt.Color(0, 0, 0));
        numerotxt.setColorMaterial(new java.awt.Color(0, 0, 0));
        numerotxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        numerotxt.setPhColor(new java.awt.Color(0, 0, 0));
        numerotxt.setPlaceholder("numero...");
        numerotxt.setSelectionColor(new java.awt.Color(0, 0, 0));
        numerotxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numerotxtActionPerformed(evt);
            }
        });
        jPanel1.add(numerotxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 130, 200, 30));

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
        jPanel1.add(telefonotxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 200, 30));

        direcciontxt.setForeground(new java.awt.Color(0, 0, 0));
        direcciontxt.setColorMaterial(new java.awt.Color(0, 0, 0));
        direcciontxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        direcciontxt.setPhColor(new java.awt.Color(0, 0, 0));
        direcciontxt.setPlaceholder("Ingrese la dirección...");
        direcciontxt.setSelectionColor(new java.awt.Color(0, 0, 0));
        direcciontxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                direcciontxtActionPerformed(evt);
            }
        });
        jPanel1.add(direcciontxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 300, 200, 30));

        identificaciontxt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tipo de identificacion:", "CC", "TI", "CE", "NIT" }));
        identificaciontxt.setColorMaterial(new java.awt.Color(29, 30, 51));
        identificaciontxt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        identificaciontxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                identificaciontxtActionPerformed(evt);
            }
        });
        jPanel1.add(identificaciontxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 210, 30));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel10.setText("Apellido:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 180, -1, 20));

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
        jPanel1.add(apellidotxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 210, 200, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 502, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nombretxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombretxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombretxtActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed

    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
   
// Validación de campos
        if (identificaciontxt.getSelectedItem() == null || identificaciontxt.getSelectedItem().toString().equals("Tipo de identificacion:")) {
            JOptionPane.showMessageDialog(this, "Seleccione un tipo de identificación válido.");
            return;
        }
        if (numerotxt.getText().isEmpty() || !numerotxt.getText().matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Ingrese un número de identificación válido.");
            return;
        }
        if (nombretxt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un nombre válido.");
            return;
        }
        if (apellidotxt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un apellido válido.");
            return;
        }
        if (telefonotxt.getText().isEmpty() || !telefonotxt.getText().matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Ingrese un teléfono válido.");
            return;
        }
        if (direcciontxt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese una dirección válida.");
            return;
        }

// Validación de campos (sin cambios)...
        modelo.Cliente cliente = new modelo.Cliente();
        cliente.setIdentificacion(identificaciontxt.getSelectedItem().toString());
        cliente.setNumero(Integer.parseInt(numerotxt.getText()));
        cliente.setNombre(nombretxt.getText());
        cliente.setApellido(apellidotxt.getText());
        cliente.setTelefono(telefonotxt.getText());
        cliente.setDireccion(direcciontxt.getText());

        Ctrl_Cliente contro = new Ctrl_Cliente();
        if (contro.editar(cliente, idCliente)) {
            JOptionPane.showMessageDialog(null, "Datos actualizados correctamente");
            // Actualizar directamente la fila en el modelo
            if (tableModel != null && selectedRow != -1) {
                tableModel.setValueAt(idCliente, selectedRow, 0); // Código
                tableModel.setValueAt(cliente.getIdentificacion(), selectedRow, 1); // Identificación
                tableModel.setValueAt(cliente.getNumero(), selectedRow, 2); // Número
                tableModel.setValueAt(cliente.getNombre(), selectedRow, 3); // Nombre
                tableModel.setValueAt(cliente.getApellido(), selectedRow, 4); // Apellido
                tableModel.setValueAt(cliente.getTelefono(), selectedRow, 5); // Teléfono
                tableModel.setValueAt(cliente.getDireccion(), selectedRow, 6); // Dirección
            }
            dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Error al guardar");
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void numerotxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numerotxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numerotxtActionPerformed

    private void telefonotxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_telefonotxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_telefonotxtActionPerformed

    private void direcciontxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_direcciontxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_direcciontxtActionPerformed

    private void apellidotxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apellidotxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_apellidotxtActionPerformed

    private void identificaciontxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_identificaciontxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_identificaciontxtActionPerformed

    /**
     * @param args
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
            java.util.logging.Logger.getLogger(EditarCliente1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditarCliente1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditarCliente1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditarCliente1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
// Crear un JFrame como ventana padre
                javax.swing.JFrame frame = new javax.swing.JFrame();
                // Crear un modelo de tabla de prueba con las mismas columnas que tablaclientes
                DefaultTableModel tableModel = new DefaultTableModel(
                );
                // Usar una fila de prueba (índice 0)
                int selectedRow = 0;
                // Usar un idCliente de prueba (por ejemplo, 1)
                int idCliente = 1;

                EditarCliente1 dialog = new EditarCliente1(frame, true, idCliente, tableModel, selectedRow);
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
    private RSMaterialComponent.RSTextFieldMaterial apellidotxt;
    private rojeru_san.RSButtonRiple btnCancelar;
    private rojeru_san.RSButtonRiple btnGuardar;
    private RSMaterialComponent.RSTextFieldMaterial direcciontxt;
    private RSMaterialComponent.RSComboBoxMaterial identificaciontxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private RSMaterialComponent.RSTextFieldMaterial nombretxt;
    private RSMaterialComponent.RSTextFieldMaterial numerotxt;
    private RSMaterialComponent.RSTextFieldMaterial telefonotxt;
    // End of variables declaration//GEN-END:variables



    private void cargarDatosCliente() {
        Ctrl_Cliente controlCliente = new Ctrl_Cliente();
        modelo.Cliente cliente = controlCliente.obtenerClientePorid(idCliente);

        if (cliente != null) {
            identificaciontxt.setSelectedItem(cliente.getIdentificacion());
            numerotxt.setText(String.valueOf(cliente.getNumero()));
            nombretxt.setText(cliente.getNombre());
            apellidotxt.setText(cliente.getApellido());
            telefonotxt.setText(cliente.getTelefono());
            direcciontxt.setText(cliente.getDireccion());
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo cargar la información del cliente.");
            dispose();
        }
    }
}
