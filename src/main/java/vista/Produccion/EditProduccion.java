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
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;
import modelo.Conexion;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 *
 * @author pc
 */
public class EditProduccion extends javax.swing.JDialog {

    private int idProduccionActual;
    private boolean datosModificados = false;

    /**
     * Creates new form EditTresProduccion
     */
    public EditProduccion(Frame parent, boolean modal, int idProduccion) {
        super(parent, modal);
        initComponents();
        this.idProduccionActual = idProduccion;
        setLocationRelativeTo(parent);

        cargarDetallesPedido();

        if (idProduccion > 0) {
            cargarDatosProduccion(idProduccion);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        Boxestado = new RSMaterialComponent.RSComboBoxMaterial();
        txtinicio = new com.toedter.calendar.JDateChooser();
        txtfinal = new com.toedter.calendar.JDateChooser();
        btnGuardar1 = new rojeru_san.RSButtonRiple();
        btnCancelar1 = new rojeru_san.RSButtonRiple();
        jLabel11 = new javax.swing.JLabel();
        BoxNombre = new RSMaterialComponent.RSComboBoxMaterial();

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

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("fecha final:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, -1, -1));

        jLabel9.setBackground(new java.awt.Color(0, 0, 0));
        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Estado:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 80, -1, -1));

        jLabel10.setBackground(new java.awt.Color(0, 0, 0));
        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("fecha inicial:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, -1, -1));

        Boxestado.setForeground(new java.awt.Color(102, 102, 102));
        Boxestado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar", "pendiente", "proceso", "finalizado" }));
        Boxestado.setFont(new java.awt.Font("Roboto Bold", 0, 14)); // NOI18N
        Boxestado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoxestadoActionPerformed(evt);
            }
        });
        jPanel1.add(Boxestado, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, 190, -1));

        txtinicio.setBackground(new java.awt.Color(255, 255, 255));
        txtinicio.setForeground(new java.awt.Color(255, 255, 255));
        txtinicio.setDateFormatString("y-MM-d");
        txtinicio.setMaxSelectableDate(new java.util.Date(253370786472000L));
        jPanel1.add(txtinicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 190, 30));

        txtfinal.setBackground(new java.awt.Color(255, 255, 255));
        txtfinal.setForeground(new java.awt.Color(255, 255, 255));
        txtfinal.setDateFormatString("y-MM-d");
        jPanel1.add(txtfinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 180, 190, 30));

        btnGuardar1.setBackground(new java.awt.Color(46, 49, 82));
        btnGuardar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plus (2).png"))); // NOI18N
        btnGuardar1.setText("Guardar");
        btnGuardar1.setFont(new java.awt.Font("Humnst777 BlkCn BT", 1, 18)); // NOI18N
        btnGuardar1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 230, 140, -1));

        btnCancelar1.setBackground(new java.awt.Color(46, 49, 82));
        btnCancelar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/salida (1).png"))); // NOI18N
        btnCancelar1.setText("Volver");
        btnCancelar1.setFont(new java.awt.Font("Humnst777 BlkCn BT", 1, 18)); // NOI18N
        btnCancelar1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnCancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 230, 140, -1));

        jLabel11.setBackground(new java.awt.Color(0, 0, 0));
        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Nombre pedido:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        BoxNombre.setForeground(new java.awt.Color(102, 102, 102));
        BoxNombre.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar", "pendiente", "proceso", "finalizado" }));
        BoxNombre.setFont(new java.awt.Font("Roboto Bold", 0, 14)); // NOI18N
        BoxNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoxNombreActionPerformed(evt);
            }
        });
        jPanel1.add(BoxNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 190, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 490, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BoxestadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoxestadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BoxestadoActionPerformed

    private void btnGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar1ActionPerformed
        // Validación básica de campos
        if (txtinicio.getDate() == null
                || Boxestado.getSelectedIndex() <= 0
                || BoxNombre.getSelectedIndex() <= 0) {
            new espacio_alerta((Frame) this.getParent(), true,
                    "Error", "Todos los campos son obligatorios").setVisible(true);
            return;
        }

        try {
            // Obtener valores del formulario
            Date fechaInicio = new Date(txtinicio.getDate().getTime());
            Date fechaFin = txtfinal.getDate() != null ? new Date(txtfinal.getDate().getTime()) : null;
            String estado = Boxestado.getSelectedItem().toString();

            // Obtener ID del detalle seleccionado
            ItemDetallePedido selectedItem = (ItemDetallePedido) BoxNombre.getSelectedItem();
            int idDetallePedido = selectedItem.getId();

            // Validación de fechas
            if (fechaFin != null && fechaFin.before(fechaInicio)) {
                new Error_fecha((Frame) this.getParent(), true,
                        "Error", "La fecha final no puede ser anterior a la inicial").setVisible(true);
                return;
            }

            // Confirmación del usuario
            alertaa confirmDialog = new alertaa(
                    (Frame) this.getParent(),
                    true,
                    "Confirmar",
                    "¿Desea guardar los datos?"
            );
            confirmDialog.setVisible(true);

            if (!confirmDialog.opcionConfirmada) {
                return;
            }

            // Operación en base de datos
            try (Connection con = Conexion.getConnection()) {
                String sql;
                if (idProduccionActual == 0) {
                    // INSERT
                    sql = "INSERT INTO produccion (fecha_inicio, fecha_fin, estado, detalle_pedido_iddetalle_pedido) "
                            + "VALUES (?, ?, ?, ?)";
                } else {
                    // UPDATE
                    sql = "UPDATE produccion SET fecha_inicio = ?, fecha_fin = ?, estado = ?, "
                            + "detalle_pedido_iddetalle_pedido = ? WHERE id_produccion = ?";
                }

                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setDate(1, fechaInicio);
                    ps.setDate(2, fechaFin);
                    ps.setString(3, estado);
                    ps.setInt(4, idDetallePedido);

                    if (idProduccionActual > 0) {
                        ps.setInt(5, idProduccionActual);
                    }

                    int affectedRows = ps.executeUpdate();
                    if (affectedRows > 0) {
                        this.datosModificados = true;
                        mostrarMensajeExito();
                        this.dispose();
                    } else {
                        new Error_guardar((Frame) this.getParent(), true,
                                "Error", "No se pudo guardar el registro").setVisible(true);
                    }
                }
            } catch (SQLException e) {
                new Error_guardar((Frame) this.getParent(), true,
                        "Error", "Error al guardar: " + e.getMessage()).setVisible(true);
                e.printStackTrace();
            }
        } catch (Exception e) {
            new espacio_alerta((Frame) this.getParent(), true,
                    "Error", "Error inesperado: " + e.getMessage()).setVisible(true);
        }
    }

    private void mostrarMensajeExito() {
        if (idProduccionActual == 0) {
            new Datos_guardados(
                    (Frame) this.getParent(),
                    true,
                    "Éxito",
                    "Datos guardados correctamente"
            ).setVisible(true);
        } else {
            new DatosActualizados(
                    (Frame) this.getParent(),
                    true,
                    "Éxito",
                    "Datos actualizados correctamente"
            ).setVisible(true);
        }

    }//GEN-LAST:event_btnGuardar1ActionPerformed

    private void btnCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelar1ActionPerformed

    private void BoxNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoxNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BoxNombreActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormuEtapaProduccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            private Produccion produccionPanel;
            private int idProduccion;

            @Override
            public void run() {
                FormuEtapaProduccion dialog = new FormuEtapaProduccion(new javax.swing.JFrame(), true, this.idProduccion);
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
    private RSMaterialComponent.RSComboBoxMaterial BoxNombre;
    private RSMaterialComponent.RSComboBoxMaterial Boxestado;
    private rojeru_san.RSButtonRiple btnCancelar1;
    private rojeru_san.RSButtonRiple btnGuardar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private com.toedter.calendar.JDateChooser txtfinal;
    private com.toedter.calendar.JDateChooser txtinicio;
    // End of variables declaration//GEN-END:variables
public void setDatos(int idProduccion, String fechaInicio, String fechaFin, String estado, String estado1, int idDetallePedido, String dimensiones) {
        this.datosModificados = false;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            // Fechas
            if (fechaInicio != null && !fechaInicio.isEmpty() && fechaInicio.matches("\\d{4}-\\d{2}-\\d{2}")) {
                txtinicio.setDate(sdf.parse(fechaInicio));
            }

            if (fechaFin != null && !fechaFin.isEmpty() && fechaFin.matches("\\d{4}-\\d{2}-\\d{2}")) {
                txtfinal.setDate(sdf.parse(fechaFin));
            }

            // Estado
            if (estado != null && !estado.isEmpty()) {
                Boxestado.setSelectedItem(estado);
            }

            // Detalle pedido
            if (idDetallePedido > 0) {
                seleccionarItemEnCombo(BoxNombre, idDetallePedido);
            }
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this,
                    "Error al parsear fechas: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarDatosProduccion(int idProduccion) {
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(
                "SELECT p.id_produccion, p.fecha_inicio, p.fecha_fin, p.estado, "
                + "p.detalle_pedido_iddetalle_pedido, dp.descripcion "
                + "FROM produccion p "
                + "JOIN detalle_pedido dp ON p.detalle_pedido_iddetalle_pedido = dp.iddetalle_pedido "
                + "WHERE p.id_produccion = ?")) {

            ps.setInt(1, idProduccion);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Fechas
                    Date fechaInicio = rs.getDate("fecha_inicio");
                    Date fechaFin = rs.getDate("fecha_fin");

                    txtinicio.setDate(fechaInicio);
                    txtfinal.setDate(fechaFin);

                    // Estado
                    String estado = rs.getString("estado");
                    Boxestado.setSelectedItem(estado);

                    // Detalle pedido
                    int idDetalle = rs.getInt("detalle_pedido_iddetalle_pedido");
                    seleccionarItemEnCombo(BoxNombre, idDetalle);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar datos: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

// Método auxiliar para seleccionar item en combo
    private void seleccionarItemEnCombo(RSMaterialComponent.RSComboBoxMaterial combo, int id) {
        for (int i = 0; i < combo.getItemCount(); i++) {
            ItemDetallePedido item = (ItemDetallePedido) combo.getItemAt(i);
            if (item.getId() == id) {
                combo.setSelectedIndex(i);
                break;
            }
        }
    }


    public boolean datosModificados() {

        return this.datosModificados;
    }

    private void cargarDetallesPedido() {
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement("SELECT iddetalle_pedido, descripcion FROM detalle_pedido"); ResultSet rs = ps.executeQuery()) {

            // Limpiar el combo box
            BoxNombre.removeAllItems();

            // Añadir item por defecto
            BoxNombre.addItem(new ItemDetallePedido(0, "Seleccionar"));

            // Llenar con datos de la base de datos
            while (rs.next()) {
                int id = rs.getInt("iddetalle_pedido");
                String descripcion = rs.getString("descripcion");
                BoxNombre.addItem(new ItemDetallePedido(id, descripcion));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar detalles de pedido: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            // Añadir opción vacía para evitar NullPointerException
            BoxNombre.addItem(new ItemDetallePedido(0, "Error al cargar"));
        }
    }

    public class ItemDetallePedido {

        private int id;
        private String descripcion;

        public ItemDetallePedido(int id, String descripcion) {
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
