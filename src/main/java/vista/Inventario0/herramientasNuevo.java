/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package vista.Inventario0;

import controlador.Ctrl_CategoriaHerramienta;
import controlador.Ctrl_MarcaHerramienta;
import controlador.Ctrl_UnidadHerramienta;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.AbstractDocument;
import modelo.Categoria;
import modelo.HerramientaDatos;
import modelo.Marca;
import modelo.MaterialDatos;
import modelo.Unidad;

/**
 *
 * @author ZenBook
 */
public class herramientasNuevo extends javax.swing.JDialog {

    private byte[] imagenBytes; // Para almacenar la imagen en bytes
    private byte[] previewImageBytes; // Para almacenar la imagen de vista previa
    private ImageIcon previewImageIcon; // Para mantener la imagen de vista previa escalada

    public boolean materialGuardado = false;
    public HerramientaDatos material;
    private List<Categoria> categorias;
    private List<Marca> marcas;
    private List<Unidad> unidades;

    /**
     * Creates new form herramientasNuevo
     */
    public herramientasNuevo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setTitle("Nueva herramienta");

        // Cargar la imagen de vista previa
        cargarImagenVistaPrevia();

        // Cargar la imagen predeterminada para la base de datos
        cargarImagenPorDefectoParaBaseDatos();

        // Cargar datos en los combo boxes
        cargarCategorias();
        cargarMarcas();
        cargarUnidades();

        ((AbstractDocument) txtPrecioUnitario.getDocument()).setDocumentFilter(new NumberFormatFilterInventario());

        // Agregar esto en el constructor o método de inicialización de tu clase
        txtCantidad.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                String currentText = txtCantidad.getText();

                // Permitir: dígitos, coma, backspace y delete
                if (!(Character.isDigit(c) || c == ',' || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
                    e.consume();
                    JOptionPane.showMessageDialog(null,
                            "Solo se permiten números enteros y decimales (use coma para decimales)",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

            }
        });

    }

    private void cargarCategorias() {
        Ctrl_CategoriaHerramienta ctrl = new Ctrl_CategoriaHerramienta();
        categorias = ctrl.obtenerCategoriasHerramienta();
        cmbCategoria.removeAllItems();
        cmbCategoria.addItem("Seleccione categoría:");
        for (Categoria cat : categorias) {
            cmbCategoria.addItem(cat.getNombre());
        }
    }

    private void cargarMarcas() {
        Ctrl_MarcaHerramienta ctrl = new Ctrl_MarcaHerramienta();
        marcas = ctrl.obtenerMarcasHerramienta();
        cmbMarca.removeAllItems();
        cmbMarca.addItem("Seleccione marca:");
        for (Marca m : marcas) {
            cmbMarca.addItem(m.getNombre());
        }
    }

    private void cargarUnidades() {
        Ctrl_UnidadHerramienta ctrl = new Ctrl_UnidadHerramienta();
        unidades = ctrl.obtenerUnidadesHerramienta();
        cmbUnidad.removeAllItems();
        cmbUnidad.addItem("Seleccione unidad-medida:");
        for (Unidad um : unidades) {
            cmbUnidad.addItem(um.getNombre());
        }
    }

    private void cargarImagenVistaPrevia() {
        try {
            // Cargar la imagen de vista previa desde los recursos
            String rutaImagenPrevia = "/subirImagen.png"; // Ajusta la ruta según la ubicación
            java.net.URL imgURL = getClass().getResource(rutaImagenPrevia);

            if (imgURL == null) {
                JOptionPane.showMessageDialog(this, "No se encontró la imagen de vista previa.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Leer los bytes de la imagen de vista previa
            previewImageBytes = Files.readAllBytes(new File(imgURL.toURI()).toPath());

            // Obtener dimensiones del lblImagen
            int width = lblImagen.getWidth();  // 180
            int height = lblImagen.getHeight(); // 150

            // Escalar la imagen de vista previa
            ImageIcon imagen = new ImageIcon(imgURL);
            Image img = imagen.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            previewImageIcon = new ImageIcon(img);
            lblImagen.setIcon(previewImageIcon);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar la imagen de vista previa: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

// Método para cargar la imagen que se guardará en la base de datos
    private void cargarImagenPorDefectoParaBaseDatos() {
        try {
            // Cargar la imagen predeterminada para la base de datos
            String rutaImagenDefecto = "/imagenSin.png"; // Ajusta la ruta según la ubicación
            java.net.URL imgURL = getClass().getResource(rutaImagenDefecto);

            if (imgURL == null) {
                JOptionPane.showMessageDialog(this, "No se encontró la imagen por defecto para la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Leer los bytes de la imagen predeterminada
            imagenBytes = Files.readAllBytes(new File(imgURL.toURI()).toPath());

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar la imagen por defecto para la base de datos: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void formatNumberField() {
        // Eliminar cualquier carácter que no sea número
        String text = txtPrecioUnitario.getText().replaceAll("[^0-9]", "");

        if (!text.isEmpty()) {
            try {
                long amount = Long.parseLong(text);

                // Formatear el número con puntos como separadores de miles
                String formatted = String.format("%,d", amount).replace(",", ".");

                // Evitar bucles infinitos al comparar antes de actualizar
                if (!formatted.equals(txtPrecioUnitario.getText())) {
                    txtPrecioUnitario.setText(formatted);
                    txtPrecioUnitario.setCaretPosition(formatted.length());
                }
            } catch (NumberFormatException e) {
                // Ignorar errores de formato
            }
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

        panelP = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cmbEstado = new RSMaterialComponent.RSComboBoxMaterial();
        txtNombre = new RSMaterialComponent.RSTextFieldMaterial();
        cmbCategoria = new RSMaterialComponent.RSComboBoxMaterial();
        txtCantidad = new RSMaterialComponent.RSTextFieldMaterial();
        btnCancelar = new rojeru_san.RSButtonRiple();
        btnGuardar = new rojeru_san.RSButtonRiple();
        cmbUnidad = new RSMaterialComponent.RSComboBoxMaterial();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtPrecioUnitario = new RSMaterialComponent.RSTextFieldMaterial();
        jLabel9 = new javax.swing.JLabel();
        cmbMarca = new RSMaterialComponent.RSComboBoxMaterial();
        jLabel12 = new javax.swing.JLabel();
        lblImagen = new javax.swing.JLabel();
        btnSubirImagen = new RSMaterialComponent.RSButtonShape();
        btnQuitar = new RSMaterialComponent.RSButtonShape();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelP.setBackground(new java.awt.Color(255, 255, 255));
        panelP.setPreferredSize(new java.awt.Dimension(500, 500));
        panelP.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(46, 49, 82));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Century751 BT", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Agregar herramienta");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        panelP.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 520, 50));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel3.setText("Nombre:");
        panelP.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel5.setText("Descripcion: (opcional)");
        panelP.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel7.setText("Estado:");
        panelP.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 205, -1, -1));

        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione estado:", "Disponible", "Reparación", "Dañado" }));
        cmbEstado.setColorMaterial(new java.awt.Color(0, 0, 0));
        cmbEstado.setFont(new java.awt.Font("Roboto Bold", 0, 14)); // NOI18N
        cmbEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEstadoActionPerformed(evt);
            }
        });
        panelP.add(cmbEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 230, -1, 30));

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
        panelP.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 200, 30));

        cmbCategoria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione categoría:", "Categoría 1", "Categoría 2", "Categoría 3" }));
        cmbCategoria.setColorMaterial(new java.awt.Color(0, 0, 0));
        cmbCategoria.setFont(new java.awt.Font("Roboto Bold", 0, 14)); // NOI18N
        cmbCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCategoriaActionPerformed(evt);
            }
        });
        panelP.add(cmbCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 90, 200, 30));

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
        panelP.add(txtCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 200, 30));

        btnCancelar.setBackground(new java.awt.Color(46, 49, 82));
        btnCancelar.setText("Cancelar");
        btnCancelar.setColorHover(new java.awt.Color(204, 0, 0));
        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        panelP.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 540, 140, -1));

        btnGuardar.setBackground(new java.awt.Color(46, 49, 82));
        btnGuardar.setText("Guardar");
        btnGuardar.setColorHover(new java.awt.Color(0, 153, 51));
        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        panelP.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 540, 140, -1));

        cmbUnidad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione unidad-medida:", "RSItem 1", "RSItem 2", "RSItem 3", "RSItem 4" }));
        cmbUnidad.setColorMaterial(new java.awt.Color(0, 0, 0));
        cmbUnidad.setFont(new java.awt.Font("Roboto Bold", 0, 14)); // NOI18N
        cmbUnidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbUnidadActionPerformed(evt);
            }
        });
        panelP.add(cmbUnidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, 30));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel8.setText("Cantidad:");
        panelP.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, -1, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel10.setText("Categoria:");
        panelP.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 60, -1, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel11.setText("Unidad de medida:");
        panelP.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel6.setText("Precio unitario:");
        panelP.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, -1, -1));

        txtPrecioUnitario.setForeground(new java.awt.Color(0, 0, 0));
        txtPrecioUnitario.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtPrecioUnitario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtPrecioUnitario.setPhColor(new java.awt.Color(0, 0, 0));
        txtPrecioUnitario.setPlaceholder("Ingrese el precio unitario...");
        txtPrecioUnitario.setSelectionColor(new java.awt.Color(0, 0, 0));
        txtPrecioUnitario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioUnitarioActionPerformed(evt);
            }
        });
        txtPrecioUnitario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPrecioUnitarioKeyReleased(evt);
            }
        });
        panelP.add(txtPrecioUnitario, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 200, 30));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel9.setText("Marca:");
        panelP.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 130, -1, -1));

        cmbMarca.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione marca:" }));
        cmbMarca.setColorMaterial(new java.awt.Color(0, 0, 0));
        cmbMarca.setFont(new java.awt.Font("Roboto Bold", 0, 14)); // NOI18N
        cmbMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMarcaActionPerformed(evt);
            }
        });
        panelP.add(cmbMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 160, -1, 30));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel12.setText("Imagen: (opcional)");
        panelP.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 280, -1, -1));

        lblImagen.setBackground(new java.awt.Color(153, 204, 255));
        lblImagen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelP.add(lblImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 310, 180, 150));

        btnSubirImagen.setBackground(new java.awt.Color(28, 135, 212));
        btnSubirImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/importar.png"))); // NOI18N
        btnSubirImagen.setText("Subir");
        btnSubirImagen.setBackgroundHover(new java.awt.Color(35, 112, 210));
        btnSubirImagen.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        btnSubirImagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnSubirImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubirImagenActionPerformed(evt);
            }
        });
        panelP.add(btnSubirImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 470, 80, 26));

        btnQuitar.setBackground(new java.awt.Color(163, 38, 0));
        btnQuitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/boton-menos (1).png"))); // NOI18N
        btnQuitar.setText("Quitar");
        btnQuitar.setBackgroundHover(new java.awt.Color(147, 0, 0));
        btnQuitar.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        btnQuitar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarActionPerformed(evt);
            }
        });
        panelP.add(btnQuitar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 470, 80, 26));

        txtDescripcion.setColumns(10);
        txtDescripcion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setRows(1);
        txtDescripcion.setTabSize(1);
        txtDescripcion.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtDescripcion);

        panelP.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 190, 60));

        getContentPane().add(panelP, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 520, 590));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbEstadoActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void cmbCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCategoriaActionPerformed

    private void txtCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        materialGuardado = false;
        dispose(); // Cierra la ventana emergente
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // Obtener los valores de los campos
        String nombre = txtNombre.getText().trim();
        String descripcion = txtDescripcion.getText().trim();
        String cantidad = txtCantidad.getText().trim();
        String categoriaNombre = (String) cmbCategoria.getSelectedItem();
        String marcaNombre = (String) cmbMarca.getSelectedItem();
        String unidadNombre = (String) cmbUnidad.getSelectedItem();
        String estado = (String) cmbEstado.getSelectedItem();

        // Validar campos obligatorios
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese el nombre de la herramienta.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar y obtener la cantidad
        if (cantidad.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese la cantidad.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (categoriaNombre == null || categoriaNombre.equals("Seleccione categoría:")) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione una categoría.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (marcaNombre == null || marcaNombre.equals("Seleccione marca:")) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione una marca.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (unidadNombre == null || unidadNombre.equals("Seleccione unidad-medida:")) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione una unidad de medida.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (estado == null || estado.equals("Seleccione estado:")) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione una estado.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar y obtener la cantidad
        int precioUnitario;
        try {
            precioUnitario = Integer.parseInt(txtPrecioUnitario.getText().replace(".", "").trim());
            if (precioUnitario < 0) {
                JOptionPane.showMessageDialog(this, "El precio no puede ser negativo.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un número válido en el precio.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Obtener los IDs correspondientes a los nombres seleccionados
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

        // Validar que se hayan encontrado los IDs
        if (idCategoria == -1) {
            JOptionPane.showMessageDialog(this, "Error: No se encontró la categoría seleccionada.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (idMarca == -1) {
            JOptionPane.showMessageDialog(this, "Error: No se encontró la marca seleccionada.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (idUnidadMedida == -1) {
            JOptionPane.showMessageDialog(this, "Error: No se encontró la unidad de medida seleccionada.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Usar imagenBytes (que contiene la imagen predeterminada para la base de datos)
        if (imagenBytes == null) {
            cargarImagenPorDefectoParaBaseDatos(); // Recargar si no se ha inicializado
        }

        // Crear el objeto MaterialDatos
        material = new HerramientaDatos(
                nombre,
                descripcion,
                cantidad,
                precioUnitario,
                estado,
                idCategoria,
                idMarca,
                idUnidadMedida,
                imagenBytes
        );

        materialGuardado = true;
        dispose();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void cmbUnidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbUnidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbUnidadActionPerformed

    private void txtPrecioUnitarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioUnitarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioUnitarioActionPerformed

    private void cmbMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMarcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbMarcaActionPerformed

    private void btnSubirImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubirImagenActionPerformed
        try {
            // Configurar el Look and Feel de Windows (más moderno)
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Imágenes", "jpg", "png", "jpeg"));

        int opcion = fileChooser.showOpenDialog(null);
        if (opcion == JFileChooser.APPROVE_OPTION) {
            try {
                File archivo = fileChooser.getSelectedFile();
                // Convertir la imagen a bytes
                imagenBytes = Files.readAllBytes(archivo.toPath()); // Actualizar imagen para la base de datos

                // Obtener dimensiones del lblImagen
                int width = lblImagen.getWidth();  // 180
                int height = lblImagen.getHeight(); // 150

                // Mostrar previsualización
                ImageIcon imagen = new ImageIcon(archivo.getAbsolutePath());
                Image img = imagen.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
                previewImageIcon = new ImageIcon(img); // Actualizar la vista previa
                lblImagen.setIcon(previewImageIcon);

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al leer la imagen: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnSubirImagenActionPerformed

    private void btnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarActionPerformed
        // Restaurar la imagen predeterminada y limpiar imagenBytes
        imagenBytes = null;
        cargarImagenPorDefectoParaBaseDatos(); // Reestablecer imagen predeterminada para base de datos
        cargarImagenVistaPrevia(); // Restaurar la vista previa predeterminada
    }//GEN-LAST:event_btnQuitarActionPerformed

    private void txtPrecioUnitarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioUnitarioKeyReleased
        formatNumberField();
    }//GEN-LAST:event_txtPrecioUnitarioKeyReleased

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
            java.util.logging.Logger.getLogger(herramientasNuevo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(herramientasNuevo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(herramientasNuevo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(herramientasNuevo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                herramientasNuevo dialog = new herramientasNuevo(new javax.swing.JFrame(), true);
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
    private RSMaterialComponent.RSButtonShape btnQuitar;
    private RSMaterialComponent.RSButtonShape btnSubirImagen;
    private RSMaterialComponent.RSComboBoxMaterial cmbCategoria;
    private RSMaterialComponent.RSComboBoxMaterial cmbEstado;
    private RSMaterialComponent.RSComboBoxMaterial cmbMarca;
    private RSMaterialComponent.RSComboBoxMaterial cmbUnidad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JPanel panelP;
    private RSMaterialComponent.RSTextFieldMaterial txtCantidad;
    private javax.swing.JTextArea txtDescripcion;
    private RSMaterialComponent.RSTextFieldMaterial txtNombre;
    private RSMaterialComponent.RSTextFieldMaterial txtPrecioUnitario;
    // End of variables declaration//GEN-END:variables
}
