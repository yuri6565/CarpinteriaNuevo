/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;





import vista.alertas.CodigoIncorrectoAlerta;
import vista.alertas.ValidacionCodigoExitoso;
import controlador.Ctrl_Usuarios;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import javax.swing.text.Document;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import java.util.Arrays;
import java.util.List;
import modelo.Consulta_Usuarios;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.stream.Collectors;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;




/**
 *
 * @author Personal
*/

public class Correo_electronico extends javax.swing.JFrame {

    // List of common email domains
    private final List<String> emailDomains = Arrays.asList(
        "@gmail.com", "@hotmail.com", "@yahoo.com", "@outlook.com", "@icloud.com"
    );
    private int currentDomainIndex = -1;
    private String userPart = "";
    private List<String> matchingDomains = Arrays.asList();
    private boolean isUpdating = false; // Flag to prevent re-entry

    public Correo_electronico() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setLocationRelativeTo(null); 

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER; 
        add(kGradientPanel1, gbc);

        JPanel fondo = new JPanel(new BorderLayout());
        fondo.add(kGradientPanel1, BorderLayout.CENTER);
        setContentPane(fondo);

        // Setup autocomplete for txtcorreo
        setupAutoComplete();

        // Debug to confirm initialization
        System.out.println("Correo_electronico initialized");
    }

    private void setupAutoComplete() {
        // Add DocumentListener to txtcorreo
        Document doc = txtcorreo.getDocument();
        doc.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (!isUpdating) {
                    SwingUtilities.invokeLater(() -> updateSuggestions());
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (!isUpdating) {
                    SwingUtilities.invokeLater(() -> updateSuggestions());
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (!isUpdating) {
                    SwingUtilities.invokeLater(() -> updateSuggestions());
                }
            }
        });

        // Add KeyListener for cycling through domains and deletion
        txtcorreo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    cycleDomain(-1); 
                    System.out.println("Cycled up to: " + (currentDomainIndex >= 0 && !matchingDomains.isEmpty() ? matchingDomains.get(currentDomainIndex) : "none"));
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    cycleDomain(1); 
                    System.out.println("Cycled down to: " + (currentDomainIndex >= 0 && !matchingDomains.isEmpty() ? matchingDomains.get(currentDomainIndex) : "none"));
                } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    currentDomainIndex = -1; 
                    System.out.println("Enter pressed, selection finalized");
                } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_DELETE) {
                    // Handle deletion
                    String text = txtcorreo.getText().trim();
                    if (text.isEmpty() || !text.contains("@")) {
                        matchingDomains = Arrays.asList();
                        currentDomainIndex = -1;
                    } else {
                        int atIndex = text.lastIndexOf("@");
                        userPart = text.substring(0, atIndex);
                        String domainPart = text.substring(atIndex).replaceAll("[^@a-zA-Z0-9.]", "");
                        if (domainPart.length() <= 1) {
                            matchingDomains = Arrays.asList();
                            currentDomainIndex = -1;
                        }
                    }
                    System.out.println("Key pressed: " + KeyEvent.getKeyText(e.getKeyCode()));
                }
            }
        });
    }

    private void updateSuggestions() {
        if (isUpdating) return; // Prevent re-entry
        isUpdating = true;

        try {
            String text = txtcorreo.getText().trim();
            System.out.println("Text updated: " + text); // Debug input

            currentDomainIndex = -1; // Reset index on every update

            if (text.contains("@")) {
                // Remove any trailing non-domain characters after @
                int atIndex = text.lastIndexOf("@");
                userPart = text.substring(0, atIndex);
                String domainPart = text.substring(atIndex).replaceAll("[^@a-zA-Z0-9.]", ""); // Clean non-domain chars
                System.out.println("Domain part: " + domainPart); // Debug prefix

                // Only proceed with autocomplete if a letter follows @
                if (domainPart.length() > 1) { // Ensure at least one letter after @
                    // Filter matching domains based on the letter after @
                    matchingDomains = emailDomains.stream()
                        .filter(domain -> domain.toLowerCase().startsWith("@" + domainPart.toLowerCase().replace("@", "").substring(0, 1)))
                        .collect(Collectors.toList());
                    System.out.println("Matching domains: " + matchingDomains); // Debug filtered list

                    // Set to first match if available
                    if (!matchingDomains.isEmpty()) {
                        currentDomainIndex = 0; // Start with first match
                        String newText = userPart + matchingDomains.get(currentDomainIndex);
                        if (!txtcorreo.getText().equals(newText)) {
                            txtcorreo.setText(newText);
                            txtcorreo.setCaretPosition(newText.length()); // Move caret to end only when applying suggestion
                            System.out.println("Suggestion applied: " + matchingDomains.get(currentDomainIndex));
                        }
                    } else {
                        System.out.println("No matching domain found");
                    }
                } else {
                    matchingDomains = Arrays.asList(); // Reset if no letter after @
                    System.out.println("No letter after @, no suggestion applied");
                }
            } else {
                matchingDomains = Arrays.asList();
                System.out.println("No @ found, resetting index");
            }
        } finally {
            isUpdating = false; // Ensure flag is reset
        }
    }

    private void cycleDomain(int direction) {
        if (userPart.isEmpty() || matchingDomains.isEmpty()) return;

        currentDomainIndex += direction;
        if (currentDomainIndex < 0) {
            currentDomainIndex = matchingDomains.size() - 1; // Loop to last
        } else if (currentDomainIndex >= matchingDomains.size()) {
            currentDomainIndex = 0; // Loop to first
        }
        String newText = userPart + matchingDomains.get(currentDomainIndex);
        txtcorreo.setText(newText);
        txtcorreo.setCaretPosition(newText.length()); // Move caret to end
        System.out.println("Cycled to: " + matchingDomains.get(currentDomainIndex));
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        kGradientPanel1 = new keeptoo.KGradientPanel();
        jPanel6 = new javax.swing.JPanel();
        rSPanelImage1 = new rojerusan.RSPanelImage();
        jLabel11 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtcorreo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        rSButtonRound1 = new rojerusan.RSButtonRound();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        kGradientPanel1.setkEndColor(new java.awt.Color(239, 248, 255));
        kGradientPanel1.setkStartColor(new java.awt.Color(254, 254, 254));
        kGradientPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSPanelImage1.setImagen(new javax.swing.ImageIcon(getClass().getResource("/logo_azul.png"))); // NOI18N

        javax.swing.GroupLayout rSPanelImage1Layout = new javax.swing.GroupLayout(rSPanelImage1);
        rSPanelImage1.setLayout(rSPanelImage1Layout);
        rSPanelImage1Layout.setHorizontalGroup(
            rSPanelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
        );
        rSPanelImage1Layout.setVerticalGroup(
            rSPanelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
        );

        jPanel6.add(rSPanelImage1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 90, 90));

        jLabel11.setFont(new java.awt.Font("SansSerif", 1, 30)); // NOI18N
        jLabel11.setText("Restablece la contraseña");
        jPanel6.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, -1, -1));

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel7.setText("Email");
        jPanel6.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, -1, 20));

        txtcorreo.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        txtcorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcorreoActionPerformed(evt);
            }
        });
        jPanel6.add(txtcorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 410, 40));

        jLabel8.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jLabel8.setText("volver al inicio de sesion");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel8MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel8MouseReleased(evt);
            }
        });
        jPanel6.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 490, 170, 20));

        jLabel9.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jLabel9.setText("Escribe el correo electronico de tu cuenta para que te ");
        jPanel6.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 410, 20));

        jLabel10.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jLabel10.setText("enviamos un codigo de seguridad");
        jPanel6.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 410, 20));

        rSButtonRound1.setBackground(new java.awt.Color(29, 30, 51));
        rSButtonRound1.setText("Siguiente");
        rSButtonRound1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonRound1ActionPerformed(evt);
            }
        });
        jPanel6.add(rSButtonRound1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, 410, -1));

        kGradientPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 150, 480, 550));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 810, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtcorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcorreoActionPerformed
verificarCorreo();
    }//GEN-LAST:event_txtcorreoActionPerformed

    private void jLabel8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MousePressed
      Login11211 login = new Login11211();
      
    }//GEN-LAST:event_jLabel8MousePressed

    private void rSButtonRound1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonRound1ActionPerformed

        verificarCorreo();
 
 

       
    }//GEN-LAST:event_rSButtonRound1ActionPerformed

    private void jLabel8MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel8MouseReleased

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
         
         cargando11 cargando = new cargando11(new JFrame(), true);

        new Thread(() -> {
            cargando.setVisible(true);
        }).start();

        javax.swing.Timer timer = new javax.swing.Timer(2000, e -> {
            cargando.dispose();

        });

        timer.setRepeats(false);
        timer.start();
         this.dispose();
        Login1121 dialog = new Login1121();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
     

    }//GEN-LAST:event_jLabel8MouseClicked

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
            java.util.logging.Logger.getLogger(Correo_electronico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Correo_electronico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Correo_electronico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Correo_electronico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Correo_electronico().setVisible(true);
            }
        });
    }
       
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel6;
    private keeptoo.KGradientPanel kGradientPanel1;
    private rojerusan.RSButtonRound rSButtonRound1;
    private rojerusan.RSPanelImage rSPanelImage1;
    private javax.swing.JTextField txtcorreo;
    // End of variables declaration//GEN-END:variables

    

   
    private void verificarCorreo() {
        String correo = txtcorreo.getText().trim();

        if (correo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Error, debe llenar los campos");
            return;
        }

        Consulta_Usuarios consulta = new Consulta_Usuarios();
        String usuario = consulta.obtenerCodigoDesdeCorreo(correo);

        if (usuario != null) {
            String codigo = consulta.recuperarCuenta(usuario, correo);

            if (codigo != null) {
                Ctrl_Usuarios controlador = new Ctrl_Usuarios();
                controlador.enviarCodigoRecuperacion(usuario, correo);

                ValidacionCodigoExitoso usu = new ValidacionCodigoExitoso(
                    (Frame) this.getParent(),
                    true,
                    "Confirmar",
                    "¿Desea guardar los datos?"
                );
                usu.setVisible(true);
                this.dispose();
                cargando11 cargando = new cargando11(new JFrame(), true);
                new Thread(() -> cargando.setVisible(true)).start();

                javax.swing.Timer timer = new javax.swing.Timer(2000, e -> {
                    cargando.dispose();
                    this.dispose();
                    Contrasena3 ventana = new Contrasena3();
                    ventana.setCorreoIngresado(correo);
                    ventana.setVisible(true);
                });
                timer.setRepeats(false);
                timer.start();
            } else {
                CodigoIncorrectoAlerta cod = new CodigoIncorrectoAlerta(
                    (Frame) this.getParent(),
                    true,
                    "Confirmar",
                    "¿Desea guardar los datos?"
                );
                cod.setVisible(true);
                this.dispose();
                JOptionPane.showMessageDialog(this, "No se pudo generar el código de recuperación");
            }
        } else {
            JOptionPane.showMessageDialog(this, "El correo no está registrado");
        }
    }
     


}
        

  




