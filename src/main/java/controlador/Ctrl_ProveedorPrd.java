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
import modelo.ProductoProveedor;

public class Ctrl_ProveedorPrd {
    
    
    
    private final String tipo = "prd";  // ️ Fijamos el tipo automáticamente

    public boolean insertar(ProductoProveedor proveedor) {
        String sql = "INSERT INTO proveedorprd (nombre) VALUES (?)";
        try (Connection con = Conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, proveedor.getNombre());
           
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de conexión: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public List<ProductoProveedor> obtenerCategoriasProductoProveedors() {
        List<ProductoProveedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM proveedorprd WHERE nombre = ?";
        
        try (Connection con = Conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, tipo);  //  Solo 'prd'
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(new ProductoProveedor(rs.getInt("idprdproveedor"), rs.getString("nombre")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean actualizar(ProductoProveedor proveedor) {
        String sql = "UPDATE proveedorprd SET nombre = ? WHERE idprdproveedor = ? ";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, proveedor.getNombre());
            stmt.setInt(2, proveedor.getCodigo());
           // 👈 Asegura que solo actualice de tipo prd
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int idprdproveedor) {
        String sql = "DELETE FROM proveedorprd WHERE idprdproveedor = ? AND tipo = ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idprdproveedor);
            stmt.setString(2, tipo);  // 👈 Asegura que solo borre si es prd
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<ProductoProveedor> obtenerProductoProveedors(String tipo) {
        List<ProductoProveedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM proveedorprd WHERE tipo = ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, tipo);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(new ProductoProveedor(rs.getInt("idprdproveedor"), rs.getString("nombre")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    
}
    
    

