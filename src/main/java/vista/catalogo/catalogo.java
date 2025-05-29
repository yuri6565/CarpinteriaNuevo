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
        txtBuscar = new RSMaterialComponent.RSTextFieldMaterialIcon();
        Añadir2 = new rojeru_san.RSButtonRiple();

        setBackground(new java.awt.Color(242, 247, 255));

        jPanel1.setBackground(new java.awt.Color(242, 247, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(242, 247, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelCards.setBackground(new java.awt.Color(255, 255, 255));
        panelCards.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(panelCards, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, 970, 450));

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

        Añadir2.setBackground(new java.awt.Color(46, 49, 82));
        Añadir2.setText("Agregar Categoria");
        Añadir2.setColorHover(new java.awt.Color(0, 153, 51));
        Añadir2.setFont(new java.awt.Font("Humnst777 BlkCn BT", 1, 14)); // NOI18N
        Añadir2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Añadir2ActionPerformed(evt);
            }
        });
        jPanel2.add(Añadir2, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 90, 160, 40));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1090, 720));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(222, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(61, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 698, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void Añadir2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Añadir2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Añadir2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSButtonRiple Añadir2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel panelCards;
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
    // Clear the current panelCards content
    panelCards.removeAll();
    panelCards.setLayout(new java.awt.BorderLayout());

    // Create a new panel for the category view
    JPanel categoriaVista = new JPanel();
    categoriaVista.setBackground(new Color(242, 247, 255));
  

    // Add category title
    JLabel tituloCategoria = new JLabel("Categoría: " + categoriaNombre);
    tituloCategoria.setFont(new java.awt.Font("Segoe UI Black", 0, 20));

    // Create a panel to hold the product list
    JPanel productosPanel = new JPanel();
    productosPanel.setLayout(new java.awt.GridLayout(0, 1, 10, 10)); // Una columna, espaciado de 10
    productosPanel.setBackground(new Color(242, 247, 255));

    // Ejemplo de lista de productos (reemplaza con tu lógica real)
    java.util.List<String> productos = obtenerProductosPorCategoria(categoriaNombre);
    for (String producto : productos) {
        JLabel productoLabel = new JLabel(producto);
        productoLabel.setFont(new java.awt.Font("Segoe UI", 0, 16));
        productosPanel.add(productoLabel);
    }

    // Agregar el panel de productos al categoriaVista con desplazamiento si es necesario
    JScrollPane scrollProductos = new JScrollPane(productosPanel);
    scrollProductos.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    scrollProductos.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

    // Add "back" button
    rojeru_san.RSButtonRiple btnBack = new rojeru_san.RSButtonRiple();
    btnBack.setBackground(new java.awt.Color(46, 49, 82));
    btnBack.setText("Regresar");
    btnBack.setColorHover(new java.awt.Color(0, 153, 51));
    btnBack.setFont(new java.awt.Font("Humnst777 BlkCn BT", 1, 14));
    btnBack.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            // Return to the catalog view
            panelCards.removeAll();
            panelCards.setLayout(new BorderLayout());
            initializePagination();
            updateCardsDisplay();
            panelCards.revalidate();
            panelCards.repaint();
        }
    });


    // Add the category view to panelCards
    panelCards.add(categoriaVista, BorderLayout.CENTER);
    panelCards.revalidate();
    panelCards.repaint();
}

// Método de ejemplo para obtener productos por categoría (reemplaza con tu lógica real)
private java.util.List<String> obtenerProductosPorCategoria(String categoriaNombre) {
    java.util.List<String> productos = new java.util.ArrayList<>();
    switch (categoriaNombre.toLowerCase()) {
        case "cama":
            productos.add("Cama Individual");
            productos.add("Cama Queen");
            productos.add("Cama King");
            break;
        case "comedor":
            productos.add("Mesa de Comedor 6 Sillas");
            productos.add("Mesa de Comedor 4 Sillas");
            break;
        case "sala":
            productos.add("Sofá 3 Cuerpos");
            productos.add("Silla Reclinable");
            break;
        // Agrega más casos para otras categorías
        default:
            productos.add("No hay productos disponibles");
            break;
    }
    return productos;
}


}