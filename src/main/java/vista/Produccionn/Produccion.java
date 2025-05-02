
package vista.Produccionn;

import javax.swing.JFrame;
import vista.Produccionn.ProduccionContenido;


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
        panelPrincipal = new javax.swing.JPanel();
        produccion = new RSMaterialComponent.RSButtonShape();
        Detalle_produccion = new RSMaterialComponent.RSButtonShape();
        Etapas_produccion = new RSMaterialComponent.RSButtonShape();

        nuevo.setBackground(new java.awt.Color(255, 255, 255));
        nuevo.setMinimumSize(new java.awt.Dimension(1290, 730));
        nuevo.setPreferredSize(new java.awt.Dimension(1290, 730));
        nuevo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        nuevo.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(368, 111, -1, -1));

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

        produccion.setBackground(new java.awt.Color(46, 49, 82));
        produccion.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        produccion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gears-set.png"))); // NOI18N
        produccion.setText("Produccion");
        produccion.setBackgroundHover(new java.awt.Color(67, 150, 209));
        produccion.setFont(new java.awt.Font("Roboto Bold", 1, 16)); // NOI18N
        produccion.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        produccion.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        produccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                produccionActionPerformed(evt);
            }
        });
        nuevo.add(produccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 200, 40));

        Detalle_produccion.setBackground(new java.awt.Color(46, 49, 82));
        Detalle_produccion.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        Detalle_produccion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/llave-inglesa.png"))); // NOI18N
        Detalle_produccion.setText("Detalle produccion");
        Detalle_produccion.setBackgroundHover(new java.awt.Color(67, 150, 209));
        Detalle_produccion.setFont(new java.awt.Font("Roboto Bold", 1, 16)); // NOI18N
        Detalle_produccion.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        Detalle_produccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Detalle_produccionActionPerformed(evt);
            }
        });
        nuevo.add(Detalle_produccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, 200, 40));

        Etapas_produccion.setBackground(new java.awt.Color(46, 49, 82));
        Etapas_produccion.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        Etapas_produccion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ajuste.png"))); // NOI18N
        Etapas_produccion.setText("Etapas produccion");
        Etapas_produccion.setBackgroundHover(new java.awt.Color(67, 150, 209));
        Etapas_produccion.setFont(new java.awt.Font("Roboto Bold", 1, 16)); // NOI18N
        Etapas_produccion.setForma(RSMaterialComponent.RSButtonShape.FORMA.ROUND);
        Etapas_produccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Etapas_produccionActionPerformed(evt);
            }
        });
        nuevo.add(Etapas_produccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, 200, 40));

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

    private void produccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_produccionActionPerformed
        // TODO add your handling code here:
        if (!this.produccion.isSelected()) {
            this.produccion.setSelected(true);
            this.Detalle_produccion.setSelected(false);
            this.Etapas_produccion.setSelected(false);

            ProduccionContenido c = new ProduccionContenido();
            c.setSize(1250, 630);
            c.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(c);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();

        }
    }//GEN-LAST:event_produccionActionPerformed

    private void Detalle_produccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Detalle_produccionActionPerformed
        // TODO add your handling code here:
        if (!this.Detalle_produccion.isSelected()) {
            this.produccion.setSelected(false);
            this.Detalle_produccion.setSelected(true);
            this.Etapas_produccion.setSelected(false);

            ProduccionContDetalle c = new ProduccionContDetalle();
            c.setSize(1250, 630);
            c.setLocation(0, 0);
            panelPrincipal.removeAll();
            panelPrincipal.add(c);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();

        }
    }//GEN-LAST:event_Detalle_produccionActionPerformed

    private void Etapas_produccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Etapas_produccionActionPerformed
        // TODO add your handling code here:
        if (!this.Etapas_produccion.isSelected()) {
            this.produccion.setSelected(false);
            this.Detalle_produccion.setSelected(false);
            this.Etapas_produccion.setSelected(true);

            ProduccionConEtapa c = new ProduccionConEtapa();
            c.setSize(1250, 630);
            c.setLocation(0, 0);

            panelPrincipal.removeAll();
            panelPrincipal.add(c);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();

        }
    }//GEN-LAST:event_Etapas_produccionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RSMaterialComponent.RSButtonShape Detalle_produccion;
    private RSMaterialComponent.RSButtonShape Etapas_produccion;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel nuevo;
    private javax.swing.JPanel panelPrincipal;
    private RSMaterialComponent.RSButtonShape produccion;
    // End of variables declaration//GEN-END:variables

}
