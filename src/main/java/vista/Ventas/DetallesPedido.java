/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista.Ventas;

import controlador.Ctrl_Cliente;
import controlador.Ctrl_Pedido;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import modelo.Cliente;
import modelo.Conexion;
import modelo.Pedido;
import modelo.PedidoDetalle;
import vista.TemaManager;

/**
 *
 * @author ZenBook
 */
public class DetallesPedido extends javax.swing.JPanel {

    private JPanel contenedor;
    private Ctrl_Pedido controlador;
    private int idPedido;
    private boolean editando = false;
    public boolean clienteGuardado = false;
    public Cliente cliente;
    private List<Cliente> clientes;

    public DetallesPedido(String codigo, JPanel contenedor) {
        this.contenedor = contenedor;
        this.controlador = new Ctrl_Pedido();
        try {
            this.idPedido = Integer.parseInt(codigo); // Convertir el código a int (id_pedido)
        } catch (NumberFormatException e) {
            this.idPedido = -1; // En caso de error, marcamos un ID inválido
        }
        initComponents();
        aplicarTema(); // Apply initial theme

        // Register for theme changes
        TemaManager.getInstance().addThemeChangeListener(() -> {
            aplicarTema(); // Update theme when it changes
        });
        cargarClientes();
        cargarDatosPedido();

        tablaDetalles.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        TableColumn descripcionColumn = tablaDetalles.getColumnModel().getColumn(0);
        descripcionColumn.setPreferredWidth(43); // Ajustar el ancho de la columna

        TableColumn cantidadColumn = tablaDetalles.getColumnModel().getColumn(1);
        cantidadColumn.setPreferredWidth(16); // Ajustar el ancho de la columna

        TableColumn dimensionColumn = tablaDetalles.getColumnModel().getColumn(2);
        dimensionColumn.setPreferredWidth(30); // Ajustar el ancho de la columna

        TableColumn preciouColumn = tablaDetalles.getColumnModel().getColumn(3);
        preciouColumn.setPreferredWidth(30); // Ajustar el ancho de la columna

        TableColumn subtotalColumn = tablaDetalles.getColumnModel().getColumn(4);
        subtotalColumn.setPreferredWidth(30); // Ajustar el ancho de la columna

        // Configurar la columna "Detalle"
        TableColumn deleteColumn = tablaDetalles.getColumnModel().getColumn(5);
        deleteColumn.setPreferredWidth(18); // Ajustar el ancho de la columna

        cargarDatosPedido(); // Cargar los datos al inicializar

        deshabilitarEdicion();

        // Agregar listener para actualizar subtotal
        tablaDetalles.getModel().addTableModelListener(e -> {
            if (e.getColumn() == 1 || e.getColumn() == 3) { // Si se edita "Cantidad" o "Precio unitario"
                int row = e.getFirstRow();
                DefaultTableModel tableModel = (DefaultTableModel) tablaDetalles.getModel();

                try {
                    Object cantidadObj = tableModel.getValueAt(row, 1);
                    Object precioUnitarioObj = tableModel.getValueAt(row, 3);

                    int cantidad = (cantidadObj != null) ? Integer.parseInt(cantidadObj.toString().trim().isEmpty() ? "0" : cantidadObj.toString()) : 0;
                    double precioUnitario = (precioUnitarioObj != null) ? Double.parseDouble(precioUnitarioObj.toString().trim().replace("$", "").replace(".", "")) : 0.0;

                    double subtotal = cantidad * precioUnitario;
                    // Formatear subtotal con signo de pesos y sin decimales si son .00
                    DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
                    symbols.setGroupingSeparator('.'); // Usar punto como separador de miles
                    DecimalFormat df = new DecimalFormat("$#,##0.##", symbols);
                    tableModel.setValueAt(df.format(subtotal), row, 4);

                    // Actualizar total
                    actualizarTotal();
                } catch (NumberFormatException ex) {
                    tableModel.setValueAt("$0", row, 4);
                    actualizarTotal();
                }
            }
        });

    }

    private void cargarDatosPedido() {
        if (idPedido == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Código de pedido inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Ctrl_Pedido.MaterialConDetalles material = controlador.obtenerPedidoPorId(idPedido);
        if (material == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "No se encontró el pedido con ID: " + idPedido, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Pedido pedido = material.getPedido();
        String nombreCliente = material.getNombreCliente();

        // Depuración: Verificar valores
        System.out.println("Nombre del cliente: " + nombreCliente);
        System.out.println("Estado del pedido: " + pedido.getEstado());

        // Llenar las etiquetas con los datos del pedido
        lblNumeroPedido.setText(String.valueOf(pedido.getId_pedido()));
        txtNombrePedido.setText(pedido.getNombre());

        String estado = pedido.getEstado();
        if (estado != null && !estado.isEmpty()) {
            // Normalizar el estado para que coincida con los ítems de cmbEstado
            estado = estado.trim();
            String estadoNormalizado = estado.substring(0, 1).toUpperCase() + estado.substring(1).toLowerCase();
            System.out.println("Estado normalizado: " + estadoNormalizado);
            boolean estadoEncontrado = false;
            for (int i = 0; i < cmbEstado.getItemCount(); i++) {
                String item = (String) cmbEstado.getItemAt(i);
                if (item.equalsIgnoreCase(estadoNormalizado)) {
                    cmbEstado.setSelectedIndex(i);
                    estadoEncontrado = true;
                    break;
                }
            }
            if (!estadoEncontrado) {
                System.out.println("Estado '" + estadoNormalizado + "' no encontrado en el JComboBox.");
                cmbEstado.setSelectedIndex(0); // Seleccionar el primer ítem por defecto
            }
        } else {
            System.out.println("Estado es nulo o vacío.");
            cmbEstado.setSelectedIndex(0); // Seleccionar el primer ítem por defecto
        }

        // Seleccionar el cliente
        if (nombreCliente != null && !nombreCliente.isEmpty()) {
            // Buscar el cliente en la lista para asegurar coincidencia exacta
            boolean clienteEncontrado = false;
            for (int i = 0; i < cmbCliente.getItemCount(); i++) {
                String item = (String) cmbCliente.getItemAt(i);
                if (item.equals(nombreCliente)) {
                    cmbCliente.setSelectedIndex(i);
                    clienteEncontrado = true;
                    break;
                }
            }
            if (!clienteEncontrado) {
                System.out.println("Cliente '" + nombreCliente + "' no encontrado en el JComboBox.");
                cmbCliente.setSelectedIndex(0); // Seleccionar "Seleccione cliente:"
            }
        } else {
            cmbCliente.setSelectedIndex(0); // Seleccionar "Seleccione cliente:"
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFechaInicio.setDate(pedido.getFecha_inicio());
        dateFechaFin.setDate(pedido.getFecha_fin());

        // Cargar los detalles en la tabla
        DefaultTableModel model = (DefaultTableModel) tablaDetalles.getModel();
        model.setRowCount(0); // Limpiar la tabla

        List<PedidoDetalle> detalles = controlador.obtenerDetallesPorPedido(idPedido);
        double total = 0.0;

        // Formatear valores sin decimales si son .00
        // Formatear valores con signo de pesos y sin decimales si son .00
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        symbols.setGroupingSeparator('.'); // Usar punto como separador de miles
        DecimalFormat df = new DecimalFormat("$#,##0.##", symbols);

        for (PedidoDetalle detalle : detalles) {
            model.addRow(new Object[]{
                detalle.getDescripcion(),
                detalle.getCantidad(),
                detalle.getDimensiones(),
                df.format(detalle.getPrecioUnitario()),
                df.format(detalle.getSubtotal()),
                "Eliminar" // Mantener la columna de acción
            });
            total += detalle.getSubtotal();
        }

        // Mostrar el total
        lblTotal.setText(df.format(total));
    }

    public void aplicarTema() {
        boolean oscuro = TemaManager.getInstance().isOscuro();

        if (oscuro) {
            Color fondo = new Color(21, 21, 33);
            Color primario = new Color(40, 60, 150);
            Color texto = Color.WHITE;
            jPanel2.setBackground(fondo);
            jPanel2.setForeground(new Color(255, 255, 255));
            cmbCliente.setBackground(fondo);
            cmbCliente.setForeground(new Color(255, 255, 255));
            txtNombrePedido.setBackground(fondo);
            txtNombrePedido.setForeground(new Color(255, 255, 255));
            dateFechaInicio.setBackground(fondo);
            dateFechaInicio.setForeground(new Color(255, 255, 255));
            dateFechaFin.setBackground(fondo);
            dateFechaFin.setForeground(new Color(255, 255, 255));
            cmbEstado.setBackground(fondo);
            cmbEstado.setForeground(new Color(255, 255, 255));
            jPanel1.setBackground(new Color(46, 49, 82));
            jLabel11.setFont(new Font("Segoe UI", Font.BOLD, 18));
            jLabel11.setForeground(new Color(255, 255, 255));
            jLabel19.setFont(new Font("Segoe UI", Font.BOLD, 18));
            jLabel19.setForeground(new Color(255, 255, 255));
            jLabel9.setFont(new Font("Segoe UI", Font.BOLD, 18));
            jLabel9.setForeground(new Color(255, 255, 255));
            jLabel5.setFont(new Font("Segoe UI", Font.BOLD, 18));
            jLabel5.setForeground(new Color(255, 255, 255));
            jLabel8.setFont(new Font("Segoe UI", Font.BOLD, 18));
            jLabel8.setForeground(new Color(255, 255, 255));
            jLabel17.setFont(new Font("Segoe UI", Font.BOLD, 18));
            jLabel17.setForeground(new Color(255, 255, 255));
            jLabel16.setFont(new Font("Segoe UI", Font.BOLD, 18));
            jLabel16.setForeground(new Color(255, 255, 255));
            lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 18));
            lblTotal.setForeground(new Color(255, 255, 255));
            jLabel3.setFont(new Font("Segoe UI", Font.BOLD, 18));
            jLabel3.setForeground(new Color(255, 255, 255));
            lblNumeroPedido.setFont(new Font("Segoe UI", Font.BOLD, 18));
            lblNumeroPedido.setForeground(new Color(255, 255, 255));
            tablaDetalles.setBackground(new Color(21, 21, 33));
            tablaDetalles.setBackgoundHead(new Color(67, 71, 120));
            tablaDetalles.setForegroundHead(new Color(255, 255, 255));
            tablaDetalles.setBackgoundHover(new Color(109, 160, 221));
            tablaDetalles.setFont(new Font("Tahoma", Font.PLAIN, 15));
            tablaDetalles.setColorPrimary(new Color(37, 37, 52));
            tablaDetalles.setColorPrimaryText(texto);
            tablaDetalles.setColorSecondary(new Color(30, 30, 45));
            tablaDetalles.setColorSecundaryText(texto);
            tablaDetalles.setColorBorderHead(primario);
            tablaDetalles.setColorBorderRows(fondo.darker());
            tablaDetalles.setFontHead(new Font("Tahoma", Font.BOLD, 15));
            tablaDetalles.setFontRowHover(new Font("Tahoma", Font.BOLD, 15));
            tablaDetalles.setFontRowSelect(new Font("Tahoma", Font.BOLD, 15));
            tablaDetalles.setEffectHover(true);
            tablaDetalles.setShowGrid(true);
            btnVolver.setBackground(new Color(67, 71, 120));
            btnVolver.setBackgroundHover(new Color(118, 142, 240));

        } else {
            Color fondo = new Color(242, 247, 255);
            Color texto = Color.BLACK;
            Color primario = new Color(72, 92, 188);

            jPanel1.setBackground(fondo);
            btnVolver.setBackground(fondo);

            jPanel2.setBackground(fondo);
            jPanel2.setForeground(fondo);
            cmbCliente.setBackground(fondo);
            cmbCliente.setForeground(new Color(0, 0, 0));
            txtNombrePedido.setBackground(fondo);
            txtNombrePedido.setForeground(new Color(0, 0, 0));
            dateFechaInicio.setBackground(fondo);
            dateFechaInicio.setForeground(new Color(0, 0, 0));
            dateFechaFin.setBackground(fondo);
            dateFechaFin.setForeground(new Color(0, 0, 0));
            cmbEstado.setBackground(fondo);
            cmbEstado.setForeground(new Color(0, 0, 0));
            jPanel1.setBackground(new Color(240, 240, 240));
            jLabel11.setFont(new Font("Segoe UI", Font.BOLD, 18));
            jLabel11.setForeground(new Color(0, 0, 0));
            jLabel19.setFont(new Font("Segoe UI", Font.BOLD, 18));
            jLabel19.setForeground(new Color(0, 0, 0));
            jLabel9.setFont(new Font("Segoe UI", Font.BOLD, 18));
            jLabel9.setForeground(new Color(0, 0, 0));
            jLabel5.setFont(new Font("Segoe UI", Font.BOLD, 18));
            jLabel5.setForeground(new Color(0, 0, 0));
            jLabel8.setFont(new Font("Segoe UI", Font.BOLD, 18));
            jLabel8.setForeground(new Color(0, 0, 0));
            jLabel17.setFont(new Font("Segoe UI", Font.BOLD, 18));
            jLabel17.setForeground(new Color(0, 0, 0));
            jLabel16.setFont(new Font("Segoe UI", Font.BOLD, 18));
            jLabel16.setForeground(new Color(0, 0, 0));
            lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 18));
            lblTotal.setForeground(new Color(0, 0, 0));
            jLabel3.setFont(new Font("Segoe UI", Font.BOLD, 18));
            jLabel3.setForeground(new Color(0, 0, 0));
            lblNumeroPedido.setFont(new Font("Segoe UI", Font.BOLD, 18));
            lblNumeroPedido.setForeground(new Color(0, 0, 0));

            tablaDetalles.setBackground(new Color(255, 255, 255));
            tablaDetalles.setBackgoundHead(new Color(46, 49, 82));
            tablaDetalles.setForegroundHead(Color.WHITE);
            tablaDetalles.setBackgoundHover(new Color(67, 150, 209));
            tablaDetalles.setFont(new Font("Tahoma", Font.PLAIN, 15));
            tablaDetalles.setColorPrimary(new Color(242, 242, 242));
            tablaDetalles.setColorPrimaryText(texto);
            tablaDetalles.setColorSecondary(new Color(255, 255, 255));
            tablaDetalles.setColorSecundaryText(texto);
            tablaDetalles.setColorBorderHead(primario);
            tablaDetalles.setColorBorderRows(new Color(0, 0, 0));
            tablaDetalles.setFontHead(new Font("Tahoma", Font.BOLD, 15));
            tablaDetalles.setFontRowHover(new Font("Tahoma", Font.BOLD, 15));
            tablaDetalles.setFontRowSelect(new Font("Tahoma", Font.BOLD, 15));
            tablaDetalles.setEffectHover(true);
            tablaDetalles.setSelectionBackground(new Color(67, 150, 209));
            tablaDetalles.setShowGrid(true);
            tablaDetalles.setGridColor(Color.BLACK); // o el color que desees
            btnVolver.setBackground(new Color(46, 49, 82));
        }

    }

    private void actualizarTotal() {
        DefaultTableModel model = (DefaultTableModel) tablaDetalles.getModel();
        double total = 0.0;

        for (int i = 0; i < model.getRowCount(); i++) {
            try {
                double subtotal = Double.parseDouble(model.getValueAt(i, 4).toString().replace("$", "").replace(".", "")); // Reemplazar signo de pesos y puntos para parsear
                total += subtotal;
            } catch (NumberFormatException e) {
                // Ignorar filas con valores inválidos
            }
        }

        // Formatear total con signo de pesos y sin decimales si son .00
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        symbols.setGroupingSeparator('.'); // Usar punto como separador de miles
        DecimalFormat df = new DecimalFormat("$#,##0.##", symbols);
        lblTotal.setText(df.format(total));
    }

    private void deshabilitarEdicion() {
        txtNombrePedido.setEnabled(false);
        cmbEstado.setEnabled(false);
        cmbCliente.setEnabled(false);
        dateFechaInicio.setEnabled(false);
        dateFechaFin.setEnabled(false);
        tablaDetalles.setEnabled(false);
        btnAñadir.setVisible(false);
        btnGuardar.setVisible(false);
    }

    private void habilitarEdicion() {
        txtNombrePedido.setEnabled(true);
        cmbEstado.setEnabled(true);
        cmbCliente.setEnabled(true); // Cliente no editable por ahora
        dateFechaInicio.setEnabled(true);
        dateFechaFin.setEnabled(true);
        tablaDetalles.setEnabled(true);
        btnAñadir.setVisible(true);
        btnGuardar.setVisible(true);
    }

    private void guardarCambios() {
        Pedido pedido = new Pedido();
        pedido.setId_pedido(idPedido);
        pedido.setNombre(txtNombrePedido.getText().trim());

        // Obtener y validar el estado
        String estado = (String) cmbEstado.getSelectedItem();
        if (estado == null || estado.isEmpty() || estado.equals("Seleccione estado:")) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un estado válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        pedido.setEstado(estado);

        // Obtener y validar las fechas
        java.util.Date fechaInicio = dateFechaInicio.getDate();
        java.util.Date fechaFin = dateFechaFin.getDate();
        if (fechaInicio == null) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione una fecha de inicio válida.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (fechaFin == null) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione una fecha de fin válida.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        pedido.setFecha_inicio(fechaInicio);
        pedido.setFecha_fin(fechaFin);

        // Obtener cliente_codigo basado en el cliente seleccionado
        String clienteSeleccionado = (String) cmbCliente.getSelectedItem();
        if (clienteSeleccionado == null || clienteSeleccionado.equals("Seleccione cliente:")) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un cliente válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Cliente clienteSeleccionadoObj = null;
        for (Cliente cli : clientes) {
            String nombreCompleto = cli.getNombre() + " " + cli.getApellido();
            if (nombreCompleto.equals(clienteSeleccionado)) {
                clienteSeleccionadoObj = cli;
                break;
            }
        }

        if (clienteSeleccionadoObj != null) {
//            pedido.setIdCliente(clienteSeleccionadoObj.getid_cliente());
        } else {
            JOptionPane.showMessageDialog(this, "Error al obtener el cliente seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Obtener usuario_id_usuario (esto debería venir de la base de datos original si es necesario)
        Ctrl_Pedido.MaterialConDetalles materialOriginal = controlador.obtenerPedidoPorId(idPedido);
        if (materialOriginal != null) {
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo obtener los datos originales del pedido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Obtener detalles de la tabla
        DefaultTableModel model = (DefaultTableModel) tablaDetalles.getModel();
        List<PedidoDetalle> detalles = new java.util.ArrayList<>();

        for (int i = 0; i < model.getRowCount(); i++) {
            // Descripción (ya es String)
            String descripcion = model.getValueAt(i, 0).toString();

            // Cantidad
            Object cantidadObj = model.getValueAt(i, 1);
            int cantidad = 0;
            try {
                cantidad = (cantidadObj != null)
                        ? (cantidadObj instanceof Number ? ((Number) cantidadObj).intValue()
                                : Integer.parseInt(cantidadObj.toString())) : 0;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Cantidad inválida en fila " + (i + 1), "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Dimensiones
            String medida = model.getValueAt(i, 2).toString();

            // Precio unitario
            Object precioUnitarioObj = model.getValueAt(i, 3);
            double precioUnitario = 0.0;
            try {
                precioUnitario = (precioUnitarioObj != null)
                        ? (precioUnitarioObj instanceof Number ? ((Number) precioUnitarioObj).doubleValue()
                                : Double.parseDouble(precioUnitarioObj.toString().replace("$", "").replace(".", ""))) : 0.0;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Precio unitario inválido en fila " + (i + 1), "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Subtotal
            Object subtotalObj = model.getValueAt(i, 4);
            double subtotal = 0.0;
            try {
                subtotal = (subtotalObj != null)
                        ? (subtotalObj instanceof Number ? ((Number) subtotalObj).doubleValue()
                                : Double.parseDouble(subtotalObj.toString().replace("$", "").replace(".", ""))) : 0.0;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Subtotal inválido en fila " + (i + 1), "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validaciones adicionales
            if (descripcion.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Descripción vacía en fila " + (i + 1), "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (cantidad <= 0 || precioUnitario <= 0) {
                JOptionPane.showMessageDialog(this, "Valores deben ser positivos en fila " + (i + 1), "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            PedidoDetalle detalle = new PedidoDetalle();
            detalle.setDescripcion(descripcion);
            detalle.setCantidad(cantidad);
            detalle.setDimensiones(medida);
            detalle.setPrecioUnitario(precioUnitario);
            detalle.setSubtotal(subtotal);
            detalle.setTotal(subtotal); // Total igual al subtotal por ahora
            detalle.setPedidoIdPedido(idPedido);
            detalles.add(detalle);
        }

        // Guardar cambios en la base de datos
        Connection con = null;
        try {
            con = Conexion.getConnection();
            con.setAutoCommit(false); // Iniciar transacción

            // Actualizar el pedido
            if (!controlador.actualizar(pedido)) {
                throw new SQLException("Error al actualizar el pedido.");
            }

            // Eliminar detalles antiguos
            String sqlDelete = "DELETE FROM detalle_pedido WHERE pedido_id_pedido = ?";
            try (PreparedStatement stmtDelete = con.prepareStatement(sqlDelete)) {
                stmtDelete.setInt(1, idPedido);
                stmtDelete.executeUpdate();
            }

            // Insertar nuevos detalles
            String sqlInsert = "INSERT INTO detalle_pedido (descripcion, cantidad, dimension, precio_unitario, subtotal, total, pedido_id_pedido) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmtInsert = con.prepareStatement(sqlInsert)) {
                for (PedidoDetalle detalle : detalles) {
                    stmtInsert.setString(1, detalle.getDescripcion());
                    stmtInsert.setInt(2, detalle.getCantidad());
                    stmtInsert.setString(3, detalle.getDimensiones());
                    stmtInsert.setDouble(4, detalle.getPrecioUnitario());
                    stmtInsert.setDouble(5, detalle.getSubtotal());
                    stmtInsert.setDouble(6, detalle.getTotal());
                    stmtInsert.setInt(7, idPedido);
                    stmtInsert.executeUpdate();
                }
            }

            con.commit(); // Confirmar transacción
            JOptionPane.showMessageDialog(this, "Pedido actualizado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cargarDatosPedido(); // Recargar datos
            deshabilitarEdicion();
        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback(); // Revertir transacción en caso de error
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Error al revertir la transacción: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            JOptionPane.showMessageDialog(this, "Error al actualizar los datos en la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (con != null) {
                try {
                    con.setAutoCommit(true); // Restaurar auto-commit
                    con.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "Error al cerrar la conexión: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }


    private void cargarClientes() {
        try {
            Ctrl_Cliente ctrl = new Ctrl_Cliente();
            clientes = ctrl.obtenerClientes();
            cmbCliente.removeAllItems();
            cmbCliente.addItem("Seleccione cliente:");
            if (clientes != null) {
                for (Cliente cli : clientes) {
                    String nombreCompleto = cli.getNombre() + " " + cli.getApellido();
                    cmbCliente.addItem(nombreCompleto);
                }
            } else {
                System.out.println("Lista de clientes es null. Verifica Ctrl_Cliente.obtenerClientes().");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los clientes: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            cmbCliente.addItem("Error al cargar clientes");
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
        dateFechaInicio = new com.toedter.calendar.JDateChooser();
        btnVolver = new RSMaterialComponent.RSButtonShape();
        dateFechaFin = new com.toedter.calendar.JDateChooser();
        txtNombrePedido = new RSMaterialComponent.RSTextFieldMaterial();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaDetalles = new RSMaterialComponent.RSTableMetroCustom();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        cmbCliente = new RSMaterialComponent.RSComboBoxMaterial();
        btnEditar1 = new RSMaterialComponent.RSButtonShape();
        jLabel11 = new javax.swing.JLabel();
        btnGuardar = new RSMaterialComponent.RSButtonShape();
        jLabel8 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        btnAñadir = new RSMaterialComponent.RSButtonShape();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lblNumeroPedido = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cmbEstado = new RSMaterialComponent.RSComboBoxMaterial();
        lblTotal = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1304, 742));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dateFechaInicio.setBackground(new java.awt.Color(153, 0, 0));
        dateFechaInicio.setForeground(new java.awt.Color(102, 255, 51));
        dateFechaInicio.setToolTipText("");
        jPanel2.add(dateFechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 140, 160, 25));

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
        jPanel2.add(btnVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 130, 30));

        dateFechaFin.setBackground(new java.awt.Color(255, 255, 255));
        dateFechaFin.setToolTipText("");
        jPanel2.add(dateFechaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 180, 160, 25));

        txtNombrePedido.setForeground(new java.awt.Color(0, 0, 0));
        txtNombrePedido.setText("fyy");
        txtNombrePedido.setColorMaterial(new java.awt.Color(204, 204, 204));
        txtNombrePedido.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtNombrePedido.setPhColor(new java.awt.Color(0, 0, 0));
        txtNombrePedido.setPlaceholder("Ingrese el nombre...");
        txtNombrePedido.setSelectionColor(new java.awt.Color(0, 0, 0));
        txtNombrePedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombrePedidoActionPerformed(evt);
            }
        });
        jPanel2.add(txtNombrePedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 140, 180, 21));

        tablaDetalles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Descripcion", "Cantidad", "Dimesiones", "Precio unitario", "Subtotal", "Accion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, true, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaDetalles.setBackgoundHead(new java.awt.Color(46, 49, 82));
        tablaDetalles.setBackgoundHover(new java.awt.Color(109, 160, 221));
        tablaDetalles.setBorderHead(null);
        tablaDetalles.setBorderRows(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        tablaDetalles.setColorBorderHead(new java.awt.Color(46, 49, 82));
        tablaDetalles.setColorBorderRows(new java.awt.Color(46, 49, 82));
        tablaDetalles.setColorPrimaryText(new java.awt.Color(0, 0, 0));
        tablaDetalles.setColorSecondary(new java.awt.Color(255, 255, 255));
        tablaDetalles.setColorSecundaryText(new java.awt.Color(0, 0, 0));
        tablaDetalles.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        tablaDetalles.setFontHead(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        tablaDetalles.setFontRowHover(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        tablaDetalles.setFontRowSelect(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        tablaDetalles.setSelectionBackground(new java.awt.Color(109, 160, 221));
        tablaDetalles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaDetallesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaDetalles);
        tablaDetalles.getColumnModel().getColumn(0).setPreferredWidth(10);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 290, 900, 250));
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 600, 910, 10));
        jPanel2.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 220, 910, 10));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel16.setText("Total del pedido:");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 550, -1, -1));

        cmbCliente.setColorMaterial(new java.awt.Color(0, 0, 0));
        cmbCliente.setFont(new java.awt.Font("Roboto Bold", 0, 16)); // NOI18N
        jPanel2.add(cmbCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 180, 163, 26));

        btnEditar1.setBackground(new java.awt.Color(46, 49, 82));
        btnEditar1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        btnEditar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pencil (1)_1.png"))); // NOI18N
        btnEditar1.setText(" Editar pedido");
        btnEditar1.setBackgroundHover(new java.awt.Color(67, 150, 209));
        btnEditar1.setFont(new java.awt.Font("Roboto Bold", 1, 17)); // NOI18N
        btnEditar1.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        btnEditar1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnEditar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditar1ActionPerformed(evt);
            }
        });
        jPanel2.add(btnEditar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 620, 160, 30));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setText("Pedido: ");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 140, 80, 20));

        btnGuardar.setBackground(new java.awt.Color(46, 49, 82));
        btnGuardar.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        btnGuardar.setText("Guardar cambios");
        btnGuardar.setBackgroundHover(new java.awt.Color(67, 150, 209));
        btnGuardar.setFont(new java.awt.Font("Roboto Bold", 1, 17)); // NOI18N
        btnGuardar.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        btnGuardar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel2.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 620, 160, 30));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("Estado:");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 140, 70, 20));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel19.setText("Cliente: ");
        jPanel2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 180, 80, 20));

        btnAñadir.setBackground(new java.awt.Color(46, 49, 82));
        btnAñadir.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        btnAñadir.setText(" Añadir");
        btnAñadir.setBackgroundHover(new java.awt.Color(67, 150, 209));
        btnAñadir.setFont(new java.awt.Font("Roboto Bold", 1, 15)); // NOI18N
        btnAñadir.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        btnAñadir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAñadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAñadirActionPerformed(evt);
            }
        });
        jPanel2.add(btnAñadir, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 250, 110, 30));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Detalles del Pedido #");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, 190, 20));

        lblNumeroPedido.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblNumeroPedido.setText("jlabel");
        jPanel1.add(lblNumeroPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 10, 60, 20));

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 90, 910, 40));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel17.setText("Productos incluidos:");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 230, 190, 20));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Fecha fin:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 180, 90, 20));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("Fecha inicio:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 140, 110, 20));

        cmbEstado.setBackground(new java.awt.Color(0, 0, 0));
        cmbEstado.setForeground(new java.awt.Color(204, 51, 255));
        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione el estado:", "Pendiente", "Proceso", "Finalizado" }));
        cmbEstado.setColorMaterial(new java.awt.Color(51, 204, 0));
        cmbEstado.setFont(new java.awt.Font("Roboto Bold", 0, 16)); // NOI18N
        jPanel2.add(cmbEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 140, 180, 26));

        lblTotal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTotal.setText("0.00");
        jPanel2.add(lblTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 550, -1, -1));

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1310, 740));
    }// </editor-fold>//GEN-END:initComponents

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        // Crear una nueva instancia de pedido pasando el contenedor
        pedido h = new pedido(contenedor);
        h.setSize(1290, 730);
        h.setLocation(0, 0);

        contenedor.removeAll();
        contenedor.add(h);
        contenedor.revalidate();
        contenedor.repaint();
    }//GEN-LAST:event_btnVolverActionPerformed

    private void txtNombrePedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombrePedidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombrePedidoActionPerformed

    private void tablaDetallesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaDetallesMouseClicked
        int column = tablaDetalles.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY() / tablaDetalles.getRowHeight();

        if (row < tablaDetalles.getRowCount() && row >= 0 && column == 5 && editando) { // Si se hace clic en "Eliminar"
            DefaultTableModel model = (DefaultTableModel) tablaDetalles.getModel();
            int confirm = JOptionPane.showConfirmDialog(this,
                    "¿Está seguro de que desea eliminar esta fila?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                model.removeRow(row);
                actualizarTotal();
            }
        }
    }//GEN-LAST:event_tablaDetallesMouseClicked

    private void btnAñadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAñadirActionPerformed
        DefaultTableModel model = (DefaultTableModel) tablaDetalles.getModel();
        // Agregar una nueva fila vacía
        model.addRow(new Object[]{"", 0, "", 0.0, 0.0, "Eliminar"});
        actualizarTotal();
    }//GEN-LAST:event_btnAñadirActionPerformed

    private void btnEditar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditar1ActionPerformed
        editando = true;
        habilitarEdicion();
    }//GEN-LAST:event_btnEditar1ActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardarCambios();
    }//GEN-LAST:event_btnGuardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RSMaterialComponent.RSButtonShape btnAñadir;
    private RSMaterialComponent.RSButtonShape btnEditar1;
    private RSMaterialComponent.RSButtonShape btnGuardar;
    private RSMaterialComponent.RSButtonShape btnVolver;
    private RSMaterialComponent.RSComboBoxMaterial cmbCliente;
    private RSMaterialComponent.RSComboBoxMaterial cmbEstado;
    private com.toedter.calendar.JDateChooser dateFechaFin;
    private com.toedter.calendar.JDateChooser dateFechaInicio;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lblNumeroPedido;
    private javax.swing.JLabel lblTotal;
    private RSMaterialComponent.RSTableMetroCustom tablaDetalles;
    private RSMaterialComponent.RSTextFieldMaterial txtNombrePedido;
    // End of variables declaration//GEN-END:variables
}
