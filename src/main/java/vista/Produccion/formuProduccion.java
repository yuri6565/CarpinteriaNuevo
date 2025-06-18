/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package vista.Produccion;

import java.awt.Frame;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import modelo.Conexion;

/**
 *
 * @author pc
 */
public class formuProduccion extends javax.swing.JDialog {

    private Integer idDetallePedido;

    /**
     * Creates new form formuProduccion
     */
    public formuProduccion(Frame parent, boolean modal, Integer produccionPanel) {
        super(parent, modal);
        setLocationRelativeTo(parent);
        initComponents();
        cargarPedidos(); // Método para cargar los pedidos al iniciar

    }

    public formuProduccion(java.awt.Frame parent, boolean modal) {
        this(parent, modal, null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnGuardar = new rojeru_san.RSButtonRiple();
        jLabel10 = new javax.swing.JLabel();
        btnCancelar = new rojeru_san.RSButtonRiple();
        txtinicio = new com.toedter.calendar.JDateChooser();
        txtfinal = new com.toedter.calendar.JDateChooser();
        txtcantidad = new RSMaterialComponent.RSTextFieldMaterial();
        jLabel12 = new javax.swing.JLabel();
        txtdimensiones = new RSMaterialComponent.RSTextFieldMaterial();
        jLabel13 = new javax.swing.JLabel();
        Boxnombrepedi = new RSMaterialComponent.RSComboBoxMaterial();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(46, 49, 82));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Century751 BT", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Produccion");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 520, 50));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel6.setText("fecha final:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 150, -1, -1));

        btnGuardar.setBackground(new java.awt.Color(46, 49, 82));
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plus (2).png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setFont(new java.awt.Font("Humnst777 BlkCn BT", 1, 18)); // NOI18N
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 330, 140, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel10.setText("fecha inicial:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, -1, -1));

        btnCancelar.setBackground(new java.awt.Color(46, 49, 82));
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/salida (1).png"))); // NOI18N
        btnCancelar.setText("Volver");
        btnCancelar.setFont(new java.awt.Font("Humnst777 BlkCn BT", 1, 18)); // NOI18N
        btnCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 330, 140, -1));

        txtinicio.setBackground(new java.awt.Color(255, 255, 255));
        txtinicio.setForeground(new java.awt.Color(255, 255, 255));
        txtinicio.setDateFormatString("y-MM-d");
        txtinicio.setMaxSelectableDate(new java.util.Date(253370786472000L));
        jPanel1.add(txtinicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 100, 200, 30));

        txtfinal.setBackground(new java.awt.Color(255, 255, 255));
        txtfinal.setForeground(new java.awt.Color(255, 255, 255));
        txtfinal.setDateFormatString("y-MM-d");
        jPanel1.add(txtfinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 180, 200, 30));

        txtcantidad.setForeground(new java.awt.Color(0, 0, 0));
        txtcantidad.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtcantidad.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtcantidad.setPhColor(new java.awt.Color(0, 0, 0));
        txtcantidad.setPlaceholder("");
        txtcantidad.setSelectionColor(new java.awt.Color(0, 0, 0));
        txtcantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcantidadActionPerformed(evt);
            }
        });
        jPanel1.add(txtcantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 190, 30));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel12.setText("Nombre pedido:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        txtdimensiones.setForeground(new java.awt.Color(0, 0, 0));
        txtdimensiones.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtdimensiones.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtdimensiones.setPhColor(new java.awt.Color(0, 0, 0));
        txtdimensiones.setPlaceholder("");
        txtdimensiones.setSelectionColor(new java.awt.Color(0, 0, 0));
        txtdimensiones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdimensionesActionPerformed(evt);
            }
        });
        jPanel1.add(txtdimensiones, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 190, 30));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel13.setText("Dimensiones:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, -1, -1));

        Boxnombrepedi.setForeground(new java.awt.Color(102, 102, 102));
        Boxnombrepedi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar", "pendiente", "proceso", "finalizado" }));
        Boxnombrepedi.setFont(new java.awt.Font("Roboto Bold", 0, 14)); // NOI18N
        Boxnombrepedi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoxnombrepediActionPerformed(evt);
            }
        });
        jPanel1.add(Boxnombrepedi, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel14.setText("Cantidad:");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // Validar campos
        if (txtinicio.getDate() == null || txtfinal.getDate() == null
                || Boxnombrepedi.getSelectedIndex() == 0) {

            new espacio_alerta((Frame) this.getParent(), true, "Error",
                    "Todos los campos son obligatorios").setVisible(true);
            return;

        }

        // Obtener fechas
        Date fechaInicio = new Date(txtinicio.getDate().getTime());
        Date fechaFinProduccion = new Date(txtfinal.getDate().getTime());

        // Validar que fecha final no sea anterior a la inicial
        if (fechaFinProduccion.before(fechaInicio)) {
            new Error_fecha((Frame) this.getParent(), true, "Error",
                    "La fecha final no puede ser anterior a la inicial").setVisible(true);
            return;

        }

        // Obtener la fecha límite del pedido desde la base de datos
        try (Connection con = new Conexion().getConnection(); PreparedStatement ps = con.prepareStatement(
                "SELECT p.fecha_fin FROM pedido p "
                + "JOIN detalle_pedido dp ON p.id_pedido = dp.pedido_id_pedido "
                + "WHERE dp.iddetalle_pedido = ?")) {

            ps.setInt(1, this.idDetallePedido);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Date fechaFinPedido = rs.getDate("fecha_fin");

                if (fechaFinProduccion.after(fechaFinPedido)) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String fechaPedidoStr = sdf.format(fechaFinPedido);
                    String fechaProduccionStr = sdf.format(fechaFinProduccion);

                    // Crear el mensaje personalizado
                    String mensajeError = "La fecha final de producción (" + fechaProduccionStr + ") "
                            + "sobrepasa la fecha límite del pedido";

                    // Mostrar la alerta personalizada
                    new AlertaFechaPedido(
                            (Frame) this.getParent(),
                            true,
                            mensajeError,
                            fechaPedidoStr
                    ).setVisible(true);
                    return;

                }
            }
        } catch (SQLException e) {
            new Error_guardar((Frame) this.getParent(), true, "Error",
                    "Error al verificar fechas: " + e.getMessage()).setVisible(true);
            return;
        }

        // Confirmación
        alertaa confirmDialog = new alertaa(
                (Frame) this.getParent(),
                true,
                "Confirmar",
                "¿Desea guardar los datos?"
        );
        confirmDialog.setVisible(true);

        if (confirmDialog.opcionConfirmada) {
            try (Connection con = new Conexion().getConnection(); PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO produccion (fecha_inicio, fecha_fin,  detalle_pedido_iddetalle_pedido) "
                    + "VALUES (?,  ?, ?)")) {

                ps.setDate(1, fechaInicio);
                ps.setDate(2, fechaFinProduccion);
                ps.setInt(3, this.idDetallePedido);
                ps.executeUpdate();

                Datos_guardados exitoDialog = new Datos_guardados(
                        (Frame) this.getParent(),
                        true,
                        "Éxito",
                        "Datos guardados correctamente"
                );
                exitoDialog.setLocationRelativeTo(null);
                exitoDialog.setVisible(true);

                this.dispose();
            } catch (SQLException e) {
                new Error_guardar((Frame) this.getParent(), true, "Error",
                        "Error al guardar: " + e.getMessage()).setVisible(true);
                e.printStackTrace();
            }
        } else {
            this.dispose();

        }

    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();

    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtcantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcantidadActionPerformed

    private void txtdimensionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdimensionesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdimensionesActionPerformed

    private void BoxnombrepediActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoxnombrepediActionPerformed
        if (Boxnombrepedi.getSelectedIndex() > 0) {
            PedidoItem selected = (PedidoItem) Boxnombrepedi.getSelectedItem();
            this.idDetallePedido = selected.getId();
            cargarDetallesPedido(selected.getId());
        }
    }

    private void cargarDetallesPedido(int idDetalle) {
        // Usar try-with-resources para asegurar el cierre de conexiones
        try (Connection con = new Conexion().getConnection(); PreparedStatement ps = con.prepareStatement(
                "SELECT dp.cantidad, dp.dimension, p.fecha_inicio, p.fecha_fin "
                + "FROM detalle_pedido dp "
                + "JOIN pedido p ON dp.pedido_id_pedido = p.id_pedido "
                + "WHERE dp.iddetalle_pedido = ?")) {

            ps.setInt(1, idDetalle);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Cargar cantidad (con manejo de nulos)
                    int cantidad = rs.getInt("cantidad");
                    txtcantidad.setText(rs.wasNull() ? "" : String.valueOf(cantidad));

                    // Cargar dimensiones (con manejo de nulos)
                    String dimension = rs.getString("dimension");
                    txtdimensiones.setText(dimension != null ? dimension : "");

                    // Cargar fechas con validación de nulos
                    java.sql.Date fechaInicio = rs.getDate("fecha_inicio");
                    if (fechaInicio != null) {
                        txtinicio.setDate(fechaInicio);
                    } else {
                        txtinicio.setDate(null); // Limpiar si es nulo
                    }

                    java.sql.Date fechaFin = rs.getDate("fecha_fin");
                    if (fechaFin != null) {
                        txtfinal.setDate(fechaFin);
                    } else {
                        txtfinal.setDate(null); // Limpiar si es nulo
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontraron detalles para el pedido seleccionado",
                            "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar detalles: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

    }//GEN-LAST:event_BoxnombrepediActionPerformed

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
            java.util.logging.Logger.getLogger(formuProduccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formuProduccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formuProduccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formuProduccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                formuProduccion dialog = new formuProduccion(new javax.swing.JFrame(), true);
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
    private RSMaterialComponent.RSComboBoxMaterial Boxnombrepedi;
    private rojeru_san.RSButtonRiple btnCancelar;
    private rojeru_san.RSButtonRiple btnGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private RSMaterialComponent.RSTextFieldMaterial txtcantidad;
    private RSMaterialComponent.RSTextFieldMaterial txtdimensiones;
    private com.toedter.calendar.JDateChooser txtfinal;
    private com.toedter.calendar.JDateChooser txtinicio;
    // End of variables declaration//GEN-END:variables

    private void cargarPedidos() {
        try (Connection con = new Conexion().getConnection(); PreparedStatement ps = con.prepareStatement("SELECT iddetalle_pedido, descripcion FROM detalle_pedido"); ResultSet rs = ps.executeQuery()) {

            Boxnombrepedi.removeAllItems();
            Boxnombrepedi.addItem("Seleccionar");

            while (rs.next()) {
                Boxnombrepedi.addItem(new PedidoItem(
                        rs.getInt("iddetalle_pedido"),
                        rs.getString("descripcion")
                ));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar pedidos: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

// Clase auxiliar para manejar los items del ComboBox
    private class PedidoItem {

        private int id;
        private String descripcion;

        public PedidoItem(int id, String descripcion) {
            this.id = id;
            this.descripcion = descripcion;
        }

        public int getId() {
            return id;
        }

        @Override
        public String toString() {
            return descripcion;
        }
    }
}
