package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Catalogocategoria;
import modelo.Conexion;

public class Ctrl_catalogocategoria {
    
    // Método para crear una nueva categoría
    public boolean crearCategoria(Catalogocategoria categoria) {
        String sql = "INSERT INTO categoria(nombre) VALUES (?)";
        
        try (Connection con = Conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, categoria.getNombre());
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        categoria.setIdCategoria(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al crear categoría: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    
    // Método para obtener todas las categorías
    public List<Catalogocategoria> obtenerTodasCategorias() {
        List<Catalogocategoria> categorias = new ArrayList<>();
        String sql = "SELECT idCategoria, nombre FROM categoria";
        
        try (Connection con = Conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Catalogocategoria cat = new Catalogocategoria(
                    rs.getInt("idCategoria"),
                    rs.getString("nombre")
                );
                categorias.add(cat);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener categorías: " + e.getMessage());
            e.printStackTrace();
        }
        return categorias;
    }
    
    // Método para actualizar una categoría
    public boolean actualizarCategoria(Catalogocategoria categoria) {
        String sql = "UPDATE categoria SET nombre = ? WHERE idCategoria = ?";
        
        try (Connection con = Conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setString(1, categoria.getNombre());
            stmt.setInt(2, categoria.getIdCategoria());
            
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar categoría: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    
    // Método para eliminar una categoría
    public boolean eliminarCategoria(int idCategoria) {
        String sql = "DELETE FROM categoria WHERE idCategoria = ?";
        
        try (Connection con = Conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setInt(1, idCategoria);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar categoría: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    
   public void cargarCategoriasEnCombo(RSMaterialComponent.RSComboBoxMaterial comboBox) {
    try {
        comboBox.removeAllItems();
        comboBox.addItem(new Catalogocategoria(0, "Seleccione categoría"));
        
        List<Catalogocategoria> categorias = obtenerTodasCategorias();
        if (categorias.isEmpty()) {
            System.out.println("No se encontraron categorías en la base de datos");
            JOptionPane.showMessageDialog(null, 
                "No hay categorías registradas", 
                "Advertencia", 
                JOptionPane.WARNING_MESSAGE);
        }
        
        for (Catalogocategoria cat : categorias) {
            comboBox.addItem(cat);
        }
    } catch (Exception e) {
        System.err.println("Error en cargarCategoriasEnCombo: " + e.getMessage());
        JOptionPane.showMessageDialog(null, 
            "Error al cargar categorías: " + e.getMessage(),
            "Error", 
            JOptionPane.ERROR_MESSAGE);
    }
}
}