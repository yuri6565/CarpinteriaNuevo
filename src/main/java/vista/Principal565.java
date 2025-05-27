/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import controlador.Ctrl_Perfil;
import vista.Caja.Caja;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import modelo.UsuarioModelo;
import rojeru_san.RSButton;

import vista.Cotizacion.cotizacion;
import vista.Inventario0.herramientas;
import vista.Inventario0.materiales;
import vista.Produccion.Produccion;
import vista.catalogo.catalogo;



/**
 *
 * @author Personal
 */
public class Principal565 extends javax.swing.JFrame {

    private JPanel submenuInventario;
    private boolean submenuVisible = false; // Para controlar si el submenú está visible
    private rojeru_san.RSButton item1; // Botón "Materiales" del submenú
    private rojeru_san.RSButton item2; // Botón "Herramientas" del submenú
     private int idUsuarioValido ;
    private String nombreUsuario;
     private int idUsuario;
     private Ctrl_Perfil controlador;
private rojeru_san.RSButton item3; 
private rojeru_san.RSButton item4;


    //bfhfhb
    // Nuevas variables para el submenú de Ventas
    private JPanel submenuVentas;
    private boolean submenuVentasVisible = false; // Para controlar si el submenú de Ventas está visible
   

   public Principal565(int idUsuario) {
        this.idUsuario = idUsuario;
        this.controlador = new Ctrl_Perfil();
        this.item3 = new rojeru_san.RSButton(); // ✔️ usa la variable de instancia
this.item4 = new rojeru_san.RSButton(); // ✔️

         boolean oscuro = TemaManager.getInstance().isOscuro();
        initComponents();
        aplicarTema();
         
        cargarUsuarioLogueado();
       cargarrol();
       
        jPanel4.setVisible(true);
        jPanel5.setVisible(false);
        jPanel3.setVisible(false);




//submenu inventario------------------
        // Inicializar el submenú
        submenuInventario = new JPanel();
        submenuInventario.setBackground(new Color(29, 30, 81)); // Mismo color que el menú
        submenuInventario.setLayout(new GridLayout(2, 1, 0, 0)); // 3 filas, 1 columna, espacio entre ítems
        submenuInventario.setPreferredSize(new Dimension(250, 80)); // Reducir altura total para reflejar menos espacio

        // Añadir ítems al submenú (como botones RSButton)
        //boton para materiales
        rojeru_san.RSButton item1 = new rojeru_san.RSButton();
        item1.setBackground(new Color(29, 30, 81)); // Fondo igual que el menú
        item1.setForeground(Color.WHITE);
        item1.setFont(new Font("Tahoma", Font.BOLD, 15));
        item1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 60, 1, 1)); // Margen izquierdo para alinear con el texto del menú
        item1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        item1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        item1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tratar-con-cuidado.png")));
        item1.setIconTextGap(10);
        item1.setText("Materiales");
        item1.setColorHover(new Color(150, 150, 150)); // Mismo color de hover que los botones del menú
        item1.setColorTextHover(Color.WHITE);

        //boton para herramientas
        rojeru_san.RSButton item2 = new rojeru_san.RSButton();
        item2.setBackground(new Color(29, 30, 81)); // Fondo igual que el menú
        item2.setForeground(Color.WHITE);
        item2.setFont(new Font("Tahoma", Font.BOLD, 15));
        item2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 60, 1, 1)); // Margen izquierdo para alinear con el texto del menú
        item2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        item2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        item2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/llave-inglesa.png")));
        item2.setIconTextGap(10);
        item2.setText("Herramientas");
        item2.setColorHover(new Color(150, 150, 150)); // Mismo color de hover que los botones del menú
        item2.setColorTextHover(Color.WHITE);

        // Configurar el ActionListener para "Materiales"
        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deseleccionar();
                dos.setSelected(true); // Mantener "Inventario" resaltado

                // Cargar la vista de Materiales
                materiales m = new materiales();
                m.setSize(1290, 730);
                m.setLocation(0, 0);
                contenedor.removeAll();
                contenedor.add(m);
                contenedor.revalidate();
                contenedor.repaint();

                animacion();
            }
        });

// Configurar el ActionListener para "Herramientas"
        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                deseleccionar();
                dos.setSelected(true);

                // Cargar la vista de Herramientas
                herramientas h = new herramientas();
                h.setSize(1290, 730);
                h.setLocation(0, 0);
                contenedor.removeAll();
                contenedor.add(h);
                contenedor.revalidate();
                contenedor.repaint();

                animacion();
            }
        });

        submenuInventario.add(item1);
        submenuInventario.add(item2);
//submenu inventario------------------

//submenu ventas------------------
// Inicializar el submenú de Ventas
        submenuVentas = new JPanel();
        submenuVentas.setBackground(new Color(29, 30, 81)); // Mismo color que el menú
        submenuVentas.setLayout(new java.awt.GridLayout(2, 1, 0, 0)); // 2 filas, 1 columna
        submenuVentas.setPreferredSize(new Dimension(250, 100)); // Alto de 100 (50 por cada botón)

        // Botón "Pedidos"
        item3 = new rojeru_san.RSButton();
        item3.setBackground(new Color(29, 30, 81));
        item3.setForeground(Color.WHITE);
        item3.setFont(new Font("Tahoma", Font.BOLD, 15));
        item3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 60, 1, 1));
        item3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        item3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        item3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bolsa-de-la-compra.png"))); // Puedes cambiar el icono
        item3.setIconTextGap(10);
        item3.setText("Pedidos");
        item3.setColorHover(new Color(150, 150, 150));
        item3.setColorTextHover(Color.WHITE);

        // Botón "Cotización"
        item4 = new rojeru_san.RSButton();
        item4.setBackground(new Color(29, 30, 81));
        item4.setForeground(Color.WHITE);
        item4.setFont(new Font("Tahoma", Font.BOLD, 15));
        item4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 60, 1, 1));
        item4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        item4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        item4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/solicitud-de-cotizacion.png"))); // Puedes cambiar el icono
        item4.setIconTextGap(10);
        item4.setText("Cotización");
        item4.setColorHover(new Color(150, 150, 150));
        item4.setColorTextHover(Color.WHITE);

        // ActionListener para "Pedidos"
        item3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Deseleccionar otros botones del menú principal
                deseleccionar();
                cinco.setSelected(true); // Mantener "Ventas" resaltado

                // Cargar la vista de Pedidos
                vista.Ventas.pedido pedidos = new vista.Ventas.pedido(contenedor);
                pedidos.setSize(1290, 730);
                pedidos.setLocation(0, 0);
                contenedor.removeAll();
                contenedor.add(pedidos);
                contenedor.revalidate();
                contenedor.repaint();
                animacion();
            }

        });

        // ActionListener para "Cotización"
        item4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Deseleccionar otros botones del menú principal
                deseleccionar();
                cinco.setSelected(true); // Mantener "Ventas" resaltado

                // Cargar la vista de Cotización (temporalmente un mensaje)
                cotizacion co = new cotizacion();
                co.setSize(1290, 730); // Ajustar tamaño dinámicamente
                co.setLocation(0, 0);

                contenedor.removeAll();
                contenedor.add(co);
                contenedor.revalidate();
                contenedor.repaint();
                animacion();
            }
        });

        submenuVentas.add(item3);
        submenuVentas.add(item4);
//submenu ventas------------------

        // Seleccionar el botón "uno" por defecto y cargar el panel Escritorio1
        this.uno.setSelected(true);
        Escritorio1 es = new Escritorio1();
        es.setSize(1290, 730); // Ajustar tamaño dinámicamente
        es.setLocation(0, 0);

        contenedor.removeAll();
        contenedor.add(es, BorderLayout.CENTER);
        contenedor.revalidate();
        contenedor.repaint();

    }
   
    private void cambiarEstiloBotonRS(RSButton boton, Color fondo, Color texto) {
    boton.setBackground(fondo);                // Fondo (si es compatible en tu versión)
    boton.setColorText(texto);                 // Texto
    boton.setColorHover(new Color(72, 92, 188)); // Hover fijo azul
    boton.setColorTextHover(texto); 
}

private void aplicarTema() {
    Color fondoPrincipal, fondoPanel, fondoBoton, textoBoton, textoLabel, contenedorc;
    String rutaLogo;

    // Use TemaManager to get the current theme
    boolean oscuro = TemaManager.getInstance().isOscuro();

    if (oscuro) {
   fondoPrincipal = new Color(21,21,33);  
        fondoPanel = new Color(21,21,33);     
        fondoBoton = new Color(30, 30, 45);      
        textoBoton = Color.WHITE;                
        textoLabel = Color.WHITE;                
        contenedorc = new Color(21,21,33);
jPanel1.setBackground (new Color(21,21,33)); 
jPanel2.setBackground(new Color(30,30,45));
jPanel3.setBackground(new Color(30,30,45)); 
jPanel4.setBackground(new Color(30,30,45)); 
contenedor.setBackground(new Color(21,21,33)); 
jPanel5.setBackground(new Color(30,30,45));     
lblUsuarioLogueado.setForeground(Color.WHITE);
rolusuario.setForeground(Color.WHITE);
lblTituloPrincipal.setForeground(Color.WHITE);

        uno.setIcon(new ImageIcon(getClass().getResource("/imagenes/casa.png")));
        dos.setIcon(new ImageIcon(getClass().getResource("/imagenes/caja-negra.png")));
        tres.setIcon(new ImageIcon(getClass().getResource("/imagenes/proveedor-de-servicio.png")));
        cuatro.setIcon(new ImageIcon(getClass().getResource("/imagenes/productivity_1.png")));
        cinco.setIcon(new ImageIcon(getClass().getResource("/imagenes/etiqueta-de-precio.png")));
        seis.setIcon(new ImageIcon(getClass().getResource("/imagenes/caja-registradora_1.png")));
        siete1.setIcon(new ImageIcon(getClass().getResource("/imagenes/gestion-de-usuarios_1.png")));
        ocho.setIcon(new ImageIcon(getClass().getResource("/imagenes/public-service_1.png")));
        nueve.setIcon(new ImageIcon(getClass().getResource("/imagenes/catalogar.png")));
        Diez.setIcon(new ImageIcon(getClass().getResource("/imagenes/ajustesblanco.png")));
       // rSPanelImage3.setImagen(new ImageIcon(getClass().getResource("/imagenes/logo_blanco.png")));
        rSLabelImage3.setIcon(new ImageIcon(getClass().getResource("/imagenes/luna (6).png")));
        rSLabelImage1.setIcon(new ImageIcon(getClass().getResource("/imagenes/feliz-sol-negro.png")));

        uno1.setIcon(new ImageIcon(getClass().getResource("/imagenes/casa.png")));
        dos1.setIcon(new ImageIcon(getClass().getResource("/imagenes/caja-negra.png")));
        tres1.setIcon(new ImageIcon(getClass().getResource("/imagenes/proveedor-de-servicio.png")));
        cuatro1.setIcon(new ImageIcon(getClass().getResource("/imagenes/productivity_1.png")));
        cinco1.setIcon(new ImageIcon(getClass().getResource("/imagenes/etiqueta-de-precio.png")));
        seis1.setIcon(new ImageIcon(getClass().getResource("/imagenes/caja-registradora_1.png")));
        siete2.setIcon(new ImageIcon(getClass().getResource("/imagenes/gestion-de-usuarios_1.png")));
        ocho1.setIcon(new ImageIcon(getClass().getResource("/imagenes/public-service_1.png")));
        nueve1.setIcon(new ImageIcon(getClass().getResource("/imagenes/catalogar.png")));
        menu.setIcon(new ImageIcon(getClass().getResource("/imagenes/burger-bar.png")));
      
        //rSPanelImage3.setImagen(new ImageIcon(getClass().getResource("/imagenes/logo_blanco.png")));



    } else {
     fondoPrincipal = new Color(255, 255, 255);
    fondoPanel = new Color(242, 247, 255);
    fondoBoton = new Color(255, 255, 255);
    textoBoton = Color.BLACK;
    textoLabel = Color.BLACK;
    contenedorc = new Color(242, 247, 255);
    
    jPanel1.setBackground (new Color(242, 247, 255)); // [21, 21, 33] - Fondo principal
jPanel2.setBackground(new Color(255, 255, 255)); // [21, 21, 33] - Fondo principal
jPanel3.setBackground(new Color(255, 255, 255)); // [21, 21, 33] - Fondo principal
jPanel4.setBackground(new Color(255, 255, 255)); // [21, 21, 33] - Fondo principal
contenedor.setBackground(new Color(242, 247, 255)); // [30, 30, 45] - Barra derecha
jPanel5.setBackground(new Color(242, 247, 255));    // Azul claro (242, 247, 255)
lblUsuarioLogueado.setForeground(textoLabel);
rolusuario.setForeground(textoLabel);
lblTituloPrincipal.setForeground(textoLabel);


        uno.setIcon(new ImageIcon(getClass().getResource("/imagenes/home.png")));
        dos.setIcon(new ImageIcon(getClass().getResource("/imagenes/caja-blanca.png")));
        tres.setIcon(new ImageIcon(getClass().getResource("/imagenes/proveedor-de-servicio_1.png")));
        cuatro.setIcon(new ImageIcon(getClass().getResource("/imagenes/productivity.png")));
        cinco.setIcon(new ImageIcon(getClass().getResource("/imagenes/etiqueta-del-precionegro.png")));
        seis.setIcon(new ImageIcon(getClass().getResource("/imagenes/caja-registradora.png")));
        siete1.setIcon(new ImageIcon(getClass().getResource("/imagenes/gestion-de-usuarios.png")));
        ocho.setIcon(new ImageIcon(getClass().getResource("/imagenes/public-service.png")));
        nueve.setIcon(new ImageIcon(getClass().getResource("/imagenes/catalogarnegro.png")));
        Diez.setIcon(new ImageIcon(getClass().getResource("/imagenes/ajustess.png")));
        menu.setIcon(new ImageIcon(getClass().getResource("/imagenes/menu22.png")));
        rSLabelImage1.setIcon(new ImageIcon(getClass().getResource("/imagenes/feliz-sol (6).png")));
        rSLabelImage3.setIcon(new ImageIcon(getClass().getResource("/imagenes/luna (7).png")));

        uno1.setIcon(new ImageIcon(getClass().getResource("/imagenes/home.png")));
        dos1.setIcon(new ImageIcon(getClass().getResource("/imagenes/caja-blanca.png")));
        tres1.setIcon(new ImageIcon(getClass().getResource("/imagenes/proveedor-de-servicio_1.png")));
        cuatro1.setIcon(new ImageIcon(getClass().getResource("/imagenes/productivity.png")));
        cinco1.setIcon(new ImageIcon(getClass().getResource("/imagenes/etiqueta-del-precionegro.png")));
        seis1.setIcon(new ImageIcon(getClass().getResource("/imagenes/caja-registradora.png")));
        siete2.setIcon(new ImageIcon(getClass().getResource("/imagenes/gestion-de-usuarios.png")));
        ocho1.setIcon(new ImageIcon(getClass().getResource("/imagenes/public-service.png")));
        nueve1.setIcon(new ImageIcon(getClass().getResource("/imagenes/catalogarnegro.png")));
        rSPanelImage3.setImagen(new ImageIcon(getClass().getResource("/imagenes/logo_azul.png")));

}



    // Botones
    cambiarEstiloBotonRS(uno, fondoBoton, textoBoton);
    cambiarEstiloBotonRS(dos, fondoBoton, textoBoton);
    cambiarEstiloBotonRS(tres, fondoBoton, textoBoton);
    cambiarEstiloBotonRS(cuatro, fondoBoton, textoBoton);
    cambiarEstiloBotonRS(cinco, fondoBoton, textoBoton);
    cambiarEstiloBotonRS(seis, fondoBoton, textoBoton);
    cambiarEstiloBotonRS(siete1, fondoBoton, textoBoton);
    cambiarEstiloBotonRS(ocho, fondoBoton, textoBoton);
    cambiarEstiloBotonRS(nueve, fondoBoton, textoBoton);
    cambiarEstiloBotonRS(Diez, fondoBoton, textoBoton);
    cambiarEstiloBotonRS(menu, fondoBoton, textoBoton);

    cambiarEstiloBotonRS(uno1, fondoBoton, textoBoton);
    cambiarEstiloBotonRS(dos1, fondoBoton, textoBoton);
    cambiarEstiloBotonRS(tres1, fondoBoton, textoBoton);
    cambiarEstiloBotonRS(cuatro1, fondoBoton, textoBoton);
    cambiarEstiloBotonRS(cinco1, fondoBoton, textoBoton);
    cambiarEstiloBotonRS(seis1, fondoBoton, textoBoton);
    cambiarEstiloBotonRS(siete2, fondoBoton, textoBoton);
    cambiarEstiloBotonRS(ocho1, fondoBoton, textoBoton);
    cambiarEstiloBotonRS(nueve1, fondoBoton, textoBoton);

    // Labels
  
}
  private void configurarPopupMenu() {  
    JPopupMenu userPopupMenu = new JPopupMenu();  
    userPopupMenu.setOpaque(false);  
    userPopupMenu.setBackground(new Color(255, 255, 255));  
    userPopupMenu.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));  
  
    // Crear los ítems del menú  
    JMenuItem settingsItem = new JMenuItem("Configuraciones");  
    JMenuItem profileItem = new JMenuItem("Perfil");  
    JMenuItem messagesItem = new JMenuItem("Mensajes");  
    JMenuItem logoutItem = new JMenuItem("Salir");  
  
    // Personalizar los ítems sin hover
    Font menuFont = new Font("Arial", Font.PLAIN, 14);  
    Dimension itemSize = new Dimension(150, 30);  
  
    settingsItem.setFont(menuFont);  
    settingsItem.setForeground(new Color(100, 100, 100));  
    settingsItem.setBackground(new Color(255, 255, 255));  
    settingsItem.setOpaque(false);  
    settingsItem.setPreferredSize(itemSize);  
    settingsItem.setBorderPainted(false);  // Eliminar bordes al hacer hover
  
    profileItem.setFont(menuFont);  
    profileItem.setForeground(new Color(100, 100, 100));  
    profileItem.setBackground(new Color(255, 255, 255));  
    profileItem.setOpaque(false);  
    profileItem.setPreferredSize(itemSize);  
    profileItem.setBorderPainted(false);
  
    messagesItem.setFont(menuFont);  
    messagesItem.setForeground(new Color(100, 100, 100));  
    messagesItem.setBackground(new Color(255, 255, 255));  
    messagesItem.setOpaque(false);  
    messagesItem.setPreferredSize(itemSize);  
    messagesItem.setBorderPainted(false);
  
    logoutItem.setFont(menuFont);  
    logoutItem.setForeground(new Color(100, 100, 100));  
    logoutItem.setBackground(new Color(255, 255, 255));  
    logoutItem.setOpaque(false);  
    logoutItem.setPreferredSize(itemSize);  
    logoutItem.setBorderPainted(false);
  
    // Agregar ítems al menú  
    userPopupMenu.add(settingsItem);  
    userPopupMenu.add(profileItem);  
    userPopupMenu.add(messagesItem);  
    userPopupMenu.add(logoutItem);  
  
    // Mostrar el JPopupMenu al hacer clic en rSLabelCircleImage1  
    rSLabelCircleImage1.addMouseListener(new MouseAdapter() {  
        @Override  
        public void mouseClicked(MouseEvent e) {  
            userPopupMenu.show(rSLabelCircleImage1, 0, rSLabelCircleImage1.getHeight());  
        }  
    });  
  
    // Acciones de los ítems del menú  
    settingsItem.addActionListener(e -> JOptionPane.showMessageDialog(null, "Abriendo configuración..."));  
    profileItem.addActionListener(e -> {  
        // Cargar la vista de InformacionBasica en el contenedor  
        perfil1 info = new perfil1(idUsuario);  
  
        info.setSize(1290, 730);  
        info.setLocation(0, 0);  
        contenedor.removeAll();  
        contenedor.add(info);  
        contenedor.revalidate();  
        contenedor.repaint();  
    });  
    messagesItem.addActionListener(e -> JOptionPane.showMessageDialog(null, "Abriendo mensajes..."));  
    logoutItem.addActionListener(e -> System.exit(0));  
}

    private void animacion() {
        int posicion = jPanel3.getX();
        System.out.println("Posición actual: " + posicion); // Debug
        if (posicion >= -1) {  // Cambiar a >= para mayor seguridad
            Animacion.Animacion.mover_izquierda(0, -258, 2, 2, jPanel3);
            Animacion.Animacion.mover_izquierda(258, +111, 2, 2, contenedor);

            jPanel4.setVisible(true);
            jPanel5.setVisible(false);
            jPanel3.setVisible(false);
        }
    }

    private void animation_salir() {
        int posicion = jPanel3.getX();
        System.out.println("Posición actual: " + posicion); // Debug
        if (posicion >= -1) {
            Animacion.Animacion.mover_derecha(-258, 0, 2, 2, jPanel3);
            Animacion.Animacion.mover_derecha(-2, +258, 2, 2, contenedor);

            jPanel3.setVisible(true);
            jPanel4.setVisible(false);
            jPanel5.setVisible(true);
            jPanel5.setOpaque(false);
        } else {
            Animacion.Animacion.mover_derecha(-258, 0, 2, 2, jPanel3);
            Animacion.Animacion.mover_derecha(-2, +258, 2, 2, contenedor);

            jPanel3.setVisible(true);
            jPanel4.setVisible(false);
            jPanel5.setVisible(true);
            jPanel5.setOpaque(false);
        }

    }

    private void ocultarSubmenus() {
        // Cerrar submenú de Inventario si está visible
        if (submenuVisible) {
            jPanel3.removeAll();
            jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            // Restaurar posiciones originales de los botones con un alto de 50 píxeles
            jPanel3.add(rSPanelImage3, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 41, 158, 153));
            jPanel3.add(uno, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 250, 50));
            jPanel3.add(dos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 250, 50));
            jPanel3.add(tres, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 250, 50));
            jPanel3.add(cuatro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 250, 50));
            jPanel3.add(cinco, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, 250, 50));
            jPanel3.add(seis, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 450, 250, 50));
            jPanel3.add(siete1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 500, 250, 50));
            jPanel3.add(ocho, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 550, 250, 50));
            jPanel3.add(nueve, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 600, 250, 50));

            dos.setText(" Inventario           ▼");
            submenuVisible = false;
            animacion();
        }

        // Cerrar submenú de Ventas si está visible
        if (submenuVentasVisible) {
            jPanel3.removeAll();
            jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            // Restaurar posiciones originales de los botones con un alto de 50 píxeles
            jPanel3.add(rSPanelImage3, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 41, 158, 153));
            jPanel3.add(uno, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 250, 50));
            jPanel3.add(dos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 250, 50));
            jPanel3.add(tres, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 250, 50));
            jPanel3.add(cuatro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 250, 50));
            jPanel3.add(cinco, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, 250, 50));
            jPanel3.add(seis, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 450, 250, 50));
            jPanel3.add(siete1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 500, 250, 50));
            jPanel3.add(ocho, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 550, 250, 50));
            jPanel3.add(nueve, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 600, 250, 50));

            cinco.setText(" Ventas                ▼");
            submenuVentasVisible = false;

        }

        jPanel3.revalidate();
        jPanel3.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        contenedor = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        menu = new rojeru_san.RSButton();
        rSLabelImage1 = new rojerusan.RSLabelImage();
        rSSwitch1 = new rojerusan.RSSwitch();
        rSLabelImage3 = new rojerusan.RSLabelImage();
        rSLabelIcon1 = new rojerusan.RSLabelIcon();
        rSLabelCircleImage1 = new rojerusan.RSLabelCircleImage();
        lblUsuarioLogueado = new javax.swing.JLabel();
        rolusuario = new javax.swing.JLabel();
        lblTituloPrincipal = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        dos = new rojeru_san.RSButton();
        tres = new rojeru_san.RSButton();
        cuatro = new rojeru_san.RSButton();
        cinco = new rojeru_san.RSButton();
        seis = new rojeru_san.RSButton();
        ocho = new rojeru_san.RSButton();
        siete1 = new rojeru_san.RSButton();
        nueve = new rojeru_san.RSButton();
        uno = new rojeru_san.RSButton();
        rSPanelImage3 = new rojerusan.RSPanelImage();
        Diez = new rojeru_san.RSButton();
        jPanel4 = new javax.swing.JPanel();
        uno1 = new rojeru_san.RSButton();
        dos1 = new rojeru_san.RSButton();
        tres1 = new rojeru_san.RSButton();
        cuatro1 = new rojeru_san.RSButton();
        cinco1 = new rojeru_san.RSButton();
        seis1 = new rojeru_san.RSButton();
        siete2 = new rojeru_san.RSButton();
        ocho1 = new rojeru_san.RSButton();
        nueve1 = new rojeru_san.RSButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setMinimumSize(new java.awt.Dimension(1290, 730));
        jPanel5.setPreferredSize(new java.awt.Dimension(1290, 730));
        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel5MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 810, Short.MAX_VALUE)
        );

        contenedor.setBackground(new java.awt.Color(255, 255, 255));
        contenedor.setMinimumSize(new java.awt.Dimension(1290, 730));
        contenedor.setPreferredSize(new java.awt.Dimension(1290, 730));

        javax.swing.GroupLayout contenedorLayout = new javax.swing.GroupLayout(contenedor);
        contenedor.setLayout(contenedorLayout);
        contenedorLayout.setHorizontalGroup(
            contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1498, Short.MAX_VALUE)
        );
        contenedorLayout.setVerticalGroup(
            contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 840, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(29, 30, 81));

        menu.setBackground(new java.awt.Color(29, 30, 81));
        menu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/burger-bar.png"))); // NOI18N
        menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuActionPerformed(evt);
            }
        });

        rSLabelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/feliz-sol-negro.png"))); // NOI18N

        rSSwitch1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSSwitch1MouseClicked(evt);
            }
        });

        rSLabelImage3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/luna (6).png"))); // NOI18N

        rSLabelIcon1.setForeground(new java.awt.Color(0, 0, 0));
        rSLabelIcon1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.NOTIFICATIONS);

        rSLabelCircleImage1.setBackground(new java.awt.Color(29, 30, 81));
        rSLabelCircleImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/WhatsApp Image 2025-03-28 at 11.10.17 AM.jpeg"))); // NOI18N
        rSLabelCircleImage1.setColorBorde(new java.awt.Color(29, 30, 81));
        rSLabelCircleImage1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSLabelCircleImage1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                rSLabelCircleImage1MouseEntered(evt);
            }
        });

        lblUsuarioLogueado.setBackground(new java.awt.Color(0, 0, 0));
        lblUsuarioLogueado.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        lblUsuarioLogueado.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuarioLogueado.setText("Escritorio");

        rolusuario.setBackground(new java.awt.Color(0, 0, 0));
        rolusuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rolusuario.setForeground(new java.awt.Color(255, 255, 255));
        rolusuario.setText("Escritorio");

        lblTituloPrincipal.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        lblTituloPrincipal.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloPrincipal.setText("Escritorio");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(260, 260, 260)
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTituloPrincipal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 656, Short.MAX_VALUE)
                .addComponent(rSLabelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(rSSwitch1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(rSLabelImage3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(rSLabelIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rSLabelCircleImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lblUsuarioLogueado))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rolusuario)))
                .addGap(172, 172, 172))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rSLabelCircleImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblUsuarioLogueado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rolusuario))
                    .addComponent(rSLabelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSSwitch1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSLabelImage3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTituloPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rSLabelIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(29, 30, 81));

        dos.setBackground(new java.awt.Color(29, 30, 81));
        dos.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 30, 1, 1));
        dos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/caja.png"))); // NOI18N
        dos.setText("Inventario           ▼");
        dos.setColorHover(new java.awt.Color(128, 128, 128));
        dos.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        dos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        dos.setMaximumSize(new java.awt.Dimension(127, 24));
        dos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dosMouseEntered(evt);
            }
        });
        dos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dosActionPerformed(evt);
            }
        });

        tres.setBackground(new java.awt.Color(29, 30, 81));
        tres.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 30, 1, 1));
        tres.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proveedor-de-servicio.png"))); // NOI18N
        tres.setText(" Proveedores");
        tres.setColorHover(new java.awt.Color(128, 128, 128));
        tres.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        tres.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        tres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tresActionPerformed(evt);
            }
        });

        cuatro.setBackground(new java.awt.Color(29, 30, 81));
        cuatro.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 30, 1, 1));
        cuatro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/productivity.png"))); // NOI18N
        cuatro.setText(" Producción");
        cuatro.setColorHover(new java.awt.Color(128, 128, 128));
        cuatro.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        cuatro.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cuatro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cuatroActionPerformed(evt);
            }
        });

        cinco.setBackground(new java.awt.Color(29, 30, 81));
        cinco.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 30, 1, 1));
        cinco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/etiqueta-de-precio.png"))); // NOI18N
        cinco.setText(" Ventas                ▼");
        cinco.setColorHover(new java.awt.Color(128, 128, 128));
        cinco.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        cinco.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cinco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cincoActionPerformed(evt);
            }
        });

        seis.setBackground(new java.awt.Color(29, 30, 81));
        seis.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 30, 1, 1));
        seis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/caja-registradora_1.png"))); // NOI18N
        seis.setText(" Caja");
        seis.setColorHover(new java.awt.Color(128, 128, 128));
        seis.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        seis.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        seis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seisActionPerformed(evt);
            }
        });

        ocho.setBackground(new java.awt.Color(29, 30, 81));
        ocho.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 30, 1, 1));
        ocho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/public-service_1.png"))); // NOI18N
        ocho.setText("Gestion de clientes");
        ocho.setToolTipText("");
        ocho.setColorHover(new java.awt.Color(128, 128, 128));
        ocho.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        ocho.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ocho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ochoActionPerformed(evt);
            }
        });

        siete1.setBackground(new java.awt.Color(29, 30, 81));
        siete1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 30, 1, 1));
        siete1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestion-de-usuarios.png"))); // NOI18N
        siete1.setText("Gestion de usuarios");
        siete1.setColorHover(new java.awt.Color(128, 128, 128));
        siete1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        siete1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        siete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siete1ActionPerformed(evt);
            }
        });

        nueve.setBackground(new java.awt.Color(29, 30, 81));
        nueve.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 30, 1, 1));
        nueve.setIcon(new javax.swing.ImageIcon(getClass().getResource("/public-service_1.png"))); // NOI18N
        nueve.setText("Catalogo");
        nueve.setToolTipText("");
        nueve.setColorHover(new java.awt.Color(128, 128, 128));
        nueve.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        nueve.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        nueve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nueveActionPerformed(evt);
            }
        });

        uno.setBackground(new java.awt.Color(29, 30, 81));
        uno.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 30, 1, 1));
        uno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casa.png"))); // NOI18N
        uno.setText("Escritorio");
        uno.setToolTipText("");
        uno.setColorHover(new java.awt.Color(128, 128, 128));
        uno.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        uno.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        uno.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        uno.setIconTextGap(10);
        uno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unoActionPerformed(evt);
            }
        });

        rSPanelImage3.setImagen(null);

        javax.swing.GroupLayout rSPanelImage3Layout = new javax.swing.GroupLayout(rSPanelImage3);
        rSPanelImage3.setLayout(rSPanelImage3Layout);
        rSPanelImage3Layout.setHorizontalGroup(
            rSPanelImage3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 158, Short.MAX_VALUE)
        );
        rSPanelImage3Layout.setVerticalGroup(
            rSPanelImage3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 153, Short.MAX_VALUE)
        );

        Diez.setBackground(new java.awt.Color(29, 30, 81));
        Diez.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 30, 1, 1));
        Diez.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ajustes (1).png"))); // NOI18N
        Diez.setText("Configuracion");
        Diez.setToolTipText("");
        Diez.setColorHover(new java.awt.Color(128, 128, 128));
        Diez.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Diez.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Diez.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DiezActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(rSPanelImage3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(uno, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(dos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(siete1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tres, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(seis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cuatro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cinco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ocho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Diez, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nueve, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(rSPanelImage3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(uno, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(dos, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(197, 197, 197)
                        .addComponent(siete1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tres, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addComponent(seis, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(cuatro, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(cinco, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(247, 247, 247)
                        .addComponent(ocho, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(nueve, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Diez, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(208, 208, 208))
        );

        jPanel4.setBackground(new java.awt.Color(29, 30, 81));

        uno1.setBackground(new java.awt.Color(29, 30, 81));
        uno1.setBorder(null);
        uno1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casa.png"))); // NOI18N
        uno1.setColorHover(new java.awt.Color(200, 200, 200));
        uno1.setColorTextHover(new java.awt.Color(0, 0, 0));
        uno1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        uno1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                uno1MouseEntered(evt);
            }
        });
        uno1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uno1ActionPerformed(evt);
            }
        });

        dos1.setBackground(new java.awt.Color(29, 30, 81));
        dos1.setBorder(null);
        dos1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/caja.png"))); // NOI18N
        dos1.setColorHover(new java.awt.Color(169, 169, 169));
        dos1.setColorTextHover(new java.awt.Color(0, 0, 0));
        dos1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        dos1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dos1MouseEntered(evt);
            }
        });
        dos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dos1ActionPerformed(evt);
            }
        });

        tres1.setBackground(new java.awt.Color(29, 30, 81));
        tres1.setBorder(null);
        tres1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proveedor-de-servicio.png"))); // NOI18N
        tres1.setColorHover(new java.awt.Color(128, 128, 128));
        tres1.setColorTextHover(new java.awt.Color(0, 0, 0));
        tres1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tres1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tres1MouseEntered(evt);
            }
        });
        tres1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tres1ActionPerformed(evt);
            }
        });

        cuatro1.setBackground(new java.awt.Color(29, 30, 81));
        cuatro1.setBorder(null);
        cuatro1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/productivity.png"))); // NOI18N
        cuatro1.setColorHover(new java.awt.Color(200, 200, 200));
        cuatro1.setColorTextHover(new java.awt.Color(0, 0, 0));
        cuatro1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        cuatro1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cuatro1MouseEntered(evt);
            }
        });
        cuatro1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cuatro1ActionPerformed(evt);
            }
        });

        cinco1.setBackground(new java.awt.Color(29, 30, 81));
        cinco1.setBorder(null);
        cinco1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/etiqueta-de-precio.png"))); // NOI18N
        cinco1.setColorHover(new java.awt.Color(128, 128, 128));
        cinco1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        cinco1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cinco1MouseEntered(evt);
            }
        });
        cinco1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cinco1ActionPerformed(evt);
            }
        });

        seis1.setBackground(new java.awt.Color(29, 30, 81));
        seis1.setBorder(null);
        seis1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/caja-registradora_1.png"))); // NOI18N
        seis1.setColorHover(new java.awt.Color(128, 128, 128));
        seis1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        seis1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                seis1MouseEntered(evt);
            }
        });
        seis1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seis1ActionPerformed(evt);
            }
        });

        siete2.setBackground(new java.awt.Color(29, 30, 81));
        siete2.setBorder(null);
        siete2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestion-de-usuarios.png"))); // NOI18N
        siete2.setColorHover(new java.awt.Color(128, 128, 128));
        siete2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        siete2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                siete2MouseEntered(evt);
            }
        });
        siete2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siete2ActionPerformed(evt);
            }
        });

        ocho1.setBackground(new java.awt.Color(29, 30, 81));
        ocho1.setBorder(null);
        ocho1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/public-service_1.png"))); // NOI18N
        ocho1.setColorHover(new java.awt.Color(128, 128, 128));
        ocho1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        ocho1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ocho1MouseEntered(evt);
            }
        });
        ocho1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ocho1ActionPerformed(evt);
            }
        });

        nueve1.setBackground(new java.awt.Color(29, 30, 81));
        nueve1.setBorder(null);
        nueve1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/public-service_1.png"))); // NOI18N
        nueve1.setColorHover(new java.awt.Color(128, 128, 128));
        nueve1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        nueve1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                nueve1MouseEntered(evt);
            }
        });
        nueve1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nueve1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(uno1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(dos1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(ocho1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(nueve1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(siete2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(cinco1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(seis1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(tres1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(cuatro1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(200, 200, 200)
                .addComponent(uno1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(dos1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(250, 250, 250)
                        .addComponent(ocho1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(300, 300, 300)
                        .addComponent(nueve1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(200, 200, 200)
                        .addComponent(siete2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(cinco1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(seis1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tres1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(cuatro1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(contenedor, javax.swing.GroupLayout.PREFERRED_SIZE, 1498, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 1322, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(contenedor, javax.swing.GroupLayout.PREFERRED_SIZE, 840, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 810, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        contenedor.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ochoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ochoActionPerformed
        ocultarSubmenus(); // Ocultar submenú si ya está visible
        if (!this.ocho.isSelected()) {
            deseleccionar();
            this.ocho.setSelected(true);
             VistaClientes cliente = new VistaClientes();
            cliente.setSize(1290, 730);
            cliente.setLocation(0, 0);

            contenedor.removeAll();
            contenedor.add(cliente);
            contenedor.revalidate();
            contenedor.repaint();

        }
        animacion();

    }//GEN-LAST:event_ochoActionPerformed

    private void seisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seisActionPerformed
        ocultarSubmenus(); // Ocultar submenú si ya está visible
        if (!this.seis.isSelected()) {
            deseleccionar();
            this.seis.setSelected(true);

            Caja caja = new Caja();
            caja.setSize(1290, 730);
            caja.setLocation(0, 0);

            contenedor.removeAll();
            contenedor.add(caja);
            contenedor.revalidate();
            contenedor.repaint();
        }
        animacion();

    }//GEN-LAST:event_seisActionPerformed

    private void cincoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cincoActionPerformed

        if (!this.cinco.isSelected()) {
            deseleccionar();
            this.cinco.setSelected(true);

            if (!submenuVentasVisible) {
                ocultarSubmenus(); // Cerrar otros submenús
                jPanel3.removeAll();
                jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                // Reubicar los botones con el submenú de Ventas visible
                jPanel3.add(rSPanelImage3, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 41, 158, 153));
                jPanel3.add(uno, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 250, 50));
                jPanel3.add(dos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 250, 50));
                jPanel3.add(tres, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 250, 50));
                jPanel3.add(cuatro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 250, 50));
                jPanel3.add(cinco, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, 250, 50));
                jPanel3.add(submenuVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 450, 250, 100)); // Submenú de Ventas
                jPanel3.add(seis, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 550, 250, 50));
                jPanel3.add(siete1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 600, 250, 50));
                jPanel3.add(ocho, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 650, 250, 50));
                jPanel3.add(nueve, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 700, 250, 50));
                jPanel1.add(contenedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 20, 1290, 730));

                cinco.setText(" Ventas                ▲");
                submenuVentasVisible = true;
                jPanel3.revalidate();
                jPanel3.repaint();
            }
        } else {
            ocultarSubmenus();
            this.cinco.setSelected(false);
        }

    }//GEN-LAST:event_cincoActionPerformed

    private void cuatroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cuatroActionPerformed
        ocultarSubmenus();
        if (!this.cuatro.isSelected()) {
            deseleccionar();
            this.cuatro.setSelected(true);

            Produccion pr = new Produccion(new javax.swing.JFrame());
            pr.setSize(1290, 730);
            pr.setLocation(0, 0);

            contenedor.removeAll();
            contenedor.add(pr);
            contenedor.revalidate();
            contenedor.repaint();

        }

        animacion();

    }//GEN-LAST:event_cuatroActionPerformed

    private void tresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tresActionPerformed
        ocultarSubmenus();
        if (!this.tres.isSelected()) {
            deseleccionar();
            this.tres.setSelected(true);

            Proveedor pr = new Proveedor();
            pr.setSize(1290, 730);
            pr.setLocation(0, 0);

            contenedor.removeAll();
            contenedor.add(pr);
            contenedor.revalidate();
            contenedor.repaint();

        }
        animacion();

    }//GEN-LAST:event_tresActionPerformed

    private void unoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unoActionPerformed
        ocultarSubmenus();
        if (!this.uno.isSelected()) {
            deseleccionar();
            this.uno.setSelected(true);

            Escritorio1 es = new Escritorio1();
            es.setSize(1090, 690);
            es.setLocation(0, 0);

            contenedor.removeAll();
            contenedor.add(es);
            contenedor.revalidate();
            contenedor.repaint();

        }
        animacion();
    }//GEN-LAST:event_unoActionPerformed

    private void dosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dosActionPerformed
        if (!this.dos.isSelected()) {
            deseleccionar();
            this.dos.setSelected(true);

        }
        // Alternar visibilidad del submenú
        if (!submenuVisible) {
            ocultarSubmenus();// Asegurarse de que el estado anterior esté limpio
            jPanel3.removeAll();
            jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            // Reubicar los botones con el submenú visible
            jPanel3.add(rSPanelImage3, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 41, 158, 153));
            jPanel3.add(uno, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 250, 50));
            jPanel3.add(dos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 250, 50));
            jPanel3.add(submenuInventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 250, 100)); // Submenú (Materiales + Herramientas)
            jPanel3.add(tres, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, 250, 50));
            jPanel3.add(cuatro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 450, 250, 50));
            jPanel3.add(cinco, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 500, 250, 50));
            jPanel3.add(seis, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 550, 250, 50));
            jPanel3.add(siete1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 600, 250, 50));
            jPanel3.add(ocho, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 650, 250, 50));
            jPanel3.add(nueve, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 700, 250, 50));
            jPanel1.add(contenedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 20, 1290, 730));

            dos.setText(" Inventario           ▲"); // Submenú abierto

            submenuVisible = true;
            jPanel3.revalidate();
            jPanel3.repaint();
        } else {
            ocultarSubmenus(); // Ocultar submenú si ya está visible
            this.dos.setSelected(false);
        }

    }//GEN-LAST:event_dosActionPerformed


    private void siete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siete1ActionPerformed
        ocultarSubmenus();
        if (!this.siete1.isSelected()) {
            deseleccionar();
            this.siete1.setSelected(true);
            // Crear y mostrar el panel de inventario
     Usuarios1 usu = new Usuarios1();  
            usu.setSize(1290, 730);
            usu.setLocation(0, 0);

            contenedor.removeAll();
            contenedor.add(usu);
            contenedor.revalidate();
            contenedor.repaint();

        }
        animacion();
    }//GEN-LAST:event_siete1ActionPerformed

    private void uno1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uno1ActionPerformed

    }//GEN-LAST:event_uno1ActionPerformed

    private void dos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dos1ActionPerformed

    }//GEN-LAST:event_dos1ActionPerformed

    private void tres1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tres1ActionPerformed

    }//GEN-LAST:event_tres1ActionPerformed

    private void cuatro1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cuatro1ActionPerformed

    }//GEN-LAST:event_cuatro1ActionPerformed

    private void cinco1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cinco1ActionPerformed

    }//GEN-LAST:event_cinco1ActionPerformed

    private void seis1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seis1ActionPerformed

    }//GEN-LAST:event_seis1ActionPerformed

    private void siete2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siete2ActionPerformed

    }//GEN-LAST:event_siete2ActionPerformed

    private void ocho1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ocho1ActionPerformed

    }//GEN-LAST:event_ocho1ActionPerformed

    private void uno1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_uno1MouseEntered
        animation_salir();
    }//GEN-LAST:event_uno1MouseEntered

    private void cuatro1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cuatro1MouseEntered
        animation_salir();
    }//GEN-LAST:event_cuatro1MouseEntered

    private void dos1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dos1MouseEntered
        animation_salir();
    }//GEN-LAST:event_dos1MouseEntered

    private void tres1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tres1MouseEntered
        animation_salir();
    }//GEN-LAST:event_tres1MouseEntered

    private void cinco1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cinco1MouseEntered
        animation_salir();
    }//GEN-LAST:event_cinco1MouseEntered

    private void seis1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seis1MouseEntered
        animation_salir();
    }//GEN-LAST:event_seis1MouseEntered

    private void siete2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_siete2MouseEntered
        animation_salir();
    }//GEN-LAST:event_siete2MouseEntered

    private void ocho1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ocho1MouseEntered
        animation_salir();
    }//GEN-LAST:event_ocho1MouseEntered

    private void jPanel5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseExited

    }//GEN-LAST:event_jPanel5MouseExited

    private void menuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuActionPerformed
        animacion();

    }//GEN-LAST:event_menuActionPerformed

    private void jPanel5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseEntered
        animacion();
    }//GEN-LAST:event_jPanel5MouseEntered

    private void nueveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nueveActionPerformed
        if (!this.nueve.isSelected()) {
            deseleccionar();
            this.nueve.setSelected(true);

            // Crear y mostrar el panel de inventario
          catalogo cat = new catalogo(new javax.swing.JFrame(), true);
           //catalogo cat = new catalogo(this, submenuVisible);
            cat.setSize(1290, 730);
            cat.setLocation(0, 0);

            contenedor.removeAll();
            contenedor.add(cat);
            contenedor.revalidate();
            contenedor.repaint();

        }
        animacion();
    }//GEN-LAST:event_nueveActionPerformed

    private void nueve1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nueve1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_nueve1MouseEntered

    private void nueve1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nueve1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nueve1ActionPerformed

    private void DiezActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DiezActionPerformed
        // TODO add your handling code here:

        if (!this.Diez.isSelected()) {
            deseleccionar();
            this.Diez.setSelected(true);

            // Crear y mostrar el panel de inventario
            config cong = new config();
            cong.setSize(1290, 730);
            cong.setLocation(0, 0);

            contenedor.removeAll();
            contenedor.add(cong);
            contenedor.revalidate();
            contenedor.repaint();

        }
        animacion();
    }//GEN-LAST:event_DiezActionPerformed

    private void dosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dosMouseEntered

    }//GEN-LAST:event_dosMouseEntered

    private void rSSwitch1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSSwitch1MouseClicked

        boolean nuevoTema = !TemaManager.getInstance().isOscuro();
        TemaManager.getInstance().guardarTema(nuevoTema);
        aplicarTema(); // Update this panel
    }//GEN-LAST:event_rSSwitch1MouseClicked

    private void rSLabelCircleImage1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSLabelCircleImage1MouseClicked
        configurarPopupMenu();
        // Acciones de los ítems del menú
    }//GEN-LAST:event_rSLabelCircleImage1MouseClicked

    private void rSLabelCircleImage1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSLabelCircleImage1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_rSLabelCircleImage1MouseEntered

    /**
     * @param args the command line arguments
     */
     public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold desc=" Look and feel setting code (optional) ">
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal111.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal111.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal111.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal111.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Nota: No ejecutes este main directamente en el flujo real.
                // Inicia desde Login11211.java para pasar el idUsuario real.
                // Usa un idUsuario de prueba solo para depuración local.
                int idUsuarioPrueba = Integer.parseInt(args.length > 0 ? args[0] : "0");
                new Principal565(idUsuarioPrueba).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSButton Diez;
    private rojeru_san.RSButton cinco;
    private rojeru_san.RSButton cinco1;
    private javax.swing.JPanel contenedor;
    private rojeru_san.RSButton cuatro;
    private rojeru_san.RSButton cuatro1;
    private rojeru_san.RSButton dos;
    private rojeru_san.RSButton dos1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lblTituloPrincipal;
    private javax.swing.JLabel lblUsuarioLogueado;
    private rojeru_san.RSButton menu;
    private rojeru_san.RSButton nueve;
    private rojeru_san.RSButton nueve1;
    private rojeru_san.RSButton ocho;
    private rojeru_san.RSButton ocho1;
    private rojerusan.RSLabelCircleImage rSLabelCircleImage1;
    private rojerusan.RSLabelIcon rSLabelIcon1;
    private rojerusan.RSLabelImage rSLabelImage1;
    private rojerusan.RSLabelImage rSLabelImage3;
    private rojerusan.RSPanelImage rSPanelImage3;
    private rojerusan.RSSwitch rSSwitch1;
    private javax.swing.JLabel rolusuario;
    private rojeru_san.RSButton seis;
    private rojeru_san.RSButton seis1;
    private rojeru_san.RSButton siete1;
    private rojeru_san.RSButton siete2;
    private rojeru_san.RSButton tres;
    private rojeru_san.RSButton tres1;
    private rojeru_san.RSButton uno;
    private rojeru_san.RSButton uno1;
    // End of variables declaration//GEN-END:variables

    private void deseleccionar() {
        this.uno.setSelected(false);
        this.dos.setSelected(false);
        this.tres.setSelected(false);
        this.cuatro.setSelected(false);
        this.cinco.setSelected(false);
        this.seis.setSelected(false);
        this.ocho.setSelected(false);
        this.nueve.setSelected(false);
        this.siete1.setSelected(false);
    }
    
    
    
    private void cargarUsuarioLogueado() {
        if (idUsuario == 0) {
            lblUsuarioLogueado.setText("Usuario logueado: No identificado");
            return;
        }
        UsuarioModelo usuario = controlador.obtenerUsuario(idUsuario);
        if (usuario.getId_usuario() != 0) {
           lblUsuarioLogueado.setText(usuario.getNombre() + " " + usuario.getApellido());

        } else {
            lblUsuarioLogueado.setText("Usuario logueado: No encontrado (ID: " + idUsuario + ")");
        }
    }
        
   private void cargarrol() {
if (idUsuario == 0) {
            rolusuario.setText("Rol: No identificado");
            return;
        }
 UsuarioModelo usuario = controlador.obtenerUsuario(idUsuario);

if (usuario.getId_usuario() != 0) {
           rolusuario.setText(usuario.getRol());

        } else {
            rolusuario.setText("Usuario logueado: No encontrado (ID: " + idUsuario + ")");
        }
}

}
