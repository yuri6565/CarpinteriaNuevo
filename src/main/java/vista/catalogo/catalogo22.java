package vista.catalogo;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import newscomponents.RSPanelEffect;
import rojeru_san.efectos.ValoresEnum;
import rojerusan.RSLabelIcon;
import rojerusan.RSPanelImage;


/**
 *
 * @author buitr
 */
public class catalogo22 extends javax.swing.JPanel {

    private JScrollPane scrollPane;
    private int cardX = 15; // Starting X position for cards
    private int cardY = 15; // Starting Y position for cards

    public catalogo22(JFrame jFrame, boolean par) {
        initComponents();
panelCards.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));        scrollPane = new JScrollPane(panelCards);
        scrollPane.setBounds(40, 160, 1070, 600); // Match the original panelCards bounds
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jPanel2.add(scrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 1070, 600));
        jPanel2.remove(panelCards); // Remove the original panelCards
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        panelCards = new javax.swing.JPanel();
        txtBuscar = new RSMaterialComponent.RSTextFieldMaterialIcon();
        Añadir2 = new rojeru_san.RSButtonRiple();
        Añadir1 = new rojeru_san.RSButtonRiple();
        Añadir4 = new rojeru_san.RSButtonRiple();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelCards.setBackground(new java.awt.Color(245, 245, 245));

        javax.swing.GroupLayout panelCardsLayout = new javax.swing.GroupLayout(panelCards);
        panelCards.setLayout(panelCardsLayout);
        panelCardsLayout.setHorizontalGroup(
            panelCardsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1160, Short.MAX_VALUE)
        );
        panelCardsLayout.setVerticalGroup(
            panelCardsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        jPanel2.add(panelCards, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 1160, 600));

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
        jPanel2.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 430, 40));

        Añadir2.setBackground(new java.awt.Color(46, 49, 82));
        Añadir2.setText("Agregar Categoria");
        Añadir2.setColorHover(new java.awt.Color(0, 153, 51));
        Añadir2.setFont(new java.awt.Font("Humnst777 BlkCn BT", 1, 14)); // NOI18N
        Añadir2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Añadir2ActionPerformed(evt);
            }
        });
        jPanel2.add(Añadir2, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 90, 160, 40));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -50, 1320, 700));
        jPanel2.getAccessibleContext().setAccessibleName("");

        Añadir1.setBackground(new java.awt.Color(46, 49, 82));
        Añadir1.setText("Anterior");
        Añadir1.setColorHover(new java.awt.Color(0, 153, 51));
        Añadir1.setFont(new java.awt.Font("Humnst777 BlkCn BT", 1, 14)); // NOI18N
        Añadir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Añadir1ActionPerformed(evt);
            }
        });

        Añadir4.setBackground(new java.awt.Color(46, 49, 82));
        Añadir4.setText("Anterior");
        Añadir4.setColorHover(new java.awt.Color(0, 153, 51));
        Añadir4.setFont(new java.awt.Font("Humnst777 BlkCn BT", 1, 14)); // NOI18N
        Añadir4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Añadir4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(1314, Short.MAX_VALUE)
                .addComponent(Añadir1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Añadir4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(199, 199, 199))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(312, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1278, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(126, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(903, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Añadir1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Añadir4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(136, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(182, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void Añadir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Añadir1ActionPerformed
catalogocategoria cat = new catalogocategoria((JFrame) getTopLevelAncestor(), true); // Use getTopLevelAncestor() to get the parent JFrame
    cat.setVisible(true); // Aquí se detiene hasta que se cierre

    String rutaImagen = cat.getRutaImagenSeleccionada();
    String categoriaNombre = cat.getCategoriaNombre();
    if (rutaImagen != null && !rutaImagen.isEmpty() && !categoriaNombre.isEmpty()) {
        agregarCard(rutaImagen, categoriaNombre);
    }
  
    }//GEN-LAST:event_Añadir1ActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void Añadir2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Añadir2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Añadir2ActionPerformed

    private void Añadir4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Añadir4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Añadir4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSButtonRiple Añadir1;
    private rojeru_san.RSButtonRiple Añadir2;
    private rojeru_san.RSButtonRiple Añadir4;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel panelCards;
    private RSMaterialComponent.RSTextFieldMaterialIcon txtBuscar;
    // End of variables declaration//GEN-END:variables
private void agregarCard(String rutaImagen, String categoriaNombre) {
        // Create a parent panel to stack the image and text vertically
        JPanel cardWrapper = new JPanel();
        cardWrapper.setLayout(new BoxLayout(cardWrapper, BoxLayout.Y_AXIS));
        cardWrapper.setOpaque(false);

        // Circular image panel
        rojerusan.RSPanelCircleImage cardPanel = new rojerusan.RSPanelCircleImage();
        cardPanel.setPreferredSize(new java.awt.Dimension(150, 150)); // Square for circular effect
        cardPanel.setImagen(new javax.swing.ImageIcon(rutaImagen));
        cardPanel.setColorBorde(new Color(242, 247, 255));
        cardPanel.setOpaque(false);

        // Text label for the category
        JLabel tituloCategoria = new JLabel(categoriaNombre);
        tituloCategoria.setFont(new java.awt.Font("Century751 BT", 0, 20));
        tituloCategoria.setHorizontalAlignment(JLabel.CENTER);
        tituloCategoria.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align in BoxLayout

        // Add components to the wrapper panel
        cardWrapper.add(cardPanel);
        cardWrapper.add(tituloCategoria);

        // Determine row (top 5 cards or bottom 5 cards)
        int totalCards = panelCards.getComponentCount();
        int row = (totalCards < 5) ? 0 : 1; // Row 0 for first 5, Row 1 for next 5
        int cardIndex = totalCards % 5; // Index within the row (0 to 4)

        // Calculate position
        int cardX = 10 + cardIndex * 165; // 10px gap + 165px width per card
        int cardY = row * 210 + 10; // 210px height per row + 10px gap

        // Set bounds for the wrapper panel
        cardWrapper.setBounds(cardX, cardY, 150, 200);

        // Add to panelCards
        panelCards.add(cardWrapper);
        panelCards.revalidate();
        panelCards.repaint();

        // Update panelCards size
        int nuevoAlto = (row + 1) * 210 + 10;
        panelCards.setPreferredSize(new Dimension(1070, nuevoAlto)); // 1070px width to fit 5 cards
        scrollPane.revalidate();
        scrollPane.repaint();
    }

}