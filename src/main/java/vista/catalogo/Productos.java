package vista.catalogo;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
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
public class Productos extends javax.swing.JPanel {

    private JScrollPane scrollPane;
    private int cardX = 10; // Starting X position for cards
    private int cardY = 10; // Starting Y position for cards

    public Productos(JFrame jFrame, boolean par) {
      panelCards.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
    scrollPane = new JScrollPane(panelCards);
    scrollPane.setBounds(40, 210, 1070, 400); // Adjusted y to 210 and height to 400
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    jPanel2.add(scrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 1070, 400));
    jPanel2.remove(panelCards);
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
productocategoria cat = new productocategoria((JFrame) getTopLevelAncestor(), true); // Use getTopLevelAncestor() to get the parent JFrame
    cat.setVisible(true); // Aquí se detiene hasta que se cierre

    String rutaImagen = cat.getRutaImagenSeleccionada();
    String categoriaNombre = cat.getNombre();
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
        RSPanelEffect cardPanel = new RSPanelEffect();
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setPreferredSize(new Dimension(200, 290));
        cardPanel.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        topPanel.setPreferredSize(new Dimension(200, 190));

        JPanel iconPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        iconPanel.setOpaque(false);

        RSLabelIcon iconDelete = new RSLabelIcon();
        iconDelete.setIcons(ValoresEnum.ICONS.DELETE);
        iconDelete.setPreferredSize(new Dimension(25, 25));
        iconDelete.setForeground(Color.RED);

        RSLabelIcon iconEdit = new RSLabelIcon();
        iconEdit.setIcons(ValoresEnum.ICONS.EDIT);
        iconEdit.setPreferredSize(new Dimension(25, 25));
        iconEdit.setForeground(Color.BLACK);

        iconPanel.add(iconDelete);
        iconPanel.add(iconEdit);

        JPanel imageWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 5));
        imageWrapper.setOpaque(false);

        RSPanelImage imagenCard = new RSPanelImage();
        imagenCard.setPreferredSize(new Dimension(170, 150));
        imagenCard.setImagen(new ImageIcon(rutaImagen));

        imageWrapper.add(imagenCard);

        topPanel.add(iconPanel, BorderLayout.NORTH);
        topPanel.add(imageWrapper, BorderLayout.CENTER);

        JPanel textPanel = new JPanel(new GridLayout(4, 1, 0, 2));
        textPanel.setOpaque(false);

        JLabel titleLabel = new JLabel("Armario tres puertas");
        titleLabel.setFont(new Font("Sans Serif", Font.BOLD, 17));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel categoryLabel = new JLabel(categoriaNombre);
        categoryLabel.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        categoryLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel sizeLabel = new JLabel("10x100");
        sizeLabel.setFont(new Font("Sans Serif", Font.PLAIN, 12));
        sizeLabel.setHorizontalAlignment(JLabel.CENTER);

        textPanel.add(titleLabel);
        textPanel.add(categoryLabel);
        textPanel.add(sizeLabel);

        cardPanel.add(topPanel, BorderLayout.NORTH);
        cardPanel.add(textPanel, BorderLayout.SOUTH);

        cardPanel.setBounds(cardX, cardY, 200, 300);
        cardX += 215; 
        if (cardX + 200 > 1075) { // 7 cards per row (1505px  )
            cardX = 10;
            cardY += 310;
        }
panelCards.setPreferredSize(new Dimension(Math.max(cardX + 200, scrollPane.getWidth()), cardY + 310));
        panelCards.add(cardPanel);
        panelCards.revalidate();
        panelCards.repaint();

        int nuevoAlto = cardY + 310;
        panelCards.setPreferredSize(new Dimension(1505, nuevoAlto));
        scrollPane.revalidate();
        scrollPane.repaint();
    }


}