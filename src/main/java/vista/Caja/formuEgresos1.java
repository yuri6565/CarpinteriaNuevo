/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package vista.Caja;

import java.awt.BorderLayout;
import modelo.CheckableItem;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.accessibility.Accessible;
import javax.swing.AbstractAction;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.ComboPopup;
import modelo.Conexion;
import vista.Produccion.Error_guardar;
import vista.Produccion.FormuEtapaProduccion;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import vista.Inventario0.herramientasNuevo;
import vista.proveedor.proveedornuevo;

/**
 *
 * @author ADSO
 */
public class formuEgresos1 extends javax.swing.JDialog {

    private CheckedComboBox<CheckableItem> cmbMateriales;
    private CheckedComboBox<CheckableItem> cmbHerramientas;

    /**
     * Creates new form formuIngresos
     */
    public formuEgresos1(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        cargarProveedores();
        ohtaniahea();
        setPreferredSize(new java.awt.Dimension(522, 460));

        cmbMateriales = new CheckedComboBox<>(makeProductModel("material"));
        cmbMateriales.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        jPanel1.add(cmbMateriales, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 360, 170, 30));

        cmbHerramientas = new CheckedComboBox<>(makeProductModel("herramienta"));
        cmbHerramientas.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        jPanel1.add(cmbHerramientas, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 415, 170, 30));
        cmbHerramientas.setVisible(false);
        cmbMateriales.setVisible(false);
        getContentPane().add(jPanel1, new java.awt.GridBagConstraints());
    }

    private DefaultComboBoxModel<CheckableItem> makeProductModel(String tipo) {
        DefaultComboBoxModel<CheckableItem> model = new DefaultComboBoxModel<>();
        try {
            Connection con = Conexion.getConnection();
            String sql = "SELECT id_inventario, nombre FROM inventario WHERE tipo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tipo);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id_inventario");
                String nombre = rs.getString("nombre");
                model.addElement(new CheckableItem(id, nombre, false));
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(FormuEtapaProduccion.class.getName()).log(Level.SEVERE, null, ex);
            Error_guardar errorDialog = new Error_guardar(
                    (Frame) this.getParent(),
                    true,
                    "Error",
                    "No se pudieron cargar los " + tipo + ": " + ex.getMessage()
            );
            errorDialog.setLocationRelativeTo(null);
            errorDialog.setVisible(true);
        }
        return model;
    }

    private List<String> obtenerSeleccionados(CheckedComboBox<CheckableItem> combo) {
        List<String> seleccionados = new ArrayList<>();
        for (int i = 0; i < combo.getModel().getSize(); i++) {
            CheckableItem item = combo.getModel().getElementAt(i);
            if (item.isSelected()) {
                seleccionados.add(item.toString());
            }
        }
        return seleccionados;
    }

    class CheckedComboBox<E extends CheckableItem> extends JComboBox<E> {

        protected boolean keepOpen;
        private final JPanel panel = new JPanel(new BorderLayout());

        protected CheckedComboBox(ComboBoxModel<E> model) {
            super(model);
            setBackground(new Color(255, 255, 255)); // Fondo blanco para coincidir con jPanel1
            setForeground(Color.DARK_GRAY); // Texto oscuro
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(200, 40); // Aumentar altura para un look más moderno
        }

        @Override
        public void updateUI() {
            setRenderer(null);
            super.updateUI();

            Accessible a = getAccessibleContext().getAccessibleChild(0);
            if (a instanceof ComboPopup) {
                ((ComboPopup) a).getList().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        JList<?> list = (JList<?>) e.getComponent();
                        if (SwingUtilities.isLeftMouseButton(e)) {
                            keepOpen = true;
                            updateItem(list.locationToIndex(e.getPoint()));
                        }
                    }
                });
            }

            DefaultListCellRenderer renderer = new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                    Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (index >= 0) {
                        c.setBackground(isSelected ? new Color(0, 120, 215, 50) : new Color(255, 255, 255));
                        c.setForeground(Color.DARK_GRAY);
                    } else {
                        c.setBackground(new Color(0, 0, 0, 0)); // Fondo transparente para el texto seleccionado
                    }
                    return c;
                }
            };
            JCheckBox check = new JCheckBox();
            check.setOpaque(false);
            check.setForeground(new Color(0, 120, 215)); // Color de casilla moderna
            setRenderer((list, value, index, isSelected, cellHasFocus) -> {
                panel.removeAll();
                Component c = renderer.getListCellRendererComponent(
                        list, value, index, isSelected, cellHasFocus);
                if (index < 0) {
                    String txt = getCheckedItemString(list.getModel());
                    JLabel l = (JLabel) c;
                    l.setText(txt.isEmpty() ? " " : txt);
                    l.setForeground(Color.DARK_GRAY);
                    l.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                    panel.setOpaque(false); // Hacer el panel transparente
                    panel.setBackground(new Color(0, 0, 0, 0)); // Fondo transparente
                } else {
                    check.setSelected(value.isSelected());
                    panel.add(check, BorderLayout.WEST);
                    panel.setBackground(isSelected ? new Color(0, 120, 215, 50) : new Color(255, 255, 255));
                }
                panel.add(c, BorderLayout.CENTER);
                return panel;
            });
            initActionMap();
        }

        protected void initActionMap() {
            KeyStroke ks = KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0);
            getInputMap(JComponent.WHEN_FOCUSED).put(ks, "checkbox-select");
            getActionMap().put("checkbox-select", new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    Accessible a = getAccessibleContext().getAccessibleChild(0);
                    if (a instanceof ComboPopup) {
                        updateItem(((ComboPopup) a).getList().getSelectedIndex());
                    }
                }
            });
        }

        protected void updateItem(int index) {
            if (isPopupVisible() && index >= 0) {
                E item = getItemAt(index);
                item.setSelected(!item.isSelected());
                setSelectedIndex(-1);
                setSelectedItem(item);
            }
        }

        @Override
        public void setPopupVisible(boolean v) {
            if (keepOpen) {
                keepOpen = false;
            } else {
                super.setPopupVisible(v);
            }
        }

        protected static <E extends CheckableItem> String getCheckedItemString(ListModel<E> model) {
            return IntStream.range(0, model.getSize())
                    .mapToObj(model::getElementAt)
                    .filter(CheckableItem::isSelected)
                    .map(Objects::toString)
                    .sorted()
                    .collect(Collectors.joining(", "));
        }
    }

    private void ohtaniahea() {
        jLabel8.setVisible(false);
        comboProveedor.setVisible(false);
        btnClienteN1.setVisible(false);
        jLabel3.setVisible(false);
        btnClienteN.setVisible(false);
        jLabel11CC.setVisible(false);
        btnClienteN2.setVisible(false);

        jPanel1.revalidate();
        jPanel1.repaint();
    }

    class ProductoItem {

        private final int id;
        private final String nombre;

        public ProductoItem(int id, String nombre) {
            this.id = id;
            this.nombre = nombre;
        }

        public int getId() {
            return id;
        }

        @Override
        public String toString() {
            return nombre;
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtPago = new com.toedter.calendar.JDateChooser();
        txtCantidadnuevo = new RSMaterialComponent.RSTextFieldMaterial();
        jLabel2 = new javax.swing.JLabel();
        txtDetallenuevo = new RSMaterialComponent.RSTextFieldMaterial();
        comboCategoria = new RSMaterialComponent.RSComboBoxMaterial();
        jLabel7 = new javax.swing.JLabel();
        comboProveedor = new RSMaterialComponent.RSComboBoxMaterial();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnClienteN1 = new RSMaterialComponent.RSButtonShape();
        btnClienteN = new RSMaterialComponent.RSButtonShape();
        rSLabelHora1 = new rojeru_san.rsdate.RSLabelHora();
        jLabel11CC = new javax.swing.JLabel();
        btnClienteN2 = new RSMaterialComponent.RSButtonShape();
        btnGuardar1 = new rojeru_san.RSButtonRiple();
        btnCancelar2 = new rojeru_san.RSButtonRiple();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(504, 526));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(46, 49, 82));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Century751 BT", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Egreso");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 520, 50));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel9.setText("Detalle de Ingreso:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 130, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel10.setText("Fecha Pago:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1, -1));

        txtPago.setBackground(new java.awt.Color(255, 255, 255));
        txtPago.setForeground(new java.awt.Color(255, 255, 255));
        txtPago.setDateFormatString("y-MM-d");
        txtPago.setMaxSelectableDate(new java.util.Date(253370786472000L));
        jPanel1.add(txtPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 190, 30));

        txtCantidadnuevo.setForeground(new java.awt.Color(46, 49, 82));
        txtCantidadnuevo.setColorMaterial(new java.awt.Color(46, 49, 82));
        txtCantidadnuevo.setPhColor(new java.awt.Color(46, 49, 82));
        txtCantidadnuevo.setPlaceholder("");
        txtCantidadnuevo.setSelectionColor(new java.awt.Color(46, 49, 82));
        jPanel1.add(txtCantidadnuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 420, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setText("Valor monetario:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, -1, 30));

        txtDetallenuevo.setForeground(new java.awt.Color(0, 0, 0));
        txtDetallenuevo.setColorMaterial(new java.awt.Color(46, 49, 82));
        txtDetallenuevo.setPhColor(new java.awt.Color(46, 49, 82));
        txtDetallenuevo.setPlaceholder("");
        txtDetallenuevo.setSelectionColor(new java.awt.Color(46, 49, 82));
        txtDetallenuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDetallenuevoActionPerformed(evt);
            }
        });
        jPanel1.add(txtDetallenuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 420, 30));

        comboCategoria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione categoria:", "Servicios Publicos", "Compra de Productos e Insumos", "Arriendo", "Nómina", "Gastos Administrativos", "Mercadeo y Publicidad", "Transporte", "Domicilios y Logistica", "mantenimineto y Reparaciones", "Muebles", "Equipos o Maquinaria", "Otros" }));
        comboCategoria.setColorMaterial(new java.awt.Color(0, 0, 0));
        comboCategoria.setFont(new java.awt.Font("Roboto Bold", 0, 14)); // NOI18N
        comboCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCategoriaActionPerformed(evt);
            }
        });
        jPanel1.add(comboCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 420, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel7.setText("Categoria");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, -1, -1));

        comboProveedor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione proveedor:" }));
        comboProveedor.setColorMaterial(new java.awt.Color(0, 0, 0));
        comboProveedor.setFont(new java.awt.Font("Roboto Bold", 0, 14)); // NOI18N
        comboProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboProveedorActionPerformed(evt);
            }
        });
        jPanel1.add(comboProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, 170, 30));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel8.setText("Proveedor:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setText("Insumos:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 330, -1, -1));

        btnClienteN1.setBackground(new java.awt.Color(46, 49, 82));
        btnClienteN1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        btnClienteN1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plus (2).png"))); // NOI18N
        btnClienteN1.setBackgroundHover(new java.awt.Color(67, 150, 209));
        btnClienteN1.setFont(new java.awt.Font("Roboto Bold", 1, 15)); // NOI18N
        btnClienteN1.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        btnClienteN1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnClienteN1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClienteN1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnClienteN1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 320, 20, 20));

        btnClienteN.setBackground(new java.awt.Color(46, 49, 82));
        btnClienteN.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        btnClienteN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plus (2).png"))); // NOI18N
        btnClienteN.setBackgroundHover(new java.awt.Color(67, 150, 209));
        btnClienteN.setFont(new java.awt.Font("Roboto Bold", 1, 15)); // NOI18N
        btnClienteN.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        btnClienteN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnClienteN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClienteNActionPerformed(evt);
            }
        });
        jPanel1.add(btnClienteN, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 390, 20, 20));

        rSLabelHora1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(rSLabelHora1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 120, -1));

        jLabel11CC.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel11CC.setText("Herramientas:");
        jPanel1.add(jLabel11CC, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, -1, -1));

        btnClienteN2.setBackground(new java.awt.Color(46, 49, 82));
        btnClienteN2.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        btnClienteN2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plus (2).png"))); // NOI18N
        btnClienteN2.setBackgroundHover(new java.awt.Color(67, 150, 209));
        btnClienteN2.setFont(new java.awt.Font("Roboto Bold", 1, 15)); // NOI18N
        btnClienteN2.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        btnClienteN2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnClienteN2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClienteN2ActionPerformed(evt);
            }
        });
        jPanel1.add(btnClienteN2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 330, 20, 20));

        btnGuardar1.setBackground(new java.awt.Color(46, 49, 82));
        btnGuardar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plus (2).png"))); // NOI18N
        btnGuardar1.setText("Guardar");
        btnGuardar1.setFont(new java.awt.Font("Humnst777 BlkCn BT", 1, 18)); // NOI18N
        btnGuardar1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 470, 140, -1));

        btnCancelar2.setBackground(new java.awt.Color(46, 49, 82));
        btnCancelar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/salida (1).png"))); // NOI18N
        btnCancelar2.setText("Volver");
        btnCancelar2.setFont(new java.awt.Font("Humnst777 BlkCn BT", 1, 18)); // NOI18N
        btnCancelar2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnCancelar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar2ActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 470, 140, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void comboCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCategoriaActionPerformed
        String seleccion = comboCategoria.getSelectedItem().toString();
        boolean esCompraProductos = seleccion.equals("Compra de Productos e Insumos");

        jLabel8.setVisible(esCompraProductos);
        comboProveedor.setVisible(esCompraProductos);
        btnClienteN1.setVisible(esCompraProductos);

        // Mostrar solo materiales o herramientas según necesidad
        jLabel3.setVisible(esCompraProductos);
        btnClienteN.setVisible(esCompraProductos);
        jLabel11CC.setVisible(esCompraProductos);

        // Mostrar combobox según tipo de producto
        cmbMateriales.setVisible(esCompraProductos);
        cmbHerramientas.setVisible(esCompraProductos); // Cambiar según lógica de negocio

        jPanel1.revalidate();
        jPanel1.repaint();
    }//GEN-LAST:event_comboCategoriaActionPerformed

    private void txtDetallenuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDetallenuevoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDetallenuevoActionPerformed

    private void btnClienteN1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClienteN1ActionPerformed
        proveedornuevo dialog = new proveedornuevo(new javax.swing.JFrame(), true);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

    }//GEN-LAST:event_btnClienteN1ActionPerformed

    private void btnClienteNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClienteNActionPerformed
        herramientasNuevo dialog = new herramientasNuevo(new javax.swing.JFrame(), true);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnClienteNActionPerformed

    private void comboProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboProveedorActionPerformed

    private void btnClienteN2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClienteN2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnClienteN2ActionPerformed

    private void btnGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar1ActionPerformed
        Connection con = null;
        PreparedStatement ps = null;
        try {
            // Validaciones básicas
            if (txtPago.getDate() == null) {
                mostrarError("La fecha de pago es requerida");
                return;
            }
            if (txtCantidadnuevo.getText().trim().isEmpty()) {
                mostrarError("El monto es requerido");
                return;
            }
            if (txtDetallenuevo.getText().trim().isEmpty()) {
                mostrarError("La descripción es requerida");
                return;
            }
            if (comboCategoria.getSelectedIndex() == 0) {
                mostrarError("Debe seleccionar una categoría");
                return;
            }

            // Obtener valores del formulario
            java.sql.Date fecha = new java.sql.Date(txtPago.getDate().getTime());
            double monto = Double.parseDouble(txtCantidadnuevo.getText().trim().replace(",", "."));
            String descripcion = txtDetallenuevo.getText();
            String categoria = comboCategoria.getSelectedItem().toString();

            // Recolectar items seleccionados
            List<CheckableItem> seleccionados = new ArrayList<>();
            for (int i = 0; i < cmbMateriales.getModel().getSize(); i++) {
                CheckableItem item = cmbMateriales.getModel().getElementAt(i);
                if (item.isSelected()) {
                    seleccionados.add(item);
                }
            }
            for (int i = 0; i < cmbHerramientas.getModel().getSize(); i++) {
                CheckableItem item = cmbHerramientas.getModel().getElementAt(i);
                if (item.isSelected()) {
                    seleccionados.add(item);
                }
            }

            // Validar proveedor si es compra de productos
            String proveedorSeleccionado = null;
            if (categoria.equals("Compra de Productos e Insumos")) {
                if (comboProveedor.getSelectedIndex() <= 0) {
                    mostrarError("Debe seleccionar un proveedor");
                    return;
                }
                proveedorSeleccionado = comboProveedor.getSelectedItem().toString();

                if (seleccionados.isEmpty()) {
                    mostrarError("Debe seleccionar al menos un producto o herramienta");
                    return;
                }
            }

            // Iniciar transacción
            con = Conexion.getConnection();
            con.setAutoCommit(false);

            // Insertar en caja
            String sqlCaja = "INSERT INTO caja (fecha, movimiento, monto, descripcion, categoria) VALUES (?, 'egreso', ?, ?, ?)";
            ps = con.prepareStatement(sqlCaja, Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, fecha);
            ps.setDouble(2, monto);
            ps.setString(3, descripcion);
            ps.setString(4, categoria);
            int resultado = ps.executeUpdate();

            if (resultado > 0) {
                // Si es compra de productos, actualizar inventario
                if (categoria.equals("Compra de Productos e Insumos")) {
                    // Mostrar diálogo para cantidades
                    double cantidad = Double.parseDouble(txtCantidadnuevo.getText().trim().replace(",", "."));
                    EgresosMH formMH = new EgresosMH((Frame) this.getParent(), true, seleccionados, cantidad);
                    formMH.setLocationRelativeTo(null);
                    formMH.setVisible(true);

                    if (formMH.isConfirmado()) {
                        // Procesar cantidades ingresadas
                        Map<String, String> cantidadesMateriales = formMH.getCantidadesMateriales();
                        Map<String, String> cantidadesHerramientas = formMH.getCantidadesHerramientas();

                        // Actualizar inventario
                        actualizarInventario(con, cantidadesMateriales, cantidadesHerramientas);
                    } else {
                        con.rollback();
                        return;
                    }
                }

                con.commit();
                mostrarMensaje("Egreso registrado correctamente");
                this.dispose();
            } else {
                con.rollback();
                mostrarError("No se pudo registrar el egreso");
            }
        } catch (NumberFormatException e) {
            mostrarError("El monto debe ser un número válido con formato '12,50'");
        } catch (SQLException e) {
            try {
                if (con != null) {
                    con.rollback();
                }
                mostrarError("Error al registrar el egreso: " + e.getMessage());
            } catch (SQLException ex) {
                mostrarError("Error al revertir transacción: " + ex.getMessage());
            }
        } finally {
            if (ps != null) try {
                ps.close();
            } catch (SQLException e) {
            }
            if (con != null) try {
                con.close();
            } catch (SQLException e) {
            }
        }

    }//GEN-LAST:event_btnGuardar1ActionPerformed
    private void actualizarInventario(Connection con, Map<String, String> cantidadesMateriales, Map<String, String> cantidadesHerramientas) throws SQLException {
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.forLanguageTag("es-ES"));

        // Actualizar materiales
        for (Map.Entry<String, String> entry : cantidadesMateriales.entrySet()) {
            String nombre = entry.getKey();
            String cantidadStr = entry.getValue().replace(",", ".");

            try {
                double cantidad = nf.parse(cantidadStr).doubleValue();
                if (cantidad > 0) {
                    String sql = "UPDATE inventario SET cantidad = cantidad + ? WHERE nombre = ? AND tipo = 'material'";
                    try (PreparedStatement ps = con.prepareStatement(sql)) {
                        ps.setDouble(1, cantidad);
                        ps.setString(2, nombre);
                        ps.executeUpdate();
                    }
                }
            } catch (ParseException e) {
                throw new SQLException("Formato de cantidad inválido para " + nombre);
            }
        }

        // Actualizar herramientas
        for (Map.Entry<String, String> entry : cantidadesHerramientas.entrySet()) {
            String nombre = entry.getKey();
            String cantidadStr = entry.getValue().replace(",", ".");

            try {
                double cantidad = nf.parse(cantidadStr).doubleValue();
                if (cantidad > 0) {
                    String sql = "UPDATE inventario SET cantidad = cantidad + ? WHERE nombre = ? AND tipo = 'herramienta'";
                    try (PreparedStatement ps = con.prepareStatement(sql)) {
                        ps.setDouble(1, cantidad);
                        ps.setString(2, nombre);
                        ps.executeUpdate();
                    }
                }
            } catch (ParseException e) {
                throw new SQLException("Formato de cantidad inválido para " + nombre);
            }
        }
    }
    private void btnCancelar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelar2ActionPerformed

    private void cargarProveedores() {
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement("SELECT nombre FROM proveedor"); ResultSet rs = ps.executeQuery()) {
            comboProveedor.removeAllItems();
            comboProveedor.addItem("Seleccione un Proveedor:");
            while (rs.next()) {
                comboProveedor.addItem(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            mostrarError("Error al cargar proveedores: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        formuEgresos1 dialog = new formuEgresos1(new javax.swing.JFrame(), true);
                        dialog.setVisible(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error al abrir el formulario: " + e.getMessage(),
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSButtonRiple btnCancelar2;
    private RSMaterialComponent.RSButtonShape btnClienteN;
    private RSMaterialComponent.RSButtonShape btnClienteN1;
    private RSMaterialComponent.RSButtonShape btnClienteN2;
    private rojeru_san.RSButtonRiple btnGuardar1;
    private RSMaterialComponent.RSComboBoxMaterial comboCategoria;
    private RSMaterialComponent.RSComboBoxMaterial comboProveedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11CC;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private rojeru_san.rsdate.RSLabelHora rSLabelHora1;
    private RSMaterialComponent.RSTextFieldMaterial txtCantidadnuevo;
    private RSMaterialComponent.RSTextFieldMaterial txtDetallenuevo;
    private com.toedter.calendar.JDateChooser txtPago;
    // End of variables declaration//GEN-END:variables

    private boolean insertarEgreso(java.sql.Date fecha, String descripcion, Double monto,
            String categoria, String proveedor, List<Integer> productosIds, double cantidad)
            throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = Conexion.getConnection();
            con.setAutoCommit(false);

            Integer idSuministra = null;
            if (categoria.equals("Compra de Productos e Insumos")
                    && productosIds != null && !productosIds.isEmpty()
                    && proveedor != null && !proveedor.isEmpty()) {
                int idProveedor = obtenerIdProveedor(con, proveedor);
                idSuministra = insertarSuministra(con, productosIds.get(0), idProveedor);
            }

            String sql = "INSERT INTO caja (fecha, movimiento, monto, cantidad, descripcion, suministra_idSuministra, categoria) "
                    + "VALUES (?, 'egreso', ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setDate(1, fecha);
            ps.setDouble(2, monto);
            ps.setDouble(3, cantidad);
            ps.setString(4, descripcion);
            if (idSuministra != null) {
                ps.setInt(5, idSuministra);
            } else {
                ps.setNull(5, java.sql.Types.INTEGER);
            }
            ps.setString(6, categoria);

            int resultado = ps.executeUpdate();
            con.commit();
            return resultado > 0;
        } catch (SQLException e) {
            if (con != null) {
                con.rollback();
            }
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    private int insertarSuministra(Connection con, int idInventario, int idProveedor) throws SQLException {
        String sql = "INSERT INTO suministra (inventario_id_inventario, proveedor_id_proveedor) VALUES (?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, idInventario);
            ps.setInt(2, idProveedor);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        throw new SQLException("No se pudo insertar en suministra");
    }

    private int obtenerIdProveedor(Connection con, String nombreProveedor) throws SQLException {
        String sql = "SELECT id_proveedor FROM proveedor WHERE nombre = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombreProveedor);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_proveedor");
                }
            }
        }
        throw new SQLException("Proveedor no encontrado: " + nombreProveedor);
    }

    private int obtenerIdInventario(Connection con, String nombreProducto) throws SQLException {
        String sql = "SELECT id_inventario FROM inventario WHERE nombre = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombreProducto);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_inventario");
                }
            }
        }
        throw new SQLException("Producto no encontrado: " + nombreProducto);
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

}
