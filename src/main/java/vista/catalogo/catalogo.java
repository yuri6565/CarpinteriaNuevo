package vista.catalogo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;




public class catalogo extends javax.swing.JPanel {

private int cardsPerPage = 10; // Mostrar 5 cards por página
private int currentPage = 0;
private java.util.List<javax.swing.JPanel> allCards = new java.util.ArrayList<>();
private rojerusan.RSLabelIcon nextIcon;
private javax.swing.JScrollPane scrollPane;
    public catalogo(JFrame jFrame, boolean par) {
        initComponents();
        initializePagination();
    }


private void initializePagination() {
    nextIcon = new rojerusan.RSLabelIcon();
    nextIcon.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.TRENDING_FLAT); // Icono de flecha horizontal
    nextIcon.setForeground(new java.awt.Color(0, 0, 0)); // Color del icono (ajustable)
    nextIcon.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {
            currentPage++;
            updateCardsDisplay();
        }
    });

   // Creamos un contenedor intermedio con BorderLayout
JPanel container = new JPanel(new BorderLayout());

// Agregamos el scrollPane al centro de ese contenedor
scrollPane = new JScrollPane();
scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
container.add(scrollPane, BorderLayout.CENTER);

// Agregamos el botón de flecha a la derecha del contenedor
container.add(nextIcon, BorderLayout.EAST);

// Finalmente agregamos el contenedor al panelCards (que puede estar en null o BorderLayout, pero mejor usar uno consistente)
panelCards.setLayout(new BorderLayout());
panelCards.add(container, BorderLayout.CENTER);

}

private void updateCardsDisplay() {
    // Crear un nuevo panel para las cards con GridLayout (2 filas, 3 y 2 columnas)
    JPanel cardsPanel = new JPanel(new java.awt.GridBagLayout());
    cardsPanel.setBackground(new Color(242, 247, 255));

    java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
    gbc.insets = new java.awt.Insets(15, 15, 15, 15); // Espaciado entre cards
    gbc.fill = java.awt.GridBagConstraints.NONE;

    int startIndex = currentPage * cardsPerPage;
    int endIndex = Math.min(startIndex + cardsPerPage, allCards.size());

    int cardIndex = 0;
    for (int i = startIndex; i < endIndex; i++) {
        if (cardIndex < 5) {
            // Primera fila: 3 cards
            gbc.gridx = cardIndex;
            gbc.gridy = 0;
        } else {
            // Segunda fila: 2 cards
            gbc.gridx = cardIndex - 5;
            gbc.gridy = 1;
        }
        cardsPanel.add(allCards.get(i), gbc);
        cardIndex++;
    }

    // Rellenar con espacios vacíos si es necesario para mantener la estructura
    while (cardIndex < 5) {
        gbc.gridx = cardIndex < 3 ? cardIndex : cardIndex - 3;
        gbc.gridy = cardIndex < 3 ? 0 : 1;
        cardsPanel.add(new JLabel(), gbc);
        cardIndex++;
    }

    // Actualizar el JScrollPane con el panel de cards
    scrollPane.setViewportView(cardsPanel);
    nextIcon.setVisible(endIndex < allCards.size()); // Mostrar icono si hay más cards
    panelCards.revalidate();
    panelCards.repaint();
}
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        panelCards = new javax.swing.JPanel();
        rSLabelIcon2 = new rojerusan.RSLabelIcon();
        Añadir1 = new rojeru_san.RSButtonRiple();
        txtBuscar = new RSMaterialComponent.RSTextFieldMaterialIcon();

        setBackground(new java.awt.Color(242, 247, 255));

        jPanel1.setBackground(new java.awt.Color(242, 247, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(242, 247, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelCards.setBackground(new java.awt.Color(255, 255, 255));
        panelCards.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSLabelIcon2.setForeground(new java.awt.Color(153, 0, 153));
        rSLabelIcon2.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.KEYBOARD_ARROW_RIGHT);
        rSLabelIcon2.setInheritsPopupMenu(true);
        panelCards.add(rSLabelIcon2, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 200, 50, 50));

        jPanel2.add(panelCards, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, 970, 450));

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
        jPanel2.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, 430, 40));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1090, 700));

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSButtonRiple Añadir1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel panelCards;
    private rojerusan.RSLabelIcon rSLabelIcon2;
    private RSMaterialComponent.RSTextFieldMaterialIcon txtBuscar;
    // End of variables declaration//GEN-END:variables
private void agregarCard(String rutaImagen, String categoriaNombre) {
    javax.swing.JPanel cardPanel = new javax.swing.JPanel();
    cardPanel.setBackground(new Color(242, 247, 255));
    cardPanel.setPreferredSize(new java.awt.Dimension(150, 180));

    rojerusan.RSPanelCircleImage nuevaCard = new rojerusan.RSPanelCircleImage();
    nuevaCard.setPreferredSize(new java.awt.Dimension(150, 150));
    nuevaCard.setImagen(new javax.swing.ImageIcon(rutaImagen));
    nuevaCard.setColorBorde(new Color(242, 247, 255));

    JLabel cardLabel = new JLabel(categoriaNombre);
    cardLabel.setFont(new java.awt.Font("Segoe UI Black", 0, 17));
    cardLabel.setHorizontalAlignment(JLabel.CENTER);

    cardPanel.setLayout(new java.awt.BorderLayout());
    cardPanel.add(nuevaCard, java.awt.BorderLayout.CENTER);
    cardPanel.add(cardLabel, java.awt.BorderLayout.SOUTH);

    cardPanel.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            mostrarVistaCategoria(categoriaNombre);
        }
    });

    allCards.add(cardPanel);
    updateCardsDisplay();
}


    private void mostrarVistaCategoria(String categoriaNombre) {
        // Limpiar el panelCards
        panelCards.removeAll();
        panelCards.setLayout(new java.awt.BorderLayout());

        // Crear un nuevo panel para la vista de la categoría seleccionada
        JPanel categoriaVista = new JPanel();
        categoriaVista.setBackground(new Color(242, 247, 255));
        categoriaVista.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());


        JLabel tituloCategoria = new JLabel("Categoría: " + categoriaNombre);
        tituloCategoria.setFont(new java.awt.Font("Segoe UI Black", 0, 20));
        categoriaVista.add(tituloCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 300, 30));


        JLabel contenido = new JLabel("Aquí se mostrarían los productos de " + categoriaNombre);
        contenido.setFont(new java.awt.Font("Segoe UI", 0, 16));
        categoriaVista.add(contenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 400, 30));

        // Agregar botón de regresar
    
    }


}