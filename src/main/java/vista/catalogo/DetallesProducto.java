/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista.catalogo;

import vista.Ventas.*;
import controlador.Ctrl_Cliente;
import controlador.Ctrl_Pedido;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import modelo.Cliente;
import modelo.Conexion;
import modelo.Pedido;
import modelo.PedidoDetalle;
import modelo.catalogoproducto;
import vista.TemaManager;

/**
 *
 * @author Personal
 */
public class DetallesProducto extends javax.swing.JPanel {

    private JPanel contenedor;
    private catalogoproducto producto;
    private boolean editando = false;
    private int idCategoria;
    private String nombreCategoria;
    private JPanel parentPanel;
    private Map<rojerusan.RSLabelImage, ImageIcon> imageMap;
    private List<ImageIcon> imageList; // List to store available images
    private int currentIndex;

    // Constructor with idCategoria and nombreCategoria
    public DetallesProducto(JPanel contenedor, catalogoproducto producto, int idCategoria, String nombreCategoria) {
        this.contenedor = contenedor;
        this.producto = producto;
        this.idCategoria = idCategoria;
        this.nombreCategoria = nombreCategoria != null ? nombreCategoria : "Categoría desconocida";
        this.parentPanel = contenedor;
        this.imageMap = new HashMap<>();
        this.imageList = new ArrayList<>(); // Initialize image list
        this.currentIndex = 0;
        initComponents();
        cargarDatosProducto();
        addZoomListeners();
        addNavigationListeners(); // Add navigation listeners for arrows
        updateThumbnails();
        aplicarTema();
        TemaManager.getInstance().addThemeChangeListener(() -> {
            aplicarTema();
        });
    }

    public void aplicarTema() {
        boolean oscuro = TemaManager.getInstance().isOscuro();
        if (oscuro) {
            Color fondo = new Color(21, 21, 33);
            Color primario = new Color(40, 60, 150);
            Color texto = Color.WHITE;
            jPanel2.setBackground(fondo);
        } else {
            Color fondo = new Color(242, 247, 255);
            Color texto = Color.BLACK;
            Color primario = new Color(72, 92, 188);
            jPanel2.setBackground(fondo);
        }
    }

    private void cargarDatosProducto() {
        // Set category
        jLabel6.setText(nombreCategoria != null ? nombreCategoria : "Categoría desconocida");

        // Set product name
        nombre.setText(producto.getNombre() != null ? producto.getNombre() : "Sin nombre");

        // Set description with HTML for text wrapping
        String descText = producto.getDescripcion() != null ? producto.getDescripcion() : "Sin descripción";
        descripcion1.setText("<html><body style='width: 300px;'>" + descText + "</body></html>");

        // Set color
        color1.setText((producto.getColor() != null ? producto.getColor() : "Sin color"));


        // Set dimensions
        String ancho = producto.getAncho() != null ? producto.getAncho() : "N/A";
        String largo = producto.getAlto() != null ? producto.getAlto() : "N/A";
        String profundidad = producto.getProfundidad() != null ? producto.getProfundidad() : "N/A";
//        jLabelDimensiones.setText(String.format("Ancho: %s, Largo: %s, Profundidad: %s", ancho, largo, profundidad));
material2.setText("Ancho: " + ancho);
material3.setText("Largo: " + largo);
material1.setText("Profundidad: " + profundidad);
        // Set material
     

        // Load images and populate imageList
        imageList.clear();
        imageMap.clear();
        if (producto.getImagen() != null && producto.getImagen().length > 0) {
            try {
                ImageIcon mainIcon = new ImageIcon(producto.getImagen());
                if (mainIcon.getImageLoadStatus() == MediaTracker.COMPLETE) {
                    imageList.add(mainIcon);
                    imageMap.put(rSLabelImage2, mainIcon);
                    rSLabelImage2.setIcon(mainIcon);
                } else {
                    System.err.println("Main image load failed for rSLabelImage2");
                }
            } catch (Exception e) {
                System.err.println("Error loading main image: " + e.getMessage());
            }
        } else {
            ImageIcon defaultIcon = new ImageIcon(getClass().getResource("/catalogo/armario3.jpg"));
            imageList.add(defaultIcon);
            imageMap.put(rSLabelImage2, defaultIcon);
            rSLabelImage2.setIcon(defaultIcon);
        }

        if (producto.getImagen2() != null && producto.getImagen2().length > 0) {
            try {
                ImageIcon image2Icon = new ImageIcon(producto.getImagen2());
                if (image2Icon.getImageLoadStatus() == MediaTracker.COMPLETE) {
                    imageList.add(image2Icon);
                    imageMap.put(rSLabelImage3, image2Icon);
                    rSLabelImage3.setIcon(image2Icon);
                } else {
                    System.err.println("Image2 load failed for rSLabelImage3");
                }
            } catch (Exception e) {
                System.err.println("Error loading image2: " + e.getMessage());
            }
        } else {
            ImageIcon defaultIcon = new ImageIcon(getClass().getResource("/WhatsApp_Image_2025-03-28_at_10.59.04_AM-removebg-preview.png"));
            imageList.add(defaultIcon);
            imageMap.put(rSLabelImage3, defaultIcon);
            rSLabelImage3.setIcon(defaultIcon);
        }

        if (producto.getImagen3() != null && producto.getImagen3().length > 0) {
            try {
                ImageIcon image3Icon = new ImageIcon(producto.getImagen3());
                if (image3Icon.getImageLoadStatus() == MediaTracker.COMPLETE) {
                    imageList.add(image3Icon);
                    imageMap.put(rSLabelImage4, image3Icon);
                    rSLabelImage4.setIcon(image3Icon);
                } else {
                    System.err.println("Image3 load failed for rSLabelImage4");
                }
            } catch (Exception e) {
                System.err.println("Error loading image3: " + e.getMessage());
            }
        } else {
            rSLabelImage4.setIcon(null);
        }

        if (producto.getImagen4() != null && producto.getImagen4().length > 0) {
            try {
                ImageIcon image4Icon = new ImageIcon(producto.getImagen4());
                if (image4Icon.getImageLoadStatus() == MediaTracker.COMPLETE) {
                    imageList.add(image4Icon);
                    imageMap.put(rSLabelImage5, image4Icon);
                    rSLabelImage5.setIcon(image4Icon);
                } else {
                    System.err.println("Image4 load failed for rSLabelImage5");
                }
            } catch (Exception e) {
                System.err.println("Error loading image4: " + e.getMessage());
            }
        } else {
            rSLabelImage5.setIcon(null);
        }

        // Update pagination label
        updatePaginationLabel();
    }

    private void updatePaginationLabel() {
        int totalImages = imageList.size();
        jLabel4.setText((currentIndex + 1) + "/" + totalImages);
    }

    private void updateThumbnails() {
        if (!imageList.isEmpty()) {
            rSLabelImage2.setIcon(imageList.get(currentIndex));
            imageMap.put(rSLabelImage2, imageList.get(currentIndex));
            updatePaginationLabel();
            System.out.println("Updated thumbnail to index: " + currentIndex);
        } else {
            System.out.println("imageList is empty");
        }
    }

    private void addNavigationListeners() {
        rSLabelIcon2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (currentIndex > 0) {
                    currentIndex--;
                    updateThumbnails();
                }
            }
        });

        rSLabelIcon1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (currentIndex < imageList.size() - 1) {
                    currentIndex++;
                    updateThumbnails();
                }
            }
        });
    }

    private void addZoomListeners() {
        rSLabelImage2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Clicked rSLabelImage2");
                showZoomedImage(rSLabelImage2);
            }
        });
        rSLabelImage3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Clicked rSLabelImage3");
                setMainImage(rSLabelImage3);
                showZoomedImage(rSLabelImage3);
            }
        });
        rSLabelImage4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Clicked rSLabelImage4");
                setMainImage(rSLabelImage4);
                showZoomedImage(rSLabelImage4);
            }
        });
        rSLabelImage5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Clicked rSLabelImage5");
                setMainImage(rSLabelImage5);
                showZoomedImage(rSLabelImage5);
            }
        });
    }

    private void setMainImage(rojerusan.RSLabelImage source) {
        ImageIcon icon = imageMap.get(source);
        if (icon != null) {
            rSLabelImage2.setIcon(icon);
            imageMap.put(rSLabelImage2, icon);
            // Update currentIndex based on the selected image
            currentIndex = imageList.indexOf(icon);
            updatePaginationLabel();
        }
    }

    private void showZoomedImage(rojerusan.RSLabelImage label) {
        ImageIcon icon = imageMap.get(label);
        if (icon != null) {
            Image image = icon.getImage();
            int initialWidth = 800;
            int initialHeight = 600;
            Image scaledImage = image.getScaledInstance(initialWidth, initialHeight, Image.SCALE_SMOOTH);
            ImageIcon zoomedIcon = new ImageIcon(scaledImage);
            JLabel zoomedLabel = new JLabel(zoomedIcon);
            zoomedLabel.setHorizontalAlignment(JLabel.CENTER);

            JPanel imagePanel = new JPanel();
            imagePanel.add(zoomedLabel);

            JScrollPane scrollPane = new JScrollPane(imagePanel);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

            JDialog dialog = new JDialog(JOptionPane.getFrameForComponent(this), "Zoomed Image", false);
            dialog.setContentPane(scrollPane);
            dialog.setSize(initialWidth + 20, initialHeight + 40);
            dialog.setLocationRelativeTo(this);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

            zoomedLabel.addMouseWheelListener(new MouseAdapter() {
                private double zoomFactor = 1.0;

                @Override
                public void mouseWheelMoved(MouseWheelEvent e) {
                    int notches = e.getWheelRotation();
                    if (notches < 0) {
                        zoomFactor *= 1.1;
                    } else {
                        zoomFactor /= 1.1;
                        if (zoomFactor < 0.1) {
                            zoomFactor = 0.1;
                        }
                    }

                    int newWidth = (int) (initialWidth * zoomFactor);
                    int newHeight = (int) (initialHeight * zoomFactor);
                    Image newScaledImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                    zoomedIcon.setImage(newScaledImage);
                    zoomedLabel.repaint();

                    dialog.setSize(newWidth + 20, newHeight + 40);
                    dialog.revalidate();
                }
            });

            zoomedLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    dialog.dispose();
                }
            });

            dialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "No image available to zoom.", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("No image found for zoom in " + label.getName());
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

        jPanel2 = new javax.swing.JPanel();
        btnVolver = new RSMaterialComponent.RSButtonShape();
        rSLabelIcon1 = new rojerusan.RSLabelIcon();
        rSLabelIcon2 = new rojerusan.RSLabelIcon();
        rSLabelImage2 = new rojerusan.RSLabelImage();
        rSLabelImage3 = new rojerusan.RSLabelImage();
        rSLabelImage4 = new rojerusan.RSLabelImage();
        rSLabelImage5 = new rojerusan.RSLabelImage();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        color1 = new javax.swing.JLabel();
        descripcion1 = new javax.swing.JLabel();
        material = new javax.swing.JLabel();
        nombre = new javax.swing.JLabel();
        material1 = new javax.swing.JLabel();
        material2 = new javax.swing.JLabel();
        material3 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1304, 742));

        jPanel2.setBackground(new java.awt.Color(242, 247, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnVolver.setBackground(new java.awt.Color(46, 49, 82));
        btnVolver.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        btnVolver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/volver (1).png"))); // NOI18N
        btnVolver.setText(" Volver");
        btnVolver.setBackgroundHover(new java.awt.Color(67, 150, 209));
        btnVolver.setFont(new java.awt.Font("Roboto Bold", 1, 17)); // NOI18N
        btnVolver.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        btnVolver.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });
        jPanel2.add(btnVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 130, 30));

        rSLabelIcon1.setBackground(new java.awt.Color(255, 204, 51));
        rSLabelIcon1.setForeground(new java.awt.Color(204, 255, 102));
        rSLabelIcon1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.KEYBOARD_ARROW_RIGHT);
        jPanel2.add(rSLabelIcon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 320, -1, -1));

        rSLabelIcon2.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.KEYBOARD_ARROW_LEFT);
        jPanel2.add(rSLabelIcon2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 290, -1, -1));

        rSLabelImage2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/catalogo/armario3.jpg"))); // NOI18N
        jPanel2.add(rSLabelImage2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, 610, 510));

        rSLabelImage3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/WhatsApp_Image_2025-03-28_at_10.59.04_AM-removebg-preview.png"))); // NOI18N
        jPanel2.add(rSLabelImage3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 140, 140));
        jPanel2.add(rSLabelImage4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 140, 150));
        jPanel2.add(rSLabelImage5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 410, 140, 150));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 28)); // NOI18N
        jLabel4.setText("paginación");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(382, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(281, 281, 281))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(559, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 51, 800, 600));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 23)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 255));
        jLabel6.setText("categoria");

        color1.setFont(new java.awt.Font("Segoe UI", 0, 28)); // NOI18N
        color1.setText("Color: ");

        descripcion1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        descripcion1.setText("Descripción");

        material.setFont(new java.awt.Font("Segoe UI", 0, 28)); // NOI18N
        material.setText("material");

        nombre.setFont(new java.awt.Font("Segoe UI", 0, 28)); // NOI18N
        nombre.setText("nombre");

        material1.setFont(new java.awt.Font("Segoe UI", 0, 28)); // NOI18N
        material1.setText("Profundidad");

        material2.setFont(new java.awt.Font("Segoe UI", 0, 28)); // NOI18N
        material2.setText("Ancho");

        material3.setFont(new java.awt.Font("Segoe UI", 0, 28)); // NOI18N
        material3.setText("Largo");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(nombre)
                            .addComponent(descripcion1)
                            .addComponent(material1)
                            .addComponent(material3)
                            .addComponent(material2)
                            .addComponent(material)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(color1)))
                .addContainerGap(270, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(descripcion1)
                .addGap(27, 27, 27)
                .addComponent(color1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(material)
                .addGap(18, 18, 18)
                .addComponent(material2)
                .addGap(30, 30, 30)
                .addComponent(material3)
                .addGap(35, 35, 35)
                .addComponent(material1)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 50, 490, 600));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 740, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        // Crear una nueva instancia de pedido pasando el contenedor
        removeAll(); // Clear current content
        setLayout(new BorderLayout()); // Use BorderLayout for the panel

        Productos detallePanel = new Productos(idCategoria, nombreCategoria, parentPanel);
        add(detallePanel, BorderLayout.CENTER);

        revalidate();
        repaint();
    }//GEN-LAST:event_btnVolverActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RSMaterialComponent.RSButtonShape btnVolver;
    private javax.swing.JLabel color1;
    private javax.swing.JLabel descripcion1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel material;
    private javax.swing.JLabel material1;
    private javax.swing.JLabel material2;
    private javax.swing.JLabel material3;
    private javax.swing.JLabel nombre;
    private rojerusan.RSLabelIcon rSLabelIcon1;
    private rojerusan.RSLabelIcon rSLabelIcon2;
    private rojerusan.RSLabelImage rSLabelImage2;
    private rojerusan.RSLabelImage rSLabelImage3;
    private rojerusan.RSLabelImage rSLabelImage4;
    private rojerusan.RSLabelImage rSLabelImage5;
    // End of variables declaration//GEN-END:variables
}
