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
            btnproveedores1.setBackground(fondo);
            btnproveedores1.setBackgroundHover(new Color(118, 142, 240));
            btnCliente.setBackground(encabezado);
            btnCliente.setBackgroundHover(new Color(118, 142, 240));
            btnCliente1.setBackground(fondo);
            btnCliente1.setBackgroundHover(new Color(118, 142, 240));
            btnUsuario.setBackground(encabezado);
            btnUsuario.setBackgroundHover(new Color(118, 142, 240));
            btnUsuario1.setBackground(fondo);
            btnUsuario1.setBackgroundHover(new Color(118, 142, 240));
            btnproduccion.setBackground(encabezado);
            btnproduccion.setBackgroundHover(new Color(118, 142, 240));
            btnproduccion1.setBackground(fondo);
            btnproduccion1.setBackgroundHover(new Color(118, 142, 240));
            btnprodu.setBackground(encabezado);
            btnprodu.setBackgroundHover(new Color(118, 142, 240));
            btnprodu1.setBackground(fondo);
            btnprodu1.setBackgroundHover(new Color(118, 142, 240));

            // Labels de imágenes (pueden no necesitar fondo, pero verifica)
            rSLabelImage1.setBackground(fondo);
            rSLabelImage3.setBackground(fondo);
            rSLabelImage4.setBackground(fondo);
            rSPanelImage1.setBackground(fondo);
            rSPanelImage2.setBackground(fondo);

            // Labels de texto
            jLabel1.setForeground(texto);
            jLabel2.setForeground(texto);
            jLabel3.setForeground(texto);
            jLabel4.setForeground(texto);
            jLabel5.setForeground(texto);
            jLabel9.setForeground(texto);

        } else {
            // Configuración para modo claro
            Color fondo = new Color(242, 247, 255);
            Color texto = Color.BLACK;
            Color primario = new Color(72, 92, 188);

            // Panel principal
            jPanel4.setBackground(fondo);

            // Tabla
            Tabla1.setBackground(new Color(255, 255, 255));
            Tabla1.setBackgoundHead(new Color(46, 49, 82));
            Tabla1.setForegroundHead(Color.WHITE);
            Tabla1.setBackgoundHover(new Color(67, 150, 209));
            Tabla1.setColorPrimary(new Color(242, 242, 242));
            Tabla1.setColorPrimaryText(texto);
            Tabla1.setColorSecondary(new Color(255, 255, 255));
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
            btnproveedores1.setBackground(fondo);
            btnproveedores1.setBackgroundHover(new Color(204, 204, 204));
            btnCliente.setBackground(primario);
            btnCliente.setBackgroundHover(new Color(67, 150, 209));
            btnCliente1.setBackground(fondo);
            btnCliente1.setBackgroundHover(new Color(204, 204, 204));
            btnUsuario.setBackground(primario);
            btnUsuario.setBackgroundHover(new Color(67, 150, 209));
            btnUsuario1.setBackground(fondo);
            btnUsuario1.setBackgroundHover(new Color(204, 204, 204));
            btnproduccion.setBackground(primario);
            btnproduccion.setBackgroundHover(new Color(67, 150, 209));
            btnproduccion1.setBackground(fondo);
            btnproduccion1.setBackgroundHover(new Color(204, 204, 204));
            btnprodu.setBackground(primario);
            btnprodu.setBackgroundHover(new Color(67, 150, 209));
            btnprodu1.setBackground(fondo);
            btnprodu1.setBackgroundHover(new Color(204, 204, 204));

            // Labels de imágenes
            rSLabelImage1.setBackground(fondo);
            rSLabelImage3.setBackground(fondo);
            rSLabelImage4.setBackground(fondo);
            rSPanelImage1.setBackground(fondo);
            rSPanelImage2.setBackground(fondo);

            // Labels de texto
            jLabel1.setForeground(texto);
            jLabel2.setForeground(texto);
            jLabel3.setForeground(texto);
            jLabel4.setForeground(texto);
            jLabel5.setForeground(texto);
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
        rSLabelImage1.repaint();
        rSLabelImage3.repaint();
        rSLabelImage4.repaint();
        rSPanelImage1.repaint();
        rSPanelImage2.repaint();
        jLabel1.repaint();
        jLabel2.repaint();
        jLabel3.repaint();
        jLabel4.repaint();
        jLabel5.repaint();
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
        if (jLabel1 == null) {
            return;
        }
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) AS total FROM proveedor"); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                int maxId = rs.getInt("total");
                jLabel1.setText(String.valueOf(maxId));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Error al obtener el ID máximo: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarIdMaximocliente() {
        if (jLabel2 == null) {
            return;
        }
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) AS total FROM cliente"); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                int maxId = rs.getInt("total");
                jLabel2.setText(String.valueOf(maxId));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Error al obtener el ID máximo: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarIdMaximousuario() {
        if (jLabel3 == null) {
            return;
        }
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) AS total FROM usuario"); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                int maxId = rs.getInt("total");
                jLabel3.setText(String.valueOf(maxId));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Error al obtener el ID máximo: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarIdMaximoproduccion() {
        if (jLabel5 == null) {
            return;
        }
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) AS total FROM produccion"); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                int maxId = rs.getInt("total");
                jLabel5.setText(String.valueOf(maxId));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Error al obtener el ID máximo: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarIdMaximopedido() {
        if (jLabel4 == null) {
            return;
        }
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) AS total FROM pedido"); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                int maxId = rs.getInt("total");
                jLabel4.setText(String.valueOf(maxId));
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
        rSLabelImage1 = new rojeru_san.rslabel.RSLabelImage();
        rSLabelImage3 = new rojeru_san.rslabel.RSLabelImage();
        rSLabelImage4 = new rojeru_san.rslabel.RSLabelImage();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnproveedores1 = new RSMaterialComponent.RSButtonShape();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnCliente = new RSMaterialComponent.RSButtonShape();
        btnCliente1 = new RSMaterialComponent.RSButtonShape();
        btnUsuario = new RSMaterialComponent.RSButtonShape();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnUsuario1 = new RSMaterialComponent.RSButtonShape();
        btnproduccion = new RSMaterialComponent.RSButtonShape();
        jLabel4 = new javax.swing.JLabel();
        rSPanelImage1 = new rojerusan.RSPanelImage();
        jLabel5 = new javax.swing.JLabel();
        rSPanelImage2 = new rojerusan.RSPanelImage();
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

        rSComboBoxMaterial1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Notificaciones", " " }));
        rSComboBoxMaterial1.setFont(new java.awt.Font("Roboto Bold", 0, 18)); // NOI18N

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

        rSLabelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cliente (1).png"))); // NOI18N

        rSLabelImage3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proveedor (6).png"))); // NOI18N

        rSLabelImage4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/usu (1).png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jLabel1FocusLost(evt);
            }
        });

        btnproveedores1.setBackground(new java.awt.Color(242, 242, 242));
        btnproveedores1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnproveedores1.setBackgroundHover(new java.awt.Color(204, 204, 204));
        btnproveedores1.setFont(new java.awt.Font("Roboto Bold", 1, 18)); // NOI18N
        btnproveedores1.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        btnproveedores1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnproveedores1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

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

        btnCliente1.setBackground(new java.awt.Color(242, 242, 242));
        btnCliente1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnCliente1.setBackgroundHover(new java.awt.Color(204, 204, 204));
        btnCliente1.setFont(new java.awt.Font("Roboto Bold", 1, 18)); // NOI18N
        btnCliente1.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        btnCliente1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliente1ActionPerformed(evt);
            }
        });

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

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btnUsuario1.setBackground(new java.awt.Color(242, 242, 242));
        btnUsuario1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnUsuario1.setBackgroundHover(new java.awt.Color(204, 204, 204));
        btnUsuario1.setFont(new java.awt.Font("Roboto Bold", 1, 18)); // NOI18N
        btnUsuario1.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        btnUsuario1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuario1ActionPerformed(evt);
            }
        });

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

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        rSPanelImage1.setImagen(new javax.swing.ImageIcon(getClass().getResource("/carrito-removebg-preview.png"))); // NOI18N

        javax.swing.GroupLayout rSPanelImage1Layout = new javax.swing.GroupLayout(rSPanelImage1);
        rSPanelImage1.setLayout(rSPanelImage1Layout);
        rSPanelImage1Layout.setHorizontalGroup(
            rSPanelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );
        rSPanelImage1Layout.setVerticalGroup(
            rSPanelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        rSPanelImage2.setImagen(new javax.swing.ImageIcon(getClass().getResource("/proceso (1).png"))); // NOI18N

        javax.swing.GroupLayout rSPanelImage2Layout = new javax.swing.GroupLayout(rSPanelImage2);
        rSPanelImage2.setLayout(rSPanelImage2Layout);
        rSPanelImage2Layout.setHorizontalGroup(
            rSPanelImage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );
        rSPanelImage2Layout.setVerticalGroup(
            rSPanelImage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        btnproduccion1.setBackground(new java.awt.Color(242, 242, 242));
        btnproduccion1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnproduccion1.setBackgroundHover(new java.awt.Color(204, 204, 204));
        btnproduccion1.setFont(new java.awt.Font("Roboto Bold", 1, 18)); // NOI18N
        btnproduccion1.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        btnproduccion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnproduccion1ActionPerformed(evt);
            }
        });

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

        btnprodu1.setBackground(new java.awt.Color(242, 242, 242));
        btnprodu1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnprodu1.setBackgroundHover(new java.awt.Color(204, 204, 204));
        btnprodu1.setFont(new java.awt.Font("Roboto Bold", 1, 18)); // NOI18N
        btnprodu1.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        btnprodu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnprodu1ActionPerformed(evt);
            }
        });

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

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("Pedidos Proximos");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnproveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(130, 130, 130)
                                .addComponent(jLabel6))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(130, 130, 130)
                                .addComponent(rSLabelImage3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnproveedores1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(rSLabelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btnCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel8)
                                .addGap(10, 10, 10)
                                .addComponent(rSLabelImage4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnUsuario1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(100, 100, 100)
                                .addComponent(rSPanelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnproduccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnproduccion1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnprodu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(rSPanelImage2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnprodu1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(rSComboBoxMaterial1, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 565, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(281, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnproveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(rSLabelImage3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnproveedores1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rSLabelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rSLabelImage4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(btnUsuario1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(rSPanelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnproduccion, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnproduccion1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnprodu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rSPanelImage2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnprodu1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rSComboBoxMaterial1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel9)
                        .addGap(5, 5, 5)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(170, Short.MAX_VALUE))
        );

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

    private void jLabel1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jLabel1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1FocusLost

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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private RSMaterialComponent.RSComboBoxMaterial rSComboBoxMaterial1;
    private rojeru_san.rslabel.RSLabelImage rSLabelImage1;
    private rojeru_san.rslabel.RSLabelImage rSLabelImage3;
    private rojeru_san.rslabel.RSLabelImage rSLabelImage4;
    private rojerusan.RSPanelImage rSPanelImage1;
    private rojerusan.RSPanelImage rSPanelImage2;
    // End of variables declaration//GEN-END:variables
}
