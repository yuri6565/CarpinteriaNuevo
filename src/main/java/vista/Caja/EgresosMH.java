/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package vista.Caja;

import java.awt.BorderLayout;
import vista.Produccion.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
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
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import modelo.CheckableItem;
import modelo.Conexion;
import rojeru_san.RSButtonRiple;

/**
 *
 * @author SENA
 */
public class EgresosMH extends javax.swing.JDialog {

    private final List<String> materialesSeleccionados;
    private final List<String> herramientasSeleccionadas;
    private JPanel panelMateriales;
    private JPanel panelHerramientas;
    private boolean confirmado = false;
    private Map<String, Double> inventarioMateriales = new HashMap<>();
    private Map<String, Double> inventarioHerramientas = new HashMap<>();
    private List<String> materialesAEliminar = new ArrayList<>();
    private List<String> herramientasAEliminar = new ArrayList<>();
    private Map<String, String> cantidadesHerramientasIniciales;
    private Map<String, String> cantidadesMaterialesIniciales;
    private final double cantidadTotal = 0;
    private JTextField txtCantidadnuevo;

    // Constructor principal
    public EgresosMH(Frame parent, boolean modal, List<CheckableItem> itemsSeleccionados, double cantidadTotal) {
        super(parent, modal);
        this.materialesSeleccionados = new ArrayList<>();
        this.herramientasSeleccionadas = new ArrayList<>();

        // Clasificación mejorada de items
        for (CheckableItem item : itemsSeleccionados) {
            // Consultar a la base de datos para determinar el tipo real
            String tipo = obtenerTipoItemDesdeBD(item.getId());

            if (tipo != null && tipo.equalsIgnoreCase("material")) {
                materialesSeleccionados.add(item.toString());
            } else if (tipo != null && tipo.equalsIgnoreCase("herramienta")) {
                herramientasSeleccionadas.add(item.toString());
            }
        }

        initComponents();
        configurarInterfaz();
    }

    private String obtenerTipoItemDesdeBD(int idItem) {
        String tipo = null;
        try (Connection con = Conexion.getConnection()) {
            String sql = "SELECT tipo FROM inventario WHERE id_inventario = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, idItem);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        tipo = rs.getString("tipo");
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(EgresosMH.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tipo;
    }

    private void configurarInterfaz() {
        cargarInventario();
        generarCamposDinamicos();

        // Configuración de los scroll panes
        ContenedorH.setBackground(new java.awt.Color(255, 255, 255));
        JScrollPane scrollPaneH = new JScrollPane(ContenedorH);
        scrollPaneH.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jPanel1.add(scrollPaneH, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 60, 360, 370));

        ContenedorM.setBackground(new java.awt.Color(255, 255, 255));
        JScrollPane scrollPaneM = new JScrollPane(ContenedorM);
        scrollPaneM.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jPanel1.add(scrollPaneM, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 360, 370));
    }

    private void cargarInventario() {
        inventarioMateriales.clear();
        inventarioHerramientas.clear();

        try (Connection con = Conexion.getConnection()) {
            // Cargar materiales
            String sqlMateriales = "SELECT i.nombre, i.cantidad, u.nombre AS unidad "
                    + "FROM inventario i "
                    + "JOIN unidad_medida u ON i.unidad_medida_idunidad_medida = u.idunidad_medida "
                    + "WHERE i.tipo = 'material' ";
            try (PreparedStatement ps = con.prepareStatement(sqlMateriales)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String nombre = rs.getString("nombre");
                    String cantidadStr = rs.getString("cantidad").trim();
                    double cantidad = parseCantidad(cantidadStr);
                    String unidad = rs.getString("unidad");
                    String clave = nombre + "|" + unidad;
                    inventarioMateriales.put(clave, cantidad);
                }
            }

            // Cargar herramientas
            String sqlHerramientas = "SELECT i.nombre, i.cantidad, u.nombre AS unidad "
                    + "FROM inventario i "
                    + "JOIN unidad_medida u ON i.unidad_medida_idunidad_medida = u.idunidad_medida "
                    + "WHERE i.tipo = 'herramienta' ";
            try (PreparedStatement ps = con.prepareStatement(sqlHerramientas)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String nombre = rs.getString("nombre");
                    String cantidadStr = rs.getString("cantidad").trim();
                    double cantidad = parseCantidad(cantidadStr);
                    String unidad = rs.getString("unidad");
                    String clave = nombre + "|" + unidad;
                    inventarioHerramientas.put(clave, cantidad);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(EgresosMH.class.getName()).log(Level.SEVERE, "Error al cargar inventario", ex);
            JOptionPane.showMessageDialog(this,
                    "Error al cargar el inventario: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void agregarCampoMaterial(String nombreMaterial) {
        NumberFormat formatter = NumberFormat.getNumberInstance(Locale.forLanguageTag("es-ES"));
        formatter.setMinimumFractionDigits(2);
        formatter.setMaximumFractionDigits(2);

        // Buscar en inventario de materiales
        String claveCompleta = inventarioMateriales.keySet().stream()
                .filter(k -> k.startsWith(nombreMaterial + "|"))
                .findFirst()
                .orElse(nombreMaterial + "|unidad");

        String[] partes = claveCompleta.split("\\|");
        String nombre = partes[0];
        String unidad = partes.length > 1 ? partes[1] : "unidad";
        double stockActual = inventarioMateriales.getOrDefault(claveCompleta, 0.0);

        // Crear fila con estilo original
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

        // Aplicar filtro de números
        ((AbstractDocument) txtCantidad.getDocument()).setDocumentFilter(new NumberFilter());

        fila.add(label);
        fila.add(txtCantidad);
        panelMateriales.add(fila);
        panelMateriales.add(Box.createVerticalStrut(5));
    }

    private void agregarCampoHerramienta(String nombreHerramienta) {
        NumberFormat formatter = NumberFormat.getNumberInstance(Locale.forLanguageTag("es-ES"));
        formatter.setMinimumFractionDigits(2);
        formatter.setMaximumFractionDigits(2);

        String claveCompleta = inventarioHerramientas.keySet().stream()
                .filter(k -> k.startsWith(nombreHerramienta + "|"))
                .findFirst()
                .orElse(nombreHerramienta + "|unidad");

        String[] partes = claveCompleta.split("\\|");
        String nombre = partes[0];
        String unidad = partes.length > 1 ? partes[1] : "unidad";
        double stockActual = inventarioHerramientas.getOrDefault(claveCompleta, 0.0);

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

        ((AbstractDocument) txtCantidad.getDocument()).setDocumentFilter(new NumberFilter());

        fila.add(label);
        fila.add(txtCantidad);
        panelHerramientas.add(fila); // CORRECCIÓN: Ahora se agrega al panel correcto
        panelHerramientas.add(Box.createVerticalStrut(5));
    }

    private void actualizarStockInventario(Connection con, int idInventario, double cantidad) throws SQLException {
        // Validar que la cantidad sea positiva
        if (cantidad <= 0) {
            throw new SQLException("La cantidad debe ser mayor que cero");
        }

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

        // Sumar la nueva cantidad (sin restricciones de máximo)
        double nuevaCantidad = cantidadActual + cantidad;

        // Formatear la cantidad
        java.text.NumberFormat numberFormat = java.text.NumberFormat.getNumberInstance(java.util.Locale.forLanguageTag("es-ES"));
        numberFormat.setMinimumFractionDigits(2);
        numberFormat.setMaximumFractionDigits(2);
        String cantidadFormateada = numberFormat.format(nuevaCantidad).replace(".", ",");

        // Actualizar el inventario
        String sqlUpdate = "UPDATE inventario SET cantidad = ? WHERE id_inventario = ?";
        try (PreparedStatement psUpdate = con.prepareStatement(sqlUpdate)) {
            psUpdate.setString(1, cantidadFormateada);
            psUpdate.setInt(2, idInventario);
            int affectedRows = psUpdate.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("No se pudo actualizar el stock");
            }
        }
    }

    private double parseCantidad(String cantidadStr) {
        try {
            String normalized = cantidadStr.replace(".", "").replace(",", ".");
            return Double.parseDouble(normalized);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    private void actualizarInventario(Connection con, List<CheckableItem> seleccionados) throws SQLException {
        try {
            // Validar que haya seleccionados
            if (seleccionados == null || seleccionados.isEmpty()) {
                throw new SQLException("No hay ítems seleccionados para actualizar");
            }

            // Obtener la cantidad del campo (asumiendo que es la misma para todos)
            double cantidad = Double.parseDouble(txtCantidadnuevo.getText().trim().replace(",", "."));

            // Validar cantidad positiva
            if (cantidad <= 0) {
                throw new SQLException("La cantidad debe ser mayor que cero");
            }

            // Actualizar cada ítem seleccionado
            for (CheckableItem item : seleccionados) {
                actualizarStockInventario(con, item.getId(), cantidad);
            }

        } catch (NumberFormatException e) {
            throw new SQLException("Formato de cantidad inválido: " + e.getMessage());
        }
    }

    public void setCantidadesIniciales(Map<String, String> materiales, Map<String, String> herramientas) {
        this.cantidadesMaterialesIniciales = materiales;
        this.cantidadesHerramientasIniciales = herramientas;
    }

    public List<String> getMaterialesAEliminar() {
        return materialesAEliminar;
    }

    public List<String> getHerramientasAEliminar() {
        return herramientasAEliminar;
    }

    private void mostrarError(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    

    public boolean isConfirmado() {
        return confirmado;
    }

    public Map<String, String> getCantidadesMateriales() {
        Map<String, String> cantidades = new HashMap<>();
        NumberFormat formatter = NumberFormat.getNumberInstance(Locale.forLanguageTag("es-ES"));

        for (Component comp : panelMateriales.getComponents()) {
            if (comp instanceof JPanel) {
                JPanel fila = (JPanel) comp;
                if (fila.getComponentCount() >= 2) {
                    JLabel label = (JLabel) fila.getComponent(0);
                    JTextField txtCantidad = (JTextField) fila.getComponent(1);

                    String nombreMaterial = obtenerNombreDeLabel(label.getText());
                    cantidades.put(nombreMaterial, txtCantidad.getText().trim());
                }
            }
        }
        return cantidades;
    }

    private String obtenerNombreDeLabel(String textoLabel) {
        // Extrae el nombre del material/herramienta del texto del label
        return textoLabel.replaceAll("<html><b>|</b>.*", "");
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
        jLabel1.setText("Etapa Produccion");
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
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar1ActionPerformed
        Connection con = null;
        try {
            NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.forLanguageTag("es-ES"));
            numberFormat.setMinimumFractionDigits(2);
            numberFormat.setMaximumFractionDigits(2);

            // Obtener conexión y comenzar transacción
            con = Conexion.getConnection();
            con.setAutoCommit(false);

            // Procesar MATERIALES (SUMAR al inventario)
            for (Component comp : panelMateriales.getComponents()) {
                if (comp instanceof JPanel) {
                    JPanel fila = (JPanel) comp;
                    JLabel label = (JLabel) fila.getComponent(0);
                    JTextField txtCantidad = (JTextField) fila.getComponent(1);

                    String textoLabel = label.getText();
                    String nombreMaterial = textoLabel.split("<b>")[1].split("</b>")[0].trim();
                    String cantidadStr = txtCantidad.getText().trim();

                    if (cantidadStr.isEmpty() || cantidadStr.equals("0,00")) {
                        continue;
                    }

                    double cantidad = numberFormat.parse(cantidadStr).doubleValue();

                    if (cantidad <= 0) {
                        con.rollback();
                        JOptionPane.showMessageDialog(this,
                                "La cantidad de " + nombreMaterial + " debe ser mayor que cero",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // SUMAR la cantidad al inventario (cambió el - por +)
                    String sql = "UPDATE inventario SET cantidad = cantidad + ? WHERE nombre = ? AND tipo = 'material'";
                    try (PreparedStatement ps = con.prepareStatement(sql)) {
                        ps.setDouble(1, cantidad);
                        ps.setString(2, nombreMaterial);
                        ps.executeUpdate();
                    }
                }
            }

            // Procesar HERRAMIENTAS (SUMAR al inventario)
            for (Component comp : panelHerramientas.getComponents()) {
                if (comp instanceof JPanel) {
                    JPanel fila = (JPanel) comp;
                    JLabel label = (JLabel) fila.getComponent(0);
                    JTextField txtCantidad = (JTextField) fila.getComponent(1);

                    String textoLabel = label.getText();
                    String nombreHerramienta = textoLabel.split("<b>")[1].split("</b>")[0].trim();
                    String cantidadStr = txtCantidad.getText().trim();

                    if (cantidadStr.isEmpty() || cantidadStr.equals("0,00")) {
                        continue;
                    }

                    double cantidad = numberFormat.parse(cantidadStr).doubleValue();

                    if (cantidad <= 0) {
                        con.rollback();
                        JOptionPane.showMessageDialog(this,
                                "La cantidad de " + nombreHerramienta + " debe ser mayor que cero",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // SUMAR la cantidad al inventario (cambió el - por +)
                    String sql = "UPDATE inventario SET cantidad = cantidad + ? WHERE nombre = ? AND tipo = 'herramienta'";
                    try (PreparedStatement ps = con.prepareStatement(sql)) {
                        ps.setDouble(1, cantidad);
                        ps.setString(2, nombreHerramienta);
                        ps.executeUpdate();
                    }
                }
            }

            // Confirmar transacción
            con.commit();
            confirmado = true;
            JOptionPane.showMessageDialog(this,
                    "Ingresos al inventario registrados correctamente",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();

        } catch (ParseException | NumberFormatException e) {
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException ex) {
            }
            JOptionPane.showMessageDialog(this,
                    "Error en formato de números: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException ex) {
            }
            JOptionPane.showMessageDialog(this,
                    "Error al actualizar inventario: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
            }
        }


    }//GEN-LAST:event_btnGuardar1ActionPerformed

    public Map<String, Double> getMaterialesActualizados() {
        Map<String, Double> materiales = new HashMap<>();
        NumberFormat formatter = NumberFormat.getNumberInstance(Locale.forLanguageTag("es-ES"));

        for (Component comp : panelMateriales.getComponents()) {
            if (comp instanceof JPanel) {
                JPanel fila = (JPanel) comp;
                JLabel label = (JLabel) fila.getComponent(0);
                JTextField txtCantidad = (JTextField) fila.getComponent(1);

                String nombre = label.getText().split("<b>")[1].split("</b>")[0].trim();
                try {
                    double cantidad = formatter.parse(txtCantidad.getText()).doubleValue();
                    materiales.put(nombre, cantidad);
                } catch (ParseException e) {
                    materiales.put(nombre, 0.0);
                }
            }
        }
        return materiales;
    }

    public Map<String, Double> getHerramientasActualizadas() {
        Map<String, Double> herramientas = new HashMap<>();
        NumberFormat formatter = NumberFormat.getNumberInstance(Locale.forLanguageTag("es-ES"));

        for (Component comp : panelHerramientas.getComponents()) {
            if (comp instanceof JPanel) {
                JPanel fila = (JPanel) comp;
                JLabel label = (JLabel) fila.getComponent(0);
                JTextField txtCantidad = (JTextField) fila.getComponent(1);

                String nombre = label.getText().split("<b>")[1].split("</b>")[0].trim();
                try {
                    double cantidad = formatter.parse(txtCantidad.getText()).doubleValue();
                    herramientas.put(nombre, cantidad);
                } catch (ParseException e) {
                    herramientas.put(nombre, 0.0);
                }
            }
        }
        return herramientas;
    }
    private void btnCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar1ActionPerformed
        this.setVisible(false);
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
            java.util.logging.Logger.getLogger(EgresosMH.class.getName()).log(Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            // Ejemplo con CheckableItems
            List<CheckableItem> seleccionados = new ArrayList<>();
            seleccionados.add(new CheckableItem(1, "Material 1", false));
            seleccionados.add(new CheckableItem(2, "Material 2", false));
            seleccionados.add(new CheckableItem(3, "Herramienta 1", false));
            seleccionados.add(new CheckableItem(4, "Herramienta 2", false));

            EgresosMH dialog = new EgresosMH(new javax.swing.JFrame(), true, seleccionados, 0.0);
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
private void generarCamposDinamicos() {
        panelMateriales = new JPanel();
        panelMateriales.setLayout(new BoxLayout(panelMateriales, BoxLayout.Y_AXIS));
        panelMateriales.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                "Materiales",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                new Font("Segoe UI", Font.BOLD, 18)
        ));
        panelMateriales.setBackground(Color.WHITE);

        panelHerramientas = new JPanel();
        panelHerramientas.setLayout(new BoxLayout(panelHerramientas, BoxLayout.Y_AXIS));
        panelHerramientas.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                "Herramientas",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                new Font("Segoe UI", Font.BOLD, 18)
        ));
        panelHerramientas.setBackground(Color.WHITE);

        // Agregar campos para materiales
        for (String material : materialesSeleccionados) {
            agregarCampoMaterial(material);
        }

        // Agregar campos para herramientas
        for (String herramienta : herramientasSeleccionadas) {
            agregarCampoHerramienta(herramienta);
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
    }

    private class NumberFilter extends DocumentFilter {

        private Double maxQuantity = null; // Ahora es opcional
        private final NumberFormat numberFormat;

        // Constructor original (para compatibilidad)
        public NumberFilter(double maxQuantity) {
            this.maxQuantity = maxQuantity;
            this.numberFormat = NumberFormat.getNumberInstance(Locale.forLanguageTag("es-ES"));
            numberFormat.setMinimumFractionDigits(0);
            numberFormat.setMaximumFractionDigits(2);
        }

        // Nuevo constructor sin restricciones
        public NumberFilter() {
            this.numberFormat = NumberFormat.getNumberInstance(Locale.forLanguageTag("es-ES"));
            numberFormat.setMinimumFractionDigits(0);
            numberFormat.setMaximumFractionDigits(2);
        }

        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                throws BadLocationException {
            String newStr = fb.getDocument().getText(0, fb.getDocument().getLength()) + string;
            if (isValidInput(newStr)) {
                super.insertString(fb, offset, string, attr);
            } else {
                Toolkit.getDefaultToolkit().beep();
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                throws BadLocationException {
            String newStr = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
            if (isValidInput(newStr)) {
                super.replace(fb, offset, length, text, attrs);
            } else {
                Toolkit.getDefaultToolkit().beep();
            }
        }

        private boolean isValidInput(String input) {
            if (input.isEmpty()) {
                return true;
            }
            try {
                Number parsed = numberFormat.parse(input.replace(",", "."));
                double value = parsed.doubleValue();
                // Solo verifica el máximo si maxQuantity no es null
                return value >= 0 && (maxQuantity == null || value <= maxQuantity);
            } catch (ParseException e) {
                return false;
            }
        }
    }
}
