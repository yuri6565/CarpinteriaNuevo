/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista.ProveedoresUsu;

import vista.proveedor.*;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import vista.TemaManager;



public class proveedores extends javax.swing.JPanel {
    private DefaultTableModel modeloTabla;

    public proveedores() {
        initComponents();

        TablaP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        TablaP.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "Nombre", "correo", "Telefono", "Direccion"}
        ));

        TablaP.setCellSelectionEnabled(false);
        TablaP.setRowSelectionAllowed(true);
        TablaP.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        Color colorSeleccion = new Color(109, 160, 221);
        Color colorTexto = Color.white;

        TablaP.setSelectionBackground(colorSeleccion);
        TablaP.setSelectionForeground(colorTexto);

        txtbuscar.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filtrarTabla();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filtrarTabla();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filtrarTabla();
            }
        });

        cargarTablaProveedor(); // Carga Tabla1
        aplicarTema(); // Apply initial theme
        // Register for theme changes
        TemaManager.getInstance().addThemeChangeListener(() -> {
            aplicarTema(); // Update theme when it changes
        });
    }

    public void aplicarTema() {
        if (TemaManager.getInstance().isOscuro()) {
            Color fondo = new Color(18, 18, 28);
            Color primario = new Color(40, 60, 150);
            Color texto = Color.WHITE;

            jPanel1.setBackground(fondo);
            jPanel2.setBackground(fondo);
            jPanel3.setBackground(fondo);
            jPanel4.setBackground(fondo);
            txtbuscar.setBackground(fondo);
            txtbuscar.setForeground(texto);
            txtbuscar.setColorIcon(texto);
            txtbuscar.setPhColor(Color.LIGHT_GRAY);

            TablaP.setBackgoundHead(primario);
            TablaP.setForegroundHead(Color.WHITE);
            TablaP.setBackgoundHover(new Color(40, 50, 90));
            TablaP.setForegroundHover(Color.WHITE);
            TablaP.setColorPrimary(fondo);
            TablaP.setColorPrimaryText(texto);
            TablaP.setColorSecondary(fondo.darker());
            TablaP.setColorSecundaryText(texto);
            TablaP.setColorBorderHead(primario);
            TablaP.setColorBorderRows(fondo.darker());
            TablaP.setFontHead(new Font("Segoe UI", Font.BOLD, 12));
            TablaP.setFontRowHover(new Font("Segoe UI", Font.PLAIN, 12));
            TablaP.setFontRowSelect(new Font("Segoe UI", Font.PLAIN, 12));
            TablaP.setEffectHover(true);

       
        } else {
            Color fondo = new Color(242, 247, 255);
            Color texto = Color.BLACK;
            Color primario = new Color(72, 92, 188);

            jPanel1.setBackground(fondo);
            jPanel2.setBackground(fondo);
            jPanel3.setBackground(fondo);
            jPanel4.setBackground(fondo);
            txtbuscar.setBackground(fondo);
            txtbuscar.setForeground(texto);
            txtbuscar.setColorIcon(texto);
            txtbuscar.setPhColor(Color.GRAY);

            TablaP.setBackgoundHead(primario);
            TablaP.setForegroundHead(Color.WHITE);
            TablaP.setBackgoundHover(new Color(220, 220, 240));
            TablaP.setForegroundHover(texto);
            TablaP.setColorPrimary(fondo);
            TablaP.setColorPrimaryText(texto);
            TablaP.setColorSecondary(new Color(245, 245, 245));
            TablaP.setColorSecundaryText(texto);
            TablaP.setColorBorderHead(primario);
            TablaP.setColorBorderRows(new Color(200, 200, 200));
            TablaP.setFontHead(new Font("Segoe UI", Font.BOLD, 12));
            TablaP.setFontRowHover(new Font("Segoe UI", Font.PLAIN, 12));
            TablaP.setFontRowSelect(new Font("Segoe UI", Font.PLAIN, 12));
            TablaP.setEffectHover(true);

        }
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        txtbuscar = new RSMaterialComponent.RSTextFieldMaterialIcon();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaP = new RSMaterialComponent.RSTableMetro();

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(1290, 730));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtbuscar.setForeground(new java.awt.Color(29, 30, 91));
        txtbuscar.setColorIcon(new java.awt.Color(29, 30, 111));
        txtbuscar.setColorMaterial(new java.awt.Color(29, 30, 111));
        txtbuscar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SEARCH);
        txtbuscar.setPlaceholder("Buscar");
        txtbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbuscarActionPerformed(evt);
            }
        });
        jPanel4.add(txtbuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, 28));

        TablaP.setForeground(new java.awt.Color(255, 255, 255));
        TablaP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        TablaP.setAlignmentX(0.1F);
        TablaP.setAlignmentY(0.1F);
        TablaP.setBackgoundHead(new java.awt.Color(46, 49, 82));
        TablaP.setBackgoundHover(new java.awt.Color(46, 49, 82));
        TablaP.setColorBorderRows(new java.awt.Color(153, 153, 153));
        TablaP.setColorPrimaryText(new java.awt.Color(46, 49, 82));
        TablaP.setColorSecondary(new java.awt.Color(255, 255, 255));
        TablaP.setColorSecundaryText(new java.awt.Color(46, 49, 82));
        jScrollPane2.setViewportView(TablaP);

        jPanel4.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 1190, 490));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 1293, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1293, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1293, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1293, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents
 
    private void txtbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbuscarActionPerformed
        
    }//GEN-LAST:event_txtbuscarActionPerformed

    
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RSMaterialComponent.RSTableMetro TablaP;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private RSMaterialComponent.RSTextFieldMaterialIcon txtbuscar;
    // End of variables declaration//GEN-END:variables

public class Conexion {

        public Connection getConnection() {
            Connection con = null;
            try {
                String myBD = "jdbc:mysql://localhost:3306/carpinteriasistema?serverTimezone=UTC";
                con = DriverManager.getConnection(myBD, "root", "");
                System.out.println("Conexión exitosa.");
            } catch (SQLException e) {
                System.out.println("Error al conectar: " + e.getMessage());
            }
            return con;
        }
    }

public void cargarTablaProveedor() {
        try (Connection con = new proveedores.Conexion().getConnection(); PreparedStatement ps = con.prepareStatement("SELECT id_proveedor, nombre,correo_electronico, telefono, direccion  FROM proveedor"); ResultSet rs = ps.executeQuery()) {

            DefaultTableModel model = (DefaultTableModel) TablaP.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id_proveedor"),
                    rs.getString("nombre"),
                    rs.getString("correo_electronico"),
                    rs.getString("telefono"),
                    rs.getString("direccion")});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar datos: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }



private void filtrarTabla(){
    String textobusqueda = txtbuscar.getText().trim();
    DefaultTableModel modelo = (DefaultTableModel)TablaP.getModel();
    
    TableRowSorter<DefaultTableModel>tr= new TableRowSorter<>(modelo);
    TablaP.setRowSorter(tr);
    
    if (textobusqueda.isEmpty()){
        tr.setRowFilter(null);
        
        return;
        
    }
    
    List<RowFilter<Object, Object>>filters = new ArrayList<>();
    
    if(textobusqueda.matches("\\d+")){
        filters.add(RowFilter.regexFilter("^"+textobusqueda, 0));
    }
    
    filters.add(RowFilter.regexFilter("(?i)"+ textobusqueda ,  1));
    
    filters.add(RowFilter.regexFilter("(?i)"+ textobusqueda ,  2));
    filters.add(RowFilter.regexFilter("(?i)"+ textobusqueda ,  3));
    
    filters.add(RowFilter.regexFilter("(?i)"+ textobusqueda ,  4));
    
    tr.setRowFilter(RowFilter.orFilter(filters));
    
}







}


