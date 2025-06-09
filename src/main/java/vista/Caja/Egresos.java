package vista.Caja;

import controlador.Ctrl_CajaEgresos;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import vista.TemaManager;

public final class Egresos extends javax.swing.JPanel {

    public Egresos() {
        initComponents();

        Tabla1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"Codigo", "Fecha Pago", "Monto", "Descripcion", "Categoria", "Detalle", "Editar", "proveedor", "productos", "cantidad"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        Tabla1.removeColumn(Tabla1.getColumnModel().getColumn(7)); // Oculta proveedor
        Tabla1.removeColumn(Tabla1.getColumnModel().getColumn(7)); // Oculta productos
        Tabla1.removeColumn(Tabla1.getColumnModel().getColumn(7)); // Oculta productos

        Tabla1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        Tabla1.setRowSelectionAllowed(true);
        Tabla1.setFocusable(false);
        cargarTablaEgresos();
        aplicarTema(); // Apply initial theme
        // Register for theme changes
        TemaManager.getInstance().addThemeChangeListener(() -> {
            aplicarTema(); // Update theme when it changes
        });
    }

    public void cargarTablaEgresos() {
        DefaultTableModel model = (DefaultTableModel) Tabla1.getModel();
        model.setRowCount(0); // Limpiar tabla

        Ctrl_CajaEgresos ctrl = new Ctrl_CajaEgresos();
        List<modelo.Caja> egresos = ctrl.obtenerEgresos();

        if (egresos.isEmpty()) {
            System.out.println("No se encontraron egresos en la base de datos");
            return;
        }

        for (modelo.Caja caja : egresos) {
            model.addRow(new Object[]{
                caja.getId_codigo(),
                caja.getFecha(),
                caja.getMonto(),
                caja.getDescripcion(),
                caja.getCategoria(),
                "Ver",
                "editar",
                caja.getProveedor() != null ? caja.getProveedor() : "",
                caja.getProductos() != null ? String.join(", ", caja.getProductos()) : "",
                caja.getCantidad()

            });
        }

        // Ajustar ancho de columnas
        Tabla1.getColumnModel().getColumn(0).setPreferredWidth(50);  // ID
        Tabla1.getColumnModel().getColumn(1).setPreferredWidth(100); // Fecha
        Tabla1.getColumnModel().getColumn(2).setPreferredWidth(80);  // Monto
        Tabla1.getColumnModel().getColumn(3).setPreferredWidth(200); // Descripción
        Tabla1.getColumnModel().getColumn(4).setPreferredWidth(150); // Categoría
    }

    public void aplicarTema() {
        if (TemaManager.getInstance().isOscuro()) {
            Color fondo = new Color(18, 18, 28);
            Color primario = new Color(40, 60, 150);
            Color texto = Color.WHITE;

            jPanel2.setBackground(fondo);
            jPanel3.setBackground(fondo);
            txtbuscar.setBackground(fondo);
            txtbuscar.setForeground(texto);
            txtbuscar.setColorIcon(texto);
            txtbuscar.setPhColor(Color.LIGHT_GRAY);

            Tabla1.setBackgoundHead(primario);
            Tabla1.setForegroundHead(Color.WHITE);
            Tabla1.setBackgoundHover(new Color(40, 50, 90));
            Tabla1.setForegroundHover(Color.WHITE);
            Tabla1.setColorPrimary(fondo);
            Tabla1.setColorPrimaryText(texto);
            Tabla1.setColorSecondary(fondo.darker());
            Tabla1.setColorSecundaryText(texto);
            Tabla1.setColorBorderHead(primario);
            Tabla1.setColorBorderRows(fondo.darker());
            Tabla1.setFontHead(new Font("Segoe UI", Font.BOLD, 12));
            Tabla1.setFontRowHover(new Font("Segoe UI", Font.PLAIN, 12));
            Tabla1.setFontRowSelect(new Font("Segoe UI", Font.PLAIN, 12));
            Tabla1.setEffectHover(true);

            btnNuevo.setBackground(new Color(46, 49, 82));
            btnGuardar.setBackground(new Color(46, 49, 82));
            btnEliminar.setBackground(new Color(46, 49, 82));
        } else {
            Color fondo = new Color(242, 247, 255);
            Color texto = Color.BLACK;
            Color primario = new Color(72, 92, 188);

            jPanel2.setBackground(fondo);
            jPanel3.setBackground(fondo);
            txtbuscar.setBackground(fondo);
            txtbuscar.setForeground(texto);
            txtbuscar.setColorIcon(texto);
            txtbuscar.setPhColor(Color.GRAY);

            Tabla1.setBackgoundHead(primario);
            Tabla1.setForegroundHead(Color.WHITE);
            Tabla1.setBackgoundHover(new Color(220, 220, 240));
            Tabla1.setForegroundHover(texto);
            Tabla1.setColorPrimary(fondo);
            Tabla1.setColorPrimaryText(texto);
            Tabla1.setColorSecondary(new Color(245, 245, 245));
            Tabla1.setColorSecundaryText(texto);
            Tabla1.setColorBorderHead(primario);
            Tabla1.setColorBorderRows(new Color(200, 200, 200));
            Tabla1.setFontHead(new Font("Segoe UI", Font.BOLD, 12));
            Tabla1.setFontRowHover(new Font("Segoe UI", Font.PLAIN, 12));
            Tabla1.setFontRowSelect(new Font("Segoe UI", Font.PLAIN, 12));
            Tabla1.setEffectHover(true);

            btnNuevo.setBackground(new Color(46, 49, 82));
            btnGuardar.setBackground(new Color(46, 49, 82));
            btnEliminar.setBackground(new Color(46, 49, 82));
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

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        txtbuscar = new RSMaterialComponent.RSTextFieldMaterialIcon();
        btnNuevo = new rojeru_san.RSButtonRiple();
        btnGuardar = new rojeru_san.RSButtonRiple();
        btnEliminar = new rojeru_san.RSButtonRiple();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tabla1 = new RSMaterialComponent.RSTableMetro();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(1250, 630));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtbuscar.setBackground(new java.awt.Color(245, 245, 245));
        txtbuscar.setForeground(new java.awt.Color(29, 30, 91));
        txtbuscar.setColorIcon(new java.awt.Color(29, 30, 111));
        txtbuscar.setColorMaterial(new java.awt.Color(29, 30, 111));
        txtbuscar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SEARCH);
        txtbuscar.setPlaceholder("Buscar");
        txtbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbuscarActionPerformed(evt);
            }
        });
        jPanel3.add(txtbuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 430, 40));

        btnNuevo.setBackground(new java.awt.Color(46, 49, 82));
        btnNuevo.setText(" Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel3.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 20, 120, 40));

        btnGuardar.setBackground(new java.awt.Color(46, 49, 82));
        btnGuardar.setText("Editar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel3.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 20, 120, 40));

        btnEliminar.setBackground(new java.awt.Color(46, 49, 82));
        btnEliminar.setText(" Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel3.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 20, 120, 40));

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));

        Tabla1.setForeground(new java.awt.Color(255, 255, 255));
        Tabla1.setAlignmentX(0.1F);
        Tabla1.setAlignmentY(0.1F);
        Tabla1.setBackgoundHead(new java.awt.Color(46, 49, 82));
        Tabla1.setBackgoundHover(new java.awt.Color(46, 49, 82));
        Tabla1.setColorBorderRows(new java.awt.Color(153, 153, 153));
        Tabla1.setColorPrimaryText(new java.awt.Color(46, 49, 82));
        Tabla1.setColorSecondary(new java.awt.Color(255, 255, 255));
        Tabla1.setColorSecundaryText(new java.awt.Color(46, 49, 82));
        jScrollPane2.setViewportView(Tabla1);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 1160, 490));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1301, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 1301, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 673, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbuscarActionPerformed
        filtrarTabla();
    }//GEN-LAST:event_txtbuscarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        formuEgresos1 dialog = new formuEgresos1(new javax.swing.JFrame(), true);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        cargarTablaEgresos();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        EditEgresos dialog = new EditEgresos(new javax.swing.JFrame(), true);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        cargarTablaEgresos();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int[] selectedRows = Tabla1.getSelectedRows();

        if (selectedRows.length == 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "Por favor seleccione al menos una fila para eliminar",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro que desea eliminar los " + selectedRows.length + " registros seleccionados?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        Ctrl_CajaEgresos ctrl = new Ctrl_CajaEgresos();
        DefaultTableModel model = (DefaultTableModel) Tabla1.getModel();
        int eliminados = 0;

        for (int i = selectedRows.length - 1; i >= 0; i--) {
            int codigo = (int) Tabla1.getValueAt(selectedRows[i], 0);
            if (ctrl.eliminar(codigo)) {
                model.removeRow(selectedRows[i]);
                eliminados++;
            }
        }

        JOptionPane.showMessageDialog(
                this,
                "Se eliminaron " + eliminados + " registros correctamente.",
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE
        );
    }//GEN-LAST:event_btnEliminarActionPerformed
    private void filtrarTabla() {
        String textoBusqueda = txtbuscar.getText().trim();
        DefaultTableModel modelo = (DefaultTableModel) Tabla1.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(modelo);
        Tabla1.setRowSorter(tr);
        List<RowFilter<Object, Object>> filters = new ArrayList<>();

        if (textoBusqueda.isEmpty()) {
            tr.setRowFilter(null);
            return;
        }
        // Expresión regular para detectar si son solo números (1-2 dígitos)
        if (textoBusqueda.matches("\\d+")) {
            // Buscar en ID (columna 0) y fechas (columnas 1 y 2)
            filters.add(RowFilter.regexFilter(textoBusqueda, 0));// ID (coincidencia exacta)
            filters.add(RowFilter.regexFilter(textoBusqueda, 1));
            filters.add(RowFilter.regexFilter(textoBusqueda, 4));

        } // Si contiene letras (aunque sea parcial)
        else {
            // Buscar en Detalle (columna 2) y Categoría (columna 3)
            String regex = "(?i)" + textoBusqueda; // (?i) = ignore case
            filters.add(RowFilter.regexFilter(regex, 2)); // Detalle
            filters.add(RowFilter.regexFilter(regex, 3)); // Categoría
        }

        // Aplicar todos los filtros combinados con OR
        tr.setRowFilter(RowFilter.orFilter(filters));

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RSMaterialComponent.RSTableMetro Tabla1;
    private rojeru_san.RSButtonRiple btnEliminar;
    private rojeru_san.RSButtonRiple btnGuardar;
    private rojeru_san.RSButtonRiple btnNuevo;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private RSMaterialComponent.RSTextFieldMaterialIcon txtbuscar;
    // End of variables declaration//GEN-END:variables
private void Tabla1MouseClicked(java.awt.event.MouseEvent evt) {
        try {
            int column = Tabla1.columnAtPoint(evt.getPoint());
            int viewRow = Tabla1.rowAtPoint(evt.getPoint());

            if (viewRow < 0 || column < 0) {
                return;
            }

            int modelRow = Tabla1.convertRowIndexToModel(viewRow);
            DefaultTableModel model = (DefaultTableModel) Tabla1.getModel();
            int idEgreso = (int) model.getValueAt(modelRow, 0);

            if (column == 5) { // Columna "Ver"
                mostrarDetalleEgreso(model, modelRow, idEgreso);
            } else if (column == 6) { // Columna "Editar"
                abrirEditarEgreso(idEgreso); // Nuevo método para abrir el formulario de edición
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al procesar clic: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void abrirEditarEgreso(int idEgreso) {
        try {
            Ctrl_CajaEgresos ctrl = new Ctrl_CajaEgresos();
            modelo.Caja caja = ctrl.obtenerEgresoPorId(idEgreso); // Obtener datos del egreso

            if (caja != null) {
                // Crear y mostrar el formulario de edición
                EditarEgresos2 dialog = new EditarEgresos2(
                        (JFrame) SwingUtilities.getWindowAncestor(this),
                        true,
                        caja.getId_codigo(),
                        caja.getFecha(),
                        caja.getMonto(),
                        caja.getDescripcion(),
                        caja.getCategoria(),
                        caja.getProveedor(),
                        caja.getProductos(),
                        caja.getCantidad()
                );
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);

                // Recargar la tabla después de editar
                cargarTablaEgresos();
            } else {
                JOptionPane.showMessageDialog(this,
                        "No se encontraron datos para este egreso.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al abrir editor: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void mostrarDetalleEgreso(DefaultTableModel model, int modelRow, int idEgreso) {
        try {
            Ctrl_CajaEgresos ctrl = new Ctrl_CajaEgresos();
            modelo.Caja caja = ctrl.obtenerEgresoPorId(idEgreso);

            if (caja != null) {
                // Preparar los datos con valores por defecto si son nulos
                String fecha = caja.getFecha() != null ? caja.getFecha() : "No especificado";
                String descripcion = caja.getDescripcion() != null ? caja.getDescripcion() : "No especificado";
                String categoria = caja.getCategoria() != null ? caja.getCategoria() : "No especificado";
                String monto = caja.getMonto() == 0.0 ? "No especificado" : String.valueOf(caja.getMonto());
                String proveedor = caja.getProveedor() != null ? caja.getProveedor() : "No especificado";
                String cantidad = caja.getCantidad() != 0 ? String.valueOf(caja.getCantidad()) : "No especificado";
                // Manejar productos (puede ser null o lista vacía)
                String productosStr;
                if (caja.getProductos() == null || caja.getProductos().isEmpty()) {
                    productosStr = "No especificado";
                } else {
                    productosStr = String.join(", ", caja.getProductos());
                }

                DetalleEgreso dialog = new DetalleEgreso(
                        (JFrame) SwingUtilities.getWindowAncestor(this),
                        true,
                        caja.getId_codigo(),
                        fecha,
                        descripcion,
                        categoria,
                        monto,
                        proveedor,
                        productosStr,
                        cantidad
                );
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this,
                        "No se encontraron detalles para este egreso",
                        "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al mostrar detalle: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
