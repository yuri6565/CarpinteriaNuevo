package vista;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADSO
 */
public final class caja extends javax.swing.JPanel {

    public caja() {
        initComponents();
        tblCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblCliente.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"Id", "Nombre", "Apellido", "Numero Tel", "Email"}
        ));
        cargarTablaClientes();
    }

    public class Conexion {

        public Connection getConnection() {
            Connection con = null;
            try {
                String myBD = "jdbc:mysql://localhost:3306/carpinteriasistema?serverTimezone=UTC";
                con = DriverManager.getConnection(myBD, "root", "");
                System.out.println("Conexión exitosa.");
            } catch (SQLException e) {
                System.out.println("Error al conectar: " + e.getMessage());
            }
            return con;
        }
    }

    public void cargarTablaClientes() {
        try (Connection con = new Conexion().getConnection(); PreparedStatement ps = con.prepareStatement("SELECT codigo, nombre, apellido, telefono, email FROM cliente"); ResultSet rs = ps.executeQuery()) {

            DefaultTableModel model = (DefaultTableModel) tblCliente.getModel();
            model.setRowCount(0);

            int rowNum = 1;
            while (rs.next()) {
                model.addRow(new Object[]{
                    rowNum++,
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("telefono"),
                    rs.getString("email"),
                    null
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar datos: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelprincipal = new javax.swing.JPanel();
        txtProductionPrice = new javax.swing.JTextField();
        txtProductDescription = new javax.swing.JTextField();
        txtQuantityOrder = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnAddCarrito = new javax.swing.JButton();
        txtProductName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCliente = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        btnCrearCliente = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCarrito = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        ComboIVA = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1290, 730));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelprincipal.setBackground(new java.awt.Color(255, 255, 255));
        panelprincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtProductionPrice.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        panelprincipal.add(txtProductionPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 140, 280, -1));

        txtProductDescription.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        panelprincipal.add(txtProductDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 190, 280, -1));

        txtQuantityOrder.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        panelprincipal.add(txtQuantityOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 240, 280, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel4.setText("Nombre Producto");
        panelprincipal.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 70, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel5.setText("Precio Produccion");
        panelprincipal.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 120, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel6.setText("Descripcion");
        panelprincipal.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 170, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel7.setText("Cantidad de la Orden");
        panelprincipal.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 220, -1, -1));

        btnAddCarrito.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnAddCarrito.setText("Añadir al Carrito");
        panelprincipal.add(btnAddCarrito, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 340, 290, 50));

        txtProductName.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        panelprincipal.add(txtProductName, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 90, 280, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel2.setText("Datos Venta Producto");
        panelprincipal.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 20, -1, -1));

        jScrollPane1.setViewportView(tblCliente);

        panelprincipal.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 330, -1));

        jLabel8.setText("te llevara directamente a crear un cliente");
        panelprincipal.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 550, -1, -1));

        btnCrearCliente.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnCrearCliente.setText("Añadir cliente");
        btnCrearCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearClienteActionPerformed(evt);
            }
        });
        panelprincipal.add(btnCrearCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 487, 320, 50));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setText("Lista de Clientes");
        panelprincipal.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, -1, -1));

        tblCarrito.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Producto", "Nombre", "Cantidad", "Precio", "Descripcion", "Sub Total"
            }
        ));
        jScrollPane2.setViewportView(tblCarrito);

        panelprincipal.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 60, 320, 247));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel3.setText("Carrito");
        panelprincipal.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 20, -1, -1));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jButton1.setText("Guardar detalles del pedido");
        panelprincipal.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 370, 270, 50));

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jButton2.setText("Reiniciar");
        panelprincipal.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 430, 270, 50));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel9.setText("Cantidad Total:");
        panelprincipal.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 340, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel10.setText("00000");
        panelprincipal.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 340, -1, -1));

        ComboIVA.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        ComboIVA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione Porcentaje del IVA", "Ninguno", "21%", "10%", "4%" }));
        panelprincipal.add(ComboIVA, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 290, 280, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel11.setText("IVA");
        panelprincipal.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 270, -1, -1));

        add(panelprincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1120, 600));
    }// </editor-fold>//GEN-END:initComponents
/*
    public void cambiarPanel(JPanel nuevoPanel) {
    nuevoPanel.setSize(1290, 730);
    nuevoPanel.setLocation(0, 0);
    
    contenedor.removeAll();
    contenedor.add(nuevoPanel, BorderLayout.CENTER);
    contenedor.revalidate();
    contenedor.repaint();
}

caja in = new caja(this); // Pasar this (la instancia de Principal)
in.setSize(1290, 730);
in.setLocation(0, 0);

contenedor.removeAll();
contenedor.add(in);
contenedor.revalidate();
contenedor.repaint();
     */
    private void btnCrearClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearClienteActionPerformed
        if(!this.btnCrearCliente.isSelected()){
        this.btnCrearCliente.setSelected(true);
        
        Cliente cliente = new Cliente();
        cliente.setSize(1290, 730);
        cliente.setLocation(0, 0);
        
        panelprincipal.removeAll();
        panelprincipal.add(cliente);
        panelprincipal.revalidate();
        panelprincipal.repaint();
        
        }
       
        
    }//GEN-LAST:event_btnCrearClienteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboIVA;
    private javax.swing.JButton btnAddCarrito;
    private javax.swing.JButton btnCrearCliente;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panelprincipal;
    private javax.swing.JTable tblCarrito;
    private javax.swing.JTable tblCliente;
    private javax.swing.JTextField txtProductDescription;
    private javax.swing.JTextField txtProductName;
    private javax.swing.JTextField txtProductionPrice;
    private javax.swing.JTextField txtQuantityOrder;
    // End of variables declaration//GEN-END:variables
}
