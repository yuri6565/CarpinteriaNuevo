/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista.InventarioTrap;

import RSMaterialComponent.RSButtonShape;
import controlador.Ctrl_CategoriaMaterial;
import controlador.Ctrl_InventarioMaterial;
import controlador.Ctrl_MarcaMaterial;
import controlador.Ctrl_UnidadMaterial;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import modelo.Categoria;
import modelo.MaterialDatos;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JViewport;
import javax.swing.plaf.basic.BasicScrollBarUI;
import modelo.Marca;
import modelo.Unidad;
import rojeru_san.RSButton;
import vista.TemaManager;

/**
 *
 * @author ZenBook
 */
public class materiales extends javax.swing.JPanel {

    private Ctrl_InventarioMaterial ctrlInventario;
    private JPanel contenedorPrincipal;

    /**
     * Creates new form materiales
     */
    public materiales() {

        initComponents();
        contenedorPrincipal = new JPanel();

        ctrlInventario = new Ctrl_InventarioMaterial();

        // Panel contenedor para principalPanel (para agregar márgenes)
        JPanel contenedorPrincipal = new JPanel(new BorderLayout());
        contenedorPrincipal.setBackground(new java.awt.Color(245, 246, 250));

        principalPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.insets = new Insets(10, 10, 10, 10); // Márgenes
        gbc.fill = GridBagConstraints.NONE; // No estirar componentes

        principalPanel.setBackground(new java.awt.Color(245, 246, 250));

        // Agregar márgenes al contenedor (aumentado a 40px en todos los lados)
        contenedorPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contenedorPrincipal.add(principalPanel, BorderLayout.CENTER);

// JScrollPane -----------------------------
        // Configurar el JScrollPane con el contenedor (en lugar de principalPanel directamente)
        JScrollPane scrollPane = new JScrollPane(contenedorPrincipal);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);

        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(8, 0));
        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(153, 153, 153);
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return new JButton() {
                    @Override
                    public Dimension getPreferredSize() {
                        return new Dimension(0, 0);
                    }
                };
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return new JButton() {
                    @Override
                    public Dimension getPreferredSize() {
                        return new Dimension(0, 0);
                    }
                };
            }

            @Override
            protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(thumbColor);
                g2.fillRoundRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, 10, 10);
                g2.dispose();
            }
        });

        // Reemplazar en panelprincipal
        panelprincipal.remove(principalPanel);
        panelprincipal.add(scrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, 1150, 560));
// JScrollPane -----------------------------        

        cmbCategoria.removeAllItems(); // Limpia por si acaso
        cmbCategoria.addItem("Seleccione una categoría"); // Primer ítem informativo

        Ctrl_CategoriaMaterial ctrl = new Ctrl_CategoriaMaterial();
        List<Categoria> categorias = ctrl.obtenerCategoriasMaterial();

        for (Categoria cat : categorias) {
            cmbCategoria.addItem(cat.getNombre());
        }

        // Cargar materiales existentes al iniciar
        cargarMateriales();

    }

    // Método para cargar los materiales desde la base de datos
    private void cargarMateriales() {
        principalPanel.removeAll();
        // Cambiar a FlowLayout alineado a la izquierda
        principalPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        List<Ctrl_InventarioMaterial.MaterialConDetalles> materiales = ctrlInventario.obtenerMateriales();
        for (Ctrl_InventarioMaterial.MaterialConDetalles materialConDetalles : materiales) {
            agregarMaterial(
                    materialConDetalles.getMaterial(),
                    materialConDetalles.getNombreCategoria(),
                    materialConDetalles.getNombreMarca(),
                    materialConDetalles.getNombreUnidadMedida()
            );
        }
        principalPanel.revalidate();
        principalPanel.repaint();
        if (principalPanel.getParent() instanceof JViewport) {
            ((JViewport) principalPanel.getParent()).setViewPosition(new Point(0, 0));
        }

    }

    //-------------------------
    public void agregarMaterial(MaterialDatos material, String nombreCategoria, String nombreMarca, String nombreUnidadMedida) {

        JPanel tarjeta = new JPanel(new BorderLayout());
        tarjeta.setPreferredSize(new Dimension(210, 300)); // Ancho: 200, Alto: 300
        tarjeta.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // JPanel para la imagen
        JPanel panelImagen = new JPanel(new GridBagLayout());
        panelImagen.setPreferredSize(new Dimension(210, 197));
        panelImagen.setBackground(Color.WHITE);

        JLabel lblImagen = new JLabel();
        if (material.getImagen() != null) {
            ImageIcon icon = new ImageIcon(material.getImagen());
            // Redimensionar la imagen al tamaño del panelImagen (210x197)
            Image img = icon.getImage().getScaledInstance(210, 197, Image.SCALE_SMOOTH);
            lblImagen.setIcon(new ImageIcon(img));
        } else {
            lblImagen.setText("No imagen");
        }
        panelImagen.add(lblImagen);
        tarjeta.add(panelImagen, BorderLayout.NORTH);

        // JPanel para la información
        JPanel panelInfo = new JPanel(new GridLayout(3, 1));
        panelInfo.setBackground(new Color(46, 49, 82)); // Color de fondo azul oscuro
        Font fuenteInfo = new Font("Segoe UI", Font.PLAIN, 15); // Fuente Arial, negrita, tamaño 16

        JLabel lblNombre = new JLabel("Nombre: " + material.getNombre());
        lblNombre.setForeground(Color.WHITE);
        lblNombre.setFont(fuenteInfo); // Establece la fuente
        lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
        panelInfo.add(lblNombre);

        JLabel lblCategoria = new JLabel("Categoría: " + nombreCategoria);
        lblCategoria.setForeground(Color.WHITE);
        lblCategoria.setFont(fuenteInfo); // Establece la fuente
        lblCategoria.setHorizontalAlignment(SwingConstants.CENTER);
        panelInfo.add(lblCategoria);

        JLabel lblCantidad = new JLabel("Cantidad: " + material.getCantidad() + " " + nombreUnidadMedida);
        lblCantidad.setForeground(Color.WHITE);
        lblCantidad.setFont(fuenteInfo);
        lblCantidad.setHorizontalAlignment(SwingConstants.CENTER);
        panelInfo.add(lblCantidad);

        tarjeta.add(panelInfo, BorderLayout.CENTER);

        // Almacenar el objeto MaterialDatos y los nombres en la tarjeta
        tarjeta.putClientProperty("material", material);
        tarjeta.putClientProperty("nombreCategoria", nombreCategoria);
        tarjeta.putClientProperty("nombreMarca", nombreMarca);
        tarjeta.putClientProperty("nombreUnidadMedida", nombreUnidadMedida);

        // JPanel para los botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.setBackground(new Color(46, 49, 82));

        RSButtonShape verBtn = new RSButtonShape(); // Reemplaza "ver.png" con la ruta de tu imagen
        verBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view (1).png")));
        verBtn.setPreferredSize(new Dimension(33, 25)); // Ancho: 30, Alto: 30
        verBtn.setBackground(new Color(216, 246, 221)); // Establece el fondo en rojo
        verBtn.setBackgroundHover(new java.awt.Color(188, 225, 193));
        verBtn.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        verBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        // Botón Ver con tooltip mejorado
        verBtn.setToolTipText("<html><b>Ver detalles del material</html>");
        
        //accion del boton de ver
        verBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                materialInfo dialog = new materialInfo(new javax.swing.JFrame(), true);
                dialog.mostrarMaterial(material); // Muestra la información del material
                dialog.setLocationRelativeTo(null); // Centra el JDialog
                dialog.setVisible(true); // Muestra el JDialog
            }
        });


        panelBotones.add(verBtn);
        
        tarjeta.add(panelBotones, BorderLayout.SOUTH);

        principalPanel.add(tarjeta);
        principalPanel.revalidate();
        principalPanel.repaint();
        contenedorPrincipal.revalidate(); // Revalidar el contenedor principal también
        contenedorPrincipal.repaint();   // Repintar el contenedor principal también

        // Calcular la altura dinámicamente según 5 tarjetas por fila
        int totalTarjetas = principalPanel.getComponentCount();
        int filas = (int) Math.ceil((double) totalTarjetas / 5); // Número de filas
        int tarjetaHeight = 310; // Altura de la tarjeta + margen
        int totalHeight = filas * tarjetaHeight;
        // Ajustar el ancho y alto considerando el margen (1150 - 80px de márgenes laterales, 560 - 80px de márgenes verticales)
        principalPanel.setPreferredSize(new Dimension(1150 - 80, Math.max(totalHeight, 560 - 80))); // Mínimo ajustado
    }

//metodo para actualizar tarjeta
    public void actualizarTarjeta(JPanel tarjeta, MaterialDatos material, String nombreCategoria, String nombreMarca, String nombreUnidadMedida) {
        // Obtener el panel de información (panelInfo) de la tarjeta
        JPanel panelInfo = (JPanel) tarjeta.getComponent(1); // El segundo componente es panelInfo

        // Actualizar las etiquetas dentro de panelInfo
        JLabel lblNombre = (JLabel) panelInfo.getComponent(0);
        lblNombre.setText("Nombre: " + material.getNombre());

        JLabel lblCategoria = (JLabel) panelInfo.getComponent(1);
        lblCategoria.setText("Categoría: " + nombreCategoria);

        JLabel lblCantidad = (JLabel) panelInfo.getComponent(2);
        lblCantidad.setText("Cantidad: " + material.getCantidad() + " " + nombreUnidadMedida);

        // Actualizar la imagen si es necesario
        JPanel panelImagen = (JPanel) tarjeta.getComponent(0); // El primer componente es panelImagen
        JLabel lblImagen = (JLabel) panelImagen.getComponent(0);
        if (material.getImagen() != null) {
            ImageIcon icon = new ImageIcon(material.getImagen());
            // Redimensionar la imagen al tamaño del panelImagen (210x197)
            Image img = icon.getImage().getScaledInstance(210, 197, Image.SCALE_SMOOTH);
            lblImagen.setIcon(new ImageIcon(img));
            lblImagen.setText("");
        } else {
            lblImagen.setIcon(null);
            lblImagen.setText("No imagen");
        }

        // Actualizar los nombres almacenados en la tarjeta
        tarjeta.putClientProperty("material", material);
        tarjeta.putClientProperty("nombreCategoria", nombreCategoria);
        tarjeta.putClientProperty("nombreMarca", nombreMarca);
        tarjeta.putClientProperty("nombreUnidadMedida", nombreUnidadMedida);

        tarjeta.revalidate();
        tarjeta.repaint();
    }

    // Método auxiliar para obtener los nombres de categoría, marca y unidad de medida
    private String[] obtenerDetalles(int idCategoria, int idMarca, int idUnidadMedida) {
        String nombreCategoria = "Sin categoría";
        String nombreMarca = "Sin marca";
        String nombreUnidadMedida = "Sin unidad de medida";

        // Obtener nombre de la categoría
        Ctrl_CategoriaMaterial ctrlCategoria = new Ctrl_CategoriaMaterial();
        List<Categoria> categorias = ctrlCategoria.obtenerCategoriasMaterial();
        for (Categoria cat : categorias) {
            if (cat.getCodigo() == idCategoria) {
                nombreCategoria = cat.getNombre();
                break;
            }
        }

        // Obtener nombre de la marca
        Ctrl_MarcaMaterial ctrlMarca = new Ctrl_MarcaMaterial();
        List<Marca> marcas = ctrlMarca.obtenerCategoriasMaterial();
        for (Marca m : marcas) {
            if (m.getCodigo() == idMarca) {
                nombreMarca = m.getNombre();
                break;
            }
        }

        // Obtener nombre de la unidad de medida
        Ctrl_UnidadMaterial ctrlUnidad = new Ctrl_UnidadMaterial();
        List<Unidad> unidades = ctrlUnidad.obtenerCategoriasMaterial();
        for (Unidad um : unidades) {
            if (um.getCodigo() == idUnidadMedida) {
                nombreUnidadMedida = um.getNombre();
                break;
            }
        }

        return new String[]{nombreCategoria, nombreMarca, nombreUnidadMedida};
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelprincipal = new javax.swing.JPanel();
        principalPanel = new javax.swing.JPanel();
        cmbCategoria = new RSMaterialComponent.RSComboBoxMaterial();
        txtBuscar = new RSMaterialComponent.RSTextFieldMaterialIcon();

        setPreferredSize(new java.awt.Dimension(1290, 730));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelprincipal.setBackground(new java.awt.Color(255, 255, 255));
        panelprincipal.setPreferredSize(new java.awt.Dimension(1240, 580));
        panelprincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        principalPanel.setBackground(new java.awt.Color(30, 30, 45));
        principalPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelprincipal.add(principalPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 1210, 570));

        cmbCategoria.setForeground(new java.awt.Color(153, 153, 153));
        cmbCategoria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione una categoria:" }));
        cmbCategoria.setColorMaterial(new java.awt.Color(153, 153, 153));
        cmbCategoria.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        panelprincipal.add(cmbCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 90, 280, 30));

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
        panelprincipal.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 390, 30));

        add(panelprincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1290, 730));
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RSMaterialComponent.RSComboBoxMaterial cmbCategoria;
    private javax.swing.JPanel panelprincipal;
    private javax.swing.JPanel principalPanel;
    private RSMaterialComponent.RSTextFieldMaterialIcon txtBuscar;
    // End of variables declaration//GEN-END:variables
}
