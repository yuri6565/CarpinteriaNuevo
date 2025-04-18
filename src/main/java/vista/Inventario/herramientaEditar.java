/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package vista.Inventario;

import controlador.Ctrl_CategoriaHerramienta;
import controlador.Ctrl_MarcaHerramienta;
import controlador.Ctrl_UnidadHerramienta;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import modelo.Categoria;
import modelo.HerramientaDatos;
import modelo.Marca;
import modelo.MaterialDatos;
import modelo.Unidad;

/**
 *
 * @author ZenBook
 */
public class herramientaEditar extends javax.swing.JDialog {
    public MaterialDatos material;
    private boolean guardado = false;
    private byte[] imagenBytes; // Para almacenar la imagen en bytes
    private List<Categoria> categorias;
    private List<Marca> marcas;
    private List<Unidad> unidades;

    /**
     * Creates new form herramientaEditar
     */
    public herramientaEditar(java.awt.Frame parent, boolean modal, MaterialDatos material) {
        super(parent, modal);
        initComponents();
        this.material = material;
        cargarDatosComboBoxes(); // Cargar datos en los combo boxes
        cargarMaterial(); // Llamar al método para prellenar los campos
    }
    
    
        // Método para cargar datos en los combo boxes
    private void cargarDatosComboBoxes() {
        Ctrl_CategoriaHerramienta ctrlCategoria = new Ctrl_CategoriaHerramienta();
        categorias = ctrlCategoria.obtenerCategoriasHerramienta();
        cmbCategoria.removeAllItems();
        cmbCategoria.addItem("Seleccione categoría:");
        for (Categoria cat : categorias) {
            cmbCategoria.addItem(cat.getNombre());
        }

        Ctrl_MarcaHerramienta ctrlMarca = new Ctrl_MarcaHerramienta();
        marcas = ctrlMarca.obtenerMarcasHerramienta();
        cmbMarca.removeAllItems();
        cmbMarca.addItem("Seleccione marca:");
        for (Marca m : marcas) {
            cmbMarca.addItem(m.getNombre());
        }

        Ctrl_UnidadHerramienta ctrlUnidad = new Ctrl_UnidadHerramienta();
        unidades = ctrlUnidad.obtenerUnidadesHerramienta();
        cmbUnidad.removeAllItems();
        cmbUnidad.addItem("Seleccione unidad-medida:");
        for (Unidad um : unidades) {
            cmbUnidad.addItem(um.getNombre());
        }

        cmbEstado.removeAllItems();
        cmbEstado.addItem("Seleccione estado:");
        cmbEstado.addItem("Disponible");
        cmbEstado.addItem("No disponible");

        cmbStock.removeAllItems();
        cmbStock.addItem("Seleccione stock:");
        cmbStock.addItem("Bajo");
        cmbStock.addItem("Medio");
        cmbStock.addItem("Alto");
    }
    
// Método para prellenar los campos con los datos del material
    private void cargarMaterial() {
        if (material != null) {
            txtNombre.setText(material.getNombre());
            txtDescripcion.setText(material.getDescripcion());
            txtCantidad.setText(String.valueOf(material.getCantidad()));
            //txtCodigo.setText(String.valueOf(material.getIdInventario())); // Mostrar el ID como código

            // Seleccionar categoría
            for (Categoria cat : categorias) {
                if (cat.getCodigo() == material.getIdCategoria()) {
                    cmbCategoria.setSelectedItem(cat.getNombre());
                    break;
                }
            }

            // Seleccionar marca
            for (Marca m : marcas) {
                if (m.getCodigo() == material.getIdMarca()) {
                    cmbMarca.setSelectedItem(m.getNombre());
                    break;
                }
            }

            // Seleccionar unidad de medida
            for (Unidad um : unidades) {
                if (um.getCodigo() == material.getIdUnidadMedida()) {
                    cmbUnidad.setSelectedItem(um.getNombre());
                    break;
                }
            }

            // Seleccionar estado
            cmbEstado.setSelectedItem(material.getEstado());

            // Seleccionar stock
            cmbStock.setSelectedItem(material.getStock());

            // Cargar imagen si existe
            if (material.getImagen() != null) {
                ImageIcon imagenIcon = new ImageIcon(material.getImagen());
                // Obtener dimensiones del lblImagen
                int width = lblImagen.getWidth();  // 140
                int height = lblImagen.getHeight(); // 140
                Image img = imagenIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
                lblImagen.setIcon(new ImageIcon(img));
            } else {
                lblImagen.setIcon(null);
            }
        }
    }

    public boolean isGuardado() {
        return guardado;
    }

    public MaterialDatos getMaterial() {
        return material;
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
        btnSubirImagen = new rojeru_san.RSButton();
        lblImagen = new javax.swing.JLabel();
        btnCancelar = new rojeru_san.RSButtonRiple();
        btnGuardar = new rojeru_san.RSButtonRiple();
        cmbStock = new RSMaterialComponent.RSComboBoxMaterial();
        cmbEstado = new RSMaterialComponent.RSComboBoxMaterial();
        cmbUnidad = new RSMaterialComponent.RSComboBoxMaterial();
        cmbCategoria = new RSMaterialComponent.RSComboBoxMaterial();
        cmbMarca = new RSMaterialComponent.RSComboBoxMaterial();
        txtCantidad = new RSMaterialComponent.RSTextFieldMaterial();
        jLabel7 = new javax.swing.JLabel();
        txtDescripcion = new RSMaterialComponent.RSTextFieldMaterial();
        jLabel5 = new javax.swing.JLabel();
        txtNombre = new RSMaterialComponent.RSTextFieldMaterial();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(46, 49, 82));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Century751 BT", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Editar herramienta");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 520, 50));

        btnSubirImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/up-arrow (1).png"))); // NOI18N
        btnSubirImagen.setText(" Subir imagen");
        btnSubirImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubirImagenActionPerformed(evt);
            }
        });
        jPanel1.add(btnSubirImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 290, 140, 30));

        lblImagen.setBackground(new java.awt.Color(153, 204, 255));
        lblImagen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(lblImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 330, 150, 120));

        btnCancelar.setBackground(new java.awt.Color(46, 49, 82));
        btnCancelar.setText("Cancelar");
        btnCancelar.setColorHover(new java.awt.Color(204, 0, 0));
        btnCancelar.setFont(new java.awt.Font("Humnst777 BlkCn BT", 1, 14)); // NOI18N
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 480, 140, -1));

        btnGuardar.setBackground(new java.awt.Color(46, 49, 82));
        btnGuardar.setText("Actualizar");
        btnGuardar.setColorHover(new java.awt.Color(0, 153, 51));
        btnGuardar.setFont(new java.awt.Font("Humnst777 BlkCn BT", 1, 14)); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 480, 140, -1));

        cmbStock.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione stock:", "Bajo", "Medio", "alto" }));
        cmbStock.setColorMaterial(new java.awt.Color(0, 0, 0));
        cmbStock.setFont(new java.awt.Font("Roboto Bold", 0, 14)); // NOI18N
        cmbStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbStockActionPerformed(evt);
            }
        });
        jPanel1.add(cmbStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 250, -1, 30));

        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione estado:", "Disponible", "No disponible" }));
        cmbEstado.setColorMaterial(new java.awt.Color(0, 0, 0));
        cmbEstado.setFont(new java.awt.Font("Roboto Bold", 0, 14)); // NOI18N
        cmbEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEstadoActionPerformed(evt);
            }
        });
        jPanel1.add(cmbEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 170, -1, 30));

        cmbUnidad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione unidad-medida:", "RSItem 1", "RSItem 2", "RSItem 3", "RSItem 4" }));
        cmbUnidad.setColorMaterial(new java.awt.Color(0, 0, 0));
        cmbUnidad.setFont(new java.awt.Font("Roboto Bold", 0, 14)); // NOI18N
        cmbUnidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbUnidadActionPerformed(evt);
            }
        });
        jPanel1.add(cmbUnidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 90, -1, 30));

        cmbCategoria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione categoría:", "Categoría 1", "Categoría 2", "Categoría 3" }));
        cmbCategoria.setColorMaterial(new java.awt.Color(0, 0, 0));
        cmbCategoria.setFont(new java.awt.Font("Roboto Bold", 0, 14)); // NOI18N
        cmbCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCategoriaActionPerformed(evt);
            }
        });
        jPanel1.add(cmbCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 200, 30));

        cmbMarca.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione marca:" }));
        cmbMarca.setColorMaterial(new java.awt.Color(0, 0, 0));
        cmbMarca.setFont(new java.awt.Font("Roboto Bold", 0, 14)); // NOI18N
        cmbMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMarcaActionPerformed(evt);
            }
        });
        jPanel1.add(cmbMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, -1, 30));

        txtCantidad.setForeground(new java.awt.Color(0, 0, 0));
        txtCantidad.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtCantidad.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCantidad.setPhColor(new java.awt.Color(0, 0, 0));
        txtCantidad.setPlaceholder("Ingrese la cantidad...");
        txtCantidad.setSelectionColor(new java.awt.Color(0, 0, 0));
        txtCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadActionPerformed(evt);
            }
        });
        jPanel1.add(txtCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 200, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel7.setText("Marca:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, -1, -1));

        txtDescripcion.setForeground(new java.awt.Color(0, 0, 0));
        txtDescripcion.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtDescripcion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDescripcion.setPhColor(new java.awt.Color(0, 0, 0));
        txtDescripcion.setPlaceholder("Ingrese el descripcion...");
        txtDescripcion.setSelectionColor(new java.awt.Color(0, 0, 0));
        txtDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescripcionActionPerformed(evt);
            }
        });
        jPanel1.add(txtDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 200, 30));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel5.setText("Descripcion:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        txtNombre.setForeground(new java.awt.Color(0, 0, 0));
        txtNombre.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtNombre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNombre.setPhColor(new java.awt.Color(0, 0, 0));
        txtNombre.setPlaceholder("Ingrese el nombre...");
        txtNombre.setSelectionColor(new java.awt.Color(0, 0, 0));
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 200, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel3.setText("Stock:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 220, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel8.setText("Cantidad:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel9.setText("Categoria:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel4.setText("Nombre:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel6.setText("Unidad de Medida:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 60, -1, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel10.setText("Estado:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 140, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSubirImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubirImagenActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Imágenes", "jpg", "png", "jpeg"));

        int opcion = fileChooser.showOpenDialog(null);
        if (opcion == JFileChooser.APPROVE_OPTION) {
            try {
                File archivo = fileChooser.getSelectedFile();
                // Convertir la imagen a bytes
                imagenBytes = Files.readAllBytes(archivo.toPath());

                // Obtener dimensiones del lblImagen
                int width = lblImagen.getWidth();  // 140
                int height = lblImagen.getHeight(); // 140

                // Mostrar previsualización ajustada al tamaño de lblImagen
                ImageIcon imagen = new ImageIcon(archivo.getAbsolutePath());
                Image img = imagen.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
                lblImagen.setIcon(new ImageIcon(img));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al leer la imagen: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnSubirImagenActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed

        dispose(); // Cierra la ventana emergente
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        String nombre = txtNombre.getText().trim();
        String descripcion = txtDescripcion.getText().trim();
        String estado = (String) cmbEstado.getSelectedItem();
        String stock = (String) cmbStock.getSelectedItem();
        String categoriaNombre = (String) cmbCategoria.getSelectedItem();
        String marcaNombre = (String) cmbMarca.getSelectedItem();
        String unidadNombre = (String) cmbUnidad.getSelectedItem();

        // Validar campos obligatorios
        if (nombre.isEmpty() || descripcion.isEmpty() || estado == null || stock == null ||
            categoriaNombre == null || marcaNombre == null || unidadNombre == null) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos obligatorios.",
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (estado.equals("Seleccione estado:") || stock.equals("Seleccione stock:") ||
            categoriaNombre.equals("Seleccione categoría:") || marcaNombre.equals("Seleccione marca:") ||
            unidadNombre.equals("Seleccione unidad-medida:")) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione valores válidos en los campos.",
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar y obtener la cantidad
        int cantidad;
        try {
            cantidad = Integer.parseInt(txtCantidad.getText().trim());
            if (cantidad < 0) {
                JOptionPane.showMessageDialog(this, "La cantidad no puede ser negativa.",
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un número válido en la cantidad.",
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verificar si hay una imagen seleccionada (opcional, puede mantener la imagen original si no se cambia)
        if (imagenBytes == null && material.getImagen() == null) {
            JOptionPane.showMessageDialog(this, "Por favor, suba una imagen o conserve la imagen original.",
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Mapear nombres a IDs
        int idCategoria = -1;
        for (Categoria cat : categorias) {
            if (cat.getNombre().equals(categoriaNombre)) {
                idCategoria = cat.getCodigo();
                break;
            }
        }

        int idMarca = -1;
        for (Marca m : marcas) {
            if (m.getNombre().equals(marcaNombre)) {
                idMarca = m.getCodigo();
                break;
            }
        }

        int idUnidadMedida = -1;
        for (Unidad um : unidades) {
            if (um.getNombre().equals(unidadNombre)) {
                idUnidadMedida = um.getCodigo();
                break;
            }
        }

        if (idCategoria == -1 || idMarca == -1 || idUnidadMedida == -1) {
            JOptionPane.showMessageDialog(this, "Error al mapear los datos seleccionados.",
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Actualizar los datos del material
        if (material != null) {
            material.setNombre(nombre);
            material.setDescripcion(descripcion);
            material.setEstado(estado);
            material.setStock(stock);
            material.setCantidad(cantidad);
            material.setIdCategoria(idCategoria);
            material.setIdMarca(idMarca);
            material.setIdUnidadMedida(idUnidadMedida);
            material.setImagen(imagenBytes != null ? imagenBytes : material.getImagen()); // Usar nueva imagen o mantener la original
        } else {
            JOptionPane.showMessageDialog(this, "Error: material no está inicializado.",
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        guardado = true; // Marcar que se han guardado los cambios
        dispose(); // Cierra el JDialog
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void cmbStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbStockActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbStockActionPerformed

    private void cmbEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbEstadoActionPerformed

    private void cmbUnidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbUnidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbUnidadActionPerformed

    private void cmbCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCategoriaActionPerformed

    private void cmbMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMarcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbMarcaActionPerformed

    private void txtCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadActionPerformed

    private void txtDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

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
            java.util.logging.Logger.getLogger(herramientaEditar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(herramientaEditar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(herramientaEditar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(herramientaEditar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                herramientaEditar dialog = new herramientaEditar(new javax.swing.JFrame(), true, null);
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
    private rojeru_san.RSButtonRiple btnCancelar;
    private rojeru_san.RSButtonRiple btnGuardar;
    private rojeru_san.RSButton btnSubirImagen;
    private RSMaterialComponent.RSComboBoxMaterial cmbCategoria;
    private RSMaterialComponent.RSComboBoxMaterial cmbEstado;
    private RSMaterialComponent.RSComboBoxMaterial cmbMarca;
    private RSMaterialComponent.RSComboBoxMaterial cmbStock;
    private RSMaterialComponent.RSComboBoxMaterial cmbUnidad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblImagen;
    private RSMaterialComponent.RSTextFieldMaterial txtCantidad;
    private RSMaterialComponent.RSTextFieldMaterial txtDescripcion;
    private RSMaterialComponent.RSTextFieldMaterial txtNombre;
    // End of variables declaration//GEN-END:variables
}
