package vista.catalogo;

import javax.swing.JFrame;


/**
 *
 * @author buitr
 */
public class catalogo extends javax.swing.JPanel {

    public catalogo(JFrame jFrame, boolean par) {
        initComponents();

      

        silla s = new silla();
            s.setSize(1250, 630);
            s.setLocation(0, 0);

        panelPrincipal.removeAll();
        panelPrincipal.add(s);
        panelPrincipal.revalidate();
        panelPrincipal.repaint();

    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        comedor = new rojerusan.RSLabelImage();
        silla = new rojerusan.RSLabelImage();
        armario = new rojerusan.RSLabelImage();
        rSLabelImage2 = new rojerusan.RSLabelImage();
        jPanel3 = new javax.swing.JPanel();
        rSLabelImage1 = new rojerusan.RSLabelImage();
        panelPrincipal = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Añadir = new rojeru_san.RSButtonRiple();
        Añadir1 = new rojeru_san.RSButtonRiple();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        comedor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        comedor.setText("Comedor y Bar");
        comedor.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        comedor.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        comedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comedorMouseClicked(evt);
            }
        });
        jPanel2.add(comedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 60, 130, 110));

        silla.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        silla.setIcon(new javax.swing.ImageIcon(getClass().getResource("/catalogo/silla2-png.png"))); // NOI18N
        silla.setText("Sala y Estar");
        silla.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        silla.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        silla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sillaMouseClicked(evt);
            }
        });
        jPanel2.add(silla, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 120, 110));

        armario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        armario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/catalogo/armarioextendido-png.png"))); // NOI18N
        armario.setText("Armario y Closet");
        armario.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        armario.setAlignmentY(5.0F);
        armario.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        armario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                armarioMouseClicked(evt);
            }
        });
        jPanel2.add(armario, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 70, 130, 100));

        rSLabelImage2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rSLabelImage2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/catalogo/camama-png.png"))); // NOI18N
        rSLabelImage2.setText("Camas");
        rSLabelImage2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        rSLabelImage2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rSLabelImage2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSLabelImage2MouseClicked(evt);
            }
        });
        jPanel2.add(rSLabelImage2, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 60, 120, 110));

        rSLabelImage1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rSLabelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/catalogo/cocinadefinitiva-png.png"))); // NOI18N
        rSLabelImage1.setText("Cocina");
        rSLabelImage1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        rSLabelImage1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rSLabelImage1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSLabelImage1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(560, Short.MAX_VALUE)
                .addComponent(rSLabelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(220, 220, 220))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rSLabelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, 910, 120));

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 980, Short.MAX_VALUE)
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
        );

        jPanel2.add(panelPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 980, 450));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/catalogo/catalogo.png"))); // NOI18N
        jLabel1.setText("Catalogo");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 10, -1, -1));

        Añadir.setBackground(new java.awt.Color(46, 49, 82));
        Añadir.setText("Producto");
        Añadir.setColorHover(new java.awt.Color(0, 153, 51));
        Añadir.setFont(new java.awt.Font("Humnst777 BlkCn BT", 1, 14)); // NOI18N
        Añadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AñadirActionPerformed(evt);
            }
        });
        jPanel2.add(Añadir, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 200, 140, -1));

        Añadir1.setBackground(new java.awt.Color(46, 49, 82));
        Añadir1.setText("categoria");
        Añadir1.setColorHover(new java.awt.Color(0, 153, 51));
        Añadir1.setFont(new java.awt.Font("Humnst777 BlkCn BT", 1, 14)); // NOI18N
        Añadir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Añadir1ActionPerformed(evt);
            }
        });
        jPanel2.add(Añadir1, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 20, 140, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1110, 710));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1089, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1089, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 706, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 706, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void comedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comedorMouseClicked
        // TODO add your handling code here:
        
  
            comedor c = new comedor();
            c.setSize(1250, 630);
            c.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(c);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();

        
    }//GEN-LAST:event_comedorMouseClicked

    private void sillaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sillaMouseClicked
        // TODO add your handling code here:
        
          silla s = new silla();
            s.setSize(1250, 630);
            s.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(s);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
    }//GEN-LAST:event_sillaMouseClicked

    private void armarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_armarioMouseClicked
        // TODO add your handling code here:
          armario a = new armario();
            a.setSize(1250, 630);
            a.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(a);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();

    }//GEN-LAST:event_armarioMouseClicked

    private void rSLabelImage2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSLabelImage2MouseClicked
        // TODO add your handling code here:
         cama ca = new cama();
            ca.setSize(1250, 630);
            ca.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(ca);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        
    }//GEN-LAST:event_rSLabelImage2MouseClicked

    private void rSLabelImage1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSLabelImage1MouseClicked
        // TODO add your handling code here:
         cocina co = new cocina();
            co.setSize(1250, 630);
            co.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(co);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        
    }//GEN-LAST:event_rSLabelImage1MouseClicked

    private void AñadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AñadirActionPerformed
        // TODO add your handling code here:
        catalogoNuevo dialog = new catalogoNuevo(new javax.swing.JFrame(), true);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }//GEN-LAST:event_AñadirActionPerformed

    private void Añadir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Añadir1ActionPerformed
        // TODO add your handling code here:
        catalogocategoria dialog = new catalogocategoria(new javax.swing.JFrame(), true);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }//GEN-LAST:event_Añadir1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSButtonRiple Añadir;
    private rojeru_san.RSButtonRiple Añadir1;
    private rojerusan.RSLabelImage armario;
    private rojerusan.RSLabelImage comedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel panelPrincipal;
    private rojerusan.RSLabelImage rSLabelImage1;
    private rojerusan.RSLabelImage rSLabelImage2;
    private rojerusan.RSLabelImage silla;
    // End of variables declaration//GEN-END:variables

}