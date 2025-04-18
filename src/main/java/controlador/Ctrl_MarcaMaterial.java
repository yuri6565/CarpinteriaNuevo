/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Conexion;
import modelo.Marca;

/**
 *
 * @author ZenBook
 */
public class Ctrl_MarcaMaterial {
    private final String tipo = "material";  // ⚙️ Fijamos el tipo automáticamente

    public boolean insertar(Marca marca) {
        String sql = "INSERT INTO marca (nombre, tipo) VALUES (?, ?)";
        try (Connection con = Conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, marca.getNombre());
            stmt.setString(2, tipo);  // 👈 Siempre 'material'
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            
            
            JOptionPane.showMessageDialog(null, "Error de conexión: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public List<Marca> obtenerCategoriasMaterial() {
        List<Marca> lista = new ArrayList<>();
        String sql = "SELECT * FROM marca WHERE tipo = ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, tipo);  // 👈 Solo 'material'
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(new Marca(rs.getInt("idmarca"), rs.getString("nombre")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean actualizar(Marca marca) {
        String sql = "UPDATE marca SET nombre = ? WHERE idmarca = ? AND tipo = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, marca.getNombre());
            stmt.setInt(2, marca.getCodigo());
            stmt.setString(3, tipo);  // 👈 Asegura que solo actualice de tipo material
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int idmarca) {
        String sql = "DELETE FROM marca WHERE idmarca = ? AND tipo = ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idmarca);
            stmt.setString(2, tipo);  // 👈 Asegura que solo borre si es material
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Marca> obtenerMarcaMaterial(String tipo) {
    List<Marca> lista = new ArrayList<>();
    String sql = "SELECT * FROM marca WHERE tipo = ?";
    try (Connection con = Conexion.getConnection();
         PreparedStatement stmt = con.prepareStatement(sql)) {
        stmt.setString(1, tipo);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            lista.add(new Marca(rs.getInt("idmarca"), rs.getString("nombre")));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return lista;
    }
    
}
