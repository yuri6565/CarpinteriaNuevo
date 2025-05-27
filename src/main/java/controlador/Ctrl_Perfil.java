package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Conexion;
import modelo.UsuarioModelo;

/**
 *
 * @author Personal
 */
public class Ctrl_Perfil {
   
    public UsuarioModelo obtenerUsuario(int id_usuario) {
    UsuarioModelo usuario = new UsuarioModelo();
    Connection cn = Conexion.getConnection();
    String sql = "SELECT * FROM usuario WHERE id_usuario = ?";
    try (PreparedStatement ps = cn.prepareStatement(sql)) {
        ps.setInt(1, id_usuario);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            usuario.setId_usuario(rs.getInt("id_usuario"));
            usuario.setNombre(rs.getString("nombre"));
            usuario.setApellido(rs.getString("apellido"));
            usuario.setCorreo_electronico(rs.getString("correo_electronico"));
            usuario.setUsuario(rs.getString("usuario"));
            usuario.setContrasena(rs.getString("contrasena"));
            usuario.setTelefono(rs.getString("telefono"));
            usuario.setRol(rs.getString("rol"));
        }

    } catch (SQLException e) {
        System.err.println("Error al obtener usuario: " + e.getMessage());
        // Aquí puedes lanzar una excepción si lo prefieres
        // throw new RuntimeException("Error al obtener usuario", e);
    }

    return usuario;
}


    // Método para actualizar los datos de un usuario
    public boolean actualizarUsuario(UsuarioModelo usuario, boolean esTrabajador) {
        boolean exito = false;
        Connection cn = Conexion.getConnection();
        String rolActual = obtenerUsuario(usuario.getId_usuario()).getRol();
        String rolFinal = esTrabajador ? rolActual : usuario.getRol();

        String sql = "UPDATE usuario SET nombre = ?, apellido = ?, correo_electronico = ?, usuario = ?, contrasena = ?, telefono = ?, rol = ? WHERE id_usuario = ?";
        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setString(3, usuario.getCorreo_electronico());
            ps.setString(4, usuario.getUsuario());
            ps.setString(5, usuario.getContrasena());
            ps.setString(6, usuario.getTelefono());
            ps.setString(7, rolFinal);
            ps.setInt(8, usuario.getId_usuario());

            int filasAfectadas = ps.executeUpdate();
            exito = filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.out.println("Error al conectar la base de datos, no se encuentra");
            JOptionPane.showMessageDialog(null, "Error al actualizar usuario");
        }

        return exito;
    }

    // Método para guardar un nuevo usuario
    public boolean guardar(UsuarioModelo objeto) {
        boolean respuesta = false;
        Connection con = Conexion.getConnection();

        try {
            String sql = "INSERT INTO usuario (nombre, apellido, usuario, contrasena, correo_electronico, telefono, rol) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement consulta = con.prepareStatement(sql);
            consulta.setString(1, objeto.getNombre());
            consulta.setString(2, objeto.getApellido());
            consulta.setString(3, objeto.getUsuario());
            consulta.setString(4, objeto.getContrasena());
            consulta.setString(5, objeto.getCorreo_electronico());
            consulta.setString(6, objeto.getTelefono());
            consulta.setString(7, objeto.getRol());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            consulta.close();
            con.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar usuario: " + e.getMessage());
        }

        return respuesta;
    }

   public List<UsuarioModelo> obtenerUsuarios() {
    List<UsuarioModelo> lista = new ArrayList<>();
    String sql = "SELECT * FROM usuario";

    try (Connection con = Conexion.getConnection();
         PreparedStatement stmt = con.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            UsuarioModelo usuario = new UsuarioModelo();
            usuario.setId_usuario(rs.getInt("id_usuario"));
            usuario.setNombre(rs.getString("nombre"));
            usuario.setApellido(rs.getString("apellido"));
            usuario.setUsuario(rs.getString("usuario"));
            usuario.setContrasena(rs.getString("contrasena"));
            usuario.setCorreo_electronico(rs.getString("correo_electronico"));
            usuario.setTelefono(rs.getString("telefono"));
            usuario.setRol(rs.getString("rol"));
            lista.add(usuario);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al obtener usuarios: " + e.getMessage());
    }

    return lista;
}
   
   public boolean existeUsuario(String usuario) {
    boolean existe = false;
    String sql = "SELECT id_usuario FROM usuario WHERE usuario = ?";

    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        
        ps.setString(1, usuario);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                existe = true;
            }
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al verificar existencia del usuario: " + e.getMessage());
    }

    return existe;
}



   public boolean editar(UsuarioModelo objeto, int id_usuario) {
    boolean respuesta = false;
    try (Connection con = Conexion.getConnection();
         PreparedStatement consulta = con.prepareStatement(
             "UPDATE usuario SET nombre = ?, apellido = ?, usuario = ?, contrasena = ?, correo_electronico = ?, telefono = ?, rol = ? WHERE id_usuario = ?")) {
        consulta.setString(1, objeto.getNombre());
        consulta.setString(2, objeto.getApellido());
        consulta.setString(3, objeto.getUsuario());
        consulta.setString(4, objeto.getContrasena());
        consulta.setString(5, objeto.getCorreo_electronico());
        consulta.setString(6, objeto.getTelefono());
        consulta.setString(7, objeto.getRol());
        consulta.setInt(8, id_usuario);
        respuesta = consulta.executeUpdate() > 0;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al editar usuario: " + e.getMessage());
    }
    return respuesta;
}

    public boolean eliminar(int id_usuario) {
        boolean respuesta = false;
        Connection con = Conexion.getConnection();

        try {
            String sql = "DELETE FROM usuario WHERE id_usuario = ?";
            PreparedStatement consulta = con.prepareStatement(sql);
            consulta.setInt(1, id_usuario);

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            consulta.close();
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar usuario: " + e.getMessage());
        }

        return respuesta;
    }

    
    
}
