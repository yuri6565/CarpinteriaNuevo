/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package vista;

import controlador.Ctrl_Proveedor;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
import modelo.ProveedorDatos;


public class proveedornuevo extends javax.swing.JDialog {
public boolean guardado = false;


    private CheckedComboBox<CheckableItem> cmbProducto;
    private Ctrl_Proveedor ctrlProveedor;

    public proveedornuevo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setTitle("Nuevo Proveedor");
        

txtNombre.addActionListener(e -> txttelefono.requestFocus());

txttelefono.addActionListener(e -> txtcorreo.requestFocus());

txtcorreo.addActionListener(e -> txtdireccion.requestFocus());
    
        ctrlProveedor = new Ctrl_Proveedor();
        cmbProducto = new CheckedComboBox<>(makeProductModel());
        cmbProducto.setFont(new Font("Segoe UI", Font.PLAIN, 14)); // Fuente moderna


//        jPanel1.add(cmbProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 190, 200, 30));
//        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 490, 480));

    }
    // Método para verificar si se presionó "Guardar"
    public boolean isGuardado() {
        return guardado;
    }
    


    // Método para crear el modelo de productos
 private DefaultComboBoxModel<CheckableItem> makeProductModel() {
    DefaultComboBoxModel<CheckableItem> model = new DefaultComboBoxModel<>();
    // Obtener los nombres de los productos desde el controlador
    List<String> productos = ctrlProveedor.obtenerTodosNombresInventario();
    if (productos != null && !productos.isEmpty()) {
        for (String producto : productos) {
            model.addElement(new CheckableItem(producto, false));
        }
    } else {
        // Si no hay productos, agregar un elemento por defecto o mostrar un mensaje
        model.addElement(new CheckableItem("No hay productos disponibles", false));
    }
    return model;
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnGuardar2 = new rojeru_san.RSButtonRiple();
        jLabel6 = new javax.swing.JLabel();
        lblProducto1 = new javax.swing.JLabel();
        btnCancelar1 = new rojeru_san.RSButtonRiple();
        btnGuardar1 = new rojeru_san.RSButtonRiple();
        jLabel12 = new javax.swing.JLabel();
        txttelefono = new RSMaterialComponent.RSTextFieldMaterial();
        txtcorreo = new RSMaterialComponent.RSTextFieldMaterial();
        jLabel13 = new javax.swing.JLabel();
        txtNombre = new RSMaterialComponent.RSTextFieldMaterial();
        txtdireccion = new RSMaterialComponent.RSTextFieldMaterial();
        jLabel14 = new javax.swing.JLabel();
        tipoidentificacion4 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        tipoidentificacion5 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtNombre3 = new RSMaterialComponent.RSTextFieldMaterial();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        tipoidentificacion6 = new javax.swing.JLabel();
        tipoidentificacion1 = new javax.swing.JLabel();
        tipoidentificacion7 = new javax.swing.JLabel();
        tipoidentificacion8 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(490, 480));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(46, 49, 82));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Century751 BT", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Crear Proveedor");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        btnGuardar2.setBackground(new java.awt.Color(46, 49, 82));
        btnGuardar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/x.png"))); // NOI18N
        btnGuardar2.setColorHover(new java.awt.Color(204, 0, 0));
        btnGuardar2.setFont(new java.awt.Font("Humnst777 BlkCn BT", 1, 18)); // NOI18N
        btnGuardar2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar2ActionPerformed(evt);
            }
        });
        jPanel4.add(btnGuardar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, 40, 30));

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 490, 70));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel6.setText("Correo:");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, -1, -1));

        lblProducto1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lblProducto1.setText("Producto: ");
        jPanel3.add(lblProducto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 280, -1, -1));

        btnCancelar1.setBackground(new java.awt.Color(46, 49, 82));
        btnCancelar1.setText("Cancelar");
        btnCancelar1.setColorHover(new java.awt.Color(204, 0, 0));
        btnCancelar1.setFont(new java.awt.Font("Humnst777 BlkCn BT", 1, 14)); // NOI18N
        jPanel3.add(btnCancelar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 420, 140, -1));

        btnGuardar1.setBackground(new java.awt.Color(46, 49, 82));
        btnGuardar1.setText("Guardar");
        btnGuardar1.setColorHover(new java.awt.Color(0, 153, 51));
        btnGuardar1.setFont(new java.awt.Font("Humnst777 BlkCn BT", 1, 14)); // NOI18N
        jPanel3.add(btnGuardar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 420, 140, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel12.setText("Telefono:");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 190, -1, -1));

        txttelefono.setForeground(new java.awt.Color(0, 0, 0));
        txttelefono.setColorMaterial(new java.awt.Color(0, 0, 0));
        txttelefono.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txttelefono.setPhColor(new java.awt.Color(0, 0, 0));
        txttelefono.setPlaceholder("Ingrese telefono");
        txttelefono.setSelectionColor(new java.awt.Color(0, 0, 0));
        jPanel3.add(txttelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 220, 200, 30));

        txtcorreo.setForeground(new java.awt.Color(0, 0, 0));
        txtcorreo.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtcorreo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtcorreo.setPhColor(new java.awt.Color(0, 0, 0));
        txtcorreo.setPlaceholder("Ingrese la cantidad...");
        txtcorreo.setSelectionColor(new java.awt.Color(0, 0, 0));
        jPanel3.add(txtcorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 200, 30));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel13.setText("Nombre:");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 110, -1, 20));

        txtNombre.setForeground(new java.awt.Color(0, 0, 0));
        txtNombre.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtNombre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNombre.setPhColor(new java.awt.Color(0, 0, 0));
        txtNombre.setPlaceholder("Ingrese el nombre...");
        txtNombre.setSelectionColor(new java.awt.Color(0, 0, 0));
        jPanel3.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 130, 200, 30));

        txtdireccion.setForeground(new java.awt.Color(0, 0, 0));
        txtdireccion.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtdireccion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtdireccion.setPhColor(new java.awt.Color(0, 0, 0));
        txtdireccion.setPlaceholder("Ingrese la cantidad...");
        txtdireccion.setSelectionColor(new java.awt.Color(0, 0, 0));
        jPanel3.add(txtdireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 200, 30));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel14.setText("Direccion");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, -1, -1));

        tipoidentificacion4.setForeground(new java.awt.Color(255, 51, 51));
        tipoidentificacion4.setText("TIPO");
        jPanel3.add(tipoidentificacion4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 350, 60, -1));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 51, 51));
        jLabel19.setText("*");
        jPanel3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 110, 20, -1));

        tipoidentificacion5.setForeground(new java.awt.Color(255, 51, 51));
        tipoidentificacion5.setText("TIPO");
        jPanel3.add(tipoidentificacion5, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, 60, -1));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 51, 51));
        jLabel20.setText("*");
        jPanel3.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 290, 10, 10));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel21.setText("Nit:");
        jPanel3.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, 20));

        txtNombre3.setForeground(new java.awt.Color(0, 0, 0));
        txtNombre3.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtNombre3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNombre3.setPhColor(new java.awt.Color(0, 0, 0));
        txtNombre3.setPlaceholder("Ingrese el nombre...");
        txtNombre3.setSelectionColor(new java.awt.Color(0, 0, 0));
        jPanel3.add(txtNombre3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 200, 30));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 51, 51));
        jLabel22.setText("*");
        jPanel3.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 20, -1));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 51, 51));
        jLabel23.setText("*");
        jPanel3.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 190, 20, -1));

        tipoidentificacion6.setForeground(new java.awt.Color(255, 51, 51));
        tipoidentificacion6.setText("TIPO");
        jPanel3.add(tipoidentificacion6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 60, -1));

        tipoidentificacion1.setForeground(new java.awt.Color(255, 51, 51));
        tipoidentificacion1.setText("TIPO");
        jPanel3.add(tipoidentificacion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 250, 60, -1));

        tipoidentificacion7.setForeground(new java.awt.Color(255, 51, 51));
        tipoidentificacion7.setText("TIPO");
        jPanel3.add(tipoidentificacion7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 60, -1));

        tipoidentificacion8.setForeground(new java.awt.Color(255, 51, 51));
        tipoidentificacion8.setText("TIPO");
        jPanel3.add(tipoidentificacion8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 60, -1));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 51, 51));
        jLabel24.setText("*");
        jPanel3.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 200, 10, 10));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 51, 51));
        jLabel25.setText("*");
        jPanel3.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 290, 10, 10));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 490, 480));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnGuardar2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(proveedornuevo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(proveedornuevo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(proveedornuevo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(proveedornuevo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                proveedornuevo dialog = new proveedornuevo(new javax.swing.JFrame(), true);
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
    private rojeru_san.RSButtonRiple btnCancelar1;
    private rojeru_san.RSButtonRiple btnGuardar1;
    private rojeru_san.RSButtonRiple btnGuardar2;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lblProducto1;
    private javax.swing.JLabel tipoidentificacion1;
    private javax.swing.JLabel tipoidentificacion4;
    private javax.swing.JLabel tipoidentificacion5;
    private javax.swing.JLabel tipoidentificacion6;
    private javax.swing.JLabel tipoidentificacion7;
    private javax.swing.JLabel tipoidentificacion8;
    private RSMaterialComponent.RSTextFieldMaterial txtNombre;
    private RSMaterialComponent.RSTextFieldMaterial txtNombre3;
    private RSMaterialComponent.RSTextFieldMaterial txtcorreo;
    private RSMaterialComponent.RSTextFieldMaterial txtdireccion;
    private RSMaterialComponent.RSTextFieldMaterial txttelefono;
    // End of variables declaration//GEN-END:variables
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

    // Panel personalizado para un efecto visual moderno
    class ModernPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            GradientPaint gp = new GradientPaint(0, 0, getBackground(), getWidth(), getHeight(), getBackground().brighter(), true);
            g2d.setPaint(gp);
            g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
            g2d.setColor(new Color(0, 0, 0, 20));
            g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
        }
    }



}
