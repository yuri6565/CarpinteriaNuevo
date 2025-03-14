package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Cliente;
import modelo.Conexion;

public class Ctrl_Cliente {
    
    public boolean guardar(Cliente objeto) {
        boolean respuesta = false;
        Connection con = Conexion.getConnection();

        try {
            String sql = "INSERT INTO cliente (identificacion, numero, nombre, apellido, telefono, direccion) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement consulta = con.prepareStatement(sql);
            
            consulta.setString(1, objeto.getIdentificacion());
            consulta.setInt(2, objeto.getNumero());
            consulta.setString(3, objeto.getNombre());
            consulta.setString(4, objeto.getApellido());
            consulta.setString(5, objeto.getTelefono());
            consulta.setString(6, objeto.getDireccion());
            
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            consulta.close(); 
            con.close(); 
         
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar cliente: " + e.getMessage());
        }
        
        return respuesta;
    }
    


public Cliente obtenerClientePorid(int id_cliente) {
    Cliente cliente = null;
    Connection con = Conexion.getConnection();
    
    try {
        String sql = "SELECT * FROM cliente WHERE id_cliente = ?";
        PreparedStatement consulta = con.prepareStatement(sql);
        consulta.setInt(1, id_cliente);
        
        ResultSet rs = consulta.executeQuery();
        
        if (rs.next()) {
            cliente = new Cliente();
            cliente.setid_cliente(rs.getInt("id_cliente"));
            cliente.setIdentificacion(rs.getString("identificacion"));
            cliente.setNumero(rs.getInt("numero"));
            cliente.setNombre(rs.getString("nombre"));
            cliente.setApellido(rs.getString("apellido"));
            cliente.setTelefono(rs.getString("telefono"));
            cliente.setDireccion(rs.getString("direccion"));
        }
        
        rs.close();
        consulta.close();
        con.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al obtener cliente: " + e.getMessage());
    }
    
    return cliente; 
}



    
    public boolean editar(Cliente objeto, int id_cliente){
        boolean respuesta= false;
        Connection con = Conexion.getConnection();
        
        try{
         String sql = "UPDATE cliente SET identificacion = ?, numero = ?, nombre = ?, apellido = ?, telefono = ?, direccion = ? WHERE id_cliente ='" + id_cliente + "'"; 
            PreparedStatement consulta = con.prepareStatement(sql);
            consulta.setString(1, objeto.getIdentificacion());
            consulta.setInt(2, objeto.getNumero());
            consulta.setString(3, objeto.getNombre());
            consulta.setString(4, objeto.getApellido());
            consulta.setString(5, objeto.getTelefono());
            consulta.setString(6, objeto.getDireccion());


           if(consulta.executeUpdate()>0){
               respuesta = true;
           }
           con.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al guardar cliente: " + e.getMessage());
        }
        return respuesta;
    }
    
     public boolean eliminar(int id_cliente) {
        boolean respuesta = false;
        Connection con = Conexion.getConnection();
        
        try {
            String sql = "DELETE FROM cliente WHERE id_cliente = ?";
            PreparedStatement consulta = con.prepareStatement(sql);
            consulta.setInt(1, id_cliente);
            
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            consulta.close();
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar cliente: " + e.getMessage());
        }
        
        return respuesta;
    }

    

    
}
