/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista.Caja;

import controlador.Ctrl_CajaEgresos;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import modelo.Caja;
import vista.TemaManager;

/**
 *
 * @author ADSO
 */
public final class Egresos extends javax.swing.JPanel {

    /**
     * Creates new form CajaContenido
     */
    public Egresos() {
        initComponents();
        aplicarTema();
        

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
        
        TemaManager.getInstance().addThemeChangeListener(() -> {
            aplicarTema(); // Update theme when it changes
        });

        
        cargarTablaEgresos();
    }

    
    
    public void aplicarTema() {
        boolean oscuro = TemaManager.getInstance().isOscuro();
        System.out.println("Aplicando tema en Cotizacion: " + (oscuro ? "Oscuro" : "Claro"));

        if (oscuro) {
            // Configuración para modo oscuro
            Color fondo = new Color(21, 21, 33);
            Color fondoTabla = new Color(30, 30, 45);
            Color encabezado = new Color(67, 71, 120);
            Color texto = Color.WHITE;
            Color borde = new Color(60, 60, 80);

            // Panel raíz y panel principal
            setBackground(fondo);
            jPanel2.setBackground(fondo);
            jPanel3.setBackground(fondo);

            // Tabla
            Tabla1.setBackground(fondoTabla);
            Tabla1.setForeground(texto);
            Tabla1.setColorPrimary(new Color(37, 37, 52));  // Filas impares
            Tabla1.setColorSecondary(new Color(30, 30, 45)); // Filas pares
            Tabla1.setColorPrimaryText(texto);
            Tabla1.setColorSecundaryText(texto);
            Tabla1.setBackgoundHead(encabezado);
            Tabla1.setForegroundHead(texto);
            Tabla1.setColorBorderHead(encabezado);
            Tabla1.setColorBorderRows(borde);
            Tabla1.setGridColor(borde);

           
           jLabel1.setForeground(Color.white);

            txtbuscar.setBackground(new Color(37, 37, 52));
            txtbuscar.setForeground(texto);
            txtbuscar.setColorMaterial(texto);
            txtbuscar.setPhColor(Color.LIGHT_GRAY);
            txtbuscar.setSelectionColor(new Color(118, 142, 240));

            // Botones
            btnNuevoProduc.setBackground(encabezado);
            btnNuevoProduc.setForeground(texto);

            btnEliminar.setBackground(encabezado);
            btnEliminar.setForeground(texto);

        

            // Fondo (JLabel, probablemente para una imagen de fondo)
            /*
        fondo.setBackground(fondo);
        fondo.setOpaque(false); // Asegurar que no interfiera con el fondo del panel*/
        } else {
            // Configuración para modo claro
            Color fondo = new Color(242, 247, 255);
            Color texto = Color.BLACK;
            Color primario = new Color(72, 92, 188);
            Color borde = new Color(0, 0, 0);

            // Panel raíz y panel principal
            setBackground(fondo);
            jPanel2.setBackground(fondo);

            // Tabla
            Tabla1.setBackground(new Color(255, 255, 255));
            Tabla1.setBackgoundHead(new Color(46, 49, 82));
            Tabla1.setForegroundHead(Color.WHITE);
            Tabla1.setBackgoundHover(new Color(67, 150, 209));
            Tabla1.setColorPrimary(new Color(242, 242, 242));
            Tabla1.setColorPrimaryText(texto);
            Tabla1.setColorSecondary(new Color(255, 255, 255));
            Tabla1.setColorSecundaryText(texto);
            Tabla1.setColorBorderHead(primario);
            Tabla1.setColorBorderRows(borde);
            Tabla1.setGridColor(Color.WHITE);

         
            // Campos de texto
            txtbuscar.setBackground(fondo);
            txtbuscar.setForeground(texto);
            txtbuscar.setColorMaterial(primario);
            txtbuscar.setPhColor(Color.GRAY);
            txtbuscar.setSelectionColor(new Color(67, 150, 209));

            
            // Fondo
            /*
        fondo.setBackground(fondo);
        fondo.setOpaque(false);*/
        }

        // Repintar todos los componentes
        repaint();
        jPanel2.repaint();
        Tabla1.repaint();
        Tabla1.getTableHeader().repaint();
     
    }
    
    
    
    
    
    
   
    public void cargarTablaEgresos() {
        DefaultTableModel model = (DefaultTableModel) Tabla1.getModel();
        model.setRowCount(0); // Limpiar tabla

        Ctrl_CajaEgresos ctrl = new Ctrl_CajaEgresos();
        List<Caja> egresos = ctrl.obtenerEgresos();

        if (egresos.isEmpty()) {
            System.out.println("No se encontraron egresos en la base de datos");
            return;
        }

        for (Caja caja : egresos) {
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
        Tabla1.getColumnModel().getColumn(0).setPreferredWidth(20);  // ID
        Tabla1.getColumnModel().getColumn(1).setPreferredWidth(100); // Fecha
        Tabla1.getColumnModel().getColumn(2).setPreferredWidth(80);  // Monto
        Tabla1.getColumnModel().getColumn(3).setPreferredWidth(200); // Descripción
        Tabla1.getColumnModel().getColumn(4).setPreferredWidth(300); // Categoría
        Tabla1.getColumnModel().getColumn(5).setPreferredWidth(40); // ver
        Tabla1.getColumnModel().getColumn(6).setPreferredWidth(50); // editar

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
        btnNuevoProduc = new rojeru_san.RSButtonRiple();
        btnEliminar = new rojeru_san.RSButtonRiple();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Tabla1 = new RSMaterialComponent.RSTableMetroCustom();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(1250, 630));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtbuscar.setBackground(new java.awt.Color(245, 245, 245));
        txtbuscar.setForeground(new java.awt.Color(0, 0, 0));
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

        btnNuevoProduc.setBackground(new java.awt.Color(46, 49, 82));
        btnNuevoProduc.setText(" Nuevo");
        btnNuevoProduc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoProducActionPerformed(evt);
            }
        });
        jPanel3.add(btnNuevoProduc, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 20, 120, 40));

        btnEliminar.setBackground(new java.awt.Color(46, 49, 82));
        btnEliminar.setText(" Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel3.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 20, 120, 40));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        jLabel1.setText("Egresos");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 20, -1, -1));

        Tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id Registro", "Fecha Pago", "Detalle", "Categoria", "Cantidad ingresada"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Tabla1.setBackgoundHead(new java.awt.Color(46, 49, 82));
        Tabla1.setBackgoundHover(new java.awt.Color(109, 160, 221));
        Tabla1.setBorderHead(null);
        Tabla1.setBorderRows(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        Tabla1.setColorBorderHead(new java.awt.Color(46, 49, 82));
        Tabla1.setColorBorderRows(new java.awt.Color(46, 49, 82));
        Tabla1.setColorPrimaryText(new java.awt.Color(0, 0, 0));
        Tabla1.setColorSecondary(new java.awt.Color(255, 255, 255));
        Tabla1.setColorSecundaryText(new java.awt.Color(0, 0, 0));
        Tabla1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Tabla1.setFontHead(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Tabla1.setFontRowHover(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Tabla1.setFontRowSelect(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Tabla1.setPreferredSize(new java.awt.Dimension(375, 433));
        Tabla1.setRowHeight(23);
        Tabla1.setSelectionBackground(new java.awt.Color(109, 160, 221));
        Tabla1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tabla1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(Tabla1);
        Tabla1.getColumnModel().getColumn(0).setPreferredWidth(10);

        jPanel3.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 1130, 470));

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
            .addGap(0, 680, Short.MAX_VALUE)
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

    private void btnNuevoProducActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoProducActionPerformed
        formuEgresos1 dialog = new formuEgresos1(new javax.swing.JFrame(), true);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        cargarTablaEgresos();
    }//GEN-LAST:event_btnNuevoProducActionPerformed

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

    private void Tabla1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabla1MouseClicked
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
    }//GEN-LAST:event_Tabla1MouseClicked
    private void abrirEditarEgreso(int idEgreso) {
        try {
            Ctrl_CajaEgresos ctrl = new Ctrl_CajaEgresos();
            Caja caja = ctrl.obtenerEgresoPorId(idEgreso); // Obtener datos del egreso

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
    private RSMaterialComponent.RSTableMetroCustom Tabla1;
    private rojeru_san.RSButtonRiple btnEliminar;
    private rojeru_san.RSButtonRiple btnNuevoProduc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane3;
    private RSMaterialComponent.RSTextFieldMaterialIcon txtbuscar;
    // End of variables declaration//GEN-END:variables

    private void mostrarDetalleEgreso(DefaultTableModel model, int modelRow, int idEgreso) {
        try {
            Ctrl_CajaEgresos ctrl = new Ctrl_CajaEgresos();
            Caja caja = ctrl.obtenerEgresoPorId(idEgreso);

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
