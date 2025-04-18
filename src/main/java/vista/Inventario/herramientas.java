/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista.Inventario;

import controlador.Ctrl_CategoriaHerramienta;
import controlador.Ctrl_InventarioHerramienta;
import controlador.Ctrl_MarcaHerramienta;
import controlador.Ctrl_UnidadHerramienta;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import modelo.Categoria;
import modelo.HerramientaDatos;
import modelo.Marca;
import modelo.MaterialDatos;
import modelo.Unidad;

/**
 *
 * @author ZenBook
 */
public class herramientas extends javax.swing.JPanel {

    private Ctrl_InventarioHerramienta ctrlInventario;

    /**
     * Creates new form herramientas
     */
    public herramientas() {
        initComponents();
        ctrlInventario = new Ctrl_InventarioHerramienta();
        principalPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); // Layout para las tarjetas

        cmbCategoria.removeAllItems(); // Limpia por si acaso
        cmbCategoria.addItem("Seleccione una categoría"); // Primer ítem informativo

        Ctrl_CategoriaHerramienta ctrl = new Ctrl_CategoriaHerramienta();
        List<Categoria> categorias = ctrl.obtenerCategoriasHerramienta();

        for (Categoria cat : categorias) {
            cmbCategoria.addItem(cat.getNombre());
        }
        // Cargar materiales existentes al iniciar
        cargarMateriales();
    }

    // Método para cargar los materiales desde la base de datos
    private void cargarMateriales() {
        principalPanel.removeAll();
        List<Ctrl_InventarioHerramienta.MaterialConDetalles> materiales = ctrlInventario.obtenerMateriales();
        for (Ctrl_InventarioHerramienta.MaterialConDetalles materialConDetalles : materiales) {
            agregarMaterial(
                    materialConDetalles.getMaterial(),
                    materialConDetalles.getNombreCategoria(),
                    materialConDetalles.getNombreMarca(),
                    materialConDetalles.getNombreUnidadMedida()
            );
        }
        principalPanel.revalidate();
        principalPanel.repaint();
    }

    // Método para agregar una nueva tarjeta
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

        JLabel lblCodigo = new JLabel("Código: " + material.getIdInventario());
        lblCodigo.setForeground(Color.WHITE);
        lblCodigo.setFont(fuenteInfo); // Establece la fuente
        lblCodigo.setHorizontalAlignment(SwingConstants.CENTER);
        panelInfo.add(lblCodigo);

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

        tarjeta.add(panelInfo, BorderLayout.CENTER);

        // Almacenar el objeto MaterialDatos y los nombres en la tarjeta
        tarjeta.putClientProperty("material", material);
        tarjeta.putClientProperty("nombreCategoria", nombreCategoria);
        tarjeta.putClientProperty("nombreMarca", nombreMarca);
        tarjeta.putClientProperty("nombreUnidadMedida", nombreUnidadMedida);

        // JPanel para los botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.setBackground(new Color(46, 49, 82));

        JButton verBtn = new JButton(new ImageIcon(getClass().getResource("/view (1).png"))); // Reemplaza "ver.png" con la ruta de tu imagen
        verBtn.setPreferredSize(new Dimension(36, 28)); // Ancho: 30, Alto: 30
        verBtn.setBackground(new Color(188, 225, 193)); // Establece el fondo en rojo
        //accion del boton de ver
        verBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                herramientaInfo dialog = new herramientaInfo(new javax.swing.JFrame(), true);
                dialog.mostrarMaterial(material); // Muestra la información del material
                dialog.setLocationRelativeTo(null); // Centra el JDialog
                dialog.setVisible(true); // Muestra el JDialog
            }
        });

        JButton editarBtn = new JButton(new ImageIcon(getClass().getResource("/edit.png")));
        editarBtn.setPreferredSize(new Dimension(36, 28)); // Ancho: 30, Alto: 30
        editarBtn.setBackground(new Color(166, 199, 245));
        //accion del boton de editar
        editarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el material asociado a la tarjeta
                MaterialDatos material = (MaterialDatos) tarjeta.getClientProperty("material");

                // Abrir el JDialog para editar
                herramientaEditar dialog = new herramientaEditar(new javax.swing.JFrame(), true, material);
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);

                // Si se guardaron los cambios, actualizar la tarjeta
                if (dialog.isGuardado()) {
                    String nombreCategoria = (String) tarjeta.getClientProperty("nombreCategoria");
                    String nombreMarca = (String) tarjeta.getClientProperty("nombreMarca");
                    String nombreUnidadMedida = (String) tarjeta.getClientProperty("nombreUnidadMedida");
                    actualizarTarjeta(tarjeta, material, nombreCategoria, nombreMarca, nombreUnidadMedida);
                    ctrlInventario.actualizar(material);
                }
            }
        });

        JButton eliminarBtn = new JButton(new ImageIcon(getClass().getResource("/delete (6).png")));
        eliminarBtn.setPreferredSize(new Dimension(36, 28)); // Ancho: 30, Alto: 30
        eliminarBtn.setBackground(new Color(242, 174, 188));
        eliminarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mostrar ventana emergente de confirmación
                int confirmacion = JOptionPane.showConfirmDialog(
                        null, // Parent component (null para centrar en la pantalla)
                        "¿Estás seguro de que deseas eliminar este material?\nCódigo: " + material.getIdInventario() + "\nNombre: " + material.getNombre(),
                        "Confirmar Eliminación",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

                // Si el usuario selecciona "Sí", eliminar la tarjeta
                if (confirmacion == JOptionPane.YES_OPTION) {
                    if (ctrlInventario.eliminar(material.getIdInventario())) {
                        principalPanel.remove(tarjeta);
                        principalPanel.revalidate();
                        principalPanel.repaint();
                        JOptionPane.showMessageDialog(null, "Material eliminado correctamente.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al eliminar el material.");
                    }
                }
            }
        });

        panelBotones.add(verBtn);
        panelBotones.add(editarBtn);
        panelBotones.add(eliminarBtn);
        tarjeta.add(panelBotones, BorderLayout.SOUTH);

        principalPanel.add(tarjeta);
        principalPanel.revalidate();
        principalPanel.repaint();
    }

//metodo para actualizar tarjeta
    public void actualizarTarjeta(JPanel tarjeta, MaterialDatos material, String nombreCategoria, String nombreMarca, String nombreUnidadMedida) {
        // Obtener el panel de información (panelInfo) de la tarjeta
        JPanel panelInfo = (JPanel) tarjeta.getComponent(1); // El segundo componente es panelInfo

        // Actualizar las etiquetas dentro de panelInfo
        JLabel lblCodigo = (JLabel) panelInfo.getComponent(0);
        lblCodigo.setText("Código: " + material.getIdInventario());

        JLabel lblNombre = (JLabel) panelInfo.getComponent(1);
        lblNombre.setText("Nombre: " + material.getNombre());

        JLabel lblCategoria = (JLabel) panelInfo.getComponent(2);
        lblCategoria.setText("Categoría: " + nombreCategoria);

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
        Ctrl_CategoriaHerramienta ctrlCategoria = new Ctrl_CategoriaHerramienta();
        List<Categoria> categorias = ctrlCategoria.obtenerCategoriasHerramienta();
        for (Categoria cat : categorias) {
            if (cat.getCodigo() == idCategoria) {
                nombreCategoria = cat.getNombre();
                break;
            }
        }

        // Obtener nombre de la marca
        Ctrl_MarcaHerramienta ctrlMarca = new Ctrl_MarcaHerramienta();
        List<Marca> marcas = ctrlMarca.obtenerMarcasHerramienta();
        for (Marca m : marcas) {
            if (m.getCodigo() == idMarca) {
                nombreMarca = m.getNombre();
                break;
            }
        }

        // Obtener nombre de la unidad de medida
        Ctrl_UnidadHerramienta ctrlUnidad = new Ctrl_UnidadHerramienta();
        List<Unidad> unidades = ctrlUnidad.obtenerUnidadesHerramienta();
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
        txtBuscar = new RSMaterialComponent.RSTextFieldMaterialIcon();
        btnNuevo = new rojeru_san.RSButtonRiple();
        principalPanel = new javax.swing.JPanel();
        btnUnidad = new RSMaterialComponent.RSButtonShape();
        btnCategoria = new RSMaterialComponent.RSButtonShape();
        btnMarcas = new RSMaterialComponent.RSButtonShape();
        cmbCategoria = new RSMaterialComponent.RSComboBoxMaterial();

        setPreferredSize(new java.awt.Dimension(1240, 580));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelprincipal.setBackground(new java.awt.Color(255, 255, 255));
        panelprincipal.setPreferredSize(new java.awt.Dimension(1290, 730));
        panelprincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        panelprincipal.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 420, 30));

        btnNuevo.setBackground(new java.awt.Color(46, 49, 82));
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plus (2).png"))); // NOI18N
        btnNuevo.setText(" Nuevo");
        btnNuevo.setFont(new java.awt.Font("Roboto Bold", 1, 16)); // NOI18N
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        panelprincipal.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 90, 110, 30));

        principalPanel.setBackground(new java.awt.Color(245, 246, 250));
        principalPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelprincipal.add(principalPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 1210, 550));

        btnUnidad.setBackground(new java.awt.Color(46, 49, 82));
        btnUnidad.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        btnUnidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/medida.png"))); // NOI18N
        btnUnidad.setText(" Unidad medida");
        btnUnidad.setToolTipText("");
        btnUnidad.setBackgroundHover(new java.awt.Color(67, 150, 209));
        btnUnidad.setFont(new java.awt.Font("Roboto Bold", 1, 16)); // NOI18N
        btnUnidad.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        btnUnidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUnidadActionPerformed(evt);
            }
        });
        panelprincipal.add(btnUnidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 20, 170, 40));

        btnCategoria.setBackground(new java.awt.Color(46, 49, 82));
        btnCategoria.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        btnCategoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/categorizacion.png"))); // NOI18N
        btnCategoria.setText("Categorias");
        btnCategoria.setBackgroundHover(new java.awt.Color(67, 150, 209));
        btnCategoria.setFont(new java.awt.Font("Roboto Bold", 1, 16)); // NOI18N
        btnCategoria.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        btnCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCategoriaActionPerformed(evt);
            }
        });
        panelprincipal.add(btnCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 20, 140, 40));

        btnMarcas.setBackground(new java.awt.Color(46, 49, 82));
        btnMarcas.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        btnMarcas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/marca-comercial (1).png"))); // NOI18N
        btnMarcas.setText("Marcas");
        btnMarcas.setBackgroundHover(new java.awt.Color(67, 150, 209));
        btnMarcas.setFont(new java.awt.Font("Roboto Bold", 1, 16)); // NOI18N
        btnMarcas.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        btnMarcas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMarcasActionPerformed(evt);
            }
        });
        panelprincipal.add(btnMarcas, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 20, 120, 40));

        cmbCategoria.setForeground(new java.awt.Color(153, 153, 153));
        cmbCategoria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione una categoria:" }));
        cmbCategoria.setColorMaterial(new java.awt.Color(153, 153, 153));
        cmbCategoria.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmbCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCategoriaActionPerformed(evt);
            }
        });
        panelprincipal.add(cmbCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 90, 280, 30));

        add(panelprincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1290, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        herramientasNuevo dialog = new herramientasNuevo(new javax.swing.JFrame(), true);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

        if (dialog.materialGuardado) {
            MaterialDatos material = dialog.material;
            if (ctrlInventario.insertar(material)) {
                // Obtener los nombres de categoría, marca y unidad de medida para el nuevo material
                String[] detalles = obtenerDetalles(material.getIdCategoria(), material.getIdMarca(), material.getIdUnidadMedida());
                agregarMaterial(material, detalles[0], detalles[1], detalles[2]);
                JOptionPane.showMessageDialog(null, "Material agregado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Error al agregar el material.");
            }
        }
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnUnidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUnidadActionPerformed
        herramientaUnidad dialog = new herramientaUnidad(new javax.swing.JFrame(), true);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnUnidadActionPerformed

    private void btnCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCategoriaActionPerformed
        herramientasCategoria dialog = new herramientasCategoria(new javax.swing.JFrame(), true);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnCategoriaActionPerformed

    private void btnMarcasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMarcasActionPerformed
        herramientaMarca dialog = new herramientaMarca(new javax.swing.JFrame(), true);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnMarcasActionPerformed

    private void cmbCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCategoriaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RSMaterialComponent.RSButtonShape btnCategoria;
    private RSMaterialComponent.RSButtonShape btnMarcas;
    private rojeru_san.RSButtonRiple btnNuevo;
    private RSMaterialComponent.RSButtonShape btnUnidad;
    private RSMaterialComponent.RSComboBoxMaterial cmbCategoria;
    private javax.swing.JPanel panelprincipal;
    private javax.swing.JPanel principalPanel;
    private RSMaterialComponent.RSTextFieldMaterialIcon txtBuscar;
    // End of variables declaration//GEN-END:variables
}
