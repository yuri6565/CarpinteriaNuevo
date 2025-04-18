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
import modelo.MaterialDatos;

/**
 *
 * @author ZenBook
 */
public class Ctrl_InventarioHerramienta {

    // Clase pública y estática para poder acceder desde fuera
    public static class MaterialConDetalles {

        private MaterialDatos material;
        private String nombreCategoria;
        private String nombreMarca;
        private String nombreUnidadMedida;

        public MaterialConDetalles(MaterialDatos material, String nombreCategoria,
                String nombreMarca, String nombreUnidadMedida) {
            this.material = material;
            this.nombreCategoria = nombreCategoria;
            this.nombreMarca = nombreMarca;
            this.nombreUnidadMedida = nombreUnidadMedida;
        }

        // Getters
        public MaterialDatos getMaterial() {
            return material;
        }

        public String getNombreCategoria() {
            return nombreCategoria != null ? nombreCategoria : "Sin categoría";
        }

        public String getNombreMarca() {
            return nombreMarca != null ? nombreMarca : "Sin marca";
        }

        public String getNombreUnidadMedida() {
            return nombreUnidadMedida != null ? nombreUnidadMedida : "Sin unidad de medida";
        }
    }

    public boolean insertar(MaterialDatos material) {
        String sql = "INSERT INTO inventario (nombre, descripcion, estado, stock, cantidad, tipo, "
                + "categoria_codigo, marca_idmarca, unidad_medida_idunidad_medida, imagen) "
                + "VALUES (?, ?, ?, ?, ?, 'herramienta', ?, ?, ?, ?)";
        try (Connection con = Conexion.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, material.getNombre());
            stmt.setString(2, material.getDescripcion());
            stmt.setString(3, material.getEstado());
            stmt.setString(4, material.getStock());
            stmt.setInt(5, material.getCantidad());
            stmt.setInt(6, material.getIdCategoria());
            stmt.setInt(7, material.getIdMarca());
            stmt.setInt(8, material.getIdUnidadMedida());
            stmt.setBytes(9, material.getImagen()); // imagen como byte[]

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<MaterialConDetalles> obtenerMateriales() {
        List<MaterialConDetalles> lista = new ArrayList<>();
        String sql = "SELECT i.*, c.nombre AS nombre_categoria, m.nombre AS nombre_marca, um.nombre AS nombre_unidad_medida "
                + "FROM inventario i "
                + "LEFT JOIN categoria c ON i.categoria_codigo = c.codigo "
                + "LEFT JOIN marca m ON i.marca_idmarca = m.idmarca "
                + "LEFT JOIN unidad_medida um ON i.unidad_medida_idunidad_medida = um.idunidad_medida "
                + "WHERE i.tipo = 'herramienta'";

        try (Connection con = Conexion.getConnection(); PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                MaterialDatos material = new MaterialDatos(
                        rs.getInt("id_inventario"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getString("estado"),
                        rs.getString("stock"),
                        rs.getInt("cantidad"),
                        rs.getInt("categoria_codigo"),
                        rs.getInt("marca_idmarca"),
                        rs.getInt("unidad_medida_idunidad_medida"),
                        rs.getBytes("imagen")
                );
                String nombreCategoria = rs.getString("nombre_categoria");
                String nombreMarca = rs.getString("nombre_marca");
                String nombreUnidadMedida = rs.getString("nombre_unidad_medida");
                lista.add(new MaterialConDetalles(material, nombreCategoria, nombreMarca, nombreUnidadMedida));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener materiales: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

    public boolean actualizar(MaterialDatos material) {
        String sql = "UPDATE inventario SET nombre = ?, descripcion = ?, estado = ?, stock = ?, cantidad = ?, "
                + "categoria_codigo = ?, marca_idmarca = ?, unidad_medida_idunidad_medida = ?, imagen = ? "
                + "WHERE id_inventario = ? AND tipo = 'herramienta'";

        try (Connection con = Conexion.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, material.getNombre());
            stmt.setString(2, material.getDescripcion());
            stmt.setString(3, material.getEstado());
            stmt.setString(4, material.getStock());
            stmt.setInt(5, material.getCantidad());
            stmt.setInt(6, material.getIdCategoria());
            stmt.setInt(7, material.getIdMarca());
            stmt.setInt(8, material.getIdUnidadMedida());
            stmt.setBytes(9, material.getImagen());
            stmt.setInt(10, material.getIdInventario());

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar material: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int idInventario) {
        String sql = "DELETE FROM inventario WHERE id_inventario = ? AND tipo = 'herramienta'";

        try (Connection con = Conexion.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, idInventario);
            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar material: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

}
