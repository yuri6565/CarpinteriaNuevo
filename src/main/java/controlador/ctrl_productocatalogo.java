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
import modelo.catalogoproducto;

/**
 *
 * @author buitr
 */
public class ctrl_productocatalogo {

    public static class productoConDetalles {

        private catalogoproducto producto;
        private int idproducto;
        private String nombre;
        private String categoria;

        public productoConDetalles(catalogoproducto producto, String nombre, String categoria) {
            this.producto = producto;
            this.nombre = nombre;
            this.categoria = categoria;
        }

        public catalogoproducto getProducto() {
            return producto;
        }

        public String getNombre() {
            return nombre;
        }

        public String getCategoria() {
            return categoria;
        }

        public int getIdproducto() {
            return idproducto;
        }

    }

    
// Modifica el método insertar
public boolean insertar(catalogoproducto producto) {
    String sql = "INSERT INTO catalogo_producto(nombre, Categoria_idCategoria, img) VALUES (?, ?, ?)";
    
    try (Connection con = Conexion.getConnection(); 
         PreparedStatement stmt = con.prepareStatement(sql)) {

        stmt.setString(1, producto.getNombre());
        stmt.setInt(2, Integer.parseInt(producto.getCategoria())); // Convertir a int
        stmt.setBytes(3, producto.getImagen());
        
        return stmt.executeUpdate() > 0;
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al insertar: " + e.getMessage());
        e.printStackTrace();
        return false;
    }
}

// Modifica el método obtenerproducto
public List<productoConDetalles> obtenerproducto() {
    List<productoConDetalles> lista = new ArrayList<>();
    String sql = "SELECT cp.idproducto, cp.nombre, cp.img, c.idCategoria, c.nombre AS nombre_categoria "
               + "FROM catalogo_producto cp "
               + "LEFT JOIN categoria c ON cp.Categoria_idCategoria = c.idCategoria";

    try (Connection con = Conexion.getConnection(); 
         PreparedStatement stmt = con.prepareStatement(sql); 
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            catalogoproducto producto = new catalogoproducto(
                rs.getInt("idproducto"),
                rs.getString("nombre"),
                rs.getString("nombre_categoria"), // Mostramos el nombre
                rs.getBytes("img")
            );

            lista.add(new productoConDetalles(
                producto,
                producto.getNombre(),
                rs.getString("nombre_categoria")
            ));
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al obtener productos: " + e.getMessage());
        e.printStackTrace();
    }
    return lista;
}
    
    

  

    public boolean actualizar(catalogoproducto producto) {
        String sql = "UPDATE catalogo_producto SET nombre = ?, Categoria_idCategoria = ?, img = ? "
                + "WHERE idproducto = ?";

        try (Connection con = Conexion.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, producto.getNombre());
            stmt.setString(2, producto.getCategoria()); // Asegúrate que coincida con el nombre de tu método getter
            stmt.setBytes(3, producto.getImagen());
            stmt.setInt(4, producto.getId());

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar producto: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int idProducto) {
        String sql = "DELETE FROM catalogo_producto WHERE idproducto = ?";

        try (Connection con = Conexion.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, idProducto);
            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar producto: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

}
