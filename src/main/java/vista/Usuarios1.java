/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista;

import controlador.Ctrl_Cliente;
import controlador.Ctrl_Perfil;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import modelo.Conexion;


/**
 *
 * @author ZenBook
 */
public class Usuarios1 extends javax.swing.JPanel {
    private int id_usuario;
    private Ctrl_Perfil controlador;
    private int idUsuario;

    public Usuarios1() {
        controlador = new Ctrl_Perfil();
        this.id_usuario = -1; // Default to no selection
        initComponents();
        cargarTablaUsuarios();
        aplicarTema(); // Apply initial theme
        // Register for theme changes
        TemaManager.getInstance().addThemeChangeListener(() -> {
            aplicarTema(); // Update theme when it changes
        });
    }

    public void cargarTablaUsuarios() {
        tablaUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Código");
        model.addColumn("Nombre");
        model.addColumn("Apellido");
        model.addColumn("Usuario");
        model.addColumn("Correo electrónico");
        model.addColumn("Contraseña");
        model.addColumn("Rol");

        List<modelo.UsuarioModelo> usuarios = controlador.obtenerUsuarios();
        for (modelo.UsuarioModelo usuario : usuarios) {
            Object[] fila = new Object[7];
            fila[0] = usuario.getId_usuario();
            fila[1] = usuario.getNombre() != null ? usuario.getNombre() : "Sin nombre";
            fila[2] = usuario.getApellido() != null ? usuario.getApellido() : "Sin apellido";
            fila[3] = usuario.getUsuario() != null ? usuario.getUsuario() : "Sin usuario";
            fila[4] = usuario.getCorreo_electronico() != null ? usuario.getCorreo_electronico() : "Sin correo";
            fila[5] = usuario.getContrasena() != null ? usuario.getContrasena() : "********"; // Mask password
            fila[6] = usuario.getRol() != null ? usuario.getRol() : "Sin rol";
            model.addRow(fila);
        }

        tablaUsuarios.setModel(model);

        tablaUsuarios.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila_point = tablaUsuarios.rowAtPoint(e.getPoint());
                if (fila_point > -1) {
                    id_usuario = (int) tablaUsuarios.getValueAt(fila_point, 0);
                }
            }
        });
    }

 public void aplicarTema() {
        boolean oscuro = TemaManager.getInstance().isOscuro();

        if (oscuro) {
            Color fondo = new Color(21,21,33);
            Color primario = new Color(40, 60, 150);
            Color texto = Color.WHITE;

            jPanel1.setBackground(fondo);
            txtBuscar.setBackground(fondo);
            txtBuscar.setForeground(texto);
            txtBuscar.setColorIcon(texto);
            txtBuscar.setPhColor(Color.LIGHT_GRAY);
            
            
          tablaUsuarios.setBackground(new Color (21,21,33));
            tablaUsuarios.setBackgoundHead(new Color (67, 71, 120));
            tablaUsuarios.setForegroundHead(new Color (255,255,255));
            tablaUsuarios.setBackgoundHover(new Color(40, 50, 90));
         tablaUsuarios.setFont(new Font("Tahoma", Font.PLAIN, 15));
            tablaUsuarios.setColorPrimary(new Color(37,37,52));
            tablaUsuarios.setColorPrimaryText(texto);
            tablaUsuarios.setColorSecondary(new Color(30,30,45));
            tablaUsuarios.setColorSecundaryText(texto);
            tablaUsuarios.setColorBorderHead(primario);
            tablaUsuarios.setColorBorderRows(fondo.darker());
            tablaUsuarios.setFontHead(new Font("Tahoma", Font.BOLD, 15));
            tablaUsuarios.setFontRowHover(new Font("Tahoma", Font.BOLD, 15));
            tablaUsuarios.setFontRowSelect(new Font("Tahoma", Font.BOLD, 15));
            tablaUsuarios.setEffectHover(true);
              tablaUsuarios.setShowGrid(true);
tablaUsuarios.setGridColor(Color.WHITE); // o el color que desees

            btnNuevo1.setBackground(new Color(67, 71, 120));
            btnNuevo1.setBackgroundHover(new Color(118,142,240));
            btnEditar1.setBackground(new Color(67, 71, 120));
           btnEditar1.setBackgroundHover(new Color(118,142,240));
            btnEliminar1.setBackground(new Color(67, 71, 120));
            btnEliminar1.setBackgroundHover(new Color(118,142,240));
        } else {
            Color fondo = new Color(242, 247, 255);
            Color texto = Color.BLACK;
            Color primario = new Color(72, 92, 188);

            jPanel1.setBackground(fondo);
            txtBuscar.setBackground(fondo);
            txtBuscar.setForeground(texto);
            txtBuscar.setColorIcon(texto);
            txtBuscar.setPhColor(Color.GRAY);
            
            
      tablaUsuarios.setBackground(new Color (255,255,255));
            tablaUsuarios.setBackgoundHead(new Color (46,49,82));
            tablaUsuarios.setForegroundHead(Color.WHITE);
            tablaUsuarios.setBackgoundHover(new Color(67,150,209));
           tablaUsuarios.setFont(new Font("Tahoma", Font.PLAIN, 15));
            tablaUsuarios.setColorPrimary(new Color(242, 242, 242));
            tablaUsuarios.setColorPrimaryText(texto);
            tablaUsuarios.setColorSecondary(new Color(255, 255, 255));
            tablaUsuarios.setColorSecundaryText(texto);
            tablaUsuarios.setColorBorderHead(primario);
            tablaUsuarios.setColorBorderRows(new Color(0,0,0));
            tablaUsuarios.setFontHead(new Font("Tahoma", Font.BOLD, 15));
            tablaUsuarios.setFontRowHover(new Font("Tahoma", Font.BOLD, 15));
            tablaUsuarios.setFontRowSelect(new Font("Tahoma", Font.BOLD, 15));
            tablaUsuarios.setEffectHover(true);
            tablaUsuarios.setSelectionBackground(new Color(67,150,209));
            tablaUsuarios.setShowGrid(true);
tablaUsuarios.setGridColor(Color.BLACK); // o el color que desees

            btnNuevo1.setBackground(new Color(46, 49, 82));
            btnEditar1.setBackground(new Color(46, 49, 82));
            btnEliminar1.setBackground(new Color(46, 49, 82));
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
        btnEditar1 = new RSMaterialComponent.RSButtonShape();
        btnEliminar1 = new RSMaterialComponent.RSButtonShape();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaUsuarios = new RSMaterialComponent.RSTableMetroCustom();
        txtBuscar = new RSMaterialComponent.RSTextFieldMaterialIcon();

        setPreferredSize(new java.awt.Dimension(1290, 730));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(241, 245, 253));
        jPanel1.setPreferredSize(new java.awt.Dimension(960, 570));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnNuevo1.setBackground(new java.awt.Color(46, 49, 82));
        btnNuevo1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        btnNuevo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plus (2).png"))); // NOI18N
        btnNuevo1.setText(" Nuevo");
        btnNuevo1.setBackgroundHover(new java.awt.Color(67, 150, 209));
        btnNuevo1.setFont(new java.awt.Font("Roboto Bold", 1, 16)); // NOI18N
        btnNuevo1.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        btnNuevo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnNuevo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevo1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnNuevo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 110, 110, 30));

        btnEditar1.setBackground(new java.awt.Color(46, 49, 82));
        btnEditar1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        btnEditar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pencil (1)_1.png"))); // NOI18N
        btnEditar1.setText(" Editar");
        btnEditar1.setBackgroundHover(new java.awt.Color(67, 150, 209));
        btnEditar1.setFont(new java.awt.Font("Roboto Bold", 1, 16)); // NOI18N
        btnEditar1.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        btnEditar1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnEditar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditar1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnEditar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 110, 110, 30));

        btnEliminar1.setBackground(new java.awt.Color(46, 49, 82));
        btnEliminar1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        btnEliminar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/delete (1).png"))); // NOI18N
        btnEliminar1.setText(" Eliminar");
        btnEliminar1.setToolTipText("");
        btnEliminar1.setBackgroundHover(new java.awt.Color(67, 150, 209));
        btnEliminar1.setFont(new java.awt.Font("Roboto Bold", 1, 16)); // NOI18N
        btnEliminar1.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        btnEliminar1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnEliminar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminar1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 110, 110, 30));

        tablaUsuarios.setBackground(new java.awt.Color(67, 150, 209));
        tablaUsuarios.setForeground(new java.awt.Color(255, 255, 255));
        tablaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Codigo", "Nombre", "Apellido", "Usuario", "Correo Electronico", "Contraseña", "Rol"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaUsuarios.setToolTipText("");
        tablaUsuarios.setBackgoundHead(new java.awt.Color(255, 255, 51));
        tablaUsuarios.setBackgoundHover(new java.awt.Color(51, 255, 51));
        tablaUsuarios.setBorderHead(null);
        tablaUsuarios.setBorderRows(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        tablaUsuarios.setColorBorderHead(new java.awt.Color(102, 102, 255));
        tablaUsuarios.setColorBorderRows(new java.awt.Color(255, 102, 102));
        tablaUsuarios.setColorPrimary(new java.awt.Color(153, 255, 153));
        tablaUsuarios.setColorPrimaryText(new java.awt.Color(0, 0, 0));
        tablaUsuarios.setColorSecondary(new java.awt.Color(0, 204, 153));
        tablaUsuarios.setColorSecundaryText(new java.awt.Color(30, 30, 45));
        tablaUsuarios.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        tablaUsuarios.setFontHead(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        tablaUsuarios.setFontRowHover(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        tablaUsuarios.setFontRowSelect(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        tablaUsuarios.setGridColor(new java.awt.Color(102, 255, 102));
        tablaUsuarios.setPreferredSize(new java.awt.Dimension(500, 500));
        tablaUsuarios.setSelectionBackground(new java.awt.Color(67, 150, 209));
        jScrollPane3.setViewportView(tablaUsuarios);
        tablaUsuarios.getColumnModel().getColumn(0).setPreferredWidth(10);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, 1160, 530));

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
        jPanel1.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 430, 40));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1290, 730));
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo1ActionPerformed
        crear_usuario dialog = new crear_usuario(new javax.swing.JFrame(), true);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

        if (dialog.isGuardado()) {
            cargarTablaUsuarios(); // Recargar la tabla solo si se guardó correctamente
        }
    }//GEN-LAST:event_btnNuevo1ActionPerformed

    private void btnEditar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditar1ActionPerformed
  int selectedRow = tablaUsuarios.getSelectedRow(); // Assuming your table is named tablaUsuarios
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Por favor, seleccione un usuario para editar.");
        return;
    }

    int idUsuario = Integer.parseInt(tablaUsuarios.getValueAt(selectedRow, 0).toString()); // Assuming ID is in column 0
    editar_usuario dialog = new editar_usuario(new javax.swing.JFrame(), true, idUsuario, 
                                              (DefaultTableModel) tablaUsuarios.getModel(), selectedRow);
    dialog.setLocationRelativeTo(null);
    dialog.setVisible(true);
    if (dialog.isGuardado()) {
        cargarTablaUsuarios(); // Reload the table if needed
    }
       
    }//GEN-LAST:event_btnEditar1ActionPerformed

    private void btnEliminar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminar1ActionPerformed
    
    // Check if a row is selected
    int selectedRow = tablaUsuarios.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Por favor, seleccione un usuario para eliminar.");
        return;
    }

    // Get the user ID from the selected row (column 0 contains the ID)
    int idUsuario = Integer.parseInt(tablaUsuarios.getValueAt(selectedRow, 0).toString());

    // Confirm with the user before deleting
    int confirm = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro de que desea eliminar este usuario?", 
            "Confirmar eliminación", 
            JOptionPane.YES_NO_OPTION);
    
    if (confirm == JOptionPane.YES_OPTION) {
        // Call the controller to delete the user
        Ctrl_Perfil controlador = new Ctrl_Perfil();
        boolean eliminado = controlador.eliminar(idUsuario);

        if (eliminado) {
            JOptionPane.showMessageDialog(this, "Usuario eliminado correctamente.");
            cargarTablaUsuarios(); // Reload the table to reflect the deletion
        } else {
            JOptionPane.showMessageDialog(this, "Error al eliminar el usuario.");
        }
    }
    }//GEN-LAST:event_btnEliminar1ActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtBuscarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RSMaterialComponent.RSButtonShape btnEditar1;
    private RSMaterialComponent.RSButtonShape btnEliminar1;
    private RSMaterialComponent.RSButtonShape btnNuevo1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private RSMaterialComponent.RSTableMetroCustom tablaUsuarios;
    private RSMaterialComponent.RSTextFieldMaterialIcon txtBuscar;
    // End of variables declaration//GEN-END:variables
  
    
    




}

