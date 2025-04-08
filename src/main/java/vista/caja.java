package vista;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import modelo.Conexion;


/**
 *
 * @author ADSO
 */
public final class caja extends javax.swing.JPanel {
  
public caja() {
        initComponents();
        btnNuevo.setVisible(false);
}
    
/*
    public void cargarComboClientes(Cliente cliente) {
        String sql = "SELECT * FROM cliente                   ";
       try (Connection con = new Conexion().getConnection();
         PreparedStatement stmt = con.prepareStatement(TOOL_TIP_TEXT_KEY);
         ResultSet rs = ps.executeQuery()) {

        // Crear nuevo modelo para el combobox
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        
        // Agregar primer elemento opcional (puedes eliminarlo si no lo necesitas)
        model.addElement("-- Seleccione un cliente --");
        
        while (rs.next()) {
            // Concatenar nombre y apellido para mostrar en el combobox
            String cliente = rs.getString("nombre") + " " + rs.getString("apellido");
            model.addElement(cliente);
        }
        
        // Aplicar el modelo al combobox
        comboCliente.setModel(model);
        
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this,
                "Error al cargar clientes: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
    }

*/
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel12 = new javax.swing.JLabel();
        comboCliente = new RSMaterialComponent.RSComboBoxMaterial();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaM = new RSMaterialComponent.RSTableMetroCustom();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnNuevo1 = new rojeru_san.RSButtonRiple();
        btnNuevo2 = new rojeru_san.RSButtonRiple();
        btnNuevo = new rojeru_san.RSButtonRiple();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1290, 730));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setText("Cliente");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, -1, -1));

        comboCliente.setColorMaterial(new java.awt.Color(29, 30, 51));
        comboCliente.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        comboCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboClienteActionPerformed(evt);
            }
        });
        add(comboCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, 280, 30));

        tablaM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Detalle", "Unidad", "Cantidad", "Valor Unitario"
            }
        ));
        tablaM.setBackgoundHead(new java.awt.Color(46, 49, 82));
        tablaM.setBackgoundHover(new java.awt.Color(67, 150, 209));
        tablaM.setBorderHead(null);
        tablaM.setBorderRows(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        tablaM.setColorBorderHead(new java.awt.Color(46, 49, 82));
        tablaM.setColorBorderRows(new java.awt.Color(46, 49, 82));
        tablaM.setColorPrimaryText(new java.awt.Color(0, 0, 0));
        tablaM.setColorSecondary(new java.awt.Color(255, 255, 255));
        tablaM.setColorSecundaryText(new java.awt.Color(0, 0, 0));
        tablaM.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        tablaM.setFontHead(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        tablaM.setFontRowHover(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        tablaM.setFontRowSelect(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        tablaM.setSelectionBackground(new java.awt.Color(67, 150, 209));
        jScrollPane2.setViewportView(tablaM);
        tablaM.getColumnModel().getColumn(0).setPreferredWidth(10);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 1140, 420));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel1.setText("000000");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 560, 100, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        jLabel2.setText("Total:");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 560, 80, -1));

        btnNuevo1.setBackground(new java.awt.Color(46, 49, 82));
        btnNuevo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plus (2).png"))); // NOI18N
        btnNuevo1.setText("Añadir");
        btnNuevo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevo1ActionPerformed(evt);
            }
        });
        add(btnNuevo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 30, 100, 30));

        btnNuevo2.setBackground(new java.awt.Color(46, 49, 82));
        btnNuevo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/file-pdf-solid-60 (1).png"))); // NOI18N
        btnNuevo2.setText("Crear PDF");
        btnNuevo2.setColorHover(new java.awt.Color(46, 49, 82));
        btnNuevo2.setRippleColor(new java.awt.Color(255, 153, 153));
        btnNuevo2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnNuevo2MouseMoved(evt);
            }
        });
        btnNuevo2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNuevo2MouseExited(evt);
            }
        });
        btnNuevo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevo2ActionPerformed(evt);
            }
        });
        add(btnNuevo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 550, 200, 140));

        btnNuevo.setBackground(new java.awt.Color(204, 204, 204));
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/file-pdf-solid-60 (2).png"))); // NOI18N
        btnNuevo.setText("Crear PDF");
        btnNuevo.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnNuevoMouseMoved(evt);
            }
        });
        btnNuevo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNuevoMouseEntered(evt);
            }
        });
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 550, 200, 140));
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
       
       
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnNuevoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevoMouseEntered
      
    }//GEN-LAST:event_btnNuevoMouseEntered

    private void btnNuevo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevo1ActionPerformed

    private void btnNuevoMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevoMouseMoved
        btnNuevo2.setVisible(true);
btnNuevo.setVisible(false);
    }//GEN-LAST:event_btnNuevoMouseMoved

    private void btnNuevo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevo2ActionPerformed

    private void btnNuevo2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevo2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevo2MouseExited

    private void btnNuevo2MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevo2MouseMoved

        btnNuevo2.setVisible(false);
        btnNuevo.setVisible(true);

    }//GEN-LAST:event_btnNuevo2MouseMoved

    private void comboClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboClienteActionPerformed
    
        
        
        
    }//GEN-LAST:event_comboClienteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSButtonRiple btnNuevo;
    private rojeru_san.RSButtonRiple btnNuevo1;
    private rojeru_san.RSButtonRiple btnNuevo2;
    private RSMaterialComponent.RSComboBoxMaterial comboCliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private RSMaterialComponent.RSTableMetroCustom tablaM;
    // End of variables declaration//GEN-END:variables
}
