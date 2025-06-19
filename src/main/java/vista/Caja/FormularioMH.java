/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package vista.Caja;

import vista.Produccion.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import modelo.Conexion;
import modelo.CheckableItem;
import java.util.List;

/**
 *
 * @author SENA
 */
public class FormularioMH extends javax.swing.JDialog {

    private List<CheckableItem> seleccionados;
    private JPanel panelMateriales;
    private JPanel panelHerramientas;
    private Map<Integer, Double> inventarioMap = new HashMap<>();
    private boolean confirmado = false;

    public FormularioMH(Frame parent, boolean modal, List<CheckableItem> seleccionados) {
        super(parent, modal);
        this.seleccionados = seleccionados;
        System.out.println("Elementos seleccionados: " + seleccionados);
        initComponents();

        // Configurar el layout principal
        jPanel1.setLayout(new java.awt.BorderLayout());

        // Configurar los paneles de desplazamiento
        ContenedorM.setBackground(new java.awt.Color(255, 255, 255));
        JScrollPane scrollPaneM = new JScrollPane(ContenedorM);
        scrollPaneM.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jPanel1.add(scrollPaneM, java.awt.BorderLayout.WEST);

        ContenedorH.setBackground(new java.awt.Color(255, 255, 255));
        JScrollPane scrollPaneH = new JScrollPane(ContenedorH);
        scrollPaneH.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jPanel1.add(scrollPaneH, java.awt.BorderLayout.EAST);

        // Agregar el panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(btnCancelar1);
        buttonPanel.add(btnGuardar1);
        jPanel1.add(buttonPanel, java.awt.BorderLayout.SOUTH);

        // Cargar datos y generar campos
        cargarInventario();
        generarCamposDinamicos();

        // Establecer tamaño preferido (ajustable)
        setPreferredSize(new Dimension(750, 515));
        pack(); // Ajusta el tamaño automáticamente según los componentes
    }

    private void cargarInventario() {
        inventarioMap.clear();
        System.out.println("Iniciando carga de inventario...");

        try (Connection con = Conexion.getConnection()) {
            String sql = "SELECT id_inventario, cantidad, u.nombre AS unidad "
                    + "FROM inventario i "
                    + "JOIN unidad_medida u ON i.unidad_medida_idunidad_medida = u.idunidad_medida";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id_inventario");
                    String cantidadStr = rs.getString("cantidad").trim();
                    System.out.println("Cantidad cruda para id " + id + ": '" + cantidadStr + "'");
                    double cantidad = parseCantidad(cantidadStr);
                    System.out.println("Cantidad parseada para id " + id + ": " + cantidad);
                    inventarioMap.put(id, cantidad);
                }
                System.out.println("Total elementos cargados: " + inventarioMap.size());
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormularioMH.class.getName()).log(Level.SEVERE, "Error al cargar inventario", ex);
            JOptionPane.showMessageDialog(this,
                    "Error al cargar el inventario: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private double parseCantidad(String cantidadStr) {
        try {
            String normalized = cantidadStr.replace(".", "").replace(",", ".");
            double cantidad = Double.parseDouble(normalized);
            return cantidad >= 0 ? cantidad : 0.0;
        } catch (NumberFormatException e) {
            System.err.println("Error al parsear cantidad: '" + cantidadStr + "' - " + e.getMessage());
            return 0.0;
        }
    }

    private void generarCamposDinamicos() {
        panelMateriales = new JPanel();
        panelMateriales.setLayout(new BoxLayout(panelMateriales, BoxLayout.Y_AXIS));
        panelMateriales.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                "Materiales",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new Font("Segoe UI", Font.BOLD, 18)
        ));
        panelMateriales.setBackground(Color.WHITE);

        panelHerramientas = new JPanel();
        panelHerramientas.setLayout(new BoxLayout(panelHerramientas, BoxLayout.Y_AXIS));
        panelHerramientas.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                "Herramientas",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new Font("Segoe UI", Font.BOLD, 18)
        ));
        panelHerramientas.setBackground(Color.WHITE);

        NumberFormat formatter = NumberFormat.getNumberInstance(Locale.forLanguageTag("es-ES"));
        formatter.setMinimumFractionDigits(2);
        formatter.setMaximumFractionDigits(2);

        for (CheckableItem item : seleccionados) {
            int id = item.getId();
            String nombre = item.toString();
            double stockActual = inventarioMap.getOrDefault(id, 0.0);
            String unidad = "unidad"; // Placeholder, ajustar si necesitas la unidad real

            JPanel fila = new JPanel(new FlowLayout(FlowLayout.LEFT));
            fila.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            fila.setBackground(Color.WHITE);

            JLabel label = new JLabel(String.format("<html><b>%s</b> (Stock: %s %s)</html>",
                    nombre, formatter.format(stockActual), unidad));
            label.setFont(new Font("Segoe UI", Font.PLAIN, 16));

            JTextField txtCantidad = new JTextField(formatter.format(0.0));
            txtCantidad.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            txtCantidad.setForeground(Color.BLACK);
            txtCantidad.setPreferredSize(new Dimension(100, 30));
            ((javax.swing.text.AbstractDocument) txtCantidad.getDocument())
                    .setDocumentFilter(new NumberFilter(stockActual));

            fila.add(label);
            fila.add(txtCantidad);

            if (item.toString().toLowerCase().contains("material")) {
                panelMateriales.add(fila);
            } else {
                panelHerramientas.add(fila);
            }
            fila.getParent().add(Box.createVerticalStrut(5));
        }

        ContenedorM.setLayout(new BoxLayout(ContenedorM, BoxLayout.Y_AXIS));
        ContenedorM.removeAll();
        ContenedorM.add(panelMateriales);

        ContenedorH.setLayout(new BoxLayout(ContenedorH, BoxLayout.Y_AXIS));
        ContenedorH.removeAll();
        ContenedorH.add(panelHerramientas);

        ContenedorM.revalidate();
        ContenedorM.repaint();
        ContenedorH.revalidate();
        ContenedorH.repaint();

        this.setSize(750, 515); // Tamaño fijo
    }
// DocumentFilter to restrict input to numbers and max quantity

    private class NumberFilter extends DocumentFilter {

        private final double maxQuantity;
        private final NumberFormat numberFormat;

        public NumberFilter(double maxQuantity) {
            this.maxQuantity = maxQuantity;
            this.numberFormat = NumberFormat.getNumberInstance(Locale.forLanguageTag("es-ES"));
            numberFormat.setMinimumFractionDigits(0);
            numberFormat.setMaximumFractionDigits(2);
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                throws BadLocationException {
            String newStr = fb.getDocument().getText(0, fb.getDocument().getLength());
            newStr = newStr.substring(0, offset) + text + newStr.substring(offset + length);

            if (isValidInput(newStr)) {
                super.replace(fb, offset, length, text, attrs);
            } else {
                Toolkit.getDefaultToolkit().beep();
            }
        }

        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                throws BadLocationException {
            String newStr = fb.getDocument().getText(0, fb.getDocument().getLength());
            newStr = newStr.substring(0, offset) + string + newStr.substring(offset);

            if (isValidInput(newStr)) {
                super.insertString(fb, offset, string, attr);
            } else {
                Toolkit.getDefaultToolkit().beep();
            }
        }

        private boolean isValidInput(String input) {
            if (input.isEmpty()) {
                return true;
            }
            if (!input.matches("\\d*,?\\d{0,2}")) {
                return false;
            }
            try {
                Number parsed = numberFormat.parse(input);
                double value = parsed.doubleValue();
                return value >= 0 && value <= maxQuantity;
            } catch (ParseException e) {
                return false;
            }
        }
    }

    public boolean isConfirmado() {
        return confirmado;
    }

    public Map<String, String> getCantidadesMateriales() {
        Map<String, String> cantidades = new HashMap<>();
        NumberFormat formatter = NumberFormat.getNumberInstance(Locale.forLanguageTag("es-ES"));
        formatter.setMinimumFractionDigits(2);
        formatter.setMaximumFractionDigits(2);

        for (Component comp : panelMateriales.getComponents()) {
            if (comp instanceof JPanel) {
                JPanel fila = (JPanel) comp;
                JLabel label = (JLabel) fila.getComponent(0);
                JTextField txtCantidad = (JTextField) fila.getComponent(1);

                String textoLabel = label.getText();
                String nombreMaterial = textoLabel.split("<b>")[1].split("</b>")[0].trim();

                String cantidadStr = txtCantidad.getText().trim();
                try {
                    double cantidad = cantidadStr.isEmpty() || cantidadStr.equals("0,00") ? 0.0
                            : formatter.parse(cantidadStr).doubleValue();
                    cantidades.put(nombreMaterial, formatter.format(cantidad));
                } catch (ParseException e) {
                    cantidades.put(nombreMaterial, "");
                }
            }
        }
        return cantidades;
    }

    public Map<String, String> getCantidadesHerramientas() {
        Map<String, String> cantidades = new HashMap<>();
        NumberFormat formatter = NumberFormat.getNumberInstance(Locale.forLanguageTag("es-ES"));
        formatter.setMinimumFractionDigits(2);
        formatter.setMaximumFractionDigits(2);

        for (Component comp : panelHerramientas.getComponents()) {
            if (comp instanceof JPanel) {
                JPanel fila = (JPanel) comp;
                JLabel label = (JLabel) fila.getComponent(0);
                JTextField txtCantidad = (JTextField) fila.getComponent(1);

                String textoLabel = label.getText();
                String nombreHerramienta = textoLabel.split("<b>")[1].split("</b>")[0].trim();

                String cantidadStr = txtCantidad.getText().trim();
                try {
                    double cantidad = cantidadStr.isEmpty() || cantidadStr.equals("0,00") ? 0.0
                            : formatter.parse(cantidadStr).doubleValue();
                    cantidades.put(nombreHerramienta, formatter.format(cantidad));
                } catch (ParseException e) {
                    cantidades.put(nombreHerramienta, "");
                }
            }
        }
        return cantidades;
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
        btnGuardar1 = new rojeru_san.RSButtonRiple();
        btnCancelar1 = new rojeru_san.RSButtonRiple();
        ContenedorM = new javax.swing.JPanel();
        ContenedorH = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(46, 49, 82));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Century751 BT", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Egreso");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, 50));

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
        jPanel1.add(btnGuardar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 460, 140, -1));

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
        jPanel1.add(btnCancelar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 460, 140, -1));

        ContenedorM.setBackground(new java.awt.Color(255, 255, 255));
        ContenedorM.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout ContenedorMLayout = new javax.swing.GroupLayout(ContenedorM);
        ContenedorM.setLayout(ContenedorMLayout);
        ContenedorMLayout.setHorizontalGroup(
            ContenedorMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 360, Short.MAX_VALUE)
        );
        ContenedorMLayout.setVerticalGroup(
            ContenedorMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 370, Short.MAX_VALUE)
        );

        jPanel1.add(ContenedorM, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 360, 370));

        ContenedorH.setBackground(new java.awt.Color(255, 255, 255));
        ContenedorH.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout ContenedorHLayout = new javax.swing.GroupLayout(ContenedorH);
        ContenedorH.setLayout(ContenedorHLayout);
        ContenedorHLayout.setHorizontalGroup(
            ContenedorHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 360, Short.MAX_VALUE)
        );
        ContenedorHLayout.setVerticalGroup(
            ContenedorHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 370, Short.MAX_VALUE)
        );

        jPanel1.add(ContenedorH, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 60, 360, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar1ActionPerformed
        Connection con = null;
        try {
            con = Conexion.getConnection();
            con.setAutoCommit(false);

            NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.forLanguageTag("es-ES"));
            numberFormat.setMinimumFractionDigits(2);
            numberFormat.setMaximumFractionDigits(2);

            // Actualizar cantidades sin restricciones
            for (Component comp : panelMateriales.getComponents()) {
                if (comp instanceof JPanel) {
                    JPanel fila = (JPanel) comp;
                    JLabel label = (JLabel) fila.getComponent(0);
                    JTextField txtCantidad = (JTextField) fila.getComponent(1);

                    String textoLabel = label.getText();
                    String nombre = textoLabel.split("<b>")[1].split("</b>")[0].trim();
                    String cantidadStr = txtCantidad.getText().trim();
                    double cantidad = cantidadStr.isEmpty() || cantidadStr.equals("0,00") ? 0.0
                            : numberFormat.parse(cantidadStr).doubleValue();

                    // Buscar id_inventario
                    int idInventario = encontrarIdInventario(con, nombre);
                    if (idInventario != -1) {
                        actualizarStock(con, idInventario, cantidad);
                    }
                }
            }

            for (Component comp : panelHerramientas.getComponents()) {
                if (comp instanceof JPanel) {
                    JPanel fila = (JPanel) comp;
                    JLabel label = (JLabel) fila.getComponent(0);
                    JTextField txtCantidad = (JTextField) fila.getComponent(1);

                    String textoLabel = label.getText();
                    String nombre = textoLabel.split("<b>")[1].split("</b>")[0].trim();
                    String cantidadStr = txtCantidad.getText().trim();
                    double cantidad = cantidadStr.isEmpty() || cantidadStr.equals("0,00") ? 0.0
                            : numberFormat.parse(cantidadStr).doubleValue();

                    // Buscar id_inventario
                    int idInventario = encontrarIdInventario(con, nombre);
                    if (idInventario != -1) {
                        actualizarStock(con, idInventario, cantidad);
                    }
                }
            }

            con.commit();
            confirmado = true;
            JOptionPane.showMessageDialog(this, "Stock actualizado correctamente");
            this.dispose();

        } catch (ParseException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Ingrese valores numéricos válidos (ej: 10,50)",
                    "Error", JOptionPane.ERROR_MESSAGE);
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al revertir transacción: " + ex.getMessage());
            }
            e.printStackTrace();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Error al actualizar el stock: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al revertir transacción: " + ex.getMessage());
            }
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "Error al cerrar conexión: " + e.getMessage());
                }
            }
        }

    }//GEN-LAST:event_btnGuardar1ActionPerformed
    private int encontrarIdInventario(Connection con, String nombre) throws SQLException {
        String sql = "SELECT id_inventario FROM inventario WHERE nombre = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_inventario");
                }
            }
        }
        System.err.println("No se encontró id_inventario para: " + nombre);
        return -1;
    }

    private void actualizarStock(Connection con, int idInventario, double cantidad) throws SQLException {
        String sqlSelect = "SELECT cantidad FROM inventario WHERE id_inventario = ?";
        double cantidadActual = 0.0;
        try (PreparedStatement psSelect = con.prepareStatement(sqlSelect)) {
            psSelect.setInt(1, idInventario);
            try (ResultSet rs = psSelect.executeQuery()) {
                if (rs.next()) {
                    String cantidadStr = rs.getString("cantidad").trim();
                    cantidadActual = parseCantidad(cantidadStr);
                }
            }
        }
        double nuevaCantidad = cantidadActual + cantidad;
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.forLanguageTag("es-ES"));
        numberFormat.setMinimumFractionDigits(2);
        numberFormat.setMaximumFractionDigits(2);
        String cantidadFormateada = numberFormat.format(nuevaCantidad).replace(".", ",");

        String sqlUpdate = "UPDATE inventario SET cantidad = ? WHERE id_inventario = ?";
        try (PreparedStatement psUpdate = con.prepareStatement(sqlUpdate)) {
            psUpdate.setString(1, cantidadFormateada);
            psUpdate.setInt(2, idInventario);
            int affectedRows = psUpdate.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("No se pudo actualizar el stock para id_inventario: " + idInventario);
            }
            System.out.println("Stock actualizado para id_inventario " + idInventario + ": " + cantidadFormateada);
        }
    }
    private void btnCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelar1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(FormularioMH.class.getName()).log(Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            List<CheckableItem> seleccionados = new ArrayList<>();
            seleccionados.add(new CheckableItem(1, "Material1", false));
            seleccionados.add(new CheckableItem(2, "Herramienta1", false));
            FormularioMH dialog = new FormularioMH(new javax.swing.JFrame(), true, seleccionados);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ContenedorH;
    private javax.swing.JPanel ContenedorM;
    private rojeru_san.RSButtonRiple btnCancelar1;
    private rojeru_san.RSButtonRiple btnGuardar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
