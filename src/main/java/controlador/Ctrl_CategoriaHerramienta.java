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
import modelo.Categoria;
import modelo.Conexion;

/**
 *
 * @author ZenBook
 */
public class Ctrl_CategoriaHerramienta {
    private final String tipo = "herramienta";  // ⚙️ Fijamos el tipo automáticamente

    public boolean insertar(Categoria categoria) {
        String sql = "INSERT INTO categoria (nombre, tipo) VALUES (?, ?)";
        try (Connection con = Conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, categoria.getNombre());
            stmt.setString(2, tipo);  // 👈 Siempre 'material'
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de conexión: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public List<Categoria> obtenerCategoriasHerramienta() {
        List<Categoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM categoria WHERE tipo = 'herramienta'"; // Filtrar por tipo en la base de datos

        try (Connection con = Conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Categoria categoria = new Categoria(
                    rs.getInt("codigo"),
                    rs.getString("nombre")
                );
                lista.add(categoria);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener categorías: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

    public boolean actualizar(Categoria categoria) {
        String sql = "UPDATE categoria SET nombre = ? WHERE codigo = ? AND tipo = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, categoria.getNombre());
            stmt.setInt(2, categoria.getCodigo());
            stmt.setString(3, tipo);  // 👈 Asegura que solo actualice de tipo material
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int codigo) {
        String sql = "DELETE FROM categoria WHERE codigo = ? AND tipo = ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            stmt.setString(2, tipo);  // 👈 Asegura que solo borre si es material
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
        public List<Categoria> obtenerCategoriaHerramienta(String tipo) {
    List<Categoria> lista = new ArrayList<>();
    String sql = "SELECT * FROM categoria WHERE tipo = ?";
    try (Connection con = Conexion.getConnection();
         PreparedStatement stmt = con.prepareStatement(sql)) {
        stmt.setString(1, tipo);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            lista.add(new Categoria(rs.getInt("codigo"), rs.getString("nombre")));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return lista;
    }
        
        
}
