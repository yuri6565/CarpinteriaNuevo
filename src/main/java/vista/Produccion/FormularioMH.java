/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package vista.Produccion;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import modelo.Conexion;

/**
 *
 * @author SENA
 */
public class FormularioMH extends javax.swing.JDialog {

    private List<String> materialesSeleccionados;
    private List<String> herramientasSeleccionadas;
    private JPanel panelMateriales;
    private JPanel panelHerramientas;
    private boolean confirmado = false;
    private Map<String, Integer> inventarioMateriales = new HashMap<>();
    private Map<String, Integer> inventarioHerramientas = new HashMap<>();

    public FormularioMH(Frame parent, boolean modal, List<String> materiales, List<String> herramientasLista) {
        super(parent, modal);
        this.materialesSeleccionados = materiales;
        this.herramientasSeleccionadas = herramientasLista;
        this.inventarioMateriales = inventarioMateriales;
        this.inventarioHerramientas = inventarioHerramientas;
        initComponents();
        generarCamposDinamicos();
        // Configurar JScrollPane para ContenedorH
        ContenedorH.setBackground(new java.awt.Color(255, 255, 255));
        JScrollPane scrollPaneH = new JScrollPane(ContenedorH);
        scrollPaneH.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jPanel1.add(scrollPaneH, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 60, 360, 370));

// Configurar JScrollPane para ContenedorM
        ContenedorM.setBackground(new java.awt.Color(255, 255, 255));
        JScrollPane scrollPaneM = new JScrollPane(ContenedorM);
        scrollPaneM.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jPanel1.add(scrollPaneM, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 360, 370));
    }

    private void cargarInventario() {
        try (Connection con = Conexion.getConnection()) {
            // Load materials
            String sqlMateriales = "SELECT nombre, cantidad FROM inventario WHERE tipo = 'material' AND estado = 'disponible'";
            try (PreparedStatement ps = con.prepareStatement(sqlMateriales)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    inventarioMateriales.put(rs.getString("nombre"), rs.getInt("cantidad"));
                }
            }

            // Load tools
            String sqlHerramientas = "SELECT nombre, cantidad FROM inventario WHERE tipo = 'herramienta' AND estado = 'disponible'";
            try (PreparedStatement ps = con.prepareStatement(sqlHerramientas)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    inventarioHerramientas.put(rs.getString("nombre"), rs.getInt("cantidad"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormularioMH.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error al cargar inventario: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void generarCamposDinamicos() {
        panelMateriales = new JPanel();
        panelMateriales.setLayout(new BoxLayout(panelMateriales, BoxLayout.Y_AXIS));
        panelMateriales.setBorder(BorderFactory.createTitledBorder("Materiales"));
        panelMateriales.setBackground(Color.WHITE);
        panelHerramientas = new JPanel();
        panelHerramientas.setLayout(new BoxLayout(panelHerramientas, BoxLayout.Y_AXIS));
        panelHerramientas.setBorder(BorderFactory.createTitledBorder("Herramientas"));
        panelHerramientas.setBackground(Color.WHITE);

        // Agregar campos para materiales
        for (String material : materialesSeleccionados) {
            agregarCampoMaterial(material);
        }

        // Agregar campos para herramientas
        for (String herramienta : herramientasSeleccionadas) {
            agregarCampoHerramienta(herramienta);
        }

        // Añadir panelMateriales a ContenedorM
        ContenedorM.setLayout(new BoxLayout(ContenedorM, BoxLayout.Y_AXIS));
        ContenedorM.removeAll();
        ContenedorM.add(panelMateriales);

        // Añadir panelHerramientas a ContenedorH
        ContenedorH.setLayout(new BoxLayout(ContenedorH, BoxLayout.Y_AXIS));
        ContenedorH.removeAll();
        ContenedorH.add(panelHerramientas);

        // Forzar revalidación y repintado
        ContenedorM.revalidate();
        ContenedorM.repaint();
        ContenedorH.revalidate();
        ContenedorH.repaint();

        // Ajustar tamaño del JDialog (fijo para permitir scroll)
        this.setSize(520, 700); // Tamaño fijo suficiente para título, botones y área de scroll
        this.pack();
    }

    private void agregarCampoMaterial(String nombreMaterial) {
        JPanel fila = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fila.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        fila.setBackground(Color.WHITE);

        // Mostrar nombre y cantidad disponible
        int disponible = inventarioMateriales.getOrDefault(nombreMaterial, 0);
        JLabel label = new JLabel(nombreMaterial + " (Disponible: " + disponible + "):");
        fila.add(label);

        JTextField txtCantidad = new JTextField("0");
        txtCantidad.setForeground(Color.BLACK);
        txtCantidad.setPreferredSize(new Dimension(100, 30));

        // Placeholder simulation
        txtCantidad.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (txtCantidad.getText().equals("Cantidad")) {
                    txtCantidad.setText("");
                    txtCantidad.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (txtCantidad.getText().isEmpty()) {
                    txtCantidad.setText("Cantidad");
                    txtCantidad.setForeground(new Color(153, 153, 153));
                }
            }
        });

        fila.add(txtCantidad);
        panelMateriales.add(fila);
        panelMateriales.add(Box.createVerticalStrut(5));
    }

    private void agregarCampoHerramienta(String nombreHerramienta) {
        JPanel fila = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fila.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        fila.setBackground(Color.WHITE);

        // Mostrar nombre y cantidad disponible
        int disponible = inventarioHerramientas.getOrDefault(nombreHerramienta, 0);
        JLabel label = new JLabel(nombreHerramienta + " (Disponible: " + disponible + "):");
        fila.add(label);

        JTextField txtObservacion = new JTextField("Observación");
        txtObservacion.setForeground(new Color(153, 153, 153));
        txtObservacion.setPreferredSize(new Dimension(200, 30));

        // Placeholder simulation
        txtObservacion.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (txtObservacion.getText().equals("Observación")) {
                    txtObservacion.setText("");
                    txtObservacion.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (txtObservacion.getText().isEmpty()) {
                    txtObservacion.setText("Observación");
                    txtObservacion.setForeground(new Color(153, 153, 153));
                }
            }
        });

        fila.add(txtObservacion);
        panelHerramientas.add(fila);
        panelHerramientas.add(Box.createVerticalStrut(5));
    }
// DocumentFilter to restrict input to numbers and max quantity

    private class NumberFilter extends DocumentFilter {

        private final int maxQuantity;

        public NumberFilter(int maxQuantity) {
            this.maxQuantity = maxQuantity;
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            String newStr = fb.getDocument().getText(0, fb.getDocument().getLength());
            newStr = newStr.substring(0, offset) + text + newStr.substring(offset + length);
            if (newStr.matches("\\d*") && (newStr.isEmpty() || Integer.parseInt(newStr) <= maxQuantity)) {
                super.replace(fb, offset, length, text, attrs);
            }
        }

        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            String newStr = fb.getDocument().getText(0, fb.getDocument().getLength());
            newStr = newStr.substring(0, offset) + string + newStr.substring(offset);
            if (newStr.matches("\\d*") && (newStr.isEmpty() || Integer.parseInt(newStr) <= maxQuantity)) {
                super.insertString(fb, offset, string, attr);
            }
        }
    }

    public boolean isConfirmado() {
        return confirmado;
    }

    public Map<String, String> getCantidadesMateriales() {
        Map<String, String> cantidades = new HashMap<>();
        for (Component comp : panelMateriales.getComponents()) {
            if (comp instanceof JPanel) {
                JPanel fila = (JPanel) comp;
                JLabel label = (JLabel) fila.getComponent(0);
                JTextField txtCantidad = (JTextField) fila.getComponent(1);
                String nombreMaterial = label.getText().split("\\(")[0].trim();
                String cantidad = txtCantidad.getText().trim();
                cantidades.put(nombreMaterial, cantidad.isEmpty() ? "0" : cantidad);
            }
        }
        return cantidades;
    }

    public Map<String, String> getObservacionesHerramientas() {
        Map<String, String> observaciones = new HashMap<>();
        for (Component comp : panelHerramientas.getComponents()) {
            if (comp instanceof JPanel) {
                JPanel fila = (JPanel) comp;
                JLabel label = (JLabel) fila.getComponent(0);
                JTextField txtObservacion = (JTextField) fila.getComponent(1);
                String nombreHerramienta = label.getText().split("\\(")[0].trim();
                String observacion = txtObservacion.getText().trim();
                if (observacion.equals("Observación")) {
                    observacion = "";
                }
                observaciones.put(nombreHerramienta, observacion.isEmpty() ? "Sin observación" : observacion);
            }
        }
        return observaciones;
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
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar1ActionPerformed
        try {
            // Validar cantidades de materiales
            for (Component comp : panelMateriales.getComponents()) {
                if (comp instanceof JPanel) {
                    JPanel fila = (JPanel) comp;
                    JLabel label = (JLabel) fila.getComponent(0);
                    JTextField txtCantidad = (JTextField) fila.getComponent(1);

                    String nombreMaterial = label.getText().split("\\(")[0].trim();
                    int disponible = inventarioMateriales.getOrDefault(nombreMaterial, 0);
                    int cantidad = txtCantidad.getText().equals("Cantidad") ? 0
                            : Integer.parseInt(txtCantidad.getText());

                    if (cantidad > disponible) {
                        JOptionPane.showMessageDialog(this,
                                "La cantidad de " + nombreMaterial + " excede el inventario",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            }

            confirmado = true;
            this.dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Ingrese valores numéricos válidos",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnGuardar1ActionPerformed

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
            List<String> materiales = new ArrayList<>();
            materiales.add("Material1");
            materiales.add("Material2");
            List<String> herramientas = new ArrayList<>();
            herramientas.add("Herramienta1");
            herramientas.add("Herramienta2");

            FormularioMH dialog = new FormularioMH(new javax.swing.JFrame(), true, materiales, herramientas);
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
