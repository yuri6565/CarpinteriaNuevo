package vista;

import java.awt.BorderLayout;
import java.awt.Component;
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

    private caja parentCaja;

public void setCajaParent(caja parent) {
    this.parentCaja = parent;
}

// Método para cerrar/volver (debe llamarse desde algún botón en Cliente)
public void volverACaja() {
    if (parentCaja != null) {
        parentCaja.mostrarPanelPrincipal();
    }
    // O si está en un JDialog:
    // this.dispose();
}
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

        txtProductionPrice.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N

        txtProductDescription.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N

        txtQuantityOrder.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel4.setText("Nombre Producto");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel5.setText("Precio Produccion");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel6.setText("Descripcion");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel7.setText("Cantidad de la Orden");

        btnAddCarrito.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnAddCarrito.setText("Añadir al Carrito");

        txtProductName.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel2.setText("Datos Venta Producto");

        jScrollPane1.setViewportView(tblCliente);

        jLabel8.setText("te llevara directamente a crear un cliente");

        btnCrearCliente.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnCrearCliente.setText("Añadir cliente");
        btnCrearCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearClienteActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setText("Lista de Clientes");

        tblCarrito.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Producto", "Nombre", "Cantidad", "Precio", "Descripcion", "Sub Total"
            }
        ));
        jScrollPane2.setViewportView(tblCarrito);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel3.setText("Carrito");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jButton1.setText("Guardar detalles del pedido");

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jButton2.setText("Reiniciar");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel9.setText("Cantidad Total:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel10.setText("00000");

        ComboIVA.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        ComboIVA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione Porcentaje del IVA", "Ninguno", "21%", "10%", "4%" }));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel11.setText("IVA");

        javax.swing.GroupLayout panelprincipalLayout = new javax.swing.GroupLayout(panelprincipal);
        panelprincipal.setLayout(panelprincipalLayout);
        panelprincipalLayout.setHorizontalGroup(
            panelprincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelprincipalLayout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addComponent(jLabel1)
                .addGap(225, 225, 225)
                .addComponent(jLabel2)
                .addGap(282, 282, 282)
                .addComponent(jLabel3))
            .addGroup(panelprincipalLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addGroup(panelprincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtProductionPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtProductDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtQuantityOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(ComboIVA, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddCarrito, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(70, 70, 70)
                .addGroup(panelprincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelprincipalLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel9)
                        .addGap(13, 13, 13)
                        .addComponent(jLabel10))
                    .addGroup(panelprincipalLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelprincipalLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(panelprincipalLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(btnCrearCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelprincipalLayout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jLabel8))
        );
        panelprincipalLayout.setVerticalGroup(
            panelprincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelprincipalLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelprincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(20, 20, 20)
                .addGroup(panelprincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelprincipalLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel4)
                        .addGap(0, 0, 0)
                        .addComponent(txtProductName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jLabel5)
                        .addGap(0, 0, 0)
                        .addComponent(txtProductionPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jLabel6)
                        .addGap(0, 0, 0)
                        .addComponent(txtProductDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jLabel7)
                        .addGap(0, 0, 0)
                        .addComponent(txtQuantityOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jLabel11)
                        .addGap(0, 0, 0)
                        .addComponent(ComboIVA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(btnAddCarrito, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelprincipalLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addGroup(panelprincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addGap(10, 10, 10)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(7, 7, 7)
                .addComponent(btnCrearCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jLabel8))
        );

        add(panelprincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1290, 730));
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
    
        
    if (!panelClienteVisible) {
        panelClienteVisible = true;
        
        Cliente cliente = new Cliente();
        cliente.setCajaParent(this); // Pasar referencia a este panel
        cliente.setSize(panelprincipal.getSize());
        cliente.setLocation(0, 0);
        
        panelprincipal.removeAll();
        panelprincipal.add(cliente, BorderLayout.CENTER); // Usar BorderLayout
        panelprincipal.revalidate();
        panelprincipal.repaint();
    }
}

public void mostrarPanelPrincipal() {
    panelClienteVisible = false;
    panelprincipal.removeAll();
    // Aquí debes reconstruir el panel principal original
    initComponents(); // O reconstruir manualmente los componentes
    cargarTablaClientes();
    panelprincipal.revalidate();
    panelprincipal.repaint();

        
    }//GEN-LAST:event_btnCrearClienteActionPerformed
    private boolean panelClienteVisible = false;

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
