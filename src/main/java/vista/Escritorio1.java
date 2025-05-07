/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import vista.proveedor.proveedores;
import modelo.Conexion;;

/**
 *
 * @author Personal
 */
public class Escritorio1 extends javax.swing.JPanel {
    
    
    
    public Escritorio1( ) {

        initComponents();
        actualizarIdMaximoProveedor();
        actualizarIdMaximocliente();
        actualizarIdMaximousuario();
        actualizarIdMaximoproduccion();
        actualizarIdMaximopedido();
        
    }

    
private void actualizarIdMaximoProveedor() {
    if (jLabel1 == null) return;
    try (Connection con = Conexion.getConnection();
         PreparedStatement ps = con.prepareStatement("SELECT MAX(id_proveedor) AS max_id FROM proveedor");
         ResultSet rs = ps.executeQuery()) {

        if (rs.next()) {
            int maxId = rs.getInt("max_id");
            jLabel1.setText(String.valueOf(maxId)); // Actualiza el label con el ID máximo
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this,
                "Error al obtener el ID máximo: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }
}
private void actualizarIdMaximocliente() {
    if (jLabel2 == null) return;
    try (Connection con = Conexion.getConnection();
         PreparedStatement ps = con.prepareStatement("SELECT MAX(codigo) AS max_id FROM cliente");
         ResultSet rs = ps.executeQuery()) {

        if (rs.next()) {
            int maxId = rs.getInt("max_id");
            jLabel2.setText(String.valueOf(maxId)); // Actualiza el label con el ID máximo
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this,
                "Error al obtener el ID máximo: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }
}
private void actualizarIdMaximousuario() {
    if (jLabel3 == null) return;
    try (Connection con = Conexion.getConnection();
         PreparedStatement ps = con.prepareStatement("SELECT MAX(id_usuario) AS max_id FROM usuario");
         ResultSet rs = ps.executeQuery()) {

        if (rs.next()) {
            int maxId = rs.getInt("max_id");
            jLabel3.setText(String.valueOf(maxId)); // Actualiza el label con el ID máximo
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this,
                "Error al obtener el ID máximo: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }
}
private void actualizarIdMaximoproduccion() {
    if (jLabel5 == null) return;
    try (Connection con = Conexion.getConnection();
         PreparedStatement ps = con.prepareStatement("SELECT MAX(id_produccion) AS max_id FROM produccion");
         ResultSet rs = ps.executeQuery()) {

        if (rs.next()) {
            int maxId = rs.getInt("max_id");
            jLabel5.setText(String.valueOf(maxId)); // Actualiza el label con el ID máximo
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this,
                "Error al obtener el ID máximo: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }
}
private void actualizarIdMaximopedido() {
    if (jLabel4 == null) return;
    try (Connection con = Conexion.getConnection();
         PreparedStatement ps = con.prepareStatement("SELECT MAX(id_pedido) AS max_id FROM pedido");
         ResultSet rs = ps.executeQuery()) {

        if (rs.next()) {
            int maxId = rs.getInt("max_id");
            jLabel4.setText(String.valueOf(maxId)); // Actualiza el label con el ID máximo
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this,
                "Error al obtener el ID máximo: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        usuario = new RSMaterialComponent.RSButtonMaterialTwo();
        jLabel3 = new javax.swing.JLabel();
        rSComboBoxMaterial1 = new RSMaterialComponent.RSComboBoxMaterial();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        proveedor = new RSMaterialComponent.RSButtonMaterialTwo();
        jPanel5 = new javax.swing.JPanel();
        pedidos = new RSMaterialComponent.RSButtonMaterialTwo();
        jLabel4 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        cliente = new RSMaterialComponent.RSButtonMaterialTwo();
        jLabel2 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        stock = new RSMaterialComponent.RSButtonMaterialTwo();
        jLabel9 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        produccion = new RSMaterialComponent.RSButtonMaterialTwo();
        jLabel5 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1290, 730));
        setPreferredSize(new java.awt.Dimension(890, 690));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel4.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        usuario.setBackground(new java.awt.Color(29, 30, 51));
        usuario.setText("Usuarios");
        usuario.setBackgroundHover(new java.awt.Color(29, 30, 31));
        usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuarioActionPerformed(evt);
            }
        });
        jPanel3.add(usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -13, 230, 60));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 70, 50));

        jPanel4.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 250, 200, 130));

        rSComboBoxMaterial1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Notificaciones", " " }));
        rSComboBoxMaterial1.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        jPanel4.add(rSComboBoxMaterial1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 600, 60));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jLabel1FocusLost(evt);
            }
        });
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 70, 50));

        proveedor.setBackground(new java.awt.Color(29, 30, 51));
        proveedor.setText("Proveedores");
        proveedor.setBackgroundHover(new java.awt.Color(29, 30, 31));
        proveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proveedorActionPerformed(evt);
            }
        });
        jPanel2.add(proveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -13, 230, 60));

        jPanel4.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 250, 200, 130));

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pedidos.setBackground(new java.awt.Color(29, 30, 51));
        pedidos.setText("Pedidos");
        pedidos.setBackgroundHover(new java.awt.Color(29, 30, 31));
        pedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pedidosActionPerformed(evt);
            }
        });
        jPanel5.add(pedidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -13, 230, 60));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 70, 50));

        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 410, 200, 130));

        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cliente.setBackground(new java.awt.Color(29, 30, 51));
        cliente.setText("Clientes");
        cliente.setBackgroundHover(new java.awt.Color(29, 30, 31));
        cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clienteActionPerformed(evt);
            }
        });
        jPanel6.add(cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -13, 230, 60));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel6.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 70, 50));

        jPanel4.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 250, 200, 130));

        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        stock.setBackground(new java.awt.Color(29, 30, 51));
        stock.setText("Stock");
        stock.setBackgroundHover(new java.awt.Color(29, 30, 31));
        stock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockActionPerformed(evt);
            }
        });
        jPanel7.add(stock, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -13, 230, 60));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel9.setText("10");
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel7.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 70, 50));

        jPanel4.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 430, 200, 130));

        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        produccion.setBackground(new java.awt.Color(29, 30, 51));
        produccion.setText("Produccion");
        produccion.setBackgroundHover(new java.awt.Color(29, 30, 31));
        produccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                produccionActionPerformed(evt);
            }
        });
        jPanel8.add(produccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -13, 230, 60));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel8.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 70, 50));

        jPanel4.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 420, 200, 130));

        add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 600));
    }// </editor-fold>//GEN-END:initComponents

    private void proveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_proveedorActionPerformed
        // TODO add your handling code here:
       actualizarIdMaximoProveedor();
    }//GEN-LAST:event_proveedorActionPerformed

    private void usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuarioActionPerformed
        // TODO add your handling code here:
        actualizarIdMaximousuario();
    }//GEN-LAST:event_usuarioActionPerformed

    private void pedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pedidosActionPerformed
        // TODO add your handling code here:
        actualizarIdMaximopedido();
    }//GEN-LAST:event_pedidosActionPerformed

    private void clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clienteActionPerformed
        // TODO add your handling code here:
        actualizarIdMaximocliente();
    }//GEN-LAST:event_clienteActionPerformed

    private void stockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stockActionPerformed

    private void produccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_produccionActionPerformed
        // TODO add your handling code here:
        actualizarIdMaximoproduccion();
    }//GEN-LAST:event_produccionActionPerformed

    private void jLabel1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jLabel1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1FocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RSMaterialComponent.RSButtonMaterialTwo cliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private RSMaterialComponent.RSButtonMaterialTwo pedidos;
    private RSMaterialComponent.RSButtonMaterialTwo produccion;
    private RSMaterialComponent.RSButtonMaterialTwo proveedor;
    private RSMaterialComponent.RSComboBoxMaterial rSComboBoxMaterial1;
    private RSMaterialComponent.RSButtonMaterialTwo stock;
    private RSMaterialComponent.RSButtonMaterialTwo usuario;
    // End of variables declaration//GEN-END:variables
}
