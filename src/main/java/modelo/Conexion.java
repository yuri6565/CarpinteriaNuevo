
/*

 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
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
      String myBD = "jdbc:mysql://192.168.137.170:3306/carpinteriasistema?serverTimezone=UTC";
         con = DriverManager.getConnection(myBD, "grupojoseabel", "grupojoseabel1"); 
         
        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
        }
        return con; 
        
    }

  
}