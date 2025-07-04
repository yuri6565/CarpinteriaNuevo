/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package vista.alertas;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import javax.swing.JDialog;


/**
 *
 * @author EQUIPO
 */



/**
 *
 * @author EQUIPO
 */
public class alertaEliminar11 extends JDialog {

    public alertaEliminar11(Frame parent, boolean modal, String error, String todos_los_campos_son_obligatorios, String customIconPath) {
        super(parent, modal);
        if (!isVisible()) { // Prevent multiple instances
            initComponents();
            System.out.println("Setting error: " + error);
            System.out.println("Setting message: " + todos_los_campos_son_obligatorios);
            jLabel1.setText(error != null ? error : "¿Estás seguro?");
            if (customIconPath != null && getClass().getResource(customIconPath) != null) {
                rSLabelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource(customIconPath)));
            } else {
                rSLabelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/warning-triangle-sign-free-vector-removebg-preview.png")));
            }
            setOpacity(0.0f);
            setBackground(new java.awt.Color(0, 0, 0, 0));
            Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
            setSize(pantalla);
            setLocationRelativeTo(parent);

            // Manual fade-in effect
            new Thread(() -> {
                for (float i = 0.0f; i <= 1.0f; i += 0.1f) {
                    setOpacity(i);
                    try { Thread.sleep(50); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
                }
                setOpacity(1.0f);
                setVisible(true); // Show after fade-in
            }).start();
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

        jEImagePanel1 = new LIB.JEImagePanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        rSLabelImage1 = new rojerusan.RSLabelImage();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnCancelar = new rojeru_san.RSButtonRiple();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jEImagePanel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/fondotransparente.png"))); // NOI18N
        jEImagePanel1.setPreferredSize(new java.awt.Dimension(500, 192));
        jEImagePanel1.setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(510, 250));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(46, 49, 82));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Century751 BT", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Error");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, -1, -1));

        rSLabelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/warning-triangle-sign-free-vector-removebg-preview.png"))); // NOI18N
        jPanel2.add(rSLabelImage1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 80, 70));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 620, 100));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("los productos asociados a esa categoria");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 360, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Error al eliminar la categoria, debe eliminar primero ");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, -1));

        btnCancelar.setBackground(new java.awt.Color(46, 49, 82));
        btnCancelar.setText("OK");
        btnCancelar.setColorHover(new java.awt.Color(102, 0, 0));
        btnCancelar.setFont(new java.awt.Font("Humnst777 BlkCn BT", 1, 18)); // NOI18N
        btnCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 200, 130, -1));

        jEImagePanel1.add(jPanel1, new java.awt.GridBagConstraints());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jEImagePanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 734, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jEImagePanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    /**
     * @param args the command line arguments
     */
public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(alertaEliminar11.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            alertaEliminar11 dialog = new alertaEliminar11(new javax.swing.JFrame(), true, "¿Estás seguro?", "¿Está seguro que desea eliminar este producto?", "/warning-triangle-sign-free-vector-removebg-preview.png");
            dialog.setVisible(true);
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSButtonRiple btnCancelar;
    private LIB.JEImagePanel jEImagePanel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private rojerusan.RSLabelImage rSLabelImage1;
    // End of variables declaration//GEN-END:variables



}
