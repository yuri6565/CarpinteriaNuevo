


package vista;

import controlador.Ctrl_Usuarios;
import javax.swing.JOptionPane;
import modelo.Consulta_Usuarios;



/**
 *
 * @author Personal
 */

public class Contrasena extends javax.swing.JFrame {
    private String correoIngresado= "";
  public Contrasena() {
    /**
     * Creates new form Contrasena
     */
    
        initComponents();
       this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("CARPINTERIA JOSE ABEL");
        
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        continuar = new rojeru_san.RSButton();
        jLabel8 = new javax.swing.JLabel();
        txtcorreo = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel8.setBackground(new java.awt.Color(29, 30, 51));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel9.setBackground(new java.awt.Color(29, 30, 51));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 30)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Restablece la contraseña");
        jPanel9.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 370, 50));

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("volver al inicio de sesion");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel5MousePressed(evt);
            }
        });
        jPanel9.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 490, 200, 30));

        jLabel6.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Dirección de correo electrónico");
        jPanel9.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 230, 30));

        jLabel7.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("un codigo de seguridad");
        jPanel9.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 500, 20));

        continuar.setBackground(new java.awt.Color(204, 204, 204));
        continuar.setText("Continuar");
        continuar.setColorHover(new java.awt.Color(0, 112, 192));
        continuar.setColorText(new java.awt.Color(0, 0, 0));
        continuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                continuarActionPerformed(evt);
            }
        });
        jPanel9.add(continuar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 480, 40));

        jLabel8.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Escribe el correo electronico de tu cuenta para que te enviemos");
        jPanel9.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 500, 20));

        txtcorreo.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        txtcorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcorreoActionPerformed(evt);
            }
        });
        jPanel9.add(txtcorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 480, 40));

        jPanel8.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 520, 580));

        getContentPane().add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 0, 610, 640));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MousePressed
    login lg = new login();
            lg.setVisible(true);
            dispose();
    }//GEN-LAST:event_jLabel5MousePressed

    private void continuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_continuarActionPerformed
     if (txtcorreo == null) {
        System.out.println("ERROR: txtcorreo es NULL.");
        return;
    }
    
    String correo = txtcorreo.getText().trim();
    
    if (correo.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor, ingrese un correo válido.");
        return;
    }

    Ctrl_Usuarios ctrl = new Ctrl_Usuarios();
    Consulta_Usuarios consulta_Usuarios = new Consulta_Usuarios();
    String usuario = consulta_Usuarios.obtenerCodigoDesdeCorreo(correo);

    if (usuario != null) {
        boolean enviado = ctrl.enviarCodigoRecuperacion(usuario, correo);
        if (enviado) {
            txtcorreo.setText("");
            JOptionPane.showMessageDialog(this, "Código enviado con éxito.");
            
       
            Codigo1 con = new Codigo1();
            con.setcorreoIngresado(correo);
            con.setVisible(true);
            this.dispose();
            
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo enviar el código.");
        }
    } else {
        JOptionPane.showMessageDialog(this, "El correo no está registrado.");
    }

    
     
    }//GEN-LAST:event_continuarActionPerformed

    private void txtcorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcorreoActionPerformed
    
    }//GEN-LAST:event_txtcorreoActionPerformed

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
            java.util.logging.Logger.getLogger(Contrasena.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Contrasena.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Contrasena.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Contrasena.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Contrasena().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSButton continuar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTextField txtcorreo;
    // End of variables declaration//GEN-END:variables
}
