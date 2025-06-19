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
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
import vista.Inventario0.nuevoMateriales;
import vista.Produccion.Error_guardar;
import vista.Produccion.FormuEtapaProduccion;
import java.util.ArrayList;
import java.util.List;
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
        jPanel1.add(cmbMateriales, new org.netbeans.lib.awtextra.AbsoluteConstraints(270,360, 170, 30));

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
        btnGuardar = new rojeru_san.RSButtonRiple();
        btnCancelar1 = new rojeru_san.RSButtonRiple();
        rSLabelHora1 = new rojeru_san.rsdate.RSLabelHora();
        jLabel11CC = new javax.swing.JLabel();

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

        txtCantidadnuevo.setPlaceholder("");
        jPanel1.add(txtCantidadnuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 420, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setText("Cantidad a Ingresar:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, -1, 30));

        txtDetallenuevo.setForeground(new java.awt.Color(0, 0, 0));
        txtDetallenuevo.setPlaceholder("");
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
        jPanel1.add(comboProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 170, 30));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel8.setText("Proveedor");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setText("Producto");
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
        jPanel1.add(btnClienteN1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 330, 20, 20));

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
        jPanel1.add(btnClienteN, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 330, 20, 20));

        btnGuardar.setBackground(new java.awt.Color(46, 49, 82));
        btnGuardar.setText("Guardar");
        btnGuardar.setFont(new java.awt.Font("Humnst777 BlkCn BT", 1, 14)); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 460, 140, -1));

        btnCancelar1.setBackground(new java.awt.Color(46, 49, 82));
        btnCancelar1.setText("Volver");
        btnCancelar1.setFont(new java.awt.Font("Humnst777 BlkCn BT", 1, 14)); // NOI18N
        btnCancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 460, 140, -1));

        rSLabelHora1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(rSLabelHora1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 80, 120, -1));

        jLabel11CC.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel11CC.setText("Herramientas:");
        jPanel1.add(jLabel11CC, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, -1, -1));

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

        if (seleccion.equals("Compra de Productos e Insumos")) {
            jLabel8.setVisible(true);
            comboProveedor.setVisible(true);
            btnClienteN1.setVisible(true);
            jLabel3.setVisible(true);
            btnClienteN.setVisible(true);
            jLabel11CC.setVisible(true);
            cmbMateriales.setVisible(true);
            cmbHerramientas.setVisible(true);
        } else {
            jLabel8.setVisible(false);
            comboProveedor.setVisible(false);
            btnClienteN1.setVisible(false);
            jLabel3.setVisible(false);
            btnClienteN.setVisible(false);
            jLabel11CC.setVisible(false);
            cmbMateriales.setVisible(false);
            cmbHerramientas.setVisible(false);
        }
        jPanel1.revalidate();
        jPanel1.repaint(); // Forzar actualización de la interfaz

    }//GEN-LAST:event_comboCategoriaActionPerformed

    private void txtDetallenuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDetallenuevoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDetallenuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        Connection con = null;
        PreparedStatement ps = null;
        try {
            // Validaciones existentes
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

            java.sql.Date fecha = new java.sql.Date(txtPago.getDate().getTime());
            double monto = Double.parseDouble(txtCantidadnuevo.getText().trim().replace(",", "."));
            String descripcion = txtDetallenuevo.getText();
            String categoria = comboCategoria.getSelectedItem().toString();

            String proveedorSeleccionado = null;
            if (categoria.equals("Compra de Productos e Insumos")) {
                if (comboProveedor.getSelectedIndex() <= 0) {
                    mostrarError("Debe seleccionar un proveedor");
                    return;
                }
                proveedorSeleccionado = comboProveedor.getSelectedItem().toString();
            }

            if (monto <= 0) {
                mostrarError("El monto debe ser mayor que cero");
                return;
            }

            con = Conexion.getConnection();
            con.setAutoCommit(false);

            String sqlCaja = "INSERT INTO caja (fecha, movimiento, monto, descripcion, categoria) VALUES (?, 'egreso', ?, ?, ?)";
            ps = con.prepareStatement(sqlCaja);
            ps.setDate(1, fecha);
            ps.setDouble(2, monto);
            ps.setString(3, descripcion);
            ps.setString(4, categoria);
            int resultado = ps.executeUpdate();

            if (resultado > 0) {
                con.commit();
                mostrarMensaje("Egreso registrado correctamente");

                List<CheckableItem> seleccionados = new ArrayList<>();
                for (int i = 0; i < cmbMateriales.getModel().getSize(); i++) {
                    CheckableItem item = (CheckableItem) cmbMateriales.getModel().getElementAt(i);
                    if (item.isSelected()) {
                        seleccionados.add(item);
                    }
                }
                for (int i = 0; i < cmbHerramientas.getModel().getSize(); i++) {
                    CheckableItem item = (CheckableItem) cmbHerramientas.getModel().getElementAt(i);
                    if (item.isSelected()) {
                        seleccionados.add(item);
                    }
                }

                if (categoria.equals("Compra de Productos e Insumos") && !seleccionados.isEmpty()) {
                    FormularioMH formMH = new FormularioMH((Frame) this.getParent(), true, seleccionados);
                    formMH.setLocationRelativeTo(null);
                    formMH.setVisible(true);
                }
                this.dispose();
            } else {
                con.rollback();
                mostrarError("No se pudo registrar el egreso");
            }
        } catch (NumberFormatException e) {
            mostrarError("El monto debe ser un número válido con formato '12,50'");
        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException ex) {
                    mostrarError("Error al revertir transacción: " + ex.getMessage());
                }
            }
            mostrarError("Error al registrar el egreso: " + e.getMessage());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    mostrarError("Error al cerrar PreparedStatement: " + e.getMessage());
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    mostrarError("Error al cerrar conexión: " + e.getMessage());
                }
            }
        }
    }

    private void actualizarStockInventario(Connection con, int idInventario, double cantidad) throws SQLException {
        // Obtener la cantidad actual del inventario
        String sqlSelect = "SELECT cantidad FROM inventario WHERE id_inventario = ?";
        double cantidadActual = 0.0;
        try (PreparedStatement psSelect = con.prepareStatement(sqlSelect)) {
            psSelect.setInt(1, idInventario);
            try (ResultSet rs = psSelect.executeQuery()) {
                if (rs.next()) {
                    String cantidadStr = rs.getString("cantidad").trim();
                    cantidadActual = parseCantidad(cantidadStr);
                } else {
                    throw new SQLException("No se encontró el inventario con ID: " + idInventario);
                }
            }
        }

        // Sumar la nueva cantidad
        double nuevaCantidad = cantidadActual + cantidad;

        // Formatear la cantidad con coma como separador decimal
        java.text.NumberFormat numberFormat = java.text.NumberFormat.getNumberInstance(java.util.Locale.forLanguageTag("es-ES"));
        numberFormat.setMinimumFractionDigits(2);
        numberFormat.setMaximumFractionDigits(2);
        String cantidadFormateada = numberFormat.format(nuevaCantidad).replace(".", ",");

        // Actualizar la cantidad en inventario
        String sqlUpdate = "UPDATE inventario SET cantidad = ? WHERE id_inventario = ?";
        try (PreparedStatement psUpdate = con.prepareStatement(sqlUpdate)) {
            psUpdate.setString(1, cantidadFormateada); // Guardar como VARCHAR con coma
            psUpdate.setInt(2, idInventario);
            int affectedRows = psUpdate.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("No se pudo actualizar el stock para el inventario con ID: " + idInventario);
            }
            System.out.println("Stock actualizado para id_inventario " + idInventario + ": " + cantidadFormateada);
        }
    }

    private double parseCantidad(String cantidadStr) {
        try {
            // Normalizar el formato: reemplazar coma por punto y eliminar cualquier separador adicional
            String normalized = cantidadStr.replace(".", "").replace(",", ".");
            double cantidad = Double.parseDouble(normalized);
            return cantidad >= 0 ? cantidad : 0.0;
        } catch (NumberFormatException e) {
            System.err.println("Error al parsear cantidad: '" + cantidadStr + "' - " + e.getMessage());
            return 0.0;
        }

    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnClienteN1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClienteN1ActionPerformed
        proveedornuevo dialog = new proveedornuevo(new javax.swing.JFrame(), true);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

    }//GEN-LAST:event_btnClienteN1ActionPerformed

    private void btnClienteNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClienteNActionPerformed
        nuevoMateriales dialog = new nuevoMateriales(new javax.swing.JFrame(), true);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnClienteNActionPerformed

    private void btnCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelar1ActionPerformed

    private void comboProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboProveedorActionPerformed

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
    private rojeru_san.RSButtonRiple btnCancelar1;
    private RSMaterialComponent.RSButtonShape btnClienteN;
    private RSMaterialComponent.RSButtonShape btnClienteN1;
    private rojeru_san.RSButtonRiple btnGuardar;
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
