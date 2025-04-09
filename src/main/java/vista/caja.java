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

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import modelo.Conexion;


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
                comboClienteActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setText("Lista de Clientes");

        tblCarrito.setModel(new javax.swing.table.DefaultTableModel(

            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Detalle", "Unidad", "Cantidad", "Valor Unitario"
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


    }//GEN-LAST:event_btnNuevo2MouseMoved

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
