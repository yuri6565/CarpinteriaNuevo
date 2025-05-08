/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package vista.ProveedoresUsu;

/**
 *
 * @author buitr
 */
import vista.proveedor.*;
import controlador.Ctrl_ProveedorPrd;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import modelo.ProductoProveedor;

public class proveedorProducto extends javax.swing.JDialog {
 private int ultimaFilaSeleccionada = -1;
    /**
     * Creates new form proveedorProducto
     */
    public proveedorProducto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        actualizarTabla();
        tabla1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        
            // Listener para selección de filas
        tabla1.getSelectionModel().addListSelectionListener(e -> {
        if (!e.getValueIsAdjusting()) {
            cargarDatosSeleccionados();
        }
    });
    }
    
    private void actualizarTabla() {
    DefaultTableModel model = (DefaultTableModel) tabla1.getModel();
    model.setRowCount(0); // Limpiar tabla

    Ctrl_ProveedorPrd prdp = new Ctrl_ProveedorPrd();
    
    for (ProductoProveedor Productoroveedor : prdp.obtenerCategoriasProductoProveedors()) {
        model.addRow(new Object[]{Productoroveedor.getCodigo(), Productoroveedor.getNombre()});
    } 
   }
    
    
    
    


    
    
    private void cargarDatosSeleccionados() {
    int[] selectedRows = tabla1.getSelectedRows(); // Obtener todas las filas seleccionadas

    if (selectedRows.length == 1) { // Solo cargar datos si hay exactamente una fila seleccionada
        int filaSeleccionada = selectedRows[0];
        DefaultTableModel model = (DefaultTableModel) tabla1.getModel();
        txtCodigo.setText(model.getValueAt(filaSeleccionada, 0).toString()); // Código
        txtNombre.setText(model.getValueAt(filaSeleccionada, 1).toString()); // Nombre
        ultimaFilaSeleccionada = filaSeleccionada;
    } else {
        // Si hay más de una fila seleccionada o ninguna, limpiar los campos
        limpiarCampos();
        ultimaFilaSeleccionada = -1;
    }
    }
     private void limpiarCampos() {
    txtCodigo.setText("");
    txtNombre.setText("");
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtNombre = new RSMaterialComponent.RSTextFieldMaterial();
        jLabel4 = new javax.swing.JLabel();
        txtCodigo = new necesario.TextField();
        btnEliminar = new RSMaterialComponent.RSButtonShape();
        btnAñadir = new RSMaterialComponent.RSButtonShape();
        btnActualizar = new RSMaterialComponent.RSButtonShape();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla1 = new RSMaterialComponent.RSTableMetroCustom();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(46, 49, 82));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Century751 BT", 1, 17)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Productos");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 26));

        jPanel3.setBackground(new java.awt.Color(245, 246, 250));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel3.setText("Codigo:");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, -1, -1));

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
        jPanel3.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 50, 200, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel4.setText("Nombre:");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, -1, -1));

        txtCodigo.setEditable(false);
        txtCodigo.setBackground(new java.awt.Color(244, 244, 244));
        txtCodigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        txtCodigo.setForeground(new java.awt.Color(0, 0, 0));
        txtCodigo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCodigo.setPlaceholder("");
        jPanel3.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 100, 30));

        btnEliminar.setBackground(new java.awt.Color(46, 49, 82));
        btnEliminar.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/delete (1).png"))); // NOI18N
        btnEliminar.setText(" Eliminar");
        btnEliminar.setBackgroundHover(new java.awt.Color(204, 0, 0));
        btnEliminar.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel3.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 90, 110, 30));

        btnAñadir.setBackground(new java.awt.Color(46, 49, 82));
        btnAñadir.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        btnAñadir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plus (2).png"))); // NOI18N
        btnAñadir.setText("  Añadir");
        btnAñadir.setBackgroundHover(new java.awt.Color(0, 153, 0));
        btnAñadir.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        btnAñadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAñadirActionPerformed(evt);
            }
        });
        jPanel3.add(btnAñadir, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 10, 110, 30));

        btnActualizar.setBackground(new java.awt.Color(46, 49, 82));
        btnActualizar.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pencil (1)_1.png"))); // NOI18N
        btnActualizar.setText(" Actualizar");
        btnActualizar.setBackgroundHover(new java.awt.Color(67, 150, 209));
        btnActualizar.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        jPanel3.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 50, 110, 30));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 690, 130));

        tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "Codigo", "Nombre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla1.setBackgoundHead(new java.awt.Color(46, 49, 82));
        tabla1.setBackgoundHover(new java.awt.Color(67, 150, 209));
        tabla1.setBorderHead(null);
        tabla1.setBorderRows(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        tabla1.setColorBorderHead(new java.awt.Color(46, 49, 82));
        tabla1.setColorBorderRows(new java.awt.Color(46, 49, 82));
        tabla1.setColorPrimaryText(new java.awt.Color(0, 0, 0));
        tabla1.setColorSecondary(new java.awt.Color(255, 255, 255));
        tabla1.setColorSecundaryText(new java.awt.Color(0, 0, 0));
        tabla1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        tabla1.setFontHead(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        tabla1.setFontRowHover(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        tabla1.setFontRowSelect(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        tabla1.setSelectionBackground(new java.awt.Color(67, 150, 209));
        tabla1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabla1);
        tabla1.getColumnModel().getColumn(0).setPreferredWidth(10);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, 560, 290));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int[] filasSeleccionadas = tabla1.getSelectedRows();

        if (filasSeleccionadas.length == 0) {
            JOptionPane.showMessageDialog(this,
                "Selecciona al menos una categoría primero",
                "Advertencia",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Mensaje diferente para 1 vs múltiples elementos
        String mensajeConfirmacion;
        if (filasSeleccionadas.length == 1) {
            mensajeConfirmacion = "¿Estás seguro de eliminar esta categoría?";
        } else {
            mensajeConfirmacion = "¿Estás seguro de eliminar estas " + filasSeleccionadas.length + " categorías?";
        }

        int confirmacion = JOptionPane.showConfirmDialog(
            this,
            mensajeConfirmacion,
            "Confirmar eliminación",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            DefaultTableModel model = (DefaultTableModel) tabla1.getModel();
            Ctrl_ProveedorPrd prdp = new Ctrl_ProveedorPrd();
            boolean errorOcurrido = false;
            int eliminadosExitosos = 0;

            // Eliminar en orden inverso para mantener los índices correctos
            for (int i = filasSeleccionadas.length - 1; i >= 0; i--) {
                int fila = filasSeleccionadas[i];
                int codigo = (int) model.getValueAt(fila, 0);

                if (prdp.eliminar(codigo)) {
                    model.removeRow(fila);
                    eliminadosExitosos++;
                } else {
                    errorOcurrido = true;
                }
            }

            // Mostrar resultados
            if (errorOcurrido) {
                if (eliminadosExitosos > 0) {
                    JOptionPane.showMessageDialog(this,
                        "Se eliminaron " + eliminadosExitosos + " categorías, pero hubo errores con algunas.",
                        "Resultado parcial",
                        JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this,
                        "Error al eliminar todas las categorías seleccionadas",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                }
            } else {
                String mensajeExito = (filasSeleccionadas.length == 1)
                ? "¡Categoría eliminada correctamente!"
                : "¡Se eliminaron " + filasSeleccionadas.length + " categorías correctamente!";

                JOptionPane.showMessageDialog(this,
                    mensajeExito,
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
            }

            limpiarCampos();
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnAñadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAñadirActionPerformed
        String nombre = txtNombre.getText().trim(); // Obtener solo el nombre

        if (!nombre.isEmpty()) { // Verifica que el campo no esté vacío
            ProductoProveedor categoria = new ProductoProveedor(nombre);
            Ctrl_ProveedorPrd prdp = new Ctrl_ProveedorPrd();

            if (prdp.insertar(categoria)) {
                actualizarTabla(); // Refresca la tabla con los datos nuevos
                JOptionPane.showMessageDialog(this, "Categoría añadida correctamente.");
                txtNombre.setText(""); // Limpiar campo de entrada
            } else {
                JOptionPane.showMessageDialog(this, "Error al añadir categoría.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Ingrese un nombre.");
        }
    }//GEN-LAST:event_btnAñadirActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // Obtener datos de los campos
        int codigo = Integer.parseInt(txtCodigo.getText());
        String nuevoNombre = txtNombre.getText().trim();

        if (!nuevoNombre.isEmpty()) {
            ProductoProveedor categoria = new ProductoProveedor(codigo, nuevoNombre);
            Ctrl_ProveedorPrd prdp = new Ctrl_ProveedorPrd();

            if (prdp.actualizar(categoria)) {  // Necesitarás implementar este método
                actualizarTabla();
                JOptionPane.showMessageDialog(this, "¡Categoría actualizada!");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void tabla1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla1MouseClicked

    }//GEN-LAST:event_tabla1MouseClicked

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
            java.util.logging.Logger.getLogger(proveedorProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(proveedorProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(proveedorProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(proveedorProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                proveedorProducto dialog = new proveedorProducto(new javax.swing.JFrame(), true);
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
    private RSMaterialComponent.RSButtonShape btnActualizar;
    private RSMaterialComponent.RSButtonShape btnAñadir;
    private RSMaterialComponent.RSButtonShape btnEliminar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private RSMaterialComponent.RSTableMetroCustom tabla1;
    private necesario.TextField txtCodigo;
    private RSMaterialComponent.RSTextFieldMaterial txtNombre;
    // End of variables declaration//GEN-END:variables
}
