/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista.Ventas;

import RSMaterialComponent.RSButtonShape;
import controlador.Ctrl_Pedido;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import rojeru_san.RSButtonRiple;
import vista.Ventas.DetallesPedido; // Si decides mover DetallesPedido a vista.Ventas

/**
 *
 * @author ZenBook
 */
public class pedido extends javax.swing.JPanel {

    private JPanel contenedor; // Referencia al contenedor de Principal
    private Ctrl_Pedido controlador;

    /**
     * Creates new form pedido
     */
    public pedido(JPanel contenedor) {
        this.contenedor = contenedor;
        this.controlador = new Ctrl_Pedido();
        initComponents();

        tablaM.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        // Configurar la columna "Detalle"
        TableColumn detailColumn = tablaM.getColumnModel().getColumn(6);
        detailColumn.setCellRenderer(new ButtonRenderer());
        detailColumn.setCellEditor(new ButtonEditor(new JCheckBox(), contenedor));
        detailColumn.setPreferredWidth(35); // Ajustar el ancho de la columna
        tablaM.setRowHeight(30); // Ajusta este valor según necesites
        tablaM.setColorSecondary(new java.awt.Color(255, 255, 255)); // Fondo blanco para las celdas

        tablaM.setBorderRows(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153))); // Aumentar el grosor a 2 píxeles
        tablaM.setShowGrid(true); // Asegurar que las líneas de la cuadrícula sean visibles
        tablaM.setGridColor(new java.awt.Color(153, 153, 153)); // Color gris de las líneas

        // Cargar datos desde la base de datos
        cargarDatosIniciales();
    }

    // Método para agregar una nueva fila a la tabla
    public void agregarFilaATabla(Object[] fila) {
        DefaultTableModel model = (DefaultTableModel) tablaM.getModel();
        model.addRow(fila);
    }

    // Cargar datos desde la base de datos
    public void cargarDatosIniciales() {
        DefaultTableModel model = (DefaultTableModel) tablaM.getModel();
        model.setRowCount(0);

        List<Ctrl_Pedido.MaterialConDetalles> pedidos = controlador.obtenerMateriales();
        for (Ctrl_Pedido.MaterialConDetalles pedido : pedidos) {
            model.addRow(new Object[]{
                pedido.getPedido().getId_pedido(),
                pedido.getPedido().getNombre(),
                pedido.getPedido().getEstado(),
                pedido.getNombreCliente(),
                new java.text.SimpleDateFormat("yyyy-MM-dd").format(pedido.getPedido().getFecha_inicio()),
                new java.text.SimpleDateFormat("yyyy-MM-dd").format(pedido.getPedido().getFecha_fin()),
                "Ver"
            });
        }
    }

    // Renderizador para mostrar un botón en la columna "Detalle"
    // Renderizador para mostrar un botón pequeño centrado en la celda
    class ButtonRenderer extends JPanel implements TableCellRenderer {

        private RSButtonShape button;

        public ButtonRenderer() {
            // Layout sin márgenes internos ni externos
            super(new BorderLayout());
            setOpaque(true);
            setBorder(BorderFactory.createLineBorder(new Color(153, 153, 153), 1));
            setBackground(new Color(255,255,255));

            button = new RSButtonShape();
            button.setText("Ver");
            button.setFont(new Font("Tahoma", Font.BOLD, 12));
            button.setPreferredSize(new Dimension(70, 25));       // ancho x alto
            button.setMargin(new Insets(0, 0, 0, 0));             // recorta márgenes
            button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
            button.setBackground(new Color(46, 49, 82));
            button.setBorder(javax.swing.BorderFactory.createCompoundBorder());
            button.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
            button.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

            // Lo metemos en un flow centrado y transparente
            JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            wrapper.setOpaque(false);
            wrapper.add(button);

            // Y añadimos al Sur del BorderLayout
            add(wrapper, BorderLayout.SOUTH);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            // Ajustar colores de fondo de la celda al estado de selección
            // Fondo de la celda
            if (isSelected) {
                setBackground(table.getSelectionBackground());
            } else {
                setBackground(new Color(255,255,255));
            }
            return this;
        }
    }

// Editor que también devuelve un panel
    class ButtonEditor extends DefaultCellEditor {

        private JPanel panel;
        private RSButtonShape button;
        private int row;
        private JTable table;
        private JPanel contenedor;

        public ButtonEditor(JCheckBox checkBox, JPanel contenedor) {
            super(checkBox);
            this.contenedor = contenedor;

            panel = new JPanel(new BorderLayout());
            panel.setOpaque(true);
            panel.setBorder(BorderFactory.createLineBorder(new Color(153, 153, 153), 1));
            panel.setBackground(new Color(255,255,255));

            button = new RSButtonShape();
            button.setText("Ver");
            button.setFont(new Font("Tahoma", Font.BOLD, 12));
            button.setPreferredSize(new Dimension(70, 25));
            button.setMargin(new Insets(0, 0, 0, 0));
            button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
            button.setBackground(new Color(46, 49, 82));
            button.setBorder(javax.swing.BorderFactory.createCompoundBorder());
            button.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
            button.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

            button.addActionListener(e -> {
                fireEditingStopped();
                String id = table.getValueAt(row, 0).toString();
                DetallesPedido detalles = new DetallesPedido(id, contenedor);
                detalles.setSize(1290, 730);
                detalles.setLocation(0, 0);
                contenedor.removeAll();
                contenedor.add(detalles);
                contenedor.revalidate();
                contenedor.repaint();
            });

            JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            wrapper.setOpaque(false);
            wrapper.add(button);

            panel.add(wrapper, BorderLayout.SOUTH);
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            this.row = row;
            this.table = table;
            if (isSelected) {
                panel.setBackground(table.getSelectionBackground());
            } else {
                panel.setBackground(new Color(255,255,255));
            }
            return panel;
        }

        @Override
        public Object getCellEditorValue() {
            return "Ver";
        }

        @Override
        public boolean stopCellEditing() {
            return super.stopCellEditing();
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
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaM = new RSMaterialComponent.RSTableMetroCustom();
        cmbCategoria = new RSMaterialComponent.RSComboBoxMaterial();
        txtBuscar = new RSMaterialComponent.RSTextFieldMaterialIcon();
        btnNuevo = new RSMaterialComponent.RSButtonShape();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1240, 580));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Codigo", "Nombre", "Estado", "Cliente", "Fecha inicio", "Fecha fin", "Detalle"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaM.setBackgoundHead(new java.awt.Color(46, 49, 82));
        tablaM.setBackgoundHover(new java.awt.Color(67, 150, 209));
        tablaM.setBorderHead(null);
        tablaM.setBorderRows(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        tablaM.setColorBorderHead(new java.awt.Color(46, 49, 82));
        tablaM.setColorBorderRows(new java.awt.Color(46, 49, 82));
        tablaM.setColorPrimaryText(new java.awt.Color(0, 0, 0));
        tablaM.setColorSecondary(new java.awt.Color(255, 255, 255));
        tablaM.setColorSecundaryText(new java.awt.Color(0, 0, 0));
        tablaM.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        tablaM.setFontHead(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        tablaM.setFontRowHover(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        tablaM.setFontRowSelect(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        tablaM.setSelectionBackground(new java.awt.Color(67, 150, 209));
        jScrollPane2.setViewportView(tablaM);
        tablaM.getColumnModel().getColumn(0).setPreferredWidth(10);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 1180, 420));

        cmbCategoria.setForeground(new java.awt.Color(153, 153, 153));
        cmbCategoria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione una categoria:" }));
        cmbCategoria.setColorMaterial(new java.awt.Color(153, 153, 153));
        cmbCategoria.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel1.add(cmbCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 20, 280, 30));

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
        jPanel1.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 410, 30));

        btnNuevo.setBackground(new java.awt.Color(46, 49, 82));
        btnNuevo.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plus (2).png"))); // NOI18N
        btnNuevo.setText(" Nuevo");
        btnNuevo.setBackgroundHover(new java.awt.Color(67, 150, 209));
        btnNuevo.setFont(new java.awt.Font("Roboto Bold", 1, 16)); // NOI18N
        btnNuevo.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        btnNuevo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel1.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 30, 110, 30));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1240, 580));
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        pedidoNuevo dialog = new pedidoNuevo(new javax.swing.JFrame(), true, this);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnNuevoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RSMaterialComponent.RSButtonShape btnNuevo;
    private RSMaterialComponent.RSComboBoxMaterial cmbCategoria;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private RSMaterialComponent.RSTableMetroCustom tablaM;
    private RSMaterialComponent.RSTextFieldMaterialIcon txtBuscar;
    // End of variables declaration//GEN-END:variables
}
