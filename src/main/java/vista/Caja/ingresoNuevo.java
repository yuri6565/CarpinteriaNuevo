/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package vista.Caja;

import controlador.Ctrl_CajaIngresos;
import controlador.Ctrl_CajaIngresos.PedidoConCliente;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author ADSO
 */
public class ingresoNuevo extends javax.swing.JDialog {

    private ingresos ingresoPanel;

    /**
     * Creates new form formuIngresos
     */
    public ingresoNuevo(java.awt.Frame parent, boolean modal, ingresos ingresoPanel) {
        super(parent, modal);
        this.ingresoPanel = ingresoPanel;

        initComponents();

        // Hacer los campos no editables
        txtCliente.setEditable(false);
        txtMontoTotal.setEditable(false);

        cargarPedidos(); // Cargar los nombres de los pedidos al iniciar

        tablaDetalles.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        TableColumn fechaColumn = tablaDetalles.getColumnModel().getColumn(0);
        fechaColumn.setPreferredWidth(110); // Ajustar el ancho de la columna

        TableColumn montoColumn = tablaDetalles.getColumnModel().getColumn(1);
        montoColumn.setPreferredWidth(110); // Ajustar el ancho de la columna

        TableColumn mpagoColumn = tablaDetalles.getColumnModel().getColumn(2);
        mpagoColumn.setPreferredWidth(150); // Ajustar el ancho de la columna

    }

    private void cargarPedidos() {
        cmdPedido.removeAllItems();
        cmdPedido.addItem("Seleccione pedido:");
        System.out.println("Cargando pedidos...");

        Ctrl_CajaIngresos ctrl = new Ctrl_CajaIngresos();
        List<PedidoConCliente> pedidos = ctrl.obtenerPedidosConCliente();

        if (pedidos == null || pedidos.isEmpty()) {
            System.out.println("No se encontraron pedidos.");
        } else {
            System.out.println("Pedidos encontrados: " + pedidos.size());
            for (PedidoConCliente pc : pedidos) {
                cmdPedido.addItem(pc);
                System.out.println("Pedido añadido: " + pc.toString() + ", Monto Total: " + pc.getMontoTotal());
            }
        }

        // Personalizar el renderer del combo box
        cmdPedido.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof PedidoConCliente) {
                    PedidoConCliente pedido = (PedidoConCliente) value;
                    setText(pedido.getNombrePedido());
                } else {
                    setText(value != null ? value.toString() : "");
                }
                return this;
            }
        });
    }

    // Método para calcular y actualizar lblDebido
    private void actualizarDebido() {
        try {
            // Modifica esta parte:
            String montoTotalStr = txtMontoTotal.getText() == null ? "0" : txtMontoTotal.getText().trim();
            montoTotalStr = montoTotalStr.isEmpty() ? "0" : montoTotalStr.replace("$", "").replace(",", "");

            double montoTotal = Double.parseDouble(montoTotalStr);

            // Asegúrate también para lblPagado:
            String pagadoStr = lblPagado.getText() == null ? "0" : lblPagado.getText().trim();
            pagadoStr = pagadoStr.isEmpty() ? "0" : pagadoStr.replace("$", "").replace(",", "");
            double pagado = Double.parseDouble(pagadoStr);

            double debido = montoTotal - pagado;

            // Formatear con signo de pesos y punto como separador decimal
            DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
            symbols.setDecimalSeparator('.');
            DecimalFormat df = new DecimalFormat("$#,##0.##", symbols);
            lblDebido.setText(df.format(debido));

            if (debido > 0) {
                lblDebido.setForeground(Color.RED);
            } else {
                lblDebido.setForeground(Color.BLACK);
            }
            System.out.println("Monto Total: " + montoTotal + ", Pagado: " + pagado + ", Debido: " + debido);
        } catch (NumberFormatException e) {
            lblDebido.setText("$0");
            lblDebido.setForeground(Color.BLACK);
            System.out.println("Error al calcular debido: " + e.getMessage());
        }
    }

    // Agrega este método para actualizar la tabla
    private void agregarFilaATabla(Date fecha, String importe, String metodoPago) {
        javax.swing.table.DefaultTableModel modelo = (javax.swing.table.DefaultTableModel) tablaDetalles.getModel();

        // Formatear la fecha
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
        String fechaStr = sdf.format(fecha);

        // Formatear el importe con signo de pesos y punto como separador decimal
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        symbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("$#,##0.##", symbols);
        String importeFormateado = df.format(Double.parseDouble(importe.replace("$", "").replace(",", "")));

        // Agregar la nueva fila
        modelo.addRow(new Object[]{
            fechaStr,
            importeFormateado,
            metodoPago,
            "Editar", // Botón editar
            "Eliminar" // Botón eliminar
        });
        System.out.println("Fila añadida - Fecha: " + fechaStr + ", Importe: " + importeFormateado + ", Método: " + metodoPago);

        // Calcular totales
        calcularTotales();
    }

    // Método para calcular totales
    private void calcularTotales() {
        double totalPagado = 0.0;
        DefaultTableModel modelo = (DefaultTableModel) tablaDetalles.getModel();
        System.out.println("Calculando totales, filas en tabla: " + modelo.getRowCount());

        // Sumar todos los montos pagados
        for (int i = 0; i < modelo.getRowCount(); i++) {
            try {
                String valorStr = modelo.getValueAt(i, 1).toString();
                double valor = Double.parseDouble(valorStr.replace("$", "").replace(",", ""));
                totalPagado += valor;
            } catch (Exception e) {
                // Ignorar filas con valores no numéricos
            }
        }

        // Formatear total pagado con signo de pesos y punto como separador decimal
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        symbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("$#,##0.##", symbols);
        lblPagado.setText(df.format(totalPagado));
        System.out.println("Total Pagado actualizado: " + totalPagado);
        actualizarDebido();
    }

    private void editarFila(int row) {
        try {
            // Obtener datos de la fila seleccionada
            String fechaStr = (String) tablaDetalles.getValueAt(row, 0);

            // Obtener y formatear correctamente el monto
            Object montoObj = tablaDetalles.getValueAt(row, 1);
            String montoStr = "";
            if (montoObj != null) {
                // Eliminar símbolo $, comas y cualquier otro carácter no numérico excepto punto decimal
                montoStr = montoObj.toString().replaceAll("[^\\d.]", "");
            }

            String metodoPago = (String) tablaDetalles.getValueAt(row, 2);

            // Convertir la fecha de String a Date
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
            Date fecha = sdf.parse(fechaStr);

            // Crear y configurar el diálogo con los datos existentes
            iAbonoNuevo dialog = new iAbonoNuevo((Frame) this.getParent(), true);
            dialog.setLocationRelativeTo(this);

            // Formatear el monto con comas antes de pasarlo
            if (!montoStr.isEmpty()) {
                long amount = Long.parseLong(montoStr.replace(".", "")); // Eliminar punto decimal si existe
                String formattedAmount = String.format("%,d", amount)
                        .replace(",", "X") // Marcador temporal
                        .replace(".", ",") // Cambiar puntos por comas si el locale usa puntos
                        .replace("X", ","); // Restaurar comas de miles
                dialog.setImporte(formattedAmount);
            } else {
                dialog.setImporte("");
            }

            // Establecer los valores en el diálogo
            dialog.setFecha(fecha);
            dialog.setMetodoPago(metodoPago);

            dialog.setVisible(true);

            // Si se guardaron los cambios en el diálogo
            if (dialog.isGuardado()) {
                // Actualizar la fila con los nuevos datos
                Date nuevaFecha = dialog.getFecha();
                String nuevoImporte = dialog.getImporte().replace(",", ""); // Limpiar comas para cálculos
                String nuevoMetodo = dialog.getMetodoPago();

                // Formatear la nueva fecha
                String nuevaFechaStr = sdf.format(nuevaFecha);

                // Formatear el nuevo importe con signo de pesos y punto como separador decimal
                DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
                symbols.setDecimalSeparator('.');
                DecimalFormat df = new DecimalFormat("$#,##0.##", symbols);
                String nuevoImporteFormateado = df.format(Double.parseDouble(nuevoImporte));

                // Actualizar los valores en la tabla
                tablaDetalles.setValueAt(nuevaFechaStr, row, 0);
                tablaDetalles.setValueAt(nuevoImporteFormateado, row, 1);
                tablaDetalles.setValueAt(nuevoMetodo, row, 2);

                // Recalcular totales
                calcularTotales();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al editar: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarFila(int row) {
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro que desea eliminar este abono?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            // Eliminar la fila
            DefaultTableModel model = (DefaultTableModel) tablaDetalles.getModel();
            model.removeRow(row);

            // Recalcular totales
            calcularTotales();
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnGuardar = new rojeru_san.RSButtonRiple();
        btnCancelar = new rojeru_san.RSButtonRiple();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCliente = new RSMaterialComponent.RSTextFieldMaterial();
        jLabel5 = new javax.swing.JLabel();
        cmdPedido = new RSMaterialComponent.RSComboBoxMaterial();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaDetalles = new RSMaterialComponent.RSTableMetroCustom();
        btnAñadir = new RSMaterialComponent.RSButtonShape();
        lblPagado = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblDebido = new javax.swing.JLabel();
        txtMontoTotal = new RSMaterialComponent.RSTextFieldMaterial();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(29, 30, 81));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Century751 BT", 1, 23)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Agregar pago");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 50));

        btnGuardar.setBackground(new java.awt.Color(46, 49, 82));
        btnGuardar.setText("Guardar");
        btnGuardar.setFont(new java.awt.Font("Humnst777 BlkCn BT", 1, 14)); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 540, 140, -1));

        btnCancelar.setBackground(new java.awt.Color(46, 49, 82));
        btnCancelar.setText("Volver");
        btnCancelar.setFont(new java.awt.Font("Humnst777 BlkCn BT", 1, 14)); // NOI18N
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 540, 140, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel3.setText("Cliente:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 70, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel4.setText("Monto del abono:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        txtCliente.setForeground(new java.awt.Color(0, 0, 0));
        txtCliente.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtCliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCliente.setPhColor(new java.awt.Color(0, 0, 0));
        txtCliente.setPlaceholder("Ingrese el nombre...");
        txtCliente.setSelectionColor(new java.awt.Color(0, 0, 0));
        txtCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClienteActionPerformed(evt);
            }
        });
        jPanel1.add(txtCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, 170, 30));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel5.setText("Monto total:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 70, -1, -1));

        cmdPedido.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione pedido:" }));
        cmdPedido.setColorMaterial(new java.awt.Color(0, 0, 0));
        cmdPedido.setFont(new java.awt.Font("Roboto Bold", 0, 14)); // NOI18N
        cmdPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdPedidoActionPerformed(evt);
            }
        });
        jPanel1.add(cmdPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 170, 30));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 660, 10));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel6.setText("Pedido:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        tablaDetalles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Monto", "Metodo de pago", "Editar", "Eliminar"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaDetalles.setBackgoundHead(new java.awt.Color(46, 49, 82));
        tablaDetalles.setBackgoundHover(new java.awt.Color(67, 150, 209));
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
        tablaDetalles.setSelectionBackground(new java.awt.Color(67, 150, 209));
        tablaDetalles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaDetallesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaDetalles);
        tablaDetalles.getColumnModel().getColumn(0).setPreferredWidth(10);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 660, 210));

        btnAñadir.setBackground(new java.awt.Color(46, 49, 82));
        btnAñadir.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        btnAñadir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/anadir (1).png"))); // NOI18N
        btnAñadir.setText(" Añadir abono");
        btnAñadir.setBackgroundHover(new java.awt.Color(67, 150, 209));
        btnAñadir.setFont(new java.awt.Font("Roboto Bold", 1, 15)); // NOI18N
        btnAñadir.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        btnAñadir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAñadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAñadirActionPerformed(evt);
            }
        });
        jPanel1.add(btnAñadir, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 190, 140, 30));

        lblPagado.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblPagado.setText("0.00");
        jPanel1.add(lblPagado, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 460, -1, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel14.setText("Total del pagado:");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 460, -1, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel15.setText("Debido:");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 500, -1, -1));

        lblDebido.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblDebido.setText("0.00");
        jPanel1.add(lblDebido, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 500, -1, -1));

        txtMontoTotal.setForeground(new java.awt.Color(0, 0, 0));
        txtMontoTotal.setColorMaterial(new java.awt.Color(0, 0, 0));
        txtMontoTotal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtMontoTotal.setPhColor(new java.awt.Color(0, 0, 0));
        txtMontoTotal.setPlaceholder("Ingrese el nombre...");
        txtMontoTotal.setSelectionColor(new java.awt.Color(0, 0, 0));
        txtMontoTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMontoTotalActionPerformed(evt);
            }
        });
        jPanel1.add(txtMontoTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 100, 170, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // 1. Validar que se haya seleccionado un pedido
        if (!(cmdPedido.getSelectedItem() instanceof PedidoConCliente)) {
            JOptionPane.showMessageDialog(this,
                    "Por favor seleccione un pedido válido",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        PedidoConCliente pedido = (PedidoConCliente) cmdPedido.getSelectedItem();

        // 2. Validar que tenga cliente
        if (pedido.getIdCliente() == null) {
            JOptionPane.showMessageDialog(this,
                    "Este pedido no tiene cliente asociado",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 3. Validar que haya abonos registrados
        if (tablaDetalles.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this,
                    "Debe registrar al menos un abono",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 4. Obtener el total pagado desde la etiqueta (que ya está calculado)
        try {
            String pagadoStr = lblPagado.getText().replace("$", "").replace(",", "");
            double montoPagado = Double.parseDouble(pagadoStr);

            // 5. Validar que el pago no exceda el monto total
            if (montoPagado > pedido.getMontoTotal()) {
                JOptionPane.showMessageDialog(this,
                        "El monto pagado no puede ser mayor al total del pedido",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 6. Guardar cada abono en la base de datos
            Ctrl_CajaIngresos ctrl = new Ctrl_CajaIngresos();
            DefaultTableModel model = (DefaultTableModel) tablaDetalles.getModel();
            boolean success = true;

            for (int i = 0; i < model.getRowCount(); i++) {
                try {
                    // Obtener datos de cada fila
                    String fechaStr = (String) model.getValueAt(i, 0);
                    String montoStr = ((String) model.getValueAt(i, 1)).replace("$", "").replace(",", "");
                    String metodoPago = (String) model.getValueAt(i, 2);

                    double monto = Double.parseDouble(montoStr);
                    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
                    Date fecha = sdf.parse(fechaStr);

                    // Guardar cada abono
                    if (!ctrl.guardarPago(pedido, monto, metodoPago, fecha)) {
                        success = false;
                        break;
                    }
                } catch (Exception e) {
                    success = false;
                    Logger.getLogger(ingresoNuevo.class.getName()).log(Level.SEVERE, null, e);
                    break;
                }
            }

            if (success) {
                JOptionPane.showMessageDialog(this,
                        "Pago(s) registrado(s) exitosamente",
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);

                // Limpiar la tabla después de guardar
                DefaultTableModel modelo = (DefaultTableModel) tablaDetalles.getModel();
                modelo.setRowCount(0);

                // Actualizar los totales
                lblPagado.setText("0.00");
                actualizarDebido();

                // Actualizar el panel de ingresos si existe
                if (ingresoPanel != null) {
                    ingresoPanel.cargarDatosIniciales(); // Asegúrate de que este método exista en tu clase ingresos
                }

                // Opcional: cerrar el diálogo después de guardar
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Error al registrar uno o más pagos",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Error en el formato de los montos",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed


    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClienteActionPerformed

    private void cmdPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdPedidoActionPerformed
        Object sel = cmdPedido.getSelectedItem();
        if (sel instanceof PedidoConCliente) {
            PedidoConCliente seleccionado = (PedidoConCliente) sel;
            System.out.println("Pedido seleccionado: " + seleccionado.toString());
            System.out.println("Monto Total del pedido: " + seleccionado.getMontoTotal());
            System.out.println("Nombre del pedido: " + seleccionado.getNombrePedido());
            System.out.println("Nombre del cliente: " + seleccionado.getNombreCliente());

            txtCliente.setText(seleccionado.getNombreCliente() != null ? seleccionado.getNombreCliente() : "Sin cliente");
            DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
            symbols.setDecimalSeparator('.');
            DecimalFormat df = new DecimalFormat("$#,##0.##", symbols);
            txtMontoTotal.setText(df.format(seleccionado.getMontoTotal()));
            System.out.println("txtMontoTotal actualizado a: " + txtMontoTotal.getText());
            actualizarDebido();
        } else {
            System.out.println("Selección: Seleccione pedido");
            txtCliente.setText("");
            txtMontoTotal.setText("");
            System.out.println("txtMontoTotal reseteado a: " + txtMontoTotal.getText());
            actualizarDebido();
        }
    }//GEN-LAST:event_cmdPedidoActionPerformed

    private void tablaDetallesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaDetallesMouseClicked
        int column = tablaDetalles.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY() / tablaDetalles.getRowHeight();

        // Verificar que el clic fue dentro de los límites de la tabla
        if (row < tablaDetalles.getRowCount() && row >= 0
                && column < tablaDetalles.getColumnCount() && column >= 0) {

            Object value = tablaDetalles.getValueAt(row, column);

            // Si se hizo clic en la columna "Editar" (columna 3)
            if (value instanceof String && ((String) value).equals("Editar") && column == 3) {
                editarFila(row);
            } // Si se hizo clic en la columna "Eliminar" (columna 4)
            else if (value instanceof String && ((String) value).equals("Eliminar") && column == 4) {
                eliminarFila(row);
            }
        }
    }//GEN-LAST:event_tablaDetallesMouseClicked

    private void btnAñadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAñadirActionPerformed
        iAbonoNuevo dialog = new iAbonoNuevo(new javax.swing.JFrame(), true);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

        // Después de cerrar el diálogo, verificar si se guardó
        if (dialog.isGuardado()) {
            // Obtener los datos del diálogo
            Date fecha = dialog.getFecha();
            String importe = dialog.getImporte();
            String metodoPago = dialog.getMetodoPago();

            // Agregar a la tabla
            agregarFilaATabla(fecha, importe, metodoPago);
        }
    }//GEN-LAST:event_btnAñadirActionPerformed

    private void txtMontoTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMontoTotalActionPerformed
        System.out.println("txtMontoTotal modificado manualmente: " + txtMontoTotal.getText());
        actualizarDebido();
    }//GEN-LAST:event_txtMontoTotalActionPerformed

    /**
     * @param args the command line arguments
     */
    /*
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
    /*
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ingresoNuevo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ingresoNuevo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ingresoNuevo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ingresoNuevo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
/*
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ingresoNuevo dialog = new ingresoNuevo(new javax.swing.JFrame(), true);
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
/*
        
        */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RSMaterialComponent.RSButtonShape btnAñadir;
    private rojeru_san.RSButtonRiple btnCancelar;
    private rojeru_san.RSButtonRiple btnGuardar;
    private RSMaterialComponent.RSComboBoxMaterial cmdPedido;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblDebido;
    private javax.swing.JLabel lblPagado;
    private RSMaterialComponent.RSTableMetroCustom tablaDetalles;
    private RSMaterialComponent.RSTextFieldMaterial txtCliente;
    private RSMaterialComponent.RSTextFieldMaterial txtMontoTotal;
    // End of variables declaration//GEN-END:variables

}
