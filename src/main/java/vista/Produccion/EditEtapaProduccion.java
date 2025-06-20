/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package vista.Produccion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.basic.ComboPopup;
import modelo.Conexion;

/**
 *
 * @author pc
 */
public class EditEtapaProduccion extends javax.swing.JDialog {

    private int idProduccionActual;
    private int idEtapa;
    private boolean datosModificados;
    private CheckedComboBox<CheckableItem> cmbMateriales;
    private CheckedComboBox<CheckableItem> cmbHerramientas;

    /**
     * Creates new form EtapaProduccion
     *
     * @param parent
     * @param modal
     * @param idEtapa
     * @param produccionPanel
     */
    public EditEtapaProduccion(Frame parent, boolean modal, int idEtapa) {
    super(parent, modal);
    this.idEtapa = idEtapa;
    
    // Configurar undecorated antes de initComponents
    setUndecorated(true);
    initComponents(); // Llamada única a initComponents
    
    setLocationRelativeTo(parent); // Configurar ubicación después de initComponents
    
    // Inicializar CheckedComboBox
    cmbMateriales = new CheckedComboBox<>(makeProductModel("material"));
    cmbMateriales.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    jPanel1.add(cmbMateriales, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 180, 200, 30));

    cmbHerramientas = new CheckedComboBox<>(makeProductModel("herramienta"));
    cmbHerramientas.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    jPanel1.add(cmbHerramientas, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 260, 200, 30));

    // Configuración inicial
    if (idEtapa > 0) {
        cargarDatosEtapa(idEtapa);
    }
}

    private DefaultComboBoxModel<CheckableItem> makeProductModel(String tipo) {
        DefaultComboBoxModel<CheckableItem> model = new DefaultComboBoxModel<>();
        try {
            Connection con = new Conexion().getConnection();
            String sql = "SELECT nombre FROM inventario WHERE tipo = ? ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tipo);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                model.addElement(new CheckableItem(rs.getString("nombre"), false));
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(FormuEtapaProduccion.class.getName()).log(Level.SEVERE, null, ex);

            // Mostrar mensaje de error al usuario
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
// Clases internas para el CheckedComboBox

    class CheckableItem {

        private final String text;
        private boolean selected;

        protected CheckableItem(String text, boolean selected) {
            this.text = text;
            this.selected = selected;
        }

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }

        @Override
        public String toString() {
            return text;
        }
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
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtetapa = new RSMaterialComponent.RSTextFieldMaterial();
        jLabel11 = new javax.swing.JLabel();
        Boxestado = new RSMaterialComponent.RSComboBoxMaterial();
        txtFechainicio = new com.toedter.calendar.JDateChooser();
        txtfechafin = new com.toedter.calendar.JDateChooser();
        btnCancelar1 = new rojeru_san.RSButtonRiple();
        btnGuardar1 = new rojeru_san.RSButtonRiple();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(46, 49, 82));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Century751 BT", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Etapa Produccion");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 520, 50));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel6.setText("Estado:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 70, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel9.setText("Fecha final:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel10.setText("Nombre etapa:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        txtetapa.setForeground(new java.awt.Color(0, 0, 0));
        txtetapa.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtetapa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtetapa.setPhColor(new java.awt.Color(0, 0, 0));
        txtetapa.setPlaceholder("");
        txtetapa.setSelectionColor(new java.awt.Color(0, 0, 0));
        txtetapa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtetapaActionPerformed(evt);
            }
        });
        jPanel1.add(txtetapa, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 200, 30));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel11.setText("Materiales:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, -1, -1));

        Boxestado.setForeground(new java.awt.Color(102, 102, 102));
        Boxestado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar", "pendiente", "proceso", "completado" }));
        Boxestado.setFont(new java.awt.Font("Roboto Bold", 0, 14)); // NOI18N
        Boxestado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoxestadoActionPerformed(evt);
            }
        });
        jPanel1.add(Boxestado, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, -1, 30));

        txtFechainicio.setBackground(new java.awt.Color(255, 255, 255));
        txtFechainicio.setForeground(new java.awt.Color(255, 255, 255));
        txtFechainicio.setDateFormatString("y-MM-d");
        txtFechainicio.setMaxSelectableDate(new java.util.Date(253370786472000L));
        jPanel1.add(txtFechainicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 200, 30));

        txtfechafin.setBackground(new java.awt.Color(255, 255, 255));
        txtfechafin.setForeground(new java.awt.Color(255, 255, 255));
        txtfechafin.setDateFormatString("y-MM-d");
        jPanel1.add(txtfechafin, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 200, 30));

        btnCancelar1.setBackground(new java.awt.Color(46, 49, 82));
        btnCancelar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/salida (1).png"))); // NOI18N
        btnCancelar1.setText("Volver");
        btnCancelar1.setFont(new java.awt.Font("Humnst777 BlkCn BT", 1, 18)); // NOI18N
        btnCancelar1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnCancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 310, 140, -1));

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
        jPanel1.add(btnGuardar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 310, 140, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel12.setText("Fecha inicio:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel13.setText("Herramientas:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 230, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BoxestadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoxestadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BoxestadoActionPerformed

    private void btnCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelar1ActionPerformed

    private void btnGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar1ActionPerformed
    // 1. Validación de campos
    if (txtetapa.getText().trim().isEmpty()
            || txtFechainicio.getDate() == null
            || Boxestado.getSelectedIndex() <= 0) {
        new espacio_alerta((Frame) this.getParent(), true,
                "Error", "Todos los campos son obligatorios").setVisible(true);
        return;
    }
    // 2. Mostrar diálogo de confirmación
    alertaa confirmDialog = new alertaa(
            (Frame) this.getParent(),
            true,
            "Confirmar",
            "¿Desea guardar los datos?"
    );
    confirmDialog.setVisible(true);

    if (!confirmDialog.opcionConfirmada) {
        return;
    }

    try {
        // 3. Obtener valores del formulario
        String nombreEtapa = txtetapa.getText().trim();
        Date fechaInicio = new Date(txtFechainicio.getDate().getTime());
        Date fechaFin = txtfechafin.getDate() != null
                ? new Date(txtfechafin.getDate().getTime()) : null;
        String estado = Boxestado.getSelectedItem().toString();

        // 4. Validar fechas
        if (fechaFin != null && fechaFin.before(fechaInicio)) {
            new Error_fecha((Frame) this.getParent(), true,
                    "Error", "La fecha final no puede ser anterior a la inicial").setVisible(true);
            return;
        }

        // 5. Obtener materiales y herramientas seleccionados
        List<String> materialesSeleccionados = new ArrayList<>();
        List<String> herramientasSeleccionadas = new ArrayList<>();
        for (int i = 0; i < cmbMateriales.getModel().getSize(); i++) {
            CheckableItem item = cmbMateriales.getModel().getElementAt(i);
            if (item.isSelected()) {
                materialesSeleccionados.add(item.toString());
            }
        }
        for (int i = 0; i < cmbHerramientas.getModel().getSize(); i++) {
            CheckableItem item = cmbHerramientas.getModel().getElementAt(i);
            if (item.isSelected()) {
                herramientasSeleccionadas.add(item.toString());
            }
        }

        // 6. Abrir FormularioMH para editar cantidades
        FormularioMH formMH = new FormularioMH((Frame) this.getParent(), true, materialesSeleccionados, herramientasSeleccionadas);
        formMH.setVisible(true);

        if (formMH.isConfirmado()) {
            // 7. Actualizar etapa en la base de datos
            try (Connection con = Conexion.getConnection()) {
                String sql;
                if (idEtapa == 0) {
                    // Insertar nuevo registro
                    sql = "INSERT INTO etapa_produccion (nombre_etapa, fecha_inicio, fecha_fin, estado) "
                            + "VALUES (?, ?, ?, ?)";
                } else {
                    // Actualizar registro existente
                    sql = "UPDATE etapa_produccion SET nombre_etapa = ?, fecha_inicio = ?, "
                            + "fecha_fin = ?, estado = ? WHERE idetapa_produccion = ?";
                }

                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setString(1, nombreEtapa);
                    ps.setDate(2, fechaInicio);
                    ps.setDate(3, fechaFin);
                    ps.setString(4, estado);

                    if (idEtapa > 0) {
                        ps.setInt(5, idEtapa);
                    }

                    int affectedRows = ps.executeUpdate();
                    if (affectedRows > 0) {
                        this.datosModificados = true;
                        // 8. Actualizar cantidades de materiales y herramientas en la tabla utilizado
                        Map<String, String> cantidadesMateriales = formMH.getCantidadesMateriales();
                        Map<String, String> cantidadesHerramientas = formMH.getCantidadesHerramientas();

                        // Actualizar o insertar en la tabla utilizado
                        String updateUtilizado = "INSERT INTO utilizado (etapa_produccion_idetapa_produccion, inventario_id_inventario, cantidad_usada) "
                                + "VALUES (?, (SELECT id_inventario FROM inventario WHERE nombre = ?), ?) "
                                + "ON DUPLICATE KEY UPDATE cantidad_usada = ?";
                        try (PreparedStatement psUtilizado = con.prepareStatement(updateUtilizado)) {
                            if (idEtapa > 0) {
                                for (Map.Entry<String, String> entry : cantidadesMateriales.entrySet()) {
                                    psUtilizado.setInt(1, idEtapa);
                                    psUtilizado.setString(2, entry.getKey());
                                    psUtilizado.setDouble(3, Double.parseDouble(entry.getValue().replace(",", ".")));
                                    psUtilizado.setDouble(4, Double.parseDouble(entry.getValue().replace(",", ".")));
                                    psUtilizado.executeUpdate();
                                }
                                for (Map.Entry<String, String> entry : cantidadesHerramientas.entrySet()) {
                                    psUtilizado.setInt(1, idEtapa);
                                    psUtilizado.setString(2, entry.getKey());
                                    psUtilizado.setDouble(3, Double.parseDouble(entry.getValue().replace(",", ".")));
                                    psUtilizado.setDouble(4, Double.parseDouble(entry.getValue().replace(",", ".")));
                                    psUtilizado.executeUpdate();
                                }
                            }
                        }

                        
                        this.dispose();
                    }
                }
            }
        }
    } catch (SQLException e) {
        new Error_guardar((Frame) this.getParent(), true,
                "Error", "Error al guardar: " + e.getMessage()).setVisible(true);
        e.printStackTrace();
    }

    }//GEN-LAST:event_btnGuardar1ActionPerformed

    private void txtetapaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtetapaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtetapaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormuEtapaProduccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            private Produccion produccionPanel;
            private int idProduccion;

            @Override
            public void run() {
                FormuEtapaProduccion dialog = new FormuEtapaProduccion(new javax.swing.JFrame(), true, this.idProduccion);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RSMaterialComponent.RSComboBoxMaterial Boxestado;
    private rojeru_san.RSButtonRiple btnCancelar1;
    private rojeru_san.RSButtonRiple btnGuardar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private com.toedter.calendar.JDateChooser txtFechainicio;
    private RSMaterialComponent.RSTextFieldMaterial txtetapa;
    private com.toedter.calendar.JDateChooser txtfechafin;
    // End of variables declaration//GEN-END:variables

    public void setDatos(int idEtapa, String nombre, String cantidad, String fechaInicio, String fechaFin, String estado, String materiales, String herramientas, String asignado) {
        this.idEtapa = idEtapa;
        this.datosModificados = false; // Resetear estado de modificaciones

        try {
            // Establecer valores en los campos del formulario
            txtetapa.setText(nombre != null ? nombre : "");

            // Establecer estado
            if (estado != null && !estado.isEmpty()) {
                Boxestado.setSelectedItem(estado);
            }

            // Parsear y establecer fechas
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            if (fechaInicio != null && !fechaInicio.isEmpty() && fechaInicio.matches("\\d{4}-\\d{2}-\\d{2}")) {
                txtFechainicio.setDate(sdf.parse(fechaInicio));
            }

            if (fechaFin != null && !fechaFin.isEmpty() && fechaFin.matches("\\d{4}-\\d{2}-\\d{2}")) {
                txtfechafin.setDate(sdf.parse(fechaFin));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar datos: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean datosModificados() {
        return this.datosModificados;
    }

    private void cargarDatosEtapa(int idEtapa) {
        try (Connection con = Conexion.getConnection()) {
            // Cargar datos básicos de la etapa
            String sqlEtapa = "SELECT nombre_etapa, fecha_inicio, fecha_fin, estado, produccion_id_produccion "
                    + "FROM etapa_produccion WHERE idetapa_produccion = ?";
            try (PreparedStatement ps = con.prepareStatement(sqlEtapa)) {
                ps.setInt(1, idEtapa);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        txtetapa.setText(rs.getString("nombre_etapa"));
                        Boxestado.setSelectedItem(rs.getString("estado"));
                        txtFechainicio.setDate(rs.getDate("fecha_inicio"));
                        Date fechaFin = rs.getDate("fecha_fin");
                        if (fechaFin != null) {
                            txtfechafin.setDate(fechaFin);
                        }
                        idProduccionActual = rs.getInt("produccion_id_produccion");
                    } else {
                        throw new SQLException("No se encontró la etapa con ID: " + idEtapa);
                    }
                }
            }

            // Cargar materiales y herramientas usados
            cargarMaterialesHerramientasUsados(con, idEtapa);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar datos: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(EditEtapaProduccion.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void cargarMaterialesHerramientasUsados(Connection con, int idEtapa) throws SQLException {
        System.out.println("Cargando materiales y herramientas para idEtapa: " + idEtapa);
        System.out.println("cmbMateriales: " + (cmbMateriales != null ? "no es null" : "es null"));
        System.out.println("cmbHerramientas: " + (cmbHerramientas != null ? "no es null" : "es null"));

        String sql = "SELECT i.nombre, i.tipo, u.cantidad_usada "
                + "FROM utilizado u "
                + "JOIN inventario i ON u.inventario_id_inventario = i.id_inventario "
                + "WHERE u.etapa_produccion_idetapa_produccion = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idEtapa);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String nombre = rs.getString("nombre");
                    String tipo = rs.getString("tipo");
                    double cantidadUsada = rs.getDouble("cantidad_usada");

                    CheckedComboBox<CheckableItem> combo = tipo.equals("material") ? cmbMateriales : cmbHerramientas;
                    if (combo != null) {
                        for (int i = 0; i < combo.getModel().getSize(); i++) {
                            CheckableItem item = combo.getModel().getElementAt(i);
                            if (item.toString().equals(nombre)) {
                                item.setSelected(true);
                                System.out.println("Marcado " + tipo + ": " + nombre + " con cantidad usada: " + cantidadUsada);
                                break;
                            }
                        }
                    } else {
                        System.err.println("Combo es null para tipo: " + tipo);
                    }
                }
                if (cmbMateriales != null) {
                    cmbMateriales.repaint();
                }
                if (cmbHerramientas != null) {
                    cmbHerramientas.repaint();
                }
            }
        }
    }

}
