


package vista;

import controlador.Ctrl_Usuarios;
import javax.swing.JOptionPane;
import modelo.Consulta_Usuarios;



/**
 *
 * @author Personal
 */

public class Nueva_contrasena1 extends javax.swing.JFrame {
    private String correoIngresado= "";
     private String resultado;

  public Nueva_contrasena1() {
    /**
     * Creates new form Contrasena
     */
    
        initComponents();
       this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("CARPINTERIA JOSE ABEL");
        
    }
  public void setcorreoIngresado(String correo){
      this.correoIngresado=correo;
  }
     

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        continuar = new rojeru_san.RSButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        rSButtonPane1 = new rojerusan.RSButtonPane();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtNuevaContrasena = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel8.setBackground(new java.awt.Color(29, 30, 51));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel9.setBackground(new java.awt.Color(29, 30, 51));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        continuar.setBackground(new java.awt.Color(204, 204, 204));
        continuar.setText("Continuar");
        continuar.setColorHover(new java.awt.Color(0, 112, 192));
        continuar.setColorText(new java.awt.Color(0, 0, 0));
        continuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                continuarActionPerformed(evt);
            }
        });
        jPanel9.add(continuar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 520, 480, 40));

        jLabel9.setFont(new java.awt.Font("SansSerif", 0, 30)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Crear nueva contraseña");
        jPanel9.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 330, 40));

        jLabel10.setFont(new java.awt.Font("SansSerif", 0, 17)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Introduce una nueva contraseña");
        jPanel9.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 470, 20));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nueva contraseña");
        jPanel9.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 500, 20));

        rSButtonPane1.setBackground(new java.awt.Color(29, 30, 51));
        rSButtonPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        rSButtonPane1.setColorHover(new java.awt.Color(29, 30, 51));
        rSButtonPane1.setColorNormal(new java.awt.Color(29, 30, 51));
        rSButtonPane1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Usar almenos un caracter especial");
        rSButtonPane1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 260, 20));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Debes:");
        rSButtonPane1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 60, 20));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Usar almenos 8 carácteres");
        rSButtonPane1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 200, 20));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Usar almenos una letra");
        rSButtonPane1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 260, 20));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Usar almenos un número");
        rSButtonPane1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 260, 20));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("No usar espacio");
        rSButtonPane1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 260, 20));

        jPanel9.add(rSButtonPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 480, 160));

        txtNuevaContrasena.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        txtNuevaContrasena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNuevaContrasenaActionPerformed(evt);
            }
        });
        jPanel9.add(txtNuevaContrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 480, 40));

        jPanel8.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 520, 590));

        jLabel15.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("volver al inicio de sesion");
        jLabel15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel15MousePressed(evt);
            }
        });
        jPanel8.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 640, 200, 30));

        getContentPane().add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 0, 600, 700));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void continuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_continuarActionPerformed

        String nuevaContrasena = txtNuevaContrasena.getText().trim();
        if (nuevaContrasena.isEmpty()) {
            JOptionPane.showMessageDialog(this, "error, debe ingresar una contraseña, el campo no puede ir vacio");
            return;
        }
        
      

   Consulta_Usuarios consulta = new Consulta_Usuarios();
    boolean actualizada = consulta.actualizarContrasena(correoIngresado, nuevaContrasena);

    if (actualizada) {
        JOptionPane.showMessageDialog(this, "Contraseña actualizada con éxito.");
        login lg = new login();
       lg.setVisible(true);
         dispose();
    } else {
        JOptionPane.showMessageDialog(this, "Error al actualizar la contraseña.");
    }  
    
     
    }//GEN-LAST:event_continuarActionPerformed

    private void jLabel15MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MousePressed

    }//GEN-LAST:event_jLabel15MousePressed

    private void txtNuevaContrasenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNuevaContrasenaActionPerformed

    }//GEN-LAST:event_txtNuevaContrasenaActionPerformed

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
            java.util.logging.Logger.getLogger(Nueva_contrasena1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Nueva_contrasena1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Nueva_contrasena1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Nueva_contrasena1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Nueva_contrasena1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSButton continuar;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private rojerusan.RSButtonPane rSButtonPane1;
    private javax.swing.JTextField txtNuevaContrasena;
    // End of variables declaration//GEN-END:variables

}
