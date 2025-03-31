/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package vista;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Conexion;

/**
 *
 * @author pc
 */
public class formuProduccion extends javax.swing.JDialog {

    
    
    private final Produccion produccionPanel;

    /**
     * Creates new form formuProduccion
     */
    public formuProduccion(java.awt.Frame parent, boolean modal, Produccion produccionPanel) {
        super(parent, modal);
        this.produccionPanel = produccionPanel;  // Asigna la referencia
        initComponents();
    }

    public formuProduccion(java.awt.Frame parent, boolean modal) {
        this(parent, modal, null);  // Llama al nuevo constructor con null
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnGuardar = new rojeru_san.RSButtonRiple();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnCancelar = new rojeru_san.RSButtonRiple();
        txtinicio = new RSMaterialComponent.RSTextFieldTwo();
        txtfinal = new RSMaterialComponent.RSTextFieldTwo();
        Boxestado = new RSMaterialComponent.RSComboBoxMaterial();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(490, 340));

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
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("fecha final:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, -1, -1));

        btnGuardar.setBackground(new java.awt.Color(46, 49, 82));
        btnGuardar.setText("Guardar");
        btnGuardar.setFont(new java.awt.Font("Humnst777 BlkCn BT", 1, 14)); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 230, 140, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Estado:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("fecha inicial:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        btnCancelar.setBackground(new java.awt.Color(46, 49, 82));
        btnCancelar.setText("Volver");
        btnCancelar.setFont(new java.awt.Font("Humnst777 BlkCn BT", 1, 14)); // NOI18N
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 230, 140, -1));

        txtinicio.setForeground(new java.awt.Color(46, 49, 82));
        txtinicio.setBorderColor(new java.awt.Color(46, 49, 82));
        txtinicio.setPhColor(new java.awt.Color(46, 49, 82));
        txtinicio.setPlaceholder("");
        txtinicio.setSelectionColor(new java.awt.Color(46, 49, 82));
        txtinicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtinicioActionPerformed(evt);
            }
        });
        jPanel1.add(txtinicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 200, 30));

        txtfinal.setForeground(new java.awt.Color(46, 49, 82));
        txtfinal.setBorderColor(new java.awt.Color(46, 49, 82));
        txtfinal.setPhColor(new java.awt.Color(46, 49, 82));
        txtfinal.setPlaceholder("");
        txtfinal.setSelectionColor(new java.awt.Color(46, 49, 82));
        jPanel1.add(txtfinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, 200, 30));

        Boxestado.setForeground(new java.awt.Color(102, 102, 102));
        Boxestado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar", "pendiente", "proceso", "finalizado" }));
        Boxestado.setFont(new java.awt.Font("Roboto Bold", 0, 14)); // NOI18N
        jPanel1.add(Boxestado, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

        if (txtinicio.getText().isEmpty() || txtfinal.getText().isEmpty() || 
        Boxestado.getSelectedItem() == null || Boxestado.getSelectedIndex() == 0) {
        
        JOptionPane.showMessageDialog(this, 
            "Todos los campos son obligatorios", 
            "Error", 
            JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        // Obtener valores
        Date fechaInicio = Date.valueOf(txtinicio.getText());
        Date fechaFin = Date.valueOf(txtfinal.getText());
        String estado = Boxestado.getSelectedItem().toString();

        // Validar fechas
        if (fechaFin.before(fechaInicio)) {
            JOptionPane.showMessageDialog(this, 
                "La fecha final no puede ser anterior a la fecha inicial", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Insertar en BD (sin pedido_codigo)
        Connection con = new Conexion().getConnection();
        String sql = "INSERT INTO produccion (fecha_inicio, fecha_fin, estado) VALUES (?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setDate(1, fechaInicio);
        ps.setDate(2, fechaFin);
        ps.setString(3, estado);
        
        ps.executeUpdate();
        
        ps.close();
        con.close();
        
        this.dispose();
        
        if (produccionPanel != null) {
            produccionPanel.cargarTablaProduccion();
        }
        
    } catch (IllegalArgumentException e) {
        JOptionPane.showMessageDialog(this, 
            "Formato de fecha incorrecto (usar YYYY-MM-DD)", 
            "Error", 
            JOptionPane.ERROR_MESSAGE);
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, 
            "Error al guardar: " + e.getMessage(), 
            "Error", 
            JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }

    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
        tresProduccion dialog = new tresProduccion(new javax.swing.JFrame(), true);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtinicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtinicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtinicioActionPerformed

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
    private RSMaterialComponent.RSComboBoxMaterial Boxestado;
    private rojeru_san.RSButtonRiple btnCancelar;
    private rojeru_san.RSButtonRiple btnGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private RSMaterialComponent.RSTextFieldTwo txtfinal;
    private RSMaterialComponent.RSTextFieldTwo txtinicio;
    // End of variables declaration//GEN-END:variables
}
