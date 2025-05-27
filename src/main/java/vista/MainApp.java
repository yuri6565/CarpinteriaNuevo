/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

/**
 *
 * @author Personal
 */
public class MainApp {
    public static void main(String[] args) {
        // Esto asegura que la interfaz gráfica se crea en el hilo correcto
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Suponiendo que tu clase de login se llama Login
                new Login1121().setVisible(true);
            }
        });
    }
}
