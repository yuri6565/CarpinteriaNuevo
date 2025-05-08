/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista.catalogo;

import controlador.ctrl_productocatalogo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import modelo.catalogoproducto;
import vista.catalogo.catalogoNuevo;



public class silla extends javax.swing.JPanel {

    /**
     * Creates new form silla
     */
    public silla() {
        initComponents();
         
    }

     public void agregarproducto(catalogoproducto producto, String nombre, String categoria) {

        JPanel tarjeta = new JPanel(new BorderLayout());
        tarjeta.setPreferredSize(new Dimension(210, 300)); // Ancho: 200, Alto: 300
        tarjeta.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // JPanel para la imagen
        JPanel panelImagen = new JPanel(new GridBagLayout());
        panelImagen.setPreferredSize(new Dimension(210, 197));
        panelImagen.setBackground(Color.WHITE);

        JLabel lblImagen = new JLabel();
        if (producto.getImagen() != null) {
            ImageIcon icon = new ImageIcon(producto.getImagen());
            // Redimensionar la imagen al tamaño del panelImagen (210x197)
            Image img = icon.getImage().getScaledInstance(210, 197, Image.SCALE_SMOOTH);
            lblImagen.setIcon(new ImageIcon(img));
        } else {
            lblImagen.setText("No imagen");
        }
        panelImagen.add(lblImagen);
        tarjeta.add(panelImagen, BorderLayout.NORTH);
        
        
         JPanel panelInfo = new JPanel(new GridLayout(3, 1));
        panelInfo.setBackground(new Color(46, 49, 82)); // Color de fondo azul oscuro
        Font fuenteInfo = new Font("Segoe UI", Font.PLAIN, 15); // Fuente Arial, negrita, tamaño 16

        

        JLabel lblNombre = new JLabel("Nombre: " + producto.getNombre());
        lblNombre.setForeground(Color.WHITE);
        lblNombre.setFont(fuenteInfo); // Establece la fuente
        lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
        panelInfo.add(lblNombre);

        JLabel lblCategoria = new JLabel("Categoría: " + producto.getCategoria());
        lblCategoria.setForeground(Color.WHITE);
        lblCategoria.setFont(fuenteInfo); // Establece la fuente
        lblCategoria.setHorizontalAlignment(SwingConstants.CENTER);
        panelInfo.add(lblCategoria);

        tarjeta.add(panelInfo, BorderLayout.CENTER);
     }
     
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Añadir = new rojeru_san.RSButtonRiple();

        setMinimumSize(new java.awt.Dimension(926, 271));
        setPreferredSize(new java.awt.Dimension(1250, 630));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Añadir.setBackground(new java.awt.Color(46, 49, 82));
        Añadir.setText("Producto");
        Añadir.setColorHover(new java.awt.Color(0, 153, 51));
        Añadir.setFont(new java.awt.Font("Humnst777 BlkCn BT", 1, 14)); // NOI18N
        Añadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AñadirActionPerformed(evt);
            }
        });
        add(Añadir, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 10, 140, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void AñadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AñadirActionPerformed
       
  
    catalogoNuevo dialog = new catalogoNuevo(new javax.swing.JFrame(), true);
    dialog.setLocationRelativeTo(null);
    dialog.setVisible(true);
    
    if (dialog.productoguardado && dialog.producto != null) {
        ctrl_productocatalogo controlador = new ctrl_productocatalogo();
        
        if (controlador.insertar(dialog.producto)) {
            // Agregar el producto al panel visual
            agregarproducto(
                dialog.producto,
                dialog.producto.getNombre(),
                dialog.producto.getCategoria()
            );
            JOptionPane.showMessageDialog(null, "Producto agregado exitosamente");
        } else {
            JOptionPane.showMessageDialog(null, "Error al guardar en la base de datos");
        }
    }

    }//GEN-LAST:event_AñadirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSButtonRiple Añadir;
    // End of variables declaration//GEN-END:variables
}

