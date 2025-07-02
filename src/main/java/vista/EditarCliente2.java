/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package vista;



import controlador.Ctrl_Cliente;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import modelo.Cliente;



/**
 *
 * @author ZenBook
 */

public class EditarCliente2 extends javax.swing.JDialog {
 
    private String[] datos; // Almacena los datos ingresados
    private boolean guardado = false; // Indica si se presionó "Guardar"
    private Cliente cliente; // Almacena el cliente que se está editando
private boolean isEditMode = false; // Indica si está en modo edición
private HashMap<String, String[]> municipiosPorDepartamento = new HashMap<>();
    /**
     * Creates new form nuevoMateriales
     */
public EditarCliente2(java.awt.Frame parent, boolean modal) {
    super(parent, modal);
    initComponents();
    setTitle("Nuevo Cliente");
    agregarValidacion();
    inicializarMunicipios();
    agregarListenerDepartamento();
     tipoidentificacion.setVisible(false);
    numero.setVisible(false);
    nombre.setVisible(false);
    telefono.setVisible(false);
    dirección1.setVisible(false);
    labeldireccion.setVisible(false);
    dirección4.setVisible(false);
    telefono1.setVisible(false);
    dirección3.setVisible(false);
}
    public EditarCliente2(java.awt.Frame parent, boolean modal, Cliente cliente) {
        super(parent, modal);
        this.cliente = cliente;
        this.isEditMode = true;
        initComponents();
        setTitle("Editar Cliente");
        agregarValidacion();
        inicializarMunicipios();
        agregarListenerDepartamento();
        loadClientData(); // Cargar datos del cliente
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
    
    
    
    private void inicializarMunicipios() {
    // Definir municipios por departamento
municipiosPorDepartamento.put("Bogotá DC", new String[]{
    "Seleccione",
    "Antonio Nariño",
    "Barrios Unidos",
    "Bosa",
    "Chapinero",
    "Ciudad Bolívar",
    "Engativá",
    "Fontibón",
    "Kennedy",
    "La Candelaria",
    "Los Mártires",
    "Puente Aranda",
    "Rafael Uribe Uribe",
    "San Cristóbal",
    "Santa Fe",
    "Suba",
    "Sumapaz",
    "Teusaquillo",
    "Tunjuelito",
    "Usaquén",
    "Usme"
});
municipiosPorDepartamento.put("Boyacá", new String[]{
    "Seleccione",
    "Almeida",
    "Aquitania",
    "Arcabuco",
    "Belén",
    "Berbeo",
    "Betéitiva",
    "Boavita",
    "Boyacá",
    "Briceño",
    "Buenavista",
    "Busbanzá",
    "Caldas",
    "Campohermoso",
    "Cerinza",
    "Chinavita",
    "Chiquinquirá",
    "Chíquiza",
    "Chiscas",
    "Chita",
    "Chitaraque",
    "Chivatá",
    "Chivor",
    "Ciénaga",
    "Cómbita",
    "Coper",
    "Corrales",
    "Covarachía",
    "Cubará",
    "Cucaita",
    "Cuítiva",
    "Duitama",
    "El Cocuy",
    "El Espino",
    "Firavitoba",
    "Floresta",
    "Gachantivá",
    "Gámeza",
    "Garagoa",
    "Guacamayas",
    "Guateque",
    "Guayatá",
    "Güicán",
    "Iza",
    "Jenesano",
    "Jericó",
    "Labranzagrande",
    "La Capilla",
    "La Uvita",
    "La Victoria",
    "Macanal",
    "Maripí",
    "Miraflores",
    "Mongua",
    "Monguí",
    "Moniquirá",
    "Motavita",
    "Muzo",
    "Nobsa",
    "Nuevo Colón",
    "Oicatá",
    "Otanche",
    "Pachavita",
    "Paéz",
    "Paipa",
    "Pajarito",
    "Panqueba",
    "Pauna",
    "Paya",
    "Paz de Río",
    "Pesca",
    "Pisba",
    "Puerto Boyacá",
    "Quípama",
    "Ramiriquí",
    "Ráquira",
    "Rondón",
    "Saboyá",
    "Sáchica",
    "Samacá",
    "San Eduardo",
    "San José de Pare",
    "San Luis de Gaceno",
    "San Mateo",
    "San Miguel de Sema",
    "San Pablo de Borbur",
    "Santa María",
    "Santa Rosa de Viterbo",
    "Santa Sofía",
    "Santana",
    "Sativanorte",
    "Sativasur",
    "Siachoque",
    "Soatá",
    "Socha",
    "Socotá",
    "Sogamoso",
    "Somondoco",
    "Sora",
    "Soracá",
    "Sotaquirá",
    "Susacón",
    "Sutamarchán",
    "Sutatenza",
    "Tasco",
    "Tenza",
    "Tibaná",
    "Tibasosa",
    "Tinjacá",
    "Tipacoque",
    "Toca",
    "Togüí",
    "Tópaga",
    "Tota",
    "Tunja",
    "Tununguá",
    "Turmequé",
    "Tuta",
    "Tutazá",
    "Úmbita",
    "Ventaquemada",
    "Villa de Leyva",
    "Viracachá",
    "Zetaquirá"
});
    // Establecer el modelo inicial para gh (municipios)
    gh.setModel(new javax.swing.DefaultComboBoxModel(municipiosPorDepartamento.get("Bogotá DC")));
}
    
    
    private void agregarListenerDepartamento() {
    gh1.addItemListener(new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String departamentoSeleccionado = gh1.getSelectedItem().toString();
                String[] municipios = municipiosPorDepartamento.getOrDefault(departamentoSeleccionado, new String[]{"Seleccione"});
                gh.setModel(new javax.swing.DefaultComboBoxModel(municipios));
            }
        }
    });
    }
 private void agregarValidacion() {
    // Validación para departamento
    gh1.addFocusListener(new FocusAdapter() {
        @Override
        public void focusLost(FocusEvent e) {
            if (gh1.getSelectedIndex() == 0) {
                dirección3.setVisible(true);
                dirección3.setText("Seleccione un departamento válido");
            } else {
                dirección3.setVisible(false);
            }
        }
    });
    gh1.addKeyListener(new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                gh.requestFocusInWindow();
            }
        }
    });
// Validación para estado
gh3.addFocusListener(new FocusAdapter() {
    @Override
    public void focusLost(FocusEvent e) {
        if (gh3.getSelectedIndex() == 0) {
            dirección2.setVisible(true);
            dirección2.setText("Seleccione un estado válido");
        } else {
            dirección2.setVisible(false);
        }
    }
});
gh3.addKeyListener(new KeyAdapter() {
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            btnGuardar.doClick();
        }
        if (gh3.getSelectedIndex() != 0) {
            dirección2.setVisible(false);
        }
    }
});
    // Validación para municipio
    gh.addFocusListener(new FocusAdapter() {
        @Override
        public void focusLost(FocusEvent e) {
            if (gh.getSelectedIndex() == 0) {
                dirección1.setVisible(true);
                dirección1.setText("Seleccione un municipio válido");
            } else {
                dirección1.setVisible(false);
            }
        }
    });
    gh.addKeyListener(new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                direcciontxt2.requestFocusInWindow();
            }
            if (gh.getSelectedIndex() != 0) {
                dirección1.setVisible(false);
            }
        }
    });

    // Validación para identificaciontxt
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

    // Validación para numerotxt
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
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                nombretxt.requestFocusInWindow();
            }
        }
    });

    // Validación para nombretxt
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

    // Validación para apellidotxt (opcional)
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

    // Validación para telefonotxt
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
                telefonotxt1.requestFocusInWindow();
            }
        }
    });

    // Validación para telefonotxt1 (opcional)
    telefonotxt1.addFocusListener(new FocusAdapter() {
        @Override
        public void focusLost(FocusEvent e) {
            if (!telefonotxt1.getText().trim().isEmpty() && !telefonotxt1.getText().trim().matches("\\d{7,15}")) {
                telefono1.setVisible(true);
                telefono1.setText("7-15 dígitos");
            } else {
                telefono1.setVisible(false);
            }
        }
    });
    telefonotxt1.addKeyListener(new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                gh1.requestFocusInWindow();
            }
        }
    });

    // Validación para direcciontxt
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
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                direcciontxt2.requestFocusInWindow();
            }
        }
    });

    // Validación para direcciontxt2 (opcional)
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
        gh = new RSMaterialComponent.RSComboBoxMaterial();
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
        dirección1 = new javax.swing.JLabel();
        labeldireccion = new javax.swing.JLabel();
        direcciontxt2 = new RSMaterialComponent.RSTextFieldMaterial();
        dirección4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        telefonotxt1 = new RSMaterialComponent.RSTextFieldMaterial();
        telefono1 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        dirección3 = new javax.swing.JLabel();
        identificaciontxt = new RSMaterialComponent.RSComboBoxMaterial();
        gh1 = new RSMaterialComponent.RSComboBoxMaterial();
        gh3 = new RSMaterialComponent.RSComboBoxMaterial();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        dirección2 = new javax.swing.JLabel();

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
        jLabel1.setText("Editar Cliente");
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
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel4.setText("Nombre: ");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, -1, 20));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel6.setText("Telefono:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel7.setText("Municipio y/o localidad:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 370, -1, -1));

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
        jPanel1.add(nombretxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 200, 30));

        btnCancelar.setBackground(new java.awt.Color(46, 49, 82));
        btnCancelar.setText("Cancelar");
        btnCancelar.setColorHover(new java.awt.Color(204, 0, 0));
        btnCancelar.setFont(new java.awt.Font("Humnst777 BlkCn BT", 1, 14)); // NOI18N
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 680, 140, -1));

        btnGuardar.setBackground(new java.awt.Color(46, 49, 82));
        btnGuardar.setText("Guardar");
        btnGuardar.setColorHover(new java.awt.Color(0, 153, 51));
        btnGuardar.setFont(new java.awt.Font("Humnst777 BlkCn BT", 1, 14)); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 680, 140, -1));

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
        jPanel1.add(numerotxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 130, 200, 30));

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
        jPanel1.add(telefonotxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, 200, 30));

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
        jPanel1.add(direcciontxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 500, 200, 30));

        gh.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar", " ", " " }));
        gh.setColorMaterial(new java.awt.Color(29, 30, 51));
        gh.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        gh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ghActionPerformed(evt);
            }
        });
        jPanel1.add(gh, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 410, 200, 30));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel10.setText("Apellido:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 190, -1, 20));

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
        jPanel1.add(apellidotxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 220, 200, 30));

        numero.setForeground(new java.awt.Color(255, 51, 51));
        numero.setText("jLabel2");
        jPanel1.add(numero, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 160, 200, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 51, 51));
        jLabel9.setText("*");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 370, 20, -1));

        dirección.setText("ej. Calle 76-#8-24 apto 205");
        jPanel1.add(dirección, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 530, 170, -1));

        telefono.setForeground(new java.awt.Color(255, 51, 51));
        telefono.setText("jLabel2");
        jPanel1.add(telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, 200, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 51, 51));
        jLabel13.setText("*");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 290, 20, 20));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 51, 51));
        jLabel14.setText("*");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, 60, -1));

        tipoidentificacion.setForeground(new java.awt.Color(255, 51, 51));
        tipoidentificacion.setText("TIPO");
        jPanel1.add(tipoidentificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 200, -1));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel17.setText("Numero:");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 100, -1, -1));

        nombre.setForeground(new java.awt.Color(255, 51, 51));
        nombre.setText("jLabel2");
        jPanel1.add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 200, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 51, 51));
        jLabel16.setText("*");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, 20, 20));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 51, 51));
        jLabel18.setText("*");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 100, 10, -1));

        dirección1.setForeground(new java.awt.Color(255, 51, 51));
        dirección1.setText("jLabel2");
        jPanel1.add(dirección1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 440, 200, -1));

        labeldireccion.setForeground(new java.awt.Color(255, 51, 51));
        labeldireccion.setText("jLabel2");
        jPanel1.add(labeldireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 550, 200, -1));

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
        jPanel1.add(direcciontxt2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 500, 200, 30));

        dirección4.setForeground(new java.awt.Color(255, 51, 51));
        dirección4.setText("jLabel2");
        jPanel1.add(dirección4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 530, 200, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel8.setText("Dirección:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 480, -1, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 51, 51));
        jLabel15.setText("*");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 480, 20, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel11.setText("Descripción adicional:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 480, 160, -1));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(204, 204, 204));
        jLabel19.setText("(opcional)");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 190, 70, -1));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(204, 204, 204));
        jLabel20.setText("(opcional)");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 290, 70, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel12.setText("Telefono 2:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 290, -1, -1));

        telefonotxt1.setForeground(new java.awt.Color(0, 0, 0));
        telefonotxt1.setColorMaterial(new java.awt.Color(0, 0, 0));
        telefonotxt1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        telefonotxt1.setPhColor(new java.awt.Color(0, 0, 0));
        telefonotxt1.setPlaceholder("Ingrese el telefono..");
        telefonotxt1.setSelectionColor(new java.awt.Color(0, 0, 0));
        telefonotxt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                telefonotxt1ActionPerformed(evt);
            }
        });
        jPanel1.add(telefonotxt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 310, 200, 30));

        telefono1.setForeground(new java.awt.Color(255, 51, 51));
        telefono1.setText("jLabel2");
        jPanel1.add(telefono1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 340, 200, -1));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(204, 204, 204));
        jLabel22.setText("(opcional)");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 480, 70, -1));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel21.setText("Departamento:");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, -1, -1));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 51, 51));
        jLabel23.setText("*");
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 390, 10, 10));

        dirección3.setForeground(new java.awt.Color(255, 51, 51));
        dirección3.setText("jLabel2");
        jPanel1.add(dirección3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 440, 200, -1));

        identificaciontxt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar", "CC", "TI", "CE", "NIT" }));
        identificaciontxt.setColorMaterial(new java.awt.Color(29, 30, 51));
        identificaciontxt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        identificaciontxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                identificaciontxtActionPerformed(evt);
            }
        });
        jPanel1.add(identificaciontxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 200, 30));

        gh1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar", "Bogotá DC", "Boyacá", " " }));
        gh1.setColorMaterial(new java.awt.Color(29, 30, 51));
        gh1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        gh1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gh1ActionPerformed(evt);
            }
        });
        jPanel1.add(gh1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 410, 200, 30));

        gh3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar", " ", " " }));
        gh3.setColorMaterial(new java.awt.Color(29, 30, 51));
        gh3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        gh3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gh3ActionPerformed(evt);
            }
        });
        jPanel1.add(gh3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 590, 200, 30));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel24.setText("Estado:");
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 570, -1, -1));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 51, 51));
        jLabel25.setText("*");
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 570, 20, -1));

        dirección2.setForeground(new java.awt.Color(255, 51, 51));
        dirección2.setText("jLabel2");
        jPanel1.add(dirección2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 620, 200, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 575, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 736, Short.MAX_VALUE)
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
    String telefono2 = telefonotxt1.getText().trim();
    String departamento = gh1.getSelectedItem().toString().trim();
    String municipio = gh.getSelectedItem().toString().trim();
    String direccion = direcciontxt.getText().trim();
    String descripcionAdicional = direcciontxt2.getText().trim();
    String estado = gh3.getSelectedItem().toString();

    // Validar campos obligatorios
    if (tipoIdentificacion.equals("Seleccionar")) {
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
    if (departamento.isEmpty() || departamento.equals("Seleccionar")) {
        errores.append("Seleccione un departamento válido.\n");
    }
    if (municipio.isEmpty() || municipio.equals("Seleccione")) {
        errores.append("Seleccione un municipio válido.\n");
    }
    if (direccion.isEmpty()) {
        errores.append("La dirección es obligatoria.\n");
    }
    if (estado.equals("Seleccionar")) {
        errores.append("Seleccione un estado válido.\n");
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
    if (!telefono2.isEmpty() && !telefono2.matches("\\d{7,15}")) {
        errores.append("El segundo teléfono debe contener 7-15 dígitos.\n");
    }

    // Mostrar errores si los hay
    if (errores.length() > 0) {
        JOptionPane.showMessageDialog(this, errores.toString(), "Errores de Validación", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Crear objeto Cliente
    Cliente cliente = new Cliente();
    cliente.setIdentificacion(tipoIdentificacion);
    cliente.setId_cliente(numero);
    cliente.setNombre(nombre);
    cliente.setApellido(apellido.isEmpty() ? null : apellido);
    cliente.setTelefono(telefono);
    cliente.setTelefono2(telefono2.isEmpty() ? null : telefono2);
    cliente.setDepartamento(departamento.isEmpty() ? null : departamento);
    cliente.setMunicipio(municipio.isEmpty() ? null : municipio);
    cliente.setDireccion(direccion);
    cliente.setActivo(estado.equals("Activo"));

    boolean success;
    String message;
    if (isEditMode) {
        // Actualizar cliente existente
        success = controlador.editar(cliente, numero);
        message = success ? "Cliente actualizado correctamente" : "Error al actualizar el cliente";
    } else {
        // Crear nuevo cliente
        success = controlador.guardar(cliente);
        message = success ? "Cliente guardado correctamente" : "Error al guardar el cliente";
    }

    if (success) {
        JOptionPane.showMessageDialog(this, message, "Éxito", JOptionPane.INFORMATION_MESSAGE);
        guardado = true;
        datos = new String[]{tipoIdentificacion, String.valueOf(numero), nombre, apellido, telefono, telefono2, departamento, municipio, direccion, descripcionAdicional};
        if (listener != null) {
            listener.onClienteGuardado();
        }
        setVisible(false);
    } else {
        JOptionPane.showMessageDialog(this, message + " Detalle: " + (controlador.getLastError() != null ? controlador.getLastError() : "Sin detalles"), "Error", JOptionPane.ERROR_MESSAGE);
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

    private void ghActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ghActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ghActionPerformed

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

    private void telefonotxt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_telefonotxt1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_telefonotxt1ActionPerformed

    private void identificaciontxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_identificaciontxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_identificaciontxtActionPerformed

    private void gh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gh1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gh1ActionPerformed

    private void gh3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gh3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gh3ActionPerformed

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
            java.util.logging.Logger.getLogger(EditarCliente2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditarCliente2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditarCliente2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditarCliente2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                EditarCliente2 dialog = new EditarCliente2(new javax.swing.JFrame(), true);
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
    private RSMaterialComponent.RSTextFieldMaterial direcciontxt2;
    private javax.swing.JLabel dirección;
    private javax.swing.JLabel dirección1;
    private javax.swing.JLabel dirección2;
    private javax.swing.JLabel dirección3;
    private javax.swing.JLabel dirección4;
    private RSMaterialComponent.RSComboBoxMaterial gh;
    private RSMaterialComponent.RSComboBoxMaterial gh1;
    private RSMaterialComponent.RSComboBoxMaterial gh3;
    private RSMaterialComponent.RSComboBoxMaterial identificaciontxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel labeldireccion;
    private javax.swing.JLabel nombre;
    private RSMaterialComponent.RSTextFieldMaterial nombretxt;
    private javax.swing.JLabel numero;
    private RSMaterialComponent.RSTextFieldMaterial numerotxt;
    private javax.swing.JLabel telefono;
    private javax.swing.JLabel telefono1;
    private RSMaterialComponent.RSTextFieldMaterial telefonotxt;
    private RSMaterialComponent.RSTextFieldMaterial telefonotxt1;
    private javax.swing.JLabel tipoidentificacion;
    // End of variables declaration//GEN-END:variables
private void loadClientData() {
    if (cliente != null) {
        System.out.println("Cargando datos - ID: " + cliente.getId_cliente() + ", Nombre: " + cliente.getNombre() + 
                          ", Departamento: '" + cliente.getDepartamento() + "', Municipio: '" + cliente.getMunicipio() + 
                          "', Dirección: '" + cliente.getDireccion() + "', Estado: " + (cliente.isActivo() ? "Activo" : "Inactivo"));

        identificaciontxt.setSelectedItem(cliente.getIdentificacion() != null ? cliente.getIdentificacion() : "Seleccionar");
        numerotxt.setText(String.valueOf(cliente.getId_cliente()));
        nombretxt.setText(cliente.getNombre() != null ? cliente.getNombre() : "");
        apellidotxt.setText(cliente.getApellido() != null ? cliente.getApellido() : "");
        telefonotxt.setText(cliente.getTelefono() != null ? cliente.getTelefono() : "");
        telefonotxt1.setText(cliente.getTelefono2() != null ? cliente.getTelefono2() : "");

        String departamento = cliente.getDepartamento() != null && !cliente.getDepartamento().isEmpty() ? 
                             cliente.getDepartamento() : "Bogotá DC";
        System.out.println("Seleccionando departamento: '" + departamento + "'");
        gh1.setSelectedItem(departamento);
        String[] municipios = municipiosPorDepartamento.getOrDefault(departamento, new String[]{"Seleccione"});
        if (municipios.length == 1 && municipios[0].equals("Seleccione")) {
            municipios = new String[]{"Usaquén", "Chapinero"};
        }
        System.out.println("Municipios disponibles: " + String.join(", ", municipios));
        gh.setModel(new javax.swing.DefaultComboBoxModel(municipios));

        String municipio = cliente.getMunicipio() != null && !cliente.getMunicipio().isEmpty() ? 
                          cliente.getMunicipio() : "Usaquén";
        System.out.println("Seleccionando municipio: '" + municipio + "'");
        gh.setSelectedItem(municipio);

        String direccion = cliente.getDireccion() != null ? cliente.getDireccion() : "";
        System.out.println("Cargando dirección: '" + direccion + "'");
        direcciontxt.setText(direccion);

        // Cargar estado
        gh3.setSelectedItem(cliente.isActivo() ? "Activo" : "Inactivo");

        SwingUtilities.invokeLater(() -> {
            gh1.repaint();
            gh.repaint();
            direcciontxt.repaint();
            gh3.repaint();
        });
    } else {
        System.out.println("Cliente es null, no se cargan datos.");
    }
}
}
