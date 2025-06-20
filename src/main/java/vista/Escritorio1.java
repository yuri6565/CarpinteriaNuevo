/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista;

import controlador.Ctrl_Pedido;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import modelo.Conexion;
import vista.Produccion.Produccion;
import vista.Usuarios1;
import vista.VistaClientes;
import vista.proveedor.proveedorProducto;
import vista.proveedor.proveedores;

;

/**
 *
 * @author Personal
 */
public class Escritorio1 extends javax.swing.JPanel {

    private Ctrl_Pedido controlador;

    public Escritorio1() {

        initComponents();

        this.controlador = new Ctrl_Pedido();
        aplicarTema();
        actualizarIdMaximoProveedor();
        actualizarIdMaximocliente();
        actualizarIdMaximousuario();
        actualizarIdMaximoproduccion();
        actualizarIdMaximopedido();

        Tabla1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        // Configura el modelo de tabla correctamente
        DefaultTableModel model = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Nombre", "Fecha inicio", "Fecha Final", "Estado"}
        ) {

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return String.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        Tabla1.setModel(model);

        // Oculta las columnas adicionales después de establecer el modelo
        // Oculta Cantidad
        Tabla1.getColumnModel().getColumn(3).setCellRenderer((TableCellRenderer) new EstadoTableCellRenderer());
        TemaManager.getInstance().addThemeChangeListener(() -> {
            aplicarTema(); // Update theme when it changes
        });
        // Cargar datos desde la base de datos
        cargarDatosIniciales();

    }

    public void aplicarTema() {
        boolean oscuro = TemaManager.getInstance().isOscuro();

        if (oscuro) {
            // Configuración para modo oscuro
            Color fondo = new Color(21, 21, 33);
            Color fondoTabla = new Color(30, 30, 45);
            Color encabezado = new Color(67, 71, 120);
            Color texto = Color.WHITE;

            // Panel principal
            jPanel4.setBackground(fondo);

            // Tabla
            Tabla1.setBackground(fondoTabla);
            Tabla1.setForeground(texto);
            Tabla1.setColorPrimary(new Color(37, 37, 52));  // Filas impares
            Tabla1.setColorSecondary(new Color(30, 30, 45)); // Filas pares
            Tabla1.setColorPrimaryText(texto);
            Tabla1.setColorSecundaryText(texto);
            Tabla1.setBackgoundHead(encabezado);
            Tabla1.setForegroundHead(texto);
            Tabla1.setColorBorderHead(encabezado);
            Tabla1.setColorBorderRows(new Color(60, 60, 60));
            Tabla1.setGridColor(new Color(80, 80, 80));

            // ComboBox
            rSComboBoxMaterial1.setBackground(new Color(37, 37, 52));
            rSComboBoxMaterial1.setForeground(texto);
            // Nota: Verifica si RSComboBoxMaterial tiene métodos como setColorIcon o setPhColor
            // rSComboBoxMaterial1.setColorIcon(texto);
            // rSComboBoxMaterial1.setPhColor(Color.LIGHT_GRAY);

            // Botones
            btnproveedores.setBackground(encabezado);
            btnproveedores.setBackgroundHover(new Color(118, 142, 240));
            btnproveedores1.setBackground(new Color(37, 37, 52));
            btnproveedores1.setBackgroundHover(new Color(118, 142, 240));
            btnCliente.setBackground(encabezado);
            btnCliente.setBackgroundHover(new Color(118, 142, 240));
            btnCliente1.setBackground(new Color(37, 37, 52));
            btnCliente1.setBackgroundHover(new Color(118, 142, 240));
            btnUsuario.setBackground(encabezado);
            btnUsuario.setBackgroundHover(new Color(118, 142, 240));
            btnUsuario1.setBackground(new Color(37, 37, 52));
            btnUsuario1.setBackgroundHover(new Color(118, 142, 240));
            btnproduccion.setBackground(encabezado);
            btnproduccion.setBackgroundHover(new Color(118, 142, 240));
            btnproduccion1.setBackground(new Color(37, 37, 52));
            btnproduccion1.setBackgroundHover(new Color(118, 142, 240));
            btnprodu.setBackground(encabezado);
            btnprodu.setBackgroundHover(new Color(118, 142, 240));
            btnprodu1.setBackground(new Color(37, 37, 52));
            btnprodu1.setBackgroundHover(new Color(118, 142, 240));

            // Labels de imágenes (pueden no necesitar fondo, pero verifica)
            imageCliente.setBackground(fondo);
            imageProduccion.setBackground(fondo);
            imageProveedor.setBackground(fondo);
            ImagePedidos.setBackground(fondo);
            ImageUsu.setBackground(fondo);

            // Labels de texto
            lblProvedores.setForeground(texto);
            lblClientes.setForeground(texto);
            lblUsuario.setForeground(texto);
            lblPedidos.setForeground(texto);
            lblProduccion.setForeground(texto);
            jLabel9.setForeground(texto);

        } else {
            // Configuración para modo claro
            Color fondo = new Color(242, 247, 255);
            Color texto = Color.BLACK;
            Color primario = new Color(72, 92, 188);
            Color targetas = Color.WHITE;

            // Panel principal
            jPanel4.setBackground(fondo);

            // Tabla
            Tabla1.setBackground(fondo);
            Tabla1.setBackgoundHead(primario);
            Tabla1.setForegroundHead(Color.WHITE);
            Tabla1.setBackgoundHover(new Color(67, 150, 209));
            Tabla1.setColorPrimary(new Color(255, 255, 255));
            Tabla1.setColorPrimaryText(texto);
            Tabla1.setColorSecondary(new Color(243, 246, 253));
            Tabla1.setColorSecundaryText(texto);
            Tabla1.setColorBorderHead(primario);
            Tabla1.setColorBorderRows(new Color(0, 0, 0));
            Tabla1.setGridColor(Color.WHITE);

            // ComboBox
            rSComboBoxMaterial1.setBackground(fondo);
            rSComboBoxMaterial1.setForeground(texto);
            // rSComboBoxMaterial1.setColorIcon(texto);
            // rSComboBoxMaterial1.setPhColor(Color.GRAY);

            // Botones
            btnproveedores.setBackground(primario);
            btnproveedores.setBackgroundHover(new Color(67, 150, 209));
            btnproveedores1.setBackground(targetas);
            btnproveedores1.setBackgroundHover(new Color(201, 201, 201));
            btnCliente.setBackground(primario);
            btnCliente.setBackgroundHover(new Color(67, 150, 209));
            btnCliente1.setBackground(targetas);
            btnCliente1.setBackgroundHover(new Color(201, 201, 201));
            btnUsuario.setBackground(primario);
            btnUsuario.setBackgroundHover(new Color(67, 150, 209));
            btnUsuario1.setBackground(targetas);
            btnUsuario1.setBackgroundHover(new Color(201, 201, 201));
            btnproduccion.setBackground(primario);
            btnproduccion.setBackgroundHover(new Color(67, 150, 209));
            btnproduccion1.setBackground(targetas);
            btnproduccion1.setBackgroundHover(new Color(201, 201, 201));
            btnprodu.setBackground(primario);
            btnprodu.setBackgroundHover(new Color(67, 150, 209));
            btnprodu1.setBackground(targetas);
            btnprodu1.setBackgroundHover(new Color(201, 201, 201));

            // Labels de imágenes
            imageCliente.setBackground(fondo);
            ImageUsu.setBackground(fondo);
            imageProduccion.setBackground(fondo);
            ImagePedidos.setBackground(fondo);
            imageProveedor.setBackground(fondo);

            // Labels de texto
            lblProvedores.setForeground(texto);
            lblClientes.setForeground(texto);
            lblUsuario.setForeground(texto);
            lblPedidos.setForeground(texto);
            lblProduccion.setForeground(texto);
            jLabel9.setForeground(texto);
        }

        // Repintar todos los componentes
        jPanel4.repaint();
        Tabla1.repaint();
        Tabla1.getTableHeader().repaint();
        rSComboBoxMaterial1.repaint();
        btnproveedores.repaint();
        btnproveedores1.repaint();
        btnCliente.repaint();
        btnCliente1.repaint();
        btnUsuario.repaint();
        btnUsuario1.repaint();
        btnproduccion.repaint();
        btnproduccion1.repaint();
        btnprodu.repaint();
        btnprodu1.repaint();
        imageCliente.repaint();
        ImageUsu.repaint();
        imageProveedor.repaint();
        ImagePedidos.repaint();
        imageProduccion.repaint();
        lblProvedores.repaint();
        lblClientes.repaint();
        lblUsuario.repaint();
        lblPedidos.repaint();
        lblProduccion.repaint();
        jLabel9.repaint();
    }

    // Método para agregar una nueva fila a la tabla
    public void agregarFilaATabla(Object[] fila) {
        DefaultTableModel model = (DefaultTableModel) Tabla1.getModel();
        model.addRow(fila);
    }

    // Cargar datos desde la base de datos
    public void cargarDatosIniciales() {
        DefaultTableModel model = (DefaultTableModel) Tabla1.getModel();
        model.setRowCount(0);

        List<Ctrl_Pedido.MaterialConDetalles> pedidos = controlador.obtenerMateriales();
        for (Ctrl_Pedido.MaterialConDetalles pedido : pedidos) {
            model.addRow(new Object[]{
                pedido.getPedido().getNombre(),
                new java.text.SimpleDateFormat("dd-MM-yyyy").format(pedido.getPedido().getFecha_inicio()),
                new java.text.SimpleDateFormat("dd-MM-yyyy").format(pedido.getPedido().getFecha_fin()),
                pedido.getPedido().getEstado(),});
        }
    }

    private class EstadoTableCellRenderer extends DefaultTableCellRenderer {

        private final Color textColor = new Color(46, 49, 82);
        private final Font fontNormal = new Font("Tahoma", Font.PLAIN, 14);
        private final Font fontBold = new Font("Tahoma", Font.BOLD, 14);

        public EstadoTableCellRenderer() {
            setHorizontalAlignment(JLabel.CENTER); // Centrar el texto
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {

            // Llamar al método padre primero
            JLabel label = (JLabel) super.getTableCellRendererComponent(
                    table, value, isSelected, hasFocus, row, column);

            label.setHorizontalAlignment(CENTER);
            label.setText(value != null ? value.toString() : "");

            if (isSelected) {
                // Cuando está seleccionado, texto blanco y fondo de selección
                label.setForeground(Color.WHITE);
                label.setBackground(table.getSelectionBackground());
                label.setFont(fontBold);
            } else {
                // Cuando no está seleccionado, mantener el color original del texto
                label.setForeground(textColor);
                label.setFont(fontNormal);

                // Aplicar colores de fondo según el estado
                String estado = value != null ? value.toString() : "";
                switch (estado.toLowerCase()) {
                    case "pendiente":
                        label.setBackground(new Color(255, 204, 204)); // Rojo claro
                        break;
                    case "proceso":
                        label.setBackground(new Color(255, 255, 153)); // Amarillo claro
                        break;
                    case "finalizado":
                        label.setBackground(new Color(204, 255, 204)); // Verde claro
                        break;
                    default:
                        label.setBackground(Color.WHITE);
                        break;
                }
            }

            // Borde igual al resto de la tabla
            return label;
        }
    }

    private void actualizarIdMaximoProveedor() {
        if (lblProvedores == null) {
            return;
        }
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) AS total FROM proveedor"); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                int maxId = rs.getInt("total");
                lblProvedores.setText(String.valueOf(maxId));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Error al obtener el ID máximo: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarIdMaximocliente() {
        if (lblClientes == null) {
            return;
        }
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) AS total FROM cliente"); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                int maxId = rs.getInt("total");
                lblClientes.setText(String.valueOf(maxId));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Error al obtener el ID máximo: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarIdMaximousuario() {
        if (lblUsuario == null) {
            return;
        }
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) AS total FROM usuario"); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                int maxId = rs.getInt("total");
                lblUsuario.setText(String.valueOf(maxId));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Error al obtener el ID máximo: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarIdMaximoproduccion() {
        if (lblProduccion == null) {
            return;
        }
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) AS total FROM produccion"); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                int maxId = rs.getInt("total");
                lblProduccion.setText(String.valueOf(maxId));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Error al obtener el ID máximo: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarIdMaximopedido() {
        if (lblPedidos == null) {
            return;
        }
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) AS total FROM pedido"); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                int maxId = rs.getInt("total");
                lblPedidos.setText(String.valueOf(maxId));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Error al obtener el ID máximo: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        rSComboBoxMaterial1 = new RSMaterialComponent.RSComboBoxMaterial();
        btnproveedores = new RSMaterialComponent.RSButtonShape();
        jLabel6 = new javax.swing.JLabel();
        imageProveedor = new rojerusan.RSPanelImage();
        lblProvedores = new javax.swing.JLabel();
        btnproveedores1 = new RSMaterialComponent.RSButtonShape();
        jLabel7 = new javax.swing.JLabel();
        lblClientes = new javax.swing.JLabel();
        imageCliente = new rojerusan.RSPanelImage();
        btnCliente = new RSMaterialComponent.RSButtonShape();
        btnCliente1 = new RSMaterialComponent.RSButtonShape();
        btnUsuario = new RSMaterialComponent.RSButtonShape();
        jLabel8 = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        ImageUsu = new rojerusan.RSPanelImage();
        btnUsuario1 = new RSMaterialComponent.RSButtonShape();
        btnproduccion = new RSMaterialComponent.RSButtonShape();
        lblPedidos = new javax.swing.JLabel();
        ImagePedidos = new rojerusan.RSPanelImage();
        lblProduccion = new javax.swing.JLabel();
        imageProduccion = new rojerusan.RSPanelImage();
        btnproduccion1 = new RSMaterialComponent.RSButtonShape();
        btnprodu = new RSMaterialComponent.RSButtonShape();
        btnprodu1 = new RSMaterialComponent.RSButtonShape();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla1 = new rojerusan.RSTableMetro1();
        jLabel9 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1290, 730));
        setPreferredSize(new java.awt.Dimension(1306, 640));

        jPanel4.setBackground(new java.awt.Color(243, 247, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(1306, 640));
        jPanel4.setRequestFocusEnabled(false);
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSComboBoxMaterial1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Notificaciones", " " }));
        rSComboBoxMaterial1.setFont(new java.awt.Font("Roboto Bold", 0, 18)); // NOI18N
        jPanel4.add(rSComboBoxMaterial1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, 430, 60));

        btnproveedores.setBackground(new java.awt.Color(46, 49, 82));
        btnproveedores.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        btnproveedores.setText("   Total de Proveedores");
        btnproveedores.setAutoscrolls(true);
        btnproveedores.setBackgroundHover(new java.awt.Color(255, 102, 102));
        btnproveedores.setFont(new java.awt.Font("Roboto Bold", 1, 16)); // NOI18N
        btnproveedores.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        btnproveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnproveedoresActionPerformed(evt);
            }
        });
        jPanel4.add(btnproveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, -1, 40));
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 80, -1, 80));

        imageProveedor.setImagen(new javax.swing.ImageIcon(getClass().getResource("/provedorIcono.png"))); // NOI18N

        javax.swing.GroupLayout imageProveedorLayout = new javax.swing.GroupLayout(imageProveedor);
        imageProveedor.setLayout(imageProveedorLayout);
        imageProveedorLayout.setHorizontalGroup(
            imageProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );
        imageProveedorLayout.setVerticalGroup(
            imageProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        jPanel4.add(imageProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 70, 60));

        lblProvedores.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblProvedores.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblProvedores.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                lblProvedoresFocusLost(evt);
            }
        });
        jPanel4.add(lblProvedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 60, 60));

        btnproveedores1.setBackground(new java.awt.Color(255, 255, 255));
        btnproveedores1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnproveedores1.setBackgroundHover(new java.awt.Color(204, 204, 204));
        btnproveedores1.setFont(new java.awt.Font("Roboto Bold", 1, 18)); // NOI18N
        btnproveedores1.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        btnproveedores1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnproveedores1ActionPerformed(evt);
            }
        });
        jPanel4.add(btnproveedores1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, -1, 120));
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 90, -1, 60));

        lblClientes.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblClientes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel4.add(lblClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 90, 60, 60));

        imageCliente.setImagen(new javax.swing.ImageIcon(getClass().getResource("/clientesicono.png"))); // NOI18N

        javax.swing.GroupLayout imageClienteLayout = new javax.swing.GroupLayout(imageCliente);
        imageCliente.setLayout(imageClienteLayout);
        imageClienteLayout.setHorizontalGroup(
            imageClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );
        imageClienteLayout.setVerticalGroup(
            imageClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        jPanel4.add(imageCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 80, 70, 70));

        btnCliente.setBackground(new java.awt.Color(46, 49, 82));
        btnCliente.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnCliente.setText("         Total de Clientes");
        btnCliente.setBackgroundHover(new java.awt.Color(153, 153, 255));
        btnCliente.setFont(new java.awt.Font("Roboto Bold", 1, 16)); // NOI18N
        btnCliente.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        btnCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClienteActionPerformed(evt);
            }
        });
        jPanel4.add(btnCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 40, 210, 40));

        btnCliente1.setBackground(new java.awt.Color(255, 255, 255));
        btnCliente1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnCliente1.setBackgroundHover(new java.awt.Color(204, 204, 204));
        btnCliente1.setFont(new java.awt.Font("Roboto Bold", 1, 18)); // NOI18N
        btnCliente1.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        btnCliente1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliente1ActionPerformed(evt);
            }
        });
        jPanel4.add(btnCliente1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 40, 210, 120));

        btnUsuario.setBackground(new java.awt.Color(46, 49, 82));
        btnUsuario.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnUsuario.setText("       Usuarios Totales");
        btnUsuario.setBackgroundHover(new java.awt.Color(0, 204, 0));
        btnUsuario.setFont(new java.awt.Font("Roboto Bold", 1, 16)); // NOI18N
        btnUsuario.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        btnUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuarioActionPerformed(evt);
            }
        });
        jPanel4.add(btnUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 40, -1, 40));
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 80, -1, 80));

        lblUsuario.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblUsuario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel4.add(lblUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 90, 60, 60));

        ImageUsu.setImagen(new javax.swing.ImageIcon(getClass().getResource("/usuariosIcono.png"))); // NOI18N

        javax.swing.GroupLayout ImageUsuLayout = new javax.swing.GroupLayout(ImageUsu);
        ImageUsu.setLayout(ImageUsuLayout);
        ImageUsuLayout.setHorizontalGroup(
            ImageUsuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 65, Short.MAX_VALUE)
        );
        ImageUsuLayout.setVerticalGroup(
            ImageUsuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 55, Short.MAX_VALUE)
        );

        jPanel4.add(ImageUsu, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 90, 65, 55));

        btnUsuario1.setBackground(new java.awt.Color(255, 255, 255));
        btnUsuario1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnUsuario1.setBackgroundHover(new java.awt.Color(204, 204, 204));
        btnUsuario1.setFont(new java.awt.Font("Roboto Bold", 1, 18)); // NOI18N
        btnUsuario1.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        btnUsuario1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuario1ActionPerformed(evt);
            }
        });
        jPanel4.add(btnUsuario1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 40, -1, 120));

        btnproduccion.setBackground(new java.awt.Color(46, 49, 82));
        btnproduccion.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnproduccion.setText("         Pedidos Actuales");
        btnproduccion.setBackgroundHover(new java.awt.Color(255, 102, 255));
        btnproduccion.setFont(new java.awt.Font("Roboto Bold", 1, 16)); // NOI18N
        btnproduccion.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        btnproduccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnproduccionActionPerformed(evt);
            }
        });
        jPanel4.add(btnproduccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 40, -1, 40));

        lblPedidos.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblPedidos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel4.add(lblPedidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 90, 60, 60));

        ImagePedidos.setImagen(new javax.swing.ImageIcon(getClass().getResource("/carrito-removebg-preview.png"))); // NOI18N

        javax.swing.GroupLayout ImagePedidosLayout = new javax.swing.GroupLayout(ImagePedidos);
        ImagePedidos.setLayout(ImagePedidosLayout);
        ImagePedidosLayout.setHorizontalGroup(
            ImagePedidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );
        ImagePedidosLayout.setVerticalGroup(
            ImagePedidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel4.add(ImagePedidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 70, -1, -1));

        lblProduccion.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblProduccion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel4.add(lblProduccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 90, 60, 60));

        imageProduccion.setImagen(new javax.swing.ImageIcon(getClass().getResource("/produccionIcono.png"))); // NOI18N

        javax.swing.GroupLayout imageProduccionLayout = new javax.swing.GroupLayout(imageProduccion);
        imageProduccion.setLayout(imageProduccionLayout);
        imageProduccionLayout.setHorizontalGroup(
            imageProduccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );
        imageProduccionLayout.setVerticalGroup(
            imageProduccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        jPanel4.add(imageProduccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 90, 70, 60));

        btnproduccion1.setBackground(new java.awt.Color(255, 255, 255));
        btnproduccion1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnproduccion1.setBackgroundHover(new java.awt.Color(204, 204, 204));
        btnproduccion1.setFont(new java.awt.Font("Roboto Bold", 1, 18)); // NOI18N
        btnproduccion1.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        btnproduccion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnproduccion1ActionPerformed(evt);
            }
        });
        jPanel4.add(btnproduccion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 40, -1, 120));

        btnprodu.setBackground(new java.awt.Color(46, 49, 82));
        btnprodu.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnprodu.setText("            Produccion");
        btnprodu.setBackgroundHover(new java.awt.Color(153, 204, 255));
        btnprodu.setFont(new java.awt.Font("Roboto Bold", 1, 16)); // NOI18N
        btnprodu.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        btnprodu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnproduActionPerformed(evt);
            }
        });
        jPanel4.add(btnprodu, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 40, -1, 40));

        btnprodu1.setBackground(new java.awt.Color(255, 255, 255));
        btnprodu1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnprodu1.setBackgroundHover(new java.awt.Color(204, 204, 204));
        btnprodu1.setFont(new java.awt.Font("Roboto Bold", 1, 18)); // NOI18N
        btnprodu1.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        btnprodu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnprodu1ActionPerformed(evt);
            }
        });
        jPanel4.add(btnprodu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 40, -1, 120));

        Tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nombre", "Fecha Inicio", "Fecha Final", "Estado"
            }
        ));
        Tabla1.setBackgoundHead(new java.awt.Color(255, 255, 255));
        Tabla1.setBackgoundHover(new java.awt.Color(109, 160, 221));
        Tabla1.setBorderHead(null);
        Tabla1.setBorderRows(null);
        Tabla1.setColorBorderHead(new java.awt.Color(255, 255, 255));
        Tabla1.setColorBorderRows(new java.awt.Color(255, 255, 255));
        Tabla1.setColorPrimary(new java.awt.Color(243, 246, 253));
        Tabla1.setColorPrimaryText(new java.awt.Color(0, 0, 0));
        Tabla1.setColorSecondary(new java.awt.Color(255, 255, 255));
        Tabla1.setColorSecundaryText(new java.awt.Color(0, 0, 0));
        Tabla1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Tabla1.setFontHead(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Tabla1.setFontRowHover(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Tabla1.setFontRowSelect(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Tabla1.setForegroundHead(new java.awt.Color(0, 0, 0));
        Tabla1.setGridColor(new java.awt.Color(255, 255, 255));
        Tabla1.setHighHead(50);
        Tabla1.setPreferredSize(new java.awt.Dimension(300, 357));
        Tabla1.setRowHeight(35);
        Tabla1.setSelectionBackground(new java.awt.Color(109, 160, 221));
        jScrollPane1.setViewportView(Tabla1);

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 260, 565, 410));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("Pedidos Proximos");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 230, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 1491, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 840, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnproveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnproveedoresActionPerformed

        Proveedor pr = new  Proveedor(new javax.swing.JFrame(), true);
        pr.setSize(1290, 730);
        pr.setLocation(0, 0);

        jPanel4.removeAll();
        jPanel4.add(pr);
        jPanel4.revalidate();
        jPanel4.repaint();


    }//GEN-LAST:event_btnproveedoresActionPerformed

    private void lblProvedoresFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lblProvedoresFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_lblProvedoresFocusLost

    private void btnproveedores1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnproveedores1ActionPerformed

         Proveedor pr = new  Proveedor(new javax.swing.JFrame(), true);
        pr.setSize(1260, 730);
        pr.setLocation(0, 0);

        jPanel4.removeAll();
        jPanel4.add(pr);
        jPanel4.revalidate();
        jPanel4.repaint();
    }//GEN-LAST:event_btnproveedores1ActionPerformed

    private void btnClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClienteActionPerformed

        // TODO add your handling code here:
        VistaClientes cl = new VistaClientes(new javax.swing.JFrame(), true);
        cl.setSize(1260, 730);
        cl.setLocation(0, 0);

        jPanel4.removeAll();
        jPanel4.add(cl);
        jPanel4.revalidate();
        jPanel4.repaint();
    }//GEN-LAST:event_btnClienteActionPerformed

    private void btnCliente1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliente1ActionPerformed
        // TODO add your handling code here:
        VistaClientes cl = new VistaClientes(new javax.swing.JFrame(), true);
        cl.setSize(1260, 730);
        cl.setLocation(0, 0);

        jPanel4.removeAll();
        jPanel4.add(cl);
        jPanel4.revalidate();
        jPanel4.repaint();
    }//GEN-LAST:event_btnCliente1ActionPerformed

    private void btnUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuarioActionPerformed

        Usuarios1 usu = new Usuarios1(new javax.swing.JFrame(), true);
        usu.setSize(1260, 730);
        usu.setLocation(0, 0);

        jPanel4.removeAll();
        jPanel4.add(usu);
        jPanel4.revalidate();
        jPanel4.repaint();
    }//GEN-LAST:event_btnUsuarioActionPerformed

    private void btnUsuario1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuario1ActionPerformed

        Usuarios1 usu = new Usuarios1(new javax.swing.JFrame(), true);
        usu.setSize(1260, 730);
        usu.setLocation(0, 0);

        jPanel4.removeAll();
        jPanel4.add(usu);
        jPanel4.revalidate();
        jPanel4.repaint();
    }//GEN-LAST:event_btnUsuario1ActionPerformed

    private void btnproduccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnproduccionActionPerformed
        vista.Ventas.pedido pedidos = new vista.Ventas.pedido(jPanel4);
        pedidos.setSize(1290, 730);
        pedidos.setLocation(0, 0);

        jPanel4.removeAll();
        jPanel4.add(pedidos);
        jPanel4.revalidate();
        jPanel4.repaint();
    }//GEN-LAST:event_btnproduccionActionPerformed

    private void btnproduccion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnproduccion1ActionPerformed
        vista.Ventas.pedido pedidos = new vista.Ventas.pedido(jPanel4);
        pedidos.setSize(1290, 730);
        pedidos.setLocation(0, 0);

        jPanel4.removeAll();
        jPanel4.add(pedidos);
        jPanel4.revalidate();
        jPanel4.repaint();
    }//GEN-LAST:event_btnproduccion1ActionPerformed

    private void btnproduActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnproduActionPerformed

        Produccion pro = new Produccion(new javax.swing.JFrame(), true);
        pro.setSize(1260, 730);
        pro.setLocation(0, 0);

        jPanel4.removeAll();
        jPanel4.add(pro);
        jPanel4.revalidate();
        jPanel4.repaint();
    }//GEN-LAST:event_btnproduActionPerformed

    private void btnprodu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnprodu1ActionPerformed

        Produccion pro = new Produccion(new javax.swing.JFrame(), true);
        pro.setSize(1260, 730);
        pro.setLocation(0, 0);

        jPanel4.removeAll();
        jPanel4.add(pro);
        jPanel4.revalidate();
        jPanel4.repaint();
    }//GEN-LAST:event_btnprodu1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSPanelImage ImagePedidos;
    private rojerusan.RSPanelImage ImageUsu;
    private rojerusan.RSTableMetro1 Tabla1;
    private RSMaterialComponent.RSButtonShape btnCliente;
    private RSMaterialComponent.RSButtonShape btnCliente1;
    private RSMaterialComponent.RSButtonShape btnUsuario;
    private RSMaterialComponent.RSButtonShape btnUsuario1;
    private RSMaterialComponent.RSButtonShape btnprodu;
    private RSMaterialComponent.RSButtonShape btnprodu1;
    private RSMaterialComponent.RSButtonShape btnproduccion;
    private RSMaterialComponent.RSButtonShape btnproduccion1;
    private RSMaterialComponent.RSButtonShape btnproveedores;
    private RSMaterialComponent.RSButtonShape btnproveedores1;
    private rojerusan.RSPanelImage imageCliente;
    private rojerusan.RSPanelImage imageProduccion;
    private rojerusan.RSPanelImage imageProveedor;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblClientes;
    private javax.swing.JLabel lblPedidos;
    private javax.swing.JLabel lblProduccion;
    private javax.swing.JLabel lblProvedores;
    private javax.swing.JLabel lblUsuario;
    private RSMaterialComponent.RSComboBoxMaterial rSComboBoxMaterial1;
    // End of variables declaration//GEN-END:variables
}
