package vista;

import controlador.Ctrl_Perfil;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import modelo.UsuarioModelo;




public class perfil1 extends javax.swing.JPanel {
    private Ctrl_Perfil controlador;
    private int idUsuario;
    private byte[] imagenSeleccionada;
    private Principal principal;
    private rojerusan.RSPanelCircleImage avatarPequeño; // Añade esto como variable de instancia
public perfil1(int idUsuario) {
    this.idUsuario = idUsuario;
    controlador = new Ctrl_Perfil();
    initComponents();
    avatarPequeño = new rojerusan.RSPanelCircleImage(); // Ajusta según tu diseño
    numeroidentidad.setEditable(false); // Hacer numeroidentidad no editable
    tipodocumento.setEditable(false); // Hacer tipodocumento no editable
    cargarPerfil();
    cargarUsuarioLogueado();
    cargarrol();
    aplicarTema();
    TemaManager.getInstance().addThemeChangeListener(this::aplicarTema);

    // Conectar el botón de selección de imagen
    rSLabelIcon5.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            seleccionarImagen();
        }
    });
}
private void guardarUsuario() {
    UsuarioModelo usuario = new UsuarioModelo();
    usuario.setId_usuario(idUsuario);
    usuario.setNombre(txtNombre.getText());
    usuario.setApellido(txtapellido.getText());
    usuario.setUsuario(txtusuario.getText());
    usuario.setTipodeiden(controlador.obtenerUsuario(idUsuario).getTipodeiden()); // Mantener el valor existente
    usuario.setCorreo_electronico(txtCorreo.getText());
    usuario.setTelefono(txtTelefono.getText());
    usuario.setRol(txtrol.getText());
    usuario.setImagen(imagenSeleccionada);

    // Obtener el usuario existente para preservar la contraseña
    UsuarioModelo usuarioExistente = controlador.obtenerUsuario(idUsuario);
    if (usuarioExistente != null) {
        usuario.setContrasena(usuarioExistente.getContrasena()); // Preservar la contraseña existente
    } else {
        JOptionPane.showMessageDialog(this, "Error: No se encontró el usuario existente.");
        return;
    }

    // Validar campos editables
    if (usuario.getNombre().isEmpty() || usuario.getApellido().isEmpty() || usuario.getUsuario().isEmpty() ||
        usuario.getCorreo_electronico().isEmpty() || usuario.getTelefono().isEmpty() || 
        usuario.getRol().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
        return;
    }

    // Validar formato de correo y teléfono
    if (!txtCorreo.getText().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
        JOptionPane.showMessageDialog(this, "Por favor, ingrese un correo electrónico válido.");
        return;
    }
    if (!txtTelefono.getText().matches("\\d{7,15}")) {
        JOptionPane.showMessageDialog(this, "Por favor, ingrese un número de teléfono válido (7-15 dígitos).");
        return;
    }

    // Validar existencia de usuario
    if (controlador.existeUsuario(usuario.getUsuario())) {
        if (!usuarioExistente.getUsuario().equals(usuario.getUsuario())) {
            JOptionPane.showMessageDialog(this, "El usuario ya existe. Por favor, elija otro nombre de usuario.");
            return;
        }
    }

    // Actualizar usuario sin modificar la contraseña
    if (controlador.editar(usuario, idUsuario)) {
        JOptionPane.showMessageDialog(this, "Perfil actualizado exitosamente.");
        imagenSeleccionada = null; // Limpiar la imagen seleccionada tras guardar
    } else {
        JOptionPane.showMessageDialog(this, "Error al actualizar el perfil.");
    }
}

  public void cargarPerfil() {
    UsuarioModelo usuario = controlador.obtenerUsuario(idUsuario);
    if (usuario.getId_usuario() != 0) {
        txtNombre.setText(usuario.getNombre());
        txtapellido.setText(usuario.getApellido());
        txtusuario.setText(usuario.getUsuario());
        tipodocumento.setText(usuario.getTipodeiden()); // No editable
        numeroidentidad.setText(String.valueOf(usuario.getId_usuario())); // No editable
        txtCorreo.setText(usuario.getCorreo_electronico());
        txtTelefono.setText(usuario.getTelefono());
        txtrol.setText(usuario.getRol());

        // Cargar la imagen si existe, sino usar la imagen dinámica
        if (usuario.getImagen() != null && usuario.getImagen().length > 0) {
            ImageIcon icon = new ImageIcon(usuario.getImagen());
            Image img = icon.getImage().getScaledInstance(rSPanelCircleImage1.getWidth(), 
                                                        rSPanelCircleImage1.getHeight(), 
                                                        Image.SCALE_SMOOTH);
            rSPanelCircleImage1.setImagen(new ImageIcon(img));
        } else {
            cargarImagenDinamica(); // Usa la imagen dinámica como predeterminada
        }
    } else {
        JOptionPane.showMessageDialog(this, "No se encontró el usuario con ID: " + idUsuario);
    }

}

private void seleccionarImagen() {
    JFileChooser fileChooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Imágenes", "jpg", "png", "jpeg");
    fileChooser.setFileFilter(filter);

    int result = fileChooser.showOpenDialog(this);
    if (result == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fileChooser.getSelectedFile();
        try {
            // Validar tamaño del archivo (ejemplo: máximo 5MB)
            if (selectedFile.length() > 5 * 1024 * 1024) {
                JOptionPane.showMessageDialog(this, "La imagen es demasiado grande. Seleccione una imagen de menos de 5MB.");
                return;
            }

            // Leer la imagen como bytes
            FileInputStream fis = new FileInputStream(selectedFile);
            imagenSeleccionada = fis.readAllBytes();
            fis.close();

            // Mostrar la imagen en el componente rSPanelCircleImage1
            ImageIcon icon = new ImageIcon(imagenSeleccionada);
            Image img = icon.getImage().getScaledInstance(rSPanelCircleImage1.getWidth(), 
                                                        rSPanelCircleImage1.getHeight(), 
                                                        Image.SCALE_SMOOTH);
            rSPanelCircleImage1.setImagen(new ImageIcon(img));

            // Actualizar el avatar pequeño (ajústalo según el nombre real del componente)
            if (avatarPequeño != null) { // Suponiendo que 'avatarPequeño' es el componente del circulito pequeño
                Image imgPequeña = icon.getImage().getScaledInstance(avatarPequeño.getWidth(), 
                                                                   avatarPequeño.getHeight(), 
                                                                   Image.SCALE_SMOOTH);
                avatarPequeño.setImagen(new ImageIcon(imgPequeña));
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar la imagen: " + e.getMessage());
        }
    }
}


public void aplicarTema() {
        boolean oscuro = TemaManager.getInstance().isOscuro();

        if (oscuro) {
            Color fondo = new Color(21,21,33);
            Color primario = new Color(40, 60, 150);
            Color texto = Color.WHITE;
            panelPrincipal.setBackground(fondo);
            jLabel5.setForeground(texto);
            lblTituloPrincipal.setForeground(texto);
            lblTituloPrincipal3.setForeground(texto);
            lblTituloPrincipal1.setForeground(texto);
            rSPanelsSlider1.setBackground( new Color(30,30,45));
            btnGuardar.setBackground( new Color(67,94,190));
            jLabel7.setForeground(texto);
           jLabel13.setForeground(texto);
           jLabel8.setForeground(texto);
            jLabel12.setForeground(texto);
                   jLabel14.setForeground(texto);
               jLabel10.setForeground(texto);
               rSLabelIcon5.setForeground( new Color(255,255,255));
                  rSButtonRound1.setBackground(new Color(30,30,45));
            rSButtonRound1.setColorHover(new Color(30,30,45));
            txtNombre.setBackground(new Color(21,21,33));
            txtapellido.setBackground(new Color(21,21,33));
            txtusuario.setBackground(new Color(21,21,33));
            tipodocumento.setBackground(new Color(21,21,33));
            txtCorreo.setBackground(new Color(21,21,33));
            txtTelefono.setBackground(new Color(21,21,33));
            txtrol.setBackground(new Color(21,21,33));
     txtNombre.setPhColor(texto);
     txtapellido.setPhColor(texto);
          txtusuario.setPhColor(texto); 
             tipodocumento.setPhColor(texto);
                     txtCorreo.setPhColor(texto);
                     txtTelefono.setPhColor(texto);
                             txtrol.setPhColor(texto);
         
          
        } else {
            Color fondo = new Color(242, 247, 255);
            Color texto = Color.BLACK;
            Color primario = new Color(72, 92, 188);
 panelPrincipal.setBackground(fondo);
            jLabel5.setForeground(texto);
            lblTituloPrincipal.setForeground(texto);
            lblTituloPrincipal3.setForeground(texto);
            lblTituloPrincipal1.setForeground(texto);
            rSLabelIcon5.setForeground(new Color(0,0,0));
            rSPanelsSlider1.setBackground( new Color(255,255,255));
            btnGuardar.setBackground( new Color(67,94,190));
            jLabel7.setForeground(texto);
           jLabel13.setForeground(texto);
           jLabel8.setForeground(texto);
            jLabel12.setForeground(texto);
                   jLabel14.setForeground(texto);
            jLabel10.setForeground(texto);
            rSButtonRound1.setBackground(new Color(255, 255, 255));
            rSButtonRound1.setColorHover(new Color(255, 255, 255));
            txtNombre.setBackground(new Color(255, 255, 255));
            txtapellido.setBackground(new Color(255, 255, 255));
            txtusuario.setBackground(new Color(255, 255, 255));
            tipodocumento.setBackground(new Color(255, 255, 255));
            txtCorreo.setBackground(new Color(255, 255, 255));
            txtTelefono.setBackground(new Color(255, 255, 255));
            txtrol.setBackground(new Color(255, 255, 255));
     txtNombre.setPhColor(texto);
     txtapellido.setPhColor(texto);
          txtusuario.setPhColor(texto); 
             tipodocumento.setPhColor(texto);
                     txtCorreo.setPhColor(texto);
                     txtTelefono.setPhColor(texto);
                             txtrol.setPhColor(texto);
                             
                     
        }
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        tipodocumento = new rojerusan.RSMetroTextFullPlaceHolder();
        txtCorreo = new rojerusan.RSMetroTextFullPlaceHolder();
        txtapellido = new rojerusan.RSMetroTextFullPlaceHolder();
        txtrol = new rojerusan.RSMetroTextFullPlaceHolder();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnGuardar = new rojeru_san.RSButtonRiple();
        txtTelefono = new rojerusan.RSMetroTextFullPlaceHolder();
        txtusuario = new rojerusan.RSMetroTextFullPlaceHolder();
        txtNombre = new rojerusan.RSMetroTextFullPlaceHolder();
        numeroidentidad = new rojerusan.RSMetroTextFullPlaceHolder();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        rSPanelsSlider1 = new rojerusan.RSPanelsSlider();
        rSLabelIcon5 = new rojerusan.RSLabelIcon();
        rSPanelCircleImage1 = new rojerusan.RSPanelCircleImage();
        lblTituloPrincipal = new javax.swing.JLabel();
        lblTituloPrincipal3 = new javax.swing.JLabel();
        rSButtonRound1 = new rojerusan.RSButtonRound();
        jLabel15 = new javax.swing.JLabel();
        lblTituloPrincipal1 = new javax.swing.JLabel();
        lblTituloPrincipal2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(21, 21, 33));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelPrincipal.setBackground(new java.awt.Color(21, 21, 33));
        panelPrincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Rol");
        panelPrincipal.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 490, 30, 20));

        tipodocumento.setBackground(new java.awt.Color(21, 21, 33));
        tipodocumento.setForeground(new java.awt.Color(255, 255, 255));
        tipodocumento.setBorderColor(new java.awt.Color(204, 204, 204));
        tipodocumento.setBotonColor(new java.awt.Color(204, 204, 204));
        tipodocumento.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        tipodocumento.setPhColor(new java.awt.Color(204, 204, 204));
        tipodocumento.setPlaceholder("TIPO");
        tipodocumento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipodocumentoActionPerformed(evt);
            }
        });
        panelPrincipal.add(tipodocumento, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 300, 40, 40));

        txtCorreo.setBackground(new java.awt.Color(21, 21, 33));
        txtCorreo.setForeground(new java.awt.Color(255, 255, 255));
        txtCorreo.setBorderColor(new java.awt.Color(204, 204, 204));
        txtCorreo.setBotonColor(new java.awt.Color(204, 204, 204));
        txtCorreo.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtCorreo.setPhColor(new java.awt.Color(204, 204, 204));
        txtCorreo.setPlaceholder("Escriba su nombre");
        txtCorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCorreoActionPerformed(evt);
            }
        });
        panelPrincipal.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 370, 560, 40));

        txtapellido.setBackground(new java.awt.Color(21, 21, 33));
        txtapellido.setForeground(new java.awt.Color(255, 255, 255));
        txtapellido.setBorderColor(new java.awt.Color(204, 204, 204));
        txtapellido.setBotonColor(new java.awt.Color(204, 204, 204));
        txtapellido.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtapellido.setPhColor(new java.awt.Color(204, 204, 204));
        txtapellido.setPlaceholder("Escriba su nombre");
        txtapellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtapellidoActionPerformed(evt);
            }
        });
        panelPrincipal.add(txtapellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 230, 270, 40));

        txtrol.setBackground(new java.awt.Color(21, 21, 33));
        txtrol.setForeground(new java.awt.Color(255, 255, 255));
        txtrol.setBorderColor(new java.awt.Color(204, 204, 204));
        txtrol.setBotonColor(new java.awt.Color(204, 204, 204));
        txtrol.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtrol.setPhColor(new java.awt.Color(204, 204, 204));
        txtrol.setPlaceholder("Escriba su nombre");
        txtrol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtrolActionPerformed(evt);
            }
        });
        panelPrincipal.add(txtrol, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 510, 560, 40));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Gmail");
        panelPrincipal.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 350, -1, 20));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Número de documento");
        panelPrincipal.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 280, -1, 20));

        btnGuardar.setBackground(new java.awt.Color(67, 94, 190));
        btnGuardar.setText("Guardar");
        btnGuardar.setFont(new java.awt.Font("Humnst777 BlkCn BT", 1, 14)); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        panelPrincipal.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 570, 160, 40));

        txtTelefono.setBackground(new java.awt.Color(21, 21, 33));
        txtTelefono.setForeground(new java.awt.Color(255, 255, 255));
        txtTelefono.setBorderColor(new java.awt.Color(204, 204, 204));
        txtTelefono.setBotonColor(new java.awt.Color(204, 204, 204));
        txtTelefono.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTelefono.setPhColor(new java.awt.Color(204, 204, 204));
        txtTelefono.setPlaceholder("Escriba su nombre");
        txtTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoActionPerformed(evt);
            }
        });
        panelPrincipal.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 440, 560, 40));

        txtusuario.setBackground(new java.awt.Color(21, 21, 33));
        txtusuario.setForeground(new java.awt.Color(255, 255, 255));
        txtusuario.setBorderColor(new java.awt.Color(204, 204, 204));
        txtusuario.setBotonColor(new java.awt.Color(204, 204, 204));
        txtusuario.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtusuario.setPhColor(new java.awt.Color(204, 204, 204));
        txtusuario.setPlaceholder("Escriba su nombre");
        txtusuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtusuarioActionPerformed(evt);
            }
        });
        panelPrincipal.add(txtusuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 300, 270, 40));

        txtNombre.setBackground(new java.awt.Color(30, 30, 45));
        txtNombre.setForeground(new java.awt.Color(0, 0, 0));
        txtNombre.setBorderColor(new java.awt.Color(204, 204, 204));
        txtNombre.setBotonColor(new java.awt.Color(204, 204, 204));
        txtNombre.setCaretColor(new java.awt.Color(255, 255, 255));
        txtNombre.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtNombre.setPhColor(new java.awt.Color(204, 204, 204));
        txtNombre.setPlaceholder("Escriba su nombre");
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        panelPrincipal.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 230, 270, 40));

        numeroidentidad.setBackground(new java.awt.Color(21, 21, 33));
        numeroidentidad.setForeground(new java.awt.Color(255, 255, 255));
        numeroidentidad.setBorderColor(new java.awt.Color(204, 204, 204));
        numeroidentidad.setBotonColor(new java.awt.Color(204, 204, 204));
        numeroidentidad.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        numeroidentidad.setPhColor(new java.awt.Color(204, 204, 204));
        numeroidentidad.setPlaceholder("Escriba su nombre");
        numeroidentidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numeroidentidadActionPerformed(evt);
            }
        });
        panelPrincipal.add(numeroidentidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 300, 220, 40));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Usuario");
        panelPrincipal.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 280, -1, 20));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Telefono");
        panelPrincipal.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 420, -1, 20));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Nombre");
        panelPrincipal.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 210, -1, 20));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Apellido");
        panelPrincipal.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 210, -1, 20));

        rSPanelsSlider1.setBackground(new java.awt.Color(30, 30, 45));
        panelPrincipal.add(rSPanelsSlider1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 190, 620, 450));

        rSLabelIcon5.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon5.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ADD_A_PHOTO);
        panelPrincipal.add(rSLabelIcon5, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 290, -1, -1));

        rSPanelCircleImage1.setImagen(new javax.swing.ImageIcon(getClass().getResource("/WhatsApp Image 2025-03-28 at 11.10.17 AM.jpeg"))); // NOI18N
        rSPanelCircleImage1.setMaximumSize(new java.awt.Dimension(32, 32));
        rSPanelCircleImage1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelPrincipal.add(rSPanelCircleImage1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 220, 120, 120));

        lblTituloPrincipal.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblTituloPrincipal.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloPrincipal.setText("Perfil de la cuenta");
        panelPrincipal.add(lblTituloPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 350, 170, 30));

        lblTituloPrincipal3.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblTituloPrincipal3.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloPrincipal3.setText("Perfil de la cuenta");
        panelPrincipal.add(lblTituloPrincipal3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 380, 170, 30));

        rSButtonRound1.setBackground(new java.awt.Color(30, 30, 45));
        rSButtonRound1.setColorHover(new java.awt.Color(30, 30, 45));
        panelPrincipal.add(rSButtonRound1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 180, 320, 260));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Nombre");
        panelPrincipal.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 210, -1, 20));

        lblTituloPrincipal1.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        lblTituloPrincipal1.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloPrincipal1.setText("Perfil de la cuenta");
        panelPrincipal.add(lblTituloPrincipal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, -1, -1));

        lblTituloPrincipal2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblTituloPrincipal2.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloPrincipal2.setText("Perfil de la cuenta");
        panelPrincipal.add(lblTituloPrincipal2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 340, 210, -1));

        add(panelPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(-6, 0, 1510, 810));
    }// </editor-fold>//GEN-END:initComponents

    private void txtCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoActionPerformed

    private void txtapellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtapellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtapellidoActionPerformed

    private void txtrolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtrolActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrolActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
guardarUsuario();
        if (controlador.obtenerUsuario(idUsuario) != null) {
            cargarPerfil(); // Recarga los datos en este panel
            if (principal != null) {
                principal.actualizarAvatar();
            }
        }
   
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoActionPerformed

    private void txtusuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtusuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtusuarioActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void tipodocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipodocumentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tipodocumentoActionPerformed

    private void numeroidentidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numeroidentidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numeroidentidadActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSButtonRiple btnGuardar;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    public javax.swing.JLabel lblTituloPrincipal;
    public javax.swing.JLabel lblTituloPrincipal1;
    public javax.swing.JLabel lblTituloPrincipal2;
    public javax.swing.JLabel lblTituloPrincipal3;
    private rojerusan.RSMetroTextFullPlaceHolder numeroidentidad;
    private javax.swing.JPanel panelPrincipal;
    private rojerusan.RSButtonRound rSButtonRound1;
    private rojerusan.RSLabelIcon rSLabelIcon5;
    private rojerusan.RSPanelCircleImage rSPanelCircleImage1;
    private rojerusan.RSPanelsSlider rSPanelsSlider1;
    private rojerusan.RSMetroTextFullPlaceHolder tipodocumento;
    private rojerusan.RSMetroTextFullPlaceHolder txtCorreo;
    private rojerusan.RSMetroTextFullPlaceHolder txtNombre;
    private rojerusan.RSMetroTextFullPlaceHolder txtTelefono;
    private rojerusan.RSMetroTextFullPlaceHolder txtapellido;
    private rojerusan.RSMetroTextFullPlaceHolder txtrol;
    private rojerusan.RSMetroTextFullPlaceHolder txtusuario;
    // End of variables declaration//GEN-END:variables
  
  
   private void cargarUsuarioLogueado() {
        if (idUsuario == 0) {
            lblTituloPrincipal.setText("Usuario No identificado");
            return;
        }
        UsuarioModelo usuario = controlador.obtenerUsuario(idUsuario);
        if (usuario.getId_usuario() != 0) {
           lblTituloPrincipal.setText(usuario.getNombre() + " " + usuario.getApellido());

        } else {
            lblTituloPrincipal.setText("Usuario logueado: No encontrado (ID: " + idUsuario + ")");
        }
    }
        
   private void cargarrol() {
if (idUsuario == 0) {
            lblTituloPrincipal3.setText("Rol: No identificado");
            return;
        }
 UsuarioModelo usuario = controlador.obtenerUsuario(idUsuario);

if (usuario.getId_usuario() != 0) {
           lblTituloPrincipal3.setText(usuario.getRol());

        } else {
            lblTituloPrincipal3.setText("Usuario logueado: No encontrado (ID: " + idUsuario + ")");
        }
}
   
   
   private void cargarImagenDinamica() {
    try {
        // Cargar la imagen desde los recursos del proyecto
        java.net.URL imgURL = getClass().getResource("/profile_image.png"); // Ajusta la ruta
        if (imgURL != null) {
            ImageIcon icon = new ImageIcon(imgURL);
            Image img = icon.getImage().getScaledInstance(rSPanelCircleImage1.getWidth(), 
                                                        rSPanelCircleImage1.getHeight(), 
                                                        Image.SCALE_SMOOTH);
            rSPanelCircleImage1.setImagen(new ImageIcon(img));
            
            // Opcional: Guardar la imagen en bytes para el modelo
            java.nio.file.Path path = java.nio.file.Paths.get(imgURL.toURI());
            FileInputStream fis = new FileInputStream(path.toFile());
            imagenSeleccionada = fis.readAllBytes();
            fis.close();
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró la imagen en los recursos.");
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al cargar la imagen: " + e.getMessage());
    }
}
}