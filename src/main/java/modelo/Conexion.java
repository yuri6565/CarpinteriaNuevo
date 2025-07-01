
/*

 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Yuri 5
 */
public class Conexion {

   public static Connection getConnection() {
      Connection con = null; 
        try {
        String myBD = "jdbc:mysql://localhost:3306/carpinteriasistema?serverTimezone=UTC";
         con = DriverManager.getConnection(myBD, "root", ""); 
         
        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
        }
        return con; 
        
    }

   
   
   // Encriptar contraseña con SHA-256
    public static String hashSHA256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuilder hex = new StringBuilder();
            for (byte b : hash) {
                hex.append(String.format("%02x", b));
            }
            return hex.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
  
}