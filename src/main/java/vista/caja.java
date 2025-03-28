
package vista;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Conexion;
import java.sql.*;
import controlador.Ctrl_Cliente;

public class caja extends javax.swing.JPanel {

    private int cliente=0;
    private int finalTotalPrice = 0;
    private String orderId = "";
    
    public caja() {
        initComponents();
    
      
    }
    private void clearProductFields(){
       
    txtProductName.setText("");
    txtProductPrice.setText("");
    txtProductName.setText("");
    txtOrderQuantity.setText("");
    
    }
    
    public String getUniqueId(String prefix){
    return prefix + System.nanoTime();
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableCustomer = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableCart = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtCustomerName = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtCustomerMobileNumber = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtCustomerEmail = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        lblFinalTotalPrice = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtProductName = new javax.swing.JTextField();
        txtProductPrice = new javax.swing.JTextField();
        txtProductDescription = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtOrderQuantity = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(240, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Lista de Clientes");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, -1, -1));

        tableCustomer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Numero Tel", "Email"
            }
        ));
        jScrollPane1.setViewportView(tableCustomer);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 82, 412, 222));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Carrito");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 50, -1, -1));

        tableCart.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID Producto", "Nombre", "Cantidad", "Precio", "Descripcion", "Sub Total"
            }
        ));
        jScrollPane3.setViewportView(tableCart);

        add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(858, 82, 400, 222));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Seleccionar Cliente:");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 340, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Nombre");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 370, -1, -1));

        txtCustomerName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        add(txtCustomerName, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 393, 351, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Numero Telefono");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 432, -1, -1));

        txtCustomerMobileNumber.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        add(txtCustomerMobileNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 455, 351, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Correo");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 490, -1, -1));

        txtCustomerEmail.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        add(txtCustomerEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 513, 351, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setText("Cantidad Total:");
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 336, -1, -1));

        lblFinalTotalPrice.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblFinalTotalPrice.setText("00000");
        add(lblFinalTotalPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(1109, 336, -1, -1));

        jButton2.setText("Guardar Detalles de Pedido");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(858, 394, 400, 40));

        jButton3.setText("Reiniciar");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 450, 400, 40));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Datos Venta Producto");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 50, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Nombre del Producto");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 80, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Precio del Producto");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 130, -1, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Descripcion  ");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 190, -1, -1));

        txtProductName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        add(txtProductName, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 100, 346, -1));

        txtProductPrice.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        add(txtProductPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 150, 346, -1));

        txtProductDescription.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        add(txtProductDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 210, 346, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Cantidad de la Orden");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 240, -1, -1));
        add(txtOrderQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 270, 346, -1));

        jButton1.setText("Añadir al Carrito");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 330, 346, 40));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
      

    
    txtCustomerName.setEditable(false);
    txtCustomerMobileNumber.setEditable(false);
    txtCustomerEmail.setEditable(false);
    
    txtProductName.setEditable(false);
    txtProductPrice.setEditable(false);
    txtProductDescription.setEditable(false);
    
   DefaultTableModel model = (DefaultTableModel) tableCustomer.getModel();
  
        try {
            Connection con = Conexion.getCon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select *from cliente");
            while (rs.next()) {
                Object nextElement = rs.nextElement();
                
       
       txtCustomerName.setEditable(false);
        txtCustomerMobileNumber.setEditable(false);
        txtCustomerEmail.setEditable(false);
        
        txtProductName.setEditable(false);
        txtProductPrice.setEditable(false);
        txtProductDescription.setEditable(false);
        
        DefaultTableModel model = (DefaultTableModel) tableCustomer.getModel();
       
        /**try{
        
            Connection con = Conexion.getCon();
            Statement st =  con.createStatement();
            ResultSet rs = st.executeQuery("select *from clientes");
            while (rs.next()){
                model.addRow(new Object[]{rs.getString("cliente"),rs.getString("name"),rs.getString("mobileNumber"),rs.getString("email")});
             
/*
            }

        } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
        }
        */

}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblFinalTotalPrice;
    private javax.swing.JTable tableCart;
    private javax.swing.JTable tableCustomer;
    private javax.swing.JTextField txtCustomerEmail;
    private javax.swing.JTextField txtCustomerMobileNumber;
    private javax.swing.JTextField txtCustomerName;
    private javax.swing.JTextField txtOrderQuantity;
    private javax.swing.JTextField txtProductDescription;
    private javax.swing.JTextField txtProductName;
    private javax.swing.JTextField txtProductPrice;
    // End of variables declaration//GEN-END:variables

    private void dispose() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
