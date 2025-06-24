/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package vista;



import controlador.Ctrl_Cliente;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;



/**
 *
 * @author ZenBook
 */

public class crear_cliente extends javax.swing.JDialog {
 
    private String[] datos; // Almacena los datos ingresados
    private boolean guardado = false; // Indica si se presionó "Guardar"

    /**
     * Creates new form nuevoMateriales
     */
    public crear_cliente(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setTitle("Nuevo Material");
        agregarValidacion();
        tipoidentificacion.setVisible(false);
    numero.setVisible(false);
    nombre.setVisible(false);
    telefono.setVisible(false);
    dirección.setVisible(false);
    dirección1.setVisible(false); // Municipio (optional)
    dirección2.setVisible(false); // Descripción adicional (optional)
    dirección4.setVisible(false); // Unused
    }
    
        public String[] getDatos() {
        return datos;
    }

    // Método para verificar si se presionó "Guardar"
    public boolean isGuardado() {
        return guardado;
    }
    
    // Interfaz para el callback
    public interface ClienteGuardadoListener {

        void onClienteGuardado();
    }

    private ClienteGuardadoListener listener;

    // Método para asignar el listener
    public void setClienteGuardadoListener(ClienteGuardadoListener listener) {
        this.listener = listener;
    }
    
    
 private void agregarValidacion() {
        // Validación y navegación para identificaciontxt
        identificaciontxt.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (identificaciontxt.getSelectedIndex() == 0) {
                    tipoidentificacion.setVisible(true);
                    tipoidentificacion.setText("Seleccione un tipo válido");
                } else {
                    tipoidentificacion.setVisible(false);
                }
            }
        });
        identificaciontxt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    numerotxt.requestFocusInWindow();
                }
                if (identificaciontxt.getSelectedIndex() != 0) {
                    tipoidentificacion.setVisible(false);
                }
            }
        });

        // Validación y navegación para numerotxt
        numerotxt.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (numerotxt.getText().trim().isEmpty()) {
                    numero.setVisible(true);
                    numero.setText("Campo obligatorio");
                } else {
                    try {
                        Integer.parseInt(numerotxt.getText().trim());
                        numero.setVisible(false);
                    } catch (NumberFormatException ex) {
                        numero.setVisible(true);
                        numero.setText("Solo números");
                    }
                }
            }
        });
        numerotxt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!numerotxt.getText().trim().isEmpty()) {
                    try {
                        Integer.parseInt(numerotxt.getText().trim() + e.getKeyChar());
                        numero.setVisible(false);
                    } catch (NumberFormatException ex) {
                        numero.setVisible(true);
                        numero.setText("Solo números");
                    }
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    nombretxt.requestFocusInWindow();
                }
            }
        });

        // Validación y navegación para nombretxt
        nombretxt.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (nombretxt.getText().trim().isEmpty()) {
                    nombre.setVisible(true);
                    nombre.setText("Campo obligatorio");
                } else {
                    nombre.setVisible(false);
                }
            }
        });
        nombretxt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!nombretxt.getText().trim().isEmpty()) {
                    nombre.setVisible(false);
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    apellidotxt.requestFocusInWindow();
                }
            }
        });

        // Validación y navegación para apellidotxt (opcional)
        apellidotxt.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                // Apellido es opcional
            }
        });
        apellidotxt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    telefonotxt.requestFocusInWindow();
                }
            }
        });

        // Validación y navegación para telefonotxt
        telefonotxt.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (telefonotxt.getText().trim().isEmpty()) {
                    telefono.setVisible(true);
                    telefono.setText("Campo obligatorio");
                } else if (!telefonotxt.getText().trim().matches("\\d{7,15}")) {
                    telefono.setVisible(true);
                    telefono.setText("7-15 dígitos");
                } else {
                    telefono.setVisible(false);
                }
            }
        });
        telefonotxt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!telefonotxt.getText().trim().isEmpty()) {
                    telefono.setVisible(false);
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    direcciontxt.requestFocusInWindow();
                }
            }
        });

        // Validación y navegación para direcciontxt (requerido)
        direcciontxt.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (direcciontxt.getText().trim().isEmpty()) {
                    dirección.setVisible(true);
                    dirección.setText("Campo obligatorio");
                } else {
                    dirección.setVisible(false);
                }
            }
        });
        direcciontxt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!direcciontxt.getText().trim().isEmpty()) {
                    dirección.setVisible(false);
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    direcciontxt1.requestFocusInWindow();
                }
            }
        });

        // Validación y navegación para direcciontxt1 (municipio, opcional)
        direcciontxt1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                // Municipio es opcional
            }
        });
        direcciontxt1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    direcciontxt2.requestFocusInWindow();
                }
            }
        });

        // Validación y navegación para direcciontxt2 (descripción adicional, opcional)
        direcciontxt2.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                // Descripción adicional es opcional
            }
        });
        direcciontxt2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnGuardar.doClick();
                }
            }
        });
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGuardar2 = new rojeru_san.RSButtonRiple();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnGuardar3 = new rojeru_san.RSButtonRiple();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        nombretxt = new RSMaterialComponent.RSTextFieldMaterial();
        btnCancelar = new rojeru_san.RSButtonRiple();
        btnGuardar = new rojeru_san.RSButtonRiple();
        numerotxt = new RSMaterialComponent.RSTextFieldMaterial();
        telefonotxt = new RSMaterialComponent.RSTextFieldMaterial();
        direcciontxt = new RSMaterialComponent.RSTextFieldMaterial();
        identificaciontxt = new RSMaterialComponent.RSComboBoxMaterial();
        jLabel10 = new javax.swing.JLabel();
        apellidotxt = new RSMaterialComponent.RSTextFieldMaterial();
        numero = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        dirección = new javax.swing.JLabel();
        telefono = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        tipoidentificacion = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        nombre = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        direcciontxt1 = new RSMaterialComponent.RSTextFieldMaterial();
        dirección1 = new javax.swing.JLabel();
        dirección2 = new javax.swing.JLabel();
        direcciontxt2 = new RSMaterialComponent.RSTextFieldMaterial();
        dirección4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();

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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(46, 49, 82));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Century751 BT", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Nuevo Cliente");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        btnGuardar3.setBackground(new java.awt.Color(46, 49, 82));
        btnGuardar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/x.png"))); // NOI18N
        btnGuardar3.setColorHover(new java.awt.Color(204, 0, 0));
        btnGuardar3.setFont(new java.awt.Font("Humnst777 BlkCn BT", 1, 18)); // NOI18N
        btnGuardar3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar3ActionPerformed(evt);
            }
        });
        jPanel2.add(btnGuardar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 0, 40, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 580, 70));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel3.setText("Tipo de identificación:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel4.setText("Nombre: ");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, -1, 20));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel6.setText("Telefono:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel7.setText("Municipio:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 290, -1, -1));

        nombretxt.setForeground(new java.awt.Color(0, 0, 0));
        nombretxt.setColorMaterial(new java.awt.Color(0, 0, 0));
        nombretxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nombretxt.setPhColor(new java.awt.Color(0, 0, 0));
        nombretxt.setPlaceholder("Ingrese el nombre...");
        nombretxt.setSelectionColor(new java.awt.Color(0, 0, 0));
        nombretxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombretxtActionPerformed(evt);
            }
        });
        jPanel1.add(nombretxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 200, 30));

        btnCancelar.setBackground(new java.awt.Color(46, 49, 82));
        btnCancelar.setText("Cancelar");
        btnCancelar.setColorHover(new java.awt.Color(204, 0, 0));
        btnCancelar.setFont(new java.awt.Font("Humnst777 BlkCn BT", 1, 14)); // NOI18N
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 550, 140, -1));

        btnGuardar.setBackground(new java.awt.Color(46, 49, 82));
        btnGuardar.setText("Guardar");
        btnGuardar.setColorHover(new java.awt.Color(0, 153, 51));
        btnGuardar.setFont(new java.awt.Font("Humnst777 BlkCn BT", 1, 14)); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 550, 140, -1));

        numerotxt.setForeground(new java.awt.Color(0, 0, 0));
        numerotxt.setColorMaterial(new java.awt.Color(0, 0, 0));
        numerotxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        numerotxt.setPhColor(new java.awt.Color(0, 0, 0));
        numerotxt.setPlaceholder("Ingrese el número");
        numerotxt.setSelectionColor(new java.awt.Color(0, 0, 0));
        numerotxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numerotxtActionPerformed(evt);
            }
        });
        jPanel1.add(numerotxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 130, 200, 30));

        telefonotxt.setForeground(new java.awt.Color(0, 0, 0));
        telefonotxt.setColorMaterial(new java.awt.Color(0, 0, 0));
        telefonotxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        telefonotxt.setPhColor(new java.awt.Color(0, 0, 0));
        telefonotxt.setPlaceholder("Ingrese el telefono..");
        telefonotxt.setSelectionColor(new java.awt.Color(0, 0, 0));
        telefonotxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                telefonotxtActionPerformed(evt);
            }
        });
        jPanel1.add(telefonotxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 200, 30));

        direcciontxt.setForeground(new java.awt.Color(0, 0, 0));
        direcciontxt.setColorMaterial(new java.awt.Color(0, 0, 0));
        direcciontxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        direcciontxt.setPhColor(new java.awt.Color(0, 0, 0));
        direcciontxt.setPlaceholder("Ingrese la dirección...");
        direcciontxt.setSelectionColor(new java.awt.Color(0, 0, 0));
        direcciontxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                direcciontxtActionPerformed(evt);
            }
        });
        jPanel1.add(direcciontxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, 200, 30));

        identificaciontxt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "tipo de identificación", "CC", "TI", "CE", "NIT" }));
        identificaciontxt.setColorMaterial(new java.awt.Color(29, 30, 51));
        identificaciontxt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        identificaciontxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                identificaciontxtActionPerformed(evt);
            }
        });
        jPanel1.add(identificaciontxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 200, 30));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel10.setText("Apellido:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 190, -1, 20));

        apellidotxt.setForeground(new java.awt.Color(0, 0, 0));
        apellidotxt.setColorMaterial(new java.awt.Color(0, 0, 0));
        apellidotxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        apellidotxt.setPhColor(new java.awt.Color(0, 0, 0));
        apellidotxt.setPlaceholder("Ingrese el apellido");
        apellidotxt.setSelectionColor(new java.awt.Color(0, 0, 0));
        apellidotxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apellidotxtActionPerformed(evt);
            }
        });
        jPanel1.add(apellidotxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 220, 200, 30));

        numero.setForeground(new java.awt.Color(255, 51, 51));
        numero.setText("jLabel2");
        jPanel1.add(numero, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 160, 200, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 51, 51));
        jLabel9.setText("*");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 290, 20, -1));

        dirección.setText("ej. Calle 76-#8-24 apto 205");
        jPanel1.add(dirección, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, 170, -1));

        telefono.setForeground(new java.awt.Color(255, 51, 51));
        telefono.setText("jLabel2");
        jPanel1.add(telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 200, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 51, 51));
        jLabel13.setText("*");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 290, 10, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 51, 51));
        jLabel14.setText("*");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, 60, -1));

        tipoidentificacion.setForeground(new java.awt.Color(255, 51, 51));
        tipoidentificacion.setText("TIPO");
        jPanel1.add(tipoidentificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 200, -1));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel17.setText("Numero:");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, -1, -1));

        nombre.setForeground(new java.awt.Color(255, 51, 51));
        nombre.setText("jLabel2");
        jPanel1.add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 200, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 51, 51));
        jLabel16.setText("*");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 190, 20, -1));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 51, 51));
        jLabel18.setText("*");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 100, 10, -1));

        direcciontxt1.setForeground(new java.awt.Color(0, 0, 0));
        direcciontxt1.setColorMaterial(new java.awt.Color(0, 0, 0));
        direcciontxt1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        direcciontxt1.setPhColor(new java.awt.Color(0, 0, 0));
        direcciontxt1.setPlaceholder("Ingrese la dirección...");
        direcciontxt1.setSelectionColor(new java.awt.Color(0, 0, 0));
        direcciontxt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                direcciontxt1ActionPerformed(evt);
            }
        });
        jPanel1.add(direcciontxt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 310, 200, 30));

        dirección1.setForeground(new java.awt.Color(255, 51, 51));
        dirección1.setText("jLabel2");
        jPanel1.add(dirección1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 340, 200, -1));

        dirección2.setForeground(new java.awt.Color(255, 51, 51));
        dirección2.setText("jLabel2");
        jPanel1.add(dirección2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 460, 200, -1));

        direcciontxt2.setForeground(new java.awt.Color(0, 0, 0));
        direcciontxt2.setColorMaterial(new java.awt.Color(0, 0, 0));
        direcciontxt2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        direcciontxt2.setPhColor(new java.awt.Color(0, 0, 0));
        direcciontxt2.setPlaceholder("Piso/fachada/edificio,otro");
        direcciontxt2.setSelectionColor(new java.awt.Color(0, 0, 0));
        direcciontxt2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                direcciontxt2ActionPerformed(evt);
            }
        });
        jPanel1.add(direcciontxt2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 410, 200, 30));

        dirección4.setForeground(new java.awt.Color(255, 51, 51));
        dirección4.setText("jLabel2");
        jPanel1.add(dirección4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 440, 200, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel8.setText("Dirección:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, -1, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 51, 51));
        jLabel15.setText("*");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 390, 20, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel11.setText("Descripción adicional:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 390, 160, -1));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(204, 204, 204));
        jLabel19.setText("(opcional)");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 190, 70, -1));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(204, 204, 204));
        jLabel20.setText("(opcional)");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 390, 70, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 568, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nombretxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombretxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombretxtActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
guardado = false;
        setVisible(false); // Cerrar el diálogo
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
  Ctrl_Cliente controlador = new Ctrl_Cliente();
        StringBuilder errores = new StringBuilder();

        // Obtener y limpiar datos
        String tipoIdentificacion = identificaciontxt.getSelectedItem().toString();
        String numeroStr = numerotxt.getText().trim();
        String nombre = nombretxt.getText().trim();
        String apellido = apellidotxt.getText().trim();
        String telefono = telefonotxt.getText().trim();
        String direccion = direcciontxt.getText().trim();
        String municipio = direcciontxt1.getText().trim();
        String descripcionAdicional = direcciontxt2.getText().trim();

        // Validar campos obligatorios
        if (tipoIdentificacion.equals("tipo de identificación")) {
            errores.append("Seleccione un tipo de identificación válido.\n");
        }
        if (numeroStr.isEmpty()) {
            errores.append("El número de identificación es obligatorio.\n");
        }
        if (nombre.isEmpty()) {
            errores.append("El nombre es obligatorio.\n");
        }
        if (telefono.isEmpty()) {
            errores.append("El teléfono es obligatorio.\n");
        }
        if (direccion.isEmpty()) {
            errores.append("La dirección es obligatoria.\n");
        }

        // Validar formato del número
        int numero = 0;
        try {
            numero = Integer.parseInt(numeroStr);
        } catch (NumberFormatException e) {
            errores.append("El número de identificación debe ser numérico.\n");
        }

        // Validar formato del teléfono
        if (!telefono.matches("\\d{7,15}")) {
            errores.append("El teléfono debe contener 7-15 dígitos.\n");
        }

        // Mostrar errores si los hay
        if (errores.length() > 0) {
            JOptionPane.showMessageDialog(this, errores.toString(), "Errores de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Combinar dirección, municipio y descripción adicional
        String direccionCompleta = direccion;
        if (!municipio.isEmpty()) {
            direccionCompleta += ", " + municipio;
        }
        if (!descripcionAdicional.isEmpty()) {
            direccionCompleta += ", " + descripcionAdicional;
        }

        // Crear objeto Cliente
        modelo.Cliente cliente = new modelo.Cliente();
        cliente.setIdentificacion(tipoIdentificacion);
        cliente.setNumero(numero);
        cliente.setNombre(nombre);
        cliente.setApellido(apellido.isEmpty() ? null : apellido);
        cliente.setTelefono(telefono);
        cliente.setDireccion(direccionCompleta);

        // Guardar cliente
        if (controlador.guardar(cliente)) {
            JOptionPane.showMessageDialog(this, "Cliente guardado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            guardado = true;
            datos = new String[]{tipoIdentificacion, String.valueOf(numero), nombre, apellido, telefono, direccionCompleta};
            if (listener != null) {
                listener.onClienteGuardado();
            }
            setVisible(false);
        } else {
            JOptionPane.showMessageDialog(this, "Error al guardar el cliente", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void numerotxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numerotxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numerotxtActionPerformed

    private void telefonotxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_telefonotxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_telefonotxtActionPerformed

    private void apellidotxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apellidotxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_apellidotxtActionPerformed

    private void identificaciontxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_identificaciontxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_identificaciontxtActionPerformed

    private void direcciontxt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_direcciontxt1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_direcciontxt1ActionPerformed

    private void direcciontxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_direcciontxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_direcciontxtActionPerformed

    private void direcciontxt2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_direcciontxt2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_direcciontxt2ActionPerformed

    private void btnGuardar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnGuardar2ActionPerformed

    private void btnGuardar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar3ActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnGuardar3ActionPerformed

    /**
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
            java.util.logging.Logger.getLogger(crear_cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(crear_cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(crear_cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(crear_cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                crear_cliente dialog = new crear_cliente(new javax.swing.JFrame(), true);
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
    private RSMaterialComponent.RSTextFieldMaterial apellidotxt;
    private rojeru_san.RSButtonRiple btnCancelar;
    private rojeru_san.RSButtonRiple btnGuardar;
    private rojeru_san.RSButtonRiple btnGuardar2;
    private rojeru_san.RSButtonRiple btnGuardar3;
    private RSMaterialComponent.RSTextFieldMaterial direcciontxt;
    private RSMaterialComponent.RSTextFieldMaterial direcciontxt1;
    private RSMaterialComponent.RSTextFieldMaterial direcciontxt2;
    private javax.swing.JLabel dirección;
    private javax.swing.JLabel dirección1;
    private javax.swing.JLabel dirección2;
    private javax.swing.JLabel dirección4;
    private RSMaterialComponent.RSComboBoxMaterial identificaciontxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel nombre;
    private RSMaterialComponent.RSTextFieldMaterial nombretxt;
    private javax.swing.JLabel numero;
    private RSMaterialComponent.RSTextFieldMaterial numerotxt;
    private javax.swing.JLabel telefono;
    private RSMaterialComponent.RSTextFieldMaterial telefonotxt;
    private javax.swing.JLabel tipoidentificacion;
    // End of variables declaration//GEN-END:variables
}
