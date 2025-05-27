/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista.Produccion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import modelo.Conexion;

/**
 *
 * @author pc
 */
public final class ProduccionContEtapa extends javax.swing.JPanel {

    private final int idProduccion;

    /**
     * Creates new form ProduccionContDetalle
     *
     * @param idProduccion
     */
    public ProduccionContEtapa(int idProduccion) {
        System.out.println("ID recibido en constructor: " + idProduccion);
        this.idProduccion = idProduccion;
        initComponents();

        Tabla1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Tabla1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"Id", "Nombre", "Cantidad", "Fecha inicio", "Fecha final", "Estado", "Asignado", "Detalles", "Editar", "materiales", "herramientas"}
        ));
        // Oculta las columnas adicionales después de establecer el modelo
        Tabla1.removeColumn(Tabla1.getColumnModel().getColumn(0)); // Oculta id
        Tabla1.removeColumn(Tabla1.getColumnModel().getColumn(8)); // Oculta materiales
        Tabla1.removeColumn(Tabla1.getColumnModel().getColumn(8)); // Oculta herramienta

        Tabla1.getColumnModel().getColumn(4).setCellRenderer(new EstadoTableCellRenderer());

        Tabla1.getColumnModel().getColumn(6).setCellRenderer(new VerTableCellRenderer());

        Tabla1.getColumnModel().getColumn(7).setCellRenderer(new EditarTableCellRenderer());

        Tabla1.setCellSelectionEnabled(false);
        Tabla1.setRowSelectionAllowed(true);
        Tabla1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        cargarTablaEtapa();    // Carga Tabla1
    }

    private class EditarTableCellRenderer extends DefaultTableCellRenderer {

        private final Color textColor = new Color(46, 49, 82);
        private final Font fontNormal = new Font("Tahoma", Font.PLAIN, 14);
        private final Font fontBold = new Font("Tahoma", Font.BOLD, 14);

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            c.setForeground(isSelected ? Color.WHITE : Color.BLACK);
            c.setBackground(isSelected ? table.getSelectionBackground() : Color.WHITE);
            c.setFont(isSelected ? fontBold : fontNormal);

            setHorizontalAlignment(CENTER);
            setText("Editar");

            setBorder(BorderFactory.createLineBorder(new Color(153, 153, 153), 1));
            Tabla1.setRowHeight(23);
            return c;
        }
    }
    // Renderizador para la columna de estado

    private void mostrarDetalleEtapa(DefaultTableModel model, int modelRow, int idEtapa) {
        try {
            // Obtener datos de la fila seleccionada
            String nombre = model.getValueAt(modelRow, 1).toString();
            String cantidad = String.valueOf(model.getValueAt(modelRow, 2));
            String fechaInicio = model.getValueAt(modelRow, 3).toString();
            String fechaFin = model.getValueAt(modelRow, 4).toString();
            String estado = model.getValueAt(modelRow, 5).toString();
            String asignado = model.getValueAt(modelRow, 6) != null
                    ? model.getValueAt(modelRow, 6).toString() : "No asignado";
            String materiales = model.getColumnCount() > 8 && model.getValueAt(modelRow, 8) != null
                    ? model.getValueAt(modelRow, 8).toString() : "No especificado";
            String herramientas = model.getColumnCount() > 9 && model.getValueAt(modelRow, 9) != null
                    ? model.getValueAt(modelRow, 9).toString() : "No especificado";

            // Crear el diálogo
            DetallleEtapa dialog = new DetallleEtapa(
                    (JFrame) SwingUtilities.getWindowAncestor(this),
                    true,
                    idEtapa,
                    nombre,
                    cantidad,
                    fechaInicio,
                    fechaFin,
                    estado,
                    materiales,
                    herramientas,
                    asignado
            );

            // Centrar y mostrar el diálogo
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al mostrar detalle: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void editarProduccion(DefaultTableModel model, int modelRow, int idEtapa) {
        try {

            String nombre = model.getValueAt(modelRow, 1).toString();
            String cantidad = String.valueOf(model.getValueAt(modelRow, 2));
            String fechaInicio = model.getValueAt(modelRow, 3).toString();
            String fechaFin = model.getValueAt(modelRow, 4).toString();
            String estado = model.getValueAt(modelRow, 5).toString();
            String asignado = model.getValueAt(modelRow, 6) != null ? model.getValueAt(modelRow, 6).toString() : "No asignado";
            String materiales = model.getColumnCount() > 8 && model.getValueAt(modelRow, 8) != null
                    ? model.getValueAt(modelRow, 8).toString() : "No especificado";
            String herramientas = model.getColumnCount() > 9 && model.getValueAt(modelRow, 9) != null
                    ? model.getValueAt(modelRow, 9).toString() : "No especificado";

            // Debug: Verifica los valores
            System.out.println("Datos a enviar:");
            System.out.println("ID: " + idEtapa);
            System.out.println("Nombre: " + nombre);
            System.out.println("Cantidad: " + cantidad);
            System.out.println("Fecha inicio: " + fechaInicio);
            System.out.println("Fecha fin: " + fechaFin);
            System.out.println("Estado: " + estado);
            System.out.println("Asignado: " + asignado);
            System.out.println("Materiales: " + materiales);
            System.out.println("Herramientas: " + herramientas);

            // Crear y mostrar el panel
            DetallleEtapa detallePanel = new DetallleEtapa(
                    (JFrame) SwingUtilities.getWindowAncestor(this), true, idEtapa,
                    nombre,
                    cantidad,
                    fechaInicio,
                    fechaFin,
                    estado,
                    materiales,
                    herramientas,
                    asignado
            );

            removeAll();
            setLayout(new BorderLayout());
            add(detallePanel, BorderLayout.CENTER);
            revalidate();
            repaint();

        } catch (Exception e) {
            throw new RuntimeException("Error al mostrar detalle: " + e.getMessage(), e);
        }
    }

    private class EstadoTableCellRenderer extends DefaultTableCellRenderer {

        private final Color textColor = new Color(46, 49, 82);
        private final Font fontNormal = new Font("Tahoma", Font.PLAIN, 14);
        private final Font fontBold = new Font("Tahoma", Font.BOLD, 14);

        public EstadoTableCellRenderer() {
            setHorizontalAlignment(JLabel.CENTER); // Centrar el texto
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            // Llamar al método padre primero
            JLabel label = (JLabel) super.getTableCellRendererComponent(
                    table, value, isSelected, hasFocus, row, column);

            label.setHorizontalAlignment(CENTER);
            label.setText(value != null ? value.toString() : "");

            if (isSelected) {
                // Cuando está seleccionado, texto blanco y fondo de selección
                label.setForeground(Color.WHITE);
                label.setBackground(table.getSelectionBackground());
                label.setFont(fontBold);
            } else {
                // Cuando no está seleccionado, mantener el color original del texto
                label.setForeground(textColor);
                label.setFont(fontNormal);

                // Aplicar colores de fondo según el estado
                String estado = value != null ? value.toString() : "";
                switch (estado.toLowerCase()) {
                    case "pendiente":
                        label.setBackground(new Color(255, 204, 204)); // Rojo claro
                        break;
                    case "proceso":
                        label.setBackground(new Color(255, 255, 153)); // Amarillo claro
                        break;
                    case "completado":
                        label.setBackground(new Color(204, 255, 204)); // Verde claro
                        break;
                    default:
                        label.setBackground(Color.WHITE);
                        break;
                }
            }

            // Borde igual al resto de la tabla
            label.setBorder(BorderFactory.createLineBorder(new Color(153, 153, 153), 1));
            Tabla1.setRowHeight(23); // Altura más delgada para las filas
            return label;
        }

    }
// Renderizador para la columna "Ver"

    private class VerTableCellRenderer extends DefaultTableCellRenderer {

        private final Color textColor = new Color(46, 49, 82); // Color de texto normal
        private final Font fontNormal = new Font("Tahoma", Font.PLAIN, 14);
        private final Font fontBold = new Font("Tahoma", Font.BOLD, 14);

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            c.setForeground(isSelected ? Color.WHITE : Color.BLACK);
            c.setBackground(isSelected ? table.getSelectionBackground() : Color.WHITE);
            c.setFont(isSelected ? fontBold : fontNormal);

            setHorizontalAlignment(CENTER);
            setText("Ver");

            // Bordes iguales al resto
            setBorder(BorderFactory.createLineBorder(new Color(153, 153, 153), 1));
            Tabla1.setRowHeight(23); // Altura más delgada para las filas
            return c;
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

        txtbuscar = new RSMaterialComponent.RSTextFieldMaterialIcon();
        btnNuevo = new RSMaterialComponent.RSButtonShape();
        btnElimi = new RSMaterialComponent.RSButtonShape();
        jScrollPane3 = new javax.swing.JScrollPane();
        Tabla1 = new RSMaterialComponent.RSTableMetroCustom();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1150, 510));
        setMinimumSize(new java.awt.Dimension(1150, 510));
        setPreferredSize(new java.awt.Dimension(1150, 510));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        add(txtbuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 22, 430, 40));

        btnNuevo.setBackground(new java.awt.Color(46, 49, 82));
        btnNuevo.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plus (1).png"))); // NOI18N
        btnNuevo.setText("  Nuevo");
        btnNuevo.setBackgroundHover(new java.awt.Color(67, 150, 209));
        btnNuevo.setFont(new java.awt.Font("Roboto Bold", 1, 18)); // NOI18N
        btnNuevo.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 20, 120, 40));

        btnElimi.setBackground(new java.awt.Color(46, 49, 82));
        btnElimi.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        btnElimi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/delete (1).png"))); // NOI18N
        btnElimi.setText(" Eliminar");
        btnElimi.setBackgroundHover(new java.awt.Color(67, 150, 209));
        btnElimi.setFont(new java.awt.Font("Roboto Bold", 1, 18)); // NOI18N
        btnElimi.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        btnElimi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnElimiActionPerformed(evt);
            }
        });
        add(btnElimi, new org.netbeans.lib.awtextra.AbsoluteConstraints(1024, 20, 120, 40));

        Tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Cantidad", "Fecha inicio", "Fecha final", "Estado", "Material", "Herramienta", "Asignado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
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
        Tabla1.setRowHeight(23);
        Tabla1.setSelectionBackground(new java.awt.Color(109, 160, 221));
        Tabla1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tabla1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(Tabla1);
        Tabla1.getColumnModel().getColumn(0).setPreferredWidth(10);

        add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 1120, 260));
    }// </editor-fold>//GEN-END:initComponents

    private void txtbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbuscarActionPerformed
        filtrarTabla();

    }//GEN-LAST:event_txtbuscarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        FormuEtapaProduccion dialog = new FormuEtapaProduccion(
                (JFrame) SwingUtilities.getWindowAncestor(this),
                true,
                this.idProduccion
        );
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        cargarTablaEtapa(); // Refrescar la tabla después de cerrar el diálogo
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnElimiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnElimiActionPerformed
    int selectedRow = Tabla1.getSelectedRow();
    
    if (selectedRow < 0) {
        JOptionPane.showMessageDialog(this,
                "Seleccione una etapa para eliminar",
                "Advertencia",
                JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    // Convertir índice de vista a modelo (importante si hay filtros)
    int modelRow = Tabla1.convertRowIndexToModel(selectedRow);
    int idEtapa = (int) Tabla1.getModel().getValueAt(modelRow, 0);
    
    // Verificar estado (opcional: no permitir eliminar etapas completadas)
    String estado = Tabla1.getModel().getValueAt(modelRow, 5).toString();
    if ("completado".equalsIgnoreCase(estado)) {
        JOptionPane.showMessageDialog(this,
                "No se puede eliminar una etapa completada",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    int confirm = JOptionPane.showConfirmDialog(this,
            "¿Está seguro de eliminar esta etapa?",
            "Confirmar eliminación",
            JOptionPane.YES_NO_OPTION);
    
    if (confirm != JOptionPane.YES_OPTION) {
        return;
    }
    
    try (Connection con = Conexion.getConnection()) {
        con.setAutoCommit(false); // Iniciar transacción
        
        try {
            // 1. Eliminar registros en 'utilizado'
            String sqlUtilizado = "DELETE FROM utilizado WHERE etapa_produccion_idetapa_produccion = ?";
            try (PreparedStatement ps = con.prepareStatement(sqlUtilizado)) {
                ps.setInt(1, idEtapa);
                ps.executeUpdate();
            }
            
            // 2. Eliminar registros en 'asignada'
            String sqlAsignada = "DELETE FROM asignada WHERE etapa_produccion_idetapa_produccion = ?";
            try (PreparedStatement ps = con.prepareStatement(sqlAsignada)) {
                ps.setInt(1, idEtapa);
                ps.executeUpdate();
            }
            
            // 3. Finalmente eliminar la etapa
            String sqlEtapa = "DELETE FROM etapa_produccion WHERE idetapa_produccion = ?";
            try (PreparedStatement ps = con.prepareStatement(sqlEtapa)) {
                ps.setInt(1, idEtapa);
                int filasAfectadas = ps.executeUpdate();
                
                if (filasAfectadas > 0) {
                    con.commit(); // Confirmar cambios
                    JOptionPane.showMessageDialog(this,
                            "Etapa eliminada correctamente",
                            "Éxito",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    con.rollback();
                    JOptionPane.showMessageDialog(this,
                            "No se encontró la etapa a eliminar",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException e) {
            con.rollback(); // Revertir en caso de error
            JOptionPane.showMessageDialog(this,
                    "Error al eliminar etapa: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        
        cargarTablaEtapa(); // Refrescar la tabla
        
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this,
                "Error de conexión: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    }//GEN-LAST:event_btnElimiActionPerformed

    private void Tabla1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabla1MouseClicked
            try {
            int column = Tabla1.columnAtPoint(evt.getPoint());
            int viewRow = Tabla1.rowAtPoint(evt.getPoint());

            if (viewRow < 0 || column < 0) {
                return;
            }

            int modelRow = Tabla1.convertRowIndexToModel(viewRow);
            DefaultTableModel model = (DefaultTableModel) Tabla1.getModel();
            int idEtapa = (int) model.getValueAt(modelRow, 0);

            if (column == 6) { // Columna "Ver"
                mostrarDetalleEtapa(model, modelRow, idEtapa);
            } else if (column == 7) { // Columna "Editar"
                editarEtapa(model, modelRow, idEtapa);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al procesar clic: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_Tabla1MouseClicked

    private void editarEtapa(DefaultTableModel model, int modelRow, int idEtapa) {
        try {
            // Obtener datos de la fila
            String nombre = model.getValueAt(modelRow, 1).toString();
            String cantidad = String.valueOf(model.getValueAt(modelRow, 2));
            String fechaInicio = model.getValueAt(modelRow, 3).toString();
            String fechaFin = model.getValueAt(modelRow, 4).toString();
            String estado = model.getValueAt(modelRow, 5).toString();
            String asignado = model.getValueAt(modelRow, 6) != null
                    ? model.getValueAt(modelRow, 6).toString() : "No asignado";
            String materiales = model.getColumnCount() > 9 && model.getValueAt(modelRow, 9) != null
                    ? model.getValueAt(modelRow, 9).toString() : "No especificado";
            String herramientas = model.getColumnCount() > 10 && model.getValueAt(modelRow, 10) != null
                    ? model.getValueAt(modelRow, 10).toString() : "No especificado";

            // Mostrar diálogo de edición
            EditEtapaProduccion dialog = new EditEtapaProduccion(
                    (JFrame) SwingUtilities.getWindowAncestor(this),
                    true,
                    idEtapa
            );

            // Pasar los datos al diálogo
            dialog.setDatos(
                    idEtapa,
                    nombre,
                    cantidad,
                    fechaInicio,
                    fechaFin,
                    estado,
                    materiales,
                    herramientas,
                    asignado
            );

            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);

            // Recargar la tabla si se hicieron cambios
            if (dialog.datosModificados()) {
                cargarTablaEtapa();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al preparar edición: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
// Método auxiliar para obtener el ID de producción con validación

    private int obtenerIdEtapa(DefaultTableModel model, int modelRow) {
        try {
            Object idObj = model.getValueAt(modelRow, 0);
            int id = Integer.parseInt(idObj.toString());

            if (id <= 0) {
                JOptionPane.showMessageDialog(this,
                        "ID de producción no válido",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return -1;
            }
            return id;

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Formato de ID inválido",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RSMaterialComponent.RSTableMetroCustom Tabla1;
    private RSMaterialComponent.RSButtonShape btnElimi;
    private RSMaterialComponent.RSButtonShape btnNuevo;
    private javax.swing.JScrollPane jScrollPane3;
    private RSMaterialComponent.RSTextFieldMaterialIcon txtbuscar;
    // End of variables declaration//GEN-END:variables

    public void cargarTablaEtapa() {
        try (Connection con = Conexion.getConnection()) {
            // Consulta SQL que usa la tabla asignada
            String sql = "SELECT "
                    + "ep.idetapa_produccion, "
                    + "ep.nombre_etapa, "
                    + "ep.cantidad, "
                    + "ep.fecha_inicio, "
                    + "ep.fecha_fin, "
                    + "ep.estado, "
                    + "u.nombre AS trabajador_asignado, "
                    + "GROUP_CONCAT(DISTINCT CASE WHEN i.tipo = 'material' THEN i.nombre ELSE NULL END SEPARATOR ', ') AS materiales, "
                    + "GROUP_CONCAT(DISTINCT CASE WHEN i.tipo = 'herramienta' THEN i.nombre ELSE NULL END SEPARATOR ', ') AS herramientas "
                    + "FROM etapa_produccion ep "
                    + "LEFT JOIN asignada a ON ep.idetapa_produccion = a.etapa_produccion_idetapa_produccion "
                    + "LEFT JOIN usuario u ON a.usuario_id_usuario = u.id_usuario "
                    + "LEFT JOIN utilizado ut ON ep.idetapa_produccion = ut.etapa_produccion_idetapa_produccion "
                    + "LEFT JOIN inventario i ON ut.inventario_id_inventario = i.id_inventario "
                    + "WHERE ep.produccion_id_produccion = ? "
                    + "GROUP BY ep.idetapa_produccion "
                    + "ORDER BY ep.fecha_inicio ASC";

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, this.idProduccion);
                try (ResultSet rs = ps.executeQuery()) {
                    DefaultTableModel model = (DefaultTableModel) Tabla1.getModel();
                    model.setRowCount(0);

                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                    while (rs.next()) {
                        model.addRow(new Object[]{
                            rs.getInt("idetapa_produccion"), // Columna 0 (oculta)
                            rs.getString("nombre_etapa"), // Columna 1
                            rs.getInt("cantidad"), // Columna 2
                            sdf.format(rs.getDate("fecha_inicio")), // Columna 3
                            rs.getDate("fecha_fin") != null ? sdf.format(rs.getDate("fecha_fin")) : "En proceso", // Columna 4
                            rs.getString("estado"), // Columna 5
                            rs.getString("trabajador_asignado"), // Columna 6
                            "Ver", // Columna 7 (botón)
                            "Editar",
                            rs.getString("materiales"), // Columna 8 (oculta)
                            rs.getString("herramientas") // Columna 9 (oculta)
                        });
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar etapas de producción: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void filtrarTabla() {
        String textoBusqueda = txtbuscar.getText().trim();
        DefaultTableModel modelo = (DefaultTableModel) Tabla1.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(modelo);
        Tabla1.setRowSorter(tr);

        if (textoBusqueda.isEmpty()) {
            tr.setRowFilter(null);
            return;
        }

        // Expresión regular para detectar si son solo números (1-2 dígitos)
        if (textoBusqueda.matches("\\d{1}")) {
            // Buscar en ID (columna 0) y fechas (columnas 1 y 2)
            List<RowFilter<Object, Object>> filters = new ArrayList<>();
            filters.add(RowFilter.regexFilter("^" + textoBusqueda, 0));// ID (coincidencia exacta)
            tr.setRowFilter(RowFilter.orFilter(filters));

        } else if (textoBusqueda.matches("\\d{2}")) {
            List<RowFilter<Object, Object>> filters = new ArrayList<>();
            filters.add(RowFilter.regexFilter(textoBusqueda, 1)); // fecha_inicio
            filters.add(RowFilter.regexFilter(textoBusqueda, 2)); // fecha_fin
            tr.setRowFilter(RowFilter.orFilter(filters));
        } // Si contiene letras (aunque sea parcial)
        else if (textoBusqueda.matches(".*[a-zA-ZáéíóúÁÉÍÓÚ].*")) {
            // Buscar solo en estado (columna 3)
            tr.setRowFilter(RowFilter.regexFilter("(?i)" + textoBusqueda, 3));
        } // Para otros casos (números más largos, combinaciones, etc.)
        else {
            // Buscar en todos los campos
            tr.setRowFilter(RowFilter.regexFilter("(?i)" + textoBusqueda));
        }
    }

}
