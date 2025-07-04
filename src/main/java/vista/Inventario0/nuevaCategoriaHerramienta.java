/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package vista.Inventario0;

import controlador.Ctrl_CategoriaHerramienta;
import java.awt.Frame;
import javax.swing.JOptionPane;
import modelo.Categoria;
import vista.alertas.AlerGuardadoExitgeneral;
import vista.alertas.MaterialingreseCategoria;

/**
 *
 * @author ZenBook
 */
public class nuevaCategoriaHerramienta extends javax.swing.JDialog {

    private boolean guardado = false; // Indica si se presionó "Guardar"

    /**
     * Creates new form nuevaCategoriaHerramienta
     */
    public nuevaCategoriaHerramienta(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public interface CategoriaGuardadaListener {

        void onCategoriaGuardada();
    }

    private CategoriaGuardadaListener listener;

    public void setCategoriaGuardadaListener(CategoriaGuardadaListener listener) {
        this.listener = listener;
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
        btnSalir1 = new rojeru_san.RSButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNombre = new RSMaterialComponent.RSTextFieldOne();
        jSeparator1 = new javax.swing.JSeparator();
        btnGuardar = new RSMaterialComponent.RSButtonShape();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnSalir1.setBackground(new java.awt.Color(255, 255, 255));
        btnSalir1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/x (3).png"))); // NOI18N
        btnSalir1.setColorHover(new java.awt.Color(211, 0, 52));
        btnSalir1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSalir1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSalir1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSalir1MouseExited(evt);
            }
        });
        btnSalir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalir1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnSalir1, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 1, 27, 22));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel4.setText("Nombre:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel5.setText("Agregar categoría");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 8, -1, -1));

        txtNombre.setForeground(new java.awt.Color(0, 0, 0));
        txtNombre.setBorderColor(new java.awt.Color(204, 204, 204));
        txtNombre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNombre.setName(""); // NOI18N
        txtNombre.setPhColor(new java.awt.Color(0, 0, 0));
        txtNombre.setPlaceholder("Ingrese el nombre...");
        txtNombre.setSelectionColor(new java.awt.Color(0, 0, 0));
        txtNombre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNombreMouseClicked(evt);
            }
        });
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombreKeyPressed(evt);
            }
        });
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 200, 30));

        jSeparator1.setForeground(new java.awt.Color(102, 102, 102));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 34, 240, 10));

        btnGuardar.setBackground(new java.awt.Color(46, 49, 82));
        btnGuardar.setText("Guardar");
        btnGuardar.setBackgroundHover(new java.awt.Color(0, 135, 0));
        btnGuardar.setFont(new java.awt.Font("Roboto Bold", 1, 15)); // NOI18N
        btnGuardar.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        btnGuardar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 97, 28));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 180));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalir1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalir1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalir1MouseClicked

    private void btnSalir1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalir1MouseEntered
        btnSalir1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/x (1).png")));
    }//GEN-LAST:event_btnSalir1MouseEntered

    private void btnSalir1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalir1MouseExited
        btnSalir1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/x (3).png")));        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalir1MouseExited

    private void btnSalir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalir1ActionPerformed
        guardado = false;
        setVisible(false); // Cerrar el diálogo
    }//GEN-LAST:event_btnSalir1ActionPerformed

    private void txtNombreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNombreMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreMouseClicked

    private void txtNombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyPressed

    }//GEN-LAST:event_txtNombreKeyPressed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        String nombre = txtNombre.getText().trim();

        if (!nombre.isEmpty()) {
            Categoria categoria = new Categoria(nombre);
            Ctrl_CategoriaHerramienta dao = new Ctrl_CategoriaHerramienta();

            if (dao.insertar(categoria)) {
                AlerGuardadoExitgeneral dialog = new AlerGuardadoExitgeneral((Frame) this.getParent(), true);
                dialog.setVisible(true);
                txtNombre.setText("");

                // Notificar al listener que se guardó una categoría
                if (listener != null) {
                    listener.onCategoriaGuardada();
                }

            } else {
                JOptionPane.showMessageDialog(this, "Error al añadir categoría.");
            }
        } else {
            MaterialingreseCategoria dialog = new MaterialingreseCategoria((Frame) this.getParent(), true);
            dialog.setVisible(true);
        }

    }//GEN-LAST:event_btnGuardarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(nuevaCategoriaHerramienta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(nuevaCategoriaHerramienta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(nuevaCategoriaHerramienta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(nuevaCategoriaHerramienta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                nuevaCategoriaHerramienta dialog = new nuevaCategoriaHerramienta(new javax.swing.JFrame(), true);
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RSMaterialComponent.RSButtonShape btnGuardar;
    private rojeru_san.RSButton btnSalir1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private RSMaterialComponent.RSTextFieldOne txtNombre;
    // End of variables declaration//GEN-END:variables
}
