/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import modelo.Conexion;

/**
 *
 * @author Personal
 */
public class TestConexion {
    public static void main(String[] args) {
        if (Conexion.getConnection() != null) {
            System.out.println("✅ Conexión exitosa");
        } else {
            System.out.println("❌ Falló la conexión");
        }
    }
}
