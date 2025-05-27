/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista.Inventario0;

import controlador.Ctrl_CategoriaMaterial;
import controlador.Ctrl_InventarioMaterial;
import controlador.Ctrl_MarcaMaterial;
import controlador.Ctrl_UnidadMaterial;
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
    /**
     * Creates new form materiales
     */
    
    public materiales() {
        initComponents();
     
        ctrlInventario = new Ctrl_InventarioMaterial();
        principalPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); // Layout para las tarjetas
        
        cmbCategoria.removeAllItems(); // Limpia por si acaso
        cmbCategoria.addItem("Seleccione una categoría"); // Primer ítem informativo
        
        Ctrl_CategoriaMaterial ctrl = new Ctrl_CategoriaMaterial();
        List<Categoria> categorias = ctrl.obtenerCategoriasMaterial();

        for (Categoria cat : categorias) {
        cmbCategoria.addItem(cat.getNombre());
        }
        // Cargar materiales existentes al iniciar
                       aplicarTema();
        cargarMateriales();
         TemaManager.getInstance().addThemeChangeListener(() -> {
 // Update theme when it changes
        });
    }
    
    
    
    
    // Método para cargar los materiales desde la base de datos
    private void cargarMateriales() {
        principalPanel.removeAll();
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
    panelInfo.setBackground(new Color(46,49,82)); // Color de fondo azul oscuro
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
    panelBotones.setBackground(new Color(46,49,82));
    
    JButton verBtn = new JButton(new ImageIcon(getClass().getResource("/view (1).png"))); // Reemplaza "ver.png" con la ruta de tu imagen
    verBtn.setPreferredSize(new Dimension(36, 28)); // Ancho: 30, Alto: 30
    verBtn.setBackground(new Color(188,225,193)); // Establece el fondo en rojo
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
    
    JButton editarBtn = new JButton(new ImageIcon(getClass().getResource("/edit.png")));
    editarBtn.setPreferredSize(new Dimension(36, 28)); // Ancho: 30, Alto: 30
    editarBtn.setBackground(new Color(166,199,245));

    editarBtn.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
  // Obtener el material asociado a la tarjeta
                MaterialDatos material = (MaterialDatos) tarjeta.getClientProperty("material");

                // Abrir el JDialog para editar
                materialEditar dialog = new materialEditar(new javax.swing.JFrame(), true, material);
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
    eliminarBtn.setBackground(new Color(242,174,188));
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
      private void cambiarEstiloBotonRS(RSButton boton, Color fondo, Color texto) {
    boton.setBackground(fondo);                // Fondo (si es compatible en tu versión)
    boton.setColorText(texto);                 // Texto
    boton.setColorHover(new Color(72, 92, 188)); // Hover fijo azul
    boton.setColorTextHover(texto); 

}
    
      public void aplicarTema() {
        boolean oscuro = TemaManager.getInstance().isOscuro();

        if (oscuro) { //245, 245, 245
            Color fondo = new Color(21,21,33);
            Color primario = new Color(40, 60, 150);
            Color texto = Color.WHITE;

         principalPanel.setBackground(new Color (30, 30, 45));
            txtBuscar.setBackground(fondo);
      
            
       
        } else {
            Color fondo = new Color(242, 247, 255);
            Color texto = Color.BLACK;
            Color primario = new Color(72, 92, 188);

   
        
          principalPanel.setBackground(new Color (245,245,245));
            
        }
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
        categoria = new RSMaterialComponent.RSButtonShape();
        marca = new RSMaterialComponent.RSButtonShape();
        unidadM = new RSMaterialComponent.RSButtonShape();
        btnNuevo = new rojeru_san.RSButtonRiple();
        principalPanel = new javax.swing.JPanel();
        cmbCategoria = new RSMaterialComponent.RSComboBoxMaterial();
        txtBuscar = new RSMaterialComponent.RSTextFieldMaterialIcon();

        setPreferredSize(new java.awt.Dimension(1290, 730));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelprincipal.setBackground(new java.awt.Color(255, 255, 255));
        panelprincipal.setPreferredSize(new java.awt.Dimension(1240, 580));
        panelprincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        categoria.setBackground(new java.awt.Color(46, 49, 82));
        categoria.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        categoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/categorizacion.png"))); // NOI18N
        categoria.setText("Categorias");
        categoria.setBackgroundHover(new java.awt.Color(67, 150, 209));
        categoria.setFont(new java.awt.Font("Roboto Bold", 1, 16)); // NOI18N
        categoria.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        categoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoriaActionPerformed(evt);
            }
        });
        panelprincipal.add(categoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 20, 130, 40));

        marca.setBackground(new java.awt.Color(46, 49, 82));
        marca.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        marca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/marca-comercial (1).png"))); // NOI18N
        marca.setText("Marcas");
        marca.setBackgroundHover(new java.awt.Color(67, 150, 209));
        marca.setFont(new java.awt.Font("Roboto Bold", 1, 16)); // NOI18N
        marca.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        marca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                marcaActionPerformed(evt);
            }
        });
        panelprincipal.add(marca, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 20, 120, 40));

        unidadM.setBackground(new java.awt.Color(46, 49, 82));
        unidadM.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        unidadM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/medida.png"))); // NOI18N
        unidadM.setText(" Unidad medida");
        unidadM.setToolTipText("");
        unidadM.setBackgroundHover(new java.awt.Color(67, 150, 209));
        unidadM.setFont(new java.awt.Font("Roboto Bold", 1, 16)); // NOI18N
        unidadM.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        unidadM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unidadMActionPerformed(evt);
            }
        });
        panelprincipal.add(unidadM, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 20, 170, 40));

        btnNuevo.setBackground(new java.awt.Color(46, 49, 82));
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plus (2).png"))); // NOI18N
        btnNuevo.setText(" Nuevo");
        btnNuevo.setFont(new java.awt.Font("Roboto Bold", 1, 16)); // NOI18N
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        panelprincipal.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 90, 110, 30));

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

    private void categoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoriaActionPerformed
        materialCategoria dialog = new materialCategoria(new javax.swing.JFrame(), true);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }//GEN-LAST:event_categoriaActionPerformed

    private void marcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_marcaActionPerformed
        materialMarca dialog = new materialMarca(new javax.swing.JFrame(), true);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }//GEN-LAST:event_marcaActionPerformed

    private void unidadMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unidadMActionPerformed
        materialUnidad dialog = new materialUnidad(new javax.swing.JFrame(), true);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }//GEN-LAST:event_unidadMActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        nuevoMateriales dialog = new nuevoMateriales(new javax.swing.JFrame(), true);
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

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSButtonRiple btnNuevo;
    private RSMaterialComponent.RSButtonShape categoria;
    private RSMaterialComponent.RSComboBoxMaterial cmbCategoria;
    private RSMaterialComponent.RSButtonShape marca;
    private javax.swing.JPanel panelprincipal;
    private javax.swing.JPanel principalPanel;
    private RSMaterialComponent.RSTextFieldMaterialIcon txtBuscar;
    private RSMaterialComponent.RSButtonShape unidadM;
    // End of variables declaration//GEN-END:variables
}
