/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista;

import java.awt.Color;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

/**
 *
 * @author pc
 */
public class Produccion extends javax.swing.JPanel {

    /**
     * Creates new form Produccion
     */
    public Produccion() {
        initComponents();

        Tabla1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Tabla1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"#", "ID", "Fecha inicio", "Fecha fin", "Estado"}
        ));

        Tabla1.setCellSelectionEnabled(false);
        Tabla1.setRowSelectionAllowed(true);
        Tabla1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        Tabla2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Tabla2.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"#", "ID", "Cantidad", "Dimensiones", "Materiales"}
        ));

        Tabla2.setCellSelectionEnabled(false);
        Tabla2.setRowSelectionAllowed(true);
        Tabla2.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        Tabla3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Tabla3.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"#", "ID", "Nombre Etapa", "Estado", "Fecha Inicio", "Fecha Fin"}
        ));

        Tabla3.setCellSelectionEnabled(false);
        Tabla3.setRowSelectionAllowed(true);
        Tabla3.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        Color colorSeleccion = new Color(109, 160, 221);
        Color colorTexto = Color.white;

        Tabla1.setSelectionBackground(colorSeleccion);
        Tabla1.setSelectionForeground(colorTexto);

        Tabla2.setSelectionBackground(colorSeleccion);
        Tabla2.setSelectionForeground(colorTexto);

        Tabla3.setSelectionBackground(colorSeleccion);
        Tabla3.setSelectionForeground(colorTexto);
        // Mostrar solo la tabla 1 inicialmente
        jScrollPane2.setVisible(true);
        jScrollPane3.setVisible(false);
        jScrollPane4.setVisible(false);

        this.produccion.setSelected(true);

        // Cargar datos iniciales
        cargarDatosProduccion();

// Agregar DocumentListener a la barra de búsqueda
        rSTextFieldMaterialIcon1.getDocument()
                .addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        filterTables();
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        filterTables();
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        filterTables();
                    }
                });
    }

    public void agregarFilaATabla1(String fechaInicio, String fechaFinal, String estado) {
        actualizarTablaProduccion();

        jScrollPane2.setVisible(true);
        jScrollPane3.setVisible(false);
        jScrollPane4.setVisible(false);
    }

    public int obtenerProduccionSeleccionada() {
        if (Tabla1.getSelectedRow() == -1) {
            return -1;
        }
        int viewRow = Tabla1.getSelectedRow();
        int modelRow = Tabla1.convertRowIndexToModel(viewRow);
        return (int) Tabla1.getModel().getValueAt(modelRow, 1); // Columna del ID
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nuevo = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        rSTextFieldMaterialIcon1 = new RSMaterialComponent.RSTextFieldMaterialIcon();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tabla1 = new RSMaterialComponent.RSTableMetroCustom();
        jScrollPane3 = new javax.swing.JScrollPane();
        Tabla2 = new RSMaterialComponent.RSTableMetroCustom();
        jScrollPane4 = new javax.swing.JScrollPane();
        Tabla3 = new RSMaterialComponent.RSTableMetroCustom();
        btnNuevo = new rojeru_san.RSButtonRiple();
        btnEditar = new rojeru_san.RSButtonRiple();
        btnEliminar = new rojeru_san.RSButtonRiple();
        produccion = new rojeru_san.RSButtonRiple();
        etapaProduccion = new rojeru_san.RSButtonRiple();
        detalleProduccion = new rojeru_san.RSButtonRiple();

        nuevo.setBackground(new java.awt.Color(255, 255, 255));
        nuevo.setPreferredSize(new java.awt.Dimension(1290, 730));
        nuevo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        nuevo.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(368, 111, -1, -1));

        rSTextFieldMaterialIcon1.setBackground(new java.awt.Color(245, 245, 245));
        rSTextFieldMaterialIcon1.setForeground(new java.awt.Color(29, 30, 91));
        rSTextFieldMaterialIcon1.setColorIcon(new java.awt.Color(29, 30, 111));
        rSTextFieldMaterialIcon1.setColorMaterial(new java.awt.Color(29, 30, 111));
        rSTextFieldMaterialIcon1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SEARCH);
        rSTextFieldMaterialIcon1.setPlaceholder("Buscar");
        rSTextFieldMaterialIcon1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSTextFieldMaterialIcon1ActionPerformed(evt);
            }
        });
        nuevo.add(rSTextFieldMaterialIcon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 430, 40));

        Tabla1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Tabla1.setBackgoundHead(new java.awt.Color(29, 30, 51));
        Tabla1.setBackgoundHover(new java.awt.Color(29, 30, 51));
        Tabla1.setColorPrimaryText(new java.awt.Color(0, 0, 0));
        Tabla1.setColorSecundaryText(new java.awt.Color(0, 0, 0));
        Tabla1.setSelectionBackground(new java.awt.Color(29, 30, 91));
        Tabla1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        // Configurar Tabla1 para selección múltiple
        Tabla1.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        Tabla1.setRowSelectionAllowed(true);
        Tabla1.setColumnSelectionAllowed(true); // Solo selección por filas
        jScrollPane2.setViewportView(Tabla1);

        nuevo.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 1220, 520));

        Tabla2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Tabla2.setBackgoundHead(new java.awt.Color(29, 30, 51));
        Tabla2.setBackgoundHover(new java.awt.Color(29, 30, 51));
        Tabla2.setColorPrimaryText(new java.awt.Color(0, 0, 0));
        Tabla2.setColorSecundaryText(new java.awt.Color(0, 0, 0));
        Tabla2.setSelectionBackground(new java.awt.Color(29, 30, 91));
        // Configurar Tabla1 para selección múltiple
        Tabla2.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        Tabla2.setRowSelectionAllowed(true);
        Tabla2.setColumnSelectionAllowed(true); // Solo selección por filas
        jScrollPane3.setViewportView(Tabla2);

        nuevo.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 1220, 520));

        Tabla3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Tabla3.setBackgoundHead(new java.awt.Color(29, 30, 51));
        Tabla3.setBackgoundHover(new java.awt.Color(29, 30, 51));
        Tabla3.setColorPrimaryText(new java.awt.Color(0, 0, 0));
        Tabla3.setColorSecundaryText(new java.awt.Color(0, 0, 0));
        Tabla3.setSelectionBackground(new java.awt.Color(29, 30, 91));
        // Configurar Tabla1 para selección múltiple
        Tabla3.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        Tabla3.setRowSelectionAllowed(true);
        Tabla3.setColumnSelectionAllowed(true); // Solo selección por filas
        jScrollPane4.setViewportView(Tabla3);

        nuevo.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 1220, 520));

        btnNuevo.setBackground(new java.awt.Color(46, 49, 82));
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plus (2).png"))); // NOI18N
        btnNuevo.setText(" Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        nuevo.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 80, 120, 40));

        btnEditar.setBackground(new java.awt.Color(46, 49, 82));
        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pencil (1).png"))); // NOI18N
        btnEditar.setText(" Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        nuevo.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 80, 120, -1));

        btnEliminar.setBackground(new java.awt.Color(46, 49, 82));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/delete (1).png"))); // NOI18N
        btnEliminar.setText(" Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        nuevo.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 80, 120, 40));

        produccion.setBackground(new java.awt.Color(46, 49, 82));
        produccion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gears-set.png"))); // NOI18N
        produccion.setText("  Produccion");
        produccion.setFont(new java.awt.Font("Roboto Bold", 1, 15)); // NOI18N
        produccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                produccionActionPerformed(evt);
            }
        });
        nuevo.add(produccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 180, 40));

        etapaProduccion.setBackground(new java.awt.Color(46, 49, 82));
        etapaProduccion.setBorder(null);
        etapaProduccion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/wrench.png"))); // NOI18N
        etapaProduccion.setText(" Etapas produccion");
        etapaProduccion.setFont(new java.awt.Font("Roboto Bold", 1, 15)); // NOI18N
        etapaProduccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                etapaProduccionActionPerformed(evt);
            }
        });
        nuevo.add(etapaProduccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, 180, 40));

        detalleProduccion.setBackground(new java.awt.Color(46, 49, 82));
        detalleProduccion.setBorder(null);
        detalleProduccion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ajuste.png"))); // NOI18N
        detalleProduccion.setText("Detalle produccion");
        detalleProduccion.setFont(new java.awt.Font("Roboto Bold", 1, 15)); // NOI18N
        detalleProduccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detalleProduccionActionPerformed(evt);
            }
        });
        nuevo.add(detalleProduccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, 180, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1501, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(nuevo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1501, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 742, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(nuevo, javax.swing.GroupLayout.DEFAULT_SIZE, 742, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void rSTextFieldMaterialIcon1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSTextFieldMaterialIcon1ActionPerformed
filterTables();        
    }//GEN-LAST:event_rSTextFieldMaterialIcon1ActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        tresProduccion dialog = new tresProduccion(new javax.swing.JFrame(), true, this);
        dialog.setVisible(true);


    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed


    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // Determinar qué tabla está visible
        JTable tablaActiva = determinarTablaActiva();

        if (tablaActiva == null) {
            JOptionPane.showMessageDialog(this,
                    "No hay tabla activa visible",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int[] selectedRows = tablaActiva.getSelectedRows();

        if (selectedRows.length == 0) {
            JOptionPane.showMessageDialog(this,
                    "Por favor seleccione al menos una fila para eliminar",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Confirmación con el usuario
        int confirm = JOptionPane.showConfirmDialog(this,
                "<html><b>¿Confirmar eliminación?</b><br>Se eliminarán " + selectedRows.length + " registros.</html>",
                "Confirmar Eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            // Obtener datos para eliminar
            List<Object[]> datosEliminar = obtenerDatosParaEliminar(tablaActiva, selectedRows);

            // Eliminar de la base de datos
            eliminarDeBaseDeDatos(tablaActiva, datosEliminar);

            // Eliminar de la tabla
            eliminarFilasDeTabla(tablaActiva, selectedRows);

            // Actualizar numeración si es necesario
            if (tablaActiva == Tabla1) {
                actualizarNumeracionFilas();
            }

            JOptionPane.showMessageDialog(this,
                    "Se eliminaron " + selectedRows.length + " registros exitosamente",
                    "Operación Exitosa",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void produccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_produccionActionPerformed
        jScrollPane3.setVisible(false);
        jScrollPane4.setVisible(false);
        jScrollPane2.setVisible(true);
        this.produccion.setSelected(true);
        this.etapaProduccion.setSelected(false);
        this.detalleProduccion.setSelected(false);

    }//GEN-LAST:event_produccionActionPerformed

    private void etapaProduccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_etapaProduccionActionPerformed

        jScrollPane2.setVisible(false);
        jScrollPane3.setVisible(false);
        jScrollPane4.setVisible(true);
        this.produccion.setSelected(false);
        this.etapaProduccion.setSelected(true);
        this.detalleProduccion.setSelected(false);

    }//GEN-LAST:event_etapaProduccionActionPerformed

    private void detalleProduccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detalleProduccionActionPerformed

        jScrollPane2.setVisible(false);
        jScrollPane4.setVisible(false);
        jScrollPane3.setVisible(true);
        this.produccion.setSelected(false);
        this.etapaProduccion.setSelected(false);
        this.detalleProduccion.setSelected(true);
    }//GEN-LAST:event_detalleProduccionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RSMaterialComponent.RSTableMetroCustom Tabla1;
    private RSMaterialComponent.RSTableMetroCustom Tabla2;
    private RSMaterialComponent.RSTableMetroCustom Tabla3;
    private rojeru_san.RSButtonRiple btnEditar;
    private rojeru_san.RSButtonRiple btnEliminar;
    private rojeru_san.RSButtonRiple btnNuevo;
    private rojeru_san.RSButtonRiple detalleProduccion;
    private rojeru_san.RSButtonRiple etapaProduccion;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel nuevo;
    private rojeru_san.RSButtonRiple produccion;
    private RSMaterialComponent.RSTextFieldMaterialIcon rSTextFieldMaterialIcon1;
    // End of variables declaration//GEN-END:variables

    private int generateRandomId() {
        Random random = new Random();
        return 10000 + random.nextInt(90000); // Genera un número entre 10000 y 99999
    }

    private void filterTables() {
        String searchText = rSTextFieldMaterialIcon1.getText().toLowerCase(); // Obtener el texto de búsqueda

        // Filtrar cada tabla
        if (searchText.length() == 1) {
            // Filtrar por la columna "#" (índice 0)
            filterTable(Tabla1, searchText, 0); // Cambia 0 por el índice de la columna "#" en Tabla1
            filterTable(Tabla2, searchText, 0); // Cambia 0 por el índice de la columna "#" en Tabla2
            filterTable(Tabla3, searchText, 0); // Cambia 0 por el índice de la columna "#" en Tabla3

        } else if (searchText.length() >= 2) {
            // Filtrar en todas las columnas
            filterTable(Tabla1, searchText, -1); // Mostrar todas las filas en Tabla1
            filterTable(Tabla2, searchText, -1); // Mostrar todas las filas en Tabla2
            filterTable(Tabla3, searchText, -1); // Mostrar todas las filas en Tabla3
        } else {
            // Si no hay texto, mostrar todas las filas
            filterTable(Tabla1, "", -1); // Mostrar todas las filas en Tabla1
            filterTable(Tabla2, "", -1); // Mostrar todas las filas en Tabla2
            filterTable(Tabla3, "", -1); // Mostrar todas las filas en Tabla3
        }
    }

    private void filterTable(JTable table, String searchText, int columnIndex) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        if (searchText.trim().isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            if (columnIndex == -1) {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + Pattern.quote(searchText)));
            } else {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + Pattern.quote(searchText), columnIndex));
            }
        }
    }

    private void dispose() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void insertarDetalleEnBD(int cant, String dimension, String material) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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

    public void cargarDatosProduccion() {
        cargarTablaProduccion();    // Carga Tabla1 (producción)
        cargarTablaDetalle();       // Carga Tabla2 (detalle_produccion)
        cargarTablaEtapa();         // Carga Tabla3 (etapa_produccion)
    }

    public void cargarTablaEtapa() {
        try (Connection con = new Conexion().getConnection(); PreparedStatement ps = con.prepareStatement(
                "SELECT idetapa_produccion, nombre_etapa, estado, fecha_inicio, fecha_fin "
                + "FROM etapa_produccion"); ResultSet rs = ps.executeQuery()) {

            DefaultTableModel model = (DefaultTableModel) Tabla3.getModel();
            model.setRowCount(0);

            int rowNum = 1;
            while (rs.next()) {
                model.addRow(new Object[]{
                    rowNum++,
                    rs.getInt("idetapa_produccion"),
                    rs.getString("nombre_etapa"),
                    rs.getString("estado"),
                    rs.getDate("fecha_inicio"),
                    rs.getDate("fecha_fin")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar etapas: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    public void cargarTablaDetalle() {
        try (Connection con = new Conexion().getConnection(); PreparedStatement ps = con.prepareStatement("SELECT * FROM detalle_produccion"); ResultSet rs = ps.executeQuery()) {

            DefaultTableModel model = (DefaultTableModel) Tabla2.getModel();
            model.setRowCount(0);

            int rowNum = 1;
            while (rs.next()) {
                model.addRow(new Object[]{
                    rowNum++,
                    rs.getInt("idetalle_produccion"),
                    rs.getInt("cantidad"),
                    rs.getString("dimensiones"),
                    rs.getString("materiales")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar detalles: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void actualizarTablaProduccion() {
        cargarDatosProduccion();
    }

    public void actualizarTodasLasTablas() {
        cargarTablaProduccion();
        cargarTablaDetalle();
        cargarTablaEtapa();
    }

    private void insertarEnBD(Date fechaInicio, Date fechaFin, String estado) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = new Conexion().getConnection();
            String sql = "INSERT INTO produccion (fecha_inicio, fecha_fin, estado) VALUES (?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setDate(1, fechaInicio);
            ps.setDate(2, fechaFin); // Corregido: era fecha_final
            ps.setString(3, estado);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al insertar:");
            e.printStackTrace();
        }
    }

    private List<Integer> obtenerIdsSeleccionados(int[] selectedRows) throws Exception {
        DefaultTableModel model = (DefaultTableModel) Tabla1.getModel();
        TableRowSorter<DefaultTableModel> sorter = (TableRowSorter<DefaultTableModel>) Tabla1.getRowSorter();
        List<Integer> ids = new ArrayList<>();

        for (int i = selectedRows.length - 1; i >= 0; i--) {
            int viewRow = selectedRows[i];
            int modelRow = (sorter != null) ? sorter.convertRowIndexToModel(viewRow) : viewRow;

            try {
                Object idObj = model.getValueAt(modelRow, 1); // Columna del ID
                if (idObj == null) {
                    throw new Exception("El ID en la fila " + (viewRow + 1) + " es nulo");
                }
                int id = Integer.parseInt(idObj.toString());
                ids.add(id);
            } catch (NumberFormatException e) {
                throw new Exception("ID inválido en la fila " + (viewRow + 1));
            }
        }
        return ids;
    }

    private void insertarEtapaEnBD(String nombreEtapa, String estado, Date fechaInicio, Date fechaFin) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = new Conexion().getConnection();
            String sql = "INSERT INTO etapa_produccion (nombre_etapa, estado, fecha_inicio, fecha_fin) "
                    + "VALUES (?, ?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, nombreEtapa);
            ps.setString(2, estado);
            ps.setDate(3, fechaInicio);
            ps.setDate(4, fechaFin);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al insertar etapa:");
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos:");
                e.printStackTrace();
            }
        }
    }

    public void cargarTablaProduccion() {
        try (Connection con = new Conexion().getConnection(); PreparedStatement ps = con.prepareStatement("SELECT id_produccion, fecha_inicio, fecha_fin, estado FROM produccion"); ResultSet rs = ps.executeQuery()) {

            DefaultTableModel model = (DefaultTableModel) Tabla1.getModel();
            model.setRowCount(0);

            int rowNum = 1;
            while (rs.next()) {
                model.addRow(new Object[]{
                    rowNum++,
                    rs.getInt("id_produccion"),
                    rs.getDate("fecha_inicio"),
                    rs.getDate("fecha_fin"),
                    rs.getString("estado"),
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

    private void eliminarProduccion(List<Object[]> datos) throws SQLException {
        List<Integer> ids = new ArrayList<>();
        for (Object[] fila : datos) {
            ids.add((Integer) fila[1]); // Asumiendo que el ID está en la columna 1
        }

        try (Connection con = new Conexion().getConnection(); PreparedStatement ps = con.prepareStatement(
                "DELETE FROM produccion WHERE id_produccion = ?")) {

            for (Integer id : ids) {
                ps.setInt(1, id);
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void eliminarDetalleProduccion(List<Object[]> datos) throws SQLException {
        try (Connection con = new Conexion().getConnection(); PreparedStatement ps = con.prepareStatement("DELETE FROM detalle_produccion WHERE id_detalle = ?")) {

            for (Object[] fila : datos) {
                ps.setInt(1, (Integer) fila[1]);
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void eliminarEtapaProduccion(List<Object[]> datos) throws SQLException {
        try (Connection con = new Conexion().getConnection(); PreparedStatement ps = con.prepareStatement("DELETE FROM etapa_produccion WHERE idetapa_produccion = ?")) {

            for (Object[] fila : datos) {
                ps.setInt(1, (Integer) fila[1]);
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void actualizarNumeracionFilas() {
        DefaultTableModel model = (DefaultTableModel) Tabla1.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            model.setValueAt(i + 1, i, 0); // Actualizar columna #
        }
    }

    private JTable determinarTablaActiva() {
        if (jScrollPane2.isVisible()) {
            return Tabla1;
        }
        if (jScrollPane3.isVisible()) {
            return Tabla2;
        }
        if (jScrollPane4.isVisible()) {
            return Tabla3;
        }
        return null;
    }

    private List<Object[]> obtenerDatosParaEliminar(JTable tabla, int[] selectedRows) {
        List<Object[]> datos = new ArrayList<>();
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        TableRowSorter<DefaultTableModel> sorter = (TableRowSorter<DefaultTableModel>) tabla.getRowSorter();

        for (int viewRow : selectedRows) {
            int modelRow = (sorter != null) ? sorter.convertRowIndexToModel(viewRow) : viewRow;
            Object[] rowData = new Object[model.getColumnCount()];
            for (int col = 0; col < model.getColumnCount(); col++) {
                rowData[col] = model.getValueAt(modelRow, col);
            }
            datos.add(rowData);
        }
        return datos;
    }

    private void eliminarDeBaseDeDatos(JTable tabla, List<Object[]> datos) throws SQLException {
        if (tabla == Tabla1) {
            eliminarProduccion(datos);
        } else if (tabla == Tabla2) {
            eliminarDetalleProduccion(datos);
        } else if (tabla == Tabla3) {
            eliminarEtapaProduccion(datos);
        }
    }

    private void eliminarFilasDeTabla(JTable tabla, int[] selectedRows) {
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        TableRowSorter<DefaultTableModel> sorter = (TableRowSorter<DefaultTableModel>) tabla.getRowSorter();

        // Eliminar en orden inverso
        for (int i = selectedRows.length - 1; i >= 0; i--) {
            int viewRow = selectedRows[i];
            int modelRow = (sorter != null) ? sorter.convertRowIndexToModel(viewRow) : viewRow;
            model.removeRow(modelRow);
        }
    }

    public void agregarFilaATabla2(String cantidad, String dimension, String material) {
        // Insertar en la base de datos
        try {
            int cant = Integer.parseInt(cantidad);
            insertarDetalleEnBD(cant, dimension, material);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Cantidad debe ser un número", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Actualizar la tabla
        DefaultTableModel model = (DefaultTableModel) Tabla2.getModel();
        int newRowNum = model.getRowCount() + 1;
        model.addRow(new Object[]{
            newRowNum,
            generateRandomId(),
            cantidad,
            dimension,
            material
        });

    }

    public void agregarFilaATabla3(String nombreEtapa, String estado, Date fechaInicio, Date fechaFin) {
        // Insertar en la base de datos
        insertarEtapaEnBD(nombreEtapa, estado, fechaInicio, fechaFin);

        // Actualizar la tabla
        DefaultTableModel model = (DefaultTableModel) Tabla3.getModel();
        int newRowNum = model.getRowCount() + 1;
        model.addRow(new Object[]{
            newRowNum,
            generateRandomId(),
            nombreEtapa,
            estado,
            fechaInicio,
            fechaFin

        });

    }
}
