package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
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

            consulta.close(); // Cierra el PreparedStatement
            con.close(); // Cierra la conexión
         
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar cliente: " + e.getMessage());
        }
        
        return respuesta;
    }
}
