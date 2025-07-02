/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista;

import controlador.Ctrl_Cliente;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import modelo.Cliente;
import modelo.Conexion;
import rojeru_san.RSButton;
import rojeru_san.efectos.ValoresEnum;
import rojerusan.RSLabelIcon;
import vista.Usuarios1.CustomCheckboxEditor;
import vista.Usuarios1.CustomCheckboxRenderer;

/**
 *
 * @author ZenBook
 */




public class VistaClientes extends javax.swing.JPanel {

    private int id_cliente;
    private Ctrl_Cliente controlador;
  
    private int currentPage = 0;
    private final int CLIENTES_POR_PAGINA = 19;
    private List<Cliente> todasLasClientes = new ArrayList<>();
    private boolean[] seleccionados; // To track selected checkboxes

   public VistaClientes(JFrame jFrame, boolean par) {
    controlador = new Ctrl_Cliente();
    initComponents();
    SwingUtilities.invokeLater(() -> {
        cargartablaclientes();
        rSCheckBox1.addActionListener(e -> seleccionarTodo());
        System.out.println("tablaclientes initialized with " + tablaclientes.getColumnCount() + " columns");
    });
    aplicarTema();
    TemaManager.getInstance().addThemeChangeListener(this::aplicarTema);

    // Agregar DocumentListener para búsqueda dinámica
    txtBuscar.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
        @Override
        public void insertUpdate(javax.swing.event.DocumentEvent e) {
            buscarDinamico();
        }

        @Override
        public void removeUpdate(javax.swing.event.DocumentEvent e) {
            buscarDinamico();
        }

        @Override
        public void changedUpdate(javax.swing.event.DocumentEvent e) {
            buscarDinamico();
        }
    });
}
   
  private void buscarDinamico() {
    String textoBusqueda = txtBuscar.getText().trim();
    if (textoBusqueda.isEmpty()) {
        todasLasClientes = new ArrayList<>(controlador.obtenerClientes());
        seleccionados = new boolean[todasLasClientes.size()];
        currentPage = 0;
        mostrarPagina(currentPage);
        return;
    }

    // Buscar por todos los campos
    List<Cliente> resultados = controlador.buscarClientePorTodos(textoBusqueda);
    if (!resultados.isEmpty()) {
        todasLasClientes = new ArrayList<>(resultados);
        seleccionados = new boolean[todasLasClientes.size()];
        currentPage = 0;
        mostrarPagina(currentPage);
    } else {
        JOptionPane.showMessageDialog(this, "No se encontraron clientes con el texto: " + textoBusqueda, "No encontrado", JOptionPane.WARNING_MESSAGE);
        todasLasClientes = new ArrayList<>();
        seleccionados = new boolean[todasLasClientes.size()];
        mostrarPagina(currentPage);
    }
}
public void cargartablaclientes() {
    tablaclientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    DefaultTableModel model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int fila, int columna) {
            return columna == 0 || columna == 9; // Solo "Seleccionar" y "Acciones" son editables
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return columnIndex == 0 ? Boolean.class : String.class; // Checkbox en la primera columna
        }
    };

    model.addColumn("Seleccionar");
    model.addColumn("Código");
    model.addColumn("Doc. Tipo");
    model.addColumn("Nombre");
    model.addColumn("Apellido");
    model.addColumn("Teléfono");
    model.addColumn("Departamento/Municipio");
    model.addColumn("Dirección");
    model.addColumn("Estado");
    model.addColumn("Acciones");

    // Fetch all clients and store them
    List<Cliente> clientes = controlador.obtenerClientes();
    todasLasClientes = new ArrayList<>(clientes); // Usar la lista original para compatibilidad
    System.out.println("Número de clientes obtenidos: " + todasLasClientes.size()); // Debug
    seleccionados = new boolean[todasLasClientes.size()];

    // Asignar el modelo antes de poblar
    tablaclientes.setModel(model);

    // Poblar la primera página
    mostrarPagina(currentPage);

    // Configurar renderers y editores después de poblar
    tablaclientes.getColumnModel().getColumn(0).setCellRenderer(new CustomCheckboxRenderer());
    tablaclientes.getColumnModel().getColumn(0).setCellEditor(new CustomCheckboxEditor());
    tablaclientes.getColumnModel().getColumn(9).setCellRenderer(new ButtonPanelRenderer());
    tablaclientes.getColumnModel().getColumn(9).setCellEditor(new ButtonPanelEditor(new JCheckBox()));

    tablaclientes.getColumnModel().getColumn(0).setPreferredWidth(65);
    tablaclientes.getColumnModel().getColumn(1).setPreferredWidth(70);
    tablaclientes.getColumnModel().getColumn(2).setPreferredWidth(70);
    tablaclientes.getColumnModel().getColumn(3).setPreferredWidth(100);
    tablaclientes.getColumnModel().getColumn(4).setPreferredWidth(100);
    tablaclientes.getColumnModel().getColumn(5).setPreferredWidth(80);
    tablaclientes.getColumnModel().getColumn(6).setPreferredWidth(190);
    tablaclientes.getColumnModel().getColumn(7).setPreferredWidth(100);
    tablaclientes.getColumnModel().getColumn(8).setPreferredWidth(80);
    tablaclientes.getColumnModel().getColumn(9).setPreferredWidth(80);
    tablaclientes.setRowHeight(24);

    tablaclientes.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int fila_point = tablaclientes.rowAtPoint(e.getPoint());
            if (fila_point > -1) {
                id_cliente = Integer.parseInt(tablaclientes.getValueAt(fila_point, 1).toString());
            }
        }
    });
}

private void mostrarPagina(int pagina) {
    DefaultTableModel model = (DefaultTableModel) tablaclientes.getModel();
    model.setRowCount(0); // Clear table

    int inicio = pagina * CLIENTES_POR_PAGINA;
    int fin = Math.min(inicio + CLIENTES_POR_PAGINA, todasLasClientes.size());

    if (inicio >= todasLasClientes.size()) {
        currentPage = 0; // Reset to first page if out of range
        inicio = 0;
        fin = Math.min(CLIENTES_POR_PAGINA, todasLasClientes.size());
    }

    for (int i = inicio; i < fin; i++) {
        Cliente cliente = todasLasClientes.get(i);
        Object[] fila = new Object[10];
        fila[0] = seleccionados[i]; // Checkbox state
        fila[1] = String.valueOf(cliente.getId_cliente());
        fila[2] = cliente.getIdentificacion() != null ? cliente.getIdentificacion() : "Sin identificación";
        fila[3] = cliente.getNombre() != null ? cliente.getNombre() : "Sin nombre";
        fila[4] = cliente.getApellido() != null ? cliente.getApellido() : "Sin apellido";
        fila[5] = cliente.getTelefono() != null ? cliente.getTelefono() : "Sin teléfono";
        String departamento = cliente.getDepartamento() != null ? cliente.getDepartamento() : "";
        String municipio = cliente.getMunicipio() != null ? cliente.getMunicipio() : "";
        fila[6] = (!departamento.isEmpty() || !municipio.isEmpty()) ? departamento + " / " + municipio : "Sin ubicación";
        fila[7] = cliente.getDireccion() != null ? cliente.getDireccion() : "Sin dirección";
        fila[8] = cliente.isActivo() ? "Activo" : "Inactivo";
        fila[9] = "";
        model.addRow(fila);
    }

    int totalPaginas = (int) Math.ceil((double) todasLasClientes.size() / CLIENTES_POR_PAGINA);
    paginacion.setText("Página " + (currentPage + 1) + " de " + totalPaginas);
    Añadir5.setEnabled(currentPage > 0);
    Añadir4.setEnabled(currentPage < totalPaginas - 1);
}

private void seleccionarTodo() {
    DefaultTableModel model = (DefaultTableModel) tablaclientes.getModel();
    boolean seleccionado = rSCheckBox1.isSelected();
    int inicio = currentPage * CLIENTES_POR_PAGINA;
    int fin = Math.min(inicio + CLIENTES_POR_PAGINA, todasLasClientes.size());

    for (int i = 0; i < model.getRowCount(); i++) {
        model.setValueAt(seleccionado, i, 0);
        seleccionados[inicio + i] = seleccionado;
    }
}

    // In ButtonPanelRenderer
    class ButtonPanelRenderer extends JPanel implements TableCellRenderer {
        private RSLabelIcon editIcon;
        private RSLabelIcon deleteIcon;

        public ButtonPanelRenderer() {
            setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 2, 0));
            setOpaque(true);

            editIcon = new RSLabelIcon();
            editIcon.setIcons(ValoresEnum.ICONS.EDIT);
            editIcon.setToolTipText("Editar");
            editIcon.setPreferredSize(new Dimension(20, 20));

            deleteIcon = new RSLabelIcon();
            deleteIcon.setIcons(ValoresEnum.ICONS.DELETE);
            deleteIcon.setToolTipText("Eliminar");
            deleteIcon.setPreferredSize(new Dimension(20, 20));

            add(editIcon);
            add(deleteIcon);

            updateTheme();
            TemaManager.getInstance().addThemeChangeListener(this::updateTheme);
        }

        private void updateTheme() {
            boolean oscuro = TemaManager.getInstance().isOscuro();
            Color fondo = oscuro ? new Color(21, 21, 33) : Color.WHITE;
            setBackground(fondo);
            editIcon.setBackground(oscuro ? new Color(67, 71, 120) : new Color(46, 49, 82));
            deleteIcon.setBackground(oscuro ? new Color(67, 71, 120) : new Color(46, 49, 82));
                    editIcon.setForeground(new Color(21, 21, 33)); // Green for edit icon

    deleteIcon.setForeground(new Color(29, 30, 81)); // Color azul que pediste
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                setBackground(new Color(240, 240, 240));
            } else {
                updateTheme();
            }
            return this;
        }
    }

    
    
    class CustomCheckboxRenderer extends JCheckBox implements TableCellRenderer {
    public CustomCheckboxRenderer() {
        setHorizontalAlignment(CENTER);
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        boolean oscuro = TemaManager.getInstance().isOscuro();
        Color fondo = oscuro ? new Color(21, 21, 33) : Color.WHITE;
        Color borde = oscuro ? new Color(100, 100, 150) : new Color(180, 180, 180);
        Color seleccion = oscuro ? new Color(118, 142, 240) : new Color(72, 92, 188);

        if (isSelected) {
            setBackground(new Color(240, 240, 240)); // Light gray for selection/hover
        } else {
            setBackground(fondo);
        }

        setBorderPainted(true);
        setForeground(seleccion);
        setSelected(Boolean.TRUE.equals(value));
        setBorder(javax.swing.BorderFactory.createLineBorder(borde));
        setOpaque(true); // Ensure background is painted

        return this;
    }


}

class CustomCheckboxEditor extends DefaultCellEditor {
    private final JCheckBox checkBox;

    public CustomCheckboxEditor() {
        super(new JCheckBox());
        checkBox = (JCheckBox) getComponent();

        checkBox.setOpaque(true);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {

        boolean oscuro = TemaManager.getInstance().isOscuro();
        checkBox.setBackground(oscuro ? new Color(21, 21, 33) : Color.WHITE);
        checkBox.setSelected(Boolean.TRUE.equals(value));
        return checkBox;
    }

    @Override
    public Object getCellEditorValue() {
        return checkBox.isSelected();
    }
}

    // In ButtonPanelEditor
class ButtonPanelEditor extends DefaultCellEditor {
        private JPanel panel;
        private RSLabelIcon editIcon;
        private RSLabelIcon deleteIcon;
        private String label;
        private boolean isPushed;
        private int selectedRow;

        public ButtonPanelEditor(JCheckBox checkBox) {
            super(checkBox);
            panel = new JPanel();
            panel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 2, 0));
            panel.setOpaque(true);

            editIcon = new RSLabelIcon();
            editIcon.setIcons(ValoresEnum.ICONS.EDIT);
            editIcon.setToolTipText("Editar");
            editIcon.setPreferredSize(new Dimension(20, 20));

            deleteIcon = new RSLabelIcon();
            deleteIcon.setIcons(ValoresEnum.ICONS.DELETE);
            deleteIcon.setToolTipText("Eliminar");
            deleteIcon.setPreferredSize(new Dimension(20, 20));

            panel.add(editIcon);
            panel.add(deleteIcon);
editIcon.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
        isPushed = true;
        fireEditingStopped();
        try {
            String codigoStr = tablaclientes.getValueAt(selectedRow, 1).toString(); // Ajusta la columna si es diferente
            if (codigoStr == null || codigoStr.trim().isEmpty()) {
                JOptionPane.showMessageDialog(VistaClientes.this, "Código de cliente no válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int codigo = Integer.parseInt(codigoStr);
            System.out.println("Intentando editar cliente con código: " + codigo);
            Cliente cliente = controlador.obtenerClientePorId(codigo);
            System.out.println("Cliente obtenido: " + (cliente != null ? cliente.getNombre() : "null"));
            if (cliente != null) {
                System.out.println("Departamento: " + cliente.getDepartamento() + ", Municipio: " + cliente.getMunicipio() + ", Dirección: " + cliente.getDireccion());
                EditarCliente2 dialog = new EditarCliente2((JFrame) SwingUtilities.getWindowAncestor(VistaClientes.this), true, cliente);
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
                if (dialog.isGuardado()) {
                    cargartablaclientes();
                    System.out.println("Cliente editado y tabla actualizada.");
                }
            } else {
                JOptionPane.showMessageDialog(VistaClientes.this, "No se encontró el cliente con código: " + codigo, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(VistaClientes.this, "El código del cliente no es un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(VistaClientes.this, "Error al editar el cliente: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
});

            deleteIcon.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    isPushed = true;
                    fireEditingStopped();
                    try {
                        int codigo = Integer.parseInt(tablaclientes.getValueAt(selectedRow, 1).toString());
                        Ctrl_Cliente controlador = new Ctrl_Cliente();

                        if (controlador.tienePedidos(codigo)) {
                            int opcion = JOptionPane.showConfirmDialog(VistaClientes.this,
                                    "Este cliente tiene pedidos asociados. ¿Desea marcarlo como inactivo en lugar de eliminarlo?",
                                    "Cliente con Pedidos", JOptionPane.YES_NO_OPTION);
                            if (opcion == JOptionPane.YES_OPTION) {
                                if (controlador.desactivar(codigo)) {
                                    JOptionPane.showMessageDialog(VistaClientes.this, "Cliente marcado como inactivo.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                                    cargartablaclientes();
                                } else {
                                    JOptionPane.showMessageDialog(VistaClientes.this, "Error al marcar como inactivo.", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        } else {
                            int confirm = JOptionPane.showConfirmDialog(VistaClientes.this,
                                    "¿Está seguro de que desea eliminar el cliente con código " + codigo + "?",
                                    "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                            if (confirm == JOptionPane.YES_OPTION) {
                                if (controlador.eliminar(codigo)) {
                                    JOptionPane.showMessageDialog(VistaClientes.this, "Cliente eliminado correctamente");
                                    cargartablaclientes();
                                } else {
                                    JOptionPane.showMessageDialog(VistaClientes.this, "Error al eliminar el cliente");
                                }
                            }
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(VistaClientes.this, "Error al eliminar el cliente: " + ex.getMessage());
                    }
                }
            });

            updateTheme();
            TemaManager.getInstance().addThemeChangeListener(this::updateTheme);
        }
        private void updateTheme() {
            boolean oscuro = TemaManager.getInstance().isOscuro();
            Color bgColor = oscuro ? new Color(21, 21, 33) : Color.WHITE;
            panel.setBackground(bgColor);
            editIcon.setBackground(oscuro ? new Color(67, 71, 120) : new Color(46, 49, 82));
            deleteIcon.setBackground(oscuro ? new Color(67, 71, 120) : new Color(46, 49, 82));
            editIcon.setForeground(Color.GREEN);
            deleteIcon.setForeground(Color.RED);
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            label = (value == null) ? "" : value.toString();
            isPushed = true;
            selectedRow = row;
            if (row == table.getSelectedRow()) {
                panel.setBackground(new Color(240, 240, 240));
            } else {
                updateTheme();
            }
            return panel;
        }

        @Override
        public Object getCellEditorValue() {
            return label;
        }

        @Override
        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }

        @Override
        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }
    public void aplicarTema() {
        boolean oscuro = TemaManager.getInstance().isOscuro();

        if (oscuro) {
            Color fondo = new Color(21, 21, 33);
            Color primario = new Color(40, 60, 150);
            Color texto = Color.WHITE;

            jPanel1.setBackground(fondo);
            txtBuscar.setBackground(fondo);
            txtBuscar.setForeground(texto);
            txtBuscar.setColorIcon(texto);
            txtBuscar.setPhColor(Color.LIGHT_GRAY);
            paginacion.setBackground(texto);

rSCheckBox1.setForeground(new Color(255, 255, 255));
 rSCheckBox1.setColorCheck(new Color(255, 255, 255));
  rSCheckBox1.setColorUnCheck(new Color(255, 255, 255));
rSButtonMaterialRippleIcon1.setBackground(new Color(21, 21, 33));
            tablaclientes.setBackground(new Color(21, 21, 33));
            tablaclientes.setBackgoundHead(new Color(67, 71, 120));
            tablaclientes.setForegroundHead(new Color(255, 255, 255));
            tablaclientes.setBackgoundHover(new Color(40, 50, 90));
            tablaclientes.setFont(new Font("Tahoma", Font.PLAIN, 15));
            tablaclientes.setColorPrimary(new Color(37, 37, 52));
            tablaclientes.setColorPrimaryText(texto);
            tablaclientes.setColorSecondary(new Color(30, 30, 45));
            tablaclientes.setColorSecundaryText(texto);
            tablaclientes.setColorBorderHead(primario);
            tablaclientes.setColorBorderRows(fondo.darker());
            tablaclientes.setFontHead(new Font("Tahoma", Font.BOLD, 15));
            tablaclientes.setFontRowHover(new Font("Tahoma", Font.BOLD, 15));
            tablaclientes.setFontRowSelect(new Font("Tahoma", Font.BOLD, 15));
            tablaclientes.setEffectHover(true);
            tablaclientes.setShowGrid(true);
            tablaclientes.setGridColor(Color.WHITE); // o el color que desees

            btnNuevo1.setBackground(new Color(67, 71, 120));
            btnNuevo1.setBackgroundHover(new Color(118, 142, 240));
           
        } else {
            Color fondo = new Color(242, 247, 255);
            Color texto = Color.BLACK;
            Color primario = new Color(72, 92, 188);
        paginacion.setBackground(texto);
        rSCheckBox1.setForeground(new Color(21, 21, 33));
 rSCheckBox1.setColorCheck(new Color(21, 21, 33));
  rSCheckBox1.setColorUnCheck(new Color(21, 21, 33));
            jPanel1.setBackground(fondo);
            txtBuscar.setBackground(fondo);
            txtBuscar.setForeground(texto);
            txtBuscar.setColorIcon(texto);
            txtBuscar.setPhColor(Color.GRAY);
rSButtonMaterialRippleIcon1.setBackground(new Color(242, 247, 255));

            tablaclientes.setBackground(new Color(255, 255, 255));
            tablaclientes.setBackgoundHead(new Color(46, 49, 82));
            tablaclientes.setForegroundHead(Color.WHITE);
            tablaclientes.setBackgoundHover(new Color(67, 150, 209));
            tablaclientes.setFont(new Font("Tahoma", Font.PLAIN, 15));
            tablaclientes.setColorPrimary(new Color(242, 242, 242));
            tablaclientes.setColorPrimaryText(texto);
            tablaclientes.setColorSecondary(new Color(255, 255, 255));
            tablaclientes.setColorSecundaryText(texto);
            tablaclientes.setColorBorderHead(primario);
            tablaclientes.setColorBorderRows(new Color(0, 0, 0));
            tablaclientes.setFontHead(new Font("Tahoma", Font.BOLD, 15));
            tablaclientes.setFontRowHover(new Font("Tahoma", Font.BOLD, 15));
            tablaclientes.setFontRowSelect(new Font("Tahoma", Font.BOLD, 15));
            tablaclientes.setEffectHover(true);
            tablaclientes.setSelectionBackground(new Color(67, 150, 209));
            tablaclientes.setShowGrid(true);
            tablaclientes.setGridColor(Color.BLACK); // o el color que desees

            btnNuevo1.setBackground(new Color(46, 49, 82));
           
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

        jPanel1 = new javax.swing.JPanel();
        btnNuevo1 = new RSMaterialComponent.RSButtonShape();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaclientes = new RSMaterialComponent.RSTableMetroCustom();
        txtBuscar = new RSMaterialComponent.RSTextFieldMaterialIcon();
        rSCheckBox1 = new rojerusan.RSCheckBox();
        Añadir5 = new rojeru_san.RSButtonRiple();
        Añadir4 = new rojeru_san.RSButtonRiple();
        rSButtonMaterialRippleIcon1 = new RSMaterialComponent.RSButtonMaterialRippleIcon();
        paginacion = new javax.swing.JLabel();
        btnNotificacion1 = new rojerusan.RSLabelIcon();

        setPreferredSize(new java.awt.Dimension(1290, 730));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(18, 18, 18));
        jPanel1.setPreferredSize(new java.awt.Dimension(960, 570));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnNuevo1.setBackground(new java.awt.Color(67, 94, 190));
        btnNuevo1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        btnNuevo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plus (2).png"))); // NOI18N
        btnNuevo1.setText(" Nuevo");
        btnNuevo1.setBackgroundHover(new java.awt.Color(118, 142, 240));
        btnNuevo1.setFocusable(false);
        btnNuevo1.setFont(new java.awt.Font("Roboto Bold", 1, 16)); // NOI18N
        btnNuevo1.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        btnNuevo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnNuevo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevo1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnNuevo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 60, 110, 30));

        tablaclientes.setForeground(new java.awt.Color(153, 0, 204));
        tablaclientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Seleccionar", "Codigo", "Doc.Tipo", "Nombre", "Apellido", "Telefono", "Departamento/municipio", "Direccion", "Estado", "Acciones"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, true, true, true, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaclientes.setToolTipText("");
        tablaclientes.setBackgoundHead(new java.awt.Color(44, 44, 44));
        tablaclientes.setBackgoundHover(new java.awt.Color(51, 255, 51));
        tablaclientes.setBorderHead(null);
        tablaclientes.setBorderRows(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        tablaclientes.setColorBorderHead(new java.awt.Color(102, 102, 255));
        tablaclientes.setColorBorderRows(new java.awt.Color(255, 102, 102));
        tablaclientes.setColorPrimary(new java.awt.Color(37, 37, 52));
        tablaclientes.setColorPrimaryText(new java.awt.Color(0, 0, 0));
        tablaclientes.setColorSecondary(new java.awt.Color(0, 204, 153));
        tablaclientes.setColorSecundaryText(new java.awt.Color(30, 30, 45));
        tablaclientes.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        tablaclientes.setFontHead(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        tablaclientes.setFontRowHover(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        tablaclientes.setFontRowSelect(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        tablaclientes.setForegroundHead(new java.awt.Color(224, 224, 224));
        tablaclientes.setGridColor(new java.awt.Color(102, 255, 102));
        tablaclientes.setPreferredSize(new java.awt.Dimension(500, 500));
        tablaclientes.setSelectionBackground(new java.awt.Color(67, 150, 209));
        jScrollPane3.setViewportView(tablaclientes);
        tablaclientes.getColumnModel().getColumn(0).setPreferredWidth(10);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 1180, 520));

        txtBuscar.setBackground(new java.awt.Color(242, 247, 255));
        txtBuscar.setForeground(new java.awt.Color(0, 0, 0));
        txtBuscar.setColorIcon(new java.awt.Color(0, 0, 0));
        txtBuscar.setColorMaterial(new java.awt.Color(153, 153, 153));
        txtBuscar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SEARCH);
        txtBuscar.setPhColor(new java.awt.Color(102, 102, 102));
        txtBuscar.setPlaceholder("Buscar...");
        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });
        jPanel1.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, 430, 40));

        rSCheckBox1.setForeground(new java.awt.Color(102, 102, 255));
        rSCheckBox1.setText("Seleccionar Todo");
        jPanel1.add(rSCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 680, 190, 20));

        Añadir5.setBackground(new java.awt.Color(46, 49, 82));
        Añadir5.setText("Anterior");
        Añadir5.setColorHover(new java.awt.Color(0, 153, 51));
        Añadir5.setFont(new java.awt.Font("Humnst777 BlkCn BT", 1, 14)); // NOI18N
        Añadir5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Añadir5ActionPerformed(evt);
            }
        });
        jPanel1.add(Añadir5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 680, 90, 40));

        Añadir4.setBackground(new java.awt.Color(46, 49, 82));
        Añadir4.setText("Siguiente");
        Añadir4.setColorHover(new java.awt.Color(0, 153, 51));
        Añadir4.setFont(new java.awt.Font("Humnst777 BlkCn BT", 1, 14)); // NOI18N
        Añadir4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Añadir4ActionPerformed(evt);
            }
        });
        jPanel1.add(Añadir4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 680, 98, 40));

        rSButtonMaterialRippleIcon1.setBackground(new java.awt.Color(102, 102, 102));
        rSButtonMaterialRippleIcon1.setForeground(new java.awt.Color(253, 126, 20));
        rSButtonMaterialRippleIcon1.setBackgroundHover(new java.awt.Color(242, 247, 255));
        rSButtonMaterialRippleIcon1.setForegroundHover(new java.awt.Color(255, 51, 51));
        rSButtonMaterialRippleIcon1.setForegroundIcon(new java.awt.Color(255, 51, 51));
        rSButtonMaterialRippleIcon1.setForegroundIconHover(new java.awt.Color(255, 51, 51));
        rSButtonMaterialRippleIcon1.setForegroundText(new java.awt.Color(255, 51, 51));
        rSButtonMaterialRippleIcon1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.DELETE);
        rSButtonMaterialRippleIcon1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMaterialRippleIcon1ActionPerformed(evt);
            }
        });
        jPanel1.add(rSButtonMaterialRippleIcon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 680, 40, 40));

        paginacion.setBackground(new java.awt.Color(0, 0, 0));
        paginacion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        paginacion.setForeground(new java.awt.Color(51, 51, 51));
        paginacion.setText("Escritorio");
        paginacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                paginacionMouseClicked(evt);
            }
        });
        jPanel1.add(paginacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 680, -1, -1));

        btnNotificacion1.setBackground(new java.awt.Color(255, 255, 255));
        btnNotificacion1.setForeground(new java.awt.Color(255, 255, 255));
        btnNotificacion1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.TUNE);
        btnNotificacion1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNotificacion1MouseClicked(evt);
            }
        });
        jPanel1.add(btnNotificacion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 60, -1, -1));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1290, 750));
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo1ActionPerformed
      crear_cliente dialog = new crear_cliente(new javax.swing.JFrame(), true);
    dialog.setLocationRelativeTo(null);
    dialog.setVisible(true);

    if (dialog.isGuardado()) {
        cargartablaclientes(); // Use the correct method with all columns
    }
    }//GEN-LAST:event_btnNuevo1ActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
    String textoBusqueda = txtBuscar.getText().trim();
    if (textoBusqueda.isEmpty()) {
        todasLasClientes = new ArrayList<>(controlador.obtenerClientes());
        currentPage = 0;
        seleccionados = new boolean[todasLasClientes.size()];
        mostrarPagina(currentPage);
        return;
    }

    try {
        int codigo = Integer.parseInt(textoBusqueda);
        Cliente cliente = controlador.buscarClientePorCodigo(codigo);
        if (cliente != null) {
            todasLasClientes = new ArrayList<>();
            todasLasClientes.add(cliente);
            seleccionados = new boolean[todasLasClientes.size()];
            currentPage = 0;
            mostrarPagina(currentPage);
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró cliente con código: " + codigo, "No encontrado", JOptionPane.WARNING_MESSAGE);
        }
    } catch (NumberFormatException e) {
        List<Cliente> resultados = controlador.buscarClientePorNombre(textoBusqueda);
        if (!resultados.isEmpty()) {
            todasLasClientes = new ArrayList<>(resultados);
            seleccionados = new boolean[todasLasClientes.size()];
            currentPage = 0;
            mostrarPagina(currentPage);
        } else {
            JOptionPane.showMessageDialog(this, "No se encontraron clientes con el nombre: " + textoBusqueda, "No encontrado", JOptionPane.WARNING_MESSAGE);
            todasLasClientes = new ArrayList<>();
            seleccionados = new boolean[todasLasClientes.size()];
            mostrarPagina(currentPage);
        }
    }

    }//GEN-LAST:event_txtBuscarActionPerformed

    private void Añadir5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Añadir5ActionPerformed
 if (currentPage > 0) {
            currentPage--;
            mostrarPagina(currentPage);
        }
    }//GEN-LAST:event_Añadir5ActionPerformed

    private void Añadir4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Añadir4ActionPerformed
  int totalPaginas = (int) Math.ceil((double) todasLasClientes.size() / CLIENTES_POR_PAGINA);
        if (currentPage < totalPaginas - 1) {
            currentPage++;
            mostrarPagina(currentPage);
        }
    }//GEN-LAST:event_Añadir4ActionPerformed

    private void rSButtonMaterialRippleIcon1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMaterialRippleIcon1ActionPerformed
    List<Integer> idsAEliminar = new ArrayList<>();
        List<String> nombresClientesAEliminar = new ArrayList<>();
        List<Integer> clientesConPedidos = new ArrayList<>();
        List<String> clientesConPedidosNombres = new ArrayList<>();

        // Collect selected clients
        DefaultTableModel model = (DefaultTableModel) tablaclientes.getModel();
        int inicio = currentPage * CLIENTES_POR_PAGINA;
        for (int i = 0; i < model.getRowCount(); i++) {
            if (Boolean.TRUE.equals(model.getValueAt(i, 0))) {
                int id = Integer.parseInt(model.getValueAt(i, 1).toString());
                String nombre = model.getValueAt(i, 3).toString() + " " + model.getValueAt(i, 4).toString();
                if (controlador.tienePedidos(id)) {
                    clientesConPedidos.add(id);
                    clientesConPedidosNombres.add(nombre);
                } else {
                    idsAEliminar.add(id);
                    nombresClientesAEliminar.add(nombre);
                }
                seleccionados[inicio + i] = false; // Deselect
            }
        }

        // Handle clients with associated orders
        if (!clientesConPedidos.isEmpty()) {
            String mensaje = "No se pueden eliminar los siguientes clientes porque tienen pedidos asociados:\n" +
                    String.join(", ", clientesConPedidosNombres) +
                    "\n¿Desea marcarlos como inactivos?";
            int opcion = JOptionPane.showConfirmDialog(this, mensaje, "Clientes con Pedidos", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                for (Integer id : clientesConPedidos) {
                    if (controlador.desactivar(id)) {
                        JOptionPane.showMessageDialog(this, "Cliente(s) marcados como inactivo(s).", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al marcar cliente(s) como inactivo(s).", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }

        // Handle deletion of clients without orders
        if (!idsAEliminar.isEmpty()) {
            String mensaje = "¿Está seguro que desea eliminar " +
                    (idsAEliminar.size() > 1 ? "estos " + idsAEliminar.size() + " clientes?" : "este cliente?") +
                    "\nClientes: " + String.join(", ", nombresClientesAEliminar);
            int confirm = JOptionPane.showConfirmDialog(this, mensaje, "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                boolean todasEliminadas = true;
                for (Integer id : idsAEliminar) {
                    if (!controlador.eliminar(id)) {
                        todasEliminadas = false;
                        JOptionPane.showMessageDialog(this, "Error al eliminar el cliente con ID: " + id);
                    }
                }
                if (todasEliminadas) {
                    JOptionPane.showMessageDialog(this, "Cliente(s) eliminado(s) correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                }
                todasLasClientes.removeIf(cliente -> idsAEliminar.contains(cliente.getId_cliente()));
                mostrarPagina(currentPage);
            }
        } else if (clientesConPedidos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay clientes seleccionados para eliminar.", "Sin selección", JOptionPane.WARNING_MESSAGE);
        }

        rSCheckBox1.setSelected(false); // Reset "Seleccionar Todo"
    
    }//GEN-LAST:event_rSButtonMaterialRippleIcon1ActionPerformed

    private void paginacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paginacionMouseClicked
        // TODO add your handling code here: 777777
    }//GEN-LAST:event_paginacionMouseClicked

    private void btnNotificacion1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNotificacion1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNotificacion1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSButtonRiple Añadir4;
    private rojeru_san.RSButtonRiple Añadir5;
    private rojerusan.RSLabelIcon btnNotificacion1;
    private RSMaterialComponent.RSButtonShape btnNuevo1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel paginacion;
    private RSMaterialComponent.RSButtonMaterialRippleIcon rSButtonMaterialRippleIcon1;
    private rojerusan.RSCheckBox rSCheckBox1;
    private RSMaterialComponent.RSTableMetroCustom tablaclientes;
    private RSMaterialComponent.RSTextFieldMaterialIcon txtBuscar;
    // End of variables declaration//GEN-END:variables
    
public void cargartablacliente() {
    tablaclientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    DefaultTableModel model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return column == 0 || column == 9; // Solo "Seleccionar" y "Acciones" son editables
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return columnIndex == 0 ? Boolean.class : String.class; // Checkbox en la primera columna
        }
    };

    // Definir columnas
    model.addColumn("Seleccionar"); // Checkbox
    model.addColumn("Código");
    model.addColumn("Doc. Tipo");
    model.addColumn("Nombre");
    model.addColumn("Apellido");
    model.addColumn("Teléfono");
    model.addColumn("Departamento/Municipio");
    model.addColumn("Dirección");
    model.addColumn("Estado");
    model.addColumn("Acciones");

    // Obtener clientes y llenar la tabla
    List<Cliente> clientes = controlador.obtenerClientes();
    for (Cliente cliente : clientes) {
        Object[] fila = new Object[10];
        fila[0] = false; // Checkbox no seleccionado
        fila[1] = String.valueOf(cliente.getId_cliente()); // Código
        fila[2] = cliente.getIdentificacion() != null ? cliente.getIdentificacion() : "Sin identificación";
        fila[3] = cliente.getNombre() != null ? cliente.getNombre() : "Sin nombre";
        fila[4] = cliente.getApellido() != null ? cliente.getApellido() : "Sin apellido";
        fila[5] = cliente.getTelefono() != null ? cliente.getTelefono() : "Sin teléfono";
        String departamento = cliente.getDepartamento() != null ? cliente.getDepartamento() : "";
        String municipio = cliente.getMunicipio() != null ? cliente.getMunicipio() : "";
        fila[6] = (!departamento.isEmpty() || !municipio.isEmpty()) ? departamento + " / " + municipio : "Sin ubicación";
        fila[7] = cliente.getDireccion() != null ? cliente.getDireccion() : "Sin dirección";
        fila[8] = cliente.isActivo() ? "Activo" : "Inactivo"; // Estado
        fila[9] = ""; // Acciones
        model.addRow(fila);
    }

    // Asignar el modelo a la tabla
    tablaclientes.setModel(model);

    // Configurar renderers y editores
    tablaclientes.getColumnModel().getColumn(9).setCellRenderer(new ButtonPanelRenderer());
    tablaclientes.getColumnModel().getColumn(9).setCellEditor(new ButtonPanelEditor(new JCheckBox()));

    // Ajustar anchos de columnas
    tablaclientes.getColumnModel().getColumn(0).setPreferredWidth(60);  // Seleccionar
    tablaclientes.getColumnModel().getColumn(1).setPreferredWidth(70);  // Código
    tablaclientes.getColumnModel().getColumn(2).setPreferredWidth(70);  // Tipo de identificación
    tablaclientes.getColumnModel().getColumn(3).setPreferredWidth(100); // Nombre
    tablaclientes.getColumnModel().getColumn(4).setPreferredWidth(100); // Apellido
    tablaclientes.getColumnModel().getColumn(5).setPreferredWidth(80);  // Teléfono
    tablaclientes.getColumnModel().getColumn(6).setPreferredWidth(150); // Departamento/Municipio
    tablaclientes.getColumnModel().getColumn(7).setPreferredWidth(160); // Dirección
    tablaclientes.getColumnModel().getColumn(8).setPreferredWidth(70);  // Estado
    tablaclientes.getColumnModel().getColumn(9).setPreferredWidth(80);  // Acciones
    tablaclientes.setRowHeight(24);

    // Listener para capturar la selección de una fila
    tablaclientes.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int fila_point = tablaclientes.rowAtPoint(e.getPoint());
            if (fila_point > -1) {
                id_cliente = (int) tablaclientes.getValueAt(fila_point, 1);
            }
        }
    });
}

}
