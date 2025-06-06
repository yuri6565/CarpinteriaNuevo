/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista;

import controlador.Ctrl_Cliente;
import controlador.Ctrl_Proveedor;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author ZenBook
 */
public class Proveedor extends javax.swing.JPanel {

    private int id_proveedor;
    private Ctrl_Proveedor proveedorContro;

    public Proveedor() {
        proveedorContro = new Ctrl_Proveedor();
        initComponents();
        aplicarTema(); // Apply initial theme
        cargartablacliente();
        // Register for theme changes
        TemaManager.getInstance().addThemeChangeListener(() -> {
            aplicarTema(); // Update theme when it changes
        });
    }

    // ... (keep initComponents, action listeners, and cargartablacliente as they are)

    public void aplicarTema() {
        boolean oscuro = TemaManager.getInstance().isOscuro();

        if (oscuro) {
            Color fondo = new Color(21,21,33);
            Color primario = new Color(40, 60, 150);
            Color texto = Color.WHITE;

            jPanel1.setBackground(fondo);
            txtBuscar.setBackground(fondo);
            txtBuscar.setForeground(texto);
            txtBuscar.setColorIcon(texto);
            txtBuscar.setPhColor(Color.LIGHT_GRAY);
            
            
          tablaclientes.setBackground(new Color (21,21,33));
            tablaclientes.setBackgoundHead(new Color (67, 71, 120));
            tablaclientes.setForegroundHead(new Color (255,255,255));
            tablaclientes.setBackgoundHover(new Color(40, 50, 90));
         tablaclientes.setFont(new Font("Tahoma", Font.PLAIN, 15));
            tablaclientes.setColorPrimary(new Color(37,37,52));
            tablaclientes.setColorPrimaryText(texto);
            tablaclientes.setColorSecondary(new Color(30,30,45));
            tablaclientes.setColorSecundaryText(texto);
            tablaclientes.setColorBorderHead(primario);
            tablaclientes.setColorBorderRows(fondo.darker());
            tablaclientes.setFontHead(new Font("Tahoma", Font.BOLD, 15));
            tablaclientes.setFontRowHover(new Font("Tahoma", Font.BOLD, 15));
            tablaclientes.setFontRowSelect(new Font("Tahoma", Font.BOLD, 15));
            tablaclientes.setEffectHover(true);
              tablaclientes.setShowGrid(true);
tablaclientes.setGridColor(Color.WHITE); // o el color que desees

            btnNuevo1.setBackground(new Color(67, 71, 120));
            btnNuevo1.setBackgroundHover(new Color(118,142,240));
            btnEditar1.setBackground(new Color(67, 71, 120));
           btnEditar1.setBackgroundHover(new Color(118,142,240));
            btnEliminar1.setBackground(new Color(67, 71, 120));
            btnEliminar1.setBackgroundHover(new Color(118,142,240));
        } else {
            Color fondo = new Color(242, 247, 255);
            Color texto = Color.BLACK;
            Color primario = new Color(72, 92, 188);

            jPanel1.setBackground(fondo);
            txtBuscar.setBackground(fondo);
            txtBuscar.setForeground(texto);
            txtBuscar.setColorIcon(texto);
            txtBuscar.setPhColor(Color.GRAY);
            
            
      tablaclientes.setBackground(new Color (255,255,255));
            tablaclientes.setBackgoundHead(new Color (46,49,82));
            tablaclientes.setForegroundHead(Color.WHITE);
            tablaclientes.setBackgoundHover(new Color(67,150,209));
           tablaclientes.setFont(new Font("Tahoma", Font.PLAIN, 15));
            tablaclientes.setColorPrimary(new Color(242, 242, 242));
            tablaclientes.setColorPrimaryText(texto);
            tablaclientes.setColorSecondary(new Color(255, 255, 255));
            tablaclientes.setColorSecundaryText(texto);
            tablaclientes.setColorBorderHead(primario);
            tablaclientes.setColorBorderRows(new Color(0,0,0));
            tablaclientes.setFontHead(new Font("Tahoma", Font.BOLD, 15));
            tablaclientes.setFontRowHover(new Font("Tahoma", Font.BOLD, 15));
            tablaclientes.setFontRowSelect(new Font("Tahoma", Font.BOLD, 15));
            tablaclientes.setEffectHover(true);
            tablaclientes.setSelectionBackground(new Color(67,150,209));
            tablaclientes.setShowGrid(true);
tablaclientes.setGridColor(Color.BLACK); // o el color que desees

            btnNuevo1.setBackground(new Color(46, 49, 82));
            btnEditar1.setBackground(new Color(46, 49, 82));
            btnEliminar1.setBackground(new Color(46, 49, 82));
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
        btnNuevo1 = new RSMaterialComponent.RSButtonShape();
        btnEditar1 = new RSMaterialComponent.RSButtonShape();
        btnEliminar1 = new RSMaterialComponent.RSButtonShape();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaclientes = new RSMaterialComponent.RSTableMetroCustom();
        txtBuscar = new RSMaterialComponent.RSTextFieldMaterialIcon();

        setPreferredSize(new java.awt.Dimension(1290, 730));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(241, 245, 253));
        jPanel1.setPreferredSize(new java.awt.Dimension(960, 570));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnNuevo1.setBackground(new java.awt.Color(46, 49, 82));
        btnNuevo1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        btnNuevo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plus (2).png"))); // NOI18N
        btnNuevo1.setText(" Nuevo");
        btnNuevo1.setBackgroundHover(new java.awt.Color(67, 150, 209));
        btnNuevo1.setFont(new java.awt.Font("Roboto Bold", 1, 16)); // NOI18N
        btnNuevo1.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        btnNuevo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnNuevo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevo1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnNuevo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 110, 110, 30));

        btnEditar1.setBackground(new java.awt.Color(46, 49, 82));
        btnEditar1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        btnEditar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pencil (1)_1.png"))); // NOI18N
        btnEditar1.setText(" Editar");
        btnEditar1.setBackgroundHover(new java.awt.Color(67, 150, 209));
        btnEditar1.setFont(new java.awt.Font("Roboto Bold", 1, 16)); // NOI18N
        btnEditar1.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        btnEditar1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnEditar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditar1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnEditar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 110, 110, 30));

        btnEliminar1.setBackground(new java.awt.Color(46, 49, 82));
        btnEliminar1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        btnEliminar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/delete (1).png"))); // NOI18N
        btnEliminar1.setText(" Eliminar");
        btnEliminar1.setToolTipText("");
        btnEliminar1.setBackgroundHover(new java.awt.Color(67, 150, 209));
        btnEliminar1.setFont(new java.awt.Font("Roboto Bold", 1, 16)); // NOI18N
        btnEliminar1.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        btnEliminar1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnEliminar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminar1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 110, 110, 30));

        tablaclientes.setForeground(new java.awt.Color(255, 255, 255));
        tablaclientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Codigo", "Nombre", "Correo Electronico", "Telefono", "Direccion", "Producto"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaclientes.setToolTipText("");
        tablaclientes.setBackgoundHead(new java.awt.Color(255, 255, 51));
        tablaclientes.setBackgoundHover(new java.awt.Color(51, 255, 51));
        tablaclientes.setBorderHead(null);
        tablaclientes.setBorderRows(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        tablaclientes.setColorBorderHead(new java.awt.Color(102, 102, 255));
        tablaclientes.setColorBorderRows(new java.awt.Color(255, 102, 102));
        tablaclientes.setColorPrimary(new java.awt.Color(153, 255, 153));
        tablaclientes.setColorPrimaryText(new java.awt.Color(0, 0, 0));
        tablaclientes.setColorSecondary(new java.awt.Color(0, 204, 153));
        tablaclientes.setColorSecundaryText(new java.awt.Color(30, 30, 45));
        tablaclientes.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        tablaclientes.setFontHead(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        tablaclientes.setFontRowHover(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        tablaclientes.setFontRowSelect(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        tablaclientes.setGridColor(new java.awt.Color(102, 255, 102));
        tablaclientes.setPreferredSize(new java.awt.Dimension(500, 500));
        tablaclientes.setSelectionBackground(new java.awt.Color(67, 150, 209));
        jScrollPane3.setViewportView(tablaclientes);
        tablaclientes.getColumnModel().getColumn(0).setPreferredWidth(10);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, 1160, 530));

        txtBuscar.setBackground(new java.awt.Color(242, 247, 255));
        txtBuscar.setForeground(new java.awt.Color(0, 0, 0));
        txtBuscar.setColorIcon(new java.awt.Color(0, 0, 0));
        txtBuscar.setColorMaterial(new java.awt.Color(153, 153, 153));
        txtBuscar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SEARCH);
        txtBuscar.setPhColor(new java.awt.Color(102, 102, 102));
        txtBuscar.setPlaceholder("Buscar...");
        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });
        jPanel1.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 430, 40));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1290, 730));
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo1ActionPerformed
        proveedornuevo dialog = new proveedornuevo(new javax.swing.JFrame(), true);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

        if (dialog.isGuardado()) {
            cargartablacliente(); 
        }
    }//GEN-LAST:event_btnNuevo1ActionPerformed

    private void btnEditar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditar1ActionPerformed
        int filaSeleccionada = tablaclientes.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione un cliente para editar");
            return;
        }

        int idCliente = Integer.parseInt(tablaclientes.getValueAt(filaSeleccionada, 0).toString());
        DefaultTableModel model = (DefaultTableModel) tablaclientes.getModel();
        EditarCliente1 editar = new EditarCliente1(new javax.swing.JFrame(), true, idCliente, model, filaSeleccionada);
        editar.setLocationRelativeTo(null);
        editar.setVisible(true);
    }//GEN-LAST:event_btnEditar1ActionPerformed

    private void btnEliminar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminar1ActionPerformed
        int filaSeleccionada = tablaclientes.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un cliente para eliminar");
            return;
        }

        int idCliente = (int) tablaclientes.getValueAt(filaSeleccionada, 0);
        Ctrl_Cliente controlCliente = new Ctrl_Cliente();

        int confirmacion = JOptionPane.showConfirmDialog(this,
            "¿Está seguro de que desea eliminar el cliente con código " + idCliente + "?",
            "Confirmar eliminación",
            JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            if (controlCliente.eliminar(idCliente)) {
                JOptionPane.showMessageDialog(this, "Cliente eliminado correctamente");
                cargartablacliente(); // Recargar la tabla
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar cliente");
            }
        }
    }//GEN-LAST:event_btnEliminar1ActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtBuscarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RSMaterialComponent.RSButtonShape btnEditar1;
    private RSMaterialComponent.RSButtonShape btnEliminar1;
    private RSMaterialComponent.RSButtonShape btnNuevo1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private RSMaterialComponent.RSTableMetroCustom tablaclientes;
    private RSMaterialComponent.RSTextFieldMaterialIcon txtBuscar;
    // End of variables declaration//GEN-END:variables
 public void cargartablacliente() {
    tablaclientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Código");
    model.addColumn("Nombre");
    model.addColumn("Correo Electrónico");
    model.addColumn("Teléfono");
    model.addColumn("Dirección");
    model.addColumn("Producto");

    List<modelo.ProveedorDatos> proveedores = proveedorContro.obtenerProveedores();
    for (modelo.ProveedorDatos proveedor : proveedores) {
        Object[] fila = new Object[6];
        fila[0] = proveedor.getId_proveedor();
        fila[1] = proveedor.getNombre() != null ? proveedor.getNombre() : "Sin nombre";
        fila[2] = proveedor.getCorreo_electronico() != null ? proveedor.getCorreo_electronico() : "Sin correo";
        fila[3] = proveedor.getTelefono() != null ? proveedor.getTelefono() : "Sin teléfono";
        fila[4] = proveedor.getDireccion() != null ? proveedor.getDireccion() : "Sin dirección";
        fila[5] = obtenerProductosPorProveedor(proveedor.getId_proveedor());
        model.addRow(fila);
    }

    tablaclientes.setModel(model);
    System.out.println("Número de filas en el modelo después de cargar: " + model.getRowCount());

    // Listener para capturar la selección de una fila
    tablaclientes.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int fila_point = tablaclientes.rowAtPoint(e.getPoint());
            if (fila_point > -1) {
                id_proveedor = (int) tablaclientes.getValueAt(fila_point, 0); // Usa el campo de la clase
            }
        }
    });
}
  private String obtenerProductosPorProveedor(int idProveedor) {
    List<String> productos = proveedorContro.obtenerProveedoresConProductos()
        .stream()
        .filter(p -> p.contains("Proveedor: " + idProveedor + " | "))
        .map(p -> p.replace("Proveedor: " + idProveedor + " | Producto: ", ""))
        .collect(Collectors.toList());

    return productos.isEmpty() ? "Sin productos" : String.join(", ", productos);
}

    private void cargartablaclienteFiltrado(String textoBusqueda) {
        tablaclientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Código");
        model.addColumn("Nombre");
        model.addColumn("Correo Electrónico");
        model.addColumn("Teléfono");
        model.addColumn("Dirección");
        model.addColumn("Producto");

        List<modelo.ProveedorDatos> proveedores = proveedorContro.obtenerProveedores()
            .stream()
            .filter(p -> p.getNombre() != null && p.getNombre().toLowerCase().contains(textoBusqueda.toLowerCase()))
            .toList();

        for (modelo.ProveedorDatos proveedor : proveedores) {
            Object[] fila = new Object[6];
            fila[0] = proveedor.getId_proveedor();
            fila[1] = proveedor.getNombre() != null ? proveedor.getNombre() : "Sin nombre";
            fila[2] = proveedor.getCorreo_electronico() != null ? proveedor.getCorreo_electronico() : "Sin correo";
            fila[3] = proveedor.getTelefono() != null ? proveedor.getTelefono() : "Sin teléfono";
            fila[4] = proveedor.getDireccion() != null ? proveedor.getDireccion() : "Sin dirección";
            fila[5] = obtenerProductosPorProveedor(proveedor.getId_proveedor());
            model.addRow(fila);
        }

        tablaclientes.setModel(model);
    }
    
    




}

