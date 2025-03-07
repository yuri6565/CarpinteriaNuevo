 package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Consulta_Usuarios extends Conexion {
    
    public String generarCodigo() {
        Random rand = new Random();
        int codigo = 100000 + rand.nextInt(900000); 
        return String.valueOf(codigo);
    }

    public String recuperarCuenta(String usuario, String correo_electronico) {
        PreparedStatement ps = null;
        Connection con = getConnection();
        ResultSet res = null;
        String codigoRecuperacion = null;
        String query = "SELECT id_usuario FROM usuario WHERE usuario=? AND correo_electronico=?";

        try {
            ps = con.prepareStatement(query);
            ps.setString(1, usuario);
            ps.setString(2, correo_electronico);
            res = ps.executeQuery();

            if (res.next()) { 
                int idUsuario = res.getInt("id_usuario");
                codigoRecuperacion = generarCodigo();

               
                String insertQuery = "INSERT INTO recuperacion (correo_electronico, codigo, fecha_generacion, usuario_id_usuario) VALUES (?, ?, NOW(), ?)";
                try (PreparedStatement psInsert = con.prepareStatement(insertQuery)) {
                    psInsert.setString(1, correo_electronico);
                    psInsert.setInt(2, Integer.parseInt(codigoRecuperacion));
                    psInsert.setInt(3, idUsuario);
                    psInsert.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Consulta_Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try { if (res != null) res.close(); } catch (SQLException ex) {}
            try { if (ps != null) ps.close(); } catch (SQLException ex) {}
            try { if (con != null) con.close(); } catch (SQLException ex) {}
        }
        
        
        
       

        return codigoRecuperacion;
    }
public String obtenerCodigoDesdeCorreo(String correo) {
    PreparedStatement ps = null;
    Connection con = getConnection();
    ResultSet res = null;
    String usuario = null;
    String query = "SELECT usuario FROM usuario WHERE correo_electronico=?";

    try {
        if (con == null) {
            System.out.println("ERROR: No hay conexión a la base de datos.");
            return null;
        }

        if (correo == null || correo.trim().isEmpty()) {
            System.out.println("ERROR: El correo es nulo o vacío.");
            return null;
        }

        System.out.println("Correo recibido: '" + correo + "'");

        ps = con.prepareStatement(query);
        ps.setString(1, correo.trim()); 
        System.out.println("Ejecutando consulta: " + ps.toString());

        res = ps.executeQuery();

        if (res.next()) {
            usuario = res.getString("usuario");
            System.out.println("Usuario encontrado: " + usuario);
        } else {
            System.out.println("No se encontró un usuario con ese correo.");
        }

    } catch (SQLException e) {
        System.out.println("Error SQL: " + e.getMessage());
        e.printStackTrace();
    } finally {
        try {
            if (res != null) res.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    return usuario;
}



public boolean validarcodigo(String correo, String codigo) {
    PreparedStatement ps = null;
    Connection con = getConnection();
    ResultSet res = null;
    boolean codigovalido = false; 

    String query = "SELECT codigo FROM recuperacion WHERE codigo = ? AND correo_electronico = ?";

    try {
        ps = con.prepareStatement(query);
        ps.setString(1, codigo); 
        ps.setString(2, correo); 
        res = ps.executeQuery();

        if (res.next()) {
            codigovalido = true;
            System.out.println("Código validado: " + codigovalido);
        } else {
            System.out.println("Código inválido");
        }
    } catch (SQLException ex) {
        Logger.getLogger(Consulta_Usuarios.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        try { if (res != null) res.close(); } catch (SQLException ex) {}
        try { if (ps != null) ps.close(); } catch (SQLException ex) {}
    }

    return codigovalido; 
}

public boolean actualizarContrasena(String correo, String nuevaContrasena) {
    PreparedStatement ps = null;
    Connection con = getConnection(); 
    boolean actualizado = false;

    String query = "UPDATE usuario SET contrasena = ? WHERE correo_electronico = ?";

    try {
        ps = con.prepareStatement(query);
        ps.setString(1, nuevaContrasena);
        ps.setString(2, correo);

        int filasAfectadas = ps.executeUpdate();
        if (filasAfectadas > 0) {
            actualizado = true;
            System.out.println("Contraseña actualizada correctamente.");
        } else {
            System.out.println("Error: No se encontró el usuario con ese correo.");
        }
    } catch (SQLException ex) {
        Logger.getLogger(Consulta_Usuarios.class.getName()).log(Level.SEVERE, "Error SQL al actualizar contraseña", ex);
    } finally {
        try { if (ps != null) ps.close(); } catch (SQLException ex) {}
    }

    return actualizado;

}
}


    

    


