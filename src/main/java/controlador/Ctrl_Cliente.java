/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Cliente;
import modelo.Conexion;

 



public class Ctrl_Cliente {
    
    
    
    public boolean guardar(Cliente objeto){
    boolean respuesta = false;
     Connection cn= Conexion.getConnection();
     
     try{
     PreparedStatement consulta = cn.prepareStatement("INSERT INTO  cliente VALUES ?,?,?,?,?,?'");
     consulta.setInt(1, 0);
     consulta.setString(2, objeto.getIdentificacion());
     consulta.setInt(3, objeto.getNumero());
     consulta.setString(4, objeto.getNombre());
     consulta.setString(5,objeto.getApellido() );
     consulta.setString(6,objeto.getTelefono());
     consulta.setString(7, objeto.getDireccion());
     
     
     if(consulta.executeUpdate()>0){
         respuesta=true;
     }
     cn.close();
         
     }catch(SQLException e){
           
            JOptionPane.showMessageDialog(null, "error no se encuentra la base de datos");
        }
        return respuesta;
    
        
    }
    
}
