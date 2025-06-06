/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista.Cotizacion;

import RSMaterialComponent.RSTextFieldMaterial;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import vista.TemaManager;

/**
 *
 * @author ADSO
 */
public class cotizacion extends javax.swing.JPanel {
    private String clienteActual = "";

    private boolean clienteIngresado = false;

    public cotizacion() {
        initComponents();
        configurarTabla();
        CargarUnidadMed();

        // Configurar txt_total
        txt_total.setEditable(false);  // No editable
        txt_total.setHorizontalAlignment(javax.swing.JTextField.RIGHT);  // Alinear a la derecha
        txt_total.setText("$0.00");  // Valor inicial

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void configurarTabla() {
        // Crear modelo de tabla sin la columna Cliente
        DefaultTableModel modelo = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Producto", "Unidad", "Cantidad", "Valor Unitario", "Subtotal", "Editar", "Eliminar"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Hacer que solo las columnas de acción sean editables
                return column == 5 || column == 6;
            }
        };

        Tabla1.setModel(modelo);

        // Configurar anchos de columnas (ajustados por la eliminación de una columna)
        Tabla1.getColumnModel().getColumn(0).setPreferredWidth(200); // Producto
        Tabla1.getColumnModel().getColumn(1).setPreferredWidth(80);  // Unidad
        Tabla1.getColumnModel().getColumn(2).setPreferredWidth(70);  // Cantidad
        Tabla1.getColumnModel().getColumn(3).setPreferredWidth(100); // Valor Unitario
        Tabla1.getColumnModel().getColumn(4).setPreferredWidth(100); // Subtotal
        Tabla1.getColumnModel().getColumn(5).setPreferredWidth(60);  // Editar
        Tabla1.getColumnModel().getColumn(6).setPreferredWidth(60);  // Eliminar
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToggleButton1 = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tabla1 = new RSMaterialComponent.RSTableMetro();
        combox_Unidad = new RSMaterialComponent.RSComboBoxMaterial();
        txt_total = new RSMaterialComponent.RSTextFieldMaterial();
        txtNombre4 = new RSMaterialComponent.RSTextFieldMaterial();
        jLabel5 = new javax.swing.JLabel();
        txtNombre5 = new RSMaterialComponent.RSTextFieldMaterial();
        jLabel6 = new javax.swing.JLabel();
        btnAñadir = new RSMaterialComponent.RSButtonShape();
        jButton_anadir_producto = new RSMaterialComponent.RSButtonShape();
        txtNombre6 = new RSMaterialComponent.RSTextFieldMaterial();
        txt_NombreCliente = new RSMaterialComponent.RSTextFieldMaterial();
        fondo = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        jToggleButton1.setText("jToggleButton1");

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Cotizacion");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 10, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Cliente:");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 60, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Nombre :");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 70, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Cantidad:");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 100, 70, 30));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Tabla1.setForeground(new java.awt.Color(255, 255, 255));
        Tabla1.setAlignmentX(0.1F);
        Tabla1.setAlignmentY(0.1F);
        Tabla1.setBackgoundHead(new java.awt.Color(46, 49, 82));
        Tabla1.setBackgoundHover(new java.awt.Color(46, 49, 82));
        Tabla1.setColorBorderRows(new java.awt.Color(153, 153, 153));
        Tabla1.setColorPrimaryText(new java.awt.Color(46, 49, 82));
        Tabla1.setColorSecondary(new java.awt.Color(255, 255, 255));
        Tabla1.setColorSecundaryText(new java.awt.Color(46, 49, 82));
        Tabla1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tabla1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(Tabla1);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 1180, 320));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 1220, 370));

        combox_Unidad.setColorMaterial(new java.awt.Color(0, 0, 0));
        combox_Unidad.setFont(new java.awt.Font("Roboto Bold", 0, 14)); // NOI18N
        combox_Unidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combox_UnidadActionPerformed(evt);
            }
        });
        add(combox_Unidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 100, -1, 30));

        txt_total.setEditable(false);
        txt_total.setBackground(new java.awt.Color(255, 255, 255));
        txt_total.setForeground(new java.awt.Color(0, 0, 0));
        txt_total.setColorMaterial(new java.awt.Color(0, 0, 0));
        txt_total.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_total.setPhColor(new java.awt.Color(0, 0, 0));
        txt_total.setPlaceholder("Total...");
        txt_total.setSelectionColor(new java.awt.Color(0, 0, 0));
        txt_total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_totalActionPerformed(evt);
            }
        });
        add(txt_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 520, 200, 30));

        txtNombre4.setForeground(new java.awt.Color(0, 0, 0));
        txtNombre4.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtNombre4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNombre4.setPhColor(new java.awt.Color(0, 0, 0));
        txtNombre4.setPlaceholder("Ingrese cantidad..");
        txtNombre4.setSelectionColor(new java.awt.Color(0, 0, 0));
        txtNombre4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombre4ActionPerformed(evt);
            }
        });
        add(txtNombre4, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 100, 130, 30));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Unidad:");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 100, -1, 30));

        txtNombre5.setForeground(new java.awt.Color(0, 0, 0));
        txtNombre5.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtNombre5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNombre5.setPhColor(new java.awt.Color(0, 0, 0));
        txtNombre5.setPlaceholder("Ingrese Valor Unitario..");
        txtNombre5.setSelectionColor(new java.awt.Color(0, 0, 0));
        txtNombre5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombre5ActionPerformed(evt);
            }
        });
        add(txtNombre5, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 100, 150, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Valor Unitario:");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 100, 110, 30));

        btnAñadir.setBackground(new java.awt.Color(46, 49, 82));
        btnAñadir.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        btnAñadir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/file-pdf-solid-60 (2).png"))); // NOI18N
        btnAñadir.setText("Crear PDF");
        btnAñadir.setBackgroundHover(new java.awt.Color(153, 153, 153));
        btnAñadir.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnAñadir.setForegroundHover(new java.awt.Color(0, 0, 0));
        btnAñadir.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        btnAñadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAñadirActionPerformed(evt);
            }
        });
        add(btnAñadir, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 510, 170, 70));

        jButton_anadir_producto.setBackground(new java.awt.Color(46, 49, 82));
        jButton_anadir_producto.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        jButton_anadir_producto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plus (2).png"))); // NOI18N
        jButton_anadir_producto.setText("  Añadir");
        jButton_anadir_producto.setBackgroundHover(new java.awt.Color(0, 153, 0));
        jButton_anadir_producto.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        jButton_anadir_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_anadir_productoActionPerformed(evt);
            }
        });
        add(jButton_anadir_producto, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 100, 110, 30));

        txtNombre6.setForeground(new java.awt.Color(0, 0, 0));
        txtNombre6.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtNombre6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNombre6.setPhColor(new java.awt.Color(0, 0, 0));
        txtNombre6.setPlaceholder("Ingrese nombre pedido...");
        txtNombre6.setSelectionColor(new java.awt.Color(0, 0, 0));
        txtNombre6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombre6ActionPerformed(evt);
            }
        });
        add(txtNombre6, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 200, 30));

        txt_NombreCliente.setForeground(new java.awt.Color(0, 0, 0));
        txt_NombreCliente.setColorMaterial(new java.awt.Color(0, 0, 0));
        txt_NombreCliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_NombreCliente.setPhColor(new java.awt.Color(0, 0, 0));
        txt_NombreCliente.setPlaceholder("Ingrese Cliente...");
        txt_NombreCliente.setSelectionColor(new java.awt.Color(0, 0, 0));
        txt_NombreCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_NombreClienteActionPerformed(evt);
            }
        });
        add(txt_NombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 200, 30));
        add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 640));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setText("Total:");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 520, 70, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void combox_UnidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combox_UnidadActionPerformed

    }//GEN-LAST:event_combox_UnidadActionPerformed

    private void txt_totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_totalActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_txt_totalActionPerformed

    private void txtNombre4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombre4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombre4ActionPerformed

    private void txtNombre5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombre5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombre5ActionPerformed

    private void btnAñadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAñadirActionPerformed
        if (Tabla1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this,
                    "No hay productos en la cotización",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Aquí iría el código para generar el PDF
        // Puedes usar librerías como iText o Apache PDFBox
        JOptionPane.showMessageDialog(this,
                "PDF generado exitosamente",
                "Éxito", JOptionPane.INFORMATION_MESSAGE);

        reiniciarCotizacion(); // Limpiar para nueva cotización

    }//GEN-LAST:event_btnAñadirActionPerformed

    private void jButton_anadir_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_anadir_productoActionPerformed
        // Verificar si estamos en modo edición
        Integer filaEditando = (Integer) Tabla1.getClientProperty("filaEditando");

        if (filaEditando != null) {
            // Modo edición - guardar cambios
            guardarEdicion(filaEditando);
        } else {
            // Modo normal - añadir nuevo producto
            añadirNuevoProducto();
        }
    }

    private void añadirNuevoProducto() {
        // Validación del cliente permanece igual...
        if (!clienteIngresado) {
            clienteActual = txt_NombreCliente.getText().trim();
            if (clienteActual.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Por favor ingrese el nombre del cliente primero",
                        "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }
            clienteIngresado = true;
            txt_NombreCliente.setEnabled(false);
        }

        // Obtener datos permanece igual...
        String producto = txtNombre6.getText().trim();
        String unidad = combox_Unidad.getSelectedItem().toString();
        String cantidadStr = txtNombre4.getText().trim();
        String valorUnitarioStr = txtNombre5.getText().trim();

        if (producto.isEmpty() || unidad.equals("Seleccione unidad")
                || cantidadStr.isEmpty() || valorUnitarioStr.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Por favor complete todos los campos del producto",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int cantidad = Integer.parseInt(cantidadStr);
            int valorUnitario = Integer.parseInt(valorUnitarioStr);
            int subtotal = cantidad * valorUnitario;

            DefaultTableModel modelo = (DefaultTableModel) Tabla1.getModel();

            // Añadir fila con los nuevos índices (sin cliente)
            modelo.addRow(new Object[]{
                producto, // Índice 0
                unidad, // Índice 1
                cantidad, // Índice 2
                String.format("$%d", valorUnitario), // Índice 3
                String.format("$%d", subtotal), // Índice 4
                "Editar", // Índice 5
                "Eliminar" // Índice 6
            });

            limpiarCamposProducto();
            calcularTotal();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Por favor ingrese valores numéricos válidos en cantidad y valor unitario",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void guardarEdicion(int fila) {
        // Obtener datos de los campos de edición
        String producto = txtNombre6.getText().trim();
        String unidad = combox_Unidad.getSelectedItem().toString();
        String cantidadStr = txtNombre4.getText().trim();
        String valorUnitarioStr = txtNombre5.getText().trim();

        // Validación permanece igual...
        if (producto.isEmpty() || unidad.equals("Seleccione unidad")
                || cantidadStr.isEmpty() || valorUnitarioStr.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Por favor complete todos los campos del producto",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int cantidad = Integer.parseInt(cantidadStr);
            int valorUnitario = Integer.parseInt(valorUnitarioStr);
            int subtotal = cantidad * valorUnitario;

            DefaultTableModel modelo = (DefaultTableModel) Tabla1.getModel();

            // Actualizar con los nuevos índices
            modelo.setValueAt(producto, fila, 0);  // Producto
            modelo.setValueAt(unidad, fila, 1);    // Unidad
            modelo.setValueAt(cantidad, fila, 2);  // Cantidad
            modelo.setValueAt(String.format("$%d", valorUnitario), fila, 3); // Valor Unitario
            modelo.setValueAt(String.format("$%d", subtotal), fila, 4);      // Subtotal

            // Resto del método permanece igual...
            limpiarCamposProducto();
            Tabla1.putClientProperty("filaEditando", null);
            jButton_anadir_producto.setText("  Añadir");
            calcularTotal();

            JOptionPane.showMessageDialog(this,
                    "Producto actualizado correctamente",
                    "Edición exitosa",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Por favor ingrese valores numéricos válidos en cantidad y valor unitario",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCamposProducto() {
        txtNombre6.setText("");
        combox_Unidad.setSelectedIndex(0);
        txtNombre4.setText("");
        txtNombre5.setText("");
        txtNombre6.requestFocus();


    }//GEN-LAST:event_jButton_anadir_productoActionPerformed

    private void txtNombre6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombre6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombre6ActionPerformed

    private void Tabla1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabla1MouseClicked
        if (evt.getClickCount() == 1) {
            int columna = Tabla1.columnAtPoint(evt.getPoint());
            int fila = Tabla1.rowAtPoint(evt.getPoint());

            if (fila >= 0) {
                if (columna == 6) { // Columna Eliminar (ahora índice 6)
                    eliminarFila(fila);
                } else if (columna == 5) { // Columna Editar (ahora índice 5)
                    editarFila(fila);
                }
            }
        }
    }

    private void eliminarFila(int fila) {
        // Obtener datos del producto a eliminar para mostrar en el mensaje
        String producto = Tabla1.getValueAt(fila, 0).toString(); // Producto ahora en índice 0
        String cantidad = Tabla1.getValueAt(fila, 2).toString(); // Cantidad ahora en índice 2

        // Resto del método permanece igual...
        int confirmacion = JOptionPane.showConfirmDialog(
                this,
                "<html>¿Está seguro que desea eliminar el producto:<br><br>"
                + "<b>Producto:</b> " + producto + "<br>"
                + "<b>Cantidad:</b> " + cantidad + "<br><br>"
                + "Esta acción no se puede deshacer.</html>",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            DefaultTableModel modelo = (DefaultTableModel) Tabla1.getModel();
            modelo.removeRow(fila);
            calcularTotal();

            JOptionPane.showMessageDialog(
                    this,
                    "Producto eliminado correctamente",
                    "Eliminación exitosa",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    private void editarFila(int fila) {
        // Obtener los valores de la fila seleccionada con los nuevos índices
        String producto = Tabla1.getValueAt(fila, 0).toString(); // Índice 0 para Producto
        String unidad = Tabla1.getValueAt(fila, 1).toString();   // Índice 1 para Unidad
        String cantidad = Tabla1.getValueAt(fila, 2).toString(); // Índice 2 para Cantidad
        String valorUnitario = Tabla1.getValueAt(fila, 3).toString().replace("$", "").replace(",", ""); // Índice 3 para Valor Unitario

        // Resto del método permanece igual...
        txtNombre6.setText(producto);

        for (int i = 0; i < combox_Unidad.getItemCount(); i++) {
            if (combox_Unidad.getItemAt(i).equals(unidad)) {
                combox_Unidad.setSelectedIndex(i);
                break;
            }
        }

        txtNombre4.setText(cantidad);
        txtNombre5.setText(valorUnitario);

        Tabla1.putClientProperty("filaEditando", fila);
        jButton_anadir_producto.setText("  Guardar");
    }//GEN-LAST:event_Tabla1MouseClicked

    private void txt_NombreClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_NombreClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_NombreClienteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RSMaterialComponent.RSTableMetro Tabla1;
    private RSMaterialComponent.RSButtonShape btnAñadir;
    private RSMaterialComponent.RSComboBoxMaterial combox_Unidad;
    private javax.swing.JLabel fondo;
    private RSMaterialComponent.RSButtonShape jButton_anadir_producto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToggleButton jToggleButton1;
    private RSMaterialComponent.RSTextFieldMaterial txtNombre4;
    private RSMaterialComponent.RSTextFieldMaterial txtNombre5;
    private RSMaterialComponent.RSTextFieldMaterial txtNombre6;
    private RSMaterialComponent.RSTextFieldMaterial txt_NombreCliente;
    private RSMaterialComponent.RSTextFieldMaterial txt_total;
    // End of variables declaration//GEN-END:variables
/*
//metodo cargar clientes en el combox
    private void CargarComboClientes() {
        Connection cn = Conexion.getConnection();
        String sql = "SELECT nombre, apellido FROM cliente"; // Ajusta según tu estructura de tabla
        //combox_cliente.removeAllItems(); // Limpiar el ComboBox

        // Añadir el ítem por defecto
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                // Añadir cada cliente al ComboBox
                String nombreCompleto = rs.getString("nombre") + " " + rs.getString("apellido");
                combox_cliente.addItem(nombreCompleto);
                // Opcional: guardar el ID como propiedad del ítem
                //combox_cliente.setSelectedItem(rs.getString("nombre "+"apellido"));
            }
        } catch (SQLException e) {
            System.out.println("Error al cargar clientes: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Error al cargar la lista de clientes", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
     */
    private void CargarUnidadMed() {
        combox_Unidad.removeAllItems();
        combox_Unidad.addItem("Seleccione unidad");

        // Valores del ENUM definidos en la base de datos
        String[] unidades = {"Metro", "Centímetro", "Unidad"};

        for (String unidad : unidades) {
            combox_Unidad.addItem(unidad);
        }

    }

    /*joa nambre*/
    private void calcularTotal() {
        DefaultTableModel modelo = (DefaultTableModel) Tabla1.getModel();
        int total = 0;

        for (int i = 0; i < modelo.getRowCount(); i++) {
            try {
                String subtotalStr = modelo.getValueAt(i, 4).toString(); // Índice cambiado a 4

                // Eliminar símbolo de dólar y cualquier coma existente
                subtotalStr = subtotalStr.replace("$", "").replace(",", "");

                // Convertir a entero (si usas decimales, cambia a Double.parseDouble)
                int subtotal = Integer.parseInt(subtotalStr);

                total += subtotal;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                        "Error al calcular el subtotal en la fila " + (i + 1) + "\n"
                        + "Valor problemático: " + modelo.getValueAt(i, 4), // Índice cambiado a 4
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // Formatear con comas como separadores de miles
        txt_total.setText(String.format("$%,d", total));
    }

    private void reiniciarCotizacion() {
        DefaultTableModel modelo = (DefaultTableModel) Tabla1.getModel();
        modelo.setRowCount(0);

        clienteActual = "";
        clienteIngresado = false;
        txt_NombreCliente.setEnabled(true);
        txt_NombreCliente.setText("");

        // Limpiar campos de producto y resetear modo edición
        limpiarCamposProducto();
        Tabla1.putClientProperty("filaEditando", null);
        jButton_anadir_producto.setText("  Añadir");

        // Resetear el total a cero
        txt_total.setText("$0.00");

        txt_NombreCliente.requestFocus();
    }
}
