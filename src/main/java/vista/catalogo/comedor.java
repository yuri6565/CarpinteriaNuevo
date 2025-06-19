/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista.catalogo;

import javax.swing.ImageIcon;

/**
 *
 * @author buitr
 */
public class comedor extends javax.swing.JPanel {

    ImageIcon[] img;
    
    public comedor() {
        initComponents();
       
        
        img  = new ImageIcon[6];
        
    }

    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rSLabelImage1 = new rojerusan.RSLabelImage();
        rSLabelImage2 = new rojerusan.RSLabelImage();
        rSLabelImage3 = new rojerusan.RSLabelImage();
        rSLabelImage4 = new rojerusan.RSLabelImage();

        setBackground(new java.awt.Color(204, 204, 204));
        setPreferredSize(new java.awt.Dimension(1250, 630));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSLabelImage1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        rSLabelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/escritorio.jpg"))); // NOI18N
        add(rSLabelImage1, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 160, -1, -1));

        rSLabelImage2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        add(rSLabelImage2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, -1, -1));

        rSLabelImage3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        add(rSLabelImage3, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 160, -1, -1));

        rSLabelImage4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        rSLabelImage4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/escritorio.jpg"))); // NOI18N
        add(rSLabelImage4, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 160, -1, -1));
    }// </editor-fold>//GEN-END:initComponents
public void cargar (){
    
    
    
}
javax.swing.JPanel jPanel1;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSLabelImage rSLabelImage1;
    private rojerusan.RSLabelImage rSLabelImage2;
    private rojerusan.RSLabelImage rSLabelImage3;
    private rojerusan.RSLabelImage rSLabelImage4;
    // End of variables declaration//GEN-END:variables
}
