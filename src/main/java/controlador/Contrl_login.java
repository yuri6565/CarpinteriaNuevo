package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import modelo.Usuario;
import modelo.Conexion;

public class Contrl_login {
    private Usuario usuario;

    //ola
    //perro
    public boolean loginUser(Usuario usuario) {
    try {
        Connection con = Conexion.getConnection();
        String sql = "SELECT usuario, contrasena, rol FROM usuario WHERE usuario = ? AND contrasena = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, usuario.getUsuario());
        ps.setString(2, usuario.getContrasena());
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            // Si las credenciales son correctas, establece el rol
            usuario.setRol(rs.getString("rol")); // Asume que hay un campo "rol" en tu DB
            return true;
        }
        return false;
    } catch (SQLException e) {
        System.err.println("Error en login: " + e.getMessage());
        return false;
    }
}
}
