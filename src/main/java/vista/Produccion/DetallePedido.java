/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista.Produccion;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import modelo.Conexion;
import vista.TemaManager;

/**
 *
 * @author EQUIPO
 */
public class DetallePedido extends javax.swing.JPanel {

    private int idProduccion;

    // Constructor completo
    public DetallePedido(int idProduccion, String nombre, String fechaInicio,
            String fechaFin, String estado, String cantidad, String dimensiones) {
        this.idProduccion = idProduccion;
        initComponents();
        aplicarTema();

        // Asignar valores directamente
        this.nombre.setText(nombre != null ? nombre : "");
        this.fecha_ini.setText(fechaInicio != null ? fechaInicio : "");
        this.fecha_fin.setText(fechaFin != null ? fechaFin : "");
        this.estado.setText(estado != null ? estado : "");
        this.cantidad.setText(cantidad != null ? cantidad : "0");
        this.dimensiones.setText(dimensiones != null ? dimensiones : "");

        // Configurar tabla
        configurarTabla();
        cargarTablaEtapa();
        cargarDatosPedido();

        TemaManager.getInstance().addThemeChangeListener(() -> {
            aplicarTema();
        });
    }

    public void aplicarTema() {
        boolean oscuro = TemaManager.getInstance().isOscuro();
        if (oscuro) {
            // Colores para tema oscuro
            Color fondo = new Color(21, 21, 33);
            Color texto = Color.WHITE;
            Color primario = new Color(40, 60, 150);

            // Aplicar a componentes principales
            jPanel2.setBackground(fondo);
            jPanel3.setBackground(texto);

            // Configurar el JScrollPane y su viewport
            jScrollPane3.setBackground(fondo);
            jScrollPane3.getViewport().setBackground(new Color(30, 30, 45));
            jScrollPane3.setBorder(BorderFactory.createLineBorder(new Color(60, 60, 60)));

            // Aplicar a labels
            // Aplicar color de texto a todos los labels
            jLabel1.setForeground(texto);
            jLabel5.setForeground(texto);
            jLabel9.setForeground(texto);
            jLabel11.setForeground(texto);
            jLabel12.setForeground(texto);
            jLabel13.setForeground(texto);
            jLabel15.setForeground(texto);

            // Aplicar a labels de datos
            nombre.setForeground(texto);
            fecha_ini.setForeground(texto);
            fecha_fin.setForeground(texto);
            cantidad.setForeground(texto);
            dimensiones.setForeground(texto);
            estado.setForeground(texto);

            // Configurar tabla para tema oscuro
            Tabla1.setBackground(new Color(30, 30, 45));
            Tabla1.setForeground(texto);
            Tabla1.setBackgoundHead(new Color(67, 71, 120));
            Tabla1.setForegroundHead(texto);
            Tabla1.setBackgoundHover(new Color(40, 50, 90));
            Tabla1.setColorPrimary(new Color(37, 37, 52));
            Tabla1.setColorPrimaryText(texto);
            Tabla1.setColorSecondary(new Color(30, 30, 45));
            Tabla1.setColorSecundaryText(texto);
            Tabla1.setColorBorderHead(primario);
            Tabla1.setColorBorderRows(fondo.darker());
            Tabla1.setSelectionBackground(new Color(72, 92, 188));
            Tabla1.setGridColor(new Color(60, 60, 75));

        } else {
            Color fondo = new Color(242, 247, 255);
            Color texto = Color.BLACK;

            // Panel principal y componentes
            jPanel2.setBackground(fondo);
            jPanel3.setBackground(Color.BLACK);

            // Configurar el JScrollPane y su viewport
            jScrollPane3.setBackground(fondo);
            jScrollPane3.getViewport().setBackground(Color.WHITE);
            jScrollPane3.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

            // Aplicar a labels
            jLabel1.setForeground(texto);
            jLabel5.setForeground(texto);
            jLabel9.setForeground(texto);
            jLabel11.setForeground(texto);
            jLabel12.setForeground(texto);
            jLabel13.setForeground(texto);
            jLabel15.setForeground(texto);

            // Aplicar a labels de datos
            nombre.setForeground(texto);
            fecha_ini.setForeground(texto);
            fecha_fin.setForeground(texto);
            cantidad.setForeground(texto);
            dimensiones.setForeground(texto);
            estado.setForeground(texto);

            // Configurar tabla para tema claro
            Color primario = new Color(72, 92, 188);

            Tabla1.setBackground(Color.WHITE);
            Tabla1.setForeground(texto);
            Tabla1.setBackgoundHead(new Color(46, 49, 82));
            Tabla1.setForegroundHead(Color.WHITE);
            Tabla1.setBackgoundHover(new Color(67, 150, 209));
            Tabla1.setColorPrimary(new Color(242, 242, 242));
            Tabla1.setColorPrimaryText(texto);
            Tabla1.setColorSecondary(Color.WHITE);
            Tabla1.setColorSecundaryText(texto);
            Tabla1.setColorBorderHead(primario);
            Tabla1.setColorBorderRows(Color.BLACK);
            Tabla1.setSelectionBackground(new Color(109, 160, 221));
            Tabla1.setGridColor(Color.BLACK);
        }
        actualizarTabla();
        // Forzar repintado de todos los componentes
        revalidate();
        repaint();
        Tabla1.repaint();
        Tabla1.getTableHeader().repaint();
    }

    private void actualizarTabla() {
        // Solución especial para RSTableMetroCustom
        Tabla1.updateUI();

        // Forzar actualización del renderizado
        Tabla1.repaint();
        Tabla1.getTableHeader().repaint();

        // Actualizar el modelo de la tabla si es necesario
        ((DefaultTableModel) Tabla1.getModel()).fireTableDataChanged();
    }

    // Constructor simple que carga los datos
    public DetallePedido(int idProduccion) {
        this.idProduccion = idProduccion;
        initComponents();
        configurarTabla();
        cargarDatosPedido();  // Asegúrate que esta línea esté presente
        cargarTablaEtapa();
        aplicarTema();
    }

    private void configurarTabla() {
        Tabla1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        DefaultTableModel model = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Nombre", "Cantidad", "Fecha inicio", "Fecha final", "Estado", "Asignado"}
        );
        Tabla1.setModel(model);
        Tabla1.getColumnModel().getColumn(4).setCellRenderer(new EstadoTableCellRenderer());
        Tabla1.setCellSelectionEnabled(false);
        Tabla1.setRowSelectionAllowed(true);
        Tabla1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    }

    private void cargarDatosPedido() {
        try (Connection con = new Conexion().getConnection()) {
            String sql = "SELECT dp.descripcion, p.fecha_inicio, p.fecha_fin, p.estado, "
                    + "dp.cantidad, dp.dimension "
                    + "FROM produccion p "
                    + "JOIN detalle_pedido dp ON p.detalle_pedido_iddetalle_pedido = dp.iddetalle_pedido "
                    + "WHERE p.id_produccion = ?";

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, idProduccion);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                        // Asignar valores a los labels
                        nombre.setText(rs.getString("descripcion"));
                        fecha_ini.setText(rs.getDate("fecha_inicio") != null
                                ? sdf.format(rs.getDate("fecha_inicio")) : "No definida");
                        fecha_fin.setText(rs.getDate("fecha_fin") != null
                                ? sdf.format(rs.getDate("fecha_fin")) : "No definida");
                        estado.setText(rs.getString("estado"));
                        cantidad.setText(String.valueOf(rs.getInt("cantidad")));
                        dimensiones.setText(rs.getString("dimension"));
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar datos del pedido: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace(); // Para debug
        }
    }

    private class EstadoTableCellRenderer extends DefaultTableCellRenderer {

        public EstadoTableCellRenderer() {
            setHorizontalAlignment(JLabel.CENTER);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {

            boolean oscuro = TemaManager.getInstance().isOscuro();
            JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            label.setHorizontalAlignment(CENTER);
            label.setText(value != null ? value.toString() : "");

            if (isSelected) {
                label.setForeground(oscuro ? Color.WHITE : Color.BLACK);
                label.setBackground(oscuro ? new Color(67, 71, 120) : table.getSelectionBackground());
            } else {
                label.setForeground(oscuro ? Color.WHITE : Color.BLACK);

                String estado = value != null ? value.toString() : "";
                if (oscuro) {
                    switch (estado.toLowerCase()) {
                        case "pendiente":
                            label.setBackground(new Color(153, 0, 51)); // Rojo oscuro
                            break;
                        case "proceso":
                            label.setBackground(new Color(251, 139, 36)); // Amarillo oscuro
                            break;
                        case "finalizado":
                            label.setBackground(new Color(31, 123, 21)); // Verde oscuro
                            break;
                        default:
                            label.setBackground(new Color(37, 37, 52));
                            break;
                    }
                } else {
                    switch (estado.toLowerCase()) {
                        case "pendiente":
                            label.setBackground(new Color(255, 204, 204)); // Rojo claro
                            break;
                        case "proceso":
                            label.setBackground(new Color(255, 255, 153)); // Amarillo claro
                            break;
                        case "finalizado":
                            label.setBackground(new Color(204, 255, 204)); // Verde claro
                            break;
                        default:
                            label.setBackground(Color.WHITE);
                            break;
                    }
                }
            }

            label.setBorder(BorderFactory.createLineBorder(oscuro ? new Color(153, 153, 153) : new Color(153, 153, 153), 1));
            return label;
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
        jLabel1 = new javax.swing.JLabel();
        fecha_ini = new javax.swing.JLabel();
        fecha_fin = new javax.swing.JLabel();
        cantidad = new javax.swing.JLabel();
        dimensiones = new javax.swing.JLabel();
        estado = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        nombre = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Tabla1 = new RSMaterialComponent.RSTableMetroCustom();

        jPanel2.setBackground(new java.awt.Color(242, 247, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Nombre:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        fecha_ini.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        fecha_ini.setForeground(new java.awt.Color(0, 0, 0));
        fecha_ini.setText("fecha_ini");
        jPanel2.add(fecha_ini, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, -1, -1));

        fecha_fin.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        fecha_fin.setForeground(new java.awt.Color(0, 0, 0));
        fecha_fin.setText("fecha_fin");
        jPanel2.add(fecha_fin, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, -1, -1));

        cantidad.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cantidad.setForeground(new java.awt.Color(0, 0, 0));
        cantidad.setText("cantidad");
        jPanel2.add(cantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 80, -1, -1));

        dimensiones.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        dimensiones.setForeground(new java.awt.Color(0, 0, 0));
        dimensiones.setText("dimensiones");
        jPanel2.add(dimensiones, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 130, -1, -1));

        estado.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        estado.setForeground(new java.awt.Color(0, 0, 0));
        estado.setText("estado");
        jPanel2.add(estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 80, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Etapas terminadas:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, -1, -1));

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 700, 2));

        nombre.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        nombre.setForeground(new java.awt.Color(0, 0, 0));
        nombre.setText("Nombre");
        jPanel2.add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, -1, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Fecha de fin:");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Dimensiones:");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 130, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Cantidad:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 80, -1, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Estado:");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 80, -1, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Fecha de inicio: ");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

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
        jScrollPane3.setViewportView(Tabla1);
        Tabla1.getColumnModel().getColumn(0).setPreferredWidth(10);

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 1100, 190));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RSMaterialComponent.RSTableMetroCustom Tabla1;
    private javax.swing.JLabel cantidad;
    private javax.swing.JLabel dimensiones;
    private javax.swing.JLabel estado;
    private javax.swing.JLabel fecha_fin;
    private javax.swing.JLabel fecha_ini;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel nombre;
    // End of variables declaration//GEN-END:variables

    public void cargarTablaEtapa() {
        try (Connection con = Conexion.getConnection()) {
            String sql = "SELECT ep.nombre_etapa, ep.cantidad, "
                    + "ep.fecha_inicio, ep.fecha_fin, ep.estado, "
                    + "u.nombre AS trabajador_asignado "
                    + "FROM etapa_produccion ep "
                    + "LEFT JOIN asignada a ON ep.idetapa_produccion = a.etapa_produccion_idetapa_produccion "
                    + "LEFT JOIN usuario u ON a.usuario_id_usuario = u.id_usuario "
                    + "WHERE ep.produccion_id_produccion = ? "
                    + "AND ep.estado = 'completado' "
                    + "ORDER BY ep.fecha_inicio";

            DefaultTableModel model = (DefaultTableModel) Tabla1.getModel();
            model.setRowCount(0); // Limpiar tabla antes de cargar

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, this.idProduccion);
                try (ResultSet rs = ps.executeQuery()) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                    while (rs.next()) {
                        model.addRow(new Object[]{
                            rs.getString("nombre_etapa"),
                            rs.getInt("cantidad"),
                            sdf.format(rs.getDate("fecha_inicio")),
                            rs.getDate("fecha_fin") != null ? sdf.format(rs.getDate("fecha_fin")) : "En proceso",
                            rs.getString("estado"),
                            rs.getString("trabajador_asignado")
                        });
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar etapas: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
