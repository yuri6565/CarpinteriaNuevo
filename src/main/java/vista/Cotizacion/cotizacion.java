package vista.Cotizacion;

import controlador.GeneradorCotizacionPDF;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.File;
import java.awt.Desktop;
import javax.swing.JPanel;
import modelo.Cotizacion;
import controlador.CotizacionDAO;
import controlador.Ctrl_Cliente;
import controlador.Ctrl_Cotizacion;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.table.DefaultTableModel;
import modelo.Cliente;
import vista.Cotizacion.HistorialCot;
import vista.Cotizacion.InsertarCoti;

public class cotizacion extends javax.swing.JPanel {

    private Integer clienteCodigo;
    private boolean clienteIngresado = false;
    private String[] datos; // Almacena los datos ingresados
    private boolean guardado = false; // Indica si se presionó "Guardar"

    public cotizacion(JPanel contenedor) {
        initComponents();
        configurarTabla();
        txt_total.setEditable(false);
        txt_total.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_total.setText("$0");

        CrearCotizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CrearCotizacionActionPerformed(evt);
            }
        });
    }

    public java.sql.Date getFecha() {
        return new java.sql.Date(System.currentTimeMillis());
    }

    public String[] getDatos() {
        return datos;
    }

    public boolean isGuardado() {
        return guardado;
    }

    private void configurarTabla() {
        DefaultTableModel modelo = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Producto", "Unidad", "Cantidad", "Valor Unitario", "Subtotal", "Editar", "Eliminar"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5 || column == 6;
            }
        };
        Tabla1.setModel(modelo);
        Tabla1.getColumnModel().getColumn(0).setPreferredWidth(200);
        Tabla1.getColumnModel().getColumn(1).setPreferredWidth(80);
        Tabla1.getColumnModel().getColumn(2).setPreferredWidth(70);
        Tabla1.getColumnModel().getColumn(3).setPreferredWidth(100);
        Tabla1.getColumnModel().getColumn(4).setPreferredWidth(100);
        Tabla1.getColumnModel().getColumn(5).setPreferredWidth(60);
        Tabla1.getColumnModel().getColumn(6).setPreferredWidth(60);
    }

    private void guardarCotizacion() {
        if (Tabla1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "No hay productos en la cotización", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (clienteCodigo == null) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione o cree un cliente", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        DefaultTableModel modelo = (DefaultTableModel) Tabla1.getModel();
        List<Cotizacion> cotizaciones = new ArrayList<>();

        for (int i = 0; i < modelo.getRowCount(); i++) {
            Cotizacion cotizacion = new Cotizacion();
            try {
                cotizacion.setDetalle(modelo.getValueAt(i, 0).toString());
                cotizacion.setUnidad(modelo.getValueAt(i, 1).toString());
                cotizacion.setCantidad(Integer.parseInt(modelo.getValueAt(i, 2).toString()));
                String valorUnitarioStr = modelo.getValueAt(i, 3).toString().replace("$", "").replace(".", "");
                cotizacion.setValorUnitario(Double.parseDouble(valorUnitarioStr));
                String subtotalStr = modelo.getValueAt(i, 4).toString().replace("$", "").replace(".", "");
                cotizacion.setSubTotal(Double.parseDouble(subtotalStr));
                cotizacion.setTotal(Double.parseDouble(txt_total.getText().replace("$", "").replace(".", "")));
                cotizacion.setClienteCodigo(clienteCodigo);
                cotizacion.setFecha(new java.sql.Date(System.currentTimeMillis()));
                cotizacion.setUsuarioIdUsuario(1); // Ajusta según el ID del usuario logueado
                cotizaciones.add(cotizacion);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al procesar los datos de la fila " + i + ": " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        CotizacionDAO cotizacionDAO = new CotizacionDAO();
        int idCotizacion;

        try {
            idCotizacion = cotizacionDAO.guardarCotizaciones(cotizaciones);
            if (idCotizacion != -1) {
                JOptionPane.showMessageDialog(this, "Cotización guardada exitosamente con ID: " + idCotizacion, "Éxito", JOptionPane.INFORMATION_MESSAGE);
                reiniciarCotizacion();
            } else {
                JOptionPane.showMessageDialog(this, "Error al guardar la cotización en la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar la cotización: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToggleButton1 = new javax.swing.JToggleButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Tabla1 = new RSMaterialComponent.RSTableMetroCustom();
        jButton_anadir_producto = new RSMaterialComponent.RSButtonShape();
        HistorialCoti = new RSMaterialComponent.RSButtonShape();
        txt_total = new RSMaterialComponent.RSTextFieldMaterial();
        btnCrearpdf = new RSMaterialComponent.RSButtonShape();
        txt_NombreCliente = new RSMaterialComponent.RSTextFieldMaterial();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_ApellidoCliente = new RSMaterialComponent.RSTextFieldMaterial();
        identificaciontxt = new RSMaterialComponent.RSComboBoxMaterial();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        numeroidtxt = new RSMaterialComponent.RSTextFieldMaterial();
        telefonotxt = new RSMaterialComponent.RSTextFieldMaterial();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        direcciontxt = new RSMaterialComponent.RSTextFieldMaterial();
        CrearCotizacion = new RSMaterialComponent.RSButtonShape();

        jToggleButton1.setText("jToggleButton1");

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Cotizacion");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 10, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Nombres cliente:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 120, 30));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Producto", "Unidad", "Cantidad", "Valor Unitario", "Subtotal", "Editar", "Eliminar"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Tabla1.setBackgoundHead(new java.awt.Color(46, 49, 82));
        Tabla1.setBackgoundHover(new java.awt.Color(109, 160, 221));
        Tabla1.setBorderHead(null);
        Tabla1.setBorderRows(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        Tabla1.setColorBorderHead(new java.awt.Color(46, 49, 82));
        Tabla1.setColorBorderRows(new java.awt.Color(46, 49, 82));
        Tabla1.setColorPrimaryText(new java.awt.Color(0, 0, 0));
        Tabla1.setColorSecondary(new java.awt.Color(255, 255, 255));
        Tabla1.setColorSecundaryText(new java.awt.Color(0, 0, 0));
        Tabla1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Tabla1.setFontHead(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Tabla1.setFontRowHover(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Tabla1.setFontRowSelect(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Tabla1.setRowHeight(23);
        Tabla1.setSelectionBackground(new java.awt.Color(109, 160, 221));
        Tabla1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tabla1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(Tabla1);
        Tabla1.getColumnModel().getColumn(0).setPreferredWidth(10);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 1150, 370));

        jButton_anadir_producto.setBackground(new java.awt.Color(46, 49, 82));
        jButton_anadir_producto.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        jButton_anadir_producto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plus (2).png"))); // NOI18N
        jButton_anadir_producto.setText("Añadir Producto");
        jButton_anadir_producto.setBackgroundHover(new java.awt.Color(0, 153, 0));
        jButton_anadir_producto.setFont(new java.awt.Font("Roboto Bold", 1, 16)); // NOI18N
        jButton_anadir_producto.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        jButton_anadir_producto.setHideActionText(true);
        jButton_anadir_producto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jButton_anadir_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_anadir_productoActionPerformed(evt);
            }
        });
        jPanel1.add(jButton_anadir_producto, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 10, 170, 40));

        HistorialCoti.setBackground(new java.awt.Color(46, 49, 82));
        HistorialCoti.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        HistorialCoti.setText("  Historial de Cotizaciones");
        HistorialCoti.setBackgroundHover(new java.awt.Color(0, 153, 0));
        HistorialCoti.setFont(new java.awt.Font("Roboto Bold", 1, 12)); // NOI18N
        HistorialCoti.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        HistorialCoti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HistorialCotiActionPerformed(evt);
            }
        });
        jPanel1.add(HistorialCoti, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 10, 160, 40));

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, 1220, 350));

        txt_total.setEditable(false);
        txt_total.setBackground(new java.awt.Color(255, 255, 255));
        txt_total.setForeground(new java.awt.Color(0, 0, 0));
        txt_total.setColorMaterial(new java.awt.Color(0, 0, 0));
        txt_total.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_total.setPhColor(new java.awt.Color(0, 0, 0));
        txt_total.setPlaceholder("Total...");
        txt_total.setSelectionColor(new java.awt.Color(0, 0, 0));
        txt_total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_totalActionPerformed(evt);
            }
        });
        jPanel2.add(txt_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 580, 200, 30));

        btnCrearpdf.setBackground(new java.awt.Color(46, 49, 82));
        btnCrearpdf.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        btnCrearpdf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/file-pdf-solid-60 (2).png"))); // NOI18N
        btnCrearpdf.setText("Crear PDF");
        btnCrearpdf.setBackgroundHover(new java.awt.Color(153, 153, 153));
        btnCrearpdf.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnCrearpdf.setForegroundHover(new java.awt.Color(0, 0, 0));
        btnCrearpdf.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        btnCrearpdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearpdfActionPerformed(evt);
            }
        });
        jPanel2.add(btnCrearpdf, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 550, 170, 70));

        txt_NombreCliente.setForeground(new java.awt.Color(0, 0, 0));
        txt_NombreCliente.setColorMaterial(new java.awt.Color(0, 0, 0));
        txt_NombreCliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_NombreCliente.setPhColor(new java.awt.Color(0, 0, 0));
        txt_NombreCliente.setPlaceholder("Ingrese Nombre...");
        txt_NombreCliente.setSelectionColor(new java.awt.Color(0, 0, 0));
        txt_NombreCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_NombreClienteActionPerformed(evt);
            }
        });
        jPanel2.add(txt_NombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, 180, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setText("Total:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 580, 70, 30));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Apellidos cliente:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, -1, -1));

        txt_ApellidoCliente.setForeground(new java.awt.Color(0, 0, 0));
        txt_ApellidoCliente.setColorMaterial(new java.awt.Color(0, 0, 0));
        txt_ApellidoCliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_ApellidoCliente.setPhColor(new java.awt.Color(0, 0, 0));
        txt_ApellidoCliente.setPlaceholder("Ingrese Apellidos...");
        txt_ApellidoCliente.setSelectionColor(new java.awt.Color(0, 0, 0));
        txt_ApellidoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ApellidoClienteActionPerformed(evt);
            }
        });
        jPanel2.add(txt_ApellidoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, 180, 30));

        identificaciontxt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tipo de identificacion:", "CC", "CE", "TI", "RC", "NIT", "PA", "PEP", "PPT", "DNI" }));
        identificaciontxt.setColorMaterial(new java.awt.Color(29, 30, 51));
        identificaciontxt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        identificaciontxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                identificaciontxtActionPerformed(evt);
            }
        });
        jPanel2.add(identificaciontxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 60, 200, 30));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Tipo de identificación:");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 60, 180, 30));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Numero de identificación:");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 110, -1, -1));

        numeroidtxt.setForeground(new java.awt.Color(0, 0, 0));
        numeroidtxt.setColorMaterial(new java.awt.Color(0, 0, 0));
        numeroidtxt.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        numeroidtxt.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        numeroidtxt.setPhColor(new java.awt.Color(29, 30, 51));
        numeroidtxt.setPlaceholder("Ingrese n° identificacion");
        jPanel2.add(numeroidtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 100, 200, 30));

        telefonotxt.setForeground(new java.awt.Color(29, 30, 31));
        telefonotxt.setColorMaterial(new java.awt.Color(29, 30, 31));
        telefonotxt.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        telefonotxt.setPhColor(new java.awt.Color(29, 30, 51));
        telefonotxt.setPlaceholder("Ingrese telefono");
        jPanel2.add(telefonotxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 140, 180, 30));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("Telefono:");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, 120, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("Dirección:");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 150, -1, -1));

        direcciontxt.setForeground(new java.awt.Color(29, 30, 31));
        direcciontxt.setColorMaterial(new java.awt.Color(29, 30, 31));
        direcciontxt.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        direcciontxt.setPhColor(new java.awt.Color(29, 30, 51));
        direcciontxt.setPlaceholder("Ingrese direccion");
        direcciontxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                direcciontxtActionPerformed(evt);
            }
        });
        jPanel2.add(direcciontxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 140, 200, 30));

        CrearCotizacion.setBackground(new java.awt.Color(46, 49, 82));
        CrearCotizacion.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        CrearCotizacion.setText("Crear Cotizacion");
        CrearCotizacion.setBackgroundHover(new java.awt.Color(0, 153, 0));
        CrearCotizacion.setFont(new java.awt.Font("Roboto Bold", 1, 16)); // NOI18N
        CrearCotizacion.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        CrearCotizacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CrearCotizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CrearCotizacionActionPerformed(evt);
            }
        });
        jPanel2.add(CrearCotizacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 550, 160, 70));

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 680));
    }// </editor-fold>//GEN-END:initComponents

    private void txt_totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_totalActionPerformed

    }//GEN-LAST:event_txt_totalActionPerformed

    private void btnCrearpdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearpdfActionPerformed
        if (Tabla1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "No hay productos en la cotización", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!clienteIngresado) {
            if (identificaciontxt.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "Seleccione un tipo de identificación válido");
                return;
            }
            if (numeroidtxt.getText().trim().isEmpty() || txt_NombreCliente.getText().trim().isEmpty()
                    || txt_ApellidoCliente.getText().trim().isEmpty() || telefonotxt.getText().trim().isEmpty()
                    || direcciontxt.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Todos los campos del cliente son obligatorios");
                return;
            }

            int numero;
            try {
                numero = Integer.parseInt(numeroidtxt.getText().trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "El número de identificación debe ser un valor numérico");
                return;
            }

            Cliente cliente = new Cliente();
            cliente.setIdentificacion(identificaciontxt.getSelectedItem().toString());
            cliente.setId_cliente(numero);
            cliente.setNombre(txt_NombreCliente.getText().trim());
            cliente.setApellido(txt_ApellidoCliente.getText().trim());
            cliente.setTelefono(telefonotxt.getText().trim());
            cliente.setDireccion(direcciontxt.getText().trim());

            Ctrl_Cliente contro = new Ctrl_Cliente();
            if (contro.guardar(cliente)) {
                clienteIngresado = true;
                clienteCodigo = cliente.getId_cliente();
                datos = new String[]{"", cliente.getIdentificacion(), String.valueOf(cliente.getId_cliente()),
                    cliente.getNombre(), cliente.getApellido(), cliente.getTelefono(), cliente.getDireccion()};
                JOptionPane.showMessageDialog(null, "Cliente guardado Exitosamente");

                txt_NombreCliente.setEnabled(false);
                txt_ApellidoCliente.setEnabled(false);
                identificaciontxt.setEnabled(false);
                numeroidtxt.setEnabled(false);
                telefonotxt.setEnabled(false);
                direcciontxt.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(null, "Hubo problemas en guardar el cliente");
                return;
            }
        }

        GeneradorCotizacionPDF generador = new GeneradorCotizacionPDF();
        DefaultTableModel modelo = (DefaultTableModel) Tabla1.getModel();
        String total = txt_total.getText().trim();
        String cliente = txt_NombreCliente.getText().trim() + " " + txt_ApellidoCliente.getText().trim();
        String archivoSalida = "cotizacion_" + cliente.replaceAll("[^a-zA-Z0-9_-]", "_") + "_" + new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date()) + ".pdf";

        try {
            generador.generarPDF(cliente, modelo, total, archivoSalida);
            File pdfFile = new File(archivoSalida);
            if (pdfFile.exists() && Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(pdfFile);
            } else {
                JOptionPane.showMessageDialog(this, "No se puede abrir el PDF automáticamente. El archivo se encuentra en: " + archivoSalida, "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al generar o abrir PDF: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnCrearpdfActionPerformed

    private void txt_NombreClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_NombreClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_NombreClienteActionPerformed

    private void Tabla1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabla1MouseClicked
        if (evt.getClickCount() == 1) {
            int columna = Tabla1.columnAtPoint(evt.getPoint());
            int fila = Tabla1.rowAtPoint(evt.getPoint());

            if (fila >= 0) {
                if (columna == 6) { // Columna Eliminar (ahora índice 6)
                    eliminarFila(fila);
                } else if (columna == 5) { // Columna Editar (ahora índice 5)
                    editarFila(fila);
                }
            }
        }
    }

    private void btnGuardarCotActionPerformed(java.awt.event.ActionEvent evt) {
        guardarCotizacion();
    }

    private void eliminarFila(int fila) {
        String producto = Tabla1.getValueAt(fila, 0).toString();
        String cantidad = Tabla1.getValueAt(fila, 2).toString();

        int confirmacion = JOptionPane.showConfirmDialog(
                this,
                "<html>¿Está seguro que desea eliminar el producto:<br><br>"
                + "<b>Producto:</b> " + producto + "<br>"
                + "<b>Cantidad:</b> " + cantidad + "<br><br>"
                + "Esta acción no se puede deshacer.</html>",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            DefaultTableModel modelo = (DefaultTableModel) Tabla1.getModel();
            modelo.removeRow(fila);
            calcularTotal();

            JOptionPane.showMessageDialog(
                    this,
                    "Producto eliminado correctamente",
                    "Eliminación exitosa",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    private void editarFila(int fila) {
        String producto = Tabla1.getValueAt(fila, 0).toString();
        String unidad = Tabla1.getValueAt(fila, 1).toString();
        String cantidad = Tabla1.getValueAt(fila, 2).toString();
        String valorUnitario = Tabla1.getValueAt(fila, 3).toString().replace("$", "").replace(",", "");

        java.awt.Frame parent = (java.awt.Frame) javax.swing.SwingUtilities.getWindowAncestor(this);
        InsertarCoti dialog = new InsertarCoti(parent, true, unidad, valorUnitario);

        dialog.setVisible(true);

        if (dialog.isConfirmado()) {
            Object[] datos = dialog.getDatosCoti();
            String nuevoProducto = (String) datos[0];
            String nuevaCantidadTexto = (String) datos[1];
            String nuevoValorUnitarioTexto = (String) datos[2];
            String nuevaUnidad = (String) datos[3];

            int nuevaCantidad = Integer.parseInt(nuevaCantidadTexto);
            double nuevoValorUnitario = Double.parseDouble(nuevoValorUnitarioTexto);
            double nuevoSubtotal = nuevaCantidad * nuevoValorUnitario;

            DefaultTableModel modelo = (DefaultTableModel) Tabla1.getModel();
            modelo.setValueAt(nuevoProducto, fila, 0);
            modelo.setValueAt(nuevaUnidad, fila, 1);
            modelo.setValueAt(nuevaCantidad, fila, 2);
            modelo.setValueAt(String.format("$%,d", (long) nuevoValorUnitario), fila, 3);
            modelo.setValueAt(String.format("$%,d", (long) nuevoSubtotal), fila, 4);

            Tabla1.putClientProperty("filaEditando", null);
            jButton_anadir_producto.setText("  Añadir");
            calcularTotal();
            JOptionPane.showMessageDialog(this, "Producto actualizado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_Tabla1MouseClicked


    private void HistorialCotiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HistorialCotiActionPerformed
        HistorialCot h = new HistorialCot(jPanel2, true); // Añadir el segundo parámetro
        h.setSize(1290, 730);
        h.setLocation(0, 0);
        jPanel2.removeAll();
        jPanel2.add(h);
        jPanel2.revalidate();
        jPanel2.repaint();
    }//GEN-LAST:event_HistorialCotiActionPerformed

    private void jButton_anadir_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_anadir_productoActionPerformed

        Integer filaEditando = (Integer) Tabla1.getClientProperty("filaEditando");
        if (filaEditando != null) {
            guardarEdicionamiento(filaEditando);
        } else {
            java.awt.Frame parent = (java.awt.Frame) javax.swing.SwingUtilities.getWindowAncestor(this);
            InsertarCoti dialog = new InsertarCoti(parent, true, "", ""); // Ajusta según tus necesidades
            dialog.setLocationRelativeTo(this);
            dialog.setVisible(true);

            if (dialog.isConfirmado()) {
                Object[] datos = dialog.getDatosCoti();
                String producto = (String) datos[0];
                String cantidadTexto = (String) datos[1];
                String valorUnitarioTexto = (String) datos[2];
                String unidad = (String) datos[3];

                try {
                    int cantidad = Integer.parseInt(cantidadTexto);
                    double valorUnitario = Double.parseDouble(valorUnitarioTexto);
                    if (cantidad <= 0 || valorUnitario < 0) {
                        JOptionPane.showMessageDialog(this, "Cantidad debe ser positiva y valor unitario no puede ser negativo", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    double subtotal = cantidad * valorUnitario;

                    DefaultTableModel modelo = (DefaultTableModel) Tabla1.getModel();
                    modelo.addRow(new Object[]{
                        producto,
                        unidad,
                        cantidad,
                        String.format("$%,d", (int) valorUnitario),
                        String.format("$%,d", (int) subtotal),
                        "Editar",
                        "Eliminar"
                    });
                    calcularTotal();
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Error al procesar los datos del producto: asegúrese de que sean numéricos", "Error", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
            }
        }
    }

    private void limpiarCamposProducto() {

    }//GEN-LAST:event_jButton_anadir_productoActionPerformed

    private void txt_ApellidoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ApellidoClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ApellidoClienteActionPerformed

    private void direcciontxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_direcciontxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_direcciontxtActionPerformed

    private void identificaciontxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_identificaciontxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_identificaciontxtActionPerformed

    private void CrearCotizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CrearCotizacionActionPerformed

        System.out.println("DEBUG: Botón CrearCotizacion presionado");

        try {
            System.out.println("DEBUG: Validando tabla...");
            if (Tabla1.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "No hay productos", "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println("DEBUG: Error - No hay productos en la tabla");
                return;
            }
            System.out.println("DEBUG: Tabla tiene " + Tabla1.getRowCount() + " filas");

            System.out.println("DEBUG: Validando cliente...");
            if (!validarYCrearCliente()) {
                JOptionPane.showMessageDialog(this, "Error al validar o crear cliente", "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println("DEBUG: Error - Fallo en validarYCrearCliente");
                return;
            }

            System.out.println("DEBUG: Validando clienteCodigo...");
            if (clienteCodigo == null) {
                JOptionPane.showMessageDialog(this, "Error: No se ha seleccionado un cliente válido", "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println("DEBUG: Error - clienteCodigo es null");
                return;
            }
            System.out.println("DEBUG: clienteCodigo = " + clienteCodigo);

            // Validar y obtener el total
            System.out.println("DEBUG: Validando txt_total...");
            String totalText = txt_total.getText().trim();
            System.out.println("DEBUG: Valor de txt_total antes de procesar: '" + totalText + "'");
            if (totalText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El campo total está vacío", "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println("DEBUG: Error - txt_total está vacío");
                return;
            }

            // Eliminar símbolos y validar formato
            String cleanTotal = totalText.replace("$", "").replace(",", "");
            System.out.println("DEBUG: Valor de cleanTotal: '" + cleanTotal + "'");

            // Verificar que no haya múltiples puntos
            long pointCount = cleanTotal.chars().filter(ch -> ch == '.').count();
            System.out.println("DEBUG: Cantidad de puntos decimales: " + pointCount);
            if (pointCount > 1) {
                JOptionPane.showMessageDialog(this, "El total contiene un formato inválido (múltiples puntos). Use un solo punto decimal, ej. 123.45", "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println("DEBUG: Error - Múltiples puntos en txt_total");
                return;
            }

            double total = Double.parseDouble(cleanTotal);

            // Crear objeto Cotizacion para la cabecera
            Cotizacion cotizacion = new Cotizacion();
            cotizacion.setFecha(getFecha());
            cotizacion.setClienteCodigo(clienteCodigo);
            cotizacion.setUsuarioIdUsuario(1); // Ajusta según el ID del usuario logueado
            cotizacion.setTotal(total);
            System.out.println("DEBUG: Cotización creada con total: " + total);

            // Usar el DefaultTableModel de la tabla
            DefaultTableModel modelo = (DefaultTableModel) Tabla1.getModel();
            System.out.println("DEBUG: Modelo tiene " + modelo.getRowCount() + " filas");

            // Validar que no haya filas vacías en detalle
            System.out.println("DEBUG: Validando detalles en la tabla...");
            for (int i = 0; i < modelo.getRowCount(); i++) {
                Object detalleObj = modelo.getValueAt(i, 0);
                System.out.println("DEBUG: Fila " + i + ", detalle = " + detalleObj);
                if (detalleObj == null || detalleObj.toString().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "La fila " + i + " no tiene un detalle válido", "Error", JOptionPane.ERROR_MESSAGE);
                    System.out.println("DEBUG: Error - Fila " + i + " sin detalle válido");
                    return;
                }
            }

            CotizacionDAO dao = new CotizacionDAO();
            System.out.println("DEBUG: Llamando a guardarCotizacion...");
            int id = dao.guardarCotizacion(cotizacion, modelo);
            System.out.println("DEBUG: Resultado de guardarCotizacion: " + id);

            if (id > 0) {
                JOptionPane.showMessageDialog(this, "Cotización guardada: " + id, "Éxito", JOptionPane.INFORMATION_MESSAGE);
                reiniciarCotizacion();
            } else {
                JOptionPane.showMessageDialog(this, "Error al guardar: Código de error " + id, "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println("DEBUG: Error al guardar con código: " + id);
            }
        } catch (Exception e) {
            System.out.println("DEBUG: Excepción al guardar: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_CrearCotizacionActionPerformed

    private boolean camposClienteLlenos() {
        return !txt_NombreCliente.getText().trim().isEmpty()
                && !txt_ApellidoCliente.getText().trim().isEmpty()
                && !numeroidtxt.getText().trim().isEmpty()
                && identificaciontxt.getSelectedIndex() > 0;
    }

    private boolean validarYCrearCliente() {
        if (clienteIngresado) {
            return true;
        }

        if (identificaciontxt.getSelectedIndex() == 0
                || numeroidtxt.getText().trim().isEmpty()
                || txt_NombreCliente.getText().trim().isEmpty()
                || txt_ApellidoCliente.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos del cliente son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            Cliente cliente = new Cliente();
            cliente.setIdentificacion(identificaciontxt.getSelectedItem().toString());
            cliente.setId_cliente(Integer.parseInt(numeroidtxt.getText().trim()));
            cliente.setNombre(txt_NombreCliente.getText().trim());
            cliente.setApellido(txt_ApellidoCliente.getText().trim());
            cliente.setTelefono(telefonotxt.getText().trim());
            cliente.setDireccion(direcciontxt.getText().trim());

            Ctrl_Cliente contro = new Ctrl_Cliente();
            if (contro.guardar(cliente)) {
                clienteIngresado = true;
                clienteCodigo = cliente.getId_cliente();
                bloquearCamposCliente();
                return true;
            } else {
                JOptionPane.showMessageDialog(this, "Error al guardar el cliente", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Número de identificación debe ser válido", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private void bloquearCamposCliente() {
        txt_NombreCliente.setEnabled(false);
        txt_ApellidoCliente.setEnabled(false);
        identificaciontxt.setEnabled(false);
        numeroidtxt.setEnabled(false);
        telefonotxt.setEnabled(false);
        direcciontxt.setEnabled(false);
    }

    private void guardarEdicionamiento(Integer filaEditando) {
        throw new UnsupportedOperationException("Not supported yet."); // Debes implementarlo si planeas usarlo
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RSMaterialComponent.RSButtonShape CrearCotizacion;
    private RSMaterialComponent.RSButtonShape HistorialCoti;
    private RSMaterialComponent.RSTableMetroCustom Tabla1;
    private RSMaterialComponent.RSButtonShape btnCrearpdf;
    private RSMaterialComponent.RSTextFieldMaterial direcciontxt;
    private RSMaterialComponent.RSComboBoxMaterial identificaciontxt;
    private RSMaterialComponent.RSButtonShape jButton_anadir_producto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JToggleButton jToggleButton1;
    private RSMaterialComponent.RSTextFieldMaterial numeroidtxt;
    private RSMaterialComponent.RSTextFieldMaterial telefonotxt;
    private RSMaterialComponent.RSTextFieldMaterial txt_ApellidoCliente;
    private RSMaterialComponent.RSTextFieldMaterial txt_NombreCliente;
    private RSMaterialComponent.RSTextFieldMaterial txt_total;
    // End of variables declaration//GEN-END:variables

    /*joa nambre*/
    public void calcularTotal() {
        DefaultTableModel modelo = (DefaultTableModel) Tabla1.getModel();
        long total = 0;

        for (int i = 0; i < modelo.getRowCount(); i++) {
            try {
                String subtotalStr = modelo.getValueAt(i, 4).toString();
                subtotalStr = subtotalStr.replace("$", "");
                NumberFormat format = NumberFormat.getNumberInstance(Locale.getDefault());
                Number number = format.parse(subtotalStr);
                long subtotal = number.longValue();
                total += subtotal;
            } catch (ParseException | NumberFormatException e) {
                JOptionPane.showMessageDialog(this,
                        "Error al calcular el subtotal en la fila " + (i + 1) + "\n"
                        + "Valor problemático: " + modelo.getValueAt(i, 4) + "\n"
                        + "Detalles: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setDecimalSeparator('.');
        symbols.setGroupingSeparator('.');
        DecimalFormat df = new DecimalFormat("#,###", symbols);
        txt_total.setText("$" + df.format(total));
    }

    public void reiniciarCotizacion() {
        DefaultTableModel modelo = (DefaultTableModel) Tabla1.getModel();
        modelo.setRowCount(0);

        clienteIngresado = false;
        clienteCodigo = null;
        txt_NombreCliente.setEnabled(true);
        txt_ApellidoCliente.setEnabled(true);
        identificaciontxt.setEnabled(true);
        numeroidtxt.setEnabled(true);
        telefonotxt.setEnabled(true);
        direcciontxt.setEnabled(true);

        txt_NombreCliente.setText("");
        txt_ApellidoCliente.setText("");
        identificaciontxt.setSelectedIndex(0);
        numeroidtxt.setText("");
        telefonotxt.setText("");
        direcciontxt.setText("");
        limpiarCamposProducto();
        txt_total.setText("$0");
        txt_NombreCliente.requestFocus();
    }

    private boolean guardarCliente() {
        try {
            Cliente cliente = new Cliente();
            cliente.setIdentificacion(identificaciontxt.getSelectedItem().toString());
            cliente.setId_cliente(Integer.parseInt(numeroidtxt.getText().trim()));
            cliente.setNombre(txt_NombreCliente.getText().trim());
            cliente.setApellido(txt_ApellidoCliente.getText().trim());
            cliente.setTelefono(telefonotxt.getText().trim());
            cliente.setDireccion(direcciontxt.getText().trim());

            Ctrl_Cliente contro = new Ctrl_Cliente();
            if (contro.guardar(cliente)) {
                clienteIngresado = true;
                clienteCodigo = cliente.getId_cliente();
                bloquearCamposCliente();
                return true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar cliente: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

}
