/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista;

import java.awt.Color;
import java.util.Random;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author buitr
 */
public class proveedores extends javax.swing.JPanel {
private DefaultTableModel modeloTabla;
    /**
     * Creates new form proveedores
     */
    public proveedores() {
        initComponents();
      rSTextFieldMaterialIcon1.getDocument().addDocumentListener(new DocumentListener() {
         

            @Override
            public void insertUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                filterTables();
            }
      });

 initComponents();
             TablaP.setBackground(new Color(245,246,250)); // Blanco grisáceo
     TablaP.setOpaque(true);
     TablaP.setFillsViewportHeight(true); // Ajusta el fondo en toda la tabla
        
     TablaP.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        
     modeloTabla = new DefaultTableModel(){
    @Override
    public boolean isCellEditable(int row, int column) {
        return false; // Evita la edición en todas las celdas
    }
};
     modeloTabla.setColumnIdentifiers(new String[]{ "Codigo", "Nombre", "Telefono", "Direccion", "Producto", "Tipo"});
     TablaP.setModel(modeloTabla);
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        editarBtn = new RSMaterialComponent.RSButtonMaterialTwo();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaP = new RSMaterialComponent.RSTableMetroCustom();
        Añadir1 = new RSMaterialComponent.RSButtonMaterialTwo();
        Buscar1 = new RSMaterialComponent.RSButtonMaterialTwo();
        eliminarBtn = new RSMaterialComponent.RSButtonMaterialTwo();
        rSTextFieldMaterialIcon1 = new RSMaterialComponent.RSTextFieldMaterialIcon();

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(1290, 730));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        editarBtn.setBackground(new java.awt.Color(29, 30, 51));
        editarBtn.setText("Editar");
        editarBtn.setBackgroundHover(new java.awt.Color(29, 30, 31));
        editarBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editarBtnMouseClicked(evt);
            }
        });
        editarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarBtnActionPerformed(evt);
            }
        });
        jPanel4.add(editarBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 40, 86, 37));

        TablaP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        TablaP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nombre", "Telefono", "Direccion", "Producto", "Tipo"
            }
        ));
        TablaP.setBackgoundHead(new java.awt.Color(29, 30, 51));
        TablaP.setBackgoundHover(new java.awt.Color(29, 30, 51));
        TablaP.setColorPrimaryText(new java.awt.Color(0, 0, 0));
        TablaP.setColorSecundaryText(new java.awt.Color(0, 0, 0));
        TablaP.setSelectionBackground(new java.awt.Color(29, 30, 91));
        jScrollPane2.setViewportView(TablaP);

        jPanel4.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 1040, 480));

        Añadir1.setBackground(new java.awt.Color(29, 30, 51));
        Añadir1.setText("Añadir+");
        Añadir1.setBackgroundHover(new java.awt.Color(29, 30, 31));
        Añadir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Añadir1ActionPerformed(evt);
            }
        });
        jPanel4.add(Añadir1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 40, 86, 37));

        Buscar1.setBackground(new java.awt.Color(29, 30, 51));
        Buscar1.setText("Buscar");
        Buscar1.setBackgroundHover(new java.awt.Color(29, 30, 31));
        Buscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Buscar1ActionPerformed(evt);
            }
        });
        jPanel4.add(Buscar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 40, 86, 37));

        eliminarBtn.setBackground(new java.awt.Color(29, 30, 51));
        eliminarBtn.setText("Eliminar -");
        eliminarBtn.setBackgroundHover(new java.awt.Color(29, 30, 31));
        eliminarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarBtnActionPerformed(evt);
            }
        });
        jPanel4.add(eliminarBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 40, 86, 37));

        rSTextFieldMaterialIcon1.setForeground(new java.awt.Color(29, 30, 91));
        rSTextFieldMaterialIcon1.setColorIcon(new java.awt.Color(29, 30, 111));
        rSTextFieldMaterialIcon1.setColorMaterial(new java.awt.Color(29, 30, 111));
        rSTextFieldMaterialIcon1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SEARCH);
        rSTextFieldMaterialIcon1.setPlaceholder("Buscar");
        rSTextFieldMaterialIcon1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSTextFieldMaterialIcon1ActionPerformed(evt);
            }
        });
        jPanel4.add(rSTextFieldMaterialIcon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, 28));

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
 private void agregarFilaATabla(String[] datos) {
    modeloTabla.addRow(datos);
}
    private void editarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarBtnActionPerformed

 int filaSeleccionada = TablaP.getSelectedRow();
        if (filaSeleccionada >= 0) {
            String[] datosFila = new String[5];
            for (int i = 0; i < 5; i++) {
                datosFila[i] = (String) TablaP.getValueAt(filaSeleccionada, i);
            }

            proveedorEditar dialogoEditar = new proveedorEditar(null, true);
            dialogoEditar.cargarDatos(datosFila);
            dialogoEditar.setLocationRelativeTo(null);
            dialogoEditar.setVisible(true);

            if (dialogoEditar.isGuardado()) {
                String[] nuevosDatos = dialogoEditar.getDatos();
                for (int i = 0; i < 5; i++) {
                    TablaP.setValueAt(nuevosDatos[i], filaSeleccionada, i);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una fila para editar.");
        }       

         
    }//GEN-LAST:event_editarBtnActionPerformed

    private void Añadir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Añadir1ActionPerformed
        // TODO add your handling code here:
        proveedornuevo dialog = new proveedornuevo(new javax.swing.JFrame(), true);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

        if (dialog.isGuardado()) {
            String[] datos = dialog.getDatos();
            agregarFilaATabla(datos);
        }

    }//GEN-LAST:event_Añadir1ActionPerformed

    private void Buscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Buscar1ActionPerformed
filterTables();
    }//GEN-LAST:event_Buscar1ActionPerformed

    private void eliminarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarBtnActionPerformed
        // TODO add your handling code here:
        int[] filasSeleccionadas = TablaP.getSelectedRows();

    if (filasSeleccionadas.length > 0) {
        int confirmacion = JOptionPane.showConfirmDialog(
            null,
            "¿Está seguro de que desea eliminar los movimientos seleccionados?",
            "Confirmar Eliminación",
            JOptionPane.YES_NO_OPTION
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            DefaultTableModel modelo = (DefaultTableModel) TablaP.getModel();

            // Eliminar desde la última fila seleccionada para evitar problemas de índice
            for (int i = filasSeleccionadas.length - 1; i >= 0; i--) {
                modelo.removeRow(filasSeleccionadas[i]);
            }
        }
    } else {
        JOptionPane.showMessageDialog(null, "Seleccione al menos una fila para eliminar.");
    }
    }//GEN-LAST:event_eliminarBtnActionPerformed

    private void rSTextFieldMaterialIcon1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSTextFieldMaterialIcon1ActionPerformed
        filterTables();
    }//GEN-LAST:event_rSTextFieldMaterialIcon1ActionPerformed

    private void editarBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editarBtnMouseClicked
        // TODO add your handling code here:
                                           
    try {
        int filaSeleccionada = TablaP.getSelectedRow();
        if (filaSeleccionada >= 0) {
            String[] datosFila = new String[6];
            for (int i = 0; i < 6; i++) {
                datosFila[i] = (String) TablaP.getValueAt(filaSeleccionada, i);
            }

            proveedorEditar dialogoEditar = new proveedorEditar(null, true);
            dialogoEditar.cargarDatos(datosFila);
            dialogoEditar.setLocationRelativeTo(null);
            dialogoEditar.setVisible(true); // Corrige el nombre de la variable aquí

            if (dialogoEditar.isGuardado()) {
                String[] nuevosDatos = dialogoEditar.getDatos();
                for (int i = 0; i < 6; i++) {
                    TablaP.setValueAt(nuevosDatos[i], filaSeleccionada, i);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una fila para editar.");
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al abrir el editor: " + e.getMessage());
    }

    }//GEN-LAST:event_editarBtnMouseClicked

    
    private int generateRandomId(int digits) {
    Random random = new Random();
    int lowerBound = (int) Math.pow(10, digits - 1); // 10^(n-1)
    int upperBound = (int) Math.pow(10, digits) - 1; // 10^n - 1
    return random.nextInt(upperBound - lowerBound + 1) + lowerBound;
}

    private boolean idExists(int id) {
    DefaultTableModel model = (DefaultTableModel) TablaP.getModel();
    for (int i = 0; i < model.getRowCount(); i++) {
        if ((Integer) model.getValueAt(i, 0) == id) { // Asegúrate de que estás verificando la columna correcta
            return true; // El ID ya existe
        }
    }
    return false; // El ID no existe
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RSMaterialComponent.RSButtonMaterialTwo Añadir1;
    private RSMaterialComponent.RSButtonMaterialTwo Buscar1;
    private RSMaterialComponent.RSTableMetroCustom TablaP;
    private RSMaterialComponent.RSButtonMaterialTwo editarBtn;
    private RSMaterialComponent.RSButtonMaterialTwo eliminarBtn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private RSMaterialComponent.RSTextFieldMaterialIcon rSTextFieldMaterialIcon1;
    // End of variables declaration//GEN-END:variables
private void filterTables() {
        String searchText = rSTextFieldMaterialIcon1.getText().toLowerCase(); // Obtener el texto de búsqueda

        // Filtrar cada tabla
        if (searchText.length() == 1) {
            // Filtrar por la columna "#" (índice 0)
            filterTable(TablaP, searchText, 0); // Cambia 0 por el índice de la columna "#" en Tabla1
            
        } else if (searchText.length() >= 2) {
            // Filtrar en todas las columnas
            filterTable(TablaP, searchText, -1); // Mostrar todas las filas en Tabla1
           
        } else {
            // Si no hay texto, mostrar todas las filas
            filterTable(TablaP, "", -1); // Mostrar todas las filas en Tabla1
            
        }
    }

private void filterTable(JTable table, String searchText, int columnIndex) {
        DefaultTableModel model = (DefaultTableModel) table.getModel(); // Obtener el modelo de la tabla
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model); // Crear un sorter para la tabla
        table.setRowSorter(sorter); // Establecer el sorter en la tabla

        // Filtrar las filas
        if (searchText.trim().isEmpty()) {
            sorter.setRowFilter(null); // Si no hay texto, mostrar todas las filas
        } else {
            // Filtrar en la columna especificada o en todas las columnas si columnIndex es -1
            if (columnIndex == -1) {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + Pattern.quote(searchText))); // Filtrar en todas las columnas
            } else {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + Pattern.quote(searchText), columnIndex)); // Filtrar en la columna especificada
            }
        }
    }
}

