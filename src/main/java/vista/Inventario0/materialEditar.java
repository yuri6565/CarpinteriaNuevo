/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package vista.Inventario0;

import controlador.Ctrl_CategoriaMaterial;
import controlador.Ctrl_MarcaMaterial;
import controlador.Ctrl_UnidadMaterial;
import modelo.MaterialDatos;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.text.AbstractDocument;
import modelo.Categoria;
import modelo.Marca;
import modelo.MaterialDatos;
import modelo.Unidad;

/**
 *
 * @author ZenBook
 */
public class materialEditar extends javax.swing.JDialog {

    private byte[] imagenBytes; // Para almacenar la imagen en bytes
    private byte[] previewImageBytes; // Para almacenar la imagen de vista previa
    private ImageIcon previewImageIcon; // Para mantener la imagen de vista previa escalada

    public MaterialDatos material;
    private boolean guardado = false;
    private List<Categoria> categorias;
    private List<Marca> marcas;
    private List<Unidad> unidades;

    /**
     * Creates new form materialEditar
     */
    public materialEditar(java.awt.Frame parent, boolean modal, MaterialDatos material) {
        super(parent, modal);
        initComponents();
        this.material = material;
        cargarDatosComboBoxes(); // Cargar datos en los combo boxes
        cargarMaterial(); // Llamar al método para prellenar los campos

        ((AbstractDocument) txtPrecioUnitario.getDocument()).setDocumentFilter(new NumberFormatFilterInventario());

        btnMarca.setToolTipText("<html><b>Agregar marca</html>");
        btnCategoria.setToolTipText("<html><b>Agregar categoría</html>");
        btnUM.setToolTipText("<html><b>Agregar U.M</html>");

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

        // Agregar esto en el constructor o método de inicialización de tu clase
        txtStockMinimo.addKeyListener(new KeyAdapter() {
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

    // Método para cargar datos en los combo boxes
    private void cargarDatosComboBoxes() {
        Ctrl_CategoriaMaterial ctrlCategoria = new Ctrl_CategoriaMaterial();
        categorias = ctrlCategoria.obtenerCategoriasMaterial();
        cmbCategoria.removeAllItems();
        cmbCategoria.addItem("Seleccione categoría:");
        for (Categoria cat : categorias) {
            cmbCategoria.addItem(cat.getNombre());
        }

        Ctrl_MarcaMaterial ctrlMarca = new Ctrl_MarcaMaterial();
        marcas = ctrlMarca.obtenerCategoriasMaterial();
        cmbMarca.removeAllItems();
        cmbMarca.addItem("Seleccione marca:");
        for (Marca m : marcas) {
            cmbMarca.addItem(m.getNombre());
        }

        Ctrl_UnidadMaterial ctrlUnidad = new Ctrl_UnidadMaterial();
        unidades = ctrlUnidad.obtenerCategoriasMaterial();
        cmbUnidad.removeAllItems();
        cmbUnidad.addItem("Seleccione unidad-medida:");
        for (Unidad um : unidades) {
            cmbUnidad.addItem(um.getNombre());
        }

    }

// Método para prellenar los campos con los datos del material
    private void cargarMaterial() {
        if (material != null) {
            txtNombre.setText(material.getNombre());
            txtDescripcion.setText(material.getDescripcion());
            txtCantidad.setText(String.valueOf(material.getCantidad()));
            txtStockMinimo.setText(String.valueOf(material.getStockMinimo()));
            //txtCodigo.setText(String.valueOf(material.getIdInventario())); // Mostrar el ID como código

            // Formatear el precio unitario con puntos como separadores de miles
            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setGroupingSeparator('.');
            DecimalFormat formatter = new DecimalFormat("#,###", symbols);
            formatter.setGroupingSize(3);
            txtPrecioUnitario.setText(formatter.format(material.getPrecioUnitario()));

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

        panelP = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cmbMarca = new RSMaterialComponent.RSComboBoxMaterial();
        txtNombre = new RSMaterialComponent.RSTextFieldMaterial();
        txtPrecioUnitario = new RSMaterialComponent.RSTextFieldMaterial();
        cmbCategoria = new RSMaterialComponent.RSComboBoxMaterial();
        txtCantidad = new RSMaterialComponent.RSTextFieldMaterial();
        btnCancelar = new rojeru_san.RSButtonRiple();
        btnGuardar = new rojeru_san.RSButtonRiple();
        lblImagen = new javax.swing.JLabel();
        cmbUnidad = new RSMaterialComponent.RSComboBoxMaterial();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtStockMinimo = new RSMaterialComponent.RSTextFieldMaterial();
        jLabel12 = new javax.swing.JLabel();
        btnSubirImagen = new RSMaterialComponent.RSButtonShape();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        btnQuitar = new RSMaterialComponent.RSButtonShape();
        btnUM = new RSMaterialComponent.RSButtonShape();
        btnCategoria = new RSMaterialComponent.RSButtonShape();
        btnMarca = new RSMaterialComponent.RSButtonShape();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        panelP.setBackground(new java.awt.Color(255, 255, 255));
        panelP.setPreferredSize(new java.awt.Dimension(500, 500));
        panelP.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(46, 49, 82));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Century751 BT", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Editar material");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        panelP.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 520, 50));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel3.setText("Nombre:");
        panelP.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel5.setText("Precio unitario:");
        panelP.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel7.setText("Marca:");
        panelP.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 138, -1, -1));

        cmbMarca.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione marca:" }));
        cmbMarca.setColorMaterial(new java.awt.Color(0, 0, 0));
        cmbMarca.setFont(new java.awt.Font("Roboto Bold", 0, 14)); // NOI18N
        cmbMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMarcaActionPerformed(evt);
            }
        });
        panelP.add(cmbMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 168, -1, 30));

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
        panelP.add(txtPrecioUnitario, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 200, 30));

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
        txtCantidad.setPlaceholder("Ingrese cantidad en número...");
        txtCantidad.setSelectionColor(new java.awt.Color(0, 0, 0));
        txtCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadActionPerformed(evt);
            }
        });
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadKeyTyped(evt);
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
        panelP.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 550, 140, -1));

        btnGuardar.setBackground(new java.awt.Color(46, 49, 82));
        btnGuardar.setText("Actualizar");
        btnGuardar.setColorHover(new java.awt.Color(0, 153, 51));
        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        panelP.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 550, 140, -1));

        lblImagen.setBackground(new java.awt.Color(153, 204, 255));
        lblImagen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelP.add(lblImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 324, 180, 150));

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
        jLabel11.setText("U.M:");
        panelP.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel6.setText("Descripcion: (opcional)");
        panelP.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel9.setText("Imagen: (opcional)");
        panelP.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 294, -1, -1));

        txtStockMinimo.setForeground(new java.awt.Color(0, 0, 0));
        txtStockMinimo.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtStockMinimo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtStockMinimo.setPhColor(new java.awt.Color(0, 0, 0));
        txtStockMinimo.setPlaceholder("Ingrese el stock mínimo...");
        txtStockMinimo.setSelectionColor(new java.awt.Color(0, 0, 0));
        txtStockMinimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStockMinimoActionPerformed(evt);
            }
        });
        panelP.add(txtStockMinimo, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 247, 200, 30));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel12.setText("Stock mínimo: (para alertas)");
        panelP.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 218, -1, -1));

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
        panelP.add(btnSubirImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 483, 80, 26));

        txtDescripcion.setColumns(10);
        txtDescripcion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setRows(1);
        txtDescripcion.setTabSize(1);
        txtDescripcion.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtDescripcion);

        panelP.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, 190, 60));

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
        panelP.add(btnQuitar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 483, 80, 26));

        btnUM.setBackground(new java.awt.Color(46, 49, 82));
        btnUM.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        btnUM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plus (2).png"))); // NOI18N
        btnUM.setBackgroundHover(new java.awt.Color(67, 150, 209));
        btnUM.setFont(new java.awt.Font("Roboto Bold", 1, 15)); // NOI18N
        btnUM.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        btnUM.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnUM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUMActionPerformed(evt);
            }
        });
        panelP.add(btnUM, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 25, 20));

        btnCategoria.setBackground(new java.awt.Color(46, 49, 82));
        btnCategoria.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        btnCategoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plus (2).png"))); // NOI18N
        btnCategoria.setBackgroundHover(new java.awt.Color(67, 150, 209));
        btnCategoria.setFont(new java.awt.Font("Roboto Bold", 1, 15)); // NOI18N
        btnCategoria.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        btnCategoria.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCategoriaActionPerformed(evt);
            }
        });
        panelP.add(btnCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 60, 25, 20));

        btnMarca.setBackground(new java.awt.Color(46, 49, 82));
        btnMarca.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        btnMarca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plus (2).png"))); // NOI18N
        btnMarca.setBackgroundHover(new java.awt.Color(67, 150, 209));
        btnMarca.setFont(new java.awt.Font("Roboto Bold", 1, 15)); // NOI18N
        btnMarca.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        btnMarca.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMarcaActionPerformed(evt);
            }
        });
        panelP.add(btnMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 138, 25, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 520, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panelP, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 610, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panelP, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMarcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbMarcaActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtPrecioUnitarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioUnitarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioUnitarioActionPerformed

    private void txtPrecioUnitarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioUnitarioKeyReleased
        formatNumberField();
    }//GEN-LAST:event_txtPrecioUnitarioKeyReleased

    private void cmbCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCategoriaActionPerformed

    private void txtCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadActionPerformed

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped

    }//GEN-LAST:event_txtCantidadKeyTyped

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose(); // Cierra la ventana emergente
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        String nombre = txtNombre.getText().trim();
        String descripcion = txtDescripcion.getText().trim();
        String cantidad = txtCantidad.getText().trim();
        String stockMinimo = txtStockMinimo.getText().trim();
        String categoriaNombre = (String) cmbCategoria.getSelectedItem();
        String marcaNombre = (String) cmbMarca.getSelectedItem();
        String unidadNombre = (String) cmbUnidad.getSelectedItem();

        // Validar campos obligatorios
        if (nombre.isEmpty() || cantidad.isEmpty() || stockMinimo.isEmpty() || categoriaNombre == null || marcaNombre == null || unidadNombre == null) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos obligatorios.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar y obtener la cantidad
        int precioUnitario;
        try {
            precioUnitario = Integer.parseInt(txtPrecioUnitario.getText().replace(".", "").trim());
            if (precioUnitario < 0) {
                JOptionPane.showMessageDialog(this, "El precio no puede ser negativo.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un número válido en el precio.",
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
            material.setCantidad(cantidad);
            material.setPrecioUnitario(precioUnitario);
            material.setIdCategoria(idCategoria);
            material.setIdMarca(idMarca);
            material.setIdUnidadMedida(idUnidadMedida);
            material.setImagen(imagenBytes != null ? imagenBytes : material.getImagen()); // Usar nueva imagen o mantener la original

            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(this,
                    "Material actualizado exitosamente",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);

            guardado = true;
            dispose(); // Cierra el JDialog
        } else {
            JOptionPane.showMessageDialog(this, "Error: material no está inicializado.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void cmbUnidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbUnidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbUnidadActionPerformed

    private void txtStockMinimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStockMinimoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStockMinimoActionPerformed

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

    private void btnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarActionPerformed
        // Restaurar la imagen predeterminada y limpiar imagenBytes
        imagenBytes = null;
        cargarImagenPorDefectoParaBaseDatos(); // Reestablecer imagen predeterminada para base de datos
        cargarImagenVistaPrevia(); // Restaurar la vista previa predeterminada
    }//GEN-LAST:event_btnQuitarActionPerformed

    private void btnUMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUMActionPerformed
        nuevaUMmaterial dialog = new nuevaUMmaterial(new javax.swing.JFrame(), true);
        dialog.setLocationRelativeTo(null);

        dialog.setCategoriaGuardadaListener(() -> {
            // Recargar unidades y actualizar la lista interna
            Ctrl_UnidadMaterial ctrl = new Ctrl_UnidadMaterial();
            unidades = ctrl.obtenerCategoriasMaterial();

            // Actualizar ComboBox
            cmbUnidad.removeAllItems();
            cmbUnidad.addItem("Seleccione unidad-medida:");
            for (Unidad um : unidades) {
                cmbUnidad.addItem(um.getNombre());
            }

            // Mantener la selección actual si existe
            if (material != null) {
                for (Unidad um : unidades) {
                    if (um.getCodigo() == material.getIdUnidadMedida()) {
                        cmbUnidad.setSelectedItem(um.getNombre());
                        break;
                    }
                }
            }
        });

        dialog.setVisible(true);
    }//GEN-LAST:event_btnUMActionPerformed

    private void btnCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCategoriaActionPerformed
        nuevaCategoriaMaterial dialog = new nuevaCategoriaMaterial(new javax.swing.JFrame(), true);
        dialog.setLocationRelativeTo(null);

        dialog.setCategoriaGuardadaListener(() -> {
            // Recargar categorías y actualizar la lista interna
            Ctrl_CategoriaMaterial ctrl = new Ctrl_CategoriaMaterial();
            categorias = ctrl.obtenerCategoriasMaterial();

            // Actualizar ComboBox
            cmbCategoria.removeAllItems();
            cmbCategoria.addItem("Seleccione categoría:");
            for (Categoria cat : categorias) {
                cmbCategoria.addItem(cat.getNombre());
            }

            // Mantener la selección actual si existe
            if (material != null) {
                for (Categoria cat : categorias) {
                    if (cat.getCodigo() == material.getIdCategoria()) {
                        cmbCategoria.setSelectedItem(cat.getNombre());
                        break;
                    }
                }
            }
        });

        dialog.setVisible(true);
    }//GEN-LAST:event_btnCategoriaActionPerformed

    private void btnMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMarcaActionPerformed
        nuevaMarcaMaterial dialog = new nuevaMarcaMaterial(new javax.swing.JFrame(), true);
        dialog.setLocationRelativeTo(null);

        dialog.setCategoriaGuardadaListener(() -> {
            // Recargar marcas y actualizar la lista interna
            Ctrl_MarcaMaterial ctrl = new Ctrl_MarcaMaterial();
            marcas = ctrl.obtenerCategoriasMaterial();

            // Actualizar ComboBox
            cmbMarca.removeAllItems();
            cmbMarca.addItem("Seleccione marca:");
            for (Marca m : marcas) {
                cmbMarca.addItem(m.getNombre());
            }

            // Mantener la selección actual si existe
            if (material != null) {
                for (Marca m : marcas) {
                    if (m.getCodigo() == material.getIdMarca()) {
                        cmbMarca.setSelectedItem(m.getNombre());
                        break;
                    }
                }
            }
        });

        dialog.setVisible(true);
    }//GEN-LAST:event_btnMarcaActionPerformed

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
            java.util.logging.Logger.getLogger(materialEditar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(materialEditar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(materialEditar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(materialEditar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                materialEditar dialog = new materialEditar(new javax.swing.JFrame(), true, null);
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
    private RSMaterialComponent.RSButtonShape btnCategoria;
    private rojeru_san.RSButtonRiple btnGuardar;
    private RSMaterialComponent.RSButtonShape btnMarca;
    private RSMaterialComponent.RSButtonShape btnQuitar;
    private RSMaterialComponent.RSButtonShape btnSubirImagen;
    private RSMaterialComponent.RSButtonShape btnUM;
    private RSMaterialComponent.RSComboBoxMaterial cmbCategoria;
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
    private RSMaterialComponent.RSTextFieldMaterial txtStockMinimo;
    // End of variables declaration//GEN-END:variables
}
