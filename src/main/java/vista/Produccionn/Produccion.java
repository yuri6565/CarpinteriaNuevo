



package vista.Produccionn;

import javax.swing.JFrame;

/**
 *
 * @author pc
 */
public final class Produccion extends javax.swing.JPanel {


    /**
     * Creates new form Produccion
     */
    public Produccion(JFrame jFrame, boolean par) {
        initComponents();

        this.produccion.setSelected(true);

        ProduccionContenido c = new ProduccionContenido();
        c.setSize(1250, 630);
        c.setLocation(0, 0);

        panelPrincipal.removeAll();
        panelPrincipal.add(c);
        panelPrincipal.revalidate();
        panelPrincipal.repaint();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nuevo = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        produccion = new rojeru_san.RSButtonRiple();
        etapaProduccion = new rojeru_san.RSButtonRiple();
        detalleProduccion = new rojeru_san.RSButtonRiple();
        panelPrincipal = new javax.swing.JPanel();

        nuevo.setBackground(new java.awt.Color(255, 255, 255));
        nuevo.setMinimumSize(new java.awt.Dimension(1290, 730));
        nuevo.setPreferredSize(new java.awt.Dimension(1290, 730));
        nuevo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        nuevo.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(368, 111, -1, -1));

        produccion.setBackground(new java.awt.Color(46, 49, 82));
        produccion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ajuste.png"))); // NOI18N
        produccion.setText("  Produccion");
        produccion.setFont(new java.awt.Font("Roboto Bold", 1, 15)); // NOI18N
        produccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                produccionActionPerformed(evt);
            }
        });
        nuevo.add(produccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 180, 40));

        etapaProduccion.setBackground(new java.awt.Color(46, 49, 82));
        etapaProduccion.setBorder(null);
        etapaProduccion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gears-set.png"))); // NOI18N
        etapaProduccion.setText(" Etapas produccion");
        etapaProduccion.setFont(new java.awt.Font("Roboto Bold", 1, 15)); // NOI18N
        etapaProduccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                etapaProduccionActionPerformed(evt);
            }
        });
        nuevo.add(etapaProduccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, 180, 40));

        detalleProduccion.setBackground(new java.awt.Color(46, 49, 82));
        detalleProduccion.setBorder(null);
        detalleProduccion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eye (1).png"))); // NOI18N
        detalleProduccion.setText("Detalle produccion");
        detalleProduccion.setFont(new java.awt.Font("Roboto Bold", 1, 15)); // NOI18N
        detalleProduccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detalleProduccionActionPerformed(evt);
            }
        });
        nuevo.add(detalleProduccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, 180, -1));

        panelPrincipal.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1250, Short.MAX_VALUE)
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 630, Short.MAX_VALUE)
        );

        nuevo.add(panelPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 1250, 630));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1304, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(nuevo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1304, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 742, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(nuevo, javax.swing.GroupLayout.DEFAULT_SIZE, 742, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void detalleProduccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detalleProduccionActionPerformed
        // TODO add your handling code here:
        if (!this.detalleProduccion.isSelected()) {
            this.produccion.setSelected(false);
            this.detalleProduccion.setSelected(true);
            this.etapaProduccion.setSelected(false);

            ProduccionContDetalle c = new ProduccionContDetalle();
            c.setSize(1250, 630);
            c.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(c);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();

        }

    }//GEN-LAST:event_detalleProduccionActionPerformed

    private void etapaProduccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_etapaProduccionActionPerformed
        // TODO add your handling code here:
        if (!this.etapaProduccion.isSelected()) {
            this.produccion.setSelected(false);
            this.detalleProduccion.setSelected(false);
            this.etapaProduccion.setSelected(true);

            ProduccionConEtapa c = new ProduccionConEtapa();
            c.setSize(1250, 630);
            c.setLocation(0, 0);

            panelPrincipal.removeAll();
            panelPrincipal.add(c);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();

        }

    }//GEN-LAST:event_etapaProduccionActionPerformed

    private void produccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_produccionActionPerformed
        // TODO add your handling code here:
        if (!this.produccion.isSelected()) {
            this.produccion.setSelected(true);
            this.detalleProduccion.setSelected(false);
            this.etapaProduccion.setSelected(false);

            ProduccionContenido c = new ProduccionContenido();
            c.setSize(1250, 630);
            c.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(c);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();

        }
    }//GEN-LAST:event_produccionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSButtonRiple detalleProduccion;
    private rojeru_san.RSButtonRiple etapaProduccion;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel nuevo;
    private javax.swing.JPanel panelPrincipal;
    private rojeru_san.RSButtonRiple produccion;
    // End of variables declaration//GEN-END:variables

}
