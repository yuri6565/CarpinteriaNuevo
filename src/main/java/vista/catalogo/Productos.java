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
import newscomponents.RSPanelEffect;
import rojeru_san.efectos.ValoresEnum;
import rojerusan.RSLabelIcon;
import rojerusan.RSPanelImage;


/**
 *
 * @author buitr
 */
public class Productos extends javax.swing.JPanel {

    public Productos(JFrame jFrame, boolean par) {
        initComponents();
        panelCards.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 15, 15));
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        panelCards = new javax.swing.JPanel();
        rSPanelEffect2 = new newscomponents.RSPanelEffect();
        rSPanelImage3 = new rojeru_san.rspanel.RSPanelImage();
        rSLabelIcon5 = new rojerusan.RSLabelIcon();
        rSLabelIcon6 = new rojerusan.RSLabelIcon();
        lblTituloPrincipal2 = new javax.swing.JLabel();
        lblTituloPrincipal4 = new javax.swing.JLabel();
        lblTituloPrincipal5 = new javax.swing.JLabel();
        rSPanelEffect4 = new newscomponents.RSPanelEffect();
        rSPanelImage5 = new rojeru_san.rspanel.RSPanelImage();
        rSLabelIcon9 = new rojerusan.RSLabelIcon();
        rSLabelIcon10 = new rojerusan.RSLabelIcon();
        lblTituloPrincipal6 = new javax.swing.JLabel();
        lblTituloPrincipal7 = new javax.swing.JLabel();
        lblTituloPrincipal8 = new javax.swing.JLabel();
        rSPanelEffect5 = new newscomponents.RSPanelEffect();
        rSPanelImage6 = new rojeru_san.rspanel.RSPanelImage();
        rSLabelIcon11 = new rojerusan.RSLabelIcon();
        rSLabelIcon12 = new rojerusan.RSLabelIcon();
        lblTituloPrincipal9 = new javax.swing.JLabel();
        lblTituloPrincipal10 = new javax.swing.JLabel();
        lblTituloPrincipal11 = new javax.swing.JLabel();
        Añadir1 = new rojeru_san.RSButtonRiple();
        txtBuscar = new RSMaterialComponent.RSTextFieldMaterialIcon();
        lblTituloPrincipal = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelCards.setBackground(new java.awt.Color(245, 245, 245));

        rSPanelEffect2.setBackground(new java.awt.Color(255, 255, 255));
        rSPanelEffect2.setToolTipText("");
        rSPanelEffect2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSPanelImage3.setToolTipText("");
        rSPanelImage3.setImagen(new javax.swing.ImageIcon(getClass().getResource("/catalogo/armario1.jpg"))); // NOI18N

        javax.swing.GroupLayout rSPanelImage3Layout = new javax.swing.GroupLayout(rSPanelImage3);
        rSPanelImage3.setLayout(rSPanelImage3Layout);
        rSPanelImage3Layout.setHorizontalGroup(
            rSPanelImage3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        rSPanelImage3Layout.setVerticalGroup(
            rSPanelImage3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 160, Short.MAX_VALUE)
        );

        rSPanelEffect2.add(rSPanelImage3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 190, -1));

        rSLabelIcon5.setBackground(new java.awt.Color(255, 51, 51));
        rSLabelIcon5.setForeground(new java.awt.Color(255, 51, 51));
        rSLabelIcon5.setToolTipText("");
        rSLabelIcon5.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.DELETE);
        rSPanelEffect2.add(rSLabelIcon5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 25, 25));

        rSLabelIcon6.setBackground(new java.awt.Color(51, 51, 51));
        rSLabelIcon6.setForeground(new java.awt.Color(51, 51, 51));
        rSLabelIcon6.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.EDIT);
        rSPanelEffect2.add(rSLabelIcon6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 25, 25));

        lblTituloPrincipal2.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        lblTituloPrincipal2.setText("Madera Roble");
        rSPanelEffect2.add(lblTituloPrincipal2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 237, 130, 20));

        lblTituloPrincipal4.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        lblTituloPrincipal4.setText("10x100");
        rSPanelEffect2.add(lblTituloPrincipal4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 262, 180, -1));

        lblTituloPrincipal5.setFont(new java.awt.Font("SansSerif", 1, 17)); // NOI18N
        lblTituloPrincipal5.setText("Armario tres puertas");
        lblTituloPrincipal5.setToolTipText("");
        rSPanelEffect2.add(lblTituloPrincipal5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 180, -1));

        rSPanelEffect4.setBackground(new java.awt.Color(255, 255, 255));
        rSPanelEffect4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSPanelImage5.setToolTipText("");
        rSPanelImage5.setImagen(new javax.swing.ImageIcon(getClass().getResource("/catalogo/cama6.jpg"))); // NOI18N
        rSPanelImage5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        rSPanelEffect4.add(rSPanelImage5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 180, 160));

        rSLabelIcon9.setBackground(new java.awt.Color(153, 255, 153));
        rSLabelIcon9.setForeground(new java.awt.Color(255, 51, 51));
        rSLabelIcon9.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.DELETE);
        rSPanelEffect4.add(rSLabelIcon9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 25, 25));

        rSLabelIcon10.setBackground(new java.awt.Color(51, 51, 51));
        rSLabelIcon10.setForeground(new java.awt.Color(51, 51, 51));
        rSLabelIcon10.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.EDIT);
        rSPanelEffect4.add(rSLabelIcon10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 25, 25));

        lblTituloPrincipal6.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        lblTituloPrincipal6.setText("Madera Roble");
        rSPanelEffect4.add(lblTituloPrincipal6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 237, 130, 20));

        lblTituloPrincipal7.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        lblTituloPrincipal7.setText("10x100");
        rSPanelEffect4.add(lblTituloPrincipal7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 262, 180, -1));

        lblTituloPrincipal8.setFont(new java.awt.Font("SansSerif", 1, 17)); // NOI18N
        lblTituloPrincipal8.setText("Armario tres puertas");
        rSPanelEffect4.add(lblTituloPrincipal8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 180, -1));

        rSPanelEffect5.setBackground(new java.awt.Color(255, 255, 255));
        rSPanelEffect5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSPanelImage6.setToolTipText("");
        rSPanelImage6.setImagen(new javax.swing.ImageIcon(getClass().getResource("/catalogo/comedoricon.png"))); // NOI18N
        rSPanelImage6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        rSPanelEffect5.add(rSPanelImage6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 180, 160));

        rSLabelIcon11.setBackground(new java.awt.Color(255, 51, 51));
        rSLabelIcon11.setForeground(new java.awt.Color(255, 51, 51));
        rSLabelIcon11.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.DELETE);
        rSPanelEffect5.add(rSLabelIcon11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 25, 25));

        rSLabelIcon12.setBackground(new java.awt.Color(51, 51, 51));
        rSLabelIcon12.setForeground(new java.awt.Color(51, 51, 51));
        rSLabelIcon12.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.EDIT);
        rSPanelEffect5.add(rSLabelIcon12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 25, 25));

        lblTituloPrincipal9.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        lblTituloPrincipal9.setText("Madera Roble");
        rSPanelEffect5.add(lblTituloPrincipal9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 237, 130, 20));

        lblTituloPrincipal10.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        lblTituloPrincipal10.setText("10x100");
        rSPanelEffect5.add(lblTituloPrincipal10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 262, 180, -1));

        lblTituloPrincipal11.setFont(new java.awt.Font("SansSerif", 1, 17)); // NOI18N
        lblTituloPrincipal11.setText("Armario tres puertas");
        rSPanelEffect5.add(lblTituloPrincipal11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 180, -1));

        javax.swing.GroupLayout panelCardsLayout = new javax.swing.GroupLayout(panelCards);
        panelCards.setLayout(panelCardsLayout);
        panelCardsLayout.setHorizontalGroup(
            panelCardsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCardsLayout.createSequentialGroup()
                .addGap(277, 277, 277)
                .addComponent(rSPanelEffect4, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(rSPanelEffect5, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(246, Short.MAX_VALUE))
            .addGroup(panelCardsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCardsLayout.createSequentialGroup()
                    .addGap(60, 60, 60)
                    .addComponent(rSPanelEffect2, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(699, Short.MAX_VALUE)))
        );
        panelCardsLayout.setVerticalGroup(
            panelCardsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCardsLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(panelCardsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(rSPanelEffect5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rSPanelEffect4, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE))
                .addContainerGap(162, Short.MAX_VALUE))
            .addGroup(panelCardsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCardsLayout.createSequentialGroup()
                    .addGap(44, 44, 44)
                    .addComponent(rSPanelEffect2, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(162, Short.MAX_VALUE)))
        );

        jPanel2.add(panelCards, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 970, 500));

        Añadir1.setBackground(new java.awt.Color(46, 49, 82));
        Añadir1.setText("Agregar Categoria");
        Añadir1.setColorHover(new java.awt.Color(0, 153, 51));
        Añadir1.setFont(new java.awt.Font("Humnst777 BlkCn BT", 1, 14)); // NOI18N
        Añadir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Añadir1ActionPerformed(evt);
            }
        });
        jPanel2.add(Añadir1, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 90, 160, 40));

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
        jPanel2.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 430, 40));

        lblTituloPrincipal.setFont(new java.awt.Font("Segoe UI Black", 0, 20)); // NOI18N
        lblTituloPrincipal.setForeground(new java.awt.Color(96, 112, 128));
        lblTituloPrincipal.setText("Roble");
        jPanel2.add(lblTituloPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 190, 60, -1));

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
            .addGap(0, 803, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 48, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 706, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 49, Short.MAX_VALUE)))
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSButtonRiple Añadir1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblTituloPrincipal;
    private javax.swing.JLabel lblTituloPrincipal10;
    private javax.swing.JLabel lblTituloPrincipal11;
    private javax.swing.JLabel lblTituloPrincipal2;
    private javax.swing.JLabel lblTituloPrincipal4;
    private javax.swing.JLabel lblTituloPrincipal5;
    private javax.swing.JLabel lblTituloPrincipal6;
    private javax.swing.JLabel lblTituloPrincipal7;
    private javax.swing.JLabel lblTituloPrincipal8;
    private javax.swing.JLabel lblTituloPrincipal9;
    private javax.swing.JPanel panelCards;
    private rojerusan.RSLabelIcon rSLabelIcon10;
    private rojerusan.RSLabelIcon rSLabelIcon11;
    private rojerusan.RSLabelIcon rSLabelIcon12;
    private rojerusan.RSLabelIcon rSLabelIcon5;
    private rojerusan.RSLabelIcon rSLabelIcon6;
    private rojerusan.RSLabelIcon rSLabelIcon9;
    private newscomponents.RSPanelEffect rSPanelEffect2;
    private newscomponents.RSPanelEffect rSPanelEffect4;
    private newscomponents.RSPanelEffect rSPanelEffect5;
    private rojeru_san.rspanel.RSPanelImage rSPanelImage3;
    private rojeru_san.rspanel.RSPanelImage rSPanelImage5;
    private rojeru_san.rspanel.RSPanelImage rSPanelImage6;
    private RSMaterialComponent.RSTextFieldMaterialIcon txtBuscar;
    // End of variables declaration//GEN-END:variables
private int cardX = 10; // Starting X position for cards
private int cardY = 10; // Starting Y position for cards


private void agregarCard(String rutaImagen, String categoriaNombre) {
    RSPanelEffect cardPanel = new RSPanelEffect();
    cardPanel.setBackground(Color.WHITE);
    cardPanel.setPreferredSize(new Dimension(200, 283));
    cardPanel.setLayout(new BorderLayout());


    JPanel topPanel = new JPanel(new BorderLayout());
    topPanel.setOpaque(false);
    topPanel.setPreferredSize(new Dimension(200, 190));

    // Panel de íconos alineado arriba a la derecha
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

    // Panel para imagen centrada
    JPanel imageWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 5));
    imageWrapper.setOpaque(false);

    RSPanelImage imagenCard = new RSPanelImage();
    imagenCard.setPreferredSize(new Dimension(170, 200));
    imagenCard.setImagen(new ImageIcon(rutaImagen));

    imageWrapper.add(imagenCard);

    // Agregar iconos arriba y la imagen debajo
    topPanel.add(iconPanel, BorderLayout.NORTH);
    topPanel.add(imageWrapper, BorderLayout.CENTER);

    // Panel de texto con título, categoría y medida
    JPanel textPanel = new JPanel(new GridLayout(3, 1, 0, 2));
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

    // Agregar secciones al card principal
    cardPanel.add(topPanel, BorderLayout.NORTH);
    cardPanel.add(textPanel, BorderLayout.SOUTH);

    // Posicionar la tarjeta en el panel contenedor
    cardPanel.setBounds(cardX, cardY, 200, 283);
    cardX += 210;
    if (cardX + 200 > panelCards.getWidth()) {
        cardX = 10;
        cardY += 293;
    }

    panelCards.add(cardPanel);
    panelCards.revalidate();
    panelCards.repaint();
}



}